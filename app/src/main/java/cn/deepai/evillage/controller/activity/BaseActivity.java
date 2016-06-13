package cn.deepai.evillage.controller.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.Event;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    private boolean mLock = false;
    private long lastPressedTime = 0;

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

    @Override
    public void onBackPressed() {
        if((System.currentTimeMillis()- lastPressedTime) > 2000){
            ToastUtil.shortToast("再按一次退出程序");
            lastPressedTime = System.currentTimeMillis();
        } else {
            finish();
        }
        LogUtil.v(getActivityName(),"onBackPressed");
    }

    public BaseActivity getActivity() {
        return this;
    }

    public void onEvent() {

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

    protected void tryToLoad() {

    }
}
