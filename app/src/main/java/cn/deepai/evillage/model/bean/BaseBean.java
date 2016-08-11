package cn.deepai.evillage.model.bean;

/**
 * @author GaoYixuan
 */

public class BaseBean {
    public static final int NORMAL = 0; // 正常
    public static final int NEW = 1;    // 新建
    public static final int EDIT = 2;   // 编辑

    public int beanState = NORMAL;
}
