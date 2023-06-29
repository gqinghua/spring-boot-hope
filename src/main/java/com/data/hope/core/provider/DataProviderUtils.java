package com.data.hope.core.provider;

import org.apache.commons.codec.digest.DigestUtils;

/**

/**
 * @Classname DataProviderUtils
 * @Description 唯一值生成
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public class DataProviderUtils {

    public static String toCacheKey(DataProviderSource source, QueryScript queryScript, ExecuteParam param) {
        return DigestUtils.sha512Hex(String.join(":", source.getSourceId(),
                queryScript.getScript(),
                param.toString()));
    }
}
