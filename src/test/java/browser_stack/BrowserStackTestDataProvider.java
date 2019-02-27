package browser_stack;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTestDataProvider {

    public static final String USERNAME = "aleksandrserof1";
    public static final String ACCESSKEY = "qZpBLEb9xfc3XpbMqp6a";
    public static final String URL = "http://" + USERNAME + ":" + ACCESSKEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test(dataProvider = "browserStackTestData")
    public void openSTM(Platform platform, String browserName, String versionName) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(platform);
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(versionName);
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

    @DataProvider(name = "browserStackTestData", parallel = true)
    public Object[][] getData() {
        Object[][] testData = new Object[][]{
                {Platform.MAC, "chrome", "62.0"},
                {Platform.WIN8, "chrome", "62.0"},
                {Platform.WINDOWS, "firefox", "57"}
        };
        return testData;
    }
}
