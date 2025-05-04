package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser","chrome"));

    @BeforeMethod
    public void setUp () {
        app.init();

    }

    @AfterMethod (enabled = true)
    public void tearDown () {
        app.stop();
    }




}
