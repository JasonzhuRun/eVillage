package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhcyhqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账基本信息
 */
public class TzjbxxPage extends PkhBasePage {

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

    public TzjbxxPage(Context context) {
        this(context, null);
    }

    public TzjbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TzjbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_tzjbxx, this);
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

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzjbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
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

    private void bindData(TzjbxxBean tzjbxxBean) {

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

    private void initView() {

        nrjcsr = (EditText) findViewById(R.id.jbxx_nrjcsr);
        nsrhj = (EditText) findViewById(R.id.jbxx_nsrhj);
        nzchj = (EditText) findViewById(R.id.jbxx_nzchj);
        dkje = (EditText) findViewById(R.id.jbxx_dkje);
        zfjg = (EditText) findViewById(R.id.jbxx_zfjg);
        zfmj = (EditText) findViewById(R.id.jbxx_zfmj);
        sfwf = (EditText) findViewById(R.id.jbxx_sfwf);
        gdtian = (EditText) findViewById(R.id.jbxx_gdtian);
        gdtu = (EditText) findViewById(R.id.jbxx_gdtu);
        gdldm = (EditText) findViewById(R.id.jbxx_gdldm);
        gdhjm = (EditText) findViewById(R.id.jbxx_gdhjm);
        sfts = (EditText) findViewById(R.id.jbxx_sfts);
        sftd = (EditText) findViewById(R.id.jbxx_sftd);
        sftl = (EditText) findViewById(R.id.jbxx_sftl);
        sftx = (EditText) findViewById(R.id.jbxx_sftx);
        sftds = (EditText) findViewById(R.id.jbxx_sftds);
        sftkd = (EditText) findViewById(R.id.jbxx_sftkd);
        mHasData = false;
    }
}
