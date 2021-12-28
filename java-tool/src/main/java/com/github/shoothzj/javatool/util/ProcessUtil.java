package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hezhangjian
 */
@Slf4j
public class ProcessUtil {

    public static boolean processExists(String processName) throws Exception {
        final File procFile = new File("/proc");
        if (!procFile.isDirectory()) {
            throw new Exception("why proc dir is not directory");
        }
        final File[] listFiles = procFile.listFiles();
        if (listFiles == null) {
            return false;
        }
        final List<File> procDir = Arrays.stream(listFiles)
                .filter(f -> RegexUtil.NUMBER_PATTERN.matcher(f.getName()).matches())
                .collect(Collectors.toList());
        // find the proc cmdline
        for (File file : procDir) {
            try {
                final byte[] byteArray = FileUtils.readFileToByteArray(
                        new File(file.getCanonicalPath() + File.separator + "cmdline"));
                final byte[] bytes = new byte[byteArray.length];
                for (int i = 0; i < byteArray.length; i++) {
                    if (byteArray[i] != 0x00) {
                        bytes[i] = byteArray[i];
                    } else {
                        bytes[i] = (byte) 0x20;
                    }
                }
                final String cmdLine = new String(bytes, StandardCharsets.UTF_8);
                if (cmdLine.contains(processName)) {
                    return true;
                }
            } catch (IOException e) {
                // the proc may end during the loop, ignore it
                log.error("read file exception ", e);
            }
        }
        return false;
    }

}
