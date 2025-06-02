package com.phonebook;


import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @BeforeMethod
    public void preCondition() {
        //logger.info("Hello ******@BeforeMethod");
    }

    @Test
    public void loginExistedUserPositiveTest() {
        // click on login link
        // click(By.xpath("//a[(.= 'LOGIN')]"));
        app.getUserHelper().clickLoginLink();
        // enter email
        // type(By.name("email"), "anliji225test.2023@gmail.com");
        // enter password
        // type(By.name("password"), "Password101$");
        //fillInRegistrationForm(new User("anliji225test.2023@gmail.com", "Password101$"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("anlii2025test.2025@gmail.com")
                .setPassword("Password101$"));
        // click on Login button
        // click(By.name("login"));
        app.getUserHelper().clickOnLoginButton();
        // assert that Sign out is present
        // Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2() {
        //logger.info("Hello ********@Test");
        app.getUserHelper().login("anlii2025test.2025@gmail.com", "Password101$");

    }


    //так как это негативный тест. то падает. Негативные лучше не смешивать с позитивными
    @Test
    public void loginNegativeWOEmailTest() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                //.setPassword("anliji225test.2023@gmail.com")
                .setEmail("Password101$"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getContactHelper().isAlertPresent());//к isAlertPresent можно достучаться через любой Helper
    }




    @AfterMethod
    public void postConditions() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

}
