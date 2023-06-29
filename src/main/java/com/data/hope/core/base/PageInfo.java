package com.data.hope.core.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页数据 郭大侠加载
 * @author 92306
 */
@ApiModel(value = "键值")
public class PageInfo implements Serializable {
    @ApiModelProperty(value = "分页页数")
    private long pageSize;
    @ApiModelProperty(value = "分页页码")
    private long pageNo;
    @ApiModelProperty(value = "总数")
    private long total;
    @ApiModelProperty(value = "是否要统计分页")
    private boolean countTotal;

    @Override
    public String toString() {
        return "PageInfo{pageSize=" + this.pageSize + ", pageNo=" + this.pageNo + ", total=" + this.total + '}';
    }

    public static PageInfo.PageInfoBuilder builder() {
        return new PageInfo.PageInfoBuilder();
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public long getPageNo() {
        return this.pageNo;
    }

    public long getTotal() {
        return this.total;
    }

    public boolean isCountTotal() {
        return this.countTotal;
    }

    public void setPageSize(final long pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(final long pageNo) {
        this.pageNo = pageNo;
    }

    public void setTotal(final long total) {
        this.total = total;
    }

    public void setCountTotal(final boolean countTotal) {
        this.countTotal = countTotal;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof PageInfo;
    }



    public PageInfo() {
    }

    public PageInfo(final long pageSize, final long pageNo, final long total, final boolean countTotal) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.total = total;
        this.countTotal = countTotal;
    }

    public static class PageInfoBuilder {
        private long pageSize;
        private long pageNo;
        private long total;
        private boolean countTotal;

        PageInfoBuilder() {
        }

        public PageInfo.PageInfoBuilder pageSize(final long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageInfo.PageInfoBuilder pageNo(final long pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public PageInfo.PageInfoBuilder total(final long total) {
            this.total = total;
            return this;
        }

        public PageInfo.PageInfoBuilder countTotal(final boolean countTotal) {
            this.countTotal = countTotal;
            return this;
        }

        public PageInfo build() {
            return new PageInfo(this.pageSize, this.pageNo, this.total, this.countTotal);
        }

        @Override
        public String toString() {
            return "PageInfo.PageInfoBuilder(pageSize=" + this.pageSize + ", pageNo=" + this.pageNo + ", total=" + this.total + ", countTotal=" + this.countTotal + ")";
        }
    }
}
