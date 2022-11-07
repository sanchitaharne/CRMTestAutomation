package com.crm.qa.base;

import com.crm.qa.util.TestUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

public class TestBase {
    static WebDriver driver;
    static Properties prop;

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.of(TestUtility.PAGE_LOAD_TIMEOUT, ChronoUnit.SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.of(TestUtility.IMPLICIT_WAIT_TIMEOUT, ChronoUnit.SECONDS));

        driver.get(prop.getProperty("url"));

    }

    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "src/main/java/com/crm/qa/config/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
