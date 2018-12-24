package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {


    //This method returns a WebDriver object
    public static WebDriver open(String browserType) {

        if (browserType.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        } else {
            return new FirefoxDriver();
        }
    }
}
