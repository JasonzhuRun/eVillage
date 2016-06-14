package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.fragment.TzFragment;
import cn.deepai.evillage.controller.fragment.BaseFragment;
import cn.deepai.evillage.controller.fragment.PkhFragment;
import cn.deepai.evillage.controller.fragment.MineFragment;
import cn.deepai.evillage.controller.fragment.NewsFragment;

public class MainTabActivity extends BaseActivity implements
        BaseFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * Open select menu
     */
    public void openDrawer() {

        mDrawerLayout.openDrawer(GravityCompat.START);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Activity is running
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
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


    @Override
    protected String getActivityName() {
        return "MainTabActivity";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initDrawerLayout();
        initTabHost();
    }

    private void initDrawerLayout() {

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawerlayout);
        mDrawerToggle = new ActionBarDrawerToggle(MainTabActivity.this,mDrawerLayout,R.string.app_name,R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setActivated(false);
    }

    private void initTabHost() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        if (null == tabHost) return;
        tabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);

        tabHost.addTab(newTabSpec(tabHost, "0", getString(R.string.tab_detail),
                R.drawable.ic_action_location_found_dark),
                PkhFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "1", getString(R.string.tab_news),
                R.drawable.ic_action_location_found_dark),
                NewsFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "2", getString(R.string.tab_account),
                R.drawable.ic_action_location_found_dark),
                TzFragment.class,
                null
        );
        tabHost.addTab(newTabSpec(tabHost, "3", getString(R.string.tab_mine),
                R.drawable.ic_action_location_found_dark),
                MineFragment.class,
                null
        );
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "0":
                        break;
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
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
