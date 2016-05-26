package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.fragment.AccountFragment;
import cn.deepai.evillage.controller.fragment.DetailFragment;
import cn.deepai.evillage.controller.fragment.MineFragment;
import cn.deepai.evillage.controller.fragment.NewsFragment;

public class MainTabActivity extends BaseActivity {

    private FragmentTabHost mTabHost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabHost();
        mTabHost.setCurrentTab(0);

    }

    private void initTabHost() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tab_content);

        mTabHost.addTab(newTabSpec(mTabHost, "main", getString(R.string.tab_news),
                R.drawable.ic_launcher),
                NewsFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "account", getString(R.string.tab_detail),
                R.drawable.ic_launcher),
                DetailFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "message", getString(R.string.tab_account),
                R.drawable.ic_launcher),
                AccountFragment.class,
                null
        );
        mTabHost.addTab(newTabSpec(mTabHost, "mine", getString(R.string.tab_mine),
                R.drawable.ic_launcher),
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
}
