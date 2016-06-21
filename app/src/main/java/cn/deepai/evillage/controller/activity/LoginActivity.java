package cn.deepai.evillage.controller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.Date;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.LoginDataBean;
import cn.deepai.evillage.utils.MD5Util;
import cn.deepai.evillage.model.RequestHeaderBean;

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
        LoginDataBean loginDataBean = new LoginDataBean();
        loginDataBean.setUserCode("admin");
        loginDataBean.setPassword(MD5Util.getMD5("12ab!@"));
        loginDataBean.setVersionCode("1");
        RequestHeaderBean header = new RequestHeaderBean();

        header.setReqCode("zyfp01001");
        header.setReqTime((new Date()).toString());
        header.setTokenId("0");

        final Gson gson = new Gson();
//        EVNetRequest.request(EVNetRequest.ACTION_LOGIN_WITH_PASSWORD, gson.toJson(header), gson.toJson(loginDataBean), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                tryToHideProcessDialog();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.body().string());
//                    String str1 = jsonObject.getString("rspHeader");
//                    String str2 = jsonObject.getString("data");
//
//                    ResponseHeaderBean responseHeader = gson.fromJson(str1, ResponseHeaderBean.class);
//                    LoginResultBean result = gson.fromJson(str2, LoginResultBean.class);
//                    LogUtil.v(LoginActivity.class,str1);
//                    LogUtil.v(LoginActivity.class,str2);
//
//                } catch (JSONException e) {
//
//                }
//
//                tryToHideProcessDialog();
//
//            }
//        });
    }
}
