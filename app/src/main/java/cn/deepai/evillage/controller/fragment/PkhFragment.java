package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;

public class PkhFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pkh, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_pkh);
        initView();
        return root;
    }

    @Override
    protected String getFragmentName() {
        return "Page_Pkh";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        tryToShowProcessDialog();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new PkhRecyclerAdapter());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
