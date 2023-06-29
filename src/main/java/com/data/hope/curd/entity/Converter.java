package com.data.hope.curd.entity;


import com.data.hope.utils.ReportBeanUtils;
import org.springframework.beans.BeanUtils;


/**
 * @Classname Converter
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/2/7
 * @@author by guoqinghua
 */
public interface Converter {

    default <T> T copyTo(Class<? extends T> cls){
        try {
            T t = cls.newInstance();
            BeanUtils.copyProperties(this, t);
            return t;
        }catch (Exception ignore){
            return null;
        }
    }

    default <T> T copyFormatTo(Class<? extends T> cls){
        try {
            T t = cls.newInstance();
            ReportBeanUtils.copyAndFormatter(this, t);
            return t;
        }catch (Exception ignore){
            return null;
        }
    }

}