package cn.deepai.evillage.model.event;

/**
 * 请求响应码
 */
public final class RspCode {

    public static final String RSP_CODE_SUCCESS = "0000";
    public static final String RSP_CODE_SUCCESS_DSC = "请求成功";
    public static final String RSP_CODE_MSG_PARAMS_LOSTED = "1101";
    public static final String RSP_CODE_MSG_PARAMS_LOSTED_DESC = "消息参数缺失";
    public static final String RSP_CODE_MSG_FORMAT_EXCEPTION = "1102";
    public static final String RSP_CODE_MSG_FORMAT_EXCEPTION_DSC = "消息格式错误";
    public static final String RSP_CODE_TOKEN_NOTEXIST = "1103";
    public static final String RSP_CODE_TOKEN_NOTEXIST_DSC = "TOKEN不存在或已过期";
    public static final String RSP_CODE_USER_NOT_LOGIN = "1104";
    public static final String RSP_CODE_USER_NOT_LOGIN_DSC = "用户没有登录";
    public static final String RSP_CODE_INTERFACE_EXCEPTION = "1105";
    public static final String RSP_CODE_INTERFACE_EXCEPTION_DSC = "业务接口调用异常";
    public static final String RSP_CODE_USER_NOTEXIST = "1106";
    public static final String RSP_CODE_USER_NOTEXIST_DSC = "用户不存在";
    public static final String RSP_CODE_USER_PASSWORD_WRONG = "1107";
    public static final String RSP_CODE_USER_PASSWORD_WRONG_DSC = "密码错误";
    public static final String RSP_CODE_USER_PASSWORD_OVERDUE_WRONG = "110701";
    public static final String RSP_CODE_USER_PASSWORD_OVERDUE_WRONG_DSC = "用户密码过期";
    public static final String RSP_CODE_OTHER = "1108";
    public static final String RSP_CODE_OTHER_DSC = "其他信息";
    public static final String RSP_CODE_ILLEGAL_LOGIN = "1109";
    public static final String RSP_CODE_ILLEGAL_LOGIN_DSC = "非法登陆";
    public static final String RSP_CODE_NO_CONNECTION = "请求失败";
}
