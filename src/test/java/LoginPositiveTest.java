import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTest extends TestBase {

    @Test
    public void loginExistedUserPositiveTest () {
        //click on login link
        click(By.xpath("//a[(.= 'LOGIN')]"));
        //enter email
        type(By.name("email"), "anliji225test.2023@gmail.com");
        //enter password
        type(By.name("password"), "Password101$");
        //click on Login button
        click(By.name("login"));
        // assert that Sign out is present
        Assert.assertTrue(isElementPresent(By.xpath("//button [.='Sign Out']")));
    }

    @Test
    public void loginExistedUserPositiveTest2 () {
        login("anlii2025test.2025@gmail.com", "Password101$");

    }

}
