package com.data.hope.core.poi.format;

import lombok.Data;
/**
 * @Classname POISettings
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/22
 * @Created by guoqinghua
 */
@Data
public class PercentageFormat extends PoiNumFormat {

    public static final String type = "percentage";

    @Override
    public String getFormat() {
        return this.getDecimalPlaces() + "%";
    }
}
