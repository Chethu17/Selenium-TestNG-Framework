package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() throws InterruptedException {

        System.out.println("SETUP STARTED");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.flipkart.com");
        
     // Close login pop-up
        Thread.sleep(1000);
        try {
            driver.findElement(By.xpath("//span[@role='button']")).click();
        } catch (Exception e) {
            System.out.println("Popup not present");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("TEARDOWN");
        if (driver != null) {
            driver.quit();
        }
    }
}