package com.data.hope.constant;

/**
 * 缓存key
 * @author guoqinghua
 * @since 2022-03-01
 */
public interface ReportKeyConstant {

  /**
   * 数据字典前缀
   */
  String DICT_PREFIX = "report:dict:prefix:";

  /**
   * 系统登录token
   */
  String USER_LOGIN_TOKEN = "system:login:token:";

  /**
   * 用户名与真实姓名的对应关系
   */
  String USER_NICKNAME_KEY = "report:user:nickname:${orgCode}";

  /**
   * 机构、路由与角色的缓存
   */
  String HASH_URL_ROLE_KEY = "report:security:url:roles:";


  /**
   * 用户名与角色对应关系的前缀
   */
  String USER_ROLE_SET_PREFIX = "report:security:user:roles:";

  /**
   * 所有权限的映射的前缀，带应用名称的即区分不同应用
   */
  String AUTHORITY_ALL_MAP_PREFIX_WITH_APP = "report:security:authorities:all:";

  /**
   * 所有权限的映射的前缀，不区分应用，所有放一起，可以用重复
   */
  String AUTHORITY_ALL_MAP_PREFIX = "report:security:authorities:all";
}
