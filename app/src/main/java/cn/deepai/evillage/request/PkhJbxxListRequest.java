package cn.deepai.evillage.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.PkhListEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户列表
 */
public class PkhJbxxListRequest extends EVRequest {

    public static void request(int userId) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", userId);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhJbxxList);

        final Gson gson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHLIST, gson.toJson(header), jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                PkhListEvent event = new PkhListEvent();
                event.rspHeader = new ResponseHeaderEvent();
                event.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHLIST);
                Type type = new TypeToken<List<PkhjbxxBean>>(){}.getType();
                event.data = gson.fromJson(cache,type);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                PkhListEvent event;
                try {
                    event = gson.fromJson(body, PkhListEvent.class);
                    EventBus.getDefault().post(event);
                    if (RspCode.RSP_CODE_SUCCESS.equals(event.rspHeader.getRspCode())) {
                        CacheManager.getInstance().cacheData(EVRequest.ACTION_GET_PKHLIST, gson.toJson(event.data));
                    }
                } catch (JsonSyntaxException e) {
                    event = new PkhListEvent();
                    try {
                        JSONObject object = new JSONObject(body);
                        String rspStr =object.getString("rspHeader");
                        event.rspHeader = gson.fromJson(rspStr,ResponseHeaderEvent.class);
                    } catch (JSONException jsonException) {
                        event.rspHeader = new ResponseHeaderEvent();
                        event.rspHeader.setRspCode(RspCode.RSP_CODE_OTHER);
                        event.rspHeader.setRspDesc("未知的服务器异常");
                    }
                    EventBus.getDefault().post(event);
                }
            }
        });
    }
}
