
package com.data.hope.core.base.consts;

public enum FileFormat {

    XLS(".xls"),

    XLSX(".xlsx"),

    CSV(".csv"),

    PNG(".png");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
