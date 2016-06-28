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
import cn.deepai.evillage.model.bean.HidBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.net.EVRequest;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户基本信息
 */
public class PkhJbxxPage extends PkhBasePage {

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
    private EditText sbbz;
    // 计划生育户
    private EditText jhsyh;
    // 贫困户属性
    private EditText pkhsx;
    // 贫困户状态
    private EditText pkhzt;
    // 建档年份
    private EditText jdnf;
    // 脱贫年份
    private EditText tpnf;

    public PkhJbxxPage(Context context) {
        this(context, null);
    }

    public PkhJbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjbxx, this);
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
    public void onEventMainThread(ResponseEvent<PkhjbxxBean> event) {
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
        EVRequest.request(EVRequest.ACTION_GET_PKHJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJbxx)),
                requestGson.toJson(new HidBean()),
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ResponseEvent<PkhjbxxBean> responseEvent = new ResponseEvent<>();
                        String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHJBXX);
                        responseEvent.data = requestGson.fromJson(cache, PkhjbxxBean.class);
                        responseEvent.rspHeader = new ResponseHeaderEvent();
                        responseEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                        EventBus.getDefault().post(responseEvent);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Type type = new TypeToken<ResponseEvent<PkhjbxxBean>>() {
                        }.getType();
                        ResponseEvent<PkhjbxxBean> responseEvent = requestGson.fromJson(response.body().string(), type);
                        EventBus.getDefault().post(responseEvent);
                        if (RspCode.RSP_CODE_SUCCESS.equals(responseEvent.rspHeader.getRspCode())) {
                            CacheManager.getInstance().cacheData(
                                    EVRequest.ACTION_GET_PKHJBXX, requestGson.toJson(responseEvent.data));
                        }
                    }
                });
    }


    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }

    private void bindData(PkhjbxxBean pkhjbxxBean) {
        hzxm.setText(pkhjbxxBean.getHzxm());
        jzdz.setText(pkhjbxxBean.getJzdz());
        lxdh.setText(pkhjbxxBean.getLxdh());
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        yxzh.setText(pkhjbxxBean.getYxzh());
        jhsyh.setText(String.valueOf(pkhjbxxBean.getJhsyh()));
        pkhzt.setText(pkhjbxxBean.getPkhzt());
        mHasData = true;
    }

    private void initView() {
        hzxm = (EditText) findViewById(R.id.jbxx_hzxx);
        jzdz = (EditText) findViewById(R.id.jbxx_jzdz);
        lxdh = (EditText) findViewById(R.id.jbxx_lxdh);
        hzsfz = (EditText) findViewById(R.id.jbxx_sfzh);
        hkhyx = (EditText) findViewById(R.id.jbxx_khyh);
        yxzh = (EditText) findViewById(R.id.jbxx_yhzh);
        jhsyh = (EditText) findViewById(R.id.jbxx_jhsyh);
        pkhzt = (EditText) findViewById(R.id.jbxx_pkzt);
        mHasData = false;
    }
}
