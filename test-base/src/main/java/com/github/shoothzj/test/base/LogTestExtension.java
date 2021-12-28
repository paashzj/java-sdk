package com.github.shoothzj.test.base;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LogTestExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Configurator.setRootLevel(Level.INFO);
    }
}
