package utilities;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

public class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by + "found");
    }

    //Invoke method when element is not found, and exception will be thrown
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println(throwable);

        //cast driver to TakeScreenshot type
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //File will be created in the current directory
        File screen = new File("screen-" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screen); //Class Files is in Guava Library
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(screen);
    }
}
