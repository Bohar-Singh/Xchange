package boca.flight;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BaseLib;

public class SpiceTest extends BaseLib {
	
	
	
	@BeforeTest
	public void visit()
	{
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\browsers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
	}
	
	@Test
	public void execute()
	{
		WebElement elementFromDate = driver.findElement(By.id("onward_cal"));
		  ((JavascriptExecutor)driver).executeScript ("document.getElementById('onward_cal').removeAttribute('readonly',0);"); // Enables the from date box
		  elementFromDate.clear();
		  elementFromDate.sendKeys("18-Aug-2020"); //Enter date in required format
	}

}
