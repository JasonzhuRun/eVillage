package cn.deepai.evillage.bean;

import java.util.Date;

/**
 * @author GaoYixuan
 */
public class RequestHeaderBean {


    private String reqCode;
    private  String reqTime;
    private String tokenId;

    public RequestHeaderBean() {
        reqTime = new Date().toString();
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
