package cn.deepai.evillage.manager;

import java.util.List;

import cn.deepai.evillage.model.Villager;
import cn.deepai.evillage.utils.FileUtil;

/**
 * @author GaoYixuan on 16/5/30.
 */
public class CacheManager {

    public static final int CACHE_DETAIL = 0x01;   // 贫困户详情缓存

    private static CacheManager instance;
    private CacheManager() {
        cacheFileDir = FileUtil.getPrivateDir();
    }
    static {
        instance = new CacheManager();
    }

    private List<Villager> villagers;
    private String cacheFileDir;

    public CacheManager getInstance() {
        return instance;
    }


}
