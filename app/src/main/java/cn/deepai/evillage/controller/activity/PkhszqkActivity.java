package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.net.PkhSzqkRequest;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 收支情况详情页
 */
public class PkhszqkActivity extends BaseActivity {

    private String id;
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


    public void onBindData(PkhszqkBean pkhszqkBean) {
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        initView();
        id = getIntent().getStringExtra("id");
        if (id == null) {
            ToastUtil.shortToast(getResources().getString(R.string.pkh_szqk_none));
            finish();
        } else {
            initView();
            EventBus.getDefault().register(this);
            PkhSzqkRequest.request(id);
        }
    }


    @Override
    protected String getActivityName() {
        return "PkhszqkActivity";
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
    }
}
