package cn.deepai.evillage.request;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.LoginRequestBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.MD5Util;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户列表
 */
public class PkhJbxxListRequest extends EVNetRequest{

    public static void request(int userId) {

        String str = "{\n" +
                "    \"data\": [\n" +
                "            {\n" +
                "                \"hid\":43,\n" +
                "                \"hzxm\":\"张三\",\n" +
                "                \"jdnf\":2015,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村7号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"17012332312\",\n" +
                "                \"jlsj\":20150803121212\n" +
                "            },\n" +
                "            {\n" +
                "                \"hid\":44,\n" +
                "                \"hzxm\":\"李四\",\n" +
                "                \"jdnf\":2014,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村5号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"18012332322\",\n" +
                "                \"jlsj\":20150803121212\n" +
                "            }\n" +
                "        ],\n" +
                "    \"rspHeader\": {\n" +
                "        \"reqCode\": \"zyfp01001\",\n" +
                "        \"rspCode\": \"0000\",\n" +
                "        \"rspDesc\": \"请求成功\",\n" +
                "        \"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "    }\n" +
                "}";
        Gson gson = new Gson();
        PkhListEvent event = gson.fromJson(str, PkhListEvent.class);

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("userId", userId);
//        }catch (JSONException e) {
//            return;
//        }
//
//        RequestHeaderBean header = new RequestHeaderBean();
//        header.setReqCode(EVApplication.getApplication().getString(R.string.req_code_getPkhJbxxList));
//        String token = SettingManager.getInstance().getToken();
//        header.setTokenId(token);
//
//        final Gson gson = new Gson();
//        EVNetRequest.request(EVNetRequest.ACTION_GET_PKHLIST, gson.toJson(header), jsonObject.toString(), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                PkhListEvent event = gson.fromJson(response.body().string(), PkhListEvent.class);
//                EventBus.getDefault().post(event);
//            }
//        });
    }
}
