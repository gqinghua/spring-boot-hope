package com.data.hope.curd.controller;

import com.data.hope.bean.ResponseBean;
import com.data.hope.code.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.data.hope.bean.ResponseBean.builder;

/**
 * 通用
 * @author guoqinghua
 * @since 2022-03-01
 */
public abstract class ReportController {

    /**
     * 记录日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 构建成功响应实例
     *
     * @return
     */
    public ResponseBean responseSuccess() {
        return builder().build();
    }


    /**
     * 构建成功响应实例
     *
     * @param code 响应编码
     * @param args 响应参数
     * @return
     */
    public ResponseBean responseSuccess(String code, Object... args) {
        return builder().code(code).args(args).build();
    }

    /**
     * 构建成功响应实例
     *
     * @param data
     * @return
     */
    public  ResponseBean responseSuccessWithData(Object data) {
        return builder().data(data).build();
    }

    /**
     * 构建成功响应实例
     * @param code 响应编码
     * @param data 响应数据
     * @param args 响应参数
     * @return
     */
    public  ResponseBean responseSuccessWithData(String code, Object data, Object... args) {
        return builder().code(code).data(data).args(args).build();
    }

    /**
     * 构建失败响应实例
     *
     * @return
     */
    public ResponseBean failure() {
        return builder().code(ResponseCode.FAIL_CODE).build();
    }

    /**
     * 构建失败响应实例
     *
     * @param code 响应编码
     * @param args 响应参数
     * @return
     */
    public ResponseBean failure(String code, Object... args) {
        return builder().code(code).args(args).build();
    }

    /**
     * 构建失败响应实例,包含响应数据
     * @param code 响应编码
     * @param data 响应数据
     * @param args 响应参数
     * @return
     */
    public ResponseBean failureWithData(String code, Object data, Object... args) {
        return builder().code(code).args(args).data(data).build();
    }

    /**
     * 构建失败响应实例
     * @param code
     * @param
     * @param message
     * @param args
     * @return
     */
    public ResponseBean failureWithMessage(String code,  String message,Object... args) {
        return builder().code(code).args(args).message(message).build();
    }
}
