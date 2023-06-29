package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * 缓存，将对应key,value加入缓存
 * @author guoqinghua
 * @since 2022-03-01
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HashKey {

  /**
   * 缓存对应的key
   */
  String key();

  /**
   * 当key中存在${}占位符时，需要此注解
   * @return
   */
  String[] replace() default {};
}
