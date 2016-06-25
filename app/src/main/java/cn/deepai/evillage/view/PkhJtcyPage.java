package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtcyRecyclerAdapter;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.request.EVRequest;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/**
 * @author GaoYixuan
 */
public class PkhJtcyPage extends PkhBasePage{

    private PkhjtcyRecyclerAdapter mPkhjtcyRecyclerAdapter;

    public PkhJtcyPage(Context context) {
        this(context,null);
    }
    public PkhJtcyPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtcyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtcy, this);
        initView();
    }

    @Override
    public void requestData() {
        String str = "{\n" +
                "\t\"data\":[\n" +
                "        {\n" +
                "            \"id\":43,\n" +
                "            \"xm\":\"张三\",\n" +
                "            \"xb\":\"F\",\n" +
                "            \"sfzhm\":\"110233199908091231\",\n" +
                "            \"yhzgx\":\"1\",\n" +
                "            \"jlsj\":20150803121212\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":44,\n" +
                "            \"xm\":\"张小三\",\n" +
                "            \"xb\":\"F\",\n" +
                "            \"sfzhm\":\"110233200908091231\",\n" +
                "            \"yhzgx\":\"3\",\n" +
                "            \"jlsj\":20150803121212\n" +
                "        }\n" +
                "    ],\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<List<PkhjtcyBean>>>(){}.getType();
        PkhxqBean<List<PkhjtcyBean>> pkhxqBean = gson.fromJson(str, type);
        mPkhjtcyRecyclerAdapter.notifyResult(true,pkhxqBean.data);
        mHasData = true;
        EventBus.getDefault().post(pkhxqBean.rspHeader);
        //todo///////////////////////////////////////////////////////////////////////////////////////////////////////////
        int hid = SettingManager.getInstance().getCurrentHid();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hid", hid);
        }catch (JSONException e) {
            return;
        }

        RequestHeaderBean header = new RequestHeaderBean();
        header.setReqCode(EVApplication.getApplication().getString(R.string.req_code_getPkhJtcyxxList));
        String token = SettingManager.getInstance().getToken();
        header.setTokenId(token);

        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHJTCYLIST, requestGson.toJson(header), jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<PkhxqBean<PkhjbxxBean>>(){}.getType();
                PkhxqBean<PkhjbxxBean> pkhxqBean = requestGson.fromJson(response.body().string(), type);
                EventBus.getDefault().post(pkhxqBean.rspHeader);
            }
        });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtcy);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtcy);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtcyRecyclerAdapter = new PkhjtcyRecyclerAdapter();
        recyclerView.setAdapter(mPkhjtcyRecyclerAdapter);
        mHasData = false;
    }
}
