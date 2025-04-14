import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

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
        type(By.name("email"), email.toLowerCase());
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

    protected void fillInRegistrationForm(String email, String password) {
        type(By.name("email"), email);
        type(By.name("password"), password);
    }

    protected void clickRegistrationButton() {
        click(By.name("registration"));
    }

    protected void clickLoginLink() {
        click(By.xpath("//a[(.= 'LOGIN')]"));
    }

    protected boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button [.='Sign Out']"));
    }

    protected void clickOnLoginButton() {
        click(By.name("login"));
    }

    protected boolean isError409Present() {
        return isElementPresent(By.xpath("//div[.= 'Registration failed with code 409']"));
    }

    protected boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
         driver.switchTo().alert().accept();
         return true;
        }

    }

    protected boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    protected void addNewContactPositiveData(String name) {
        clickAddLink();
        fillInNewContactForm(
                name,
                "LastName",
                "1234567890",
                "abc1@gmail.com",
                "Ukraine, Kutuzova",
                "description");
        clickSaveContactButton();
    }

    private void fillInNewContactForm(String name, String lastName, String phone, String email, String address, String description) {
        type(By.xpath("//input[@placeholder='Name']"), name);
        type(By.xpath("//input[@placeholder='Last Name']"), lastName);
        type(By.xpath("//input[@placeholder='Phone']"), phone);
        type(By.xpath("//input[@placeholder='email']"), email);
        type(By.xpath("//input[@placeholder='Address']"), address);
        type(By.xpath("//input[@placeholder='description']"), description);
    }

    private void clickSaveContactButton() {
        click(By.xpath("//b[contains(text(),'Save')]"));
    }

    private void clickAddLink() {
        click(By.xpath("//a[contains(text(),'ADD')]"));
    }

    protected void deleteOneContact() {
        click(By.className("contact-item_card__2SOIM"));
        click(By.xpath("//button[contains(text(),'Remove')]"));
    }

    protected void clickContactButton() {
        click(By.xpath("//a[contains(text(),'CONTACTS')]"));
    }

    protected void dellAllContacts() {
        List <WebElement> elements = driver.findElements(By.cssSelector("h2"));
        for (WebElement h2 : elements){
            dellContactFor(h2);
        }
    }

    public void dellContactFor (WebElement contact) {
        contact.click();
        click(By.xpath("//button[contains(text(),'Remove')]"));
    }

    public void clickHomeButton () {
        click(By.xpath("//a[contains(text(),'HOME')]"));
    }
}
