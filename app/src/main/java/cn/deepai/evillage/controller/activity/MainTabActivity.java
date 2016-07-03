package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.controller.fragment.BaseFragment;
import cn.deepai.evillage.controller.fragment.JdFragment;
import cn.deepai.evillage.controller.fragment.MineFragment;
import cn.deepai.evillage.controller.fragment.NewsFragment;
import cn.deepai.evillage.controller.fragment.PkhFragment;
import cn.deepai.evillage.controller.fragment.TzFragment;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.PkhjbxxList;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

public class MainTabActivity extends BaseActivity implements
        BaseFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private PkhRecyclerAdapter mPkhRecyclerAdapter;
    private TextView title;
    @SuppressWarnings("all")
    public void onEventMainThread(ResponseHeaderEvent event) {
        switch (event.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                break;
            case RspCode.RSP_CODE_TOKEN_NOTEXIST:
                ToastUtil.shortToast(getString(R.string.login_overdue));
                SettingManager.getInstance().clearToken();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                ToastUtil.shortToast(event.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhjbxxList event) {
        mPkhRecyclerAdapter.notifyResult(true, event.list);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onMenuOpened(featureId, menu);
    }

    private long lastPressedTime = 0;

    @Override
    public void onBackPressed() {
        if((System.currentTimeMillis()- lastPressedTime) > 2000){
            ToastUtil.shortToast(getString(R.string.back_pressed));
            lastPressedTime = System.currentTimeMillis();
        } else {
            finish();
        }
        LogUtil.v(getActivityName(),"onBackPressed");
    }

    @Override
    protected String getActivityName() {
        return "MainTabActivity";
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        title = (TextView)findViewById(R.id.title_text);
        if (title != null) {
            title.setText(getString(R.string.tab_detail));
        }
        RecyclerView drawerRecyclerView = (RecyclerView) findViewById(R.id.drawer_recyclerview);
        if (drawerRecyclerView != null){
            drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mPkhRecyclerAdapter = new PkhRecyclerAdapter();
            drawerRecyclerView.setAdapter(mPkhRecyclerAdapter);
            drawerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        initDrawerLayout();
        initTabHost();
    }

    private void initDrawerLayout() {

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawerlayout);
        if (mDrawerLayout != null) {
            mDrawerLayout.setActivated(false);
        }
    }

    private void initTabHost() {
        final FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        if (null == tabHost) return;
        tabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);

        tabHost.addTab(newTabSpec(tabHost, "0", getString(R.string.tab_detail),
                R.drawable.ic_action_location_found_dark),
                PkhFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "1", getString(R.string.tab_build),
                R.drawable.ic_action_location_found_dark),
                JdFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "2", getString(R.string.tab_account),
                R.drawable.ic_action_location_found_dark),
                TzFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "3", getString(R.string.tab_news),
                R.drawable.ic_action_location_found_dark),
                NewsFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "4", getString(R.string.tab_mine),
                R.drawable.ic_action_location_found_dark),
                MineFragment.class,
                null
        );
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "0":
                        if (title != null) {
                            title.setText(getString(R.string.tab_detail));
                        }
                        break;
                    case "1":
                        if (title != null) {
                            title.setText(getString(R.string.tab_build_detail));
                        }
                        break;
                    case "2":
                        if (title != null) {
                            title.setText(getString(R.string.tab_account));
                        }
                        break;
                    case "3":
                        if (title != null) {
                            title.setText(getString(R.string.tab_news));
                        }
                    case "4":
                        if (title != null) {
                            title.setText(getString(R.string.tab_mine));
                        }
                        break;
                }
            }
        });
        tabHost.setCurrentTab(0);
    }

    private TabHost.TabSpec newTabSpec(TabHost tabHost, String tag, CharSequence label,int iconRes) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View indicatorView = inflater
                .inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
        ImageView iconImageView = (ImageView) indicatorView.findViewById(android.R.id.icon);
        TextView labelTextView = (TextView) indicatorView.findViewById(android.R.id.title);
        iconImageView.setImageResource(iconRes);
        labelTextView.setText(label);
        return tabHost.newTabSpec(tag).setIndicator(indicatorView);
    }

}
