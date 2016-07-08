package cn.deepai.evillage.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.ListBean;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.PkhjbxxList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import de.greenrobot.event.EventBus;

/**
 * 贫困户列表
 */
public class PkhJbxxListRequest extends EVRequest {

    public static void request(String staffId) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("staffId", staffId);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getPkhJbxxList);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHLIST, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
//                        dataJsonString = "{\n" +
//                                "\"list\":[\n" +
//                                "{\n" +
//                                "\"hid\":\"1\",\n" +
//                                "\"hzxm\":\"张三\",\n" +
//                                "\"jdnf\":2015,\n" +
//                                "\"jzdz\":\"遵义市桐梓县燎原镇花园新村7号楼7-201\",\n" +
//                                "\"vid\":\"43\",\n" +
//                                "\"lxdh\":\"17012332312\",\n" +
//                                "\"jlsj\":\"20150803121212\"\n" +
//                                "},\n" +
//                                "{\n" +
//                                "\"hid\":\"1\",\n" +
//                                "\"hzxm\":\"李四\",\n" +
//                                "\"jdnf\":2014,\n" +
//                                "\"jzdz\":\"遵义市桐梓县燎原镇花园新村5号楼7-202\",\n" +
//                                "\"vid\":\"43\",\n" +
//                                "\"lxdh\":\"18012332322\",\n" +
//                                "\"jlsj\":\"20150803121212\"\n" +
//                                "}\n" +
//                                "]\n" +
//                                "}";
                        PkhjbxxList jbxxList = gson.fromJson(dataJsonString, PkhjbxxList.class);
                        EventBus.getDefault().post(jbxxList);
                    }
             });
    }
}
