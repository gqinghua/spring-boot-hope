package com.data.hope.exception;

/**
 * 业务异常封装类
 *
 * @author guoqinghua
 * @since 2022-03-01
 */

public class BusinessException extends RuntimeException {
    /**
     * 异常码
     */
    public String code;

    /**
     * 异常参数
     */
    public Object[] args;


    public String message;

    BusinessException(String code) {
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code, String message, Object[] args) {
        this.code = code;
        this.args = args;
        this.message = message;

    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public BusinessException(String code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        if (args != null) {
            return args.clone();
        } else {
            return null;
        }

    }

    public void setArgs(Object[] args) {
        if (args != null) {
            this.args = args.clone();
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
