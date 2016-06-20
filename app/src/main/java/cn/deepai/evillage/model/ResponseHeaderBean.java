package cn.deepai.evillage.model;

import java.util.Date;

/**
 * @author GaoYixuan
 */
public class ResponseHeaderBean {

    private String reqCode;
    private String rspCode;
    private String rspDesc;
    private String rspTime;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspDesc() {
        return rspDesc;
    }

    public void setRspDesc(String rspDesc) {
        this.rspDesc = rspDesc;
    }

    public String getRspTime() {
        return rspTime;
    }

    public void setRspTime(String rspTime) {
        this.rspTime = rspTime;
    }
}
