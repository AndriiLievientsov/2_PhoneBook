package HW01_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase_1_5 {

    WebDriver driver;

    @BeforeMethod
    public void setUp () {
        driver = new ChromeDriver();

        driver.get("https://demowebshop.tricentis.com/register");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public boolean isElementPresent (By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void select (By locator) {
        driver.findElement(locator).click();
    }

    public void typeData (By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
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
