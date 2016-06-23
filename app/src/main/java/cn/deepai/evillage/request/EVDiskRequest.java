package cn.deepai.evillage.request;

import cn.deepai.evillage.EVApplication;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author GaoYixuan
 */
public class EVDiskRequest {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    public void request(String action,String jsonData,Callback callback) {

    }
}
