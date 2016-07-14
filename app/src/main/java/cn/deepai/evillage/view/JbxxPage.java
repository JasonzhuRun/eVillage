package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * 贫困户基本信息
 */
public class JbxxPage extends BasePage {

    // 户主姓名
    private EditText hzxm;
    // 居住地址
    private EditText jzdz;
    // 联系电话
    private EditText lxdh;
    // 户主身份证
    private EditText hzsfz;
    // 户开户银行
    private EditText hkhyx;
    // 银行账号
    private EditText yxzh;
    // 贫困识别标准
    private EditText sbbz;
    // 计划生育户
    private EditText jhsyh;
    // 贫困户属性
    private EditText pkhsx;
    // 贫困户状态
    private EditText pkhzt;
    // 脱贫年份
    private EditText tpnf;

    public JbxxPage(Context context) {
        this(context, null);
    }

    public JbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjbxx, this);
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
    public void onEventMainThread(PkhjbxxBean event) {
        if (isSelected()) {
            bindData(event);
        }
    }

    @Override
    public void requestData() {

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHJBXX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJbxx)),
                requestGson.toJson(new PkhRequestBean()),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjbxxBean pkhjbxxBean = requestGson.fromJson(dataJsonString, PkhjbxxBean.class);
                        EventBus.getDefault().post(pkhjbxxBean);
                    }
                });
    }


    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }

    private void bindData(PkhjbxxBean pkhjbxxBean) {
        hzxm.setText(pkhjbxxBean.getHzxm());
        // 地址
        jzdz.setText(pkhjbxxBean.getJzdz());
        // 电话
        lxdh.setText(pkhjbxxBean.getLxdh());
        // 身份证
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        // 开户行
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        // 银行账号
        yxzh.setText(pkhjbxxBean.getYxzh());
        // 计划生育户
        jhsyh.setText(DictionaryUtil.getValueName(pkhjbxxBean.getJhsyh()));
        // 贫困户状态
        pkhzt.setText(pkhjbxxBean.getPkhzt());
        sbbz.setText(DictionaryUtil.getValueName("PKBZ", pkhjbxxBean.getPksbbz()));
        tpnf.setText(pkhjbxxBean.getTpnf());
        pkhsx.setText(DictionaryUtil.getValueName("PKHSX", pkhjbxxBean.getPkhsx()));
        mHasData = true;
    }

    private void initView() {
        hzxm = (EditText) findViewById(R.id.jbxx_hzxx);
        jzdz = (EditText) findViewById(R.id.jbxx_jzdz);
        lxdh = (EditText) findViewById(R.id.jbxx_lxdh);
        hzsfz = (EditText) findViewById(R.id.jbxx_sfzh);
        hkhyx = (EditText) findViewById(R.id.jbxx_khyh);
        yxzh = (EditText) findViewById(R.id.jbxx_yhzh);
        jhsyh = (EditText) findViewById(R.id.jbxx_jhsyh);
        pkhzt = (EditText) findViewById(R.id.jbxx_pkhzt);
        sbbz = (EditText) findViewById(R.id.jbxx_sbbz);
        tpnf = (EditText) findViewById(R.id.jbxx_tpnf);
        pkhsx = (EditText) findViewById(R.id.jbxx_pkhsx);
        mHasData = false;
    }
}
