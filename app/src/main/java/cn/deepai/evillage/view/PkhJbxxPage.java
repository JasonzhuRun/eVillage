package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.PkhjbxxBean;

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

    public void bindData(PkhjbxxBean pkhjbxxBean) {
        hzxm.setText(pkhjbxxBean.getHzxm());
        jzdz.setText(pkhjbxxBean.getJzdz());
        lxdh.setText(pkhjbxxBean.getLxdh());
        hzsfz.setText(pkhjbxxBean.getHzsfz());
        hkhyx.setText(pkhjbxxBean.getHkhyx());
        yxzh.setText(pkhjbxxBean.getYxzh());
        jhsyh.setText(pkhjbxxBean.getJhsyh());
        pkhsx.setText(pkhjbxxBean.getPkhsx());
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
    }
}
