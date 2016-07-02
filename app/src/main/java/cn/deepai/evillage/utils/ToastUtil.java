package cn.deepai.evillage.utils;

import android.view.Gravity;
import android.widget.Toast;

import cn.deepai.evillage.EVApplication;

/**
 * @author GaoYixuan
 *         16/6/1.
 */
public class ToastUtil {

    public static void shortToast(String content) {
        Toast toast = Toast.makeText(EVApplication.getApplication(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void longToast(String content) {
        Toast toast = Toast.makeText(EVApplication.getApplication(), content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
