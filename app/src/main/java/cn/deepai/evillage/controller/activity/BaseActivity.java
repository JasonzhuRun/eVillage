package cn.deepai.evillage.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.deepai.evillage.R;

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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public BaseActivity getActivity() {
        return this;
    }
}
