package cn.deepai.evillage.request;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.utils.LogUtil;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author GaoYixuan
 */
public class EVNetRequest {

    public static final String ACTION_LOGIN_WITH_PASSWORD = "termLogin.action";
    public static final String ACTION_LOGIN_WITH_TOKEN = "termLogin.action";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String mURL = "http://10.6.128.10:8080/zyfp/inter/termLogin.action";
    private static OkHttpClient client = new OkHttpClient();

    public static void request(String action,String jsonHeader,String jsonData,Callback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("reqHeader",jsonHeader);
            jsonObject.put("data",jsonData);
        }catch (JSONException e) {
            LogUtil.e(EVNetRequest.class,"Illegal json format:"+e.toString());
            return;
        }
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE,jsonObject.toString());

        Request request = new Request.Builder()
                .url(mURL+action)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
