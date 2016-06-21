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

import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.model.PkhjbxxBean;
import cn.deepai.evillage.model.RequestFailedEvent;
import cn.deepai.evillage.model.RequestSucceedEvent;
import cn.deepai.evillage.request.PkhJbxxListRequest;
import cn.deepai.evillage.utils.LogUtil;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @SuppressWarnings("all")
    public void onEventMainThread(RequestSucceedEvent event) {
        LogUtil.v(PkhFragment.class,event.body);
        Gson gson = new Gson();
        List<PkhjbxxBean> mPkhjbxxBeen = gson.fromJson(event.body, new TypeToken<List<PkhjbxxBean>>(){}.getType());

        mPkhRecyclerAdapter.notifyResult(true, mPkhjbxxBeen);
        tryToHideProcessDialog();

    }
    @SuppressWarnings("all")
    public void onEventMainThread(RequestFailedEvent event) {
        tryToHideProcessDialog();
    }

    @Override
    protected String getFragmentName() {
        return "Page_Pkh";
    }

    private void loadData() {
        tryToShowProcessDialog();
        PkhJbxxListRequest.request(0);
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPkhRecyclerAdapter = new PkhRecyclerAdapter();
        mRecyclerView.setAdapter(mPkhRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
