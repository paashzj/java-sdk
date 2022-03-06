package com.github.shoothzj.test.kafka;

import org.junit.jupiter.api.Test;

class TestKfkServerTest {

    @Test
    public void testKfkServerBoot() throws Exception {
        TestKfkServer server = new TestKfkServer();
        server.start();
        server.close();
    }

}