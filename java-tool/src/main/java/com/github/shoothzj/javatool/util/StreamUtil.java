package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;

/**
 * @author shoothzj
 */
@Slf4j
public class StreamUtil {

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            log.error("Closable close failed, exception is {}", ExceptionUtil.getException(e));
        }
    }

}
