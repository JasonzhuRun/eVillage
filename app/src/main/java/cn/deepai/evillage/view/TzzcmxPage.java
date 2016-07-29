package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzjtcyRecyclerAdapter;
import cn.deepai.evillage.adapter.TzzcmxRecyclerAdapter;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.bean.TzzcmxList;
import cn.deepai.evillage.model.event.TzsrmxClickEvent;
import cn.deepai.evillage.model.event.TzzcmxClickEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账支出明细
 */
public class TzzcmxPage extends BasePage {

    private String tzId;
    private String tznd;
    private TzzcmxRecyclerAdapter mTzzcmxRecyclerAdapter;
    public TzzcmxPage(Context context) {
        this(context, null, null, null);
    }

    public TzzcmxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzzcmxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzzcmxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_recycerview, this);
        tzId = id;
        tznd = nd;
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
    public void onEventMainThread(TzzcmxList event) {
        if (isSelected()) {
            mTzzcmxRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(TzzcmxClickEvent event) {
        if (isSelected()) {
            switch (event.viewId) {
                case R.id.zcmx_xmmc:
                    ToastUtil.shortToast("xmmc");
                case R.id.zcmx_dkyt:
                    ToastUtil.shortToast("dkyt");
                case R.id.zcmx_zcyf:
                    ToastUtil.shortToast("zcyf");
                case R.id.zcmx_zcjey:
                    ToastUtil.shortToast("zcjey");
            }
        }
    }

    @Override
    public void requestData() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("tzid", tzId);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZZCMX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzZcmx)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzzcmxList responseEvent = requestGson.fromJson(dataJsonString, TzzcmxList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_zcmx);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mTzzcmxRecyclerAdapter = new TzzcmxRecyclerAdapter();
        recyclerView.setAdapter(mTzzcmxRecyclerAdapter);
        mHasData = false;
    }
}
