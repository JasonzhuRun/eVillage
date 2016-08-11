package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.ToastUtil;
import cn.deepai.evillage.view.BasePage;
import cn.deepai.evillage.view.TzjbxxPage;
import cn.deepai.evillage.view.TzjtcyPage;
import cn.deepai.evillage.view.TzsrmxPage;
import cn.deepai.evillage.view.TzzcmxPage;
import cn.deepai.evillage.view.TzzfxxPage;
import de.greenrobot.event.EventBus;

import static cn.deepai.evillage.model.event.ReturnValueEvent.SUCCESS;

/**
 * 台账详情页
 */
public class TzxqActivity extends BaseActivity {

    private int selectedIndex = 0;
    private ArrayList<BasePage> viewContainter = new ArrayList<>();
    private String tzId;
    private String tznd;


    @OnClick(R.id.detail_back)
    public void onBackBtnClick(){
        this.onBackPressed();
    }

    @OnClick(R.id.detail_save)
    public void onSaveBtnClick(){
        BasePage page = viewContainter.get(selectedIndex);
        if (page instanceof BasePage.IDataEdit) {
            tryToShowProcessDialog();
            ((BasePage.IDataEdit)page).saveData();
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(ReturnValueEvent event) {
        if (event.returnValue == SUCCESS) {
            ToastUtil.shortToast(getString(R.string.upload_success));
        } else {
            ToastUtil.shortToast(getString(R.string.upload_failed));
        }
        tryToHideProcessDialog();
    }

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
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (null != intent) {
            tzId = intent.getStringExtra("tzId");
            tznd = intent.getStringExtra("tznd");
        }
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        for (BasePage page:viewContainter) {
            page.registeEventBus();
        }
        onPageShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        for (BasePage page:viewContainter) {
            page.unRegisteEventBus();
        }
    }

    @Override
    protected String getActivityName() {
        return "TzxqActivity";
    }

    private void initPagerContent() {

        viewContainter.add(new TzjbxxPage(this,tzId,tznd));
        viewContainter.add(new TzjtcyPage(this,tzId,tznd));
        viewContainter.add(new TzsrmxPage(this,tzId,tznd));
        viewContainter.add(new TzzcmxPage(this,tzId,tznd));
        viewContainter.add(new TzzfxxPage(this,tzId,tznd));
    }

    private void initTitle() {

        PkhjbxxBean pkh = SettingManager.getCurrentPkh();
        ImageView pkhPhoto = (ImageView)findViewById(R.id.detail_photo);
        TextView pkhName = (TextView) findViewById(R.id.detail_text_name);
        TextView pkhPhone = (TextView) findViewById(R.id.detail_text_phone);
        TextView pkhAddress = (TextView) findViewById(R.id.detail_text_address);
        ImageLoader.getInstance().displayImage(pkh.getTpdz(),pkhPhoto, EVApplication.getDisplayImageOptions());
        pkhName.setText(pkh.getHzxm());
        pkhAddress.setText(pkh.getJzdz());
        pkhPhone.setText(pkh.getLxdh());
        findViewById(R.id.detail_save).setVisibility(View.VISIBLE);
    }

    private void initView() {

        initTitle();
        ViewPager pager = (ViewPager) this.findViewById(R.id.view_pager);
        if (pager == null) return;
//        PagerTabStrip tabStrip = (PagerTabStrip) this.findViewById(R.id.view_pager_tabstrip);
//        if (tabStrip != null) {
//            //取消tab下面的长横线
//            tabStrip.setDrawFullUnderline(false);
//            //设置tab的背景色
//            tabStrip.setBackgroundColor(this.getResources().getColor(R.color.text_white));
//            //设置当前tab页签的下划线颜色
//            tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.title_backgroud));
//            tabStrip.setTextSpacing(200);
//        }
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

        TabLayout tabStrip = (TabLayout) this.findViewById(R.id.view_pager_tabstrip);
        if (tabStrip != null) {
            tabStrip.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (BasePage view :viewContainter) {
                tabStrip.addTab(tabStrip.newTab().setText(view.getPageName()));
            }
            tabStrip.setupWithViewPager(pager);
        }
    }

    private void onPageShow() {

        for (BasePage page:viewContainter) {
            page.setSelected(false);
        }
        viewContainter.get(selectedIndex).setSelected(true);
        if (!viewContainter.get(selectedIndex).hasData()) {
            tryToShowProcessDialog();
            viewContainter.get(selectedIndex).requestData();
        }
    }
}
