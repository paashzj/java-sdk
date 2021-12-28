package com.github.shoothzj.javatool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomUtilTest {

    @Test
    public void textRandomIntWithBound() {
        int nextInt = RandomUtil.nextInt(5);
        Assertions.assertTrue(nextInt < 5);
    }

}