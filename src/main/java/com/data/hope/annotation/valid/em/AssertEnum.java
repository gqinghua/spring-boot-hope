package com.data.hope.annotation.valid.em;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 注解
 * @author guoqinghua
 * @since 2022-03-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = {AssertEnumValidator.class})
public @interface AssertEnum {

  String message() default "enum.validation.not.match";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };
  /**
   * 枚举类
   * @return
   */
  Class value() default Object.class;
}
