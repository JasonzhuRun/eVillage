package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * 台账收入明细
 */
public class TzsrmxPage extends BasePage {

    private String tzId;
    private String tznd;

    public TzsrmxPage(Context context) {
        this(context, null, null, null);
    }

    public TzsrmxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzsrmxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzsrmxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_tzsrmx, this);
        tzId = id;
        tznd = nd;
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
        EVRequest.request(Action.ACTION_GET_TZJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzjbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzjbxxBean responseEvent = requestGson.fromJson(dataJsonString, TzjbxxBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_srmx);
    }

    private void bindData() {

        mHasData = true;
    }

    private void initView() {

        mHasData = false;
    }
}
