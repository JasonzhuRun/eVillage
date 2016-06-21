package cn.deepai.evillage.controller.activity;

import android.os.Bundle;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public class NewsDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected String getActivityName() {
        return "NewsDetailActivity";
    }
}
