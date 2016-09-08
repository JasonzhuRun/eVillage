package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.model.bean.TzxmxxBean;
import cn.deepai.evillage.model.bean.TzxmxxList;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

import static cn.deepai.evillage.model.event.ReturnValueEvent.SUCCESS;

/**
 * 台账收支明细新建
 */
public class TzszmxActivity extends BaseActivity {

    private Context mContext;
    // 项目信息列表
    private List<TzxmxxBean> mTzxmxxList;
    private TzsrmxBean mTzsrmxBean;
    private TzzcmxBean mTzzcmxBean;
    // 页面类型:sr 收入明细 zc 支出明细
    private String type;
    private String tzId;

    private TextView xmmc;
    // zc
    private TextView zcyf;
    private RelativeLayout zcyfLayout;
    private EditText dkyt;
    private RelativeLayout dkytLayout;
    private EditText zcje;
    private RelativeLayout zcjeLayout;
    // sr
    private EditText xmgm;
    private RelativeLayout xmgmLayout;
    private EditText clgj;
    private RelativeLayout clgjLayout;
    private EditText nsry;
    private RelativeLayout nsryLayout;
    @Override
    public void onBackPressed() {

        DialogManager.showYesOrNoChoiceDialog(this,this.getString(R.string.save_info),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        if (data.equals(TzszmxActivity.this.getString(R.string.yes))) {
                            tryToShowProcessDialog();
                            final Gson gson = new Gson();
                            if (type.equals("zc")) {
                                mTzzcmxBean.setTzid(tzId);
                                mTzzcmxBean.setDkyt(dkyt.getText().toString());
                                mTzzcmxBean.setZcjey(zcje.getText().toString());
                                RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addTzzcmx);
                                EVRequest.request(Action.ACTION_ADD_TZZCMX, gson.toJson(header), gson.toJson(mTzzcmxBean),
                                        new ResponseCallback() {
                                            @Override
                                            public void onDataResponse(String dataJsonString) {
                                                ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                                EventBus.getDefault().post(returnValueEvent);
                                            }
                                        });
                            } else {
                                mTzsrmxBean.setTzid(tzId);
                                mTzsrmxBean.setXmgm(xmgm.getText().toString());
                                mTzsrmxBean.setCglj(clgj.getText().toString());
                                mTzsrmxBean.setNsry(nsry.getText().toString());
                                RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addTzsrmx);
                                EVRequest.request(Action.ACTION_ADD_TZSRMX, gson.toJson(header), gson.toJson(mTzsrmxBean),
                                        new ResponseCallback() {
                                            @Override
                                            public void onDataResponse(String dataJsonString) {
                                                ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                                EventBus.getDefault().post(returnValueEvent);
                                            }
                                        });
                            }
                        } else {
                            TzszmxActivity.super.onBackPressed();
                        }
                    }
                });
    }

    @SuppressWarnings("all")
    public void onEventMainThread(ReturnValueEvent event) {
        if (event.returnValue == SUCCESS) {
            ToastUtil.shortToast(getString(R.string.upload_success));
            super.onBackPressed();
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
            case RspCode.RSP_CODE_NO_CONNECTION:
                break;
            default:
                ToastUtil.shortToast(event.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @OnClick(R.id.szmx_xmmc_layout)
    public void onXmmcClick() {
        final String[] xmmcList = new String[mTzxmxxList.size()];
        for (int i = 0;i < mTzxmxxList.size();i++) {
            xmmcList[i] = mTzxmxxList.get(i).getXmmc();
        }
        DialogManager.showSingleChoiceDialog(mContext, mContext.getString(R.string.tz_xmmc),
                xmmcList,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        xmmc.setText(data);
                        for (TzxmxxBean tzxmxxBean:mTzxmxxList) {
                            if (data.equals(tzxmxxBean.getXmmc())) {
                                mTzzcmxBean.setXmid(tzxmxxBean.getXmid());
                                mTzsrmxBean.setXmid(tzxmxxBean.getXmid());
                                mTzzcmxBean.setXmmc(data);
                                mTzsrmxBean.setXmmc(data);
                            }
                        }
                    }
                });

    }

    @OnClick(R.id.szmx_zcyf_layout)
    public void onZcyfClick() {

        DialogManager.showDateDialog(mContext,mContext.getString(R.string.pkh_jtcy_ztbhsj),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        zcyf.setText(data);
                        mTzzcmxBean.setZcyf(data);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tzszmx);
        mContext = this;
        type = getIntent().getStringExtra("type");
        tzId = getIntent().getStringExtra("tzid");
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    protected String getActivityName() {
        return "TzszmxActivity";
    }

    private void initData() {

        mTzsrmxBean = new TzsrmxBean();
        mTzzcmxBean = new TzzcmxBean();

        tryToShowProcessDialog();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hid", SettingManager.getCurrentPkh().getHid());
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getTzxmxx);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZXMXX, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzxmxxList list = gson.fromJson(dataJsonString,TzxmxxList.class);
                        mTzxmxxList = list.list;
                    }
                });
    }

    private void initView() {

        xmmc = (TextView)findViewById(R.id.szmx_xmmc);
        // zc
        zcyf = (TextView)findViewById(R.id.szmx_zcyf);
        zcyfLayout = (RelativeLayout)findViewById(R.id.szmx_zcyf_layout);
        dkyt = (EditText)findViewById(R.id.szmx_dkyt);
        dkytLayout = (RelativeLayout)findViewById(R.id.szmx_dkyt_layout);
        zcje = (EditText)findViewById(R.id.szmx_zcje);
        zcjeLayout = (RelativeLayout)findViewById(R.id.szmx_zcje_layout);
        // sr
        xmgm = (EditText)findViewById(R.id.szmx_xmgm);
        xmgmLayout = (RelativeLayout)findViewById(R.id.szmx_xmgm_layout);
        clgj = (EditText)findViewById(R.id.szmx_clgj);
        clgjLayout = (RelativeLayout)findViewById(R.id.szmx_clgj_layout);
        nsry = (EditText)findViewById(R.id.szmx_nsry);
        nsryLayout = (RelativeLayout)findViewById(R.id.szmx_nsry_layout);
        if (type.equals("zc")) {
            zcyfLayout.setVisibility(View.VISIBLE);
            dkytLayout.setVisibility(View.VISIBLE);
            zcjeLayout.setVisibility(View.VISIBLE);
            xmgmLayout.setVisibility(View.GONE);
            clgjLayout.setVisibility(View.GONE);
            nsryLayout.setVisibility(View.GONE);
            TextView title = (TextView)findViewById(R.id.title_text);
            if (null != title) {
                title.setText(getString(R.string.tz_zcmx));
            }
        } else {
            TextView title = (TextView)findViewById(R.id.title_text);
            if (null != title) {
                title.setText(getString(R.string.tz_srmx));
            }
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
