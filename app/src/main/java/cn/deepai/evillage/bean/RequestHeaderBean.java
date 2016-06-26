package cn.deepai.evillage.bean;

import java.util.Date;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.manager.SettingManager;

/**
 * @author GaoYixuan
 */
public class RequestHeaderBean {


    private String reqCode;
    private  String reqTime;
    private String tokenId;

    public RequestHeaderBean(int reqCodeId) {
        this.reqTime = new Date().toString();
        this.reqCode = EVApplication.getApplication().getString(reqCodeId);
        this.tokenId = SettingManager.getInstance().getToken();
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
