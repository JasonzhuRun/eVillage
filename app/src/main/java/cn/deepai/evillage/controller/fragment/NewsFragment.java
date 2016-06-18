package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.NewsRecyclerAdapter;
import cn.deepai.evillage.utils.PhoneInfoUtil;

public class NewsFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_news);
        initView();
        loadData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected String getFragmentName() {
        return "Page_News";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new NewsRecyclerAdapter());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadData() {
//        if (!PhoneInfoUtil.isNetworkAvailable(getContext())) {
//
//        } else {
//
//        }
//        tryToShowProcessDialog();
    }
}
