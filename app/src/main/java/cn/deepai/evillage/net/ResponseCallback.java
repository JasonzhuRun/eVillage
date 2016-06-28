package cn.deepai.evillage.net;

import cn.deepai.evillage.model.event.ResponseEvent;

/**
 * @author GaoYixuan
 */
public interface ResponseCallback {
    void onResponse(ResponseEvent event);
}
