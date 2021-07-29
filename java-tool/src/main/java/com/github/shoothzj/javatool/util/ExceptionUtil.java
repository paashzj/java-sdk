package com.github.shoothzj.javatool.util;

/**
 * @author shoothzj
 */
public class ExceptionUtil {

    public static String getException(Exception e) {
        StringBuilder builder = new StringBuilder();
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement traceElement : trace) {
            builder.append("\tat ").append(traceElement).append("\n");
        }

        return builder.toString();
    }

}
