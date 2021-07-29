package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author shoothzj
 */
@Slf4j
public class IoUtil extends IOUtils {

    public static String readFile2String(String fileName) {
        try {
            return read2String(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            log.error("file not found exception is ", e);
            return "";
        }
    }

    public static String read2String(InputStream inputStream) {
        return read2StringCharset(inputStream, StandardCharsets.UTF_8);
    }

    public static String read2StringCharset(InputStream inputStream, Charset charset) {
        return new String(read2Byte(inputStream), charset);
    }

    public static byte[] read2Byte(InputStream inputStream) {
        try {
            return readFully(new BufferedInputStream(inputStream), inputStream.available());
        } catch (java.io.IOException e) {
            log.error("Read File error, exception is ", e);
        }
        return new byte[0];
    }

}
