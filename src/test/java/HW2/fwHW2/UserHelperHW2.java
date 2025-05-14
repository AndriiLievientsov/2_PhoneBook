package HW2.fwHW2;

import HW2.coreHW2.BaseHelperHW2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserHelperHW2 extends BaseHelperHW2 {
    public UserHelperHW2(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void NewRegister (String First, String Second, String Email,
                             String Password, String ConfirmPassword) {

        driver.findElement(By.name("Gender")).click();

        select(By.name("FirstName"));
        typeData(By.name("FirstName"), First);

        select(By.name("LastName"));
        typeData(By.name("LastName"), Second);

        select(By.name("Email"));
        typeData(By.name("Email"), Email);

        select(By.name("Password"));
        typeData(By.name("Password"), Password);

        select(By.name("ConfirmPassword"));
        typeData(By.name("ConfirmPassword"), ConfirmPassword);

        driver.findElement(By.name("register-button")).click();
        driver.get("https://demowebshop.tricentis.com/registerresult/1");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.get("https://demowebshop.tricentis.com/");
        Assert.assertTrue(isElementPresent(By.xpath("//a[normalize-space()='Log out']")));

    }

    public void logoutAfterRegister () {
        driver.get("https://demowebshop.tricentis.com/");
        select(By.xpath("//a[normalize-space()='Log out']"));
    }

    public void login (String Email, String Password) {
        select(By.xpath("//a[normalize-space()='Log in']"));

        select(By.name("Email"));
        typeData(By.name("Email"),Email);

        select(By.name("Password"));
        typeData(By.name("Password"), Password);

        select(By.xpath("//input[@value='Log in']"));

        Assert.assertTrue(isElementPresent(By.xpath("//a[normalize-space()='Log out']")));
    }

    public void logoutAfterLogin () {
        select(By.xpath("//a[normalize-space()='Log out']"));
    }
}
