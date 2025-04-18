package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginExistedUserPositiveTest () {
        // click on login link
        // click(By.xpath("//a[(.= 'LOGIN')]"));
        clickLoginLink();
        // enter email
        // type(By.name("email"), "anliji225test.2023@gmail.com");
        // enter password
        // type(By.name("password"), "Password101$");
        //fillInRegistrationForm(new User("anliji225test.2023@gmail.com", "Password101$"));
        fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        // click on Login button
        // click(By.name("login"));
        clickOnLoginButton();
        // assert that Sign out is present
        // Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
        Assert.assertTrue(isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2 () {
        login("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test
    public void loginNegativeWOEmailTest () {
        clickLoginLink();
        fillInRegistrationForm(new User()
                //.setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        clickOnLoginButton();
        Assert.assertTrue(isAlertPresent());
    }


}
