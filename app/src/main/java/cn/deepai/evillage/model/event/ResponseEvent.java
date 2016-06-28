package cn.deepai.evillage.model.event;

/**
 * @author GaoYixuan
 */

public class ResponseEvent<T> {
    public ResponseHeaderEvent rspHeader;
    public T data;
}
