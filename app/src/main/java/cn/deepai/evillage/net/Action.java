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

    // 登录
    public static final Action ACTION_LOGIN = new Action("termLogin!login.action",Type.other);
    // 贫困户
    public static final Action ACTION_GET_PKHLIST = new Action("termLogin!getPkhList.action",Type.downstream);
    public static final Action ACTION_GET_PKHJBXX = new Action("termLogin!getPkhJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHJBXX = new Action("termLogin!updatePkhJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHJTCYLIST = new Action("termLogin!getPkhJtcyList.action",Type.downstream);
    public static final Action ACTION_GET_JTCYJBXX = new Action("termLogin!getPkhJtcyJbxx.action",Type.downstream);
    public static final Action ACTION_ADD_JTCYJBXX = new Action("termLogin!addPkhJtcyJbxx.action",Type.upstream);
    public static final Action ACTION_UPDATE_JTCYJBXX = new Action("termLogin!updatePkhJtcyJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHSZQKLIST = new Action("termLogin!getPkhSzqkList.action",Type.downstream);
    public static final Action ACTION_GET_PKHSZQKJBXX = new Action("termLogin!getPkhSzqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSZQKJBXX = new Action("termLogin!updatePkhSzqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHZFJBXX = new Action("termLogin!addPkhSzqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHZFQKJBXX = new Action("termLogin!getPkhZfqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHZFQKJBXX = new Action("termLogin!updatePkhZfqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHZFQKJBXX = new Action("termLogin!addPkhZfqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHSCTJJBXX = new Action("termLogin!getPkhSctjJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSCTJJBXX = new Action("termLogin!updatePkhSctjJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHSCTJJBXX = new Action("termLogin!addPkhSctjJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHSHQKJBXX = new Action("termLogin!getPkhShqkJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHSHQKJBXX = new Action("termLogin!updatePkhShqkJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHSHQKJBXX = new Action("termLogin!addPkhShqkJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHCYHZZJBXX = new Action("termLogin!getPkhCyhzzJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_PKHCYHZZJBXX = new Action("termLogin!updatePkhCyhzzJbxx.action",Type.upstream);
    public static final Action ACTION_ADD_PKHCYHZZJBXX = new Action("termLogin!addPkhCyhzzJbxx.action",Type.upstream);
    public static final Action ACTION_GET_PKHJTQKZPLIST = new Action("termLogin!getPkhZp.action",Type.downstream);
    public static final Action ACTION_ADD_PKHJTQKZP = new Action("termLogin!uploadPkhZp.action",Type.upstream);
    // 建档
    public static final Action ACTION_GET_DJDPKHLIST = new Action("termLogin!getDjkPkhList.action",Type.downstream);
    // 台账
    public static final Action ACTION_GET_TZLIST = new Action("termPkhGztz!getPkhGztzList.action",Type.downstream);
    public static final Action ACTION_ADD_TZ = new Action("termPkhGztz!addPkhGztz.action",Type.upstream);
    public static final Action ACTION_GET_TZJBXX = new Action("termPkhGztz!getPkhGztzJbxx.action",Type.downstream);
    public static final Action ACTION_UPDATE_TZJBXX = new Action("termPkhGztz!updatePkhGztzJbxx.action",Type.upstream);
    public static final Action ACTION_GET_TZJTCYLIST = new Action("termPkhGztz!getPkhGztzJtcyList.action",Type.downstream);
    public static final Action ACTION_UPDATE_TZJTCY = new Action("termPkhGztz!updatePkhGztzJtcy.action",Type.upstream);
    public static final Action ACTION_GET_TZSRMX = new Action("termPkhGztz!getPkhGztzSrmx.action",Type.downstream);
    public static final Action ACTION_ADD_TZSRMX = new Action("termPkhGztz!addPkhGztzSrmx.action",Type.upstream);
    public static final Action ACTION_UPDATE_TZSRMX = new Action("termPkhGztz!updatePkhGztzSrmx.action",Type.upstream);
    public static final Action ACTION_GET_TZZCMX = new Action("termPkhGztz!getPkhGztzZcmx.action",Type.downstream);
    public static final Action ACTION_ADD_TZZCMX = new Action("termPkhGztz!addPkhGztzZcmx.action",Type.upstream);
    public static final Action ACTION_UPDATE_TZZCMX = new Action("termPkhGztz!updatePkhGztzZcmx.action",Type.upstream);
    public static final Action ACTION_GET_TZBFJHLIST = new Action("termPkhGztz!getPkhGztzBfjhList.action",Type.downstream);
    public static final Action ACTION_GET_TZJHLSLIST = new Action("termPkhGztz!getPkhGztzJhlsList.action",Type.downstream);
    public static final Action ACTION_ADD_TZZFQK = new Action("termPkhGztz!addPkhGztzZfqk.action",Type.upstream);
    public static final Action ACTION_UPDATE_TZZFQK = new Action("termPkhGztz!updatePkhGztzZfqk.action",Type.upstream);
    public static final Action ACTION_GET_TZXMXX = new Action("termPkhGztz!selectXmxxByhid.action",Type.downstream);

    // 资讯
    public static final Action ACTION_GET_NEWS_LIST = new Action("termLogin!getZxList.action",Type.downstream);
    public static final Action ACTION_GET_NEWS_DETAIL = new Action("termLogin!getZxXq.action",Type.downstream);

    private String name;
    private Type type;
    private String args;

    enum Type{
        downstream,upstream,other
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
