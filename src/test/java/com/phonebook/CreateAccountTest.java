package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTest extends TestBase {
    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());

    }

    @Test
    public void createAccountPositiveTest2() {
        app.getUserHelper().register("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test
    public void createAccountPositiveTest() {
        app.getUserHelper().register("anlii20280test2028@gmail.com", "Password102$");
        app.getUserHelper().logout();
        app.getUserHelper().login("anlii20280test2028@gmail.com", "Password102$");

        System.out.println("Создал. Вышел И зашел");
    }

    @Test
    public void createAccountNegativeTest1() {
        SoftAssert softAssert = new SoftAssert();

        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        app.getUserHelper().clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent());
        //Assert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isError409Present());
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());

        // вызывает после всего тесты и фейлит если хоть один сфейлился.но это для softAssert
        softAssert.assertAll();
    }

}
