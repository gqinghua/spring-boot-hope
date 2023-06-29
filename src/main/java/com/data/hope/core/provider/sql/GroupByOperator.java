package com.data.hope.core.provider.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupByOperator extends ColumnOperator implements Alias {

    private String alias;

    @Override
    public String toString() {
        return "GroupByOperator{" +
                "column='" + getColumnKey() + '\'' +
                '}';
    }
}
