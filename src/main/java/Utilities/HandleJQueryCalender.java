package Utilities;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HandleJQueryCalender {
	
	public static  WebDriver driver;
	
	public static int currentDay=0;
	public static int currentMonth=0;
	public static int currentYear=0;
	
	public static int targetDay=0;
	public static int targetMonth=0;
	public static int targetYear=0;
	
	public boolean increment=true;
	public static Calendar cal;
	
	public static void getCurrentDate()
	{
		cal= Calendar.getInstance();
		
		currentDay= cal.get(Calendar.DAY_OF_MONTH);
		currentMonth= cal.get(Calendar.MONTH);
		currentYear= cal.get(Calendar.YEAR);
		
		System.out.println("Today Date is" +currentDay+" "+currentMonth+" "+currentYear);
		
	}
	
/*	public static void getTargetDate(String setTargetDate)
	{
		String dateToSet=setTargetDate;
		
		String firstIndex= dateToSet.indexOf("/");
	}*/
	
	public static void selectDates()
	{
		List<WebElement> selectDate = driver.findElements(By.xpath("//div[@class='datepick-month-row']//td"));
		  List<WebElement> selectMonth = driver.findElements(By.xpath("//div[@class='datepick-month-header']"));
		 
		 
		 for(int i=0; i<selectMonth.size(); i++)
		 {
			String reqdMonthText = selectMonth.get(i).getText();
			
			while(!reqdMonthText.contains("Jul"))
			{
				driver.findElement(By.xpath("//a[text()='Next>']")).click();
			}
		 }
		 
		 int dayCount = selectDate.size();
		 
		 for(int i=0; i<dayCount; i++)
		 {
			 String reqdDayText = driver.findElements(By.xpath("//div[@class='datepick-month-row']//td")).get(i).getText();
			 
			 if(reqdDayText.equalsIgnoreCase("30"))
			 {
				 driver.findElements(By.xpath("//div[@class='datepick-month-row']//td")).get(i).click();
			 }
		 }
		 
		 
		
		          

}
	
}
