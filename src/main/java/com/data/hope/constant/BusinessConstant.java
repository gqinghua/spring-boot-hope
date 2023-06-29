package com.data.hope.constant;

/**
 * 常量
 * @author guoqinghua
 * @since 2021-03-26
 */
public interface BusinessConstant {

    String LEFT_BIG_BOAST = "{";
    String RIGTH_BIG_BOAST = "}";
    String LEFT_MIDDLE_BOAST = "[";
    String RIGHT_MIDDLE_BOAST = "]";
    String SLASH = "/";
    String USER_ADMIN = "admin";

    /**
     * 字典项重复
     */
    String DICT_ITEM_EXIST_GROUP = "dictItemExist";

    /**
     * 所有url的权限码缓存key
     */
    String REPORT_SECURITY_AUTHORITIES= "Report:security:authorities:all";

    /**
     * 用户登录的token缓存key
     */
    String REPORT_SECURITY_LOGIN_TOKEN = "Report:security:login:token:%s";

    /**
     * 用户登录的主信息缓存信息
     */
    String REPORT_SECURITY_LOGIN_USER = "Report:security:login:user:%s";

    /**
     * 用户登录的主信息缓存信息
     */
    String REPORT_SEND_SMS = "Report:send:sms:indexname:%s";
}
