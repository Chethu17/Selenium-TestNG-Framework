package flipkart;

import Base.BaseTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class FashionTest extends BaseTest {

    @Test(groups="smoke", dependsOnMethods ="searchItem")
    public void searchFashion() throws InterruptedException {
    	
        Thread.sleep(2000);
        List<WebElement> sugg = driver.findElements(By.cssSelector("div[class='lfFUxn'] li[class='Swx5kP']"));
		for (WebElement option : sugg) {
			System.out.println(option.getText());
			if(option.getText().contains("shirts for men")) {
				option.click();
				break;
			}
		}
    }

    @Test(groups="smoke", dependsOnMethods="searchFashion")
    public void clickFirstProduct() {
        System.out.println(driver.findElement(By.xpath("(//div[@class='Fo1I0b'])[1]")).getText());
        driver.findElement(By.xpath("(//div[@class='p0C73x']//a[@class='atJtCj'])[1]")).click();
    }

    @Test(groups="smoke", timeOut=10000, priority=-1)
    public void hoverMenu() throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//span[normalize-space()='Login']"))).perform();
    }

    @Test(groups="regression")
    public void DscrollPage() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);
    }
/*
    @Test(groups="regression")
    public void handleAlert() {
        // dummy alert example
        driver.switchTo().alert().accept();
    }*/

    @Test(groups="regression",dependsOnMethods="clickFirstProduct")
    public void switchTab() throws InterruptedException {
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(1000);
    }

    @Test(groups="regression")
    public void explicitWaitExample() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.findElement(By.name("q")).isDisplayed());
    }

    @Test(groups="regression")
    //@Parameters("searchItem")
    public void searchItem() throws InterruptedException {
    	driver.findElement(By.xpath("(//div[@class='css-g5y9jx'])[11]")).click(); //going to fashion tab
    	Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("shirts"); //search shirts
    }
}
