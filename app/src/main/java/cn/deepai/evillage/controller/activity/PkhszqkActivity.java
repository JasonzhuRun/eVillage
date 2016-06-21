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
public class PkhszqkActivity extends BaseActivity {

    private ViewPager pager = null;
    private PagerTabStrip tabStrip = null;
    private ArrayList<View> viewContainter = new ArrayList<View>();

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkh);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected String getActivityName() {
        return "PkuDetailActivity";
    }

    private void initPagerContent() {
        viewContainter.add(new PkhJtcyPage(this));
        viewContainter.add(new PkhJbxxPage(this));

        viewContainter.add(new PkhSzqkPage(this));
        viewContainter.add(new PkhZfqkPage(this));
        viewContainter.add(new PkhShtjPage(this));
        viewContainter.add(new PkhSctjPage(this));
        viewContainter.add(new PkhCyhPage(this));
    }

    private void initView() {

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pager = (ViewPager) this.findViewById(R.id.view_pager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.view_pager_tabstrip);
        if (tabStrip != null) {
            //取消tab下面的长横线
            tabStrip.setDrawFullUnderline(false);
            //设置tab的背景色
            tabStrip.setBackgroundColor(this.getResources().getColor(R.color.text_light));
            //设置当前tab页签的下划线颜色
            tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.text_main));
            tabStrip.setTextSpacing(200);
        }
        initPagerContent();
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainter.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewContainter.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return ((PkhBasePage)viewContainter.get(position)).getPageName();
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int arg0) {

            }
        });

    }

}
