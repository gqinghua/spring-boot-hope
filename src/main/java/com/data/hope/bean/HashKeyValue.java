package com.data.hope.bean;

import com.data.hope.annotation.HashKey;

import java.io.Serializable;

/**
 * hash键值对
 * @author guoqinghua
 * @since 2022-03-01
 *
 **/
public class HashKeyValue implements Serializable {

    private String key;
    private String value;

    private HashKey hashKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashKey getHashKey() {
        return hashKey;
    }

    public void setHashKey(HashKey hashKey) {
        this.hashKey = hashKey;
    }
}
