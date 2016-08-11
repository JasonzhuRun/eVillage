package cn.deepai.evillage.model.bean;

/**
 * @author GaoYixuan
 */
public class TzsrmxBean extends BaseBean{
    // 唯一标识收支项目
    private String id;
    // 通过tzid找到收支列表
    private String tzid;
    // 数据字典
    private String szxmid;
    private String xmmc;
    private String xmgm;
    private String clgj;
    private String nsry;
    private String jlsj;
    private String jlr;
    private String bz;

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getTzid() {
        return tzid;
    }

    public void setTzid(String tzid) {
        this.tzid = tzid;
    }

    public String getSzxmid() {
        return szxmid;
    }

    public void setSzxmid(String szxmid) {
        this.szxmid = szxmid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmgm() {
        return xmgm;
    }

    public void setXmgm(String xmgm) {
        this.xmgm = xmgm;
    }

    public String getClgj() {
        return clgj;
    }

    public void setClgj(String clgj) {
        this.clgj = clgj;
    }

    public String getNsry() {
        return nsry;
    }

    public void setNsry(String nsry) {
        this.nsry = nsry;
    }

    public String getJlsj() {
        return jlsj;
    }

    public void setJlsj(String jlsj) {
        this.jlsj = jlsj;
    }

    public String getJlr() {
        return jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
