package cn.deepai.evillage.net;

/**
 * 数据请求
 * 1贫困户列表信息 				termLogin!getPkhList.action
 * 2贫困户详细信息 				termLogin!getPkhJbxx.action
 * 3贫困户家庭成员列表接口 		termLogin!getPkhJtcyList.action
 * 4贫困户家庭成员详细信息接口  	termLogin!getPkhJtcyJbxx.action
 * 5贫困户收支情况列表接口  		termLogin!getPkhSzqkList.action
 * 6贫困户收支情况查看接口  		termLogin!getPkhSzqkJbxx.action
 * 7贫困户收支情况维护接口 		termLogin!updatePkhSzqkJbxx.action
 * 8贫困户收支情况新增接口  		termLogin!addPkhSzqkJbxx.action
 * 9贫困户住房情况查看接口  		termLogin!getPkhZfqkJbxx.action
 * 10贫困户住房情况维护接口 		termLogin!updatePkhZfqkJbxx.action
 * 11贫困户住房情况添加接口  		termLogin!addPkhZfqkJbxx.action
 * 12贫困户生产条件情况查看接口  	termLogin!getPkhSctjJbxx.action
 * 13贫困户生产条件情况维护接口    termLogin!updatePkhSctjJbxx.action
 * 14贫困户生产条件新增接口  		termLogin!addPkhSctjJbxx.action
 * 15贫困户生活情况接口  			termLogin!getPkhShqkJbxx.action
 * 16贫困户生活情况维护接口  		termLogin!updatePkhShqkJbxx.action
 * 17贫困户生活情况新增接口  		termLogin!addPkhShqkJbxx.action
 * 18贫困户产业化组织情况查看接口  termLogin!getPkhCyhzzJbxx.action
 * 19贫困户产业化组织情况维护接口  termLogin!updatePkhCyhzzJbxx.action
 * 20贫困户产业化组织新增接口  	termLogin!addPkhCyhzzJbxx.action
 */
public class Action {

    public static final Action ACTION_LOGIN = new Action("login.action",Type.downstream);
    public static final Action ACTION_GET_PKHLIST = new Action("getPkhList.action",Type.downstream);
    public static final Action ACTION_GET_PKHJBXX = new Action("getPkhJbxx.action",Type.downstream);
    public static final Action ACTION_GET_PKHJTCYLIST = new Action("getPkhJtcyList.action",Type.downstream);
    public static final Action ACTION_GET_JTCYJBXX = new Action("getPkhJtcyJbxx.action",Type.downstream);
    public static final Action ACTION_GET_PKHSZQKLIST = new Action("getPkhSzqkList.action",Type.downstream);
    public static final Action ACTION_GET_PKHSZQKJBXX = new Action("getPkhSzqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSZQKJBXX = new Action("updatePkhSzqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHZFJBXX = new Action("addPkhSzqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHZFQJBXX = new Action("getPkhZfqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHZFQKJBXX = new Action("updatePkhZfqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHZFQKJBXX = new Action("addPkhZfqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHSCTJJBXX = new Action("getPkhSctjJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSCTJJBXX = new Action("updatePkhSctjJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHSCTJJBXX = new Action("addPkhSctjJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHSHQKJBXX = new Action("getPkhShqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSHQKJNXX = new Action("updatePkhShqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHSGQKJBXX = new Action("addPkhShqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHCYHZZJBXX = new Action("getPkhCyhzzJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHCYHZZJBXX = new Action("updatePkhCyhzzJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHCYHZZJBXX = new Action("addPkhCyhzzJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHJTQKZPLIST = new Action("getPkhJtqkzpList.action",Type.downstream);

    private String name;
    private Type type;
    private String args;

    enum Type{
        downstream,upstream
    }

    public Action(String name,Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    /**
     * 每次请求用请求名称＋参数唯一标识
     */
    @Override
    public String toString() {
        return this.name+ "_" +this.args;
    }
}
