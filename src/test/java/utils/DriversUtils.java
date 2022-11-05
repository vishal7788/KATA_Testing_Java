package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class DriversUtils {
    static WebDriver driver;

    public static void initDriver() {
        //Chrome => The chrome browser on my system is not updated so could not use chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        //Firefox => The latest version of gecko driver throwing an error
        /*System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\FireFox\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette",true);
        driver = new FirefoxDriver();*/
        /*System.setProperty("webdriver.edge.driver", "C:\\SeleniumDrivers\\Edge\\msedgedriver.exe");
        driver = new EdgeDriver();*/
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (driver == null ) {
            initDriver();
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
        driver = null;
    }
}

