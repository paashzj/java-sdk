package com.github.shoothzj.javatool.util;

import java.util.regex.Pattern;

/**
 * 正则表达式常量类
 * @author shoothzj
 */
public class RegexUtil {

    public static final String NUMBER_REG = "[0-9]+";

    public static final String WORDS_REG = "\\w+";

    public static final String IPV4_REGEX = "\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";

    public static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REG);

    public static final Pattern WORDS_PATTERN = Pattern.compile(WORDS_REG);

    public static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

}
