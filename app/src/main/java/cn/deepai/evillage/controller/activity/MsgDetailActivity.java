package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.MsgBean;

/**
 * @author GaoYixuan
 */
public class MsgDetailActivity extends BaseActivity {

    private MsgBean mMsgBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);
        mMsgBean = (MsgBean) getIntent().getSerializableExtra("msg");
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected String getActivityName() {
        return "MsgDetailActivity";
    }

    private void initView() {
        TextView  title = (TextView)findViewById(R.id.msg_title);
        TextView content = (TextView)findViewById(R.id.msg_content);
        TextView time = (TextView)findViewById(R.id.msg_time);
        if (title != null) {
            title.setText(mMsgBean.getBulletin_title());
        }
        if (time != null) {
            time.setText(mMsgBean.getPublish_time());
        }
        if (content != null) {
            content.setText(mMsgBean.getBulletin_content1());
        }

        // title
        TextView heading = (TextView)findViewById(R.id.title_text);
        if (null != heading) {
            heading.setText(getString(R.string.msg_detail));
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
