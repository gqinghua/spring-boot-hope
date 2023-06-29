package com.data.hope.core.common;

/**
 * @Classname Cache
 * @Description 缓存帮助类
 * @Version 1.0.0
 * @Date 2022/9/26
 * @@author by guoqinghua
 */
public interface Cache {

    void put(String key, Object object);

    void put(String key, Object object, int ttl);

    boolean delete(String key);

    <T> T get(String key);

}
