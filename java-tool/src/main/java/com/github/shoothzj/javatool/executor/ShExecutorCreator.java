package com.github.shoothzj.javatool.executor;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class ShExecutorCreator {

    public static ExecutorService newSingleExecutor(String poolName) {
        return new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20), new DefaultThreadFactory(poolName), new SimpleLogRejectedExecutionHandler());
    }

    public static ScheduledExecutorService newScheduleExecutorService(int corePoolSize, String poolName) {
        return new ScheduledThreadPoolExecutor(corePoolSize, new DefaultThreadFactory(poolName), new SimpleLogRejectedExecutionHandler());
    }

}
