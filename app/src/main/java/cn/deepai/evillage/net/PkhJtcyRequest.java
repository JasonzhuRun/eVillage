package cn.deepai.evillage.net;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import de.greenrobot.event.EventBus;

/**
 * 家庭成员详情
 */
public class PkhJtcyRequest extends EVRequest {

    public static void request(String id) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhJtcyJbxx);

        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_JTCYJBXX,
                requestGson.toJson(header),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjtcyBean responseEvent = requestGson.fromJson(dataJsonString, PkhjtcyBean.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }
}
