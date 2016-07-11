package cn.deepai.evillage.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxList;
import de.greenrobot.event.EventBus;

/**
 * 台账列表
 */
public class TzListRequest extends EVRequest {

    public static void request(String userId,String hid) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hid", hid);
            jsonObject.put("userId", userId);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getTzList);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZLIST, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzjbxxList jbxxList = gson.fromJson(dataJsonString,TzjbxxList.class);
                        EventBus.getDefault().post(jbxxList);
                    }
             });
    }
}
