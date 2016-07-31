package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.event.JdDataSaveEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class JdJtqkzpPage extends BasePage {

    private PkhjtqkzpRecyclerAdapter mPkhjtqkzpRecyclerAdapter;

    public JdJtqkzpPage(Context context) {
        this(context, null);
    }

    public JdJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_recycerview, this);
        EventBus.getDefault().register(this);
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
    public void onEventMainThread(PkhjtqkzpList event) {
        if (isSelected()) {
            mPkhjtqkzpRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }
    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtqkzpBean event) {
        if (isSelected()) {
            mPkhjtqkzpRecyclerAdapter.notifyResult(false, event);
            mHasData = true;
        }
    }
    // 点击保存按钮
    @SuppressWarnings("all")
    public void onEvent(JdDataSaveEvent event) {
//        localData.setHzxm(hzxm.getText().toString());
//        localData.setJzdz(jzdz.getText().toString());
//        localData.setLxdh(lxdh.getText().toString());
//        localData.setHzsfz(hzsfz.getText().toString());
//        localData.setHkhyx(hkhyx.getText().toString());
//        localData.setYxzh(yxzh.getText().toString());
//        localData.setPkhzt(pkhzt.getText().toString());
//        localData.setTpnf(tpnf.getText().toString());
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHJTQKZPLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJtqkzpList)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjtqkzpList responseEvent = requestGson.fromJson(dataJsonString, PkhjtqkzpList.class);
                        EventBus.getDefault().post(responseEvent);
//                        EventBus.getDefault().post(responseEvent.list.get(0));
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtqkzpRecyclerAdapter = new PkhjtqkzpRecyclerAdapter(true);
        recyclerView.setAdapter(mPkhjtqkzpRecyclerAdapter);
    }
}
