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
import cn.deepai.evillage.bean.DomainBean;
import cn.deepai.evillage.bean.ValueBean;

/**
 * @author GaoYixuan
 */

public class DictionaryUtil {

    private static Map<String,Map<String,String>> dictinary;
    public static void init() {
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
        Type type = new TypeToken<List<DomainBean>>(){}.getType();
        List<DomainBean> domains = gson.fromJson(jsonString, type);
        dictinary = new HashMap<>();
        for (DomainBean domain:domains) {
            Map<String,String> temp = new HashMap<>();
            for (ValueBean value:domain.domainValue) {
                temp.put(value.valueCode,value.valueName);
            }
            dictinary.put(domain.domainCode,temp);
        }
    }

    public static String getValueName(String domain,String valueCode) {
        Map<String,String> values = dictinary.get(domain);
        if (values != null) {
            return values.get(valueCode);
        }
        return null;
    }
}
