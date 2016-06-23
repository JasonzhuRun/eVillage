package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhcyhqkBean;
import cn.deepai.evillage.bean.PkhsctjBean;
import cn.deepai.evillage.bean.PkhxqBean;
import de.greenrobot.event.EventBus;

/**
 * 参与产业化组织情况
 */
public class PkhCyhPage extends PkhBasePage{

    private EditText    tjnd;
    private	EditText	cylx;
    private	EditText	cyzzlx;
    private	EditText	cyzsy;
    private	EditText	cjfphzzjzz;
    private	EditText	trhzzzje;
    private	EditText	cynyhzzz;
    private	EditText	ltqyddsy;

    public PkhCyhPage(Context context) {
        this(context,null);
    }
    public PkhCyhPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhCyhPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhcyhqk, this);
        initView();
    }

    @Override
    public void requestData() {
        String str = "{\n" +
                "\t\"data\":{\n" +
                "\t\t\"id\":43,\n" +
                "\t\t\"tjnd\":2015,\n" +
                "\t\t\"cylx\":\"类型1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"cyzsy\":20000,\n" +
                "\t\t\"cjfphzzjzz\":1,\n" +
                "\t\t\"trhzzjje\":43,\n" +
                "\t\t\"cynyhzzz\":1,\n" +
                "\t\t\"ltqyddsy\":1000,\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"bz\":\"备注内容\"\n" +
                "    },\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<PkhcyhqkBean>>(){}.getType();
        PkhxqBean<PkhcyhqkBean> pkhxqBean = gson.fromJson(str, type);
        bindData(pkhxqBean.data);
        EventBus.getDefault().post(pkhxqBean.rspHeader);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_cyh);
    }

    private void bindData(PkhcyhqkBean pkhcyhqkBean) {
        tjnd.setText(String.valueOf(pkhcyhqkBean.getTjnd()));
        cylx.setText(String.valueOf(pkhcyhqkBean.getCylx()));
        cyzzlx.setText(String.valueOf(pkhcyhqkBean.getCyzzlx()));
        cyzsy.setText(String.valueOf(pkhcyhqkBean.getCyzsy()));
        cjfphzzjzz.setText(String.valueOf(pkhcyhqkBean.getCjfphzzjzz()));
        trhzzzje.setText(String.valueOf(pkhcyhqkBean.getTrhzzzje()));
        cynyhzzz.setText(String.valueOf(pkhcyhqkBean.getCynyhzzz()));
        ltqyddsy.setText(String.valueOf(pkhcyhqkBean.getLtqyddsy()));
        mHasData = true;
    }

    private void initView() {

        tjnd	 = (EditText)findViewById(R.id.cyhqk_tjnd);
        cylx	 = (EditText)findViewById(R.id.cyhqk_cylx);
        cyzzlx	 = (EditText)findViewById(R.id.cyhqk_cyzzlx);
        cyzsy	 = (EditText)findViewById(R.id.cyhqk_cyzsy);
        cjfphzzjzz	 = (EditText)findViewById(R.id.cyhqk_cjfpzz);
        trhzzzje	 = (EditText)findViewById(R.id.cyhqk_hzzj);
        cynyhzzz	 = (EditText)findViewById(R.id.cyhqk_hzzz);
        ltqyddsy	 = (EditText)findViewById(R.id.cyhqk_ltqy);
        mHasData = false;
    }
}
