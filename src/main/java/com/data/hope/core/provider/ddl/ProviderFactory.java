package com.data.hope.core.provider.ddl;


import com.data.hope.constant.DatasourceTypes;
import com.data.hope.core.common.Application;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderFactory {


    public static DDLProvider getDDLProvider(String type) {
        DatasourceTypes datasourceType = DatasourceTypes.valueOf(type);
        switch (datasourceType) {
            case engine_doris:
                return Application.getBean("dorisEngineDDL", DDLProvider.class);
            case engine_mysql:
                return Application.getBean("mysqlEngineDDL", DDLProvider.class);
            default:
                return Application.getBean("mysqlEngineDDL", DDLProvider.class);
        }
    }

}
