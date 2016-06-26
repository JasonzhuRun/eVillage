package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.HidBean;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.PkhsctjBean;
import cn.deepai.evillage.bean.PkhshtjBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.PkhzfqkBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.ResponseHeaderEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.request.EVRequest;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 生活条件
 */
public class PkhShtjPage extends PkhBasePage{

    private EditText    tjnd;
    private	EditText	tshyd;
    private	EditText	zyrllx;
    private	EditText	ysqk;
    private	EditText	hqyysdzykn;
    private	EditText	cslx;
    private	EditText	nyxfpqk;
    private	EditText	yskn;
    private	EditText	ysaq;
    private	EditText	jlczgl;
    private	EditText	rullx;
    private	EditText	wscs;
    private	EditText	tgbds;

    public PkhShtjPage(Context context) {
        this(context,null);
    }
    public PkhShtjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhShtjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhshtj, this);
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
    public void onEventMainThread(PkhxqBean<PkhshtjBean> event) {
        if (!isSelected()) return;
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
            case RspCode.RSP_CODE_NO_CONNECTION:
                bindData(event.data);
                break;
        }
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHSHQKJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhShqkJbxx)),
                requestGson.toJson(new HidBean()),
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        PkhxqBean<PkhshtjBean> pkhxqBean = new PkhxqBean<>();
                        String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHSHQKJBXX);
                        pkhxqBean.data = requestGson.fromJson(cache, PkhshtjBean.class);
                        pkhxqBean.rspHeader = new ResponseHeaderEvent();
                        pkhxqBean.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                        EventBus.getDefault().post(pkhxqBean);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Type type = new TypeToken<PkhxqBean<PkhshtjBean>>(){}.getType();
                        PkhxqBean<PkhshtjBean> pkhxqBean = requestGson.fromJson(response.body().string(), type);
                        EventBus.getDefault().post(pkhxqBean);
                        if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqBean.rspHeader.getRspCode())) {
                            CacheManager.getInstance().cacheData(
                                    EVRequest.ACTION_GET_PKHSHQKJBXX,requestGson.toJson(pkhxqBean.data));
                        }
                    }
                });
    }

    @Override
    public boolean hasData() {
        return false;
    }

    private void bindData(PkhshtjBean pkhshtjBean) {
        tjnd.setText(String.valueOf(pkhshtjBean.getTjnd()));
        tshyd.setText(String.valueOf(pkhshtjBean.getTshyd()));
        zyrllx.setText(String.valueOf(pkhshtjBean.getZyrllx()));
        ysqk.setText(String.valueOf(pkhshtjBean.getYsqk()));
        hqyysdzykn.setText(String.valueOf(pkhshtjBean.getHqyysdzykn()));
        cslx.setText(String.valueOf(pkhshtjBean.getCslx()));
        nyxfpqk.setText(String.valueOf(pkhshtjBean.getNyxfpqk()));
        yskn.setText(String.valueOf(pkhshtjBean.getYskn()));
        ysaq.setText(String.valueOf(pkhshtjBean.getYsaq()));
        jlczgl.setText(String.valueOf(pkhshtjBean.getJlczgl()));
        rullx.setText(String.valueOf(pkhshtjBean.getRullx()));
        wscs.setText(String.valueOf(pkhshtjBean.getWscs()));
        tgbds.setText(String.valueOf(pkhshtjBean.getTgbds()));
        mHasData = true;
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_shtj);
    }

    private void initView() {
        tjnd	 = (EditText)findViewById(R.id.shtj_tjnd);
        tshyd	 = (EditText)findViewById(R.id.shtj_shyd);
        zyrllx	 = (EditText)findViewById(R.id.shtj_zyrl);
        ysqk	 = (EditText)findViewById(R.id.shtj_ysqk);
        hqyysdzykn	 = (EditText)findViewById(R.id.shtj_hqyskn);
        cslx	 = (EditText)findViewById(R.id.shtj_cslx);
        nyxfpqk	 = (EditText)findViewById(R.id.shtj_nyxfp);
        yskn	 = (EditText)findViewById(R.id.shtj_yskn);
        ysaq	 = (EditText)findViewById(R.id.shtj_ysaq);
        jlczgl	 = (EditText)findViewById(R.id.shtj_jlzgl);
        rullx	 = (EditText)findViewById(R.id.shtj_rhllx);
        wscs	 = (EditText)findViewById(R.id.shtj_wscs);
        tgbds	 = (EditText)findViewById(R.id.shtj_gbds);
        mHasData = false;
    }
}
