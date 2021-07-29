package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class StatefulSetUtil {

    /**
     * @return 获取当前节点,是statefulSet的第几个节点
     */
    public static int getStatefulSequence() {
        String hostName = EnvUtil.getHostName();
        String[] split = hostName.split("-");
        return Integer.parseInt(split[split.length - 1]);
    }

}
