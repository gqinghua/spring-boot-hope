package com.data.hope.curd.params;

/**
 * 分页参数
 * @author guoqinghua
 * @since 2022-03-01
 */
public class PageParam {

    /**
     * 页数
     */
    private Integer pageNum = 1;

    /**
     * 每页的记录数
     */
    private Integer pageSize = 10;

    /**
     * 升序还是降序
     */
    private String order;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 偏移量
     */
    private Integer offset;

    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
