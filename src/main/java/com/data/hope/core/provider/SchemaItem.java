package com.data.hope.core.provider;

import lombok.Data;

import java.util.List;

/**
 * @program: report   信息对象
 * @ClassName SchemaItem
 * @description: [用一句话描述此类]
 * @author: GUO
 * @create: 2022-08-09 15:25
 **/
@Data
public class SchemaItem {


    private String dbName;

    private List<TableInfo> tables;
}
