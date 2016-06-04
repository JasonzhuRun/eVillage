package cn.deepai.evillage.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.LoginManager;

/**
 * @author GaoYixuan
 */
public class LoginActivity extends BaseActivity{

    private LoginHanlder loginHanlder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginHanlder = new LoginHanlder(this);
        initView();
    }


    @Override
    protected String getActivityName() {
        return "LoginActivity";
    }

    private void initView() {

        Button loginBtn = (Button)findViewById(R.id.login_ensure);
        if (loginBtn != null) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tryToShowProcessDialog();
                    new Thread() {
                        @Override
                        public void run() {
                            LoginManager.getInstance().login();
                            loginHanlder.sendEmptyMessage(0);
                        }
                    }.start();
                }
            });
        }

    }

    static class LoginHanlder extends Handler {
        WeakReference<LoginActivity> loginActivityWeakReference;

        LoginHanlder(LoginActivity activity) {
            loginActivityWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity = loginActivityWeakReference.get();
            if (activity != null) {

                activity.tryToHideProcessDialog();
                activity.startActivity(new Intent(activity,MainTabActivity.class));
                activity.finish();
            }
        }
    }
}
