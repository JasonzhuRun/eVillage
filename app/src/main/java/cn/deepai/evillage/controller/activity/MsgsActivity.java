package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.MsgRecyclerAdapter;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.MsgList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * 消息列表页
 */
public class MsgsActivity extends BaseActivity {

    private MsgRecyclerAdapter mMsgRecyclerAdapter;

    // 接受消息提示
    @SuppressWarnings("all")
    public void onEventMainThread(MsgList event) {
        tryToHideProcessDialog();
        mMsgRecyclerAdapter.notifyResult(true,event.list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        ButterKnife.bind(this);
        initView();
        initData();
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_msgs);
        if (recyclerView == null) return;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMsgRecyclerAdapter = new MsgRecyclerAdapter();
        recyclerView.setAdapter(mMsgRecyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        tryToShowProcessDialog();
        String id = SettingManager.getInstance().getUserId();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getMsgList);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_MSG_LIST, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        MsgList msgList = gson.fromJson(dataJsonString, MsgList.class);
                        EventBus.getDefault().post(msgList);
                    }
                });
    }

}
