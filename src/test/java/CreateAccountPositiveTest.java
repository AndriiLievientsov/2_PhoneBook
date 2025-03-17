import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountPositiveTest  extends TestBase{

    @Test
    public void createAccountPositiveTest1 () {
        //click on Login link //a[(.= 'LOGIN')]
        click(By.xpath("//a[(.= 'LOGIN')]"));

        // enter email in input By.name("email")
        //driver.findElement(By.name("email")).click(); нажал на поле email - Старый клик
        type(By.name("email"), "anlii205test.2023@gmail.com");

        // enter password in input By.name ("password")
        //driver.findElement(By.name("password")).click();  нажал на поле password  - Старый клик
        click(By.name("password")); // Новый клик через Метод

        //driver.findElement(By.name("password")).clear();  очистил поле на всякий от автозаполнения
        //driver.findElement(By.name("password")).sendKeys("Password101$");  ввел валидное значение
        type(By.name("password"), "Password101$");

        // click on registration button By.name ('registration')
        //driver.findElement(By.name("registration")).click();
        click(By.name("registration"));

        // Assert that button //button [.='Sing Out'] is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));

    }

    @Test
    public void createAccountPositiveTest2 () {
        register("anlii2025test.2025@gmail.com", "Password101$");

    }

    @Test
    public void createAccountPositiveTest () {
        register("anlii2027test2028@gmail.com", "Password102$");
        logout();
        login("anlii2027test2028@gmail.com", "Password102$");

        System.out.println("Создал. Вышел И зашел");
    }


}
