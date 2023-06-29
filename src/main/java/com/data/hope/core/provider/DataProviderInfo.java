

package com.data.hope.core.provider;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataProviderInfo implements Serializable {

    private String type;

    private String name;

    @Override
    public String toString() {
        return "DataProviderInfo{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}