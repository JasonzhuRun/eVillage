package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.event.LoginResponseEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.net.LoginRequest;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity{

    private EditText username;
    private EditText password;

    @SuppressWarnings("all")
    public void onEventMainThread(LoginResponseEvent event) {

        SettingManager.getInstance().setToken(event.getTokenId());
        SettingManager.getInstance().setUserId(event.getUserId());
    }

    @SuppressWarnings("all")
    public void onEventMainThread(ResponseHeaderEvent event) {
        switch (event.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                tryToEnter();
                break;
            case RspCode.RSP_CODE_TOKEN_NOTEXIST:
                ToastUtil.shortToast(getString(R.string.login_overdue));
                SettingManager.getInstance().clearToken();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                ToastUtil.shortToast(event.getRspDesc());
                break;
        }
        tryToHideProcessDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        tryToEnter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected String getActivityName() {
        return "LoginActivity";
    }

    private void initView() {

        Button loginBtn = (Button)findViewById(R.id.login_ensure);
        username = (EditText)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        String curUser = SettingManager.getInstance().getCurUser();
        if (!TextUtils.isEmpty(curUser)) {
            username.setText(curUser);
            password.requestFocus();
        }
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

    private void tryToEnter() {
        tryToShowProcessDialog();
        String token = SettingManager.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            LogUtil.v(getActivityName(),token);
            Intent intent = new Intent(LoginActivity.this,MainTabActivity.class);
            startActivity(intent);
            finish();
        }
        tryToHideProcessDialog();
    }

    private void login() {

        String strName = username.getText().toString();
        String strPasswd = password.getText().toString();
        strPasswd = "12ab!@";
        if (TextUtils.isEmpty(strName)) {
            ToastUtil.shortToast(getString(R.string.username_empyt));
            tryToHideProcessDialog();
            return;
        }
        if (TextUtils.isEmpty(strPasswd)) {
            ToastUtil.shortToast(getString(R.string.password_empyt));
            tryToHideProcessDialog();
            return;
        }
        SettingManager.getInstance().setCurUser(strName);
        LoginRequest.request(strName,strPasswd);
    }
}
