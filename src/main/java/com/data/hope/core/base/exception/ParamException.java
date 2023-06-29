

package com.data.hope.core.base.exception;

public class ParamException extends BaseException {

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, int errCode) {
        super(message);
        setErrCode(errCode);
    }

    public ParamException() {
        super();
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
