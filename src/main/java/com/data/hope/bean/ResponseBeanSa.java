package com.data.hope.bean;



import com.data.hope.code.ResponseCodeSa;

import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 响应体封装
 * @author guoqinghua
 * @since 2022-03-01
 *
 *
 */
public final class ResponseBeanSa implements Serializable {

    public ResponseBeanSa() {

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
    private String status;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应参数
     */
    private Object[] args;

    /**
     * 响应数据
     */
    private Object data;

    public ResponseBeanSa(Builder builder) {
        this.status = builder.status;
        this.args = builder.args;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    /**
     * 后续异步处理
     * 请求参数param
     *
     * @param consumer
     * @return
     */
    public ResponseBeanSa thenAsync(Consumer<Object> consumer, Object param) {
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
    public ResponseBeanSa then(Consumer<Object> consumer, Object param) {
        //同步执行
        consumer.accept(param);
        return this;
    }

    /**
     * 用于创建ResponseBean实例
     *
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String status = ResponseCodeSa.SUCCESS_CODE_SA;
        private Object data;
        private String msg = ResponseCodeSa.SUCCESS_CODE;
        private Object[] args;

        private Builder() {

        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder args(Object[] args) {
            this.args = args;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ResponseBeanSa build() {
            return new ResponseBeanSa(this);
        }
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
