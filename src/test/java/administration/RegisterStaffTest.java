package administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BaseLib;

public class RegisterStaffTest extends BaseLib {
	
	@BeforeTest
	public void launchBrowser() throws Exception
	{
		loadFiles();
		userEnvironment(p.getProperty("Env"));
	}
	
	
	
	@Test()
	public void searchCorporateByAccount() throws Exception
	{
	
		backToLeftFrame();
		getWebElement("Administration").click();
		getWebElement("StaffManagement").click();
		Thread.sleep(2000);
		switchToToolHeaderFreame();
		getWebElement("RegisterStaffLink").click();
		Thread.sleep(2000);
		backToFrames();
		Thread.sleep(2000);
		
		Select s= new Select(driver.findElement(By.xpath("//select[@id='ddlbranch']")));
		s.selectByVisibleText("DEL");
	}


}
