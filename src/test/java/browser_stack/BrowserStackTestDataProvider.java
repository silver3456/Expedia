package browser_stack;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTestDataProvider {

    public static final String USERNAME = "aleksandrserof1";
    public static final String ACCESSKEY = "qZpBLEb9xfc3XpbMqp6a";
    public static final String URL = "http://" + USERNAME + ":" + ACCESSKEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test(dataProvider = "browserStackTestData")
    public void openSTM(Platform platform, String browserName, String versionName) throws IOException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(platform);
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(versionName);
        capabilities.setCapability("browserstack.debug", true);



        URL browserStackURL = new URL(URL);
        WebDriver driver = new RemoteWebDriver(browserStackURL, capabilities);

//        Actions actions = new Actions(driver);
//        actions.


        driver.get("https://www.expedia.com");
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);



        //Take screenshots
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File ("/Users/alexander/SDET_Files/screenshots/screenshot.png"));



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
