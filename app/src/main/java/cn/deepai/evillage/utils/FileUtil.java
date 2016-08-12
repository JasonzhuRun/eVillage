package cn.deepai.evillage.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Debug;
import android.os.Environment;

import java.io.File;

import cn.deepai.evillage.EVApplication;

/**
 * 文件夹及文件工具
 */
public class FileUtil {

    public static String getAppDirPath() {

        String appPath;
        Context context = EVApplication.getApplication();
        File exFileDir =  context.getExternalFilesDir(null);
        if (exFileDir != null) {
            appPath = exFileDir.getPath();
        } else {
            appPath = context.getFilesDir().getPath();
        }
        return appPath;
    }

    public static File getTextCacheDir() {
        Context context = EVApplication.getApplication();
        String cachePath;
        if (EVApplication.isDebug()&&context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + "text");
    }

    public static String getPicCacheDirPath() {

        return getPicCacheDir().getPath();
    }


    public static File getPicCacheDir() {
        Context context = EVApplication.getApplication();
        String cachePath;
        if (context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        File file = new File(cachePath + File.separator + "pic");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
