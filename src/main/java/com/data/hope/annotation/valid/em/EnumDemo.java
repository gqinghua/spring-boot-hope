package com.data.hope.annotation.valid.em;

/**
 * @author guoqinghua
 * @since 2022-03-01
 */
public enum EnumDemo implements EnumInterface<Integer>{
    RED(1),GREEN(2);

    private Integer value;

    EnumDemo(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


    @Override
    public Boolean exist(Integer value) {
        return this.value.equals(value);
    }}
