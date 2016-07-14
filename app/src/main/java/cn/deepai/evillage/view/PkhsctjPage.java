package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhsctjBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class PkhsctjPage extends BasePage {

    private EditText tjnd;
    private EditText gdmj;
    private EditText xyggdgdmj;
    private EditText tian;
    private EditText tu;
    private EditText lscgymj;
    private EditText tghlmj;
    private EditText mcdmj;
    private EditText smmj;
    private EditText syjjzwmj;
    private EditText scyfmj;
    private EditText sxsl;

    public PkhsctjPage(Context context) {
        this(context, null);
    }

    public PkhsctjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhsctjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhsctj, this);
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
    public void onEventMainThread(PkhsctjBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHSCTJJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhSctjJbxx)),
                requestGson.toJson(new PkhRequestBean()),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhsctjBean responseEvent = requestGson.fromJson(dataJsonString, PkhsctjBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    private void bindData(PkhsctjBean pkhsctjBean) {
        tjnd.setText(pkhsctjBean.getTjnd());
        gdmj.setText(pkhsctjBean.getGdmj());
        xyggdgdmj.setText(pkhsctjBean.getXyggdgdmj());
        tian.setText(pkhsctjBean.getTian());
        tu.setText(pkhsctjBean.getTu());
        lscgymj.setText(pkhsctjBean.getLscgymj());
        tghlmj.setText(pkhsctjBean.getTghlmj());
        mcdmj.setText(pkhsctjBean.getMcdmj());
        smmj.setText(pkhsctjBean.getSmmj());
        syjjzwmj.setText(pkhsctjBean.getSyjjzwmj());
        scyfmj.setText(pkhsctjBean.getScyfmj());
        sxsl.setText(pkhsctjBean.getSxsl());
        mHasData = true;
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_sctj);
    }

    private void initView() {
        tjnd = (EditText) findViewById(R.id.sctj_tjnd);
        gdmj = (EditText) findViewById(R.id.sctj_gdmj);
        xyggdgdmj = (EditText) findViewById(R.id.sctj_yxgggdmj);
        tian = (EditText) findViewById(R.id.sctj_tian);
        tu = (EditText) findViewById(R.id.sctj_tu);
        lscgymj = (EditText) findViewById(R.id.sctj_lscgy);
        tghlmj = (EditText) findViewById(R.id.sctj_tghl);
        mcdmj = (EditText) findViewById(R.id.sctj_mcd);
        smmj = (EditText) findViewById(R.id.sctj_smmj);
        syjjzwmj = (EditText) findViewById(R.id.sctj_syjjzw);
        scyfmj = (EditText) findViewById(R.id.sctj_scyf);
        sxsl = (EditText) findViewById(R.id.sctj_scsl);
        mHasData = false;
    }
}
