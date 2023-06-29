package com.data.hope.core.provider;

import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @创建人 guoqinghua
 * @创建时间 2022/9/20
 * @描述 数据库信息返回值
 */
@Data
public class SchemaInfo {

    private List<SchemaItem> schemaItems;

    private Date updateTime;

    public static SchemaInfo empty() {
        SchemaInfo schemaInfo = new SchemaInfo();
        schemaInfo.setSchemaItems(Collections.emptyList());
        return schemaInfo;
    }

}
