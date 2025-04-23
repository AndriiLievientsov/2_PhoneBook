package com.phonebook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase {

    protected final String CONTACT_LOCATOR = "contact-item_card__2SOIM";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp () {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    @AfterMethod (enabled = true)
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

        if (text != null) {

            click(locator); // Новый клик через Метод

            driver.findElement(locator).clear(); // очистил поле на всякий от автозаполнения
            driver.findElement(locator).sendKeys(text); // ввел валидное значение
        }


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

    protected void fillInRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
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
    //(name, "LastName", "1234567890", "abc1@gmail.com", "Ukraine, Kutuzova", "description")
    protected void addNewContactPositiveData(String name) {
        clickAddLink();
        fillInNewContactForm(new Contact()
                .setName(name)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("abc1@gmail.com")
                .setAddress("Ukraine, Kutuzova")
                .setDescription("description"));


        clickSaveContactButton();
    }

    private void fillInNewContactForm(Contact constructor) {
        type(By.xpath("//input[@placeholder='Name']"), constructor.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), constructor.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), constructor.getPhone());
        type(By.xpath("//input[@placeholder='email']"), constructor.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), constructor.getAddress());
        type(By.xpath("//input[@placeholder='description']"), constructor.getDescription());
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

    public boolean isHomeComponentPresent() {
        System.out.println("Looking for 'homecomponent' on the home page");
        return isElementPresent(By.xpath("//html/body/div/div/div/div/h1"));
    }


    protected void deleteAllContacts() {
        try {
            while (hasContacts()) {
                int sizeBefore = actualSizeOfContacts();
                deleteOneContact();
                wait.until((WebDriver d) -> actualSizeOfContacts() < sizeBefore);
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected int actualSizeOfContacts() {
        if(hasContacts()) {
            //считает количество контактов по классу contact-item_card__2SOIM
            return driver.findElements(By.className(CONTACT_LOCATOR)).size();
        }
        return 0;
    }

    private boolean hasContacts () {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }
}
