package cn.deepai.evillage.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhzfqkBean;
import cn.deepai.evillage.event.PkhxqEvent;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.PkhListEvent;
import cn.deepai.evillage.event.ResponseHeaderEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.CacheManager;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 家庭成员详情
 */
public class PkhJtcyRequest extends EVRequest {

    public static void request(int id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("jtcyxxId", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhJtcyJbxx);

        final Gson requestGson = new Gson();
        EVRequest.request(EVRequest.ACTION_GET_JTCYJBXX, requestGson.toJson(header), jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                PkhxqEvent<PkhjtcyBean> pkhxqEvent = new PkhxqEvent<>();
                String cache = CacheManager.getInstance().getCacheData(EVRequest.ACTION_GET_JTCYJBXX);
                pkhxqEvent.data = requestGson.fromJson(cache, PkhjtcyBean.class);
                pkhxqEvent.rspHeader = new ResponseHeaderEvent();
                pkhxqEvent.rspHeader.setRspCode(RspCode.RSP_CODE_NO_CONNECTION);
                EventBus.getDefault().post(pkhxqEvent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Type type = new TypeToken<PkhxqEvent<PkhjtcyBean>>(){}.getType();
                PkhxqEvent<PkhjtcyBean> pkhxqEvent = requestGson.fromJson(response.body().string(), type);
                EventBus.getDefault().post(pkhxqEvent);
                if (RspCode.RSP_CODE_SUCCESS.equals(pkhxqEvent.rspHeader.getRspCode())) {
                    CacheManager.getInstance().cacheData(
                            EVRequest.ACTION_GET_JTCYJBXX,requestGson.toJson(pkhxqEvent.data));
                }
            }
        });
    }
}
