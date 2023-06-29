

package com.data.hope.core.base.exception;


public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, int errCode) {
        super(message);
        this.setErrCode(errCode);
    }

    public NotFoundException() {
        super();
    }

}
