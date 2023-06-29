package com.data.hope.core.poi.format;

import com.data.hope.core.base.consts.Currency;
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
public class CurrencyFormat extends PoiNumFormat {

    public static final String type = "currency";

    /** 前缀 */
    private String prefix;

    /** 货币类型 */
    private String currency;

    public CurrencyFormat() {
        this.setUseThousandSeparator(true);
    }

    public void setCurrency(String currency) {
        this.currency = currency;
        this.prefix = "";
        if (StringUtils.isNotBlank(this.currency)){
            Currency currencyType = Currency.valueOf(currency);
            this.currency = currencyType.getUnit();
            this.prefix = currencyType.getPrefix();
        }
    }

    @Override
    public String getFormat() {
        String formatStr = "";
        if (StringUtils.isNotBlank(prefix)){
            formatStr += "\""+getPrefix()+"\"";
        }
        if (StringUtils.isNotBlank(currency)){
            formatStr += getCurrency();
        }
        formatStr += this.getUseThousandSeparator();
        formatStr += this.getDecimalPlaces();
        if (StringUtils.isNotBlank(this.getUnitKey())){
            formatStr += " \""+this.getUnitKey()+"\"";
        }
        return formatStr;
    }

    @Override
    public int getFixLength() {
        if (StringUtils.isNotBlank(currency)) {
            return super.getFixLength()+2;
        }
        return super.getFixLength();
    }
}
