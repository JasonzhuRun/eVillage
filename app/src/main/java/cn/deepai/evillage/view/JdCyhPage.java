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
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 参与产业化组织情况
 */
public class JdCyhPage extends PkhBasePage {

    private EditText tjnd;
    private EditText cylx;
    private EditText cyzzlx;
    private EditText cyzsy;
    private EditText cjfphzzjzz;
    private EditText trhzzzje;
    private EditText cynyhzzz;
    private EditText ltqyddsy;

    public JdCyhPage(Context context) {
        this(context, null);
    }

    public JdCyhPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdCyhPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhcyhqk, this);
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
    public void onEventMainThread(PkhcyhqkBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHCYHZZJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhCyhzzJbxx)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhcyhqkBean responseEvent = requestGson.fromJson(dataJsonString, PkhcyhqkBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_cyh);
    }

    private void bindData(PkhcyhqkBean pkhcyhqkBean) {
        tjnd.setText(pkhcyhqkBean.getTjnd());
        cylx.setText(DictionaryUtil.getValueName("ZDCYLX",pkhcyhqkBean.getCylx()));
        cyzzlx.setText(DictionaryUtil.getValueName("CYZZLX",pkhcyhqkBean.getCyzzlx()));
        cyzsy.setText(pkhcyhqkBean.getCyzsy());
        cjfphzzjzz.setText(DictionaryUtil.getValueName(pkhcyhqkBean.getCjfphzzjzz()));
        trhzzzje.setText(pkhcyhqkBean.getTrhzzzje());
        cynyhzzz.setText(DictionaryUtil.getValueName(pkhcyhqkBean.getCynyhzzz()));
        ltqyddsy.setText(pkhcyhqkBean.getLtqyddsy());
        mHasData = true;
    }

    private void initView() {

        tjnd = (EditText) findViewById(R.id.cyhqk_tjnd);
        cylx = (EditText) findViewById(R.id.cyhqk_cylx);
        cyzzlx = (EditText) findViewById(R.id.cyhqk_cyzzlx);
        cyzsy = (EditText) findViewById(R.id.cyhqk_cyzsy);
        cjfphzzjzz = (EditText) findViewById(R.id.cyhqk_cjfpzz);
        trhzzzje = (EditText) findViewById(R.id.cyhqk_hzzj);
        cynyhzzz = (EditText) findViewById(R.id.cyhqk_hzzz);
        ltqyddsy = (EditText) findViewById(R.id.cyhqk_ltqy);
        mHasData = false;
    }
}
