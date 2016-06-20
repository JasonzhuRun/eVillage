package cn.deepai.evillage.request;

import cn.deepai.evillage.model.RequestSucceedEvent;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class PkhJbxxListRequest extends EVNetRequest{

    public static void request(int userId) {
        RequestSucceedEvent event = new RequestSucceedEvent();
        event.body = "[\n" +
                "            {\n" +
                "                \"hid\":43,\n" +
                "                \"hzxm\":\"张三\",\n" +
                "                \"jdnf\":2015,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村7号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"17012332312\",\n" +
                "\"jlsj\":20150803121212\n" +
                "            },\n" +
                "            {\n" +
                "                \"hid\":44,\n" +
                "                \"hzxm\":\"李四\",\n" +
                "                \"jdnf\":2014,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村5号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"18012332322\",\n" +
                "\"jlsj\":20150803121212\n" +
                "            }\n" +
                "        ]";
        EventBus.getDefault().post(event);
//        LoginDataBean loginData = new LoginDataBean();
//        loginData.setUserCode(name);
//        loginData.setPassword(MD5Util.getMD5(password));
//        loginData.setVersionCode("1");

//        RequestHeaderBean header = new RequestHeaderBean();
//        header.setReqCode("zyfp01001");
//        header.setReqTime((new Date()).toString());
//        header.setTokenId("0");
//
//        final Gson gson = new Gson();
//        EVNetRequest.request(EVNetRequest.ACTION_LOGIN_WITH_PASSWORD, gson.toJson(header), gson.toJson(""), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.body().string());
//                    String str1 = jsonObject.getString("rspHeader");
//                    String str2 = jsonObject.getString("data");
//
//                    ResponseHeaderBean responseHeader = gson.fromJson(str1, ResponseHeaderBean.class);
//                    LoginResultBean result = gson.fromJson(str2, LoginResultBean.class);
//                    LogUtil.v(LoginActivity.class,str1);
//                    LogUtil.v(LoginActivity.class,str2);
//
//                } catch (JSONException e) {
//
//                }
//
//            }
//        });
    }
}
