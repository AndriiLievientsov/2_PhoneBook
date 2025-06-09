package HW2.coreHW2;

import HW2.fwHW2.UserHelperHW2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManagerHW2 {
        WebDriver driver;
        public WebDriverWait wait;

        UserHelperHW2 userHelperHW2;
        CartHW2 cartHW2;

        private final String browser;

        public ApplicationManagerHW2 (String browser) {
            this.browser = browser;
        }

        public void initHW2 () {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("windows-size=1920x1080");
                driver = new ChromeDriver();
            }
            else if (browser.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")){
                driver = new EdgeDriver();
            }else if (browser.equalsIgnoreCase("safari")){
                driver = new SafariDriver();
            }
            driver.get("https://demowebshop.tricentis.com/register");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            userHelperHW2 = new UserHelperHW2(driver, wait);
            cartHW2 = new CartHW2(driver, wait);

        }


    public CartHW2 getCartHW2() {
        return cartHW2;
    }

    public UserHelperHW2 getUserHelperHW2() {
        return userHelperHW2;
    }

    public void stop() {
        driver.quit();
    }
}
