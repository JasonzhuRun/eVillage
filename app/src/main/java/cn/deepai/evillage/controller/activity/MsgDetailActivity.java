package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.NewsBean;

/**
 * @author GaoYixuan
 */
public class MsgDetailActivity extends BaseActivity {

    private NewsBean mNewsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected String getActivityName() {
        return "NewsDetailActivity";
    }

    private void initView() {
        TextView  title = (TextView)findViewById(R.id.news_title);
        TextView content = (TextView)findViewById(R.id.news_content);
        TextView time = (TextView)findViewById(R.id.news_time);
        ImageView photo = (ImageView)findViewById(R.id.news_photo);
        if (title != null) {
            title.setText(mNewsBean.getTitle());
        }
        if (time != null) {
            time.setText(mNewsBean.getPolicyDate());
        }
        if (content != null) {
//            String policy;
//            try {
//                policy =  new String(mNewsBean.getPolicy1().getBytes((long)1, (int)mNewsBean.getPolicy1().length()));
//            } catch (UnsupportedEncodingException e) {
//                policy = mNewsBean.getPolicyDate();
//                e.printStackTrace();
//            }
            content.setText(mNewsBean.getPolicy1());
        }
        if (photo != null) {
            ImageLoader.getInstance().displayImage(mNewsBean.getBz(),photo, EVApplication.getDisplayImageOptions());
        }
        // title
        TextView heading = (TextView)findViewById(R.id.title_text);
        if (null != heading) {
            heading.setText(getString(R.string.news_detail));
        }
        View view = findViewById(R.id.normal_title_back);
        if (null != view) {
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }
}
