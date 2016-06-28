package cn.deepai.evillage.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
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
                ResponseEvent<PkhszqkBean> responseEvent = new ResponseEvent<>();
                String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_PKHSZQKJBXX);
                responseEvent.data = requestGson.fromJson(cache, PkhszqkBean.class);
                responseEvent.rspHeader = new ResponseHeaderEvent();
                responseEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                EventBus.getDefault().post(responseEvent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<ResponseEvent<PkhszqkBean>>(){}.getType();
                ResponseEvent<PkhszqkBean> responseEvent = requestGson.fromJson(response.body().string(), type);
                EventBus.getDefault().post(responseEvent);
                if (RspCode.RSP_CODE_SUCCESS.equals(responseEvent.rspHeader.getRspCode())) {
                    CacheManager.getInstance().cacheData(
                            EVRequest.ACTION_GET_PKHSZQKJBXX,requestGson.toJson(responseEvent.data));
                }
            }
        });
    }
}
