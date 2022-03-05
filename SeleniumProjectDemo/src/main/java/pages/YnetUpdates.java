package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class YnetUpdates {
	
	private static WebElement element = null ; 
	
	public static WebDriver getYnet(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.ynetnews.com");
		
		return driver;
	}
	
	public static Boolean verifyUpdates(WebDriver driver) {
		
		Boolean isEnabled = false; 
		Boolean isDisplayed = false;
		String updates_xpath = "//div[@class='animationDiv']";
		
		driver = getYnet(driver);
		
		// Find Updates 
		element =  driver.findElement(By.xpath(updates_xpath));
		
		// Check if exist 
		isEnabled = element.findElement(By.xpath(updates_xpath)).isEnabled();
		isDisplayed = element.findElement(By.xpath(updates_xpath)).isDisplayed();
		
		return isEnabled == true && isDisplayed == true ;
	}
		
	public static Boolean updatesMovingMouse(WebDriver driver) throws InterruptedException {
		
		driver = getYnet(driver);
		driver.manage().window().maximize();
		Actions action = new Actions(driver);
		Boolean isEnabled = false; 
		Boolean isDisplayed = false;
		String updates_xpath = "//div[@class='animationDiv']";
		
		// Find Updates 
		element = driver.findElement(By.xpath(updates_xpath));
		// Get Animation State before moving the mouse
		System.out.println(element.getCssValue("animation-play-state"));

		action.moveToElement(element).perform();;
		Thread.sleep(1000);
		// Get Animation State after moving the mouse
		System.out.println(element.getCssValue("animation-play-state"));
		
		return isEnabled == true && isDisplayed == true ;
		
	}
	
}
