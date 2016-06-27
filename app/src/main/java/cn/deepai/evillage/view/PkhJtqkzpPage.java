package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.bean.HidBean;
import cn.deepai.evillage.bean.PkhjtqkzpBean;
import cn.deepai.evillage.event.PkhxqEvent;
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
public class PkhJtqkzpPage extends PkhBasePage {

    private PkhjtqkzpRecyclerAdapter mPkhjtqkzpRecyclerAdapter;

    public PkhJtqkzpPage(Context context) {
        this(context, null);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtqkzp, this);
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
    public void onEventMainThread(PkhxqEvent<List<PkhjtqkzpBean>> event) {
        if (!isSelected()) return;
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
            case RspCode.RSP_CODE_NO_CONNECTION:
                mPkhjtqkzpRecyclerAdapter.notifyResult(true, event.data);
                break;
        }
    }

//    @SuppressWarnings("all")
//    public void onEvent(PkhxqEvent<List<PkhjtqkzpBean>> event) {
//        if (!isSelected()) return;
//        switch (event.rspHeader.getRspCode()) {
//            case RspCode.RSP_CODE_SUCCESS:
//            case RspCode.RSP_CODE_NO_CONNECTION:
//                mPkhjtqkzpRecyclerAdapter.notifyResult(true, event.data);
//                break;
//        }
//    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHJTQKZPLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJtqkzpList)),
                requestGson.toJson(new HidBean()),
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        PkhxqEvent<List<PkhjtqkzpBean>> pkhxqEvent = new PkhxqEvent<>();
                        String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHJTQKZPLIST);
                        Type type = new TypeToken<List<PkhjtqkzpBean>>() {
                        }.getType();
                        pkhxqEvent.data = requestGson.fromJson(cache, type);
                        pkhxqEvent.rspHeader = new ResponseHeaderEvent();
                        pkhxqEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                        EventBus.getDefault().post(pkhxqEvent);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Type type = new TypeToken<PkhxqEvent<List<PkhjtqkzpBean>>>() {
                        }.getType();
                        PkhxqEvent<List<PkhjtqkzpBean>> pkhxqEvent = requestGson.fromJson(response.body().string(), type);
                        EventBus.getDefault().post(pkhxqEvent);
                        if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqEvent.rspHeader.getRspCode())) {
                            CacheManager.getInstance().cacheData(
                                    EVRequest.ACTION_GET_PKHJTQKZPLIST, requestGson.toJson(pkhxqEvent.data));
                        }
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtqkzp);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtqkzpRecyclerAdapter = new PkhjtqkzpRecyclerAdapter();
        recyclerView.setAdapter(mPkhjtqkzpRecyclerAdapter);
    }
}
