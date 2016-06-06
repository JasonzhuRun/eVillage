package cn.deepai.evillage.model;

import cn.deepai.evillage.EVApplication;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author GaoYixuan
 */
public abstract class EVNetRequest {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String mURL = "http://"+
            EVApplication.getHost()+"/zy_fp/api/{model}/{version}/interface";
    private static OkHttpClient client = new OkHttpClient();

    public void request(String action,String jsonData,Callback callback) {
        //todo  将请求头也放在这里封装
        RequestBody body = RequestBody.create(MEDIA_TYPE, jsonData);

        Request request = new Request.Builder()
                .url(mURL+action)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
