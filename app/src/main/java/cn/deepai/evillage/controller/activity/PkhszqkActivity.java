package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhszqkBean;

/**
 * 收支情况详情页
 */
public class PkhszqkActivity extends BaseActivity {

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
        jtzsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        wgsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        scjyxsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        zyxsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        ccxsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        dk.setText(String.valueOf(pkhszqkBean.getTjnd()));
        scjyzcfy.setText(String.valueOf(pkhszqkBean.getTjnd()));
        jtcsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        jtnrjcsr.setText(String.valueOf(pkhszqkBean.getTjnd()));
        glbt.setText(String.valueOf(pkhszqkBean.getTjnd()));
        jhsyj.setText(String.valueOf(pkhszqkBean.getTjnd()));
        dbj.setText(String.valueOf(pkhszqkBean.getTjnd()));
        cxjmjbylbx.setText(String.valueOf(pkhszqkBean.getTjnd()));
        ylbx.setText(String.valueOf(pkhszqkBean.getTjnd()));
        deylbz.setText(String.valueOf(pkhszqkBean.getTjnd()));
        stbcj.setText(String.valueOf(pkhszqkBean.getTjnd()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkhszqk);
        initView();
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
    }
}
