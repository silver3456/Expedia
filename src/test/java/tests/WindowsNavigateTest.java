package tests;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowsNavigateTest {

    private WebDriver driver;
    public WebDriverWait wait;
    private String mainLink;
    private String newLink;

    @Test
    public void navigateToWindowTest() {


        String mainWindow = driver.getWindowHandle(); //save id of a current open window
        System.out.println("Main window:" + mainWindow);
        Set<String> existingWindows = driver.getWindowHandles(); // save id's of already open windows
        //System.out.println(existingWindows);
        ((JavascriptExecutor) driver).executeScript("window.open()"); //open new window(tab)
        String newWindow = wait.until(anyWindowWindowOtherThan(existingWindows));//wait for a new window with new ID
        driver.switchTo().window(newWindow); //switch to new window
        driver.get(newLink);
        String windowTwo = driver.getWindowHandle();
        System.out.println("New window:" + windowTwo);
        driver.close(); //close current window
        driver.switchTo().window(mainWindow); //go back to initial window
        System.out.println("Main window:" + mainWindow);

    }

    public ExpectedCondition<String> anyWindowWindowOtherThan(Set<String> existingWindows) throws NullPointerException{
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(existingWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };

    }

    @BeforeMethod
    public void setUp() {
        initLinks();
        driver = new ChromeDriver(); // pass initialized driver to EventFiringWebDriver
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(mainLink);

        wait = new WebDriverWait(driver, 9);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void initLinks() {
        mainLink = "https://www.expedia.com/";
        newLink = "https://ru.airbnb.com/";

    }
}
