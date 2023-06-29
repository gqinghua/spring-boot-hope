package com.data.hope.encryption;


import com.data.hope.utils.JasyptUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * 配置文件关键字段加密
 * @author
 * @since
 */
public class EncryptionEnvironmentPostProcessor implements EnvironmentPostProcessor {



    /**
     * dataSourceProperties对应的用户名
     */
    private String username = "spring.datasource.username";

    /**
     * dataSourceProperties对应的密码
     */
    private String password = "spring.datasource.password";

    /**
     * 加密方法
     * @param environment
     * @param application
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //加密私钥
//        String secret = Propertiesties.getSecret();
        String secret = "nr";

        //加密的用户名
        String propertyUsername = environment.getProperty(username);
        //加密的密码
        String propertyPassword = environment.getProperty(password);

        if (StringUtils.isBlank(propertyUsername) || StringUtils.isBlank(propertyPassword)) {
            return;
        }

        Properties properties = new Properties();

        properties.put(username, JasyptUtils.decrypt(propertyUsername, secret));
        properties.put(password,JasyptUtils.decrypt(propertyPassword, secret));

        //放在最前面
        environment.getPropertySources().addFirst(new PropertiesPropertySource("ReportProperties",properties));
    }
}
