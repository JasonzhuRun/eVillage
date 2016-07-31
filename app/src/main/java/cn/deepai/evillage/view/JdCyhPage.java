package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhcyhqkBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.model.bean.PkhzfqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.JdDataSaveEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 参与产业化组织情况
 */
public class JdCyhPage extends BasePage {

    private PkhcyhqkBean serverData;
    private PkhcyhqkBean localData;

    private TextView cylx;
    private TextView cyzzlx;
    private EditText cyzsy;
    private TextView cjfphzzjzz;
    private EditText trhzzzje;
    private TextView cynyhzzz;
    private EditText ltqyddsy;

    public JdCyhPage(Context context) {
        this(context, null);
    }

    public JdCyhPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdCyhPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_jdcyhqk, this);
        ButterKnife.bind(this);
        localData = new PkhcyhqkBean();
        initView();
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
    public void onEventMainThread(PkhcyhqkBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }
    // 产业类型
    @OnClick(R.id.cyhqk_cylx_layout)
    public void onCylxClick() {
        final String[] lxValues = new String[9];
        for (int i = 0;i < lxValues.length;i++) {
            lxValues[i] = DictionaryUtil.getValueName("ZDCYLX",String.valueOf(i + 1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_cyhqk_cylx),
                lxValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cylx.setText(data);
                        for (int i = 0;i < lxValues.length;i++) {
                            if (data.equals(lxValues[i])) {
                                localData.setCylx(String.valueOf(i));
                                break;
                            }
                        }
                    }
                });
    }
    // 产业组织类型
    @OnClick(R.id.cyhqk_cyzzlx_layout)
    public void onCyzzlxClick() {
        final String[] lxValues = new String[4];
        for (int i = 0;i < lxValues.length;i++) {
            lxValues[i] = DictionaryUtil.getValueName("CYZZLX",String.valueOf(i + 1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_cyhqk_cylx),
                lxValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cyzzlx.setText(data);
                        for (int i = 0;i < lxValues.length;i++) {
                            if (data.equals(lxValues[i])) {
                                localData.setCyzzlx(String.valueOf(i));
                                break;
                            }
                        }
                    }
                });
    }
    @OnClick(R.id.cyhqk_cjfpzz_layout)
    public void onFpzzClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_cyhqk_cjfpzz),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cjfphzzjzz.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setCjfphzzjzz("0");
                        } else localData.setCjfphzzjzz("1");
                    }
                });
    }
    @OnClick(R.id.cyhqk_hzzz_layout)
    public void onHzzzClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_cyhqk_hzzz),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cynyhzzz.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setCynyhzzz("0");
                        } else localData.setCynyhzzz("1");
                    }
                });
    }
    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtqkzpBean event) {
        if (isSelected()) {
//            mPkhjtqkzpRecyclerAdapter.notifyResult(false, event);
            mHasData = true;
        }
    }
    // 点击保存按钮
    @SuppressWarnings("all")
    public void onEvent(JdDataSaveEvent event) {
//        localData.setGdmj(gdmj.getText().toString());
//        localData.setXyggdgdmj(xyggdgdmj.getText().toString());
//        localData.setTian(tian.getText().toString());
//        localData.setTu(tu.getText().toString());
//        localData.setLscgymj(lscgymj.getText().toString());
//        localData.setTghlmj(tghlmj.getText().toString());
//        localData.setMcdmj(mcdmj.getText().toString());
//        localData.setSmmj(smmj.getText().toString());
//        localData.setSyjjzwmj(syjjzwmj.getText().toString());
//        localData.setScyfmj(scyfmj.getText().toString());
//        localData.setSxsl(sxsl.getText().toString());
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHCYHZZJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhCyhzzJbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhcyhqkBean responseEvent = requestGson.fromJson(dataJsonString, PkhcyhqkBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_cyh);
    }

    private void bindData(PkhcyhqkBean pkhcyhqkBean) {

        this.localData = pkhcyhqkBean;
        cylx.setText(DictionaryUtil.getValueName("ZDCYLX",pkhcyhqkBean.getCylx()));
        cyzzlx.setText(DictionaryUtil.getValueName("CYZZLX",pkhcyhqkBean.getCyzzlx()));
        cyzsy.setText(pkhcyhqkBean.getCyzsy());
        cjfphzzjzz.setText(DictionaryUtil.getValueName(pkhcyhqkBean.getCjfphzzjzz()));
        trhzzzje.setText(pkhcyhqkBean.getTrhzzzje());
        cynyhzzz.setText(DictionaryUtil.getValueName(pkhcyhqkBean.getCynyhzzz()));
        ltqyddsy.setText(pkhcyhqkBean.getLtqyddsy());
        mHasData = true;
    }

    private void initView() {
        cylx = (TextView) findViewById(R.id.cyhqk_cylx);
        cyzzlx = (TextView) findViewById(R.id.cyhqk_cyzzlx);
        cyzsy = (EditText) findViewById(R.id.cyhqk_cyzsy);
        cjfphzzjzz = (TextView) findViewById(R.id.cyhqk_cjfpzz);
        trhzzzje = (EditText) findViewById(R.id.cyhqk_hzzj);
        cynyhzzz = (TextView) findViewById(R.id.cyhqk_hzzz);
        ltqyddsy = (EditText) findViewById(R.id.cyhqk_ltqy);
        mHasData = false;
    }
}
