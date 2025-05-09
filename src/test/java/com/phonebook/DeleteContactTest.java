package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase{
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("anlii20280test2028@gmail.com", "Password102$");
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContractTest() {
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size before deletion: " + sizeBefore);
        app.getContactHelper().deleteOneContact();
        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContactHelper().CONTACT_LOCATOR),sizeBefore));
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        System.out.println("Size after deletion: " + sizeAfterDelete);
        Assert.assertEquals(sizeBefore-1, sizeAfterDelete, "Count is not equal");

    }

    @Test
    public void deleteAllContactsTest () {
        app.getContactHelper().deleteAllContacts();
        Assert.assertEquals(app.getContactHelper().actualSizeOfContacts(), 0, "Count is not equal");
    }

}
