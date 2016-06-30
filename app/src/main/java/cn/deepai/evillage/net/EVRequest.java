package cn.deepai.evillage.net;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EVRequest {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
//    private static final String URL = "http://192.168.101.18:8080/zyfp-web/inter/termLogin!";
//    private static final String URL = "http://192.168.212.61:8888/zyfp-web/inter/termLogin!";
    private static final String URL = "http://124.65.186.26:8973/zyfp-web/inter/termLogin!";
    private static OkHttpClient client = new OkHttpClient();

    public static void request(Action action, String jsonHeader, String jsonData, final ResponseCallback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("reqHeader", jsonHeader);
            jsonObject.put("data", jsonData);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        request(action,jsonObject.toString(),callback);
    }

    public static void request(Action action, String jsonString, final ResponseCallback callback) {
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, jsonString);

        final Request request = new Request.Builder()
                .url(URL + action.getName())
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e(EVRequest.class,e.toString());
                ResponseHeaderEvent headerEvent = new ResponseHeaderEvent();
                headerEvent.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                headerEvent.setRspDesc(RspCode.RSP_CODE_NO_CONNECTION_DSC);
                EventBus.getDefault().post(headerEvent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rspString = response.body().string();
                String headString = null;
                String dataString = null;
                try {
                    JSONObject jsonObject = new JSONObject(rspString);
                    headString = jsonObject.getString("rspHeader");
                    dataString = jsonObject.getString("data");
                } catch (JSONException e) {
                    LogUtil.e(EVRequest.class,e.toString());
                } finally {
                    Gson gson = new Gson();
                    ResponseHeaderEvent headerEvent = gson.fromJson(headString,ResponseHeaderEvent.class);
                    EventBus.getDefault().post(headerEvent);
                    if (!TextUtils.isEmpty(dataString)) {
                        callback.onDataResponse(dataString);
                    }
                }
            }
        });
    }
}
