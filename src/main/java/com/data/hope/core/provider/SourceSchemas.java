package com.data.hope.core.provider;

import com.data.hope.curd.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: report
 * @ClassName SourceSchemas
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-08-09 16:01
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SourceSchemas extends BaseEntity {
    private String id;

    private String sourceId;

    private String schemas;

    private Date updateTime;
}