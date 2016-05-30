package cn.deepai.evillage.utils;

import android.os.Environment;

/**
 * sd卡工具类，可以获取sd卡状态
 * @author GaoYixuan
 */
public class SdcardUtil {

    public static boolean isSdcardStateMount() {

        return (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()));
    }
}
