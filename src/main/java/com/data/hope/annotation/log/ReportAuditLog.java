package com.data.hope.annotation.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志记录注解
 * @author guoqinghua
 * @since 2022-03-01
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReportAuditLog {

  /**
   *页面或按钮标题
   * @return 标题
   */
  String pageTitle() default "";
  /**
   * 是否保存请求的参数和响应参数
   */
  boolean isSaveRequestData() default true;
  boolean isSaveResponseData() default false;

}
