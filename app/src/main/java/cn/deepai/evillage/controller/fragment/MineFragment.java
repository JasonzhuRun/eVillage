package cn.deepai.evillage.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.LoginActivity;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.utils.ToastUtil;

public class MineFragment extends BaseFragment {
    @Bind(R.id.mine_detail)
    View titleDetailView;

    @OnClick(R.id.mine_clean)
    public void onCleanUpClick() {
        tryToShowProcessDialog();

        tryToHideProcessDialog();
    }

    @OnClick(R.id.mine_about)
    public void onAboutClick() {

    }

    @OnClick(R.id.mine_logout)
    public void onLogoutClick() {
        titleDetailView.setVisibility(View.GONE);
        SettingManager.setCurrentPkh(null);
        SettingManager.setCurrentJdPkh(null);
        SettingManager.getInstance().clearUserInfo();
        ToastUtil.shortToast(getResources().getString(R.string.mine_logout_success));
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,root);
        initView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected String getFragmentName() {
        return "Page_Mine";
    }

    private void initView() {
        titleDetailView.setVisibility(View.VISIBLE);
    }
}
