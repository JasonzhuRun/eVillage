package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.ToastUtil;
import cn.deepai.evillage.view.PkhBasePage;
import cn.deepai.evillage.view.TzjbxxPage;
import cn.deepai.evillage.view.TzjtcyPage;
import cn.deepai.evillage.view.TzsrmxPage;
import cn.deepai.evillage.view.TzzcmxPage;
import cn.deepai.evillage.view.TzzfxxPage;
import de.greenrobot.event.EventBus;

/**
 * 台账详情页
 */
public class TzxqActivity extends BaseActivity {

    private static int selectedIndex = 0;
    private ArrayList<PkhBasePage> viewContainter = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        for (PkhBasePage page:viewContainter) {
            page.registeEventBus();
        }
        onPageShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        for (PkhBasePage page:viewContainter) {
            page.unRegisteEventBus();
        }
    }

    @Override
    protected String getActivityName() {
        return "TzxqActivity";
    }

    private void initPagerContent() {

        viewContainter.add(new TzjbxxPage(this));
        viewContainter.add(new TzjtcyPage(this));
        viewContainter.add(new TzsrmxPage(this));
        viewContainter.add(new TzzcmxPage(this));
        viewContainter.add(new TzzfxxPage(this));
    }

    private void initView() {

        ViewPager pager = (ViewPager) this.findViewById(R.id.view_pager);
        if (pager == null) return;
        PagerTabStrip tabStrip = (PagerTabStrip) this.findViewById(R.id.view_pager_tabstrip);
        if (tabStrip != null) {
            //取消tab下面的长横线
            tabStrip.setDrawFullUnderline(false);
            //设置tab的背景色
            tabStrip.setBackgroundColor(this.getResources().getColor(R.color.text_white));
            //设置当前tab页签的下划线颜色
            tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.title_backgroud));
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
                return viewContainter.get(position).getPageName();
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
                selectedIndex = arg0;
                onPageShow();
            }
        });
    }

    private void onPageShow() {

        for (PkhBasePage page:viewContainter) {
            page.setSelected(false);
        }
        viewContainter.get(selectedIndex).setSelected(true);
        if (!viewContainter.get(selectedIndex).hasData()) {
            tryToShowProcessDialog();
            viewContainter.get(selectedIndex).requestData();
        }
    }
}
