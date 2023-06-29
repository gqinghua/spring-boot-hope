package com.data.hope.curd.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.data.hope.ReportUtils;
import com.data.hope.annotation.Formatter;
import com.data.hope.constant.ReportKeyConstant;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 基础传输对象
 * @author guoqinghua
 * @since 2022-03-01
 */
public class ReportBaseSaDTO extends BaseDTO{

    private String id;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @Formatter(key = ReportKeyConstant.USER_NICKNAME_KEY, replace = {"orgCode"},targetField = "createByView")
    private String createBy;

    /**
     * 前端展示
     */
    @ApiModelProperty(value = "前端展示")
    private String createByView;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @Formatter(key = ReportKeyConstant.USER_NICKNAME_KEY, replace = {"orgCode"},targetField = "updateByView")
    @ApiModelProperty(value = "修改人")
    private String updateBy;


    /**
     * 前端展示
     */
    @ApiModelProperty(value = "前端展示")
    private String updateByView;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;



    /**
     * accessKey用于校验单条记录权限，防止越权
     * @return
     */
    public String getAccessKey() {
        if (id == null) {
            return null;
        }
        return ReportUtils.getPassKey(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateByView() {
        return createByView;
    }

    public void setCreateByView(String createByView) {
        this.createByView = createByView;
    }

    public String getUpdateByView() {
        return updateByView;
    }

    public void setUpdateByView(String updateByView) {
        this.updateByView = updateByView;
    }
}
