package com.data.hope.event.listener;


import com.data.hope.event.ExceptionEvent;
import org.springframework.context.ApplicationListener;

/**
 * 异常监听
 * @author guoqinghua
 * @since 2021-01-25
 */
public class ExceptionApplicationListener implements ApplicationListener<ExceptionEvent> {

    @Override
    public void onApplicationEvent(ExceptionEvent event) {

        //异常
        Throwable throwable = event.getThrowable();

        //异常处理
        //1、发邮件


        //2、对接睛灵


        //3、记录错误日志信息
    }
}
