package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.HidBean;
import cn.deepai.evillage.model.bean.PkhzfqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class PkhZfqkPage extends PkhBasePage {

    // 住房面积
    private EditText zfmj;
    // 主要结构
    private EditText fwzyjg;
    // 建房时间
    private EditText jfsj;
    // 是否危房
    private EditText zyzfsfwf;
    // 异地搬迁扶贫情况
    private EditText ydfpbqqk;


    public PkhZfqkPage(Context context) {
        this(context, null);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhZfqkPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhzfqk, this);
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
    public void onEventMainThread(PkhzfqkBean event) {
        bindData(event);
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHZFQJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhZfqkJbxx)),
                requestGson.toJson(new HidBean()),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhzfqkBean responseEvent = requestGson.fromJson(dataJsonString, PkhzfqkBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_zfqk);
    }

    private void bindData(PkhzfqkBean pkhzfqkBean) {
        zfmj.setText(String.valueOf(pkhzfqkBean.getZfmj()));
        fwzyjg.setText(String.valueOf(pkhzfqkBean.getFwzyjg()));
        jfsj.setText(String.valueOf(pkhzfqkBean.getJfsj()));
        zyzfsfwf.setText(String.valueOf(pkhzfqkBean.getZyzfsfwf()));
        ydfpbqqk.setText(String.valueOf(pkhzfqkBean.getYdfpbqqk()));
        mHasData = true;
    }

    private void initView() {
        zfmj = (EditText) findViewById(R.id.zfqk_zfmj);
        fwzyjg = (EditText) findViewById(R.id.zfqk_fwjg);
        jfsj = (EditText) findViewById(R.id.zfqk_jfsj);
        zyzfsfwf = (EditText) findViewById(R.id.zfqk_sfwf);
        ydfpbqqk = (EditText) findViewById(R.id.zfqk_ydfpbqqk);
        mHasData = false;
    }
}
