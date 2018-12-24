package smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPresentTestNG {

    WebDriver driver;

    @Test
    public void loginElementsPresentTest() {
        System.out.println("Running test");
        //Click Account and Sign in to get a login form

        driver.findElement(By.xpath("//button[@id='header-account-menu']")).click();
        driver.findElement(By.xpath("//a[@id='account-signin']")).click();

        boolean loginEmailBox = driver.findElement(By.xpath(".//*[@id = 'gss-signin-email']")).isDisplayed();
        boolean passwordBox = driver.findElement(By.xpath(".//*[@id = 'gss-signin-password']")).isDisplayed();

//        Assert.assertTrue(loginEmailBox);
//        Assert.assertTrue(passwordBox);
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Starting test");
        String webUrl = "https://www.expedia.com/";

        driver = utilities.DriverFactory.open("chrome");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(webUrl);
    }


    @AfterMethod
    public void tearDown() {
        System.out.println("Closing test");
        driver.quit();
    }
}
