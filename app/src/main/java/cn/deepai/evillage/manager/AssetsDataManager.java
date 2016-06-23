package cn.deepai.evillage.manager;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.utils.LogUtil;

/**
 * @author GaoYixuan
 */
public class AssetsDataManager {

    public static String getData(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = EVApplication.getApplication().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            LogUtil.e(AssetsDataManager.class,"Read "+fileName+" Error!");
        }
        return stringBuilder.toString();
    }


    public static void copyFileFromAssets(Context context, String assetsFilePath, String targetFileFullPath) {
        try {
            AssetManager assetManager = EVApplication.getApplication().getAssets();
            InputStream source = assetManager.open(new File(assetsFilePath).getPath());
            File destinationFile = new File(targetFileFullPath, assetsFilePath);
            destinationFile.getParentFile().mkdirs();
            OutputStream destination = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int nread;

            while ((nread = source.read(buffer)) != -1) {
                if (nread == 0) {
                    nread = source.read();
                    if (nread < 0)
                        break;
                    destination.write(nread);
                    continue;
                }
                destination.write(buffer, 0, nread);
            }
            destination.close();
        } catch (IOException e) {
            LogUtil.e(AssetsDataManager.class,"Copy "+assetsFilePath+" Error!");
        }
    }
}
