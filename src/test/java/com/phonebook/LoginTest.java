package com.phonebook;



import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginExistedUserPositiveTest () {



        // click on login link
        // click(By.xpath("//a[(.= 'LOGIN')]"));
        app.getUserHelper().clickLoginLink();
        // enter email
        // type(By.name("email"), "anliji225test.2023@gmail.com");
        // enter password
        // type(By.name("password"), "Password101$");
        //fillInRegistrationForm(new User("anliji225test.2023@gmail.com", "Password101$"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        // click on Login button
        // click(By.name("login"));
        app.getUserHelper().clickOnLoginButton();
        // assert that Sign out is present
        // Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2 () {
        app.getUserHelper().login("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test
    public void loginNegativeWOEmailTest () {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                //.setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getContactHelper().isAlertPresent());//к isAlertPresent можно достучаться через любой Helper
    }


}
