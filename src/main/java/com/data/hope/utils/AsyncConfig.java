package com.data.hope.utils;


import com.data.hope.utils.pool.PriorityThreadPoolExecutor;
import com.data.hope.utils.pool.PriorityThreadPoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsyncConfig {

    @Resource
    private PriorityThreadPoolProperties priorityThreadPoolProperties;

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Async-Executor");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        return executor;
    }

    @Bean
    public PriorityThreadPoolExecutor priorityExecutor() {
        int corePoolSize = priorityThreadPoolProperties.getCorePoolSize();

        int maximumPoolSize = priorityThreadPoolProperties.getMaximumPoolSize();

        int keepAliveTime = priorityThreadPoolProperties.getKeepAliveTime();

        PriorityThreadPoolExecutor executor = new PriorityThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS);
        return executor;
    }

}
