package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhsctjBean;
import cn.deepai.evillage.model.bean.PkhshtjBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.JdDataSaveEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 生活条件
 */
public class JdShtjPage extends BasePage {

    private PkhshtjBean localData;

    private EditText tjnd;
    private EditText tshyd;
    private EditText zyrllx;
    private EditText ysqk;
    private EditText hqyysdzykn;
    private EditText cslx;
    private EditText nyxfpqk;
    private EditText yskn;
    private EditText ysaq;
    private EditText jlczgl;
    private EditText rullx;
    private EditText wscs;
    private EditText tgbds;

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

    @SuppressWarnings("all")
    public void onEventMainThread(PkhshtjBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }
    // 点击保存按钮
    @SuppressWarnings("all")
    public void onEvent(JdDataSaveEvent event) {
        localData.setTjnd(tjnd.getText().toString());
        localData.setJlczgl(jlczgl.getText().toString());
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
        tjnd.setText(pkhshtjBean.getTjnd());
        tshyd.setText(DictionaryUtil.getValueName(pkhshtjBean.getTshyd()));
        String[] rllxCodes = pkhshtjBean.getZyrllx().split(",");
        String rllxValue = "";
        for (String code:rllxCodes) {
            rllxValue += DictionaryUtil.getValueName("RLLX",code);
        }
        zyrllx.setText(rllxValue);
        ysqk.setText(DictionaryUtil.getValueName("WaterCon",pkhshtjBean.getYsqk()));
        hqyysdzykn.setText(DictionaryUtil.getValueName("YYSZYKN",pkhshtjBean.getHqyysdzykn()));
        cslx.setText(DictionaryUtil.getValueName("CSLX",pkhshtjBean.getCslx()));
        String[] nypxfCodes = pkhshtjBean.getNyxfpqk().split(",");
        String nypxfValue = "";
        for (String code:nypxfCodes) {
            nypxfValue += DictionaryUtil.getValueName("NYXFP",code) + ";";
        }
        nyxfpqk.setText(nypxfValue);
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
        tjnd = (EditText) findViewById(R.id.shtj_tjnd);
        tshyd = (EditText) findViewById(R.id.shtj_shyd);
        zyrllx = (EditText) findViewById(R.id.shtj_zyrl);
        ysqk = (EditText) findViewById(R.id.shtj_ysqk);
        hqyysdzykn = (EditText) findViewById(R.id.shtj_hqyskn);
        cslx = (EditText) findViewById(R.id.shtj_cslx);
        nyxfpqk = (EditText) findViewById(R.id.shtj_nyxfp);
        yskn = (EditText) findViewById(R.id.shtj_yskn);
        ysaq = (EditText) findViewById(R.id.shtj_ysaq);
        jlczgl = (EditText) findViewById(R.id.shtj_jlzgl);
        rullx = (EditText) findViewById(R.id.shtj_rhllx);
        wscs = (EditText) findViewById(R.id.shtj_wscs);
        tgbds = (EditText) findViewById(R.id.shtj_gbds);
        mHasData = false;
    }
}
