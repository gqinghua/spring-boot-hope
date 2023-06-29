package com.data.hope.core.provider.sql;
/**
 * JDBC驱动 标准
 *
 * @author h1823
 * @author gqh
 */

public class FunArg {
    private FunArgType type;
    private Object value;

    public FunArgType getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public void setType(final FunArgType type) {
        this.type = type;
    }

    public void setValue(final Object value) {
        this.value = value;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof FunArg;
    }


    public FunArg(final FunArgType type, final Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FunArg(type=" + this.getType() + ", value=" + this.getValue() + ")";
    }

    public FunArg() {
    }

}
