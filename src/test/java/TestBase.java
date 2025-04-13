import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    WebDriver driver;

    @BeforeMethod
    public void setUp () {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod (enabled = false)
    public void tearDown () {
        driver.quit();
    }

//Ниже пример JavaDoc просто даем этот код метода gpt и просим написать javaDoc
    /**
     * Проверяет наличие элемента на текущей странице по заданному локатору.
     *
     * @param locator локатор элемента, который необходимо проверить (например, By.id, By.name и т.д.).
     * @return {@code true}, если элемент присутствует на странице (найден хотя бы один),
     *         {@code false} — если элемент отсутствует.
     */
    public boolean isElementPresent(By locator) {
        System.out.println("Есть лит элемент [" + locator + "] на странице");
        return driver.findElements(locator).size()>0;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator); // Новый клик через Метод

        driver.findElement(locator).clear(); // очистил поле на всякий от автозаполнения
        driver.findElement(locator).sendKeys(text); // ввел валидное значение
    }

    public void login(String email, String password) {
        //click on login link
        click(By.xpath("//a[(.= 'LOGIN')]"));
        //enter email
        type(By.name("email"), email);
        //enter password
        type(By.name("password"), password);
        //click on Login button
        click(By.name("login"));
        // assert that Sign out is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
    }

    public void register(String email, String password) {
        //click on Login link //a[(.= 'LOGIN')]
        click(By.xpath("//a[(.= 'LOGIN')]"));

        // enter email in input By.name("email")
        //driver.findElement(By.name("email")).click(); нажал на поле email - Старый клик
        type(By.name("email"), email);

        // enter password in input By.name ("password")
        //driver.findElement(By.name("password")).click();  нажал на поле password  - Старый клик
        click(By.name("password")); // Новый клик через Метод

        //driver.findElement(By.name("password")).clear();  очистил поле на всякий от автозаполнения
        //driver.findElement(By.name("password")).sendKeys("Password101$");  ввел валидное значение
        type(By.name("password"), password);

        // click on registration button By.name ('registration')
        //driver.findElement(By.name("registration")).click();
        click(By.name("registration"));

        // Assert that button //button [.='Sing Out'] is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
    }

    public void logout() {
        click(By.xpath("//button [.='Sign Out']"));
    }
}
