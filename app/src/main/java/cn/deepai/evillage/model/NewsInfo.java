package cn.deepai.evillage.model;

/**
 * @author GaoYixuan
 * 资讯内容
 */
public class NewsInfo {

    private int id;
    private String title;
    private String type;
    private String content;
    private String attachement;
    private String keyword;
    private String policyDate;
    private String origin;
    private String bz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPolicyDate() {
        return policyDate;
    }

    public void setPolicyDate(String policyDate) {
        this.policyDate = policyDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
