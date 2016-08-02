package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.NewsRecyclerAdapter;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.JdPkhjbxxList;
import cn.deepai.evillage.model.bean.NewsBean;
import cn.deepai.evillage.model.bean.NewsList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.JdPkhListRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.PhoneInfoUtil;
import de.greenrobot.event.EventBus;

public class NewsFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private NewsRecyclerAdapter mNewsRecyclerAdapter;
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
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(NewsList event) {
        mNewsRecyclerAdapter.notifyResult(true, event.list);
        tryToHideProcessDialog();
    }

    @Override
    protected String getFragmentName() {
        return "Page_News";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsRecyclerAdapter = new NewsRecyclerAdapter();
        mRecyclerView.setAdapter(mNewsRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadData() {
        tryToShowProcessDialog();
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getJdList);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_NEWS_LIST, gson.toJson(header), "{}",
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        NewsList newsList = gson.fromJson(dataJsonString, NewsList.class);
                        EventBus.getDefault().post(newsList);
                    }
                });
    }
}
