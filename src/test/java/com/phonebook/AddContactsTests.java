package com.phonebook;

import com.phonebook.model.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AddContactsTests extends TestBase {

    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserHelper().login("anlii2025test.2025@gmail.com", "Password101$");
    }

    @Test(invocationCount = 1, priority = 1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test(priority = 2)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }


    @DataProvider
    public Iterator<Object[]> addContactsString() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Name 111", "LastName 1", "1234567891", "abc1@gmail.com", "Ukraine 1", "description 1"});
        list.add(new Object[]{"Name 2", "LastName 2", "1234567892", "abc2@gmail.com", "Ukraine 2", "description 2"});
        list.add(new Object[]{"Name 3", "LastName 3", "1234567893", "abc3@gmail.com", "Ukraine 3", "description 3"});
        list.add(new Object[]{"Name 4", "LastName 4", "1234567894", "abc4@gmail.com", "Ukraine 4", "description 4"});
        list.add(new Object[]{"Name 555", "LastName 5", "1234567895", "abc4@gmail.com", "Ukraine 5", "description 5"});
        return list.iterator();

    }

    @Test(dataProvider = "addContactsString")
    public void addContactStringTest(String name, String lastName, String phone, String email, String address, String desc) {
        app.getContactHelper().clickAddLink();
        app.getContactHelper().fillInNewContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(desc));


        app.getContactHelper().clickSaveContactButton();
    }

    @AfterMethod(enabled = true)
    public void postCondition() {
        app.getContactHelper().deleteOneContact();
       // app.getUserHelper().logout();
    }

}
