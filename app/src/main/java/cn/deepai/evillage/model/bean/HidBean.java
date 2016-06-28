package cn.deepai.evillage.model.bean;

import cn.deepai.evillage.manager.SettingManager;

/**
 * @author GaoYixuan
 */
public class HidBean {

    public int hid;

    public HidBean() {
        hid = SettingManager.getInstance().getCurrentHid();
    }
}
