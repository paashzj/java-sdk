package com.github.shoothzj.javatool.util;

/**
 * @author hezhangjian
 */
public class CharUtil {

    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isDown(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public static boolean isAlpha(char ch) {
        return isUpper(ch) || isDown(ch);
    }


}
