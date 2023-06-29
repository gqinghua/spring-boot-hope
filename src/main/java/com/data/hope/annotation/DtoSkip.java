package com.data.hope.annotation;

import java.lang.annotation.*;

/**
 * DTO跳过指定字段
 * @author guoqinghua
 * @since 2022-03-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface DtoSkip {
}
