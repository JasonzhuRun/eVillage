package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.deepai.evillage.R;
import cn.deepai.evillage.view.PkhBasePage;
import cn.deepai.evillage.view.PkhCyhPage;
import cn.deepai.evillage.view.PkhJbxxPage;
import cn.deepai.evillage.view.PkhJtcyPage;
import cn.deepai.evillage.view.PkhSctjPage;
import cn.deepai.evillage.view.PkhShtjPage;
import cn.deepai.evillage.view.PkhSzqkPage;
import cn.deepai.evillage.view.PkhZfqkPage;

/**
 * @author GaoYixuan
 */
public class PkhjtcyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkh);
    }

    @Override
    protected String getActivityName() {
        return "PkhjtcyActivity";
    }
}
