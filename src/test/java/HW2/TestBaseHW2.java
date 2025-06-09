package HW2;

import HW2.coreHW2.ApplicationManagerHW2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBaseHW2 {
    protected static ApplicationManagerHW2 appHW2 = new ApplicationManagerHW2(System.getProperty("browser", "chrome"));
    Logger loggerHW2 = LoggerFactory.getLogger(TestBaseHW2.class);

    @BeforeSuite
    public void beforeSuiteCheck() {
        loggerHW2.info("*************************** TESTING HW, IN PROGRESS ***************************");

    }

    @BeforeMethod
    public void setUp(Method method) {
        loggerHW2.info("Test is started: [" + method.getName() + "]");
        appHW2.initHW2();
        loggerHW2.info("Выводится после метода ");
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {

        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }
//        if (parameters.length() > 0) {
//            parameters.setLength(parameters.length() - 2);
//        }

        loggerHW2.info("Test is started with data: [" + parameters + "]");

        if (result.isSuccess()) {
            loggerHW2.info("Test is PASSED: [" + method.getName() + "], with data: [" + parameters + "]");
        } else {
            //проверяем наличие алерта и закрываем его с помощью isAlertPresent
            if (appHW2.getUserHelperHW2().isAlertPresent()) {
                loggerHW2.warn("Alert was present and has been accepted");
            } else {
                loggerHW2.info("No alert present");
            }


            //Теперь делаем скриншот
            String screenshotPath = appHW2.getUserHelperHW2().takeScreenshot();
            loggerHW2.info("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }

        loggerHW2.info("Test is ended: [" + method.getName() + "]");
    }

    @AfterSuite(enabled = true)
    public void suiteEnd() {
        loggerHW2.info("*************************** ALL HW TEST END ***************************");
        appHW2.stop();
    }
}
