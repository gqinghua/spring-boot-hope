package com.data.hope.core.poi;

import com.data.hope.core.provider.Column;
import lombok.Data;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname POISettings
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/22
 * @Created by guoqinghua
 */
@Data
public class POISettings {

    private Map<Integer, ColumnSetting> columnSetting = new HashMap<>();

    private Map<Integer, List<Column>> headerRows = new HashMap<>();

    private List<CellRangeAddress> mergeCells = new ArrayList<>();
}
