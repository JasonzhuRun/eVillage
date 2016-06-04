package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.fragment.AccountFragment;
import cn.deepai.evillage.controller.fragment.BaseFragment;
import cn.deepai.evillage.controller.fragment.DetailFragment;
import cn.deepai.evillage.controller.fragment.MineFragment;
import cn.deepai.evillage.controller.fragment.NewsFragment;

public class MainTabActivity extends BaseActivity implements
        BaseFragment.OnFragmentInteractionListener {

    private FragmentTabHost mTabHost;
    View view;
    TextView textView;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabHost();
        mTabHost.setCurrentTab(0);
        view = findViewById(R.id.test_view);
        textView = (TextView) findViewById(R.id.test_text);
//        ((DrawerLayout)findViewById(R.id.main_drawerlayout)).openDrawer(GravityCompat.END);
//        ((DrawerLayout)findViewById(R.id.main_drawerlayout)).addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                super.onDrawerStateChanged(newState);
//
//            }
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                if (flag) {
//
//                    textView.setText("hello");
//                    flag = false;
//                } else {
//                    textView.setText("world");
//                    flag = true;
//                }
//
//            }
//        });

//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("data", "")
//                .add("token", "")
//                .add("system", "sample").build();
//
//        Request request = new Request.Builder()
//                .url("http://sample.com/api/v1")
//                .post(body).build();
//        Response response = client.newCall(request).execute();

       // EventBus eventBus = new EventBus();
       // EventBus.getDefault().register(this);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
//        navigationView.
//        navigationView.set
//        NavigationView  Menu menu = new Menu() {
//        }
//        setUpDrawer();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

//    private void setUpDrawer() {
//        ListView mLvLeftMenu = (ListView) findViewById(R.id.id_lv_left_menu);
//        LayoutInflater inflater = LayoutInflater.from(this);
////        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.header_just_username, mLvLeftMenu, false));
////        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
//    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void initTabHost() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);

        mTabHost.addTab(newTabSpec(mTabHost, "main", getString(R.string.tab_news),
                R.drawable.ic_action_location_found_dark),
                NewsFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "account", getString(R.string.tab_detail),
                R.drawable.ic_action_location_found_dark),
                DetailFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "message", getString(R.string.tab_account),
                R.drawable.ic_action_location_found_dark),
                AccountFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "mine", getString(R.string.tab_mine),
                R.drawable.ic_action_location_found_dark),
                MineFragment.class,
                null
        );
    }

    private TabHost.TabSpec newTabSpec(TabHost tabHost, String tag, CharSequence label,
                                       int iconRes) {
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

    @Override
    protected String getActivityName() {
        return "MainTabActivity";
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
