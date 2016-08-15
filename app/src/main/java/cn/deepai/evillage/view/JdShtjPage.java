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
import cn.deepai.evillage.model.bean.PkhshtjBean;
import cn.deepai.evillage.model.bean.PkhzfqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.StringUtil;
import de.greenrobot.event.EventBus;

/**
 * 生活条件
 */
public class JdShtjPage extends BasePage implements BasePage.IDataEdit{

    private PkhshtjBean localData;

//    private TextView tjnd;
    private TextView tshyd;
    private TextView zyrllx;
    private TextView ysqk;
    private TextView hqyysdzykn;
    private TextView cslx;
    private TextView nyxfpqk;
    private TextView yskn;
    private TextView ysaq;
    private EditText jlczgl;
    private TextView rullx;
    private TextView wscs;
    private TextView tgbds;

    public JdShtjPage(Context context) {
        this(context, null);
    }

    public JdShtjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdShtjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_jdshtj, this);
        ButterKnife.bind(this);
        localData = new PkhshtjBean();
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
    // 生活用电
    @OnClick(R.id.shtj_shyd_layout)
    public void onShydClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_shyd),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        tshyd.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setTshyd("0");
                        } else localData.setTshyd("1");
                    }
                });
    }
    // 饮水困难
    @OnClick(R.id.shtj_yskn_layout)
    public void onYsknClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_yskn),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        yskn.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setYskn("0");
                        } else localData.setYskn("1");
                    }
                });
    }
    // 饮水安全
    @OnClick(R.id.shtj_ysaq_layout)
    public void onYsaqClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_ysqk),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        ysaq.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setYsaq("0");
                        } else localData.setYsaq("1");
                    }
                });
    }
    // 卫生厕所
    @OnClick(R.id.shtj_wscs_layout)
    public void onWscsClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_wscs),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        wscs.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setWscs("0");
                        } else localData.setWscs("1");
                    }
                });
    }
    // 通广播电视
    @OnClick(R.id.shtj_gbds_layout)
    public void onGbdsClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_gbds),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        tgbds.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setTgbds("0");
                        } else localData.setTgbds("1");
                    }
                });
    }
    // 燃料类型
    @OnClick(R.id.shtj_zyrl_layout)
    public void onRllxClick() {
        final String[] lxValues = new String[11];
        boolean[] lxSelected = new boolean[11];
        String[] rllxCodes = StringUtil.splitCode(localData.getZyrllx());
        for (String code:rllxCodes) {
            lxSelected[Integer.valueOf(code) - 1] = true;
        }
        for (int i = 0;i < lxValues.length;i++) {
            lxValues[i] =  DictionaryUtil.getValueName("RLLX",String.valueOf(i+1));
        }
        DialogManager.showMultiChoiceDialog(mContext, mContext.getString(R.string.pkh_shtj_zyrl),
                lxValues,
                lxSelected,
                new DialogManager.IOnMultiDialogFinished() {
                    @Override
                    public void returnData(String code, String text) {
                        zyrllx.setText(text);
                        localData.setZyrllx(code);
                    }
                });
    }
    // 饮水情况
    @OnClick(R.id.shtj_ysqk_layout)
    public void onYsqkClick() {
        final String[] ysValues = new String[]{
                DictionaryUtil.getValueName("WaterCon","J"),
                DictionaryUtil.getValueName("WaterCon","B"),
                DictionaryUtil.getValueName("WaterCon","N"),
                DictionaryUtil.getValueName("WaterCon","R"),
                DictionaryUtil.getValueName("WaterCon","S"),
                DictionaryUtil.getValueName("WaterCon","T"),
                DictionaryUtil.getValueName("WaterCon","Q")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_ysqk),
                ysValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        ysqk.setText(data);
                        if (data.equals(ysValues[0])) {
                            localData.setYsqk("J");
                        } else if (data.equals(ysValues[1])) {
                            localData.setYsqk("B");
                        } else if (data.equals(ysValues[2])) {
                            localData.setYsqk("N");
                        } else if (data.equals(ysValues[3])) {
                            localData.setYsqk("R");
                        } else if (data.equals(ysValues[4])) {
                            localData.setYsqk("S");
                        } else if (data.equals(ysValues[5])) {
                            localData.setYsqk("T");
                        } else {
                            localData.setYsqk("Q");
                        }
                    }
                });
    }
    // 饮水主要困难
    @OnClick(R.id.shtj_hqyskn_layout)
    public void onYszyknClick() {
        final String[] ysValues = new String[]{
                DictionaryUtil.getValueName("YYSZYKN","N"),
                DictionaryUtil.getValueName("YYSZYKN","H"),
                DictionaryUtil.getValueName("YYSZYKN","J"),
                DictionaryUtil.getValueName("YYSZYKN","F"),
                DictionaryUtil.getValueName("YYSZYKN","Q")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_hqyskn),
                ysValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        hqyysdzykn.setText(data);
                        if (data.equals(ysValues[0])) {
                            localData.setHqyysdzykn("N");
                        } else if (data.equals(ysValues[1])) {
                            localData.setHqyysdzykn("H");
                        } else if (data.equals(ysValues[2])) {
                            localData.setHqyysdzykn("J");
                        } else if (data.equals(ysValues[3])) {
                            localData.setHqyysdzykn("F");
                        } else {
                            localData.setHqyysdzykn("Q");
                        }
                    }
                });
    }
    // 厕所类型
    @OnClick(R.id.shtj_cslx_layout)
    public void onCslxClick() {
        final String[] csValues = new String[5];
        for (int i = 0;i < csValues.length;i++) {
            csValues[i] =  DictionaryUtil.getValueName("CSLX",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_cslx),
                csValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cslx.setText(data);
                        for (int i = 0;i < csValues.length;i++) {
                            if (data.equals(csValues[i])) {
                                localData.setCslx(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }
    // 耐用消费品
    @OnClick(R.id.shtj_nyxfp_layout)
    public void onNyxfpClick() {
        final String[] nypValues = new String[14];
        boolean[] nypSelected = new boolean[14];
        String[] nypCodes = StringUtil.splitCode(localData.getNyxfpqk());
        for (String code:nypCodes) {
            nypSelected[Integer.valueOf(code) - 1] = true;
        }
        for (int i = 0;i < nypValues.length;i++) {
            nypValues[i] =  DictionaryUtil.getValueName("NYXFP",String.valueOf(i+1));
        }
        DialogManager.showMultiChoiceDialog(mContext, mContext.getString(R.string.pkh_shtj_zyrl),
                nypValues,
                nypSelected,
                new DialogManager.IOnMultiDialogFinished() {
                    @Override
                    public void returnData(String code, String text) {
                        nyxfpqk.setText(text);
                        localData.setNyxfpqk(code);
                    }
                });
    }
    // 入户路类型
    @OnClick(R.id.shtj_rhllx_layout)
    public void onRhllxClick() {
        final String[] lxValues = new String[]{
                DictionaryUtil.getValueName("RHLLX","R"),
                DictionaryUtil.getValueName("RHLLX","N"),
                DictionaryUtil.getValueName("RHLLX","S"),
                DictionaryUtil.getValueName("RHLLX","L"),
                DictionaryUtil.getValueName("RHLLX","Q")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_shtj_rhllx),
                lxValues,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        rullx.setText(data);
                        if (data.equals(lxValues[0])) {
                            localData.setRullx("R");
                        } else if (data.equals(lxValues[1])) {
                            localData.setRullx("N");
                        } else if (data.equals(lxValues[2])) {
                            localData.setRullx("S");
                        } else if (data.equals(lxValues[3])) {
                            localData.setRullx("L");
                        } else {
                            localData.setRullx("Q");
                        }
                    }
                });
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhshtjBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    @Override
    public void saveData() {
        localData.setJlczgl(jlczgl.getText().toString());

        final PkhshtjBean shtjBean = localData;
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updatePkhShqkJbxx);

        final Gson gson = new Gson();
        ((PkhxqActivity)mContext).tryToShowProcessDialog();
        if (TextUtils.isEmpty(localData.getId())) {
            shtjBean.setHid(SettingManager.getCurrentJdPkh().getHid());
            shtjBean.setTjnd(SettingManager.getCurrentJdPkh().getJdnf());
            EVRequest.request(Action.ACTION_ADD_PKHSHQKJBXX, gson.toJson(header), gson.toJson(shtjBean),
                    new ResponseCallback() {
                        @Override
                        public void onDataResponse(String dataJsonString) {
                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString, ReturnValueEvent.class);
                            EventBus.getDefault().post(returnValueEvent);
                        }
                    });
        }else {
            EVRequest.request(Action.ACTION_UPDATE_PKHSHQKJBXX, gson.toJson(header), gson.toJson(shtjBean),
                    new ResponseCallback() {
                        @Override
                        public void onDataResponse(String dataJsonString) {
                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString, ReturnValueEvent.class);
                            EventBus.getDefault().post(returnValueEvent);
                        }
                    });
        }
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHSHQKJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhShqkJbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhshtjBean responseEvent = requestGson.fromJson(dataJsonString, PkhshtjBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public boolean hasData() {
        return false;
    }

    private void bindData(PkhshtjBean pkhshtjBean) {
        this.localData = pkhshtjBean;

        tshyd.setText(DictionaryUtil.getValueName(pkhshtjBean.getTshyd()));

        String[] rllxCodes = StringUtil.splitCode(pkhshtjBean.getZyrllx());
        zyrllx.setText(StringUtil.appendText(rllxCodes,"RLLX"));

        ysqk.setText(DictionaryUtil.getValueName("WaterCon",pkhshtjBean.getYsqk()));

        hqyysdzykn.setText(DictionaryUtil.getValueName("YYSZYKN",pkhshtjBean.getHqyysdzykn()));

        cslx.setText(DictionaryUtil.getValueName("CSLX",pkhshtjBean.getCslx()));

        String[] nypxfCodes = StringUtil.splitCode(pkhshtjBean.getNyxfpqk());
        nyxfpqk.setText(StringUtil.appendText(nypxfCodes,"NYXFP"));

        yskn.setText(DictionaryUtil.getValueName(pkhshtjBean.getYskn()));

        ysaq.setText(DictionaryUtil.getValueName(pkhshtjBean.getYsaq()));

        jlczgl.setText(pkhshtjBean.getJlczgl());

        rullx.setText(DictionaryUtil.getValueName("RHLLX",pkhshtjBean.getRullx()));

        wscs.setText(DictionaryUtil.getValueName(pkhshtjBean.getWscs()));

        tgbds.setText(DictionaryUtil.getValueName(pkhshtjBean.getTgbds()));

        mHasData = true;
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_shtj);
    }

    private void initView() {
//        tjnd = (TextView) findViewById(R.id.shtj_tjnd);
        tshyd = (TextView) findViewById(R.id.shtj_shyd);
        zyrllx = (TextView) findViewById(R.id.shtj_zyrl);
        ysqk = (TextView) findViewById(R.id.shtj_ysqk);
        hqyysdzykn = (TextView) findViewById(R.id.shtj_hqyskn);
        cslx = (TextView) findViewById(R.id.shtj_cslx);
        nyxfpqk = (TextView) findViewById(R.id.shtj_nyxfp);
        yskn = (TextView) findViewById(R.id.shtj_yskn);
        ysaq = (TextView) findViewById(R.id.shtj_ysaq);
        jlczgl = (EditText) findViewById(R.id.shtj_jlzgl);
        rullx = (TextView) findViewById(R.id.shtj_rhllx);
        wscs = (TextView) findViewById(R.id.shtj_wscs);
        tgbds = (TextView) findViewById(R.id.shtj_gbds);
        mHasData = false;
    }
}
