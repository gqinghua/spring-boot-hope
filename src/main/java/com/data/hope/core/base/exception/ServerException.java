

package com.data.hope.core.base.exception;

public class ServerException extends BaseException {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, int errCode) {
        super(message);
        setErrCode(errCode);
    }

    public ServerException() {
        super();
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
