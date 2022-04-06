package com.github.shoothzj.test.pulsar;

import org.junit.jupiter.api.Test;

class TestPulsarServerTest {

    @Test
    public void testPulsarServerBoot() throws Exception {
        TestPulsarServer server = new TestPulsarServer();
        server.start();
        server.close();
    }

}
