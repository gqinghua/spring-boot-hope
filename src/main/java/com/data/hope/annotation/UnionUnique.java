package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * 多字段不能重复
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnionUnique {

  /**
   * 列
   * @return
   */
  String column() default "";

  /**
   * 组,必填
   * @return
   */
  String group();
}
