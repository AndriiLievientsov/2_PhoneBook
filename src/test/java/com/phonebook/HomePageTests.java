package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test
    public void isComponentPresentTest () {
//        driver.get("https://telranedu.web.app/home");
        Assert.assertTrue(isHomeComponentPresent(), "The element is not found on the page");
        System.out.println("The element 'Homecomponent' found on the home page");
    }
}
