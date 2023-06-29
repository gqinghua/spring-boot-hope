package com.data.hope.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.data.hope.holder.UserContentHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自动补充插入或更新时的值
 * @author guoqinghua
 * @since 2022-03-01
 */
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String username = UserContentHolder.getContext().getUsername();

        Object createBy = this.getFieldValByName("createBy", metaObject);
        if (createBy == null) {
            this.setFieldValByName("createBy", username,metaObject);
        }

        this.setFieldValByName("createTime", new Date(),metaObject);
//        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);

        Object updateBy = this.getFieldValByName("updateBy", metaObject);
        if (updateBy == null) {
            this.setFieldValByName("updateBy", username,metaObject);
        }

        this.setFieldValByName("updateTime", new Date(),metaObject);
//        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);



        this.setFieldValByName("version", Integer.valueOf(1),metaObject);

        //添加
        this.setFieldValByName("sendTime", new Date(),metaObject);
        this.setFieldValByName("dealTime", new Date(),metaObject);



    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String username = UserContentHolder.getContext().getUsername();
        this.setFieldValByName("updateBy", username,metaObject);
        this.setFieldValByName("updateTime", new Date(),metaObject);
        this.setFieldValByName("createTime", new Date(),metaObject);

//        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
//        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);


        this.setFieldValByName("version", this.getFieldValByName("version",metaObject),metaObject);
        //添加
        this.setFieldValByName("sendTime", new Date(),metaObject);
        this.setFieldValByName("dealTime", new Date(),metaObject);
    }
}
