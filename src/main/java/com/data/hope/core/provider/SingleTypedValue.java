

package com.data.hope.core.provider;

import com.data.hope.core.base.consts.ValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SingleTypedValue extends TypedValue {

    private Object value;

    private String format;

    public SingleTypedValue(Object value, ValueType valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return "TypedValue{" +
                "value=" + value +
                ", valueType=" + valueType +
                '}';
    }

}
