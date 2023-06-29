package com.data.hope.annotation.resolver.mask;

import com.data.hope.annotation.ReportMask;
import com.data.hope.utils.ReportMaskUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import java.io.IOException;

/**
 * 数据脱敏注解解析
 * @author guoqinghua
 * @since 2022-03-01
 * @see JsonSerialize#using()
 */
public class ReportMaskJsonSerialize extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 脱敏注解
     */
    private ReportMask reportMask;

    public ReportMaskJsonSerialize(){}

    public ReportMaskJsonSerialize(ReportMask reportMask) {
        this.reportMask = reportMask;
    }

    /**
     * Jackson序列化时会调用该方法，自定义属性值
     * @param value
     * @param gen
     * @param serializers
     * @throws IOException
     */
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        String result = value;
        //脱敏注解
        if (reportMask != null) {
            MaskEnum type = reportMask.type();

            if (type != MaskEnum.COMMON) {
                result = ReportMaskUtils.mask(value, type.getPattern(), type.getReplacement());
            } else {
                String pattern = type.getPattern();
                int left = reportMask.left();
                int right = reportMask.right();

                //当设置的左右长度大于对应值的长度时，直接采用默认默认脱敏规则
                String patternFormat;
                if (left + right >= value.length()) {
                    patternFormat = ReportMaskUtils.defaultPattern;
                } else {
                    patternFormat = String.format(pattern, reportMask.left(), reportMask.right());
                }
                result = ReportMaskUtils.mask(value, patternFormat, type.getReplacement());
            }

        }
        gen.writeString(result);
    }

    /**
     * Jackson序列化时会调用该方法创建JsonSerializer
     * @param prov
     * @param property
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {

        if (property != null) {
            reportMask = property.getAnnotation(ReportMask.class);
            if (reportMask != null) {
                return new ReportMaskJsonSerialize(reportMask);
            }
            return prov.findValueSerializer(property.getType(), property);
        }

        return NullSerializer.instance;
    }
}
