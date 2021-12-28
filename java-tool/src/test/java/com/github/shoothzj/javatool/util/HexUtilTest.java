package com.github.shoothzj.javatool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HexUtilTest {

    @Test
    public void testHexStr2ByteArray() {
        byte[] bytes = HexUtil.hexStrToByteArray("st");
        byte[] expected = new byte[]{-17};
        Assertions.assertEquals(expected.length, bytes.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], bytes[i]);
        }
    }

}