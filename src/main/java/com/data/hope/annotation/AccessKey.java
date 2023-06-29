package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * 验证数据的唯一性
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessKey {

  /**
   * 获取accessKey值的字段
   * @return
   */
  String key() default "id";
}
