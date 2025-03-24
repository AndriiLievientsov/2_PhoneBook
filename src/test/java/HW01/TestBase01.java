package HW01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase01 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod (enabled = false)
    public void tearDown() {
        driver.quit();
    }


    // ? универсальный метод на проверку наличия элемента на странице
    public boolean isElementHere (By locator) {
//        System.out.println("Есть ли элемент [" + locator + "] нас странице"); не особо надо
        return !driver.findElements(locator).isEmpty();
        //return driver.findElements(locator).size()>0;
    }

    // ? универсальный метод для нажатия чтоб не писать driver.findElement
    public void click (By locator) {
        driver.findElement(locator).click();
    }

    // ? универсальный метод для ввода данных в поля регистрации
    public void type (By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void register (String email, String password) {
        click(By.xpath("//a[(.= 'LOGIN')]"));
        click(By.name("email"));
        type(By.name("email"),email);

        click(By.name("password"));
        type(By.name("password"),password);

        click(By.name("registration"));

        Assert.assertTrue(isElementHere(By.xpath("//button [.='Sign Out']")));

    }

    public void login(String email, String password) {
        click(By.xpath("//a[(.= 'LOGIN')]"));

        click(By.name("email"));
        type(By.name("email"),email);

        click(By.name("password"));
        type(By.name("password"),password);

        click(By.name("login"));

        Assert.assertTrue(isElementHere(By.xpath("//button [.='Sign Out']")));
    }

    public void logout () {
        click(By.xpath("//button [.='Sign Out']"));
    }
}
