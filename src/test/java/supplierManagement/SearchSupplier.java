package supplierManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.BaseLib;

public class SearchSupplier extends BaseLib {
	
	@BeforeTest
	public void launchBrowser() throws Exception
	{
		loadFiles();
		userEnvironment(p.getProperty("Env"));
	}
	
	@Test
	public void searchCorporateByName() throws Exception
	{
		 
		 WebDriverWait backBtnWait= new WebDriverWait(driver, 10);
		 backBtnWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnback']")));
	     WebElement backButton = getWebElement("BackButton");
	     backButton.click();
		 WebDriverWait SearchCorpWait= new WebDriverWait(driver, 10);
		 SearchCorpWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtCompanyId']")));
	     WebElement SearchCorpField = getWebElement("CorporateName");
		getWebElement("CorporateName").sendKeys(p.getProperty("SearchByName"));
		getWebElement("SearchButton").click();
	}
	
	@Test()
	public void searchCorporateByAccount() throws Exception
	{
		
		
		backToLeftFrame();
		Thread.sleep(2000);
		getWebElement("CorporateManagement").click();
		getWebElement("SearchCorporate").click();
		Thread.sleep(2000);
		backToFrames();
		WebDriverWait SearchbuttonWait= new WebDriverWait(driver, 10);
		 SearchbuttonWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnSearch']")));
	     WebElement SearchButtonClick = getWebElement("SearchButton");
		getWebElement("AccountNo").sendKeys(p.getProperty("SearchByAccount"));
		getWebElement("SearchButton").click();
	}


}
