package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * 聚合索引，可重复添加
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnionUniqueCodes {

  /**
   * 列
   * @return
   */
  UnionUniqueCode[] value();
}
