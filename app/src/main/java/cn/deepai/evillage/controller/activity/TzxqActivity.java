package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.model.event.TakePhotoEvent;
import cn.deepai.evillage.utils.EncryptionUtil;
import cn.deepai.evillage.utils.FileUtil;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import cn.deepai.evillage.view.BasePage;
import cn.deepai.evillage.view.TzjbxxPage;
import cn.deepai.evillage.view.TzjtcyPage;
import cn.deepai.evillage.view.TzjtqkzpPage;
import cn.deepai.evillage.view.TzsrmxPage;
import cn.deepai.evillage.view.TzzcmxPage;
import cn.deepai.evillage.view.TzzfxxPage;
import de.greenrobot.event.EventBus;

import static cn.deepai.evillage.model.event.ReturnValueEvent.SUCCESS;

/**
 * 台账详情页
 */
public class TzxqActivity extends BaseActivity {

    private static String zplx;

    private int selectedIndex = 0;
    private ArrayList<BasePage> viewContainter = new ArrayList<>();
    private String tzId;
    private String tznd;
    private ViewPager mPager;

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
    public void onEventMainThread(TakePhotoEvent event) {
        Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(getImageByCamera, 1);
        this.zplx = event.zplx;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                //todo 获取相册中的照片
            } else if (requestCode == 1 ) {
                Uri uri = data.getData();
                String addr = null;
                if(uri == null){
                    //use bundle to get data
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                        addr = saveBitmap(EncryptionUtil.getMD5(new Date().toString()),photo);
                    }
                }else{
                    addr = uri.getPath();
                }
                BasePage page = viewContainter.get(selectedIndex);
                if (page instanceof BasePage.IPhotoEdit) {
                    ((BasePage.IPhotoEdit)page).addPhoto(addr,zplx);
                }
            }
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
        viewContainter.add(new TzjtqkzpPage(this,tzId,tznd));
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
        mPager = (ViewPager) this.findViewById(R.id.view_pager);
        if (mPager == null) return;
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

        TabLayout tabStrip = (TabLayout) this.findViewById(R.id.view_pager_tabstrip);
        if (tabStrip != null) {
            tabStrip.setTabMode(TabLayout.MODE_SCROLLABLE);
            for (BasePage view :viewContainter) {
                tabStrip.addTab(tabStrip.newTab().setText(view.getPageName()));
            }
            tabStrip.setupWithViewPager(mPager);
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
        mPager.setCurrentItem(selectedIndex);
    }

    private String saveBitmap(String picName,Bitmap bitmap){
        File f = new File(FileUtil.getPicCacheDirPath()+ File.separator + picName + ".png");
        try {
            if (!f.createNewFile()) {
                LogUtil.e(PkhxqActivity.class,"Can't creat pic file");
            }
        } catch (IOException e) {

            LogUtil.e(PkhxqActivity.class,"Save Bitmap error:"+e.toString());
        }
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            LogUtil.e(PkhxqActivity.class,"Save Bitmap error:"+e.toString());
            return null;
        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getPath();
    }
}
