package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

//    @BeforeSuite
//    public void test () {
//        logger.info("Hello **@BeforeSuite");
//    }

//    @BeforeClass
//    public void test2 () {
//        logger.info("Hello ****@BeforeClass"); убрали из за ненадобности
//    }

    //@BeforeMethod
    @BeforeSuite
    public void setUp () {
        app.init();
      //  logger.info("Hello world");

    }

    //@AfterMethod (enabled = true)
    @AfterSuite (enabled = true)
    public void tearDown () {
        app.stop();
    }

//    @AfterClass
//    public void testAfterClass () {
//        logger.info("Hello ****@AfterClass"); убрали из за ненадобности
//    }


//    @AfterSuite
//    public void testAfterSuite () {
//        logger.info("Hello **@AfterSuite");
//    }

}
