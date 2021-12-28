package com.github.shoothzj.javatool.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;

/**
 * @author akka
 */
@Slf4j
public class YamlUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    public static String toYaml(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            log.error("json process error, exception is ", e);
        }
        return "";
    }

    public static <T> T relativePathToObject(String relativePath, Class<T> type) {
        return toObject(Resources.getResource(relativePath), type);
    }

    public static <T> T relativePathToRefer(String relativePath, TypeReference<T> reference) {
        return toRefer(Resources.getResource(relativePath), reference);
    }

    public static <T> List<T> relativePathToList(String relativePath, TypeReference<List<T>> typeReference) {
        return toList(Resources.getResource(relativePath), typeReference);
    }

    public static <T> T toObject(URL yaml, Class<T> type) {
        try {
            return MAPPER.readValue(yaml, type);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
        }
        return null;
    }

    public static <T> T toRefer(URL yaml, TypeReference<T> reference) {
        try {
            return MAPPER.readValue(yaml, reference);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
            return null;
        }
    }

    public static <T> List<T> toList(URL yaml, TypeReference<List<T>> typeReference) {
        try {
            return MAPPER.readValue(yaml, typeReference);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
            return null;
        }
    }

    public static <T> T toObject(String yaml, Class<T> type) {
        try {
            return MAPPER.readValue(yaml, type);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
        }
        return null;
    }

    public static <T> T toRefer(String yaml, TypeReference<T> reference) {
        try {
            return MAPPER.readValue(yaml, reference);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
            return null;
        }
    }

    public static <T> List<T> toList(String yaml, TypeReference<List<T>> typeReference) {
        try {
            return MAPPER.readValue(yaml, typeReference);
        } catch (Exception e) {
            log.error("yaml process error, exception is ", e);
            return null;
        }
    }

}
