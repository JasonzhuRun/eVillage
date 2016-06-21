package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * a class for managing setting information. we can operate SharedPreferences.
 * 
 * @author dinglei.huang
 * 
 */

public final class SettingManager {

	/**
	 * the first part: a pref for common setting
	 */
	public static final String SETTING_NAME = "setting";
	public static final String ISFIRST = "first";
	public static final String IS_SHOW_AUCTION_GUIDE = "is_show_auction_guide";
	public static final String IS_SHOW_SHOP_GUIDE = "is_show_shop_guide";
    public static final String MINE_DOTE_READ = "mine_dote_read_";
    public static final String MINE_ITEM_COUNT = "mine_item_count";

    public static final String CURRENT_USER = "current_user";

    private static final String LAST_CHECK_UPDATE_TIME = "last_check_update_time";

    public static final String MATERIAL_PREFIX = "material_";

    public static final String LAST_VERSION = "last_version";
    /**
	 * the second part:  
	 * each user has unique pref for app information. named SETTING_NAME + CURRENT_USER
	 */
	public static final String TOKEN = "token";
	public static final String USERID = "user_id";
	public static final String MEMBERID = "member_id";

    private static final String USERNICK = "user_nick";
    private static final String SID = "sid";
    private static final String ECODE = "ecode";
    private static final String COOKIES = "cookies";

	public static final String MESSAGE_ID = "message_id";
	public static final String NOTICE_ID = "notice_id";

	public static final String RECEIVE_MESSAGE = "receive_message";
	public static final String RECEIVE_NOTICE = "receive_notice";

    public static final String PROMOTION_SWITCH = "promotion_switch";
	
	public static final String NEW_MESSAGE_NUM = "new_message_num";

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
		mCommPref = mContext.getSharedPreferences(SETTING_NAME,
				Context.MODE_PRIVATE);
		// 初始化当前用户设置文件
		mCurUserPref = getCurUserPref();
	}
	
	public void setFirst(boolean flag) {
		SharedPreferences.Editor edit = mCommPref.edit();
		edit.putBoolean(ISFIRST, flag);
		edit.commit();
	}

    public void setMaterialNew(boolean isNew, int materialId) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putBoolean(MATERIAL_PREFIX+materialId, isNew);
        edit.commit();
    }

    public boolean isMaterialNew(int materialId) {
        return mCommPref.getBoolean(MATERIAL_PREFIX+materialId, true);
    }

    public boolean isFirst() {
		boolean result = mCommPref.getBoolean(ISFIRST, true);
		return result;
	}

    public boolean isShowAuctionGuide() {

        return mCommPref.getBoolean(IS_SHOW_AUCTION_GUIDE, true);
    }

    public void setShowAuctionGuide(boolean flag) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putBoolean(IS_SHOW_AUCTION_GUIDE, flag);
        edit.commit();
    }

    public boolean isShowShopGuide() {

        return mCommPref.getBoolean(IS_SHOW_SHOP_GUIDE, true);
    }

    public void setShowShopGuide(boolean flag) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putBoolean(IS_SHOW_SHOP_GUIDE, flag);
        edit.commit();
    }
    // “我的”页面对应item是否浏览，用于用户第一次进入时标记红点
    public void setPostionRead(boolean flag, int position) {
        if (mCommPref == null) {
            return;
        }
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putBoolean(MINE_DOTE_READ+position, flag);
        edit.commit();
    }

    public boolean isPostionRead(int position) {
        if (mCommPref == null) {
            return true;
        }
        return mCommPref.getBoolean(MINE_DOTE_READ + position, false);
    }
    // 不带参数，用于更新tab上的红点
    public boolean isPostionRead() {
        if (mCommPref == null) {
            return true;
        }
        // 只要有未读消息就认为此Tab包含未读消息
        for (int i = 0;i < getMineItemCount();i++) {
            if (!mCommPref.getBoolean(MINE_DOTE_READ + i,true)) return false;
        }
        return true;
    }

    public void setMineItemCount(int count) {
        if (mCommPref == null) {
            return;
        }
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putInt(MINE_ITEM_COUNT, count);
        edit.commit();
    }

    public int getMineItemCount() {
        if (mCommPref == null) {
            return 0;
        }
        return mCommPref.getInt(MINE_ITEM_COUNT,0);
    }

    public void setLastVersion(int lastVersion) {
        if (mCommPref == null) {
            return;
        }
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putInt(LAST_VERSION, lastVersion);
        edit.commit();
    }

    public int getLastVersion() {
        if (mCommPref == null) {
            return -1;
        }
        return mCommPref.getInt(LAST_VERSION, -1);
    }
	/**
	 * 登录成功后设置
	 * @param nick
	 */
	public void setCurUser(String nick) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putString(CURRENT_USER, nick);
        edit.commit();

        if (TextUtils.isEmpty(nick)) {
            mCurUserPref = null;
        } else {
            // 初始化当前用户设置文件
            mCurUserPref = mContext.getSharedPreferences(SETTING_NAME + "_" + nick, Context.MODE_PRIVATE);
        }
	}

    public long getLastCheckUpdateTime() {
        return mCommPref.getLong(LAST_CHECK_UPDATE_TIME, 0l);
    }

    public void setLastCheckUpdateTime(long time) {
        SharedPreferences.Editor edit = mCommPref.edit();
        edit.putLong(LAST_CHECK_UPDATE_TIME, time);
        edit.commit();
    }
	
	public SharedPreferences getCurUserPref() {
		String curUser = "";
		if(!TextUtils.equals(curUser, ""))
			return mContext.getSharedPreferences(SETTING_NAME + "_" + curUser, Context.MODE_PRIVATE);
		
		return null;
	}

    public void logout() {
        // 不能保证setCurrentUserId的使用场景，所以，在这里做了判断，防止没有经过登出接口而直接设置currentUserId为空
//        long currentUserId = getUserId();
//        if (!TextUtils.isEmpty(currentUserId)) {
//            setLastUserId(currentUserId);
//        }
        clearUserInfo();

//        setCurUser("");
    }

	////////////////////////////user info: nick is key///////////////////////////////////////////
	
	/**
	 * 退出登录时使用, 清除当前用户ID和TOKEN
	 */
	public void clearUserInfo() {
//		String curUser = getCurUser();
//		File file = new File(mContext.getCacheDir().getParent() + "/shared_prefs", 
//				SETTING_NAME + "_" + curUser + ".xml");
//		Log.e("file", file.getAbsolutePath());
//		if(file.exists()) {
//			Log.e("file", "delete file");
//			file.delete();
//		}
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.remove(TOKEN);
		edit.remove(USERID);
        edit.remove(MEMBERID);
		edit.commit();
	}
	
	public void setToken(String token) {
        if (mCurUserPref == null) {
            return;
        }

		SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putString(TOKEN, token);
		edit.commit();
	}

	public String getToken() {
        if (mCurUserPref == null) {
            return null;
        }

        String token = mCurUserPref.getString(TOKEN, "");
		return token;
	}

	public void setUserId(Long userId) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putLong(USERID, userId);
		edit.commit();
	}

