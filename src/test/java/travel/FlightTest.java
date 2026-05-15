package travel;

import Base.BaseTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class FlightTest extends BaseTest {

    @Test(groups="smoke")
    public void searchFlight() {
    	//navigate to travel
    	driver.findElement(By.xpath("(//div[@class='grid-formation grid-column-6'])[2]")).click();
    	//go to hotel and navigate back to flight
    	driver.findElement(By.xpath("(//div[@role='tab'])[2]")).click();
    	driver.findElement(By.xpath("(//div[@role='tab'])[1]")).click();        
    }

    @Test(groups="smoke")
    public void selectSource() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//div[@class='css-g5y9jx r-z2wwpe r-1phboty r-18u37iz r-h0d30l r-1yadl64 r-ah5dr5']")).click();
    	Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Search origin city/airport']")).sendKeys("Mu");
        
        List<WebElement> sugg = driver.findElements(By.xpath("//div[@class='css-g5y9jx r-5kz9s3 r-13awgt0 r-18u37iz r-ytbthy']"));
		for (WebElement option : sugg) {
			System.out.println(option.getText());
			if(option.getText().contains("Mumbai")) {
				option.click();
				break;
			}
		}
    }

    @Test(groups="smoke", dependsOnMethods="selectSource")
    public void selectDestination() throws InterruptedException {
    	Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[@class='css-g5y9jx r-z2wwpe r-1phboty r-18u37iz r-h0d30l r-1yadl64 r-ah5dr5'])[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder='Search destination city/airport']")).sendKeys("Be");
        
        List<WebElement> sugg = driver.findElements(By.xpath("//div[@class='css-g5y9jx r-5kz9s3 r-13awgt0 r-18u37iz r-ytbthy']"));
		for (WebElement option : sugg) {
			System.out.println(option.getText());
			if(option.getText().contains("Bengaluru")) {
				option.click();
				break;
			}
		}
    }

    @Parameters({"day","monthYear"})
    @Test(groups="regression", dependsOnMethods="selectDestination")
    public void selectDate(String day, String monthYear) {
    	driver.findElement(By.xpath("//div[normalize-space()='Departure']")).click();
    	//String monthYear = "July 2026";
    	//String day ="15";
    	while(true) {
    		WebElement currentMonth = driver.findElement(By.cssSelector("div[class='css-g5y9jx r-13awgt0 r-r2y082 r-1kb76zh'] div[class='css-146c3p1']"));
    		if(currentMonth.getText().contains(monthYear)) {break;}
    		else {driver.findElement(By.cssSelector("svg[width='20']")).click();}
    	}   	
        driver.findElement(By.xpath("//div[@class='css-146c3p1'][normalize-space()='"+day+"']")).click();
    }

    @Test(groups="regression", dependsOnMethods="selectDate")
    public void clickSearch() {
        driver.findElement(By.xpath("//div[@class='css-g5y9jx r-13awgt0 r-14qjzug']//div[2]")).click();
    }

    @Test(groups="regression",dependsOnMethods="clickSearch")
    public void waitFlights() throws Exception {
        Thread.sleep(2000);
    }

    @Test(groups="regression", dependsOnMethods="waitFlights")
    public void scrollFlights() {
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("window.scrollBy(0,600)");
    }

    
}