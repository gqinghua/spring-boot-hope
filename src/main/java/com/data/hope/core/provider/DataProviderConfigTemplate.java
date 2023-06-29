
package com.data.hope.core.provider;

import java.io.Serializable;
import java.util.List;

/** 模板
 * @author gqh
 */

public class DataProviderConfigTemplate implements Serializable {

    private String type;

    private String name;

    private String displayName;

    private List<Attribute> attributes;


    public static class Attribute implements Serializable {

        private String name;

        private String displayName;

        private boolean required;

        private boolean encrypt;

        private Object defaultValue;

        private String key;

        private String type;

        private String description;

        private List<Object> options;

        private List<Attribute> children;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public boolean isEncrypt() {
            return encrypt;
        }

        public void setEncrypt(boolean encrypt) {
            this.encrypt = encrypt;
        }

        public Object getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Object> getOptions() {
            return options;
        }

        public void setOptions(List<Object> options) {
            this.options = options;
        }

        public List<Attribute> getChildren() {
            return children;
        }

        public void setChildren(List<Attribute> children) {
            this.children = children;
        }
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
