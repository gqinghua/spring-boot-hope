
package com.data.hope.core.provider;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/** 数据库配置
 * @author 郭清华
 */
@Data
@ToString
public class DatarSourceConfig {

    private Long sourceId;

    private String type;

    private String name;

    private Map<String, Object> properties;

}