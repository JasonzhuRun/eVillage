package cn.deepai.evillage.utils;

import java.util.UUID;

/**
 * @author GaoYixuan
 */
public class UUIDUtil {

    private static String uuidInThisSession;

    static {

        String temp = UUID.randomUUID().toString();
        uuidInThisSession = temp.substring(0,8)+temp.substring(9,13)+temp.substring(14,18)+
                temp.substring(19,23)+temp.substring(24);
    }

    private UUIDUtil() {}

    public static String getUUID() {

        return uuidInThisSession;
    }
}
