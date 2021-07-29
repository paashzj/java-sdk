package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author hezhangjian
 */
@Slf4j
public class Base64Util {

    public static String encodeToString(byte[] array) {
        return new String(encode(array), StandardCharsets.UTF_8);
    }

    public static byte[] encode(byte[] array) {
        return Base64.getEncoder().encode(array);
    }

}
