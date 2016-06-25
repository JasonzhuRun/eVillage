package cn.deepai.evillage;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import cn.deepai.evillage.utils.DictionaryUtil;

/**
 * @author GaoYixuan
 */
public class EVApplication extends Application {

    private static Application mContext;

    @Override
    public void onCreate() {

        super.onCreate();
        mContext = this;
        DictionaryUtil.init();
        initImageLoader();
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                // 设置图片在下载期间显示的图片
                .showStubImage(R.drawable.ic_launcher)
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory()
                // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisc()
                // 保留Exif信息
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片下载前的延迟
                .delayBeforeLoading(100)// int
                // delayInMillis为你设置的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading()// 设置图片在下载前是否重置，复位
                // .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
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
        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, "bee_k77/Cache");// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
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
                //硬盘缓存50MB
                .discCacheSize(100 * 1024 * 1024)
                //将保存的时候的URI名称用MD5
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 加密
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的File数量
                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .imageDownloader(new BaseImageDownloader(mContext, 5 * 1000,
                // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

}
