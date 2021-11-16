package com.github.shoothzj.sdk.mysql.module;

import lombok.AllArgsConstructor;

/**
 * @author hezhangjian
 */
@AllArgsConstructor
public class MysqlConnModule {

    private String host;

    private int port;

    public MysqlConnModule() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
