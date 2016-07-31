package cn.deepai.evillage.model.bean;

import cn.deepai.evillage.manager.SettingManager;

/**
 * @author GaoYixuan
 */
public class PkhRequestBean {

    public String hid;
    public String tjnd;

    public PkhRequestBean() {
        this(false);
    }

    public PkhRequestBean(boolean isJdPkh) {
        if (isJdPkh) {
            hid = SettingManager.getCurrentJdPkh().getHid();
            tjnd = SettingManager.getCurrentJdPkh().getJdnf();
        } else {
            hid = SettingManager.getCurrentPkh().getHid();
            tjnd = SettingManager.getCurrentPkh().getJdnf();
        }
    }
}
