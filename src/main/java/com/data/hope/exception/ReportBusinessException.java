package com.data.hope.exception;

/**
 * @Description: ReportBusinessException自定义异常
 * @author: ReportBusinessException自定义异常
 */
public class ReportBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReportBusinessException(String message) {
        super(message);
    }

    public ReportBusinessException(Throwable cause) {
        super(cause);
    }

    public ReportBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
