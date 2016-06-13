package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;

public class DetailFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_detail);
        initView();
        return root;
    }

    @Override
    protected String getFragmentName() {
        return "Page_Detail";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        tryToShowProcessDialog();
    }
}
