package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhszqkRecyclerAdapter;
import cn.deepai.evillage.model.bean.HidBean;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * 收支情况
 */
public class PkhSzqkPage extends PkhBasePage {

    private PkhszqkRecyclerAdapter mPkhszqkRecyclerAdapter;

    public PkhSzqkPage(Context context) {
        this(context, null);
    }

    public PkhSzqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhSzqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhszqk, this);
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
    public void onEventMainThread(List<PkhszqkBean> event) {
        mPkhszqkRecyclerAdapter.notifyResult(true, event);
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHSZQKLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhSzqkList)),
                requestGson.toJson(new HidBean()),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        Type type = new TypeToken<List<PkhszqkBean>>() {
                        }.getType();
                        List<PkhszqkBean> responseEvent = requestGson.fromJson(dataJsonString, type);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_szqk);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_szqk);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhszqkRecyclerAdapter = new PkhszqkRecyclerAdapter();
        recyclerView.setAdapter(mPkhszqkRecyclerAdapter);
        mHasData = false;
    }
}
