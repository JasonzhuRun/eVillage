package cn.deepai.evillage.net;

import com.google.gson.Gson;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.LoginRequestBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ResponseEvent;
import cn.deepai.evillage.utils.EncryptionUtil;
import cn.deepai.evillage.utils.PhoneInfoUtil;

/**
 * @author GaoYixuan
 * 登录请求处理
 */
public class LoginRequest extends EVRequest {

    public static void request(String name,String password) {

        LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.setUserCode(name);
        loginRequestBean.setPassword(EncryptionUtil.getMD5(password));
        loginRequestBean.setVersionCode(String.valueOf(PhoneInfoUtil.getVersionCode()));

        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_login);
        header.setTokenId("0");
        final Gson gson = new Gson();
        EVRequest.request(EVRequest.ACTION_LOGIN, gson.toJson(header), gson.toJson(loginRequestBean), new ResponseCallback() {
            @Override
            public void onResponse(ResponseEvent event) {

            }
        });
    }
}
