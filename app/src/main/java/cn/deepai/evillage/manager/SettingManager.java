package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharePreference操作类
 */

public final class SettingManager {

	/**
	 * the first part: a pref for common setting
	 */
	public static final String SETTING_NAME = "setting";

    public static final String CURRENT_USER = "current_user";

    /**
	 * the second part:  
	 * each user has unique pref for app information. named SETTING_NAME + CURRENT_USER
	 */
	public static final String TOKEN = "token";

	private Context mContext;
	private SharedPreferences mCommPref;
	private SharedPreferences mCurUserPref;

	private static SettingManager mInstance;

	public static SettingManager getInstance(Context context) {
		if (mInstance == null) {
			synchronized (SettingManager.class) {
				if (mInstance == null) {
					mInstance = new SettingManager(context);
				}
			}
		}

		return mInstance;
	}

	private SettingManager(Context context) {
		mContext = context;
        // 系统设置文件
		mCommPref = mContext.getSharedPreferences(SETTING_NAME,
				Context.MODE_PRIVATE);
		// 初始化当前用户设置文件
		mCurUserPref = getCurUserPref();
	}

    private SharedPreferences getCurUserPref() {
        String curUser = getCurUser();
        if(!TextUtils.isEmpty(curUser))
            return mContext.getSharedPreferences(SETTING_NAME + "_" + curUser, Context.MODE_PRIVATE);

        return null;
    }

	/**
	 * 登录成功后设置
	 */
	public void setCurUser(String userCode) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putString(CURRENT_USER, userCode);
        edit.apply();

        if (TextUtils.isEmpty(userCode)) {
            mCurUserPref = null;
        } else {
            // 初始化当前用户设置文件
            mCurUserPref = mContext.getSharedPreferences(SETTING_NAME + "_" + userCode, Context.MODE_PRIVATE);
        }
	}

    public String getCurUser() {
        if (mCommPref == null) {
            return null;
        }

        return mCommPref.getString(CURRENT_USER, "");
    }
	////////////////////////////user info: nick is key///////////////////////////////////////////
	
	/**
	 * 退出登录时使用, 清除当前用户ID和TOKEN
	 */
	public void clearUserInfo() {

        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.remove(TOKEN);
		edit.apply();
	}
	
	public void setToken(String token) {
        if (mCurUserPref == null) {
            return;
        }

		SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putString(TOKEN, token);
		edit.apply();
	}

	public String getToken() {
        if (mCurUserPref == null) {
            return null;
        }

        return mCurUserPref.getString(TOKEN, "");
	}
}
