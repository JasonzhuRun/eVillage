package cn.deepai.evillage.request;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.LoginRequestBean;
import cn.deepai.evillage.bean.RequestHeaderBean;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.utils.EncryptionUtil;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class LoginRequest extends EVRequest {

    public static void request(String name,String password) {

        LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.setUserCode(name);
        loginRequestBean.setPassword(EncryptionUtil.getMD5(password));
        loginRequestBean.setVersionCode("1");

        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_login);

        final Gson gson = new Gson();
        EVRequest.request(EVRequest.ACTION_LOGIN, gson.toJson(header), gson.toJson(loginRequestBean), new Callback() {
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
