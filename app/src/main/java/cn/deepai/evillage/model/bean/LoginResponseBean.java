package cn.deepai.evillage.model.bean;

/**
 * @author GaoYixuan
 */
public class LoginResponseBean {

    private String tokenId;
    private String isUpdate;
    private String updateUrl;
    private String updateContent;
    private String isMustUpdate;
    private String versionName;
    private int userId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getIsMustUpdate() {
        return isMustUpdate;
    }

    public void setIsMustUpdate(String isMustUpdate) {
        this.isMustUpdate = isMustUpdate;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
