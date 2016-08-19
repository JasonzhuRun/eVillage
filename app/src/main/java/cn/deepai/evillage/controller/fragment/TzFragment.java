package cn.deepai.evillage.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzRecyclerAdapter;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
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
    private ImageView mPkhPhoto;
    private TextView mPkhName;
    private TextView mPkhPhone;
    private TextView mPkhAddress;
    private View mTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tz, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_tz);
        mTitle = root.findViewById(R.id.tz_title);
        mPkhPhoto = (ImageView)root.findViewById(R.id.detail_photo);
        mPkhName = (TextView) root.findViewById(R.id.detail_text_name);
        mPkhPhone = (TextView) root.findViewById(R.id.detail_text_phone);
        mPkhAddress = (TextView) root.findViewById(R.id.detail_text_address);
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

    /**
     * 获取台账列表
     */
    @SuppressWarnings("all")
    public void onEventMainThread(TzjbxxList event) {
        mTzRecyclerAdapter.notifyResult(true, event.list);
        tryToHideProcessDialog();
    }
    /**
     * 选择贫困户后加载贫困户信息
     */
    @SuppressWarnings("all")
    public void onEventMainThread(PkhSelectedEvent event) {
        loadData();
    }
    /**
     * 新建台账
     */
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
    /**
     * 新建台账返回消息
     */
    @SuppressWarnings("all")
    public void onEventMainThread(TzxgjgEvent event) {
        if (event.returnValue == 1) {
            loadData();
        } else {

        }
        tryToHideProcessDialog();
    }

    private void initTitle() {

        mTitle.setVisibility(View.VISIBLE);
        PkhjbxxBean pkh = SettingManager.getCurrentPkh();
        ImageLoader.getInstance().displayImage(pkh.getTpdz(), mPkhPhoto, EVApplication.getDisplayImageOptions());
        mPkhName.setText(pkh.getHzxm());
        mPkhAddress.setText(pkh.getJzdz());
        mPkhPhone.setText(pkh.getLxdh());
    }

    private void loadData() {
        tryToShowProcessDialog();
        String staffId = SettingManager.getInstance().getStaffId();
        String hid = SettingManager.getCurrentPkh().getHid();
        if (TextUtils.isEmpty(hid)) {
            ToastUtil.shortToast(getString(R.string.tz_none_hid));
            mTzRecyclerAdapter.beMute(true);
            mTitle.setVisibility(View.GONE);
            tryToHideProcessDialog();
        } else {
            mTzRecyclerAdapter.beMute(false);
            initTitle();
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