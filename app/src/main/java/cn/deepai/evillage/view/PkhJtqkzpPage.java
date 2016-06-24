package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.bean.LoginRequestBean;
import cn.deepai.evillage.bean.PkhcyhqkBean;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhjtqkzpBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.request.EVNetRequest;
import cn.deepai.evillage.request.LoginRequest;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.MD5Util;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 */
public class PkhJtqkzpPage extends PkhBasePage{

    private PkhjtqkzpRecyclerAdapter mPkhjtqkzpRecyclerAdapter;

    public PkhJtqkzpPage(Context context) {
        this(context, null);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtqkzp, this);
        initView();
    }

    @Override
    public void requestData() {
        String str = "{\n" +
                "\t\"data\":[\n" +
                "\t{\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img4q.duitang.com/uploads/item/201402/07/20140207211400_LctxW.jpeg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://p4.gexing.com/G1/M00/C7/EF/rBACFFO3lPyijm3OAACQJjM7w-o542.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img5.duitang.com/uploads/item/201412/01/20141201081607_XFssr.jpeg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://tupian.enterdesk.com/2013/lxy/07/22/3/g2.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://imgsrc.baidu.com/forum/pic/item/96e1d53f8794a4c215b1fbd60ef41bd5ac6e3921.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img4.duitang.com/uploads/item/201207/18/20120718131407_hBzFy.jpeg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://cdn.duitang.com/uploads/blog/201309/22/20130922144610_mVyyY.thumb.600_0.jpeg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://a.hiphotos.baidu.com/zhidao/pic/item/f603918fa0ec08fa39cce19c58ee3d6d55fbda39.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    },\n" +
                "    {\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"hid\":43,\n" +
                "\t\t\"zplx\":\"1\",\n" +
                "\t\t\"cyzzlx\":\"类型2\",\n" +
                "\t\t\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\t\t\"tpdz\":\"http://img6.faloo.com/Picture/680x580/0/927/927569.jpg\",\n" +
                "\t\t\"jlsj\":2015080312121,\n" +
                "\t\t\"jlr\":\"管理员\",\n" +
                "\t\t\"by\":\"备注内容\"\n" +
                "    }\n" +
                "    ],\n" +
                "\t\"rspHeader\": {\n" +
                "\t\t\"reqCode\": \"zyfp01001\",\n" +
                "\t\t\"rspCode\": \"0000\",\n" +
                "\t\t\"rspDesc\": \"请求成功\",\n" +
                "\t\t\"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "\t}\n" +
                "}";
//        LoginRequestBean loginRequestBean = new LoginRequestBean();
//        loginRequestBean.setUserCode(name);
//        loginRequestBean.setPassword(MD5Util.getMD5(password));
//        loginRequestBean.setVersionCode("1");
//
//        RequestHeaderBean header = new RequestHeaderBean();
//        header.setReqCode(EVApplication.getApplication().getString(R.string.req_code_login));
//        header.setReqTime((new Date()).toString());
//        header.setTokenId("0");
//
//        final Gson gson = new Gson();
//        EVNetRequest.request(EVNetRequest.ACTION_LOGIN, gson.toJson(header), gson.toJson(loginRequestBean), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                LoginEvent event = gson.fromJson(response.body().string(), LoginEvent.class);
//                LogUtil.v(LoginRequest.class,event.toString());
//                EventBus.getDefault().post(event);
//            }
//        });
        Gson gson = new Gson();
        Type type = new TypeToken<PkhxqBean<List<PkhjtqkzpBean>>>(){}.getType();
        PkhxqBean<List<PkhjtqkzpBean>> pkhxqBean = gson.fromJson(str, type);
        mPkhjtqkzpRecyclerAdapter.notifyResult(true,pkhxqBean.data);
        mHasData = true;
        EventBus.getDefault().post(pkhxqBean.rspHeader);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtqkzp);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtqkzpRecyclerAdapter = new PkhjtqkzpRecyclerAdapter();
        recyclerView.setAdapter(mPkhjtqkzpRecyclerAdapter);
    }
}
