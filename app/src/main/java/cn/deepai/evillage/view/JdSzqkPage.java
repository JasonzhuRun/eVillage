package cn.deepai.evillage.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhszqkRecyclerAdapter;
import cn.deepai.evillage.controller.activity.PkhjtcyActivity;
import cn.deepai.evillage.controller.activity.PkhszqkActivity;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.PagexjItemEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * 收支情况
 */
public class JdSzqkPage extends BasePage {

    private PkhszqkRecyclerAdapter mPkhszqkRecyclerAdapter;

    public JdSzqkPage(Context context) {
        this(context, null);
    }

    public JdSzqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdSzqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_recycerview, this);
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
    public void onEventMainThread(ListBean<PkhszqkBean> event) {
        if (isSelected()) {
            mPkhszqkRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PagexjItemEvent event) {
        if (isSelected()) {
            Intent intent = new Intent(mContext, PkhszqkActivity.class);
            intent.putExtra("editable",true);
            mContext.startActivity(intent);
            ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            mHasData = true;
        }
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHSZQKLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhSzqkList)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        Type type = new TypeToken<ListBean<PkhszqkBean>>() {
                        }.getType();
                        ListBean<PkhszqkBean> responseEvent = requestGson.fromJson(dataJsonString, type);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_szqk);
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhszqkRecyclerAdapter = new PkhszqkRecyclerAdapter(true);
        recyclerView.setAdapter(mPkhszqkRecyclerAdapter);
        mHasData = false;
    }
}
