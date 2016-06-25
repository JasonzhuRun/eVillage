package cn.deepai.evillage.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GaoYixuan on 16/5/27.
 */
public class EncryptionUtil {
    Base64 base64;
    public String base64Encode(String input) {
      //  Base64.decode()
        return input;
    }

    public String base64Decode(String input) {
        //  Base64.decode()
        return input;
    }

    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes());
            byte[] encryption = md5.digest();

            StringBuilder strBuf = new StringBuilder();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return info;
        }
    }
}
