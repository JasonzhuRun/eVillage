package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.NewsBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;

/**
 * @author GaoYixuan
 */
public class NewsDetailActivity extends BaseActivity {

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
        if (title != null) {
            title.setText(mNewsBean.getTitle());
        }
        if (content != null) {
            content.setText(mNewsBean.getPolicy());
        }
        if (time != null) {
            time.setText(mNewsBean.getPolicyDate());
        }
    }
}
