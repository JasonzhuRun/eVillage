package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.event.JdDataSaveEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.ToastUtil;
import cn.deepai.evillage.view.JdCyhPage;
import cn.deepai.evillage.view.JdJbxxPage;
import cn.deepai.evillage.view.JdJtcyPage;
import cn.deepai.evillage.view.JdJtqkzpPage;
import cn.deepai.evillage.view.JdSctjPage;
import cn.deepai.evillage.view.JdShtjPage;
import cn.deepai.evillage.view.JdSzqkPage;
import cn.deepai.evillage.view.JdZfqkPage;
import cn.deepai.evillage.view.BasePage;
import cn.deepai.evillage.view.PkhcyhPage;
import cn.deepai.evillage.view.PkhjbxxPage;
import cn.deepai.evillage.view.PkhjtcyPage;
import cn.deepai.evillage.view.PkhjtqkzpPage;
import cn.deepai.evillage.view.PkhsctjPage;
import cn.deepai.evillage.view.PkhshtjPage;
import cn.deepai.evillage.view.PkhszqkPage;
import cn.deepai.evillage.view.PkhzfqkPage;
import de.greenrobot.event.EventBus;

/**
 * 贫困户详情页
 */
public class PkhxqActivity extends BaseActivity {

    private boolean mEditable = false;
    private static int selectedIndex = 0;
    private ArrayList<BasePage> viewContainter = new ArrayList<>();
    private ViewPager mPager;
    @OnClick(R.id.detail_back)
    public void onBackBtnClick(){
        this.onBackPressed();
    }

    @OnClick(R.id.detail_save)
    public void onSaveBtnClick(){
        EventBus.getDefault().post(new JdDataSaveEvent());
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
            case RspCode.RSP_CODE_NO_CONNECTION:
                break;
            default:
                ToastUtil.shortToast(event.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }


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
        setContentView(R.layout.activity_xq);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (null != intent) {
            mEditable = intent.getBooleanExtra("editable",false);
        }
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
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
    }

    @Override
    protected String getActivityName() {
        return "PkhxqActivity";
    }

    private void initPagerContent() {
        if (mEditable) {
            viewContainter.add(new JdJbxxPage(this));
            viewContainter.add(new JdJtcyPage(this));
            viewContainter.add(new JdSzqkPage(this));
            viewContainter.add(new JdZfqkPage(this));
            viewContainter.add(new JdShtjPage(this));
            viewContainter.add(new JdSctjPage(this));
            viewContainter.add(new JdCyhPage(this));
            viewContainter.add(new JdJtqkzpPage(this));
        } else {
            viewContainter.add(new PkhjbxxPage(this));
            viewContainter.add(new PkhjtcyPage(this));
            viewContainter.add(new PkhszqkPage(this));
            viewContainter.add(new PkhzfqkPage(this));
            viewContainter.add(new PkhshtjPage(this));
            viewContainter.add(new PkhsctjPage(this));
            viewContainter.add(new PkhcyhPage(this));
            viewContainter.add(new PkhjtqkzpPage(this));
        }
    }

    private void initTitle() {
        PkhjbxxBean pkh;
        if (mEditable) {
            pkh = SettingManager.getCurrentJdPkh();
            findViewById(R.id.detail_save).setVisibility(View.VISIBLE);
        } else {
            pkh = SettingManager.getCurrentPkh();
        }
        ImageView pkhPhoto = (ImageView)findViewById(R.id.detail_photo);
        TextView pkhName = (TextView) findViewById(R.id.detail_text_name);
        TextView pkhPhone = (TextView) findViewById(R.id.detail_text_phone);
        TextView pkhAddress = (TextView) findViewById(R.id.detail_text_address);
        ImageLoader.getInstance().displayImage(pkh.getTpdz(),pkhPhoto, EVApplication.getDisplayImageOptions());
        pkhName.setText(pkh.getHzxm());
        pkhAddress.setText(pkh.getJzdz());
        pkhPhone.setText(pkh.getLxdh());
    }

    private void initView() {
        initTitle();
        mPager = (ViewPager) this.findViewById(R.id.view_pager);
        if (mPager == null) return;
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
        mPager.setAdapter(new PagerAdapter() {
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

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        for (BasePage page:viewContainter) {
            page.setSelected(false);
        }
        viewContainter.get(selectedIndex).setSelected(true);
        // 如果没有数据或者数据也可编辑时返回刷新
        if (!viewContainter.get(selectedIndex).hasData()||mEditable) {
            tryToShowProcessDialog();
            viewContainter.get(selectedIndex).requestData();
        }
        mPager.setCurrentItem(selectedIndex);
    }
}
