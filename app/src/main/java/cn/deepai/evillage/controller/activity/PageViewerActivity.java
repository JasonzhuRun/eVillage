package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.deepai.evillage.R;

public class PageViewerActivity extends BaseActivity {

    ViewPager pager = null;
    PagerTitleStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> titleContainer = new ArrayList<String>();
    public String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_viewer);

        pager = (ViewPager) this.findViewById(R.id.view_pager);
        tabStrip = (PagerTitleStrip) this.findViewById(R.id.view_pager_tabstrip);
        //取消tab下面的长横线
//        tabStrip.setDrawFullUnderline(false);
        //设置tab的背景色
        tabStrip.setBackgroundColor(this.getResources().getColor(R.color.text_light));
        //设置当前tab页签的下划线颜色
//        tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.text_main));
        tabStrip.setTextSpacing(200);

        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment_account, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.fragment_detail, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.fragment_mine, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.fragment_news, null);
        //viewpager开始添加view
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);
        //页签项
        titleContainer.add("1");
        titleContainer.add("2");
        titleContainer.add("3");
        titleContainer.add("4");
        titleContainer.add("5");
        titleContainer.add("6");
        titleContainer.add("7");
        titleContainer.add("8");

        pager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }
            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }
            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
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
                return titleContainer.get(position);
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }
        });
    }

    class EVPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }

    @Override
    protected String getActivityName() {
        return "PageViewerActivity";
    }
}
