
package com.data.hope.core.provider;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 数据源入参
 * @author 92306
 */
@ApiModel(value = "数据源入参")
public class DataProviderSource {

    /**
     * 数据源id
     */
    @ApiModelProperty(value = "数据源id")
    private String sourceId;
    /**
     * 类型
     */
    @ApiModelProperty(value = "数据源id")
    private String type;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 配置文件
     */
    @ApiModelProperty(value = "配置文件")
    private Map<String, Object> properties;

    private List<ScriptVariable> variables;

    public List<ScriptVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<ScriptVariable> variables) {
        this.variables = variables;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}