package com.data.hope.constant;

import com.data.hope.annotation.valid.em.EnumInterface;

/**
 * 是否标识即0,1
 * @author guoqinghua
 * @since 2022-03-01
 */
public enum Enabled implements EnumInterface<Integer> {

    YES(1),NO(0);

    private Integer value;

    Enabled(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


    @Override
    public Boolean exist(Integer value) {
        return this.getValue().equals(value);
    }}
