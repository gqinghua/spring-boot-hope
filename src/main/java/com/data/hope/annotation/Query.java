package com.data.hope.annotation;

import com.data.hope.constant.QueryEnum;

import java.lang.annotation.*;

/**
 * 查询条件注解
 * @author guoqinghua
 * @since 2022-03-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Query {
  /**
   * 查询条件，默认相等
   * @return
   */
  QueryEnum value() default QueryEnum.EQ;

  /**
   * 是否参与where条件，默认是true
   * @return
   */
  boolean where() default true;

  /**
   * 查询字段，当前字段的值取查表中哪个字段
   * @return
   */
  String column() default "";
}
