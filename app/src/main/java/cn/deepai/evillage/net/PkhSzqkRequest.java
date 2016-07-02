package cn.deepai.evillage.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 贫困户收支情况详情
 */
public class PkhSzqkRequest extends EVRequest {

    public static void request(String id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhSzqkJbxx);

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHSZQKJBXX,
                requestGson.toJson(header),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhszqkBean responseEvent = requestGson.fromJson(dataJsonString, PkhszqkBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }
}
