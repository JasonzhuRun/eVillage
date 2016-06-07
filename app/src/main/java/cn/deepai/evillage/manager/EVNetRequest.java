package cn.deepai.evillage.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.model.RequestHeader;
import cn.deepai.evillage.utils.LogUtil;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author GaoYixuan
 */
public class EVNetRequest {

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
