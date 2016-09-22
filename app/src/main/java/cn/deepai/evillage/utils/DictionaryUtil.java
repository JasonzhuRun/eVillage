package cn.deepai.evillage.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.DictionaryDomainBean;
import cn.deepai.evillage.model.bean.DictionaryValueBean;

/**
 * @author GaoYixuan
 */
public class DictionaryUtil {

    private static Map<String,Map<String,String>> dictinary;
    public static void init() {
        new Thread() {
            @Override
            public void run() {
                InputStream is;
                String jsonString;
                try {
                    is= EVApplication.getApplication().getAssets().open("domains");
                    jsonString = IOUtils.toString(is,"UTF-8");
                } catch (IOException e) {
                    LogUtil.e(DictionaryUtil.class,"Dictionary init error!");
                    return;
                }
                Gson gson = new Gson();
                Type type = new TypeToken<List<DictionaryDomainBean>>(){}.getType();
                List<DictionaryDomainBean> domains = gson.fromJson(jsonString, type);
                dictinary = new HashMap<>();
                for (DictionaryDomainBean domain:domains) {
                    Map<String,String> temp = new HashMap<>();
                    for (DictionaryValueBean value:domain.domainValue) {
                        temp.put(value.valueCode,value.valueName);
                    }
                    dictinary.put(domain.domainCode,temp);
                }
            }
        }.start();
    }

    public static String getValueName(String domain,String valueCode) {
        Map<String,String> values = dictinary.get(domain);
        if (values != null) {
            return values.get(valueCode);
        }
        return valueCode;
    }

    public static Map<String,String> getValueNames(String domain) {
        return dictinary.get(domain);
    }

    public static String getValueName(String valueCode) {
        if (null == valueCode) return null;
        switch (valueCode) {
            case "0":
                return EVApplication.getApplication().getString(R.string.no);
            case "1":
                return EVApplication.getApplication().getString(R.string.yes);
            default:
                return valueCode;
        }
    }
}
