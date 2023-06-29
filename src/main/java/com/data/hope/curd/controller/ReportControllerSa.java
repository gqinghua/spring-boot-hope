package com.data.hope.curd.controller;

import com.data.hope.bean.ResponseBeanSa;
import com.data.hope.code.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.data.hope.bean.ResponseBeanSa.builder;

/**
 * 通用
 * @author guoqinghua
 * @since 2022-03-01
 */
public abstract class ReportControllerSa {

    /**
     * 记录日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 构建成功响应实例
     *
     * @return
     */
    public ResponseBeanSa responseSuccess() {
        return builder().build();
    }


    /**
     * 构建成功响应实例
     *
     * @param code 响应编码
     * @param args 响应参数
     * @return
     */
    public ResponseBeanSa responseSuccess(String code, Object... args) {
        return builder().status(code).args(args).build();
    }

    /**
     * 构建成功响应实例
     *
     * @param data
     * @return
     */
    public ResponseBeanSa responseSuccessWithData(Object data) {
        return builder().data(data).build();
    }

    /**
     * 构建成功响应实例
     * @param code 响应编码
     * @param data 响应数据
     * @param args 响应参数
     * @return
     */
    public ResponseBeanSa responseSuccessWithData(String code, Object data, Object... args) {
        return builder().status(code).data(data).args(args).build();
    }

    /**
     * 构建失败响应实例
     *
     * @return
     */
    public ResponseBeanSa failure() {
        return builder().status(ResponseCode.FAIL_CODE).build();
    }

    /**
     * 构建失败响应实例
     *
     * @param code 响应编码
     * @param args 响应参数
     * @return
     */
    public ResponseBeanSa failure(String code, Object... args) {
        return builder().status(code).args(args).build();
    }

    /**
     * 构建失败响应实例,包含响应数据
     * @param code 响应编码
     * @param data 响应数据
     * @param args 响应参数
     * @return
     */
    public ResponseBeanSa failureWithData(String code, Object data, Object... args) {
        return builder().status(code).args(args).data(data).build();
    }

}
