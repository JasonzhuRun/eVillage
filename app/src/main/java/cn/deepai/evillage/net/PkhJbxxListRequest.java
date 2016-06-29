package cn.deepai.evillage.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import de.greenrobot.event.EventBus;

/**
 * 贫困户列表
 */
public class PkhJbxxListRequest extends EVRequest {

    public static void request(String userId) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", userId);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhJbxxList);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHLIST, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        Type type = new TypeToken<List<PkhjbxxBean>>(){}.getType();
                        List<PkhjbxxBean> jbxxList = gson.fromJson(dataJsonString,type);
                        EventBus.getDefault().post(jbxxList);
                    }
             });
    }
}
