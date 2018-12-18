package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExpediaTest {

    private WebDriver driver;
    private String city = "New York, NY";
    private String checkIn = " 12/22/2018";
    private String checkOut = " 12/27/2018";
    String numOfGuests = "3";


    @Test
    public void hotelReservation() throws InterruptedException {

        //1.Search
        driver.findElement(By.xpath("//*[@id='tab-hotel-tab-hp']")).click();
        driver.findElement(By.xpath("//*[@id='hotel-destination-hp-hotel']")).clear();
        driver.findElement(By.xpath("//*[@id='hotel-destination-hp-hotel']")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id='hotel-checkin-hp-hotel']")).sendKeys(checkIn);
        driver.findElement(By.xpath("//*[@id='hotel-checkout-hp-hotel']")).sendKeys(checkOut);

        //Getting an error message : Element should have been “select” but was “div” getting an error in selenium
        //(This exception generally occurs when we use Select command to select dropdowns which are not built by using "select" tag.)
        //new Select(driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]"))).selectByValue(numOfGuests);

        driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]")).click();

        //click plus button several times
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]/div/ul/li/div/div/div[1]/div[2]/div[4]/button/span[1]")).click();
            Thread.sleep(3000);

        }

        driver.findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]")).click();

        //2.Modify the search results page, give criteria


        //3.Analyze the results and make your selection


        //4.Book reservation


        //5.Fill out contact/billing


        //6.Get confirmation

    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

}