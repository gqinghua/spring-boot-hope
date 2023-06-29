

package com.data.hope.core.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Application implements ApplicationContextAware {

    private static ApplicationContext context;


    private static Boolean initialized;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        Application.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }

    public static <T> T getBean(String beanName, Class<T> t) {
        return context.getBean(beanName, t);
    }

    public static String getProperty(String key) {
        return context.getEnvironment().getProperty(key);
    }

    public static String getProperty(String key, String defaultVal) {
        return context.getEnvironment().getProperty(key, defaultVal);
    }

    public static String getFileBasePath() {
        String path = getProperty("report.env.file-path");
        if (path.startsWith(".")) {
            path = path.replace(".", userDir());
        }
        return StringUtils.appendIfMissing(path, "/");
//        return "D:\\Projects\\kanban/files";
    }

    public static String userDir() {
        return StringUtils.removeEnd(System.getProperty("user.dir"), "/");
    }


    public static String getWebRootURL() {
        String url = getProperty("report.server.address");
        url = StringUtils.removeEnd(url, "/");
        return url;
    }

    public static String getApiPrefix() {
        return getProperty("report.path-prefix");
    }

    public static String getTokenSecret() {
        return getProperty("token.secret", "d@a$t%a^r&a*taa**");
    }

    public static boolean canRegister() {
        return BooleanUtils.toBoolean(getProperty("datart.user.register", "true"));
    }



    public static String getServerPrefix() {
        return getProperty("server.servlet.context-path","/");
    }


}
