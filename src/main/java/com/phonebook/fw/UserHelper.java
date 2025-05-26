package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login(String email, String password) {
        //click on login link
        click(By.xpath("//a[(.= 'LOGIN')]"));
        //enter email
        type(By.name("email"), email.toLowerCase());
        //enter password
        type(By.name("password"), password);
        //click on Login button
        click(By.name("login"));
        // assert that Sign out is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
    }

    public void register(String email, String password) {
        //click on Login link //a[(.= 'LOGIN')]
        click(By.xpath("//a[(.= 'LOGIN')]"));

        // enter email in input By.name("email")
        //driver.findElement(By.name("email")).click(); нажал на поле email - Старый клик
        type(By.name("email"), email);

        // enter password in input By.name ("password")
        //driver.findElement(By.name("password")).click();  нажал на поле password  - Старый клик
        click(By.name("password")); // Новый клик через Метод

        //driver.findElement(By.name("password")).clear();  очистил поле на всякий от автозаполнения
        //driver.findElement(By.name("password")).sendKeys("Password101$");  ввел валидное значение
        type(By.name("password"), password);

        // click on registration button By.name ('registration')
        //driver.findElement(By.name("registration")).click();
        click(By.name("registration"));

        // Assert that button //button [.='Sing Out'] is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
    }

    public void logout() {
        click(By.xpath("//button [.='Sign Out']"));
    }

    public void fillInRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    public void clickRegistrationButton() {
        click(By.name("registration"));
    }

    public void clickLoginLink() {
        click(By.xpath("//a[(.= 'LOGIN')]"));
    }

    public boolean isSignOutButtonPresent() {
//        return isElementPresent(By.xpath("//button [.='Sign Out']"));
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public boolean isError409Present() {
        return isElementPresent(By.xpath("//div[.= 'Registration failed with code 409']"));
    }
}
