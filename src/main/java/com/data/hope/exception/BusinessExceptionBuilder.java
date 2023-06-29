package com.data.hope.exception;

/**
 * 异常构建类
 * @author guoqinghua
 * @since 2021-01-08 10:29
 */
public class BusinessExceptionBuilder {

    /**
     * 构建code
     * @param code 异常码
     * @return
     */
    public static BusinessException build(String code){
        return new BusinessException(code);
    }

    /**
     * 构建code和args
     * @param code 异常码
     * @param args 异常参数
     * @return
     */
    public static BusinessException build(String code, String message, Object[] args){
        return new BusinessException(code,message,args);
    }

    /**
     * 构建code和args
     * @param code 异常码
     * @param message 异常参数
     * @return
     */
    public static BusinessException build(String code, String message){
        return new BusinessException(code,message);
    }

    /**
     * 构建code和args
     * @param code 异常码
     * @param args 异常参数
     * @return
     */
    public static BusinessException build(String code ,Object... args){
        return new BusinessException(code, args);
    }



}
