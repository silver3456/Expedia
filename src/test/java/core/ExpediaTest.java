package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ExpediaTest {

    private WebDriver driver;
    private String city = "New York, NY";
    private String checkIn = " 12/27/2018";
    private String checkOut = " 12/30/2018";
    private String starRating = "star4";
    private String numOfGuests = "4";


    @Test
    public void hotelReservation() throws InterruptedException {

        //1.Search
        driver.findElement(By.xpath("//*[@id='tab-hotel-tab-hp']")).click();
        driver.findElement(By.xpath("//*[@id='hotel-destination-hp-hotel']")).clear();
        driver.findElement(By.xpath("//*[@id='hotel-destination-hp-hotel']")).sendKeys(city);
        //driver.findElement(By.xpath("//*[@id='hotel-checkin-hp-hotel']")).clear();
        driver.findElement(By.xpath("//*[@id='hotel-checkin-hp-hotel']")).sendKeys(checkIn);
       // driver.findElement(By.xpath("//*[@id='hotel-checkout-hp-hotel']")).clear();
        driver.findElement(By.xpath("//*[@id='hotel-checkout-hp-hotel']")).sendKeys(checkOut);
//        driver.findElement(By.xpath("//*[@id=\"hotel-checkout-hp-hotel\"]")).clear();
//        driver.findElement(By.xpath("//*[@id=\"hotel-checkout-hp-hotel\"]")).sendKeys(checkOut);
        //Getting an error message : Element should have been “select” but was “div” getting an error in selenium
        //(This exception generally occurs when we use Select command to select dropdowns which are not built by using "select" tag.)
        //new Select(driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]"))).selectByValue(numOfGuests);

        driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]")).click();

        //click plus button several times
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//*[@id=\"traveler-selector-hp-hotel\"]/div/ul/li/div/div/div[1]/div[2]/div[4]/button/span[1]")).click();
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[10]/label/button")).click();

        //Print the name of the city

        Thread.sleep(2000);


        try {
            String actualCity = driver.findElement(By.xpath("//h1[@class = 'section-header-main']")).getText();
            System.out.println("CITY: " + actualCity);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("City element is not found");
        }


        //2.Modify the search results page, give criteria
        driver.findElement(By.xpath("//input[@id = '" + starRating + "']")).click();
        Thread.sleep(2000);


        //3.Analyze the results and make your selection
        driver.findElement(By.xpath("//*[@id=\"resultsContainer\"]/section/article[2]/div[2]/div/a")).click();
        //driver.findElement(By.xpath("//*[@id='resultsContainer']/section/article[2]/div[2]/div/a")).click();


        //Switch the window to the pop up
        ArrayList<String>windows= new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));


        //Print hotel name and star rating
        String hotelName = driver.findElement(By.xpath("//*[@id = 'hotel-name']")).getText();
        System.out.println(hotelName);
        Assert.assertEquals("6 Columbus - a SIXTY Hotel", hotelName);

        //4.Book reservation
        driver.findElement(By.xpath("//*[@id='mock-book-button']")).click();

        Thread.sleep(2000);
        //String hotelPrice = driver.findElement(By.xpath("//*[@class='summary-total amount-value']")).getText();
        String hotelPrice = driver.findElement(By.xpath("//*[@id=\"trip-summary\"]/div[2]/div[4]/div/span[2]")).getText();
        System.out.println("PRICE: " + hotelPrice);


        //5.Fill out contact/billing


        //6.Get confirmation
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Payment"));

    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        String browserVersion = options.getVersion().toString();
        System.out.println("VERSION: " + browserVersion);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

}