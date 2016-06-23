package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.event.LoginEvent;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.request.LoginRequest;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 登录Activity
 */
public class LoginActivity extends BaseActivity{

    private EditText username;
    private EditText password;

    @SuppressWarnings("all")
    public void onEventMainThread(LoginEvent event) {
        switch (event.rspHeader.getRspCode()) {
            case RspCode.RSP_CODE_SUCCESS:
                SettingManager.getInstance(this).setToken(event.data.getTokenId());
                tryToEnter();
                break;
            default:
                ToastUtil.longToast(event.rspHeader.getRspDesc());
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
        String curUser = SettingManager.getInstance(this).getCurUser();
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
        String token = SettingManager.getInstance(this).getToken();
        if (!TextUtils.isEmpty(token)) {
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
        SettingManager.getInstance(LoginActivity.this).setCurUser(strName);
        LoginRequest.request(strName,strPasswd);
    }
}
