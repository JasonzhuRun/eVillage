package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.PkhJtcyRequest;
import cn.deepai.evillage.net.PkhSzqkRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

import static cn.deepai.evillage.model.event.ReturnValueEvent.SUCCESS;

/**
 * 收支情况详情页
 */
public class PkhszqkActivity extends BaseActivity {

    private boolean mEditable;
    private Context mContext;
    private PkhszqkBean localData;

    private EditText tjnd;
    private EditText jtzsr;
    private EditText wgsr;
    private EditText scjyxsr;
    private EditText zyxsr;
    private EditText ccxsr;
    private EditText dk;
    private EditText scjyzcfy;
    private EditText jtcsr;
    private EditText jtnrjcsr;
    private EditText glbt;
    private EditText jhsyj;
    private EditText dbj;
    private EditText cxjmjbylbx;
    private EditText ylbx;
    private EditText deylbz;
    private EditText stbcj;

    @Override
    public void onBackPressed() {
        if (mEditable) {
            DialogManager.showYesOrNoChoiceDialog(this,this.getString(R.string.save_info),
                    new DialogManager.IOnDialogFinished() {
                        @Override
                        public void returnData(String data) {
                            if (data.equals(PkhszqkActivity.this.getString(R.string.yes))) {
                                localData.setTjnd(tjnd.getText().toString());
                                localData.setJtzsr(jtzsr.getText().toString());
                                localData.setWgsr(wgsr.getText().toString());
                                localData.setScjyxsr(scjyxsr.getText().toString());
                                localData.setZyxsr(zyxsr.getText().toString());
                                localData.setCcxsr(ccxsr.getText().toString());
                                localData.setDk(dk.getText().toString());
                                localData.setScjyzcfy(scjyzcfy.getText().toString());
                                localData.setJtcsr(jtcsr.getText().toString());
                                localData.setJtnrjcsr(jtnrjcsr.getText().toString());
                                localData.setGlbt(glbt.getText().toString());
                                localData.setJhsyj(jhsyj.getText().toString());
                                localData.setDbj(dbj.getText().toString());
                                localData.setCxjmjbylbx(cxjmjbylbx.getText().toString());
                                localData.setYlbx(ylbx.getText().toString());
                                localData.setDeylbz(deylbz.getText().toString());
                                localData.setStbcj(stbcj.getText().toString());

                                final PkhszqkBean szqkBean = localData;
                                tryToShowProcessDialog();
                                if (TextUtils.isEmpty(localData.getId())) {
                                    final Gson gson = new Gson();
                                    RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addPkhSzqkJbxx);

                                    EVRequest.request(Action.ACTION_ADD_PKHZFJBXX, gson.toJson(header), gson.toJson(szqkBean),
                                            new ResponseCallback() {
                                                @Override
                                                public void onDataResponse(String dataJsonString) {
                                                    ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                                    EventBus.getDefault().post(returnValueEvent);
                                                }
                                            });
                                } else {
                                    final Gson gson = new Gson();
                                    RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updatePkhSzqkJbxx);

                                    EVRequest.request(Action.ACTION_UPDATE_PKHSZQKJBXX, gson.toJson(header), gson.toJson(szqkBean),
                                            new ResponseCallback() {
                                                @Override
                                                public void onDataResponse(String dataJsonString) {
                                                    ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                                    EventBus.getDefault().post(returnValueEvent);
                                                }
                                            });
                                }
                            } else {
                                PkhszqkActivity.super.onBackPressed();
                            }
                        }
                    });
        } else {
            super.onBackPressed();
        }
    }

    public void onBindData(PkhszqkBean pkhszqkBean) {

        this.localData = pkhszqkBean;

        tjnd.setText(String.valueOf(pkhszqkBean.getTjnd()));
        jtzsr.setText(String.valueOf(pkhszqkBean.getJtzsr()));
        wgsr.setText(String.valueOf(pkhszqkBean.getWgsr()));
        scjyxsr.setText(String.valueOf(pkhszqkBean.getScjyxsr()));
        zyxsr.setText(String.valueOf(pkhszqkBean.getZyxsr()));
        ccxsr.setText(String.valueOf(pkhszqkBean.getCcxsr()));
        dk.setText(String.valueOf(pkhszqkBean.getDk()));
        scjyzcfy.setText(String.valueOf(pkhszqkBean.getScjyxsr()));
        jtcsr.setText(String.valueOf(pkhszqkBean.getJtcsr()));
        jtnrjcsr.setText(String.valueOf(pkhszqkBean.getJtnrjcsr()));
        glbt.setText(String.valueOf(pkhszqkBean.getGlbt()));
        jhsyj.setText(String.valueOf(pkhszqkBean.getJhsyj()));
        dbj.setText(String.valueOf(pkhszqkBean.getDbj()));
        cxjmjbylbx.setText(String.valueOf(pkhszqkBean.getCxjmjbylbx()));
        ylbx.setText(String.valueOf(pkhszqkBean.getYlbx()));
        deylbz.setText(String.valueOf(pkhszqkBean.getDeylbz()));
        stbcj.setText(String.valueOf(pkhszqkBean.getStbcj()));
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
    public void onEventMainThread(PkhszqkBean event) {
        onBindData(event);
        tryToHideProcessDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkhszqk);
        localData = new PkhszqkBean();
        String id = getIntent().getStringExtra("id");
        mEditable = getIntent().getBooleanExtra("editable",false);
        EventBus.getDefault().register(this);
        mContext = this;
        if (mEditable) {
            ButterKnife.bind(this);
            initView();
            if (id != null) {
                PkhSzqkRequest.request(id);
            }
        } else {
            if (id == null) {
                ToastUtil.shortToast(getResources().getString(R.string.pkh_szqk_none));
                finish();
            }
            initView();
            PkhSzqkRequest.request(id);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    protected String getActivityName() {
        return "PkhszqkActivity";
    }

    private void initView() {

        tjnd = (EditText) findViewById(R.id.szqk_tjnd);
        jtzsr = (EditText) findViewById(R.id.szqk_jtzsr);
        wgsr = (EditText) findViewById(R.id.szqk_wgsr);
        scjyxsr = (EditText) findViewById(R.id.szqk_scjyxsr);
        zyxsr = (EditText) findViewById(R.id.szqk_zyxsr);
        ccxsr = (EditText) findViewById(R.id.szqk_ccxsr);
        dk = (EditText) findViewById(R.id.szqk_dk);
        scjyzcfy = (EditText) findViewById(R.id.szqk_scjyzcfy);
        jtcsr = (EditText) findViewById(R.id.szqk_jtcsr);
        jtnrjcsr = (EditText) findViewById(R.id.szqk_jtnrjcsr);
        glbt = (EditText) findViewById(R.id.szqk_glbt);
        jhsyj = (EditText) findViewById(R.id.szqk_jhsyj);
        dbj = (EditText) findViewById(R.id.szqk_dbj);
        cxjmjbylbx = (EditText) findViewById(R.id.szqk_cxjmjbylbx);
        ylbx = (EditText) findViewById(R.id.szqk_ylbx);
        deylbz = (EditText) findViewById(R.id.szqk_deylbz);
        stbcj = (EditText) findViewById(R.id.szqk_stbcj);

        tjnd.setFocusable(mEditable);
        jtzsr.setFocusable(mEditable);
        wgsr.setFocusable(mEditable);
        scjyxsr.setFocusable(mEditable);
        zyxsr.setFocusable(mEditable);
        ccxsr.setFocusable(mEditable);
        dk.setFocusable(mEditable);
        scjyzcfy.setFocusable(mEditable);
        jtcsr.setFocusable(mEditable);
        jtnrjcsr.setFocusable(mEditable);
        glbt.setFocusable(mEditable);
        jhsyj.setFocusable(mEditable);
        dbj.setFocusable(mEditable);
        cxjmjbylbx.setFocusable(mEditable);
        ylbx.setFocusable(mEditable);
        deylbz.setFocusable(mEditable);
        stbcj.setFocusable(mEditable);

        TextView title = (TextView)findViewById(R.id.title_text);
        if (null != title) {
            title.setText(getString(R.string.pkh_szqk));
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
