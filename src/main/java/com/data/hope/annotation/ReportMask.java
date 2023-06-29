package com.data.hope.annotation;

import com.data.hope.annotation.resolver.mask.ReportMaskJsonSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.data.hope.annotation.resolver.mask.MaskEnum;

import java.lang.annotation.*;

/**
 * 数据脱敏
 * 注意：left + right 不能大于等于对应脱敏的值长度，否则会采用默认脱敏规则即 left =1, right = 1
 * @author guoqinghua
 * @since 2022-03-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(using = ReportMaskJsonSerialize.class)
public @interface ReportMask {

  /**
   * 脱敏类型，默认通用，需要跟后面的配置
   * 当时默认时要指定left、hidden、right
   * @return
   */
  MaskEnum type() default MaskEnum.COMMON;

  /**
   * 左边显示几位，type必须为MaskEnum.COMMON，才生效
   * @return
   */
  int left() default 0;


  /**
   * 右边显示几位，type必须为MaskEnum.COMMON，才生效
   * @return
   */
  int right() default 0;
}
