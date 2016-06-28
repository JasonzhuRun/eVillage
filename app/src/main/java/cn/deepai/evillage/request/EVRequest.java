package cn.deepai.evillage.request;

import org.json.JSONException;
import org.json.JSONObject;

import cn.deepai.evillage.utils.LogUtil;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author GaoYixuan
 * 1贫困户列表信息                null
 * 2贫困户详细信息 				termLogin!getPkhJbxx.action
 * 3贫困户家庭成员列表接口 		termLogin!getPkhJtcyList.action
 * 4贫困户家庭成员详细信息接口  	termLogin!getPkhJtcyJbxx.action
 * 5贫困户收支情况列表接口  		termLogin!getPkhSzqkList.action
 * 6贫困户收支情况查看接口  		termLogin!getPkhSzqkJbxx.action
 * 7贫困户收支情况维护接口 		termLogin!updatePkhSzqkJbxx.action
 * 8贫困户收支情况新增接口  		termLogin!addPkhSzqkJbxx.action
 * 9贫困户住房情况查看接口  		termLogin!getPkhZfqkJbxx.action
 * 10贫困户住房情况维护接口 		termLogin!updatePkhZfqkJbxx.action
 * 11贫困户住房情况添加接口  		termLogin!addPkhZfqkJbxx.action
 * 12贫困户生产条件情况查看接口  	termLogin!getPkhSctjJbxx.action
 * 13贫困户生产条件情况维护接口    termLogin!updatePkhSctjJbxx.action
 * 14贫困户生产条件新增接口  		termLogin!addPkhSctjJbxx.action
 * 15贫困户生活情况接口  			termLogin!getPkhShqkJbxx.action
 * 16贫困户生活情况维护接口  		termLogin!updatePkhShqkJbxx.action
 * 17贫困户生活情况新增接口  		termLogin!addPkhShqkJbxx.action
 * 18贫困户产业化组织情况查看接口  termLogin!getPkhCyhzzJbxx.action
 * 19贫困户产业化组织情况维护接口  termLogin!updatePkhCyhzzJbxx.action
 * 20贫困户产业化组织新增接口  	termLogin!addPkhCyhzzJbxx.action
 */
public class EVRequest {

    public static final String ACTION_LOGIN = "login.action";
    public static final String ACTION_GET_PKHLIST = "getPkhList.action";
    public static final String ACTION_GET_PKHJBXX = "getPkhJbxx.action";
    public static final String ACTION_GET_PKHJTCYLIST = "getPkhJtcyList.action";
    public static final String ACTION_GET_JTCYJBXX = "getPkhJtcyJbxx.action";
    public static final String ACTION_GET_PKHSZQKLIST = "getPkhSzqkList.action";
    public static final String ACTION_GET_PKHSZQKJBXX= "getPkhSzqkJbxx.action";
    public static final String ACTION_UPDATE_PKHSZQKJBXX = "updatePkhSzqkJbxx.action";
    public static final String ACTION_ADD_PKHZFJBXX = "addPkhSzqkJbxx.action";
    public static final String ACTION_GET_PKHZFQJBXX = "getPkhZfqkJbxx.action";
    public static final String ACTION_UPDATE_PKHZFQKJBXX = "updatePkhZfqkJbxx.action";
    public static final String ACTION_ADD_PKHZFQKJBXX = "addPkhZfqkJbxx.action";
    public static final String ACTION_GET_PKHSCTJJBXX = "getPkhSctjJbxx.action";
    public static final String ACTION_UPDATE_PKHSCTJJBXX = "updatePkhSctjJbxx.action";
    public static final String ACTION_ADD_PKHSCTJJBXX = "addPkhSctjJbxx.action";
    public static final String ACTION_GET_PKHSHQKJBXX = "getPkhShqkJbxx.action";
    public static final String ACTION_UPDATE_PKHSHQKJNXX = "updatePkhShqkJbxx.action";
    public static final String ACTION_ADD_PKHSGQKJBXX = "addPkhShqkJbxx.action";
    public static final String ACTION_GET_PKHCYHZZJBXX = "getPkhCyhzzJbxx.action";
    public static final String ACTION_UPDATE_PKHCYHZZJBXX = "updatePkhCyhzzJbxx.action";
    public static final String ACTION_ADD_PKHCYHZZJBXX = "addPkhCyhzzJbxx.action";
    public static final String ACTION_GET_PKHJTQKZPLIST = "getPkhJtqkzpList.action";


    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String mURL = "http://192.168.101.18:8080/zyfp-web/inter/termLogin!";
//    private static final String mURL = "http://192.168.212.29:8888/zyfp-web/inter/termLogin!";
    private static OkHttpClient client = new OkHttpClient();

    public static void request(String action,String jsonHeader,String jsonData,Callback callback) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("reqHeader",jsonHeader);
            jsonObject.put("data",jsonData);
        }catch (JSONException e) {
            LogUtil.e(EVRequest.class,"Illegal json format:"+e.toString());
            return;
        }
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE,jsonObject.toString());

        Request request = new Request.Builder()
                .url(mURL+action)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
