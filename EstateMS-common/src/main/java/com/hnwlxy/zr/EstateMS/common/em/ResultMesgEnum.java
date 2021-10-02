package com.hnwlxy.zr.EstateMS.common.em;

/*
 * @title:<h3> 错误消息枚举类 <h3>
 * @author: Zr
 * @date: 2021/1/13  14:38
 * @params
 * @return
 **/
public enum ResultMesgEnum {
    SUCCESS(200,"SUCCESS","成功"),
    ERROR(201,"error","错误"),

    SYS_ERROR(1001,"system_error", "系统异常，请联系管理员！"),
    NETWORK(1002, "network_error","网络异常"),
    ENCRYPTION(1003,"encryption_error", "加密异常"),
    RESUBMIT(1004,"resubmit", "【请勿重复提交请求】"),
    NOT_TOKEN(1024, "not_token","【Token不能为空】"),

    NO_LOGIN(1005,"not_login", "未登录或会话超时，请重新登录！"),
    NO_SEARCH(1006,"not_search", "无查询权限"),
    NO_PERMISSIONS(1007,"not_permissions" ,"对不起，您无  {module} {type} 权限，不能进行此操作！"),

    LOGIN_SECONDARY(1008, "login_secondarea","您的账户在异地登录,如不是本人操作,请及时修改密码！"),
    LOGIN_NULL_USER(1009, "login_null_user","帐号或密码不能为空"),
    LOGIN_USER(1010, "login_user_error","帐号或密码错误"),
    LOGIN_ACCOUNT(1011,"account_no_exist", "帐号不存在"),
    LOGIN_INACTIVE(1012, "account_inactive","帐号未激活，请联系管理员"),
    LOGIN_PASSWORD(1013, "login_password_error","密码错误或加密异常"),
    ACCOUT_LOCK(1014, "account_lock","30分钟内连续5次登录错误，您的帐号暂时被锁定"),
    ACCOUT_ERROR(1014, "account_error","账号异常，请联系管理员"),

    SQL_ERROR(1015, "sql_error","SQL异常，请修改SQL语句后重试！"),
    QUERY_ERROR(1016, "query_error","查询条件异常，请修改查询条件后重试！"),
    QUERY_ERROR_TIME(1017,"query_error_time", "时间查询条件异常不支持此关系查询！"),
    UPDATE_VES(1018, "update_ves","更新失败，操作的记录已被更新"),
    UPDATE_NULL(1019,"update_null", "【更新失败，字段不允许为空】"),
    UPDATE_PK(1020,"pk_error", "主键未标记"),
    ADD(1021, "add_error","新增数据异常"),
    UPDATE(1022, "update_error","修改数据异常"),

    SUBMIR_PARAMS(1025,"params_error","非法操作，请求参数异常")
    ,DELETE(1023, "delete_error","删除数据异常");

    private int code;
    private String msgCode;
    private String mesg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    ResultMesgEnum(int code, String msgCode, String mesg) {
        this.code = code;
        this.msgCode=msgCode;
        this.mesg = mesg;
    }
}
