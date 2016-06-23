package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.PkhzfqkBean;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class PkhZfqkPage extends PkhBasePage{

    // 住房面积
    private	EditText	zfmj;
    // 主要结构
    private	EditText	fwzyjg;
    // 建房时间
    private	EditText	jfsj;
    // 是否危房
    private	EditText	zyzfsfwf;
    // 异地搬迁扶贫情况
    private	EditText	ydfpbqqk;


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
    public void requestData() {

        String str = "{\n" +
                "\t\"data\":{\n" +
                "        \"id\":43,\n" +
                "        \"tjnd\":2015,\n" +
                "        \"zfmj\":10.5,\n" +
                "        \"fwzyjg\":\"土坯\",\n" +
                "        \"jfsj\":20050803,\n" +
                "        \"zyzfsfwf\":1,\n" +
                "        \"ydfpbqqk\":\"\",\n" +
                "        \"jlsj\":2015080312121,\n" +
                "        \"jlr\":\"管理员\",\n" +
                "        \"by\":\"备注内容\"\n" +
                "    },\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<PkhzfqkBean>>(){}.getType();
        PkhxqBean<PkhzfqkBean> pkhxqBean = gson.fromJson(str, type);
        bindData(pkhxqBean.data);
        EventBus.getDefault().post(pkhxqBean.rspHeader);
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
        zfmj	 = (EditText)findViewById(R.id.zfqk_zfmj);
        fwzyjg	 = (EditText)findViewById(R.id.zfqk_fwjg);
        jfsj	 = (EditText)findViewById(R.id.zfqk_jfsj);
        zyzfsfwf	 = (EditText)findViewById(R.id.zfqk_sfwf);
        ydfpbqqk	 = (EditText)findViewById(R.id.zfqk_ydfpbqqk);
        mHasData = false;
    }
}
