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
public @interface HashValue {

  /**
   * HashValue对应的缓存key
   */
  String key();
}
