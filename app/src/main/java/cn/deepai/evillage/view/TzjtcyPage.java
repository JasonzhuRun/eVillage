package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhcyhqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * 台账家庭成员
 */
public class TzjtcyPage extends PkhBasePage {

    public TzjtcyPage(Context context) {
        this(context, null);
    }

    public TzjtcyPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TzjtcyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_tzjbxx, this);
        initView();
    }

    @Override
    public void registeEventBus() {
//        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisteEventBus() {
//        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("all")
    public void onEventMainThread() {
        bindData();
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
        return getResources().getString(R.string.tz_jtcy);
    }

    private void bindData() {


        mHasData = true;
    }

    private void initView() {


        mHasData = false;
    }
}
