package cn.deepai.evillage;

import android.app.Application;
import android.content.Context;

/**
 * @author GaoYixuan
 */
public class EVApplication extends Application {

    private static Application context;


    @Override
    public void onCreate() {

        super.onCreate();
        context = this;
        // 内存检测工具
        //LeakCanary.install(this);

        // 打开webview的调试功能
//        if (BuildConfig.VERSION_CODE >= 19) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }

        // http://stackoverflow.com/questions/27173375/java-lang-classnotfoundexception-android-os-asynctask-caused-by-admob-google
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable ignore) {

        }

        context = this;
    }

    public static Context getApplication() {
        return context;
    }
}
