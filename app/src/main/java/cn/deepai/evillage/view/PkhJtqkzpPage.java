package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.model.bean.HidBean;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

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
    public void onEventMainThread(PkhjtqkzpList event) {
        if (isSelected()) {
            mPkhjtqkzpRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @Override
    public void requestData() {
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHJTQKZPLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJtqkzpList)),
                requestGson.toJson(new HidBean()),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjtqkzpList responseEvent = requestGson.fromJson(dataJsonString, PkhjtqkzpList.class);
                        EventBus.getDefault().post(responseEvent);
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
