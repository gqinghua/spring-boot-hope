package com.data.hope.constant;

/**
 * 全局变量
 * @author guoqinghua
 * @since 2022-03-01
 */
public interface ReportConstant {

  /**
   * 用户
   */
  String USER_NAME = "loginName";

  /**
   * 超管用户
   */
  String SUPER_USER_NAME = "admin";


  /**
   * 超级管理员角色
   */
  String SUPER_ADMIN_ROLE = "superAdmin";

  /**
   * 属性前缀
   */
  String COMPONENT_PREFIX = "spring.nr.subscribes.";

  /**
   * 内置数据库配置信息
   */
  String COMPONENT_PREFIX_DATA = "spring.datalocal";


  /**
   * 数据库配置信息
   */
  String DATASOURCE_PREFIX_DATA = "spring.druid";


  /**
   * 内置数据库配置信息
   */
  String COMPONENT_PREFIX_DRUID = "spring.datalocal";

  /**
   * 系统分隔符
   */
  String SPLIT = ",";

  /**
   * 下划线
   */
  String UNDERLINE = "_";

  /**
   * 请求头
   */
  String Authorization = "Authorization";

  /**
   * 机构编码
   */
  String ORG_CODE = "orgCode";

  /**
   * 终端类型，web还是移动端
   */
  String SYS_CODE = "systemCode";


  /**
   * redis分割
   */
  String REDIS_SPLIT = ":";

  /**
   * 国际化
   */
  String LOCALE = "locale";

  /**
   * 字符集.
   */
  String CHARSET_UTF8 = "UTF-8";

  /**
   * 表ID
   */
  String ID = "id";


  /**
   * 日期格式
   */
  String DATE_PATTERN = "yyyy-MM-dd";

  /**
   * 时间格式
   */
  String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * 降序
   */
  String DESC = "DESC";

  /**
   * 升序
   */
  String ASC = "ASC";

  /**
   * 空格匹配符
   */
  String BLANK_REPLACE = "\\s+";

  /**
   * 空格符
   */
  String BLANK = "";


  /**
   * 分隔符
   */
  String URL_SPLIT = "#";

  /**
   * 正则
   */
  String URL_REGEX = "\\{\\w+\\}";


  /**
   * 需要替换的请求标识
   */
  String URL_MARK = "{";


  /**
   * 需要替换的请求标识
   */
  String URL_PATTERN_MARK = "${";


  /**
   * 替换的元素
   */
  String URL_REPLACEMENT = "**";

  /**
   * 请求头
   */
  String SOURCE_IP="sourceIp";


  /**
   * 百分比符号
   */
  String PERCENT_SIGN = "%";

  /**
   * 星号
   */
  String PATTERN_SIGN = "*";

}
