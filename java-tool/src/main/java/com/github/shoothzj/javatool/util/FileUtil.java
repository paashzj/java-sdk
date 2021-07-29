package com.github.shoothzj.javatool.util;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author akka
 */
@Slf4j
public class FileUtil {

    public static String getFilePath(String filename) {
        return Resources.getResource(filename).getPath();
    }

    /**
     * 获取文件类型
     * @param fileName 文件名
     * @return
     */
    public static String getFileType(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        return fileName.substring(lastIndexOf + 1);
    }


    public static void removeRecursive(String dirPath) {
        removeRecursive(Paths.get(dirPath));
    }

    /**
     * 递归删除文件
     * @param path 路径
     */
    public static void removeRecursive(Path path) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            log.error("walk file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

    public static void inputStream2File(InputStream inputStream, String file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(file));
        } catch (Exception e) {
            log.error("convert to file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

    public static void inputStream2File(InputStream inputStream, File file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (Exception e) {
            log.error("convert to file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

}
