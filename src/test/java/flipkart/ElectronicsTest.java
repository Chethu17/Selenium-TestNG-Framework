package flipkart;

import Base.BaseTest;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class ElectronicsTest extends BaseTest {
	String parent;

    @Test(groups="smoke")
    public void searchLaptop() throws InterruptedException {
    	driver.findElement(By.xpath("//div[text()='Electronics']")).click(); //going to electronics tab
    	Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("laptop"); //search laptop
        Thread.sleep(2000);
        List<WebElement> sugg = driver.findElements(By.cssSelector("div[class='lfFUxn'] li[class='Swx5kP']"));
		for (WebElement option : sugg) {
			System.out.println(option.getText());
			if(option.getText().contains("under 40000")) {
				option.click();
				break;
			}
		}
		
    }

    
    @Test(groups="smoke", dependsOnMethods="searchLaptop")
    public void applyFilter() throws InterruptedException {
       // driver.findElement(By.xpath("//div[@class='_6odwB UHMz4K'][normalize-space()='Brand']")).click(); //select brand
        driver.findElement(By.xpath("//div[text()='Brand']")).click();
        driver.findElement(By.xpath("//div[text()='HP']")).click();
        Thread.sleep(2000);
        // Click Motorola (if available)
        driver.findElement(By.xpath("//div[text()='Brand']")).click();
        driver.findElement(By.xpath("(//div[@class='GN2Hca rQQNAD'])[1]")).click();
        //driver.findElement(By.xpath("//div[text()='MOTOROLA']")).click();
        
        Actions act = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[text()='MOTOROLA']"));
		act.moveToElement(element).perform();
		element.click();
        
        Thread.sleep(2000);
    }
    
    @Test(groups="regression",priority=1)
    public void scrollDown() {
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");
    }   
    
    @Test(groups="smoke", dependsOnMethods="applyFilter")
    public void openProduct() throws InterruptedException {
        //driver.findElement(By.xpath("//a")).click();
        Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("(//div[@class='RG5Slk'])[11]"));
		act.moveToElement(element).perform();
		element.click();
		Thread.sleep(2000);
    }
    
    @Test(groups="regression", dependsOnMethods="openProduct")
    public void switchWindow() throws InterruptedException {
    	parent = driver.getWindowHandle();
    	for (String win : driver.getWindowHandles()) {
    	    if (!win.equals(parent)) {
    	        driver.switchTo().window(win);
    	        break;
    	    }
    	}
        //click on buy 
    	Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@class='OmE16y']//div[@class='grid-formation grid-column-2'])[2]")).click(); 
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	//WebElement buyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.OmE16y div.grid-formation.grid-column-2:nth-of-type(2)")));
        Thread.sleep(2000);
    }
    
    @Test(groups="regression", dependsOnMethods="switchWindow")
    public void closeBrowserTab() {
        driver.close();
        driver.switchTo().window(parent); 
    }

    @Test(groups="regression", timeOut=4000, dependsOnMethods="searchAgain")
    public void waitExample() throws Exception {
        Thread.sleep(2000);
    }

    @Test(groups="regression", dependsOnMethods="closeBrowserTab")
    public void searchAgain() throws InterruptedException {
    	driver.findElement(By.name("q")).sendKeys(Keys.CONTROL+"a");
    	driver.findElement(By.name("q")).sendKeys(Keys.DELETE);
        driver.findElement(By.name("q")).sendKeys("tv"); //("tv" + keys.ENTER)
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

  
}