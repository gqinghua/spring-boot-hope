package com.data.hope.core.poi.format;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
/**
 * @Classname POISettings
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/9/22
 * @Created by guoqinghua
 */
@Data
public class NumericFormat extends PoiNumFormat {

    public static final String type = "numeric";

    /** 前缀 */
    private String prefix;
    /** 后缀 */
    private String suffix;

    @Override
    public String getFormat() {
        String formatStr = "";
        if (StringUtils.isNotBlank(prefix)) {
            formatStr += "\""+prefix+"\"";
        }
        formatStr += this.getUseThousandSeparator();
        formatStr += this.getDecimalPlaces();
        if (StringUtils.isNotBlank(this.getUnitKey())) {
            formatStr += "\""+this.getUnitKey()+"\"";
        }
        if (StringUtils.isNotBlank(suffix)) {
            formatStr += "\""+suffix+"\"";
        }
        return formatStr;
    }

    @Override
    public int getFixLength() {
        return super.getFixLength()+prefix.length()+suffix.length();
    }
}
