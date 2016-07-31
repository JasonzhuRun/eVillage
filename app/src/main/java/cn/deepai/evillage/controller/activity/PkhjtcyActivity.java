package cn.deepai.evillage.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.net.PkhJtcyRequest;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 家庭成员信息详情
 */
public class PkhjtcyActivity extends BaseActivity {

    private boolean mEditable;
    private Context mContext;
    private PkhjtcyBean localData;

    private EditText xm;
    private TextView xb;
    private EditText sfzhm;
    private TextView yhzgx;
    private TextView mz;
    private TextView whcd;
    private TextView zxsqk;
    private TextView jkqk;
    private TextView ldnlzk;
    private TextView wgzk;
    private EditText wgsj;
    private TextView dbrk;
    private TextView cjxnhyl;
    private TextView cjcxjmjbylbx;
    private TextView sfldlzyjy;
    private TextView sfxyjr;
    private TextView zzmm;
    private TextView sfczzgylbx;
    private EditText zdxx;
    private TextView cyzt;
    private EditText ztbhsj;

    @Override
    public void onBackPressed() {
        if (mEditable) {
            DialogManager.showYesOrNoChoiceDialog(this,this.getString(R.string.save_info),
                    new DialogManager.IOnDialogFinished() {
                        @Override
                        public void returnData(String data) {
                            if (data.equals(PkhjtcyActivity.this.getString(R.string.yes))) {
                                localData.setXm(xm.getText().toString());
                                localData.setSfzhm(sfzhm.getText().toString());
                                localData.setWgsj(wgsj.getText().toString());
                                localData.setZdxx(zdxx.getText().toString());
                                localData.setZtbhsj(ztbhsj.getText().toString());
                                PkhjtcyActivity.super.onBackPressed();
                            } else {
                                PkhjtcyActivity.super.onBackPressed();
                            }
                        }
                    });
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.jtcy_xb_layout)
    public void onXbClick() {
        final String[] values = new String[]{
                DictionaryUtil.getValueName("COMMON.GENDER","M"),
                DictionaryUtil.getValueName("COMMON.GENDER","F")
        };
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_xb),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        xb.setText(data);
                        if (data.equals(values[0])) localData.setXb("M");
                        else localData.setXb("F");
                    }
                });
    }

    @OnClick(R.id.jtcy_yhzgx_layout)
    public void onGxClick() {
        final String[] values = new String[21];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("YHZGX",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_yhzgx),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        yhzgx.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setYhzgx(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_mz_layout)
    public void onMzClick() {
        final String[] values = new String[56];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("NATION",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_mz),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        mz.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setMz(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_whcd_layout)
    public void onWhcdClick() {
        final String[] values = new String[10];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("WHCD",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_whcd),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        whcd.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setWhcd(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_zxsqk_layout)
    public void onZxsqkClick() {

        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_zxsqk),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        zxsqk.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setZxsqk("1");
                        else localData.setZxsqk("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_jkqk_layout)
    public void onJkqkClick() {
        final String[] values = new String[5];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("JKQK",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_jkqk),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        jkqk.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setJkqk(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_ldnlzk_layout)
    public void onLdlzkClick() {
        final String[] values = new String[3];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("LDLZK",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_ldnlzk),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        ldnlzk.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setLdnlzk(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_wgzk_layout)
    public void onWgzkClick() {
        final String[] values = new String[5];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("DGZT",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_wgzk),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        wgzk.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setWgzk(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_dbrk_layout)
    public void onDbrkClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_dbrk),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        dbrk.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setDbrk("1");
                        else localData.setDbrk("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_cjxnhyl_layout)
    public void onCjxnhylClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_cjxnhyl),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cjxnhyl.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setCjxnhyl("1");
                        else localData.setCjxnhyl("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_cjcxjmjbylbx_layout)
    public void onCjcxjmjbylbxClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_cjcxjmjbylbx),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cjcxjmjbylbx.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setCjcxjmjbylbx("1");
                        else localData.setCjcxjmjbylbx("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_sfldlzyjy_layout)
    public void onSfldlzyjyClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_sfldlzyjy),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sfldlzyjy.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setSfldlzyjy("1");
                        else localData.setSfldlzyjy("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_sfxyjr_layout)
    public void onSfxyjrClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_sfxyjr),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sfxyjr.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setSfxyjr("1");
                        else localData.setSfxyjr("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_zzmm_layout)
    public void onZzmmClick() {
        final String[] values = new String[3];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("ZZMM",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_zzmm),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        zzmm.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setZzmm(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.jtcy_sfczzgylbx_layout)
    public void onSfczzgylbxClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_sfczzgylbx),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sfczzgylbx.setText(data);
                        if (mContext.getString(R.string.yes).equals(data)) localData.setSfczzgylbx("1");
                        else localData.setSfczzgylbx("0");
                    }
                });
    }

    @OnClick(R.id.jtcy_cyzt_layout)
    public void onCyztClick() {
        final String[] values = new String[3];
        for (int i = 0;i < values.length;i++) {
            values[i] =  DictionaryUtil.getValueName("CYZT",String.valueOf(i+1));
        }
        DialogManager.showSingleChoiceDialog(mContext,mContext.getString(R.string.pkh_jtcy_cyzt),
                values,
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        cyzt.setText(data);
                        for (int i = 0;i < values.length;i++) {
                            if (data.equals(values[i])) {
                                localData.setCyzt(String.valueOf(i + 1));
                                break;
                            }
                        }
                    }
                });
    }
    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtcyBean event) {
        onBindData(event);
        tryToHideProcessDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkhjtcy);
        localData = new PkhjtcyBean();
        String id = getIntent().getStringExtra("id");
        mEditable = getIntent().getBooleanExtra("editable",false);
        EventBus.getDefault().register(this);
        mContext = this;
        if (mEditable) {
            ButterKnife.bind(this);
            initView();
            if (id != null) {
                PkhJtcyRequest.request(id);
            }
        } else {
            if (id == null) {
                ToastUtil.shortToast(getResources().getString(R.string.pkh_jtcy_none));
                finish();
            }
            initView();
            PkhJtcyRequest.request(id);
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
        return "PkhjtcyActivity";
    }

    public void onBindData(PkhjtcyBean pkhjtcyBean) {
        this.localData = pkhjtcyBean;
        xm.setText(pkhjtcyBean.getXm());
        xb.setText(DictionaryUtil.getValueName("COMMON.GENDER",pkhjtcyBean.getXb()));
        sfzhm.setText(pkhjtcyBean.getSfzhm());
        yhzgx.setText(DictionaryUtil.getValueName("YHZGX",pkhjtcyBean.getYhzgx()));
        mz.setText(DictionaryUtil.getValueName("NATION",pkhjtcyBean.getMz()));
        whcd.setText(DictionaryUtil.getValueName("WHCD",pkhjtcyBean.getWhcd()));
        zxsqk.setText(DictionaryUtil.getValueName(pkhjtcyBean.getZxsqk()));
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
        xb = (TextView) findViewById(R.id.jtcy_xb);
        sfzhm = (EditText) findViewById(R.id.jtcy_sfzhm);
        yhzgx = (TextView) findViewById(R.id.jtcy_yhzgx);
        mz = (TextView) findViewById(R.id.jtcy_mz);
        whcd = (TextView) findViewById(R.id.jtcy_whcd);
        zxsqk = (TextView) findViewById(R.id.jtcy_zxsqk);
        jkqk = (TextView) findViewById(R.id.jtcy_jkqk);
        ldnlzk = (TextView) findViewById(R.id.jtcy_ldnlzk);
        wgzk = (TextView) findViewById(R.id.jtcy_wgzk);
        wgsj = (EditText) findViewById(R.id.jtcy_wgsj);
        dbrk = (TextView) findViewById(R.id.jtcy_dbrk);
        cjxnhyl = (TextView) findViewById(R.id.jtcy_cjxnhyl);
        cjcxjmjbylbx = (TextView) findViewById(R.id.jtcy_cjcxjmjbylbx);
        sfldlzyjy = (TextView) findViewById(R.id.jtcy_sfldlzyjy);
        sfxyjr = (TextView) findViewById(R.id.jtcy_sfxyjr);
        zzmm = (TextView) findViewById(R.id.jtcy_zzmm);
        sfczzgylbx = (TextView) findViewById(R.id.jtcy_sfczzgylbx);
        zdxx = (EditText) findViewById(R.id.jtcy_zdxx);
        cyzt = (TextView) findViewById(R.id.jtcy_cyzt);
        ztbhsj = (EditText) findViewById(R.id.jtcy_ztbhsj);
        xm.setFocusable(mEditable);
        sfzhm.setFocusable(mEditable);
        wgsj.setFocusable(mEditable);
        zdxx.setFocusable(mEditable);
        ztbhsj.setFocusable(mEditable);
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
