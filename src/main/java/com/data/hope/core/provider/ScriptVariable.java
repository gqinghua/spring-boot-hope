

package com.data.hope.core.provider;


import com.data.hope.core.base.consts.Const;
import com.data.hope.core.base.consts.ValueType;
import com.data.hope.core.base.consts.VariableTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * 脚本变量
 *
 * @author 92306
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "脚本变量")
public class ScriptVariable extends TypedValue {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private VariableTypeEnum type;
    /**
     * 变量赋值
     */
    @ApiModelProperty(value = "变量赋值")
    private Set<String> values;

    /**
     * 名称引用
     */
    @ApiModelProperty(value = "名称引用")
    private String nameWithQuote;

    /**
     * 表达式
     */
    @ApiModelProperty(value = "表达式")
    private boolean expression;

    /**
     * 禁用
     */
    @ApiModelProperty(value = "禁用")
    // Permission variable valid flag, which is false when executed by the organization owner
    private boolean disabled;
    private String format;

    @Override
    public String toString() {
        if (values == null) {
            return "";
        }
        return String.join(",", values);
    }

    public ScriptVariable() {
    }

    public ScriptVariable(String name, VariableTypeEnum type, ValueType valueType, Set<String> values, boolean expression) {
        this.name = name;
        this.type = type;
        this.values = values;
        this.valueType = valueType;
        this.expression = expression;
    }

    public String getNameWithQuote() {
        if (nameWithQuote != null) {
            return nameWithQuote;
        }
        nameWithQuote = StringUtils.prependIfMissing(name, Const.DEFAULT_VARIABLE_QUOTE);
        nameWithQuote = StringUtils.appendIfMissing(nameWithQuote, Const.DEFAULT_VARIABLE_QUOTE);
        return nameWithQuote;
    }

    public String valueToString() {
        if (CollectionUtils.isEmpty(values)) {
            return "";
        }
        return String.join(",", values);
    }
}