
package com.data.hope.core.provider;

import com.data.hope.core.base.consts.ValueType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/** 数据类型枚举值
 * @author 92306
 */
@ApiModel("数据类型枚举值")
public class TypedValue implements Serializable {

    @ApiModelProperty("类型")
    protected ValueType valueType;

    public TypedValue() {
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public void setValueType(final ValueType valueType) {
        this.valueType = valueType;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof TypedValue;
    }


    @Override
    public String toString() {
        return "TypedValue(valueType=" + this.getValueType() + ")";
    }
}
