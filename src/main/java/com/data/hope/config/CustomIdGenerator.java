//package com.sg.nr.config;
//
//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @Classname CustomIdGenerator
// * @Description TODO
// * @Version 1.0.0
// * @Date 2023/5/3
// * @@author by guoqinghua
// */
//@Component
//public class CustomIdGenerator implements IdentifierGenerator {
//
//    private final AtomicLong al = new AtomicLong(10);
//
//    @Override
//    public Long nextId(Object entity) {
//        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
//        final long id = al.getAndAdd(1);
//        return id;
//    }
//}