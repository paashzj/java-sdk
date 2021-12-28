package com.github.shoothzj.javatool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SocketUtilTest {

    @Test
    void testGetFreePort() throws Exception {
        int freePort = SocketUtil.getFreePort();
        Assertions.assertTrue(freePort > 0);
    }

}