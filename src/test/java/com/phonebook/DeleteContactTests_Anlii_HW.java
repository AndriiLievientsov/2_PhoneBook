package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests_Anlii_HW extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserHelper().login("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test(invocationCount = 5)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test
    public void selectAndDellContact() {
        app.getContactHelper().clickContactButton();
        app.getContactHelper().dellAllContacts();
    }

    @AfterMethod
    public void postCondition() {
        app.getUserHelper().logout();
    }


}
