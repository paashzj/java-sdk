package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class CommonUtil {

    public static void sleep(TimeUnit timeUnit, int timeout) {
        try {
            switch (timeUnit) {
                case DAYS:
                    TimeUnit.DAYS.sleep(timeout);
                    break;
                case HOURS:
                    TimeUnit.HOURS.sleep(timeout);
                    break;
                case MINUTES:
                    TimeUnit.MINUTES.sleep(timeout);
                    break;
                case SECONDS:
                    TimeUnit.SECONDS.sleep(timeout);
                    break;
                case MILLISECONDS:
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    break;
                case MICROSECONDS:
                    TimeUnit.MICROSECONDS.sleep(timeout);
                    break;
                case NANOSECONDS:
                    TimeUnit.NANOSECONDS.sleep(timeout);
                    break;
                default:
                    log.info("do nothing");
                    break;
            }
        } catch (InterruptedException e) {
            log.error("Do nothing, exception is {}", ExceptionUtil.getException(e));
        }
    }

}
