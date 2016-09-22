package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import de.greenrobot.event.EventBus;

/**
 * 消息列表页
 */
public class MsgsActivity extends BaseActivity {

    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtcyBean event) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        if (null != intent) {
//            tzId = intent.getStringExtra("tzId");
//            tznd = intent.getStringExtra("tznd");
//        }
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected String getActivityName() {
        return "MsgsActivity";
    }


    private void initTitle() {

        TextView title = (TextView)findViewById(R.id.title_text);
        if (null != title) {
            title.setText(getString(R.string.msg_title));
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

    private void initView() {
        initTitle();

    }


}
