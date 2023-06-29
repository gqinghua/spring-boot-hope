package com.data.hope.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据源信息
 * @author guoqinghua
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Source extends BaseEntity {
    private String name;

    private String config;

    private String type;

    private String orgId;

    private Integer status;
}