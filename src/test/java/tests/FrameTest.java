package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FrameTest {

    private WebDriver driver;

    @Test
    public void switchFrame() {
        // Step 2: Switch to first frame and then find First Name and Last name
        // element

        /* Switch to the first frame, remember frame index starts from 0 */
        driver.switchTo().frame(0);

        /* now find the First name field */
        WebElement firstName = driver.findElement(By
                .xpath("//*[@id=\"content\"]/div[1]/div/div/div/div[2]/div/form/fieldset/div[8]/input"));

        /* now find the Last name field */
        WebElement lastName = driver.findElement(By
                .xpath("//*[@id=\"content\"]/div[1]/div/div/div/div[2]/div/form/fieldset/div[11]/input"));
        //Step 3: Fill some value in the first name and last name files
        firstName.sendKeys("Virender");
        lastName.sendKeys("Singh");

        // Step 4: Switching to the 2nd frame, frame index 1
        driver.switchTo().defaultContent();
        driver.switchTo().frame(1);

        //Step 5: Find button and click on that
//        WebElement buttonElement = driver.findElement(By
//                .xpath("//*[@id=\"post-1\"]/footer/p[1]/a"));
//
//        buttonElement.click();
//        System.out.println("Switching successfull");

        WebElement postElem = driver.findElement(By.xpath("//*[@id=\"post-1\"]/header/h2/a"));
        postElem.getText();


    }

    @BeforeMethod
    public void init() {
        driver = utilities.DriverFactory.open("Chrome");
        driver.get("https://www.toolsqa.com/iframe-practice-page/");
    }
}