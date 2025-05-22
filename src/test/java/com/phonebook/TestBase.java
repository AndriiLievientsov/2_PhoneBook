package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void test () {
        logger.info("Hello **@BeforeSuite");
    }

    @BeforeClass
    public void test2 () {
        logger.info("Hello ****@BeforeClass");
    }

    @BeforeMethod
    public void setUp () {
        app.init();
      //  logger.info("Hello world");

    }

    @AfterMethod (enabled = true)
    public void tearDown () {
        app.stop();
    }

}
