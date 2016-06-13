package cn.deepai.evillage.utils;

/**
 * @author GaoYixuan
 */
public class PreferenceUtil {

    private static PreferenceUtil instance;

    public static PreferenceUtil getInstance() {
        return instance;
    }



    static {
        instance = new PreferenceUtil();
    }

    private PreferenceUtil() {

    }
}
