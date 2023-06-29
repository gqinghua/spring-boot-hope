
package com.data.hope.core.common;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池方法
 */
@Slf4j
public class TaskExecutor {

    private static final ThreadPoolExecutor executor;

    static {
        final int max = Runtime.getRuntime().availableProcessors() * 2;
        executor = new ThreadPoolExecutor(max, max, 30
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<>()
                , Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executor.allowCoreThreadTimeOut(true);
    }

    public static void submit(Runnable task) {
        log();
        executor.submit(task);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        log();
        return executor.submit(task);
    }


    private static void log() {
        if (executor.getTaskCount() > Runtime.getRuntime().availableProcessors() * 4L) {
            log.warn("Too many pending tasks ({}),", executor.getTaskCount());
        }
    }

}