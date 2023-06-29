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
public @interface Permissions {

  /**
   * 权限
   * @return
   */
  Permission[] value();
}
