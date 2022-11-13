package driver;

import data.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverManager {

    // Single-ton to control web-driver instance
    private static DriverManager driverManagerInstance =  new DriverManager();

    private DriverManager(){
        //empty
    }

    public static DriverManager getInstance(){
        return driverManagerInstance;
    }

    public WebDriver initDriverFor(BrowserType browser){

        switch (browser){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            case SAFARI:
                SafariOptions safariOptions = new SafariOptions();
                WebDriverManager.safaridriver().setup();
                return new SafariDriver(safariOptions);
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions());
        }

    }
}
