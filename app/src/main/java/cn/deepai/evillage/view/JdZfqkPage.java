package cn.deepai.evillage.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.PkhzfqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class JdZfqkPage extends BasePage implements BasePage.IDataEdit{

    private PkhzfqkBean serverData;
    private PkhzfqkBean localData;
    // 住房面积
    private EditText zfmj;
    // 主要结构
    private TextView fwzyjg;
    // 建房时间
    private TextView jfsj;
    // 是否危房
    private TextView zyzfsfwf;
    // 异地搬迁扶贫情况
    private TextView ydfpbqqk;


    public JdZfqkPage(Context context) {
        this(context, null);
    }

    public JdZfqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdZfqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_jdzfqk, this);
        ButterKnife.bind(this);
        localData = new PkhzfqkBean();
        initView();
    }

    @OnClick(R.id.zfqk_fwjg_layout)
    public void onFwzyjgClick() {
        final String[] jgValues = new String[7];
        for (int i = 0;i < jgValues.length;i++) {
            jgValues[i] = DictionaryUtil.getValueName("FWJG",String.valueOf(i));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_zfqk_fwjg),
                jgValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        fwzyjg.setText(data);
                        for (int i = 0;i < jgValues.length;i++) {
                            if (data.equals(jgValues[i])) {
                                localData.setFwzyjg(String.valueOf(i));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.zfqk_sfwf_layout)
    public void onSfwfClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_zfqk_sfwf),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        zyzfsfwf.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setZyzfsfwf("0");
                        } else localData.setZyzfsfwf("1");
                    }
                });
    }

    @OnClick(R.id.zfqk_ydfpbqqk_layout)
    public void onBqqkClick() {
        final String[] bqValues = new String[]{
                DictionaryUtil.getValueName("YDBQQK","Y"),
                DictionaryUtil.getValueName("YDBQQK","X"),
                DictionaryUtil.getValueName("YDBQQK","Q"),
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_zfqk_ydfpbqqk),
                bqValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        ydfpbqqk.setText(data);
                        if (data.equals(bqValues[0])) {
                                localData.setFwzyjg("Y");
                        } else if (data.equals(bqValues[1])) {
                            localData.setFwzyjg("X");
                        } else if (data.equals(bqValues[2])) {
                            localData.setFwzyjg("Q");
                        }
                    }
                });
    }

    @OnClick(R.id.zfqk_jfsj_layout)
    public void onJfsjClick() {

        DialogManager.showDateDialog(mContext,mContext.getString(R.string.pkh_zfqk_jfsj),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        jfsj.setText(data);
                        localData.setJfsj(data);
                    }
                });
    }

    @Override
    public void registeEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisteEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhzfqkBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    // 点击保存按钮
    @Override
    public void saveData() {
        localData.setZfmj(zfmj.getText().toString());

        final PkhzfqkBean zfqkBean = localData;
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updatePkhZfqkJbxx);

        final Gson gson = new Gson();
        ((PkhxqActivity)mContext).tryToShowProcessDialog();
        if (TextUtils.isEmpty(localData.getId())) {
            zfqkBean.setHid(SettingManager.getCurrentJdPkh().getHid());
            zfqkBean.setTjnd(SettingManager.getCurrentJdPkh().getJdnf());
            EVRequest.request(Action.ACTION_ADD_PKHZFQKJBXX, gson.toJson(header), gson.toJson(zfqkBean),
                    new ResponseCallback() {
                        @Override
                        public void onDataResponse(String dataJsonString) {
                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                            EventBus.getDefault().post(returnValueEvent);
                        }
                    });
        } else {
            EVRequest.request(Action.ACTION_UPDATE_PKHZFQKJBXX, gson.toJson(header), gson.toJson(zfqkBean),
                    new ResponseCallback() {
                        @Override
                        public void onDataResponse(String dataJsonString) {
                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                            EventBus.getDefault().post(returnValueEvent);
                        }
                    });
        }
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHZFQKJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhZfqkJbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhzfqkBean responseEvent = requestGson.fromJson(dataJsonString, PkhzfqkBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_zfqk);
    }

    private void bindData(PkhzfqkBean pkhzfqkBean) {
        this.localData = pkhzfqkBean;
        this.serverData = pkhzfqkBean;
        zfmj.setText(pkhzfqkBean.getZfmj());
        fwzyjg.setText(DictionaryUtil.getValueName("FWJG",pkhzfqkBean.getFwzyjg()));
        jfsj.setText(pkhzfqkBean.getJfsj());
        zyzfsfwf.setText(DictionaryUtil.getValueName(pkhzfqkBean.getZyzfsfwf()));
        ydfpbqqk.setText(DictionaryUtil.getValueName("YDBQQK",pkhzfqkBean.getYdfpbqqk()));
        mHasData = true;
    }

    private void initView() {
        zfmj = (EditText) findViewById(R.id.zfqk_zfmj);
        fwzyjg = (TextView) findViewById(R.id.zfqk_fwjg);
        jfsj = (TextView) findViewById(R.id.zfqk_jfsj);
        zyzfsfwf = (TextView) findViewById(R.id.zfqk_sfwf);
        ydfpbqqk = (TextView) findViewById(R.id.zfqk_ydfpbqqk);
        mHasData = false;
    }
}
