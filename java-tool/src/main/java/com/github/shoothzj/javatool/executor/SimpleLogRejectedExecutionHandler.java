package com.github.shoothzj.javatool.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hezhangjian
 */
@Slf4j
public class SimpleLogRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.error("rejected runnable is {}, executor is {}", r, executor);
    }

}
