package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class YnetWeather {
	
	private static WebElement element = null;
	private static String Weather_xpath = "//span[@class='weathertemps']";
	
	public static WebElement getWeather(WebDriver driver) {
		WebElement weather = driver.findElement(By.xpath(Weather_xpath));
		System.out.println("Current weather in TelAviv : "+ weather.getText());
		return weather;
	}
	
	public static WebElement getWeatherByCity(WebDriver driver,String city) 
	{
		element = driver.findElement(By.id("weathercityselect"));
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(city);
		element = driver.findElement(By.xpath(Weather_xpath));
		System.out.println("Current weather in Eilat : "+ element.getText());
		return element;
	}

}
