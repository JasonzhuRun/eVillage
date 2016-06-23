package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhshtjBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.PkhzfqkBean;
import de.greenrobot.event.EventBus;

/**
 * 生活条件
 */
public class PkhShtjPage extends PkhBasePage{

    private EditText    tjnd;
    private	EditText	tshyd;
    private	EditText	zyrllx;
    private	EditText	ysqk;
    private	EditText	hqyysdzykn;
    private	EditText	cslx;
    private	EditText	nyxfpqk;
    private	EditText	yskn;
    private	EditText	ysaq;
    private	EditText	jlczgl;
    private	EditText	rullx;
    private	EditText	wscs;
    private	EditText	tgbds;

    public PkhShtjPage(Context context) {
        this(context,null);
    }
    public PkhShtjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhShtjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhshtj, this);
        initView();
    }

    @Override
    public void requestData() {
        String str = "{\n" +
                "\t\"data\":{\n" +
                "\t\t\t\"id\":43,\n" +
                "            \"tjnd\":2015,\n" +
                "            \"tshyd \":1,\n" +
                "            \"zyrllx\":\"木柴，煤\",\n" +
                "            \"ysqk\":\"不方便\",\n" +
                "            \"hqyysdzykn\":\"劳动力不足\",\n" +
                "            \"cslx\":\"露天\",\n" +
                "            \"nyxfpqk\":\"收音机，手电\",\n" +
                "            \"yskn\":1,\n" +
                "            \"ysaq\":0,\n" +
                "            \"jlczgl\":10.1,\n" +
                "            \"rullx\":\"土路\",\n" +
                "            \"wscs\":0,\n" +
                "            \"tgbds\":0,\n" +
                "            \"jlsj\":2015080312121,\n" +
                "            \"jlr\":\"管理员\",\n" +
                "            \"by\":\"备注内容\"\n" +
                "\t},\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<PkhshtjBean>>(){}.getType();
        PkhxqBean<PkhshtjBean> pkhxqBean = gson.fromJson(str, type);
        bindData(pkhxqBean.data);
        EventBus.getDefault().post(pkhxqBean.rspHeader);
    }

    @Override
    public boolean hasData() {
        return false;
    }

    private void bindData(PkhshtjBean pkhshtjBean) {
        tjnd.setText(String.valueOf(pkhshtjBean.getTjnd()));
        tshyd.setText(String.valueOf(pkhshtjBean.getTshyd()));
        zyrllx.setText(String.valueOf(pkhshtjBean.getZyrllx()));
        ysqk.setText(String.valueOf(pkhshtjBean.getYsqk()));
        hqyysdzykn.setText(String.valueOf(pkhshtjBean.getHqyysdzykn()));
        cslx.setText(String.valueOf(pkhshtjBean.getCslx()));
        nyxfpqk.setText(String.valueOf(pkhshtjBean.getNyxfpqk()));
        yskn.setText(String.valueOf(pkhshtjBean.getYskn()));
        ysaq.setText(String.valueOf(pkhshtjBean.getYsaq()));
        jlczgl.setText(String.valueOf(pkhshtjBean.getJlczgl()));
        rullx.setText(String.valueOf(pkhshtjBean.getRullx()));
        wscs.setText(String.valueOf(pkhshtjBean.getWscs()));
        tgbds.setText(String.valueOf(pkhshtjBean.getTgbds()));
        mHasData = true;
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_shtj);
    }

    private void initView() {
        tjnd	 = (EditText)findViewById(R.id.shtj_tjnd);
        tshyd	 = (EditText)findViewById(R.id.shtj_shyd);
        zyrllx	 = (EditText)findViewById(R.id.shtj_zyrl);
        ysqk	 = (EditText)findViewById(R.id.shtj_ysqk);
        hqyysdzykn	 = (EditText)findViewById(R.id.shtj_hqyskn);
        cslx	 = (EditText)findViewById(R.id.shtj_cslx);
        nyxfpqk	 = (EditText)findViewById(R.id.shtj_nyxfp);
        yskn	 = (EditText)findViewById(R.id.shtj_yskn);
        ysaq	 = (EditText)findViewById(R.id.shtj_ysaq);
        jlczgl	 = (EditText)findViewById(R.id.shtj_jlzgl);
        rullx	 = (EditText)findViewById(R.id.shtj_rhllx);
        wscs	 = (EditText)findViewById(R.id.shtj_wscs);
        tgbds	 = (EditText)findViewById(R.id.shtj_gbds);
        mHasData = false;
    }
}
