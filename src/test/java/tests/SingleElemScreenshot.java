package tests;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.MyListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SingleElemScreenshot {

    private WebDriver driver;
    // private WebElement accountElem ;


    @Test
    public void takeScreenshot() throws IOException {
        WebElement accountElement = driver.findElement(By.xpath("//button[@id='header-account-menu']"));

        // Get entire page screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        Point point = accountElement.getLocation();

        // Get width and height of the element
        int eleWidth = accountElement.getSize().getWidth();
        int eleHeight = accountElement.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);


        // Copy the element screenshot to disk
        File screenshotLocation = new File("/Users/alexander/SDET_Files/Reporting", "screen-" + System.currentTimeMillis() + ".png");
        Files.copy(screenshot, screenshotLocation);

    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // pass initialized driver to EventFiringWebDriver
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
    }
}
