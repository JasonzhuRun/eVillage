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
import cn.deepai.evillage.event.PkhxqEvent;
import cn.deepai.evillage.bean.PkhzfqkBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.ResponseHeaderEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.request.EVRequest;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 */
public class PkhZfqkPage extends PkhBasePage{

    // 住房面积
    private	EditText	zfmj;
    // 主要结构
    private	EditText	fwzyjg;
    // 建房时间
    private	EditText	jfsj;
    // 是否危房
    private	EditText	zyzfsfwf;
    // 异地搬迁扶贫情况
    private	EditText	ydfpbqqk;


    public PkhZfqkPage(Context context) {
        this(context, null);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhzfqk, this);
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
    public void onEventMainThread(PkhxqEvent<PkhzfqkBean> event) {
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
        EVRequest.request(EVRequest.ACTION_GET_PKHZFQJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhZfqkJbxx)),
                requestGson.toJson(new HidBean()),
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        PkhxqEvent<PkhzfqkBean> pkhxqEvent = new PkhxqEvent<>();
                        String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHZFQJBXX);
                        pkhxqEvent.data = requestGson.fromJson(cache, PkhzfqkBean.class);
                        pkhxqEvent.rspHeader = new ResponseHeaderEvent();
                        pkhxqEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                        EventBus.getDefault().post(pkhxqEvent);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Type type = new TypeToken<PkhxqEvent<PkhzfqkBean>>(){}.getType();
                        PkhxqEvent<PkhzfqkBean> pkhxqEvent = requestGson.fromJson(response.body().string(), type);
                        EventBus.getDefault().post(pkhxqEvent);
                        if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqEvent.rspHeader.getRspCode())) {
                            CacheManager.getInstance().cacheData(
                                    EVRequest.ACTION_GET_PKHZFQJBXX,requestGson.toJson(pkhxqEvent.data));
                        }
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_zfqk);
    }

    private void bindData(PkhzfqkBean pkhzfqkBean) {
        zfmj.setText(String.valueOf(pkhzfqkBean.getZfmj()));
        fwzyjg.setText(String.valueOf(pkhzfqkBean.getFwzyjg()));
        jfsj.setText(String.valueOf(pkhzfqkBean.getJfsj()));
        zyzfsfwf.setText(String.valueOf(pkhzfqkBean.getZyzfsfwf()));
        ydfpbqqk.setText(String.valueOf(pkhzfqkBean.getYdfpbqqk()));
        mHasData = true;
    }

    private void initView() {
        zfmj	 = (EditText)findViewById(R.id.zfqk_zfmj);
        fwzyjg	 = (EditText)findViewById(R.id.zfqk_fwjg);
        jfsj	 = (EditText)findViewById(R.id.zfqk_jfsj);
        zyzfsfwf	 = (EditText)findViewById(R.id.zfqk_sfwf);
        ydfpbqqk	 = (EditText)findViewById(R.id.zfqk_ydfpbqqk);
        mHasData = false;
    }
}
