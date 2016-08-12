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
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 贫困户基本信息
 */
public class JdJbxxPage extends BasePage implements BasePage.IDataEdit{

    private PkhjbxxBean serverData;
    private PkhjbxxBean localData;
    // 户主姓名
    private EditText hzxm;
    // 居住地址
    private EditText jzdz;
    // 联系电话
    private EditText lxdh;
    // 户主身份证
    private EditText hzsfz;
    // 户开户银行
    private EditText hkhyx;
    // 银行账号
    private EditText yxzh;
    // 贫困识别标准
    private TextView sbbz;
    // 计划生育户
    private TextView jhsyh;
    // 贫困户属性
    private TextView pkhsx;
    // 贫困户状态
    private TextView pkhzt;
    // 脱贫年份
    private EditText tpnf;

    public JdJbxxPage(Context context) {
        this(context, null);
    }

    public JdJbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdJbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_jdjbxx, this);
//        ButterKnife.bind(this); 暂时不进行编辑操作
        localData = new PkhjbxxBean();
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
    public void onEventMainThread(PkhjbxxBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    @OnClick(R.id.jbxx_jhsyh_layout)
    public void onJhsyhClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jbxx_jhsyh),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        jhsyh.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setJhsyh("0");
                        } else localData.setJhsyh("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_pkhsx_layout)
    public void onPkhsxClick() {
        final String[] values = new String[]{
                DictionaryUtil.getValueName("PKHSX","1"),
                DictionaryUtil.getValueName("PKHSX","2"),
                DictionaryUtil.getValueName("PKHSX","3"),
                DictionaryUtil.getValueName("PKHSX","4")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jbxx_pkhsx),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        pkhsx.setText(data);
                        for (int i = 0;i < 4;i++) {
                            if (data.equals(values[i])){
                                localData.setPkhsx(String.valueOf(i+1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jbxx_pkhzt_layout)
    public void onPkztClick() {
        final String[] values = new String[]{
                DictionaryUtil.getValueName("PKHZT","1"),
                DictionaryUtil.getValueName("PKHZT","2"),
                DictionaryUtil.getValueName("PKHZT","3"),
                DictionaryUtil.getValueName("PKHZT","4")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jbxx_pkzt),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        pkhzt.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setPkhzt(String.valueOf(i+1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jbxx_sbbz_layout)
    public void onSbbzClick() {
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jbxx_sbbz),
                new String[]{mContext.getString(R.string.pkh_jbxx_sbbz_gjbz),mContext.getString(R.string.pkh_jbxx_sbbz_sdbz)},
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sbbz.setText(data);
                        if (mContext.getString(R.string.pkh_jbxx_sbbz_gjbz).equals(data)) {
                            localData.setPksbbz("G");
                        } else localData.setPksbbz("S");
                    }
                });
    }

    @Override
    public void saveData() {

        localData.setHzxm(hzxm.getText().toString());
        localData.setJzdz(jzdz.getText().toString());
        localData.setLxdh(lxdh.getText().toString());
        localData.setHzsfz(hzsfz.getText().toString());
        localData.setHkhyx(hkhyx.getText().toString());
        localData.setYxzh(yxzh.getText().toString());
        localData.setTpnf(tpnf.getText().toString());

        final PkhjbxxBean jbxxBean = localData;
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updatePkhJbxx);

        final Gson gson = new Gson();
        ((PkhxqActivity)mContext).tryToShowProcessDialog();
        EVRequest.request(Action.ACTION_UPDATE_PKHJBXX, gson.toJson(header), gson.toJson(jbxxBean),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                        EventBus.getDefault().post(returnValueEvent);
                    }
                });
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjbxxBean pkhjbxxBean = requestGson.fromJson(dataJsonString,PkhjbxxBean.class);
                        EventBus.getDefault().post(pkhjbxxBean);
                    }
                });
    }


    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }

    private void bindData(PkhjbxxBean pkhjbxxBean) {
        // todo 如果做对比优化这里应该加深度拷贝
        this.localData = pkhjbxxBean;
        this.serverData = pkhjbxxBean;
        hzxm.setText(pkhjbxxBean.getHzxm());
        // 地址
        jzdz.setText(pkhjbxxBean.getJzdz());
        // 电话
        lxdh.setText(pkhjbxxBean.getLxdh());
        // 身份证
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        // 开户行
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        // 银行账号
        yxzh.setText(pkhjbxxBean.getYxzh());
        // 计划生育户
        jhsyh.setText(DictionaryUtil.getValueName(pkhjbxxBean.getJhsyh()));
        // 贫困户状态
        pkhzt.setText(DictionaryUtil.getValueName("PKHZT",pkhjbxxBean.getPkhzt()));
        sbbz.setText(DictionaryUtil.getValueName("PKBZ",pkhjbxxBean.getPksbbz()));
        tpnf.setText(pkhjbxxBean.getTpnf());
        pkhsx.setText(DictionaryUtil.getValueName("PKHSX",pkhjbxxBean.getPkhsx()));
        mHasData = true;
    }

    private void initView() {
        hzxm = (EditText) findViewById(R.id.jbxx_hzxx);
        jzdz = (EditText) findViewById(R.id.jbxx_jzdz);
        lxdh = (EditText) findViewById(R.id.jbxx_lxdh);
        hzsfz = (EditText) findViewById(R.id.jbxx_sfzh);
        hkhyx = (EditText) findViewById(R.id.jbxx_khyh);
        yxzh = (EditText) findViewById(R.id.jbxx_yhzh);
        jhsyh = (TextView) findViewById(R.id.jbxx_jhsyh);
        pkhzt = (TextView) findViewById(R.id.jbxx_pkhzt);
        sbbz = (TextView) findViewById(R.id.jbxx_sbbz);
        tpnf = (EditText) findViewById(R.id.jbxx_tpnf);
        pkhsx = (TextView) findViewById(R.id.jbxx_pkhsx);
        mHasData = false;
    }
}
