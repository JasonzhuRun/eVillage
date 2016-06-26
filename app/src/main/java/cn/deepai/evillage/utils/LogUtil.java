package cn.deepai.evillage.utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public class LogUtil {

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    public static void w(Class<?> _class, String text) {
        log(_class.getName(), text, 'w');
    }

    public static void e(Class<?> _class, String text) {
        log(_class.getName(), text, 'e');
    }

    public static void d(Class<?> _class, String text) {
        log(_class.getName(), text, 'd');
    }

    public static void i(Class<?> _class, String text) {
        log(_class.getName(), text, 'i');
    }

    public static void v(Class<?> _class, String text) {
        log(_class.getName(), text, 'v');
    }
    /**
     * 根据tag, msg和等级，输出日志
     * @param tag 标签
     * @param msg 日志
     */
    private static void log(String tag, String msg, char level) {
        if (EVApplication.isDebug()) {
            if ('e' == level) { // 输出错误信息
                Log.e(tag, msg);
            } else if ('w' == level) {
                Log.w(tag, msg);
            } else if ('d' == level) {
                Log.d(tag, msg);
            } else if ('i' == level) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
            writeLogtoFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 打开日志文件并写入日志
     * **/
    private static void writeLogtoFile(String logType, String tag, String text) {// 新建或打开日志文件

        String sdcardRoot = FileUtil.getAppDirPath();
        if (sdcardRoot == null) return;
        String appName = EVApplication.getApplication().getResources().getString(R.string.app_name);
        Date nowtime = new Date();

        SimpleDateFormat dateMark = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");// 日志的输出格式
        String logMsg = dateMark.format(nowtime) + "    " + logType
                + "    " + tag + "    " + text;
        File file = new File(sdcardRoot, appName + ".log");
        try {
            FileWriter filerWriter = new FileWriter(file, true);//后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(logMsg);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
