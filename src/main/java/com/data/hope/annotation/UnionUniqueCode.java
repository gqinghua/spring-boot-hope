package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * 多字段错误提示
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UnionUniqueCodes.class)
@Documented
public @interface UnionUniqueCode {

  /**
   * 组,必填
   * @return
   */
  String group();

  /**
   * 错误码，用于确定当出现重复时提示信息
   * @return
   */
  String code();
}
