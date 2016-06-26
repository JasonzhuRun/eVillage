package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtcyRecyclerAdapter;
import cn.deepai.evillage.bean.HidBean;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhxqBean;
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
public class PkhJtcyPage extends PkhBasePage{

    private PkhjtcyRecyclerAdapter mPkhjtcyRecyclerAdapter;

    public PkhJtcyPage(Context context) {
        this(context,null);
    }
    public PkhJtcyPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtcyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtcy, this);
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
    public void onEventMainThread(PkhxqBean<List<PkhjtcyBean>> event) {
        if (!isSelected()) return;
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
            case RspCode.RSP_CODE_NO_CONNECTION:
                mPkhjtcyRecyclerAdapter.notifyResult(true,event.data);
                break;
        }
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHJTCYLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJtcyList)),
                requestGson.toJson(new HidBean()),
                new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                PkhxqBean<List<PkhjtcyBean>> pkhxqBean = new PkhxqBean<>();
                String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHJTCYLIST);
                Type type = new TypeToken<List<PkhjtcyBean>>(){}.getType();
                pkhxqBean.data = requestGson.fromJson(cache, type);
                pkhxqBean.rspHeader = new ResponseHeaderEvent();
                pkhxqBean.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                EventBus.getDefault().post(pkhxqBean);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Type type = new TypeToken<PkhxqBean<List<PkhjtcyBean>>>(){}.getType();
                PkhxqBean<List<PkhjtcyBean>> pkhxqBean = requestGson.fromJson(response.body().string(), type);
                EventBus.getDefault().post(pkhxqBean);
                if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqBean.rspHeader.getRspCode())) {
                    CacheManager.getInstance().cacheData(
                            EVRequest.ACTION_GET_PKHJTCYLIST,requestGson.toJson(pkhxqBean.data));
                }
            }
        });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtcy);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtcy);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtcyRecyclerAdapter = new PkhjtcyRecyclerAdapter();
        recyclerView.setAdapter(mPkhjtcyRecyclerAdapter);
        mHasData = false;
    }
}
