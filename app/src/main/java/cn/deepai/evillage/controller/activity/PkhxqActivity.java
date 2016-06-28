package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.ToastUtil;
import cn.deepai.evillage.view.PkhBasePage;
import cn.deepai.evillage.view.PkhCyhPage;
import cn.deepai.evillage.view.PkhJbxxPage;
import cn.deepai.evillage.view.PkhJtcyPage;
import cn.deepai.evillage.view.PkhJtqkzpPage;
import cn.deepai.evillage.view.PkhSctjPage;
import cn.deepai.evillage.view.PkhShtjPage;
import cn.deepai.evillage.view.PkhSzqkPage;
import cn.deepai.evillage.view.PkhZfqkPage;
import de.greenrobot.event.EventBus;

/**
 * 贫困户详情页
 */
public class PkhxqActivity extends BaseActivity {

    private ArrayList<PkhBasePage> viewContainter = new ArrayList<>();

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(ResponseEvent event) {
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                break;
            case RspCode.RSP_CODE_NO_CONNECTION:
                ToastUtil.shortToast(getResources().getString(R.string.request_error));
                break;
            default:
                ToastUtil.longToast(event.rspHeader.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkh);
        initView();
        EventBus.getDefault().register(this);
        for (PkhBasePage page:viewContainter) {
            page.registeEventBus();
        }
        onPageShow(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            Uri uri = data.getData();
            //to do find the path of pic by uri

        } else if (requestCode == 1 ) {
            Uri uri = data.getData();
            if(uri == null){
                //use bundle to get data
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                    Toast.makeText(getApplicationContext(), "err****", Toast.LENGTH_LONG).show();

                    //spath :生成图片取个名字和路径包含类型
                } else {
                    Toast.makeText(getApplicationContext(), "err****", Toast.LENGTH_LONG).show();
                    return;
                }
            }else{
                //to do find the path of pic by uri
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        for (PkhBasePage page:viewContainter) {
            page.unRegisteEventBus();
        }
    }

    @Override
    protected String getActivityName() {
        return "PkuxqActivity";
    }

    private void initPagerContent() {

        viewContainter.add(new PkhJbxxPage(this));
        viewContainter.add(new PkhJtcyPage(this));
        viewContainter.add(new PkhSzqkPage(this));
        viewContainter.add(new PkhZfqkPage(this));
        viewContainter.add(new PkhShtjPage(this));
        viewContainter.add(new PkhSctjPage(this));
        viewContainter.add(new PkhCyhPage(this));
        viewContainter.add(new PkhJtqkzpPage(this));
    }

    private void initView() {

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewPager pager = (ViewPager) this.findViewById(R.id.view_pager);
        if (pager == null) return;
        PagerTabStrip tabStrip = (PagerTabStrip) this.findViewById(R.id.view_pager_tabstrip);
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
                onPageShow(arg0);
            }
        });
    }

    private void onPageShow(int index) {
        for (PkhBasePage page:viewContainter) {
            page.setSelected(false);
        }
        viewContainter.get(index).setSelected(true);
        if (!viewContainter.get(index).hasData()) {
            tryToShowProcessDialog();
            viewContainter.get(index).requestData();
        }
    }
}
