package cn.deepai.evillage.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.event.ResponseHeaderEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.manager.SettingManager;
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
                PkhListEvent event = gson.fromJson(response.body().string(), PkhListEvent.class);
                EventBus.getDefault().post(event);
                if (RspCode.RSP_CODE_SUCCESS.equals(event.rspHeader.getRspCode())) {
                    CacheManager.getInstance().cacheData(EVRequest.ACTION_GET_PKHLIST, gson.toJson(event.data));
                }
            }
        });
    }
}
