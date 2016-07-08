package cn.deepai.evillage.model.bean;

import cn.deepai.evillage.manager.SettingManager;

/**
 * @author GaoYixuan
 */
public class PkhRequestBean {

    public String hid;
    public String tjnd;

    public PkhRequestBean() {
        hid = SettingManager.getCurrentHid();
    }

    public PkhRequestBean(boolean setTjnd) {
        hid = SettingManager.getCurrentHid();
        if (setTjnd) {
            tjnd = SettingManager.getCurrentTjnd();
        }
    }
}
