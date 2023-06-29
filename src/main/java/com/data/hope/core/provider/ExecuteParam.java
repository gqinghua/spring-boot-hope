/*
 * Datart
 * <p>
 * Copyright 2021
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.data.hope.core.provider;

import com.alibaba.fastjson.JSON;
import com.data.hope.core.base.PageInfo;
import com.data.hope.core.provider.sql.*;
import com.sg.hope.core.provider.sql.*;
import com.sg.nr.core.provider.sql.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteParam implements Serializable {

    /**
     * 键值
     */
    @ApiModelProperty(value = "键值")
    private List<SelectKeyword> keywords;

    /**
     * 字段
     */
    private List<SelectColumn> columns;

    /**
     * 计算
     */
    @ApiModelProperty(value = "计算")
    private List<AggregateOperator> aggregators;

    /**
     * 过滤
     */
    @ApiModelProperty(value = "过滤")
    private List<FilterOperator> filters;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    private List<GroupByOperator> groups;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private List<OrderOperator> orders;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private List<FunctionColumn> functionColumns;

    private Set<SelectColumn> includeColumns;

    /**
     * 分页
     */
    @ApiModelProperty(value = "分页")
    private PageInfo pageInfo;

    /**
     * 统计  分页信息是否统计
     */
    @ApiModelProperty(value = "统计")
    private boolean serverAggregate;

    /**
     * 开启
     */
    @ApiModelProperty(value = "开启")
    private boolean concurrencyOptimize;

    /**
     * 缓存
     */
    @ApiModelProperty(value = "缓存")
    private boolean cacheEnable;

    /**
     * 缓存时间
     */
    @ApiModelProperty(value = "缓存时间")
    private int cacheExpires;


    @Override
    public String toString() {
        return JSON.toJSONString(JSON.toJSONString(this));
    }

    public static ExecuteParam empty() {
        ExecuteParam executeParam = new ExecuteParam();
        executeParam.setPageInfo(PageInfo.builder().pageNo(1).pageSize(Integer.MAX_VALUE).build());
        return executeParam;
    }

}