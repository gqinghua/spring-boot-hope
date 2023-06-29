package com.data.hope.bean;


import com.data.hope.core.base.PageInfo;

import java.util.List;

/**
 * @program: report
 * @ClassName ResponseData
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-08-20 14:31
 **/
public class ResponseData<T> {

    private boolean success;

    private int errCode;

    private String message;

    private T data;
    private PageInfo pageInfo;

    public static <E> ResponseData<E> success(E data) {
        return (ResponseData<E>) builder().data(data).success(true).build();
    }

    public static <E> ResponseData<E> failure(String message) {
        return (ResponseData<E>) builder().success(false).message(message).build();
    }

    public static <T> ResponseData.ResponseDataBuilder<T> builder() {
        return new ResponseData.ResponseDataBuilder();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getMessage() {
        return this.message;
    }


    public T getData() {
        return this.data;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }


    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setErrCode(final int errCode) {
        this.errCode = errCode;
    }

    public void setMessage(final String message) {
        this.message = message;
    }



    public void setData(final T data) {
        this.data = data;
    }

    public void setPageInfo(final PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }




    protected boolean canEqual(final Object other) {
        return other instanceof ResponseData;
    }




    public ResponseData(final boolean success, final int errCode, final String message, final List<String> warnings, final T data, final PageInfo pageInfo, final Exception exception) {
        this.success = success;
        this.errCode = errCode;
        this.message = message;
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public ResponseData() {
    }

    public static class ResponseDataBuilder<T> {
        private boolean success;
        private int errCode;
        private String message;
        private List<String> warnings;
        private T data;
        private PageInfo pageInfo;
        private Exception exception;

        ResponseDataBuilder() {
        }

        public ResponseData.ResponseDataBuilder<T> success(final boolean success) {
            this.success = success;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> errCode(final int errCode) {
            this.errCode = errCode;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> warnings(final List<String> warnings) {
            this.warnings = warnings;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> pageInfo(final PageInfo pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        public ResponseData.ResponseDataBuilder<T> exception(final Exception exception) {
            this.exception = exception;
            return this;
        }

        public ResponseData<T> build() {
            return new ResponseData(this.success, this.errCode, this.message, this.warnings, this.data, this.pageInfo, this.exception);
        }

    }
}
