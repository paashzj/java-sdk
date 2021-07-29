package com.github.shoothzj.javatool.util;

import com.github.shoothzj.javatool.module.ShellResult;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class ShellUtil {

    public static ShellResult executeCmd(String[] cmd) {
        log.info("exec command array {}", String.join(";", cmd));
        String inputContent = "";
        String errorContent = "";
        int ret = -1;
        try {
            Process pid = Runtime.getRuntime().exec(cmd);
            ret = pid.waitFor();
            InputStream inputStream = pid.getInputStream();
            InputStream errorStream = pid.getErrorStream();
            inputContent = IoUtil.read2String(inputStream);
            errorContent = IoUtil.read2String(errorStream);
            log.error("command is {}, result is {}, input content is [{}], error content is [{}]"
                    , cmd, ret, inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent);
        } catch (Exception e) {
            log.error("Execute command exception. [{}], input content is [{}], error content is [{}]"
                    , e.getMessage(), inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent, e);
        }
    }

    public static ShellResult executeCmd(String cmd) {
        log.info("exec command {}", cmd);
        String inputContent = "";
        String errorContent = "";
        int ret = -1;
        try {
            Process pid = Runtime.getRuntime().exec(cmd);
            ret = pid.waitFor();
            InputStream inputStream = pid.getInputStream();
            InputStream errorStream = pid.getErrorStream();
            inputContent = IoUtil.read2String(inputStream);
            errorContent = IoUtil.read2String(errorStream);
            log.error("command is {}, result is {}, input content is [{}], error content is [{}]"
                    , cmd, ret, inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent);
        } catch (Exception e) {
            log.error("Execute command exception. [{}], input content is [{}], error content is [{}]"
                    , e.getMessage(), inputContent, errorContent);
            return new ShellResult(ret, inputContent, errorContent, e);
        }
    }

}
