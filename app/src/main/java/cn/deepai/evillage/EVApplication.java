package cn.deepai.evillage;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.MsgList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class EVApplication extends Application {

    private static Application mContext;

    private Timer mTimer;

    @Override
    public void onCreate() {

        super.onCreate();
        mContext = this;
        DictionaryUtil.init();
        initImageLoader();
        startMonitorMsg();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mTimer.cancel();
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                // 设置图片在下载期间显示的图片
                .showImageOnLoading(R.drawable.moren)
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.drawable.moren)
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisk(true)
                .considerExifParams(true)
                // 保留Exif信息
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.EXACTLY)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片下载前的延迟
                .delayBeforeLoading(100)// int
                // delayInMillis为你设置的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 淡入
                .build();
    }


    public static Context getApplication() {
        return mContext;
    }

    public static boolean isDebug() {
        try {
            ApplicationInfo info = mContext.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, FileUtil.getPicCacheDirPath());// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(1000,1000)
                // Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
//				 .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
                // 线程池内加载的数量
                .threadPoolSize(3)
                // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                /*
				 * When you display an image in a small ImageView
				 *  and later you try to display this image (from identical URI) in a larger ImageView
				 *  so decoded image of bigger size will be cached in memory as a previous decoded image of smaller size.
				 *  So the default behavior is to allow to cache multiple sizes of one image in memory.
				 *  You can deny it by calling this method:
				 *  so when some image will be cached in memory then previous cached size of this image (if it exists)
				 *   will be removed from memory cache before.
				 */
//				.denyCacheImageMultipleSizesInMemory()

                // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // .memoryCacheSize(2 * 1024 * 1024)
                //硬盘缓存100MB
                .diskCacheSize(100 * 1024 * 1024)
                //将保存的时候的URI名称用MD5
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100) //缓存的File数量
//                .diskCache(new UnlimitedDiskCache(FileUtil.getPicCacheDir()))// 自定义缓存路径
                // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .imageDownloader(new BaseImageDownloader(mContext, 5 * 1000,
                // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }
    /**
     * 监听消息
     */
    private void startMonitorMsg() {

        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                requestMsgCount();
            }
        };

//        mTimer.schedule(task, 2000, 20*1000);
    }


    private void requestMsgCount() {

        String id = SettingManager.getInstance().getUserId();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", id);
        }catch (JSONException e) {
            return;
        }
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getMsgCount);

        final Gson gson = new Gson();
        EVRequest.request(Action.ACTION_GET_MSG_COUNT, gson.toJson(header), jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        MsgList msgList = gson.fromJson(dataJsonString, MsgList.class);
                        if (msgList.jls > 0) {
                            EventBus.getDefault().post(msgList);
                        }
                    }
                });
    }
}
