package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.event.PkhxqEvent;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 贫困户基本信息
 */
public class PkhJbxxPage extends PkhBasePage{

    private boolean mHasData = false;
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
                "\t\t\"isMustUpdate\": \"\",\n" +
                "\t\t\"isUpdate\": \"0\",\n" +
                "\t\t\"tokenId\": \"989ae59688094d6b8803744e6f3d4588\",\n" +
                "\t\t\"updateContent\": \"\",\n" +
                "\t\t\"updateUrl\": \"\",\n" +
                "\t\t\"userId\": \"1\",\n" +
                "\t\t\"versionName\": \"\"\n" +
                "\t},\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        PkhxqEvent event = gson.fromJson(str, PkhxqEvent.class);
        EventBus.getDefault().post(event);
    }

    @Override
    public boolean hasData() {
        return mHasData;
    }

    @Override
    public void bindData(Object dataJson) {
//        Gson gson = new Gson();
//        PkhjbxxBean pkhjbxxBean = gson.fromJson(dataJson, PkhjbxxBean.class);
        bindData((PkhjbxxBean) dataJson);
    }

    public void bindData(PkhjbxxBean pkhjbxxBean) {
        hzxm.setText(pkhjbxxBean.getHzxm());
        jzdz.setText(pkhjbxxBean.getJzdz());
        lxdh.setText(pkhjbxxBean.getLxdh());
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        yxzh.setText(pkhjbxxBean.getYxzh());
        jhsyh.setText(pkhjbxxBean.getJhsyh());
        pkhsx.setText(pkhjbxxBean.getPkhsx());
        mHasData = true;
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }

    private void initView() {
        hzxm	 = (EditText) findViewById(R.id.jbxx_hzxx);
        jzdz	 = (EditText) findViewById(R.id.jbxx_jzdz);
        lxdh	 = (EditText) findViewById(R.id.jbxx_lxdh);
        hzsfz	 = (EditText) findViewById(R.id.jbxx_sfzh);
        hkhyx	 = (EditText) findViewById(R.id.jbxx_khyh);
        yxzh	 = (EditText) findViewById(R.id.jbxx_yhzh);
        jhsyh	 = (EditText) findViewById(R.id.jbxx_jhsyh);
        pkhsx	 = (EditText) findViewById(R.id.jbxx_pkzt);
        mHasData = false;
    }
}
