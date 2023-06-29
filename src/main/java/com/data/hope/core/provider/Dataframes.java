

package com.data.hope.core.provider;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @Classname Dataframes
 * @Description 返回值
 * @Version 1.0.0
 * @Date 2022/9/27
 * @@author by guoqinghua
 */
public class Dataframes implements Serializable {

    private final String key;

    private final List<Dataframe> dataframes;

    private Dataframes(String key) {
        this.key = "DB" + key;
        dataframes = new ArrayList<>();
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(dataframes);
    }

    public static Dataframes of(String key, Dataframe... dataframes) {
        Dataframes df = new Dataframes(key);
        if (dataframes != null) {
            for (Dataframe dataframe : dataframes) {
                df.getDataframes().add(dataframe);
            }
        }
        return df;
    }

    public void add(Dataframe df) {
        dataframes.add(df);
    }

    public int size() {
        return dataframes.size();
    }

    public String getKey() {
        return key;
    }

    public List<Dataframe> getDataframes() {
        return dataframes;
    }
}