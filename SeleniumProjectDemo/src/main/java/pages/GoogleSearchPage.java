package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
	
	private static WebElement element = null;
	
	// Get Google text box search
	public static WebElement textbox_search(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.google.com");
		element = driver.findElement(By.name("q"));
		return element;
		
	}
	
	// Get Google search button
	public static WebElement button_search(WebDriver driver) {
		
		element = driver.findElement(By.name("btnK"));
		return element;
		
	}
	
	// Go to Ynet Website by it's title on Google search page
	public static WebElement ynetnews(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("ynetnews - Homepage")));
		element.click();
		return element;
	}
	
	// Simple function helps to enter giving website by String
	public static WebElement gotoWebsite(WebDriver driver, String site) {
		textbox_search(driver).sendKeys(site);
		button_search(driver).click();
		element = ynetnews(driver);
		
		return element;
	}
	
}
