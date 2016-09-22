package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzzfqkBean;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.PkhSzqkRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

import static cn.deepai.evillage.model.event.ReturnValueEvent.SUCCESS;

/**
 * 台账收支明细新建
 */
public class TzzfqkActivity extends BaseActivity {

    private Context mContext;
    private TzzfqkBean mTzzfqkBean;
    private String tzId;

    private EditText zfsj;
    private EditText lsqk;
    private EditText bfcx;
    private EditText sfzs;

    @Override
    public void onBackPressed() {
        DialogManager.showYesOrNoChoiceDialog(this,this.getString(R.string.save_info),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        if (data.equals(TzzfqkActivity.this.getString(R.string.yes))) {
                            mTzzfqkBean.setTzid(tzId);
                            mTzzfqkBean.setLsqk(lsqk.getText().toString());
                            mTzzfqkBean.setBfcx(bfcx.getText().toString());
                            mTzzfqkBean.setSfzsy(sfzs.getText().toString());
                            mTzzfqkBean.setHid(SettingManager.getCurrentPkh().getHid());

                            tryToShowProcessDialog();
                            final Gson gson = new Gson();
                            RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addTzzfqk);
                            EVRequest.request(Action.ACTION_ADD_TZZFQK, gson.toJson(header), gson.toJson(mTzzfqkBean),
                                    new ResponseCallback() {
                                        @Override
                                        public void onDataResponse(String dataJsonString) {
                                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                            EventBus.getDefault().post(returnValueEvent);
                                        }
                                    });
                        } else {
                            TzzfqkActivity.super.onBackPressed();
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

    @OnClick(R.id.zfqk_zfsj_layout)
    public void onZfsjClick() {

        DialogManager.showDateDialog(mContext,mContext.getString(R.string.tz_zfqk_zfsj),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        zfsj.setText(data);
                        mTzzfqkBean.setZfsj(data);
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tzzfqk);
        mContext = this;
        tzId = getIntent().getStringExtra("tzid");
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        initView();
        mTzzfqkBean = new TzzfqkBean();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    protected String getActivityName() {
        return "TzzfqkActivity";
    }

    private void initView() {

        zfsj = (EditText) findViewById(R.id.zfqk_zfsj);
        lsqk = (EditText) findViewById(R.id.zfqk_lsqk);
        bfcx = (EditText) findViewById(R.id.zfqk_bfcx);
        sfzs = (EditText) findViewById(R.id.zfqk_sfzsy);

        TextView title = (TextView)findViewById(R.id.title_text);
        if (null != title) {
            title.setText(getString(R.string.tz_zfqk));
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
