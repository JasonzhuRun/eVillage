package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.event.RequestFailedEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.request.EVRequest;
import cn.deepai.evillage.request.PkhJbxxListRequest;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

public class PkhFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PkhRecyclerAdapter mPkhRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pkh, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_pkh);
        initView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(RequestFailedEvent event) {

        String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHLIST);
        Gson gson = new Gson();
        Type type = new TypeToken<List<PkhjbxxBean>>(){}.getType();
        List<PkhjbxxBean> pkhjbxxBeens = gson.fromJson(cache, type);
        mPkhRecyclerAdapter.notifyResult(true, pkhjbxxBeens);
        ToastUtil.shortToast(getString(R.string.request_error));
        tryToHideProcessDialog();
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhListEvent event) {
        LogUtil.v(PkhFragment.class,event.rspHeader.toString());
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                mPkhRecyclerAdapter.notifyResult(true, event.data);
                Gson gson = new Gson();
                CacheManager cacheManager = CacheManager.getInstance();
                cacheManager.removeCacheData(EVRequest.ACTION_GET_PKHLIST);
                cacheManager.cacheData(EVRequest.ACTION_GET_PKHLIST,gson.toJson(event.data));
                break;
            default:
                ToastUtil.longToast(event.rspHeader.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @Override
    protected String getFragmentName() {
        return "Page_Pkh";
    }

    private void loadData() {
        tryToShowProcessDialog();
        int id = SettingManager.getInstance().getUserId();
        PkhJbxxListRequest.request(id);
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPkhRecyclerAdapter = new PkhRecyclerAdapter();
        mRecyclerView.setAdapter(mPkhRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
