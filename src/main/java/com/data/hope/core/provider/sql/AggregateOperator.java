package com.data.hope.core.provider.sql;
import java.io.Serializable;


/**
 * @Classname AggregateOperator
 * @Description 聚合计算模型
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public class AggregateOperator extends ColumnOperator implements Alias {

    /**
     * 计算类型
     */
    private AggregateOperator.SqlOperator sqlOperator;
    /**
     * 别名
     */
    private String alias;

    @Override
    public String toString() {
        return "AggregateOperator{sqlOperator=" + this.sqlOperator + ", column='" + this.getColumnKey() + '\'' + '}';
    }

    public AggregateOperator() {
    }

    public AggregateOperator.SqlOperator getSqlOperator() {
        return this.sqlOperator;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    public void setSqlOperator(final AggregateOperator.SqlOperator sqlOperator) {
        this.sqlOperator = sqlOperator;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }


    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = super.hashCode();
        Object $sqlOperator = this.getSqlOperator();
        result = result * 59 + ($sqlOperator == null ? 43 : $sqlOperator.hashCode());
        Object $alias = this.getAlias();
        result = result * 59 + ($alias == null ? 43 : $alias.hashCode());
        return result;
    }

    public static enum SqlOperator implements Serializable {
        MIN,
        AVG,
        MAX,
        SUM,
        COUNT,
        COUNT_DISTINCT,
        ABS,
        SQRT,
        MOD,
        FLOOR,
        RAND,
        ROUND,
        SIN,
        ASIN,
        COS,
        ACOS,
        TAN,
        ATAN,
        COT,
        POWER,
        CEIL;

        private SqlOperator() {
        }
    }
}
