package com.data.hope.core.provider.sql;

import com.data.hope.core.provider.SingleTypedValue;

import java.util.Arrays;

/**
 * @Classname FilterOperator
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public class FilterOperator extends ColumnOperator {
    private AggregateOperator.SqlOperator aggOperator;
    private FilterOperator.SqlOperator sqlOperator;
    private SingleTypedValue[] values;

    @Override
    public String toString() {
        return "FilterOperator{column='" + this.getColumnKey() + '\'' + ", aggOperator=" + this.aggOperator + ", sqlOperator=" + this.sqlOperator + ", values=" + Arrays.toString(this.values) + '}';
    }

    public FilterOperator() {
    }

    public AggregateOperator.SqlOperator getAggOperator() {
        return this.aggOperator;
    }

    public FilterOperator.SqlOperator getSqlOperator() {
        return this.sqlOperator;
    }

    public SingleTypedValue[] getValues() {
        return this.values;
    }

    public void setAggOperator(final AggregateOperator.SqlOperator aggOperator) {
        this.aggOperator = aggOperator;
    }

    public void setSqlOperator(final FilterOperator.SqlOperator sqlOperator) {
        this.sqlOperator = sqlOperator;
    }

    public void setValues(final SingleTypedValue[] values) {
        this.values = values;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof FilterOperator)) {
            return false;
        } else {
            FilterOperator other = (FilterOperator)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                label41: {
                    Object this$aggOperator = this.getAggOperator();
                    Object other$aggOperator = other.getAggOperator();
                    if (this$aggOperator == null) {
                        if (other$aggOperator == null) {
                            break label41;
                        }
                    } else if (this$aggOperator.equals(other$aggOperator)) {
                        break label41;
                    }

                    return false;
                }

                Object this$sqlOperator = this.getSqlOperator();
                Object other$sqlOperator = other.getSqlOperator();
                if (this$sqlOperator == null) {
                    if (other$sqlOperator != null) {
                        return false;
                    }
                } else if (!this$sqlOperator.equals(other$sqlOperator)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getValues(), other.getValues())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FilterOperator;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = super.hashCode();
        Object $aggOperator = this.getAggOperator();
        result = result * 59 + ($aggOperator == null ? 43 : $aggOperator.hashCode());
        Object $sqlOperator = this.getSqlOperator();
        result = result * 59 + ($sqlOperator == null ? 43 : $sqlOperator.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getValues());
        return result;
    }

    public static enum SqlOperator {
        EQ,
        NE,
        GT,
        LT,
        GTE,
        LTE,
        IN,
        NOT_IN,
        LIKE,
        PREFIX_LIKE,
        SUFFIX_LIKE,
        NOT_LIKE,
        PREFIX_NOT_LIKE,
        SUFFIX_NOT_LIKE,
        IS_NULL,
        NOT_NULL,
        BETWEEN,
        NOT_BETWEEN,
        MAX,
        MIN;

        private SqlOperator() {
        }
    }
}
