package com.phonebook;


import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
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
    public void loginExistedUserPositiveTest2(ITestContext context) {
        String email = "anlii2025test.2025@gmail.com";
        String password = "Password101$";

        context.setAttribute("email", email);
        context.setAttribute("password", password);
        app.getUserHelper().login(email, password);

    }



    @Test
    public void loginNegativeWOEmailTest() {
//        app.getUserHelper().clickLoginLink();
//        app.getUserHelper().fillInRegistrationForm(new User()
//                //.setPassword("anliji225test.2023@gmail.com")
//                .setEmail("Password101$"));
//        app.getUserHelper().clickOnLoginButton();
/// /       System.out.println(app.getUserHelper().alertTextPresent());
//

//        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password", "Messages are not equals");
//        Assert.assertTrue(app.getContactHelper().isAlertPresent());//к isAlertPresent можно достучаться через любой Helper


        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                //  .setEmail("admin_admin_20242@gmail.com")
                .setPassword("Password1@"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password", "Messages are not equals");
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
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
