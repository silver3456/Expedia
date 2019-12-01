package tests.alex_kaiser;

import net.lightbody.bmp.core.har.Har;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileOutputStream;

public class PerfTest {
    public static void main(String[] args) throws Exception {
        String strFilePath = "";

        // start the proxy
        ProxyServer server = new ProxyServer(4444);
        server.start();
        //captures the moouse movements and navigations
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);

        // get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // start the browser up
        WebDriver driver = new ChromeDriver(capabilities);

        // create a new HAR with the label "apple.com"
        server.newHar("assertselenium.com");

        driver.get("http://assertselenium.com");

        // get the HAR data
       // Har har = server.getHar();
        FileOutputStream fos = new FileOutputStream(strFilePath);
      //  har.writeTo(fos);
        server.stop();
        driver.quit();
    }
}
