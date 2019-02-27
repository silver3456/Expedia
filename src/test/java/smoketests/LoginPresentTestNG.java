package smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MyListener;

import java.util.concurrent.TimeUnit;

public class LoginPresentTestNG extends MyListener {

    private EventFiringWebDriver driver;
    private WebElement accountElement, signInElement;

    @Test
    public void loginElementsPresentTest() {
        System.out.println("Running test");
        //Click Account and Sign in to get a login form

        defineWebElements();

        accountElement.click();
        signInElement.click();

        boolean loginEmailBox = driver.findElement(By.xpath(".//*[@id = 'gss-signin-email']")).isDisplayed();
        boolean passwordBox = driver.findElement(By.xpath(".//*[@id = 'gss-signin-password']")).isDisplayed();

//        Assert.assertTrue(loginEmailBox);
//        Assert.assertTrue(passwordBox);
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Starting test");
        String webUrl = "https://www.expedia.com/";

        driver = new EventFiringWebDriver(new ChromeDriver()); // pass initialized driver to EventFiringWebDriver
        driver.register(new MyListener()); // register Listener
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(webUrl);
    }


    @AfterMethod
    public void tearDown() {
        System.out.println("Closing test");
        driver.quit();
    }

    public void defineWebElements() {
        accountElement = driver.findElement(By.xpath("//button[@id='header-account-menu']"));
        signInElement = driver.findElement(By.xpath("//a[@id='account-signin']"));

    }
}
