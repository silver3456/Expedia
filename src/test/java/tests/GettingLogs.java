package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class GettingLogs {

    private WebDriver driver;

    @Test
    public void getBrowserLogs() {
        driver.navigate().to("https://www.expedia.com");
        System.out.println(driver.manage().logs().getAvailableLogTypes());

        //Get browser's logs
       // driver.manage().logs().get("browser").forEach(l-> System.out.println(l));

        //Get performance logs
        driver.manage().logs().get("performance").forEach(l-> System.out.println(l));
    }


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexander/workspace/Java_Yaroslavl/target/classes/webdriver/chromedriver");
        //Enabling performance Log
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(cap); // pass initialized driver to EventFiringWebDriver
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.get("https://www.expedia.com");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }
}
