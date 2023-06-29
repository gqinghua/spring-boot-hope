package com.data.hope.annotation;


import java.lang.annotation.*;


/**
 * 配置权限
 * @author guoqinghua
 * @since 2022-03-01
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Permissions.class)
public @interface Permission {

  /**
   * 权限名称
   * @return
   */
  String name() default "";

  /**
   * 权限编号
   * @return
   */
  String code() default "";

  /**
   * 父编码
   * @return
   */
  String superCode() default "";
}
