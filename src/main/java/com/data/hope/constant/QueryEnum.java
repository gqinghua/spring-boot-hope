package com.data.hope.constant;

/**
 * 查询条件
 * @author guoqinghua
 * @since 2022-03-01
 */
public enum QueryEnum {
    /**
     * 查询条件为相等
     */
    EQ,

    /**
     * 查询条件为like
     */
    LIKE,

    /**
     * 查询条件大于
     */
    GT,

    /**
     * 查询条件大于等于
     */
    GE,

    /**
     * 查询条件小于
     */
    LT,

    /**
     * 查询条件小于等于
     */
    LE,

    /**
     * 数据库IN
     */
    IN,

    /**
     *
     */
    BWT
}
