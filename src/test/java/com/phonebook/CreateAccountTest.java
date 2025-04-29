package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTest extends TestBase {
    @Test
    public void createAccountPositiveTest1() {
        clickLoginLink();
        fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        clickRegistrationButton();
        Assert.assertTrue(isSignOutButtonPresent());

    }

    @Test
    public void createAccountPositiveTest2() {
        register("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test
    public void createAccountPositiveTest() {
        register("anlii20280test2028@gmail.com", "Password102$");
        logout();
        login("anlii20280test2028@gmail.com", "Password102$");

        System.out.println("Создал. Вышел И зашел");
    }

    @Test
    public void createAccountNegativeTest1() {
        SoftAssert softAssert = new SoftAssert();

        clickLoginLink();
        fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent());
        //Assert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isError409Present());
        softAssert.assertTrue(isAlertPresent());
        softAssert.assertTrue(isError409Present());

        // вызывает после всего тесты и фейлит если хоть один сфейлился.но это для softAssert
        softAssert.assertAll();
    }

}
