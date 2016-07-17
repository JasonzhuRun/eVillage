package cn.deepai.evillage.manager;

import android.text.TextUtils;

import com.jakewharton.disklrucache.DiskLruCache;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.utils.EncryptionUtil;
import cn.deepai.evillage.utils.FileUtil;
import cn.deepai.evillage.utils.PhoneInfoUtil;

/**
 * @author GaoYixuan on 16/5/30.
 */
public class CacheManager {

    private static final int TEXT_CACHE_SIZE = 10 * 1024 * 1024;

    public static final String UPLOAD_CACHE_KEY = "upload_cache_data";

    private static CacheManager instance;
    private DiskLruCache mDiskLruCache;
    private static ArrayList<String> uploadList;
    private static String uploadListString;

    static {
        instance = new CacheManager();
        String staffId = SettingManager.getInstance().getStaffId();
        if (!TextUtils.isEmpty(staffId)) {
            uploadListString = getInstance().getCacheData(UPLOAD_CACHE_KEY+":"+staffId);
            if (TextUtils.isEmpty(uploadListString)) {
                uploadListString = "";
                uploadList = new ArrayList<>();
            } else {
                List<String> tempList = Arrays.asList(uploadListString.split(";"));
                uploadList = new ArrayList<>(tempList);
            }
        }
    }

    public static CacheManager getInstance() {
        return instance;
    }

    public void addToUploadList(String request,String data) {
        String staffId = SettingManager.getInstance().getStaffId();
        if (!TextUtils.isEmpty(staffId)) {
            cacheData(request,data);
            uploadList.add(request);
            if (!TextUtils.isEmpty(uploadListString)) {
                uploadListString += ";";
            }
            uploadListString += request;
            cacheData(UPLOAD_CACHE_KEY+":"+staffId,uploadListString);
        }
    }

    public List getUploadList() {
        return uploadList;
    }

    public void clearUploadList() {
        uploadListString = "";
        uploadList = new ArrayList<>();
        String staffId = SettingManager.getInstance().getStaffId();
        if (!TextUtils.isEmpty(staffId)) {
            removeCacheData(UPLOAD_CACHE_KEY + ":" + staffId);
        }
    }
    /**
     * 缓存请求数据
     */
    public void cacheData(final String request, final String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                removeCacheData(request);
                try {
                    String key = EncryptionUtil.getMD5(request);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (dataToStream(data, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized String getCacheData(String request) {
        try {
            String key = EncryptionUtil.getMD5(request);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                return IOUtils.toString(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized void removeCacheData(String request) {
        try {
            String key = EncryptionUtil.getMD5(request);
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean dataToStream(String data,OutputStream outputStream) {
        InputStream inputStream = IOUtils.toInputStream(data);
        BufferedOutputStream out = new BufferedOutputStream(outputStream, 8 * 1024);
        BufferedInputStream in = new BufferedInputStream(inputStream, 8 * 1024);
        try {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private CacheManager() {

        try {
            File cacheDir = FileUtil.getTextCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, PhoneInfoUtil.getVersionCode(), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
