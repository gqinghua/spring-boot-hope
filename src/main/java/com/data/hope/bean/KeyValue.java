package com.data.hope.bean;

import java.io.Serializable;

/**
 * 前端下拉键值对
 * @author guoqinghua
 * @since 2022-03-01
 *
 **/
public class KeyValue implements Serializable {

    private Object id;
    private String text;
    private Object extend;
    public KeyValue() {}

    public KeyValue(Object id, String text) {
        this.id = id;
        this.text = text;
    }

    public KeyValue(Object id, String text, Object extend) {
        this.id = id;
        this.text = text;
        this.extend = extend;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
