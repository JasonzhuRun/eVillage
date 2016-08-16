package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.model.bean.PkhjbxxBean;

/**
 * SharePreference操作类
 */

public final class SettingManager {

	/**
	 * the first part: a pref for common setting
	 */
	private static final String SETTING_NAME = "setting";

	private static final String CURRENT_USER = "current_user";

	private static final String SERVER_ADDRESS = "server_address";

	private static final String IP = "192.168.101.18";
	private static final String PORT = "8080";

    /**
	 * the second part:  
	 * each user has unique pref for app information. named SETTING_NAME + CURRENT_USER
	 */
	private static final String TOKEN = "token";
	private static final String USER_ID = "user_id";
	private static final String STAFF_ID = "staff_id";
//	// 当前编辑的贫困户ID
//	private static final String HID = "hid";
//	// 当前编辑的贫困户信息的统计年度（目前只有建档年度）
//	private static final String TJND = "tjnd";

	private Context mContext;
	private SharedPreferences mCommPref;
	private SharedPreferences mCurUserPref;

	private static SettingManager mInstance;

	public static SettingManager getInstance() {
		if (mInstance == null) {
			synchronized (SettingManager.class) {
				if (mInstance == null) {
					mInstance = new SettingManager();
				}
			}
		}

		return mInstance;
	}

	private SettingManager() {
		mContext = EVApplication.getApplication();
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


	public String getServerAddress() {
		if (mCommPref == null) {
			return IP + ":" + PORT;
		}
		return mCommPref.getString(SERVER_ADDRESS, IP + ":" + PORT);
	}

	public void setServerAddress(String ip,String port) {
		SharedPreferences.Editor edit = mCommPref.edit();
		edit.putString(SERVER_ADDRESS, ip+":"+port);
		edit.apply();
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
		edit.remove(USER_ID);
		edit.remove(STAFF_ID);
		edit.apply();
	}

	public void clearToken() {
		setToken("");
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

	public void setUserId(String id) {
		if (mCurUserPref == null) {
			return;
		}

		SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putString(USER_ID, id);
		edit.apply();
	}

	public String getUserId() {
		if (mCurUserPref == null) {
			return "";
		}

		return mCurUserPref.getString(USER_ID, "");
	}

	public void setStaffId(String id) {
		if (mCurUserPref == null) {
			return;
		}
		SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putString(STAFF_ID, id);
		edit.apply();
	}

	/**
	 * 和userId是一对多的关系
	 * 用来实现一用户多账户
	 * 和这个版本没多大关系
     */
	public String getStaffId() {
		if (mCurUserPref == null) {
			return "";
		}

		return mCurUserPref.getString(STAFF_ID, "");
	}

//	private static String hid = "";
//	public static void setCurrentHid(String id) {
//		hid = id;
//	}
//	public static String getCurrentHid() {
//		return hid;
//	}
//	private static String tjnd = "";
//	public static void setCurrentTjnd(String nd) {
//		tjnd = nd;
//	}
//
//	public static String getCurrentTjnd() {
//		return tjnd;
//	}
//
//	private static String jdHid = "";
//	public static void setJdHid(String id) {
//		jdHid = id;
//	}
//	public static String getJdHid() {
//		return jdHid;
//	}
	private static PkhjbxxBean sPkh = null;
	public static void setCurrentPkh(PkhjbxxBean pkh) {
		sPkh = pkh;
	}
	public static PkhjbxxBean getCurrentPkh() {
		if (sPkh == null) sPkh = new PkhjbxxBean();
		return sPkh;
	}

	private static PkhjbxxBean sJdPkh = null;
	public static void setCurrentJdPkh(PkhjbxxBean pkh) {
		sJdPkh = pkh;
	}
	public static PkhjbxxBean getCurrentJdPkh() {
		if (sJdPkh == null) sJdPkh = new PkhjbxxBean();
		return sJdPkh;
	}
}
