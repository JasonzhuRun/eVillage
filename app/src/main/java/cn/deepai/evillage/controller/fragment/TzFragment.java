package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzRecyclerAdapter;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.net.TzListRequest;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

public class TzFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private TzRecyclerAdapter mTzRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tz, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_tz);
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
    public void onEventMainThread(ListBean<PkhjbxxBean> event) {
//        mTzRecyclerAdapter.notifyResult(true, event.list);
//        tryToHideProcessDialog();
    }

    private void loadData() {
        tryToShowProcessDialog();
        String hid = SettingManager.getCurrentHid();
        if (TextUtils.isEmpty(hid)) {
            ToastUtil.shortToast(getString(R.string.tz_none_hid));
            tryToHideProcessDialog();
        } else {
            TzListRequest.request(hid);
        }
    }

    @Override
    protected String getFragmentName() {
        return "Page_Tz";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTzRecyclerAdapter = new TzRecyclerAdapter();
        mRecyclerView.setAdapter(mTzRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}