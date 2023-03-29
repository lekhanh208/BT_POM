package common;

import drivers.DriverManager;
import keyword.KeyWord;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

import static drivers.DriverManager.*;;
import static keyword.KeyWord.*;

public class BaseTest {

    @BeforeTest
    public void createBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DriverManager.setDriver(driver);
    }


    @AfterTest
    public void closeBrowser() {
        if (getDriver() != null) {
            quit();
        }
    }
}