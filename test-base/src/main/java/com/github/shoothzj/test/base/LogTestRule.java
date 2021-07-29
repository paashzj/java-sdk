package com.github.shoothzj.test.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author hezhangjian
 */
@Slf4j
public class LogTestRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        Configurator.setRootLevel(Level.INFO);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
            }
        };
    }

}
