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
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password101$";
        app.getUserHelper().register(email, password);

    }

    @Test
    public void createAccountPositiveTest() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password101$";


        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);

    }

//    @Test
//    public void createAccountNegativeTest1() {
//        SoftAssert softAssert = new SoftAssert();
//
//        app.getUserHelper().clickLoginLink();
//        app.getUserHelper().fillInRegistrationForm(new User()
//                .setPassword("anliji225test.2023@gmail.com")
//                .setEmail("Password101$"));
//        app.getUserHelper().clickRegistrationButton();
//        //Assert.assertFalse(isSignOutButtonPresent());
//        //Assert.assertTrue(isAlertPresent());
//        //Assert.assertTrue(isError409Present());
//        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
//        softAssert.assertTrue(app.getUserHelper().isError409Present());
//
//        // вызывает после всего тесты и фейлит если хоть один сфейлился.но это для softAssert
//        softAssert.assertAll();
//    }

    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        //fillInRegistrationForm(new User("admin_admin_20242@gmail.com", "Password1@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("admin_admin_20242@gmail.com")
                .setPassword("Password1@"));
        app.getUserHelper().clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent());
        //Assert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isError409Present());

        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();

    }
}
