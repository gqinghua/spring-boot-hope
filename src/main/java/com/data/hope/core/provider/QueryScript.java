
package com.data.hope.core.provider;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * @Classname QueryScript
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/26
 * @@author by guoqinghua
 */
public class QueryScript implements Serializable {
    /**
     * 数据源id
     */
    private String sourceId;
    /**
     * 是否进行测试
     */
    private boolean test;
    /**
     * 类型
     */
    private ScriptType scriptType;
    /**
     * 脚本
     */
    private String script;
    /**
     * 变量
     */
    private List<ScriptVariable> variables;
    /**
     * 字段
     */
    private Map<String, Column> schema;

    public String toQueryKey() {
        return 'Q' + DigestUtils.md5Hex(JSON.toJSONString(this));
    }

    public static QueryScript.QueryScriptBuilder builder() {
        return new QueryScript.QueryScriptBuilder();
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public boolean isTest() {
        return this.test;
    }

    public ScriptType getScriptType() {
        return this.scriptType;
    }

    public String getScript() {
        return this.script;
    }

    public List<ScriptVariable> getVariables() {
        return this.variables;
    }

    public Map<String, Column> getSchema() {
        return this.schema;
    }

    public void setSourceId(final String sourceId) {
        this.sourceId = sourceId;
    }

    public void setTest(final boolean test) {
        this.test = test;
    }

    public void setScriptType(final ScriptType scriptType) {
        this.scriptType = scriptType;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public void setVariables(final List<ScriptVariable> variables) {
        this.variables = variables;
    }

    public void setSchema(final Map<String, Column> schema) {
        this.schema = schema;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof QueryScript;
    }



    public QueryScript(final String sourceId, final boolean test, final ScriptType scriptType, final String script, final List<ScriptVariable> variables, final Map<String, Column> schema) {
        this.sourceId = sourceId;
        this.test = test;
        this.scriptType = scriptType;
        this.script = script;
        this.variables = variables;
        this.schema = schema;
    }

    public QueryScript() {
    }

    public static class QueryScriptBuilder {
        private String sourceId;
        private boolean test;
        private ScriptType scriptType;
        private String script;
        private List<ScriptVariable> variables;
        private Map<String, Column> schema;

        QueryScriptBuilder() {
        }

        public QueryScript.QueryScriptBuilder sourceId(final String sourceId) {
            this.sourceId = sourceId;
            return this;
        }

        public QueryScript.QueryScriptBuilder test(final boolean test) {
            this.test = test;
            return this;
        }

        public QueryScript.QueryScriptBuilder scriptType(final ScriptType scriptType) {
            this.scriptType = scriptType;
            return this;
        }

        public QueryScript.QueryScriptBuilder script(final String script) {
            this.script = script;
            return this;
        }

        public QueryScript.QueryScriptBuilder variables(final List<ScriptVariable> variables) {
            this.variables = variables;
            return this;
        }

        public QueryScript.QueryScriptBuilder schema(final Map<String, Column> schema) {
            this.schema = schema;
            return this;
        }

        public QueryScript build() {
            return new QueryScript(this.sourceId, this.test, this.scriptType, this.script, this.variables, this.schema);
        }

        @Override
        public String toString() {
            return "QueryScript.QueryScriptBuilder(sourceId=" + this.sourceId + ", test=" + this.test + ", scriptType=" + this.scriptType + ", script=" + this.script + ", variables=" + this.variables + ", schema=" + this.schema + ")";
        }
    }
}
