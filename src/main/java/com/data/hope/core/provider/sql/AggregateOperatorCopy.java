

package com.data.hope.core.provider.sql;


import lombok.Data;

/**
 * 计算信息
 *
 * @author 92306
 */
@Data
public class AggregateOperatorCopy implements Operator {

    /**
     * 计算信息
     */
    private String sqlOperator;

    /**
     * 字段名
     */
    private String column;

    /**
     * 計算別名
     */
    private String alias;





}
