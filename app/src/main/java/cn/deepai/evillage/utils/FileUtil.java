package cn.deepai.evillage.utils;

import android.os.Environment;

import java.io.File;

import cn.deepai.evillage.EVApplication;

/**
 * Created by GaoYixuan on 16/5/30.
 */
public class FileUtil {


    public static String getSdcardDir() {

        String sdcardRoot = SdcardUtil.isSdcardStateMount()?Environment.getExternalStorageDirectory().getPath():null;
        if (sdcardRoot == null) return null;
        File file =new File(sdcardRoot+"/DeepAI/eVillage");
        //如果文件夹不存在则创建
        if  (!file .exists()  && !file .isDirectory()) {
            File parentFile =new File(sdcardRoot+"/DeepAI");
            parentFile .mkdir();
            file .mkdir();
        }
        return sdcardRoot+"/DeepAI/eVillage";
    }

    public static String getPrivateDir() {

        return EVApplication.getApplication().getFilesDir().getPath();
    }

}
