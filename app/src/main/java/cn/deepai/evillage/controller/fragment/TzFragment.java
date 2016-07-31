package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzRecyclerAdapter;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.bean.TzjbxxList;
import cn.deepai.evillage.model.event.PkhSelectedEvent;
import cn.deepai.evillage.model.event.TzxjtzEvent;
import cn.deepai.evillage.model.event.TzxgjgEvent;
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
    public void onEventMainThread(TzjbxxList event) {
        mTzRecyclerAdapter.notifyResult(true, event.list);
        tryToHideProcessDialog();
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhSelectedEvent event) {
        loadData();
    }
    @SuppressWarnings("all")
    public void onEventMainThread(TzxjtzEvent event) {
        final String staffId = SettingManager.getInstance().getStaffId();
        final String hid = SettingManager.getCurrentPkh().getHid();
        if (TextUtils.isEmpty(hid)) {
            ToastUtil.shortToast(getString(R.string.tz_none_hid));
        } else {
            DialogManager.showEditTextDialog(getContext(),getContext().getString(R.string.tz_new_ndtz_tip), new DialogManager.IOnDialogFinished() {
                @Override
                public void returnData(String data) {
                    if (!TextUtils.isEmpty(data)) {
                        tryToShowProcessDialog();
                        TzListRequest.request(staffId,hid,data);
                    }
                }
            });
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(TzxgjgEvent event) {
        if (event.returnValue == 1) {
            List<TzjbxxBean> jbxxList = new ArrayList<TzjbxxBean>();
            jbxxList.add(event.tzjbxxBean);
            mTzRecyclerAdapter.notifyResult(false,jbxxList);
        } else {

        }
        tryToHideProcessDialog();
    }

    private void loadData() {
        tryToShowProcessDialog();
        String staffId = SettingManager.getInstance().getStaffId();
        String hid = SettingManager.getCurrentPkh().getHid();
        if (TextUtils.isEmpty(hid)) {
            ToastUtil.shortToast(getString(R.string.tz_none_hid));
            mTzRecyclerAdapter.beMute(true);
            tryToHideProcessDialog();
        } else {
            mTzRecyclerAdapter.beMute(false);
            TzListRequest.request(staffId,hid);
        }
    }

    @Override
    protected String getFragmentName() {
        return "Page_Tz";
    }

    private void initView() {
        if (mRecyclerView == null) return;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTzRecyclerAdapter = new TzRecyclerAdapter();
        mRecyclerView.setAdapter(mTzRecyclerAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}