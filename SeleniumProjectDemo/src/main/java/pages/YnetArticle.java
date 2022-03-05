package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YnetArticle {
	
	public static WebElement element = null ; 
	private static String newCommentBtn_xpath = "//*[@id=\"SiteArticleComments\"]/div[1]/div/div[2]/button";
	private static String sendBtn_xpath = "//*[@id=\"addCommentSendButton\"]";
	
	public static WebDriver findArticleNewCommentBtn(WebDriver driver) throws InterruptedException {
		
		// Go to article directly
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.ynetnews.com/magazine/article/ry00vrlrxc");
		
		// Click on "new comment" button 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newCommentBtn_xpath)));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		System.out.println("New comment button clicked...");
		// 	New comment button should disappear 
		if(element.isEnabled() == false) {
			
			// Click on "new comment" button 
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
			element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(sendBtn_xpath)));
			
			// Make sure "send comment" button is enabled and displayed 
			while(!element.isEnabled() && !element.isDisplayed()) {
				System.out.println("send comment not enabled yet...");
				Thread.sleep(1000);
			}
			System.out.println("Send button enabled...");
			
			if(element.isDisplayed()) {
				Thread.sleep(5000);
				Thread.sleep(5000);
				System.out.println("Send button ready to be clicked...");
				// Click on "send comment" button with JavascriptExecutor 
				JavascriptExecutor executor2 = (JavascriptExecutor)driver;
				executor2.executeScript("arguments[0].click();", element);
				
				Alert alert = driver.switchTo().alert();		
		  		
		        // Capturing alert message.    
		        String alertMessage= driver.switchTo().alert().getText();		
		        		
		        // Displaying alert message		
		        System.out.println("Alert displayed : " + alertMessage);	
		        Thread.sleep(5000);
		        		
		        // Accepting alert		
		        alert.accept();	
		        System.out.println("Alert Accepted!");
				assert alertMessage.equals("You must enter a headline");
			}
		}
		
		return driver ;
	}
	
}
