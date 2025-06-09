package HW2;

import HW2.modelHW2.UserHW2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.time.Duration;

public class RandomNameAccountTest extends TestBaseHW2{

    @Test
    public void registerRandomUser () {
        String fName = System.currentTimeMillis() + "- F";
        String sName = System.currentTimeMillis() + "- S";
        String email = System.currentTimeMillis() + "@gmail.com";
        String password = "Password101$";
        String conPassword = "Password101$";
        appHW2.getUserHelperHW2().NewRegister(fName,sName,email,password,conPassword);
    }

    @Test
    public void loginRandoUser () {
        String fName = System.currentTimeMillis() + "- F";
        String sName = System.currentTimeMillis() + "- S";
        String email = System.currentTimeMillis() + "@gmail.com";
        String password = "Password101$";
        String conPassword = "Password101$";
        appHW2.getUserHelperHW2().NewRegister(fName,sName,email,password,conPassword);
        appHW2.getUserHelperHW2().logoutAfterRegister();
        appHW2.getUserHelperHW2().login(email,password);
    }


    @Test
    public void loginRandoUserDataOut(ITestContext context) {
        String email = "b5555@gmail.com";
        String password = "b5555@gmail.com";

        context.setAttribute("email", email);
        context.setAttribute("password", password);
        appHW2.getUserHelperHW2().login(email, password);
    }

}
