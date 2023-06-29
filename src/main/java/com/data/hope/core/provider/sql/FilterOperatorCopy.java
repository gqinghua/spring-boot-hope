

package com.data.hope.core.provider.sql;

import lombok.Data;

/**过滤信息
 * @author 92306
 */
@Data
public class FilterOperatorCopy implements Operator {

    /**
     * 过滤字段
     */
    private String column;

    /**
     * 过滤条件
     */
    private String aggOperator;



    /**
     * 类型
     */
    private String values;


}