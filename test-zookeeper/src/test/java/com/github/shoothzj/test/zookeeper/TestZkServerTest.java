package com.github.shoothzj.test.zookeeper;

import org.junit.jupiter.api.Test;

class TestZkServerTest {

    @Test
    public void testZkServerBoot() throws Exception {
        TestZkServer server = new TestZkServer();
        server.start();
        server.close();
    }

}