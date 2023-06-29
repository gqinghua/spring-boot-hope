package com.data.hope.annotation.enabled;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 组件装配
 * @author guoqinghua
 * @since 2022-03-01
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ReportConfigurationImportSelector.class)
public @interface EnabledReportConfiguration {
}
