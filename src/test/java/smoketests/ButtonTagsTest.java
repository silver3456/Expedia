package smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ButtonTagsTest {

    WebDriver driver;

    @Test
    public void loginElementsPresentTest() {
        System.out.println("Running test");
        boolean signUpButton = false;

        //Click Account and Sign in to get a login form

        driver.findElement(By.xpath("//button[@id='header-account-menu']")).click();
        driver.findElement(By.xpath("//a[@id='account-signin']")).click();


        //We want to test the presense of button tags
        List<WebElement> buttonElemnts = driver.findElements(By.tagName("button"));

        for (WebElement buttonElement : buttonElemnts) {
            System.out.println(buttonElement.getText());
            if (buttonElement.getText().equals("Sign Up")) {
                signUpButton = true;
                break;
            }
        }
        //Assertion
        Assert.assertTrue(signUpButton);
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
