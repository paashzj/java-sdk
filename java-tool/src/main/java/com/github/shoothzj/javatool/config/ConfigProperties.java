package com.github.shoothzj.javatool.config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class ConfigProperties {

    private final Properties properties;

    private final String dstFileName;

    public ConfigProperties(String originFile, String dstFileName) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(originFile)) {
            properties.load(fis);
        } catch (Exception e) {
            throw new IllegalStateException("read properties from file failed ", e);
        }
        this.dstFileName = dstFileName;
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public void write() {
        try (FileOutputStream fos = new FileOutputStream(dstFileName)) {
            properties.store(fos, "");
        } catch (Exception e) {
            throw new IllegalStateException("write config file failed ", e);
        }
    }

}
