package cn.deepai.evillage.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

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

    private TextView nrjcsr;
    private TextView nsrhj;
    private TextView nzchj;
    private TextView dkje;
    private TextView zfjg;
    private TextView zfmj;
    private TextView sfwf;
    private TextView gdtian;
    private TextView gdtu;
    private TextView gdldm;
    private TextView gdhjm;
    private TextView sfts;
    private TextView sftd;
    private TextView sftl;
    private TextView sftx;
    private TextView sftds;
    private TextView sftkd;

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

    @SuppressWarnings("all")
    public void onEventMainThread(TzjbxxBean event) {
        bindData(event);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(TzDataSaveEvent event) {


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

//    @OnClick(R.id.jbxx_nrjcsr_view)
//    public void onNrjcsrClick() {
//        DialogManager.showEditTextDialog(getContext(),getContext().getString(R.string.tz_jbxx_nrjcsr), new DialogManager.IOnDialogFinished() {
//            @Override
//            public void returnData(String data) {
//                if (!TextUtils.isEmpty(data)) {
//                    nrjcsr.setText(data);
//                    localData.setNrjcsr(data);
//                }
//            }
//        });
//    }

    private void initView() {

        nrjcsr = (TextView) findViewById(R.id.jbxx_nrjcsr);
        nsrhj = (TextView) findViewById(R.id.jbxx_nsrhj);
        nzchj = (TextView) findViewById(R.id.jbxx_nzchj);
        dkje = (TextView) findViewById(R.id.jbxx_dkje);
        zfjg = (TextView) findViewById(R.id.jbxx_zfjg);
        zfmj = (TextView) findViewById(R.id.jbxx_zfmj);
        sfwf = (TextView) findViewById(R.id.jbxx_sfwf);
        gdtian = (TextView) findViewById(R.id.jbxx_gdtian);
        gdtu = (TextView) findViewById(R.id.jbxx_gdtu);
        gdldm = (TextView) findViewById(R.id.jbxx_gdldm);
        gdhjm = (TextView) findViewById(R.id.jbxx_gdhjm);
        sfts = (TextView) findViewById(R.id.jbxx_sfts);
        sftd = (TextView) findViewById(R.id.jbxx_sftd);
        sftl = (TextView) findViewById(R.id.jbxx_sftl);
        sftx = (TextView) findViewById(R.id.jbxx_sftx);
        sftds = (TextView) findViewById(R.id.jbxx_sftds);
        sftkd = (TextView) findViewById(R.id.jbxx_sftkd);
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
