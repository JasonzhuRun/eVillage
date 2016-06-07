package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.EVNetRequest;
import cn.deepai.evillage.model.LoginData;
import cn.deepai.evillage.model.LoginResult;
import cn.deepai.evillage.model.ResponseHeader;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.MD5Util;
import cn.deepai.evillage.model.RequestHeader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 */
public class LoginActivity extends BaseActivity{

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    @Override
    protected String getActivityName() {
        return "LoginActivity";
    }

    private void initView() {

        Button loginBtn = (Button)findViewById(R.id.login_ensure);
        username = (EditText)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        if (loginBtn != null) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tryToShowProcessDialog();
                    login();
                }
            });
        }

    }

    private void login() {
        LoginData loginData = new LoginData();
        loginData.setUserCode("admin");
        loginData.setPassword(MD5Util.getMD5("12ab!@"));
        loginData.setVersionCode("1");
        RequestHeader header = new RequestHeader();

        header.setReqCode("zyfp01001");
        header.setReqTime((new Date()).toString());
        header.setTokenId("0");

        final Gson gson = new Gson();
        EVNetRequest.request("", gson.toJson(header), gson.toJson(loginData), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                tryToHideProcessDialog();
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

                tryToHideProcessDialog();

            }
        });
    }
}
