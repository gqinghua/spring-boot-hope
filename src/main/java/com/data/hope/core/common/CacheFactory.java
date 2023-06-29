package com.data.hope.core.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname CacheFactory
 * @Description 缓存工厂类
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public class CacheFactory {
    private static final Logger log = LoggerFactory.getLogger(CacheFactory.class);

    private static final String CACHE_IMPL_CLASS_NAME = "RedisCacheImpl";

    private static final String DEFAULT_CACHE = "com.sg.nr.utils.RedisCacheImpl";

    private static Cache cache;

    public static Cache getCache() {
        if (cache != null) {
            return cache;
        }
        try {
            String className = Application.getProperty(CACHE_IMPL_CLASS_NAME);
            if (StringUtils.isBlank(className)) {
                className = DEFAULT_CACHE;
            }
            cache = (Cache) Application.getBean(Class.forName(className));
            return cache;
        } catch (Exception e) {
            log.error("get cache instance error", e);
        }
        return null;
    }

}