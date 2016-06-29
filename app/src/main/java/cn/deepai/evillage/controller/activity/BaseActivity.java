package cn.deepai.evillage.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.event.LoginResponseEvent;
import cn.deepai.evillage.model.event.ResponseHeaderEvent;
import cn.deepai.evillage.model.event.RspCode;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    private boolean mLock = false;

    public void tryToShowProcessDialog() {
        tryToShowProcessDialog(R.string.dialog_loading);
    }

    public void tryToShowProcessDialog(int strResId) {
        tryToHideProcessDialog();
        if (mProgressDialog == null) {
            String str = getString(strResId);
            mProgressDialog = ProgressDialog.show(this, null, str, true, true);
        }
    }

    public void tryToHideProcessDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }



    public BaseActivity getActivity() {
        return this;
    }

    /**
     * 尝试loading加锁
     */
    protected boolean tryEntryLoadingLock() {
        if (mLock) {
            return false;
        }
        mLock = true;
        return true;
    }

    /**
     * 退出loading加锁
     */
    protected void unlockLoadingLock() {
        mLock = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.v(getActivityName(),"Lifecycle onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.v(getActivityName(),"Lifecycle onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.v(getActivityName(),"Lifecycle onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.v(getActivityName(),"Lifecycle onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.v(getActivityName(),"Lifecycle onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected abstract String getActivityName();

}
