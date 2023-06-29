
package com.data.hope.core.provider;

import com.data.hope.core.provider.sql.Alias;
import com.data.hope.core.provider.sql.ColumnOperator;
/**
 * sql 别名
 * @author guoqinghua
 * @since 2022-03-01
 */
public class SelectColumn extends ColumnOperator implements Alias {
    private String alias;

    public static SelectColumn of(String alias, String... names) {
        SelectColumn selectColumn = new SelectColumn();
        selectColumn.setAlias(alias);
        selectColumn.setColumn(names[0]);
        return selectColumn;
    }

    public SelectColumn() {
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "SelectColumn(alias=" + this.getAlias() + ")";
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SelectColumn;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = super.hashCode();
        Object $alias = this.getAlias();
        result = result * 59 + ($alias == null ? 43 : $alias.hashCode());
        return result;
    }
}
