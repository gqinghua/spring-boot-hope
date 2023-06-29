

package com.data.hope.core.base.exception;

import lombok.Getter;
import lombok.Setter;

public class BaseException extends RuntimeException {

    @Getter
    @Setter
    private int errCode;

    public BaseException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
