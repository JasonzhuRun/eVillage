package cn.deepai.evillage.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.event.TzDataSaveEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账基本信息
 */
public class TzjbxxPage extends BasePage {

    private String tzId;
    private String tznd;

    private EditText nrjcsr;
    private EditText nsrhj;
    private EditText nzchj;
    private EditText dkje;
    private EditText zfjg;
    private EditText zfmj;
    private EditText sfwf;
    private EditText gdtian;
    private EditText gdtu;
    private EditText gdldm;
    private EditText gdhjm;
    private EditText sfts;
    private EditText sftd;
    private EditText sftl;
    private EditText sftx;
    private EditText sftds;
    private EditText sftkd;

    private TzjbxxBean serverData;
    private TzjbxxBean localData;

    public TzjbxxPage(Context context) {
        this(context, null, null, null);
    }

    public TzjbxxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzjbxxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzjbxxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_tzjbxx, this);
        ButterKnife.bind(this);
        tzId = id;
        tznd = nd;
        initView();
    }

    @Override
    public void registeEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisteEventBus() {
        EventBus.getDefault().unregister(this);
    }
    // 返回了页面信息
    @SuppressWarnings("all")
    public void onEventMainThread(TzjbxxBean event) {
        bindData(event);
    }
    // 点击保存按钮
    @SuppressWarnings("all")
    public void onEventMainThread(TzDataSaveEvent event) {
        localData.setNrjcsr(nrjcsr.getText().toString());
        localData.setNsrhj(nsrhj.getText().toString());
        localData.setNzchj(nzchj.getText().toString());
        localData.setDkje(dkje.getText().toString());
        localData.setZfjg(zfjg.getText().toString());
        localData.setZfmj(zfmj.getText().toString());
        localData.setGdtian(gdtian.getText().toString());
        localData.setGdtu(gdtu.getText().toString());
        localData.setGdldm(gdldm.getText().toString());
        localData.setGdhjm(gdhjm.getText().toString());
        // 选择项在选择时已经赋过值
//      DictionaryUtil.getValueName(localData.getSfwf())
//      DictionaryUtil.getValueName(localData.getSfts())
//      DictionaryUtil.getValueName(localData.getSftd())
//      DictionaryUtil.getValueName(localData.getSftl())
//      DictionaryUtil.getValueName(localData.getSftx())
//      DictionaryUtil.getValueName(localData.getSftds())
//      DictionaryUtil.getValueName(localData.getSftkd())
        //todo 修改接口写好以后上传
    }

    @Override
    public void requestData() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("tzid", tzId);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzjbxx)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzjbxxBean responseEvent = requestGson.fromJson(dataJsonString, TzjbxxBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_jbxx);
    }

    @OnClick(R.id.jbxx_sfwf)
    public void onSfwfClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sfwf),
                new DialogManager.IOnDialogFinished() {
            @Override
            public void returnData(String data) {
                sfwf.setText(data);
                if (mContext.getString(R.string.no).equals(data)) {
                    localData.setNrjcsr("0");
                } else localData.setNrjcsr("1");
            }
        });
    }

    @OnClick(R.id.jbxx_sfts)
    public void onSftsClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sfts),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sfts.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_sftd)
    public void onSftdClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sftd),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sftd.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_sftl)
    public void onSftlClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sftl),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sftl.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_sftx)
    public void onSftxClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sftx),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sftx.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_sftds)
    public void onSftdsClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sftds),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sftds.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    @OnClick(R.id.jbxx_sftkd)
    public void onSftkdClick() {
        DialogManager.showYesOrNoChoiceDialog(mContext,mContext.getString(R.string.tz_jbxx_sftkd),
                new DialogManager.IOnDialogFinished() {
                    @Override
                    public void returnData(String data) {
                        sftkd.setText(data);
                        if (mContext.getString(R.string.no).equals(data)) {
                            localData.setNrjcsr("0");
                        } else localData.setNrjcsr("1");
                    }
                });
    }

    private void initView() {

        nrjcsr = (EditText) findViewById(R.id.jbxx_nrjcsr);
        nsrhj = (EditText) findViewById(R.id.jbxx_nsrhj);
        nzchj = (EditText) findViewById(R.id.jbxx_nzchj);
        dkje = (EditText) findViewById(R.id.jbxx_dkje);
        zfjg = (EditText) findViewById(R.id.jbxx_zfjg);
        zfmj = (EditText) findViewById(R.id.jbxx_zfmj);
        gdtian = (EditText) findViewById(R.id.jbxx_gdtian);
        gdtu = (EditText) findViewById(R.id.jbxx_gdtu);
        gdldm = (EditText) findViewById(R.id.jbxx_gdldm);
        gdhjm = (EditText) findViewById(R.id.jbxx_gdhjm);
        // yes or no
        sfwf = (EditText) findViewById(R.id.jbxx_sfwf);
        sfts = (EditText) findViewById(R.id.jbxx_sfts);
        sftd = (EditText) findViewById(R.id.jbxx_sftd);
        sftl = (EditText) findViewById(R.id.jbxx_sftl);
        sftx = (EditText) findViewById(R.id.jbxx_sftx);
        sftds = (EditText) findViewById(R.id.jbxx_sftds);
        sftkd = (EditText) findViewById(R.id.jbxx_sftkd);
        mHasData = false;
    }

    private void bindData(TzjbxxBean tzjbxxBean) {

        this.serverData = tzjbxxBean;
        this.localData = tzjbxxBean;
        nrjcsr.setText(tzjbxxBean.getNrjcsr());
        nsrhj.setText(tzjbxxBean.getNsrhj());
        nzchj.setText(tzjbxxBean.getNzchj());
        dkje.setText(tzjbxxBean.getDkje());
        zfjg.setText(tzjbxxBean.getZfjg());
        zfmj.setText(tzjbxxBean.getZfmj());
        sfwf.setText(DictionaryUtil.getValueName(tzjbxxBean.getSfwf()));
        gdtian.setText(tzjbxxBean.getGdtian());
        gdtu.setText(tzjbxxBean.getGdtu());
        gdldm.setText(tzjbxxBean.getGdldm());
        gdhjm.setText(tzjbxxBean.getGdhjm());
        sfts.setText(DictionaryUtil.getValueName(tzjbxxBean.getSfts()));
        sftd.setText(DictionaryUtil.getValueName(tzjbxxBean.getSftd()));
        sftl.setText(DictionaryUtil.getValueName(tzjbxxBean.getSftl()));
        sftx.setText(DictionaryUtil.getValueName(tzjbxxBean.getSftx()));
        sftds.setText(DictionaryUtil.getValueName(tzjbxxBean.getSftds()));
        sftkd.setText(DictionaryUtil.getValueName(tzjbxxBean.getSftkd()));
        mHasData = true;
    }


}
