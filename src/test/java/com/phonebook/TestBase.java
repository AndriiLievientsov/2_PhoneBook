package com.phonebook;

import com.phonebook.fw.UseHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase  extends UseHelper {

    protected final String CONTACT_LOCATOR = "contact-item_card__2SOIM";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp () {
        init();

    }

    private void init() {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //неявное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterMethod (enabled = true)
    public void tearDown () {
        stop();
    }

    private void stop() {
        driver.quit();
    }

//Ниже пример JavaDoc просто даем этот код метода gpt и просим написать javaDoc

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


    protected void addNewContactPositiveDataWODescription(String name) {
        clickAddLink();
        fillInNewContactForm(new Contact()
                .setName(name)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("abc1@gmail.com")
                .setAddress("Ukraine, Kutuzova"));
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
