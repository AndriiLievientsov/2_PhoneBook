import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test
    public void isComponentPresentTest () {
        System.out.println("--------" + "\n");

        driver.get("https://telranedu.web.app/home");
        Assert.assertTrue(isHomeComponentPresent(), "Элемент не найден на странице");
        System.out.println("Элемент 'HomeComponent' найден на домашней странице");
    }


    public boolean isHomeComponentPresent() {
        System.out.println("Ищем 'HomeComponent' на домашней странице");
        return isElementPresent(By.xpath("//html/body/div/div/div/div/h1"));
    }


}
