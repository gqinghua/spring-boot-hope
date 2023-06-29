package com.data.hope.annotation;

import com.data.hope.annotation.resolver.format.FormatterEnum;

import java.lang.annotation.*;

/**
 * 字段
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormatterType {

  FormatterEnum type() default FormatterEnum.OBJECT;
}
