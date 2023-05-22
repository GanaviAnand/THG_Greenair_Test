import com.google.common.io.Files;
import enums.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.utils.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigurationManager;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    public BaseTest() {

        //Initialize the log4j logger
        log = LogManager.getLogger(BaseTest.class.getName());
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        log.info("Initiating the test execution.");
    }

    @BeforeMethod
    public void initializeTestEnvironment() {
		System.setProperty(“webdriver.chrome.driver”, “D:\\Chrome\\chromedriver.exe”);
        WebDriver driver=new ChromeDriver();
		deleteAllCookies();
		getApplicationURL();
        driver.get(url);
        log.info("Navigated to the application URL: " + url);
        driver.manage().window().maximize();
        log.debug("Maximized the browser window.");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            log.error("Test case failed.");
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File screenshot = scrShot.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("screenshots/" + result.getName() + ".png"));
                log.info("Saved the screenshot taken at the test failure state.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            deleteAllCookies();
            driver.quit();
            log.info("Closed the webdriver.");
        }
    }
    private void deleteAllCookies() {
        driver.manage().deleteAllCookies();
        log.info("Deleted all cookies.");
    }
	
	private String getApplicationURL() {
        String url = coreConfig.getProperty(environment + "https://thg.greenair.dev.wgv.onpier.de/onboarding");
        log.info("Application URL: " + url);
        return url;
    }
	
}
