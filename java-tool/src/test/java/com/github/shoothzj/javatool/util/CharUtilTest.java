package com.github.shoothzj.javatool.util;

import com.github.shoothzj.test.base.LogTestRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class CharUtilTest {

    @ClassRule
    public static LogTestRule logTestRule = new LogTestRule();

    @Test
    public void testIsDigitDigit() {
        Assert.assertTrue(CharUtil.isDigit('3'));
    }

    @Test
    public void testIsDigitChar() {
        Assert.assertFalse(CharUtil.isDigit('a'));
    }

}
