package com.data.hope.curd.mapper.injected;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.data.hope.curd.mapper.methods.CustomInsertBatch;
import com.data.hope.curd.mapper.methods.UpdateFieldsBatch;
import com.data.hope.curd.mapper.methods.UpdateFieldsBatchById;
import com.data.hope.curd.mapper.methods.UpdateFieldsById;

import java.util.List;

/**
 * 自定义注入
 * @author guoqinghua
 * @since 2022-03-01
 */
public class CustomSqlInjector extends DefaultSqlInjector {

//    @Override
//    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        methodList.add(new CustomInsertBatch());
//        methodList.add(new UpdateFieldsById());
//        methodList.add(new UpdateFieldsBatch());
//        methodList.add(new UpdateFieldsBatchById());
//        return methodList;
//    }

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList() 再add
     * @return
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
//        methodList.add(new DeleteAll("deleteAll"));
//        methodList.add(new MyInsertAll("myInsertAll"));
//        methodList.add(new MysqlInsertAllBatch("mysqlInsertAllBatch"));

        methodList.add(new CustomInsertBatch());
        methodList.add(new UpdateFieldsById());
        methodList.add(new UpdateFieldsBatch());
        methodList.add(new UpdateFieldsBatchById());
        methodList.add(new SelectById());
        return methodList;
    }
}
