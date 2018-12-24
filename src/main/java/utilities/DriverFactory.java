package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {


    //This method returns a WebDriver object
    public static WebDriver open(String browserType) {

        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-fullscreen");
            return new ChromeDriver(options);
        } else {
            return new FirefoxDriver();
        }
    }
}
