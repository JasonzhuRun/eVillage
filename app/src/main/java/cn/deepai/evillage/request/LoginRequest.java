package cn.deepai.evillage.request;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.logging.LogRecord;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.LoginActivity;
import cn.deepai.evillage.bean.LoginRequestBean;
import cn.deepai.evillage.bean.LoginResponseBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.bean.ResponseHeaderBean;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.MD5Util;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class LoginRequest extends EVNetRequest {

    public static void request(String name,String password) {

        LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.setUserCode(name);
        loginRequestBean.setPassword(MD5Util.getMD5(password));
        loginRequestBean.setVersionCode("1");

        RequestHeaderBean header = new RequestHeaderBean();
        header.setReqCode(EVApplication.getApplication().getString(R.string.req_code_login));
        header.setReqTime((new Date()).toString());
        header.setTokenId("0");

        final Gson gson = new Gson();
        EVNetRequest.request(EVNetRequest.ACTION_LOGIN, gson.toJson(header), gson.toJson(loginRequestBean), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LoginEvent event = gson.fromJson(response.body().string(), LoginEvent.class);
                LogUtil.v(LoginRequest.class,event.toString());
                EventBus.getDefault().post(event);
            }
        });
    }
}
