package browser_stack;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTest {

    public static final String USERNAME = "aleksandrserof1";
    public static final String ACCESSKEY = "qZpBLEb9xfc3XpbMqp6a";
    public static final String URL = "http://"+USERNAME+":"+ ACCESSKEY+ "@hub-cloud.browserstack.com/wd/hub";

    @Test
    public void openSTM() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.MAC);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("57");
        capabilities.setCapability("browserstack.debug", true);


        URL browserStackURL = new URL(URL);
        WebDriver driver = new RemoteWebDriver(browserStackURL, capabilities);




        driver.get("https://www.expedia.com");
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        String actualUrl = driver.getCurrentUrl();
        String expectedURL = "https://www.expedia.com/";

        Assert.assertEquals(actualUrl, expectedURL, "URLs are not the same");

        driver.quit();

    }
}
