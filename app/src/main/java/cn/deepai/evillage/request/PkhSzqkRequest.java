package cn.deepai.evillage.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhszqkBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.PkhxqEvent;
import cn.deepai.evillage.event.ResponseHeaderEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户收支情况详情
 */
public class PkhSzqkRequest extends EVRequest {

    public static void request(int id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhSzqkJbxx);

        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_PKHSZQKJBXX, requestGson.toJson(header), jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                PkhxqEvent<PkhszqkBean> pkhxqEvent = new PkhxqEvent<>();
                String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHSZQKJBXX);
                pkhxqEvent.data = requestGson.fromJson(cache, PkhszqkBean.class);
                pkhxqEvent.rspHeader = new ResponseHeaderEvent();
                pkhxqEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                EventBus.getDefault().post(pkhxqEvent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<PkhxqEvent<PkhszqkBean>>(){}.getType();
                PkhxqEvent<PkhszqkBean> pkhxqEvent = requestGson.fromJson(response.body().string(), type);
                EventBus.getDefault().post(pkhxqEvent);
                if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqEvent.rspHeader.getRspCode())) {
                    CacheManager.getInstance().cacheData(
                            EVRequest.ACTION_GET_PKHSZQKJBXX,requestGson.toJson(pkhxqEvent.data));
                }
            }
        });
    }
}
