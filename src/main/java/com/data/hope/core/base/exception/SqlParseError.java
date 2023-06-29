

package com.data.hope.core.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SqlParseError extends BaseException {

    public SqlParseError(Throwable e) {
        super(e);
    }

    private String sql;

    private String dbType;

    @Override
    public String toString() {
        return "SQL:" + sql + " \r\n" +
                "DB: " + dbType + " \r\n" +
                "EXCEPTION:" + getMessage();
    }

}
