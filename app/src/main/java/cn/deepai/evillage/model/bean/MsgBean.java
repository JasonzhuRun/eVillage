package cn.deepai.evillage.model.bean;

import java.io.Serializable;

/**
 * 通告内容
 */
public class MsgBean implements Serializable{

    private String bulletin_content1;
    private String bulletin_id;
    private String bulletin_title;
    private String create_time;
    private String keyWord;
    private String priority;
    private String publish_time;
    private String revoke_time;
    private String sts;
    private String sts_time;
    private String user_id;

    public String getBulletin_content1() {
        return bulletin_content1;
    }

    public void setBulletin_content1(String bulletin_content1) {
        this.bulletin_content1 = bulletin_content1;
    }

    public String getBulletin_id() {
        return bulletin_id;
    }

    public void setBulletin_id(String bulletin_id) {
        this.bulletin_id = bulletin_id;
    }

    public String getBulletin_title() {
        return bulletin_title;
    }

    public void setBulletin_title(String bulletin_title) {
        this.bulletin_title = bulletin_title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getRevoke_time() {
        return revoke_time;
    }

    public void setRevoke_time(String revoke_time) {
        this.revoke_time = revoke_time;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts_time() {
        return sts_time;
    }

    public void setSts_time(String sts_time) {
        this.sts_time = sts_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
