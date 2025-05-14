package HW2.coreHW2;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHelperHW2 {
    Logger logger = LoggerFactory.getLogger(BaseHelperHW2.class);

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseHelperHW2(WebDriver driver, WebDriverWait wait) {
        this.driver  = driver;
        this.wait = wait;
    }

    public boolean isElementPresent(By locator) {
        System.out.println("Есть лит элемент [" + locator + "] на странице");
        return driver.findElements(locator).size() > 0;
    }

    public void select (By locator) {
        driver.findElement(locator).click();
        logger.info("["+ locator + "] is pressed");
    }

    public void typeData (By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
}
