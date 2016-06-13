package cn.deepai.evillage.request;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import cn.deepai.evillage.controller.activity.LoginActivity;
import cn.deepai.evillage.model.LoginData;
import cn.deepai.evillage.model.LoginResult;
import cn.deepai.evillage.model.RequestHeader;
import cn.deepai.evillage.model.ResponseHeader;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.MD5Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class LoginRequest extends EVNetRequest{

    public static void request(String name,String password) {

        LoginData loginData = new LoginData();
        loginData.setUserCode(name);
        loginData.setPassword(MD5Util.getMD5(password));
        loginData.setVersionCode("1");

        RequestHeader header = new RequestHeader();
        header.setReqCode("zyfp01001");
        header.setReqTime((new Date()).toString());
        header.setTokenId("0");

        final Gson gson = new Gson();
        EVNetRequest.request(EVNetRequest.ACTION_LOGIN_WITH_PASSWORD, gson.toJson(header), gson.toJson(loginData), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String str1 = jsonObject.getString("rspHeader");
                    String str2 = jsonObject.getString("data");

                    ResponseHeader responseHeader = gson.fromJson(str1, ResponseHeader.class);
                    LoginResult result = gson.fromJson(str2, LoginResult.class);
                    LogUtil.v(LoginActivity.class,str1);
                    LogUtil.v(LoginActivity.class,str2);

                } catch (JSONException e) {

                }

            }
        });
    }
}
