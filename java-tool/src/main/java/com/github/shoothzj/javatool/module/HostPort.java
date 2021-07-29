package com.github.shoothzj.javatool.module;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hezhangjian
 */
@Data
@AllArgsConstructor
public class HostPort {

    private String host;

    private int port;

    public HostPort() {
    }

}
