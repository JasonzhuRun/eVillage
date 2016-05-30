package cn.deepai.evillage.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.deepai.evillage.R;
import cn.deepai.evillage.utils.LogUtil;

/**
 * 基类Activity
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    private boolean mLock = false;

    /**
     * 尝试loading加锁
     *
     * @return
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

    public void tryToHideProcessDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void tryToShowProcessDialog() {
        tryToShowProcessDialog(R.string.dialog_loading);
    }

    protected void tryToShowProcessDialog(int strResId) {
        tryToHideProcessDialog();
        if (mProgressDialog == null) {
            String str = getString(strResId);
            mProgressDialog = ProgressDialog.show(this, null, str, true, false);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.v(BaseActivity.class,"Lifecycle onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.v(BaseActivity.class,"Lifecycle onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.v(BaseActivity.class,"Lifecycle onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.v(BaseActivity.class,"Lifecycle onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.v(BaseActivity.class,"Lifecycle onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.v(BaseActivity.class,"onBackPressed");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public BaseActivity getActivity() {
        return this;
    }
}
