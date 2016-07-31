package cn.deepai.evillage.utils;

import android.text.TextUtils;

/**
 * @author GaoYixuan
 * 拼接和拆分字符串
 */
public class StringUtil {
    public static final String CODE_BREAK = ",";
    public static final String TEXT_BREAK = "、";

    public static String[] splitCode(String codeString) {
        if (TextUtils.isEmpty(codeString)) return new String[]{};
        else return codeString.split(CODE_BREAK);
    }

    public static String[] splitText(String textString) {
        if (TextUtils.isEmpty(textString)) return new String[]{};
        else return textString.split(TEXT_BREAK);
    }

    public static String appendText(String[] codes,String key) {
        StringBuilder str = new StringBuilder();
        for (String code:codes) {
            str.append(DictionaryUtil.getValueName(key,code));
            str.append(TEXT_BREAK);
        }
        if (str.length() > 0) return str.substring(0,str.length() - 1);
        else return str.toString();
    }

}
