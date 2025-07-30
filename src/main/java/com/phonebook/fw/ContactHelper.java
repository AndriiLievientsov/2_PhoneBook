package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper  extends BaseHelper {
    public final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    //(name, "LastName", "1234567890", "abc1@gmail.com", "Ukraine, Kutuzova", "description")
    public void addNewContactPositiveData(String name) {
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

    public void addNewContactPositiveDataWODescription(String name) {
        clickAddLink();
        fillInNewContactForm(new Contact()
                .setName(name)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("abc1@gmail.com")
                .setAddress("Ukraine, Kutuzova"));
                clickSaveContactButton();
    }

    public void fillInNewContactForm(Contact constructor) {
        type(By.xpath("//input[@placeholder='Name']"), constructor.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), constructor.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), constructor.getPhone());
        type(By.xpath("//input[@placeholder='email']"), constructor.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), constructor.getAddress());
        type(By.xpath("//input[@placeholder='description']"), constructor.getDescription());
    }

    public void clickSaveContactButton() {
        click(By.xpath("//b[contains(text(),'Save')]"));
    }

    public void clickAddLink() {
        click(By.xpath("//a[contains(text(),'ADD')]"));
    }

    public void deleteOneContact() {
        click(By.className("contact-item_card__2SOIM"));
        click(By.xpath("//button[contains(text(),'Remove')]"));
    }

    public void clickContactButton() {
        click(By.xpath("//a[contains(text(),'CONTACTS')]"));
    }

    public void dellAllContacts() {
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

    public void deleteAllContacts() {
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

    public int actualSizeOfContacts() {
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