//	public String getUserId() {
//       /* if (mCurUserPref == null) {
//            return 0L;
//        }
//
//        Long userId = mCurUserPref.getLong(USERID, 0);
//		return userId;*/
//        return TextUtils.isEmpty(Login.getUserId()) ? "0" : Login.getUserId();
//	}
	
	public void setMemberId(Long memberId) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putLong(MEMBERID, memberId);
		edit.commit();
	}
	
	public String getMemberId() {
        if (mCurUserPref == null) {
            return "0";
        }
        Long memberId = mCurUserPref.getLong(MEMBERID, 0);
		return String.valueOf(memberId);
	}
	
	public void setUserNick(String userNick) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putString(USERNICK, userNick);
		edit.commit();
	}

	public String getUserNick() {
      /*  if (mCurUserPref == null) {
            return null;
        }

        String userNick = mCurUserPref.getString(USERNICK, "");
		return userNick;*/
        return "";
	}

    public void setSid(String sid) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
        edit.putString(SID, sid);
        edit.commit();
    }

    public String getSid() {
        if (mCurUserPref == null) {
            return null;
        }

        String token = "";//Login.getSid();
        return token;
    }

    public void setEcode(String ecode) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
        edit.putString(ECODE, ecode);
        edit.commit();
    }

    public String getEcode() {
        if (mCurUserPref == null) {
            return null;
        }

        String token = mCurUserPref.getString(ECODE, "");
        return token;
    }

    public void setCookies(String cookies) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
        edit.putString(COOKIES, cookies);
        edit.commit();
    }

    public String getCookies() {
        if (mCurUserPref == null) {
            return null;
        }

        String cookies = mCurUserPref.getString(COOKIES, "");
        return cookies;
    }

    public void setMessageId(Long id) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
        edit.putLong(MESSAGE_ID, id);
        edit.commit();
    }

    public Long getMessageId() {
        if (mCurUserPref == null) {
            return 0L;
        }

        Long id = mCurUserPref.getLong(MESSAGE_ID, 0);
        return id;
    }

	public void setNoticeId(Long id) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putLong(NOTICE_ID, id);
		edit.commit();
	}

	public Long getNoticeId() {
        if (mCurUserPref == null) {
            return 0L;
        }

        Long id = mCurUserPref.getLong(NOTICE_ID, 0);
		return id;
	}
	
	public void setRecieveMessage(boolean flag) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putBoolean(RECEIVE_MESSAGE, flag);
		edit.commit();
	}

	public boolean isRecieveMessage() {
        if (mCurUserPref == null) {
            return false;
        }

        boolean result = mCurUserPref.getBoolean(RECEIVE_MESSAGE, true);
		return result;
	}
	
	public void setRecieveNotice(boolean flag) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putBoolean(RECEIVE_NOTICE, flag);
		edit.commit();
	}
	
	public boolean isRecieveNotice() {
        if (mCurUserPref == null) {
            return false;
        }

        boolean result = mCurUserPref.getBoolean(RECEIVE_NOTICE, true);
		return result;
	}
    // 去掉默认推广位开关，始终处于打开状态
    public void setPromotionSwitch(boolean flag) {
//        if (mCurUserPref == null) {
//            return;
//        }
//
//        SharedPreferences.Editor edit = mCurUserPref.edit();
//        edit.putBoolean(PROMOTION_SWITCH, flag);
//        edit.commit();
    }

    public boolean isPromotionSwitchOn() {
        return true;
//        if (mCurUserPref == null) {
//            return false;
//        }
//        // 从3.1版本打开使用默认推广位(false)
//        boolean result = mCurUserPref.getBoolean(PROMOTION_SWITCH, true);
//        return result;
    }

	public void setNewMsgNum(int number) {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.putInt(NEW_MESSAGE_NUM, number);
		edit.commit();
	}
	
	public int getNewMsgNum() {
        if (mCurUserPref == null) {
            return 0;
        }

        int result = mCurUserPref.getInt(NEW_MESSAGE_NUM, 0);
		return result;
	}
	
	public void clearNewMsgNum() {
        if (mCurUserPref == null) {
            return;
        }

        SharedPreferences.Editor edit = mCurUserPref.edit();
		edit.remove(NEW_MESSAGE_NUM);
		edit.commit();
	}

    public boolean isLogined() {
        return true;
  //      return !StringUtil.isEmpty(getToken());
    }
}
