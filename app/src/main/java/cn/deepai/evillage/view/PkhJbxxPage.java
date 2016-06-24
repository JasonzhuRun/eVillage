package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.request.EVNetRequest;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户基本信息
 */
public class PkhJbxxPage extends PkhBasePage{

    // 户主姓名
    private	EditText	hzxm;
    // 居住地址
    private	EditText	jzdz;
    // 联系电话
    private	EditText	lxdh;
    // 户主身份证
    private	EditText	hzsfz;
    // 户开户银行
    private	EditText	hkhyx;
    // 银行账号
    private	EditText	yxzh;
    // 贫困识别标准
    private EditText    sbbz;
    // 计划生育户
    private	EditText	jhsyh;
    // 贫困户属性
    private	EditText	pkhsx;
    // 贫困户状态
    private	EditText	pkhzt;
    // 建档年份
    private	EditText	jdnf;
    // 脱贫年份
    private	EditText	tpnf;

    public PkhJbxxPage(Context context) {
        this(context,null);
    }
    public PkhJbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjbxx, this);
        initView();

    }

    @Override
    public void requestData() {

        String str = "{\n" +
                "\t\"data\": {\n" +
                "\t\t\"hzxm\": \"张三2\",\n" +
                "\t\t\"jzdz\": \"遵义\",\n" +
                "        \"vid\": null,\n" +
                "        \"lxdh\": \"1888888883\",\n" +
                "        \"hzsfz\": \"55521525351535\",\n" +
                "        \"hkhyx\": null,\n" +
                "        \"yxzh\": null,\n" +
                "        \"pksbbz\": \"G\",\n" +
                "        \"jhsyh\": 0,\n" +
                "        \"pkhsx\": \"2\",\n" +
                "        \"pkhzt\": null,\n" +
                "        \"jdnf\": 2015,\n" +
                "        \"tpnf\": 0,\n" +
                "        \"jlsj\": null,\n" +
                "        \"jlr\": null,\n" +
                "\t\t\"bz\": null,\n" +
                "\t\t\"zt\": null\n" +
                "\t},\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<PkhjbxxBean>>(){}.getType();
        PkhxqBean<PkhjbxxBean> pkhxqBean = gson.fromJson(str, type);
        bindData(pkhxqBean.data);
        EventBus.getDefault().post(pkhxqBean.rspHeader);
    //todo///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        int hid = SettingManager.getInstance().getCurrentHid();
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("hid", hid);
//        }catch (JSONException e) {
//            return;
//        }
//
//        RequestHeaderBean header = new RequestHeaderBean();
//        header.setReqCode(EVApplication.getApplication().getString(R.string.req_code_getPkhJbxx));
//        String token = SettingManager.getInstance().getToken();
//        header.setTokenId(token);
//
//        final Gson requestGson = new Gson();
//        EVNetRequest.request(EVNetRequest.ACTION_PKHJBXX, requestGson.toJson(header), jsonObject.toString(), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Type type = new TypeToken<PkhxqBean<PkhjbxxBean>>(){}.getType();
//                PkhxqBean<PkhjbxxBean> pkhxqBean = requestGson.fromJson(response.body().string(), type);
//                bindData(pkhxqBean.data);
//                EventBus.getDefault().post(pkhxqBean.rspHeader);
//            }
//        });
    }



    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }

    private void bindData(PkhjbxxBean pkhjbxxBean) {
        hzxm.setText(pkhjbxxBean.getHzxm());
        jzdz.setText(pkhjbxxBean.getJzdz());
        lxdh.setText(pkhjbxxBean.getLxdh());
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        yxzh.setText(pkhjbxxBean.getYxzh());
        jhsyh.setText(String.valueOf(pkhjbxxBean.getJhsyh()));
        pkhzt.setText(pkhjbxxBean.getPkhzt());
        mHasData = true;
    }

    private void initView() {
        hzxm	 = (EditText) findViewById(R.id.jbxx_hzxx);
        jzdz	 = (EditText) findViewById(R.id.jbxx_jzdz);
        lxdh	 = (EditText) findViewById(R.id.jbxx_lxdh);
        hzsfz	 = (EditText) findViewById(R.id.jbxx_sfzh);
        hkhyx	 = (EditText) findViewById(R.id.jbxx_khyh);
        yxzh	 = (EditText) findViewById(R.id.jbxx_yhzh);
        jhsyh	 = (EditText) findViewById(R.id.jbxx_jhsyh);
        pkhzt	 = (EditText) findViewById(R.id.jbxx_pkzt);
        mHasData = false;
    }
}
