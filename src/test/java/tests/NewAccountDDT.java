package tests;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {

    private WebDriver driver;

    private String firstName, lastName, email, password;
    private WebElement firstNameElement, lastNameElement, emailElement, passwordElement, signUpButtonElement;


    @Test
    public void newAccountTest() {
        System.out.println("NEW RECORD: " + firstName + " " + lastName + " " + email + " " + password);


        defineWebElements();


        //Fill out the form
        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        signUpButtonElement.click();


    }

    @Parameters
    public static List<String[]> getData() {
        return utilities.CSV.get("/Users/alexander/SDET_Files/ExpediaUserAccounts.csv");

    }

    //Constructor that passes parameters to the test method


    public NewAccountDDT(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Before
    public void setUp() {
        String webUrl = "https://www.expedia.com/";

        driver = utilities.DriverFactory.open("chrome");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(webUrl);
        driver.findElement(By.xpath("//*[@id='header-account-menu']")).click();
        driver.findElement(By.xpath("//*[@id='account-register']")).click();

    }

    @After
    public void tearDown() {
        driver.quit();

    }

    public void defineWebElements() {
        firstNameElement = driver.findElement(By.xpath("//*[@id='gss-signup-first-name']"));
        lastNameElement = driver.findElement(By.xpath("//*[@id='gss-signup-last-name']"));
        emailElement = driver.findElement(By.xpath("//*[@id ='gss-signup-email']"));
        passwordElement = driver.findElement(By.xpath("//*[@id='gss-signup-password']"));
        signUpButtonElement = driver.findElement(By.xpath("//*[@id='gss-signup-submit']"));

    }
}
