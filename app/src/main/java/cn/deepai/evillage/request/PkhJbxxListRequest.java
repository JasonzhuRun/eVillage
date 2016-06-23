package cn.deepai.evillage.request;

import com.google.gson.Gson;

import cn.deepai.evillage.event.PkhListEvent;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class PkhJbxxListRequest extends EVNetRequest{

    public static void request(int userId) {
        String str = "{\n" +
                "    \"data\": [\n" +
                "            {\n" +
                "                \"hid\":43,\n" +
                "                \"hzxm\":\"张三\",\n" +
                "                \"jdnf\":2015,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村7号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"17012332312\",\n" +
                "                \"jlsj\":20150803121212\n" +
                "            },\n" +
                "            {\n" +
                "                \"hid\":44,\n" +
                "                \"hzxm\":\"李四\",\n" +
                "                \"jdnf\":2014,\n" +
                "                \"jzdz\":\"遵义市桐梓县燎原镇花园新村5号楼7-201\",\n" +
                "                \"vid\":43,\n" +
                "                \"lxdh\":\"18012332322\",\n" +
                "                \"jlsj\":20150803121212\n" +
                "            }\n" +
                "        ],\n" +
                "    \"rspHeader\": {\n" +
                "        \"reqCode\": \"zyfp01001\",\n" +
                "        \"rspCode\": \"0000\",\n" +
                "        \"rspDesc\": \"请求成功\",\n" +
                "        \"rspTime\": \"2016-06-22 14:44:17\"\n" +
                "    }\n" +
                "}";
        Gson gson = new Gson();
        PkhListEvent event = gson.fromJson(str, PkhListEvent.class);
        EventBus.getDefault().post(event);

    }
}
