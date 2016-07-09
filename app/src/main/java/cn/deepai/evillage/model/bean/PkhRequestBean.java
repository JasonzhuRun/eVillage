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
        tjnd = SettingManager.getCurrentTjnd();
    }

    public PkhRequestBean(boolean isJdPkh) {
        this();
        if (isJdPkh) {
            hid = SettingManager.getJdHid();
        }
    }
}
