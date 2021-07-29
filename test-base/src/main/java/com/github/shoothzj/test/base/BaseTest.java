package com.github.shoothzj.test.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;

/**
 * @author hezhangjian
 */
@Slf4j
public class BaseTest {

    @ClassRule
    public static LogTestRule logTestRule = new LogTestRule();

}
