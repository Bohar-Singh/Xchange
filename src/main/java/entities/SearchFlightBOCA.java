package entities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Utilities.BaseLib;
import Utilities.HandleJQueryCalender;

public class SearchFlightBOCA extends BaseLib {
	
	
	
	public static void searchFlight(String SalesChannel, String EmpID, String TravellerName, String TravelType, String PolicyType,String DestCity) throws Exception
	{
		
		getWebElement("SelectProduct").click();
		BaseLib.selectSalesChannel(SalesChannel);
		
		listofautosuggestion(By.xpath("(//div[@id='divCorporateUser']//td)[7]"), EmpID, TravellerName, By.xpath("//input[@id='txtCorporateUser']"));
		
		
		BaseLib.selectTravellerType(TravelType, PolicyType);
		
		getWebElement("SearchDestiField").clear();
		
		getWebElement("SearchDestiField").sendKeys(DestCity);
		Thread.sleep(2000);
		
		getWebElement("SearchDestiField").sendKeys(Keys.ENTER);
		
		String chkIn="17-Aug-2020";
		String chkOut="20-Aug-2020";
		
		BaseLib.setDateUsingJavaScriptInCalendar(driver, chkIn, getWebElement("CheckinDateField"), p.getProperty("checkIn"));
		
		BaseLib.setDateUsingJavaScriptInCalendar(driver, chkOut, getWebElement("CheckOutDateField"), p.getProperty("CheckOut"));
		
		//BaseLib.setDateUsingJavaScriptInCalendar(driver, chkOut, checinDate);
		
		
		
		
		
		

		//BaseLib.listofautosuggestion(By.xpath("//div[@id='divHTCity']/p"), DestCity, AutoListDest, By.xpath("//input[@id='txtHotelSearch']"));
		
		
		/*WebElement hotelpath = getWebElement("HotlName");
		
		WebDriverWait waitForHotel = new WebDriverWait(driver,50);
		
		WebElement elementHotel = waitForHotel.until(ExpectedConditions.visibilityOf(hotelpath));
		
		elementHotel.sendKeys(hotelName);*/
		
		
	}

}
