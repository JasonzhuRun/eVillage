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
import cn.deepai.evillage.adapter.TzzcmxRecyclerAdapter;
import cn.deepai.evillage.adapter.TzzfqkRecyclerAdapter;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzzfqkList;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账走访信息
 */
public class TzzfxxPage extends BasePage {

    private String tzId;
    private String tznd;
    private TzzfqkRecyclerAdapter mTzzfqkRecyclerAdapter;

    public TzzfxxPage(Context context) {
        this(context, null, null, null);
    }

    public TzzfxxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzzfxxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzzfxxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
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
    public void onEventMainThread(TzzfqkList event) {
        if (isSelected()) {
            mTzzfqkRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @Override
    public void requestData() {
        JSONObject jsonObject = new JSONObject();

        try {
            String hid = SettingManager.getCurrentPkh().getHid();
            jsonObject.put("hid",hid);

            jsonObject.put("tznd", tznd);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZJHLSLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzBfjh)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzzfqkList responseEvent = requestGson.fromJson(dataJsonString, TzzfqkList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_zfxx);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mTzzfqkRecyclerAdapter = new TzzfqkRecyclerAdapter();
        recyclerView.setAdapter(mTzzfqkRecyclerAdapter);
        mHasData = false;
    }
}
