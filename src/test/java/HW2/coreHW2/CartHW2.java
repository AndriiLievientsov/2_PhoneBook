package HW2.coreHW2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartHW2 extends BaseHelperHW2{
    public CartHW2(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void selectProducts () {
        select(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[3]"));
        select(By.xpath("//div[@class='header-logo']//img[1]"));
        select(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[2]"));
        select(By.xpath("//div[@class='header-logo']//img[1]"));
        select(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[1]"));
        select(By.xpath("//div[@class='header-logo']//img[1]"));
    }

    public void selectShoppingCart () {
        select(By.xpath("(//span[@class='cart-label'])[1]"));
        Assert.assertTrue(isElementPresent(By.xpath("//input[@class='button-2 update-cart-button']")));
    }

    //поиск есть ли что-то по тексту на сайте
    public void isItemInCart () {
        Assert.assertTrue(isElementPresent(By.linkText("Build your own cheap computer")));
        System.out.println("Ожидалось, что товар 'Build your own cheap computer' будет в корзине.");
    }

}
