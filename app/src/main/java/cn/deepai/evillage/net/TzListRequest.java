package cn.deepai.evillage.net;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.bean.TzjbxxList;
import cn.deepai.evillage.model.event.TzxgjgEvent;
import de.greenrobot.event.EventBus;

/**
 * 台账列表
 */
public class TzListRequest extends EVRequest {
    /**
     * 数据请求
     */
    public static void request(String staffId,String hid) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hid", hid);
            jsonObject.put("userId", staffId);
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
    /**
     * 新建数据请求
     */
    public static void request(String staffId,String hid,String tznd) {

        final TzjbxxBean tzjbxxBean = new TzjbxxBean();
        tzjbxxBean.setZrrid(staffId);
        tzjbxxBean.setHid(hid);
        tzjbxxBean.setTznd(tznd);
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addTz);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_ADD_TZ, gson.toJson(header), gson.toJson(tzjbxxBean),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzxgjgEvent xgjg = gson.fromJson(dataJsonString,TzxgjgEvent.class);
                        xgjg.tzjbxxBean = tzjbxxBean;
                        EventBus.getDefault().post(xgjg);
                    }
                });
    }
}
