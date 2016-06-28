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
import cn.deepai.evillage.model.event.PkhListEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.request.PkhJbxxListRequest;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

public class JdFragment extends BaseFragment {

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
    public void onEventMainThread(PkhListEvent event) {
        LogUtil.v(JdFragment.class, event.rspHeader.toString());
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                mPkhRecyclerAdapter.notifyResult(true, event.data);
                break;
            case RspCode.RSP_CODE_NO_CONNECTION:
                mPkhRecyclerAdapter.notifyResult(true, event.data);
                ToastUtil.shortToast(getString(R.string.request_error));
                break;
            default:
                ToastUtil.longToast(event.rspHeader.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @Override
    protected String getFragmentName() {
        return "Page_Jd";
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
