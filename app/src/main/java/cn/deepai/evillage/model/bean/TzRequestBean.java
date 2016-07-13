package cn.deepai.evillage.model.bean;

import cn.deepai.evillage.manager.SettingManager;

/**
 * @author GaoYixuan
 */
public class TzRequestBean {

    public String hid;
    public String tjnd;

    public TzRequestBean() {
        hid = SettingManager.getCurrentPkh().getHid();
        tjnd = SettingManager.getCurrentPkh().getJdnf();
    }
}
