package HW2;

import HW2.coreHW2.ApplicationManagerHW2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBaseHW2 {
    protected static ApplicationManagerHW2 appHW2 = new ApplicationManagerHW2(System.getProperty("browser", "chrome"));
    Logger loggerHW2 = LoggerFactory.getLogger(TestBaseHW2.class);

    @BeforeMethod
    public void setUp () {
       appHW2.initHW2();
        loggerHW2.info("Успешное ДЗ 2");
    }

    @AfterMethod (enabled = true)
    public void tearDown () {
        appHW2.stop();
    }
}
