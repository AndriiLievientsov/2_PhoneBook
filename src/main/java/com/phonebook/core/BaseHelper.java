package com.phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseHelper {
     Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    protected  WebDriver driver;
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
      //  System.out.println("Есть лит элемент [" + locator + "] на странице");
        return driver.findElements(locator).size() > 0;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
       // logger.info("["+ locator + "] is pressed"); //ниже идут два примера логеров
//        logger.error("["+ locator + "] is pressed");
//        logger.error("[{}] and {} is pressed", locator, locator);
    }

    public void type(By locator, String text) {

        if (text != null) {

            click(locator); // Новый клик через Метод

            driver.findElement(locator).clear(); // очистил поле на всякий от автозаполнения
            driver.findElement(locator).sendKeys(text); // ввел валидное значение
        }

    }


    //Метод ниже переписал согласно Преподу. но старый оставил
//    public boolean isAlertPresent() {
//        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
//        if (alert == null) {
//            return false;
//        } else {
//            driver.switchTo().alert().accept();
//            return true;
//        }
//
//    }



    public boolean isAlertPresent() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        try {
            logger.warn("Alert has text: [" + alert.getText() + "]");
            alert.accept();
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public String alertTextPresent() {
        return  wait.until(ExpectedConditions.alertIsPresent()).getText();
    }



    public String takeScreenshot () {
        //Создаем объект File для сохранения скриншота добавляя текущую метку времени в названии файла
        File screenshot = new File("src/test_screenshot/screen-" + System.currentTimeMillis() + ".png");
        try {
            //получаем временный файл скриншота с помощью интерфейса TakeScreenshot
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Копируем временный файл в постоянное место назначения (в созданный ранее файл screenshot)
            Files.copy(tmp.toPath(), screenshot.toPath());
        } catch (NoSuchSessionException e) {
            logger.error("WebDriver session is closed, cannot take screenshot", e);
            return "";//Возвращаем пустую строку чтобы тест не падал
        } catch (IOException e) {
            // Логируем ошибку при сохранении скриншота и выбрасываем исключение RuntimeException
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        // Возвращаем путь к сохраненному скриншоту
        return screenshot.getAbsolutePath();
    }
}
