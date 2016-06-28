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

import cn.deepai.evillage.manager.CacheManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.FileUtil;

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
        initCache();
        SettingManager.getInstance().setToken("asdfasdfasdf");
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
        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, FileUtil.getPicCacheDirPath());// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(1000, 1000)
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

    private void initCache() {
        String str = "[\n" +
                "{\n" +
                "\"hid\":43,\n" +
                "\"hzxm\":\"张三\",\n" +
                "\"jdnf\":2015,\n" +
                "\"jzdz\":\"遵义市桐梓县燎原镇花园新村7号楼7-201\",\n" +
                "\"vid\":43,\n" +
                "\"lxdh\":\"17012332312\",\n" +
                "\"jlsj\":20150803121212\n" +
                "},\n" +
                "{\n" +
                "\"hid\":44,\n" +
                "\"hzxm\":\"李四\",\n" +
                "\"jdnf\":2014,\n" +
                "\"jzdz\":\"遵义市桐梓县燎原镇花园新村5号楼7-201\",\n" +
                "\"vid\":43,\n" +
                "\"lxdh\":\"18012332322\",\n" +
                "\"jlsj\":20150803121212\n" +
                "}\n" +
                "]\n";
        CacheManager.getInstance().cacheData(EVRequest.ACTION_GET_PKHLIST, str);
        ////////////////////////////
        str = "{\"hid\":0,\"hkhyx\":\"北京市招商银行双榆树支行\",\"hzsfz\":\"55521525351535\",\"hzxm\":\"张三2\",\"jdnf\":2015,\"jhsyh\":0,\"jzdz\":\"遵义\",\"lxdh\":\"1888888883\",\"pkhsx\":\"2\",\"pkhzt\":\"未脱贫\",\"pksbbz\":\"G\",\"tpnf\":0,\"vid\":0,\"yxzh\":\"1234567890\"}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHJBXX,str);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        str = "[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\t\"jlsj\": \"Thu Jun 02 16:47:33 CST 2016\",\n" +
                "\t\t\t\t\"sfzhm\": \"132112\",\n" +
                "\t\t\t\t\"xb\": \"M\",\n" +
                "\t\t\t\t\"xm\": \"张三1\",\n" +
                "\t\t\t\t\"yhzgx\": \"2\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"jlsj\": \"Wed Jun 01 10:02:49 CST 2016\",\n" +
                "\t\t\t\t\"sfzhm\": \"123214\",\n" +
                "\t\t\t\t\"xb\": \"F\",\n" +
                "\t\t\t\t\"xm\": \"张三儿子\",\n" +
                "\t\t\t\t\"yhzgx\": null\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"jlsj\": \"Tue May 31 16:09:47 CST 2016\",\n" +
                "\t\t\t\t\"sfzhm\": null,\n" +
                "\t\t\t\t\"xb\": \"M\",\n" +
                "\t\t\t\t\"xm\": \"张三女222\",\n" +
                "\t\t\t\t\"yhzgx\": null\n" +
                "\t\t\t}\n" +
                "\t\t]";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHJTCYLIST,str);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        str = "{\n" +
                "\"id\":43,\n" +
                "\"tjnd\":2015,\n" +
                "\"cylx\":\"类型1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"cyzsy\":20000,\n" +
                "\"cjfphzzjzz\":1,\n" +
                "\"trhzzjje\":43,\n" +
                "\"cynyhzzz\":1,\n" +
                "\"ltqyddsy\":1000,\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"bz\":\"备注内容\"\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHCYHZZJBXX,str);
        /////////////////////////////////
        str = "[\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img4q.duitang.com/uploads/item/201402/07/20140207211400_LctxW.jpeg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img5.duitang.com/uploads/item/201412/01/20141201081607_XFssr.jpeg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://tupian.enterdesk.com/2013/lxy/07/22/3/g2.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://imgsrc.baidu.com/forum/pic/item/96e1d53f8794a4c215b1fbd60ef41bd5ac6e3921.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img4.duitang.com/uploads/item/201207/18/20120718131407_hBzFy.jpeg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://cdn.duitang.com/uploads/blog/201309/22/20130922144610_mVyyY.thumb.600_0.jpeg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://a.hiphotos.baidu.com/zhidao/pic/item/f603918fa0ec08fa39cce19c58ee3d6d55fbda39.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img2.100bt.com/upload/ttq/20140601/1401600704545_middle.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://pic1.win4000.com/wallpaper/270*185/22301.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":1,\n" +
                "\"hid\":43,\n" +
                "\"zplx\":\"1\",\n" +
                "\"cyzzlx\":\"类型2\",\n" +
                "\"tpmc\":\"img_2015080123.jpg\",\n" +
                "\"tpdz\":\"http://img6.faloo.com/Picture/680x580/0/927/927569.jpg\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "}\n" +
                "]";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHJTQKZPLIST,str);
        ////////////////////////////////
        str = "{\n" +
                "\"id\":43,\n" +
                "\"tjnd\":2015,\n" +
                "\"gdmj\":1.5,\n" +
                "\"yxggdgdmj\":2.1,\n" +
                "\"tian\":3.2,\n" +
                "\"tu\":5.4,\n" +
                "\"lscgymj\":1.5,\n" +
                "\"tghlmj\":1.2,\n" +
                "\"mudmj\":1.6,\n" +
                "\"smmj\":0.8,\n" +
                "\"syjjzwmj\":10.1,\n" +
                "\"scyfmj\":5.6,\n" +
                "\"sxsl\":2,\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"bz\":\"备注内容\"\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHSCTJJBXX,str);
        ////////////////////////////////
        str = "{\n" +
                "\"id\":43,\n" +
                "\"tjnd\":2015,\n" +
                "\"tshyd \":1,\n" +
                "\"zyrllx\":\"木柴，煤\",\n" +
                "\"ysqk\":\"不方便\",\n" +
                "\"hqyysdzykn\":\"劳动力不足\",\n" +
                "\"cslx\":\"露天\",\n" +
                "\"nyxfpqk\":\"收音机，手电\",\n" +
                "\"yskn\":1,\n" +
                "\"ysaq\":0,\n" +
                "\"jlczgl\":10.1,\n" +
                "\"rullx\":\"土路\",\n" +
                "\"wscs\":0,\n" +
                "\"tgbds\":0,\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHSHQKJBXX,str);
        /////////////////////////
        str = "[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\t\"jlsj\": <null>,\n" +
                "\t\t\t\t\"jtcsr\": <null>,\n" +
                "\t\t\t\t\"jtnrjcsr\": <null>,\n" +
                "\t\t\t\t\"jtzsr\": \"234.0\",\n" +
                "\t\t\t\t\"scjyzcfy\": <null>,\n" +
                "\t\t\t\t\"tjnd\": <null>\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"id\": \"4\",\n" +
                "\t\t\t\t\"jlsj\": <null>,\n" +
                "\t\t\t\t\"jtcsr\": <null>,\n" +
                "\t\t\t\t\"jtnrjcsr\": <null>,\n" +
                "\t\t\t\t\"jtzsr\": \"234.0\",\n" +
                "\t\t\t\t\"scjyzcfy\": <null>,\n" +
                "\t\t\t\t\"tjnd\": <null>\n" +
                "\t\t\t}\n" +
                "\t\t]";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHSZQKLIST,str);
        /////////////////////////
        str = "{\n" +
                "\"id\":43,\n" +
                "\"tjnd\":2015,\n" +
                "\"zfmj\":10.5,\n" +
                "\"fwzyjg\":\"土坯\",\n" +
                "\"jfsj\":20050803,\n" +
                "\"zyzfsfwf\":1,\n" +
                "\"ydfpbqqk\":\"\",\n" +
                "\"jlsj\":2015080312121,\n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHZFQJBXX,str);
        /////////////////////////////////
        str = "{\n" +
                "\"xm\":\"赵四\",\n" +
                "\"xb\":\"F\",\n" +
                "\"sfzhm\":\"110233200908091231\",\n" +
                "\"yhzgx\":\"3\",\n" +
                "\"mz\": \"1\",\n" +
                "\"whcd\": \"3\",\n" +
                "\"zxsqk\": \"1\",\n" +
                "\"jkqk\": \"1\",\n" +
                "\"ldnlzk\": \"1\",\n" +
                "\"wgzk\": \"1\",\n" +
                "\"wgsj\": \"1\",\n" +
                "\"dbrk\": \"1\",\n" +
                "\"cjxnhyl\": \"1\",\n" +
                "\"cjcxjmjbylbx\": 0,\n" +
                "\"cjxnhyl\": 0,\n" +
                "\"cyzt\": null,\n" +
                "\"dbrk\": 0,\n" +
                "\"id\": null,\n" +
                "\"jkqk\": null,\n" +
                "\"jlr\": null,\n" +
                "\"jlsj\": null,\n" +
                "\"ldnlzk\": null\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_JTCYJBXX,str);
        /////////////////////////////
        str = "{\n" +
                "\"id\":43,\n" +
                "\"tjnd\":2015,\n" +
                "\"jtzsr\":15000,\n" +
                "\"wgsr\":200.1,\n" +
                "\"scjyxsr\":30.2,\n" +
                "\"ccxsr\":500.4,\n" +
                "\"dk\":1000.5,\n" +
                "\"scjyzcfy\":1000.2,\n" +
                "\"jtcsr\":100.6,\n" +
                "\"jtnrjcsr\":20.8,\n" +
                "\"glbt\":1000.1,\n" +
                "\"jhsyj\":50.6,\n" +
                "\"dbj\":200,\n" +
                "\"cxjmjbylbx\":20,\n" +
                "\"ylbx\":200,\n" +
                "\"deylbz\":2000,\n" +
                "\"stbcj\":20,\n" +
                "\"jlsj\":2015080312121,   \n" +
                "\"jlr\":\"管理员\",\n" +
                "\"by\":\"备注内容\"\n" +
                "}";
        CacheManager.getInstance().cacheData(
                EVRequest.ACTION_GET_PKHSZQKJBXX,str);
    }
}
