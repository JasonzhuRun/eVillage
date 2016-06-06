package cn.deepai.evillage.manager;

import android.os.Handler;
import android.os.Message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cn.deepai.evillage.model.EVRequest;

/**
 * @author GaoYixuan
 * 数据管理基类
 * 1.网络操作队列
 * 2.数据获取透明化
 *  2.1 网络可用时进行网络请求，请求失败返回缓存
 *  2.2 网络可用请求成功时判断服务器更新时间，如果服务器滞后则进行数据上传并返回本地副本
 *  2.3 网络不可用时直接调用缓存
 * 3.数据上传
 *  网络可用并且本地副本较新时更新服务器数据
 */
public class DataManager {

    private static NetworkHandler networkHandler;

    protected DataManager() {

    }

    public void addProcess(EVRequest request) {
        networkHandler.post(request);

    }

    private static class NetworkHandler extends Handler {

    }
}
