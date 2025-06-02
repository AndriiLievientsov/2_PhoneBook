package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;


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
        logger.info("*************************** TESTING IN PROGRESS ***************************");
        app.init();
    }

    @BeforeMethod
    public void startTest (Method method) {
        logger.info("Test is started: [" + method.getName() +"]");
    }


    @AfterMethod
    public void stopTest (Method method, ITestResult result) {
         if(result.isSuccess()) {
             logger.info("Test is PASSED: [" + method.getName() +"]");
         }else  {
             //проверяем наличие алерта и закрываем его с помощью isAlertPresent
             if (app.getUserHelper().isAlertPresent()){
                 logger.warn("Alert was present and has been accepted");
             }else {
                 logger.info("No alert present");
             }
             //Теперь делаем скриншот
             String screenshotPath = app.getContactHelper().takeScreenshot();
             logger.info("Test is FAILED: [" + method.getName() +"], Screenshot: [" + screenshotPath + "]");
         }

       logger.info("Test is ended: [" + method.getName() +"]");
    }



    //@AfterMethod (enabled = true)
    @AfterSuite (enabled = true)
    public void tearDown () {
        logger.info("*************************** ALL TEST END ***************************");
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
