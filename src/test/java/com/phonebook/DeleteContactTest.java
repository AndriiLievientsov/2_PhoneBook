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
        login("anlii20280test2028@gmail.com", "Password102$");
        addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContractTest() {
        int sizeBefore = actualSizeOfContacts();
        System.out.println("Size before deletion: " + sizeBefore);
        deleteOneContact();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(CONTACT_LOCATOR),sizeBefore));
        int sizeAfterDelete = actualSizeOfContacts();
        System.out.println("Size after deletion: " + sizeAfterDelete);
        Assert.assertEquals(sizeBefore-1, sizeAfterDelete, "Count is not equal");

    }

    @Test
    public void deleteAllContactsTest () {
        deleteAllContacts();
        Assert.assertEquals(actualSizeOfContacts(), 0, "Count is not equal");
    }

}
