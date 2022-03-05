package test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleSearchPage;
import pages.YnetWeather;
import pages.YnetArticle;
import pages.YnetUpdates;


import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class tests {

	WebDriver driver = null ;
	WebElement element = null;
	
	//Initialization WebDriver
	@Before
	public void start() {
		
		System.out.println("Initialization start...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Initialization Done.\n\n");
	}
	
	@Test
	public void test1_YnetWebsite() {
		
		System.out.println("Starting Test testYnetWebsite");
		String url = "https://www.ynetnews.com/category/3083" ;
		GoogleSearchPage.gotoWebsite(driver,"ynetnews" );
		
		assert driver.getCurrentUrl().equals(url);
		
		driver.close();
		System.out.println("Done..\n\n");
	}
	
	@Test
	public void test2_YnetWeather() {
	
		System.out.println("Starting Test testYnetWeather");
		String Weather_xpath = "//span[@class='weathertemps']";
		Boolean isEnabled = false;
		Boolean isDisplayed = false;
		
		GoogleSearchPage.gotoWebsite(driver,"ynetnews" );
		element = YnetWeather.getWeather(driver);
		
		isEnabled = element.findElement(By.xpath(Weather_xpath)).isEnabled();
		isDisplayed = element.findElement(By.xpath(Weather_xpath)).isDisplayed();
		
		assert isEnabled == true && isDisplayed == true ;
		
		driver.close();
		System.out.println("Done..\n\n");
	}
	
	@Test
	public void test3_checkYnetWeatherEilat() {
		
		System.out.println("Starting Test checkYnetWeatherEilat");
		String Weather_xpath = "//span[@class='weathertemps']";
		Boolean isEnabled = false;
		Boolean isDisplayed = false;
		GoogleSearchPage.gotoWebsite(driver,"ynetnews" );
		element = YnetWeather.getWeatherByCity(driver, "Eilat");
		
		isEnabled = element.findElement(By.xpath(Weather_xpath)).isEnabled();
		isDisplayed = element.findElement(By.xpath(Weather_xpath)).isDisplayed();
		
		assert isEnabled == true && isDisplayed == true ;
		
		driver.close();
		System.out.println("Done..\n\n");
	}
	
	
	// changed the window resolution to 1920/800 , due to screen resolution constraint 
	
	@Test
	public void test4_changeResolution() {

		System.out.println("Starting Test changeResolution ");
		driver.get("https://www.google.com");
		Dimension size = new Dimension(1920, 800);
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(1920, 800));
		System.out.println(driver.manage().window().getSize());
		System.out.println(driver.manage().window().getSize().equals(size));
		System.out.println(size);
		assert driver.manage().window().getSize().equals(size);
		driver.close();
		System.out.println("Done..\n\n");
	}

	@Test
	public void test5_testUpdates() throws InterruptedException {

		System.out.println("Starting Test testUpdates");
		Boolean isUpdatesDisplayed = false;
		driver.manage().window().setSize(new Dimension(1920, 800));
		isUpdatesDisplayed = YnetUpdates.verifyUpdates(driver);
		YnetUpdates.updatesMovingMouse(driver);
		Thread.sleep(15000);
		assert isUpdatesDisplayed == true;
		driver.close();
		System.out.println("Done..\n\n");
	}
	
	@Test
	public void test6_testArticle() throws InterruptedException {
		
		System.out.println("Starting Test testArticle");
		driver = YnetArticle.findArticleNewCommentBtn(driver);
		driver.close();
		System.out.println("Done!");
	}
}
