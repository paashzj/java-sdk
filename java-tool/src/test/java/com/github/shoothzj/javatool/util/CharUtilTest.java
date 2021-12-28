package com.github.shoothzj.javatool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharUtilTest {

    @Test
    public void testIsDigitDigit() {
        Assertions.assertTrue(CharUtil.isDigit('3'));
    }

    @Test
    public void testIsDigitChar() {
        Assertions.assertFalse(CharUtil.isDigit('a'));
    }

}
