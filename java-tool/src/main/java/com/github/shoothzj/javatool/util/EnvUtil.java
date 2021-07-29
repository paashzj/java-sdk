package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class EnvUtil {

    /**
     * 用户路径
     * e.g. Mac: /Users/akka
     */
    private static final String userHome = System.getProperty("user.home");
    /**
     * 操作系统名称
     * e.g. Mac: Mac OS X
     */
    private static final String osName = System.getProperty("os.name");
    /**
     * os的版本
     * e.g. 10.15.2
     */
    private static final String osVersion = System.getProperty("os.version");

    private static final String userDir = System.getProperty("user.dir");

    public static boolean getBooleanVar(String property, String env, boolean defaultVal) {
        String prop = System.getProperty(property);
        if (StringUtil.isNotEmpty(prop)) {
            return Boolean.parseBoolean(prop);
        }
        String envVal = System.getenv(env);
        if (StringUtil.isNotEmpty(envVal)) {
            return Boolean.parseBoolean(envVal);
        }
        return defaultVal;
    }

    public static int getIntVar(String property, String env, int defaultVal) {
        String prop = System.getProperty(property);
        if (StringUtil.isNotEmpty(prop)) {
            return Integer.parseInt(prop);
        }
        String envVal = System.getenv(env);
        if (StringUtil.isNotEmpty(envVal)) {
            return Integer.parseInt(envVal);
        }
        return defaultVal;
    }

    public static float getFloatVar(String property, String env, float defaultVal) {
        String prop = System.getProperty(property);
        if (StringUtil.isNotEmpty(prop)) {
            return Float.parseFloat(prop);
        }
        String envVal = System.getenv(env);
        if (StringUtil.isNotEmpty(envVal)) {
            return Float.parseFloat(envVal);
        }
        return defaultVal;
    }

    public static double getDoubleVar(String property, String env, double defaultVal) {
        String prop = System.getProperty(property);
        if (StringUtil.isNotEmpty(prop)) {
            return Double.parseDouble(prop);
        }
        String envVal = System.getenv(env);
        if (StringUtil.isNotEmpty(envVal)) {
            return Double.parseDouble(envVal);
        }
        return defaultVal;
    }

    public static String getStringVar(String property, String env, String defaultVal) {
        String prop = System.getProperty(property);
        if (StringUtil.isNotEmpty(prop)) {
            return prop;
        }
        String envVal = System.getenv(env);
        if (StringUtil.isNotEmpty(envVal)) {
            return envVal;
        }
        return defaultVal;
    }


    public static String getUserHome() {
        return userHome;
    }

    public static String getOsName() {
        return osName;
    }

    public static String getOsVersion() {
        return osVersion;
    }

    public static String getHostName() {
        return System.getenv("HOSTNAME");
    }

    public static String getUserDir() {
        return userDir;
    }

    public static String getServiceName() {
        return System.getenv("SERVICE_NAME");
    }

    public static void listAll() {
        log.info("userHome is {}", userHome);
        log.info("osName is {}", osName);
        log.info("osVersion is {}", osVersion);
    }
}
