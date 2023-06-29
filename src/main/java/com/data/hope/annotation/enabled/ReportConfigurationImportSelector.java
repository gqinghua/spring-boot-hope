package com.data.hope.annotation.enabled;

import com.data.hope.annotation.condition.ConditionalOnReportComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guoqinghua
 * @since 2022-03-01
 */
public class ReportConfigurationImportSelector implements ImportSelector , EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(ReportConfigurationImportSelector.class);

    /**
     * 应用订阅的组件
     */

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> list =
                SpringFactoriesLoader.loadFactoryNames(EnabledReportConfiguration.class, ReportConfigurationImportSelector.class.getClassLoader());
        //没有任何组件
        if (CollectionUtils.isEmpty(list)) {
            return new String[0];
        }

        List<String> importAutoConfigurations = list.stream().filter(className -> {
            try {
                Class<?> reportExtensionClass = Class.forName(className);
                return reportExtensionClass.isAnnotationPresent(ConditionalOnReportComponent.class);
            } catch (ClassNotFoundException e) {
                return false;
            }
        }).collect(Collectors.toList());

        logger.info("装载的组件：{}", importAutoConfigurations);

        return importAutoConfigurations.toArray(new String[0]);
    }
}
