package cn.deepai.evillage.utils;

import android.widget.Toast;

import cn.deepai.evillage.EVApplication;

/**
 * @author GaoYixuan
 *         16/6/1.
 */
public class ToastUtil {

    public static void shortToast(String content) {
        Toast.makeText(EVApplication.getApplication(), content, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String content) {
        Toast.makeText(EVApplication.getApplication(), content, Toast.LENGTH_LONG).show();
    }
}
