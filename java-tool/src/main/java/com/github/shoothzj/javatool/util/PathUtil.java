package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author akka
 */
@Slf4j
public class PathUtil {

    public static String pathConnect(String... paths) {
        return String.join(File.separator, paths);
    }

}
