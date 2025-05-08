package com.phonebook.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseHelper {
     Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    protected   WebDriver driver;
    protected  WebDriverWait wait;

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver  = driver;
        this.wait = wait;
    }

//Ниже пример JavaDoc просто даем этот код метода gpt и просим написать javaDoc
    /**
     * Проверяет наличие элемента на текущей странице по заданному локатору.
     *
     * @param locator локатор элемента, который необходимо проверить (например, By.id, By.name и т.д.).
     * @return {@code true}, если элемент присутствует на странице (найден хотя бы один),
     * {@code false} — если элемент отсутствует.
     */
    public boolean isElementPresent(By locator) {
        System.out.println("Есть лит элемент [" + locator + "] на странице");
        return driver.findElements(locator).size() > 0;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
        logger.info("["+ locator + "] is pressed");
    }

    public void type(By locator, String text) {

        if (text != null) {

            click(locator); // Новый клик через Метод

            driver.findElement(locator).clear(); // очистил поле на всякий от автозаполнения
            driver.findElement(locator).sendKeys(text); // ввел валидное значение
        }

    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }

    }
}
