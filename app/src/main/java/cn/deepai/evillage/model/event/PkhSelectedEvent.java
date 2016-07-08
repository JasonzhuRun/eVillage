package cn.deepai.evillage.model.event;

/**
 * @author GaoYixuan
 */

public class PkhSelectedEvent {
    public String hid;
    public String name;
    public PkhSelectedEvent(String hid,String name) {
        this.hid = hid;
        this.name = name;
    }
}
