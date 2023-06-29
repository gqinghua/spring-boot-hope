package com.data.hope.bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.data.hope.code.ResponseCode;
import com.data.hope.core.common.JacksonSerializer;

import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 响应体封装
 * @author guoqinghua
 * @since 2022-03-01
 */
public final class ResponseBean<T> implements Serializable {

    public ResponseBean() {

    }

    /**
     * 构建线程池
     */
    private transient ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1),
            r -> new Thread(r,"ResponseBean.then.executor"));
    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应参数
     */
    private Object[] args;

    /**
     * 响应数据
     */
    private T data;





    public ResponseBean(Builder builder) {
        this.code = builder.code;
        this.args = builder.args;
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public ResponseBean(String code, String message,
                        Object[] args, T data) {
        this.code = code;
        this.message = message;
        this.args = args;
        this.data = data;
    }

    /**
     * 后续异步处理
     * 请求参数param
     *
     * @param consumer
     * @return
     */
    public ResponseBean thenAsync(Consumer<Object> consumer, Object param) {
        //异步执行
        executor.execute(() -> {
            consumer.accept(param);
        });
        return this;
    }

    /**
     * 后续同步处理
     * 请求参数param
     *
     * @param consumer
     * @return
     */
    public ResponseBean then(Consumer<Object> consumer, Object param) {
        //同步执行
        consumer.accept(param);
        return this;
    }

    /**
     * 用于创建ResponseBean实例
     *
     * @return
     */
    public static  Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {
        private String code = ResponseCode.SUCCESS_CODE;
        private T data;
        private String message=ResponseCode.SUCCESS_CODE_NAME;
        private Object[] args;
        @JsonInclude()
        private boolean success;
        @JsonSerialize(using = JacksonSerializer.ExceptionSerialize.class)
        private Exception exception;

        private Builder() {

        }

        public Builder exception(Exception exception) {
            this.exception = exception;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder args(Object[] args) {
            this.args = args;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public ResponseBean build() {
            return new ResponseBean(this);
        }
        @Override
        public String toString() {
            return "ResponseData.ResponseDataBuilder(success=" + this.success +  ", message=" + this.message  + ", data=" + this.data + ", exception=" + this.exception + ")";
        }

    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }




    public static <E> ResponseBean<E> success(E data) {
        return (ResponseBean<E>) ResponseBean.builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <E> ResponseBean<E> failure(String message) {
        return (ResponseBean<E>) ResponseBean.builder()
                .success(false)
                .message(message)
                .build();
    }
}
