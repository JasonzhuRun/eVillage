package cn.deepai.evillage.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.NewsRecyclerAdapter;
import cn.deepai.evillage.controller.activity.NewsDetailActivity;

public class NewsFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_news);
        initView();
        return root;
    }

    @Override
    protected String getFragmentName() {
        return "Page_News";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        tryToShowProcessDialog();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsRecyclerAdapter newsRecyclerAdapter = new NewsRecyclerAdapter();
        newsRecyclerAdapter.setOnItemClickLitener(new NewsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                // todo 把id搞出来
                intent.putExtra("news_id",position);
                getActivity().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(new NewsRecyclerAdapter());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
