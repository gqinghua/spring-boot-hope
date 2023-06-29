package com.data.hope.annotation.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 订阅扩展组件
 * @author guoqinghua
 * @since 2022-03-01
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnReportComponentCondition.class)
public @interface ConditionalOnReportComponent {

  /**
   * 激活组件
   * @return
   */
  String value();

}
