package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.net.PkhJtcyRequest;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 家庭成员信息详情
 */
public class PkhjtcyActivity extends BaseActivity {

    private EditText xm;
    private EditText xb;
    private EditText sfzhm;
    private EditText yhzgx;
    private EditText mz;
    private EditText whcd;
    private EditText zxsqk;
    private EditText jkqk;
    private EditText ldnlzk;
    private EditText wgzk;
    private EditText wgsj;
    private EditText dbrk;
    private EditText cjxnhyl;
    private EditText cjcxjmjbylbx;
    private EditText sfldlzyjy;
    private EditText sfxyjr;
    private EditText zzmm;
    private EditText sfczzgylbx;
    private EditText zdxx;
    private EditText cyzt;
    private EditText ztbhsj;

    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtcyBean event) {
        onBindData(event);
        tryToHideProcessDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkhjtcy);
        String id = getIntent().getStringExtra("id");
        if (id == null) {
            ToastUtil.shortToast(getResources().getString(R.string.pkh_jtcy_none));
            finish();
        } else {
            initView();
            EventBus.getDefault().register(this);
            PkhJtcyRequest.request(id);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected String getActivityName() {
        return "PkhjtcyActivity";
    }

    public void onBindData(PkhjtcyBean pkhjtcyBean) {

        xm.setText(pkhjtcyBean.getXm());
        xb.setText(DictionaryUtil.getValueName("COMMON.GENDER",pkhjtcyBean.getXb()));
        sfzhm.setText(pkhjtcyBean.getSfzhm());
        yhzgx.setText(DictionaryUtil.getValueName("YHZGX",pkhjtcyBean.getYhzgx()));
        mz.setText(DictionaryUtil.getValueName("NATION",pkhjtcyBean.getMz()));
        whcd.setText(DictionaryUtil.getValueName("WHCD",pkhjtcyBean.getWhcd()));
        zxsqk.setText(pkhjtcyBean.getZxsqk());
        jkqk.setText(DictionaryUtil.getValueName("JKQK",pkhjtcyBean.getJkqk()));
        ldnlzk.setText(DictionaryUtil.getValueName("LDLZK",pkhjtcyBean.getLdnlzk()));
        wgzk.setText(DictionaryUtil.getValueName("DGZT",pkhjtcyBean.getWgzk()));
        wgsj.setText(pkhjtcyBean.getWgsj());
        dbrk.setText(DictionaryUtil.getValueName(pkhjtcyBean.getDbrk()));
        cjxnhyl.setText(DictionaryUtil.getValueName(pkhjtcyBean.getCjxnhyl()));
        cjcxjmjbylbx.setText(DictionaryUtil.getValueName(pkhjtcyBean.getCjcxjmjbylbx()));
        sfldlzyjy.setText(DictionaryUtil.getValueName(pkhjtcyBean.getSfldlzyjy()));
        sfxyjr.setText(DictionaryUtil.getValueName(pkhjtcyBean.getSfxyjr()));
        zzmm.setText(DictionaryUtil.getValueName("ZZMM",pkhjtcyBean.getZzmm()));
        sfczzgylbx.setText(DictionaryUtil.getValueName(pkhjtcyBean.getSfczzgylbx()));
        zdxx.setText(pkhjtcyBean.getZdxx());
        cyzt.setText(DictionaryUtil.getValueName("CYZT",pkhjtcyBean.getCyzt()));
        ztbhsj.setText(pkhjtcyBean.getZtbhsj());
    }

    private void initView() {

        xm = (EditText) findViewById(R.id.jtcy_xm);
        xb = (EditText) findViewById(R.id.jtcy_xb);
        sfzhm = (EditText) findViewById(R.id.jtcy_sfzhm);
        yhzgx = (EditText) findViewById(R.id.jtcy_yhzgx);
        mz = (EditText) findViewById(R.id.jtcy_mz);
        whcd = (EditText) findViewById(R.id.jtcy_whcd);
        zxsqk = (EditText) findViewById(R.id.jtcy_zxsqk);
        jkqk = (EditText) findViewById(R.id.jtcy_jkqk);
        ldnlzk = (EditText) findViewById(R.id.jtcy_ldnlzk);
        wgzk = (EditText) findViewById(R.id.jtcy_wgzk);
        wgsj = (EditText) findViewById(R.id.jtcy_wgsj);
        dbrk = (EditText) findViewById(R.id.jtcy_dbrk);
        cjxnhyl = (EditText) findViewById(R.id.jtcy_cjxnhyl);
        cjcxjmjbylbx = (EditText) findViewById(R.id.jtcy_cjcxjmjbylbx);
        sfldlzyjy = (EditText) findViewById(R.id.jtcy_sfldlzyjy);
        sfxyjr = (EditText) findViewById(R.id.jtcy_sfxyjr);
        zzmm = (EditText) findViewById(R.id.jtcy_zzmm);
        sfczzgylbx = (EditText) findViewById(R.id.jtcy_sfczzgylbx);
        zdxx = (EditText) findViewById(R.id.jtcy_zdxx);
        cyzt = (EditText) findViewById(R.id.jtcy_cyzt);
        ztbhsj = (EditText) findViewById(R.id.jtcy_ztbhsj);

        TextView title = (TextView)findViewById(R.id.title_text);
        if (null != title) {
            title.setText(getString(R.string.pkh_jtcy));
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
