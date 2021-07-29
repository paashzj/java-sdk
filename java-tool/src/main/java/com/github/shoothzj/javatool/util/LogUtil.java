package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author hezhangjian
 */
@Slf4j
public class LogUtil {

    public static void configureLog() {
        String logLevel = System.getenv("LOG_LEVEL");
        if (StringUtil.isEmpty(logLevel)) {
            Configurator.setRootLevel(Level.INFO);
            return;
        }
        switch (logLevel) {
            case "DEBUG":
                Configurator.setRootLevel(Level.DEBUG);
                break;
            case "ERROR":
                Configurator.setRootLevel(Level.ERROR);
                break;
            default:
                Configurator.setRootLevel(Level.INFO);
        }
    }

}
