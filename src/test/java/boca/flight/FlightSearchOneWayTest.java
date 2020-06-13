package boca.flight;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.BaseLib;

import Utilities.ExcelReader;
import entities.SearchFlightBOCA;

public class FlightSearchOneWayTest extends CheckSearchFlightBOCA {
	
	ExcelReader read;
	
	@BeforeTest
	public void setup() throws Exception
	{
		
		userEnvironment(p.getProperty("Env"));
	}
	
	@DataProvider
	public Object[][] readExcelData() throws Exception
	{
		read= new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\java\\excelfiles\\Hotel.xlsx");
		
		int row = read.getrowCount("SearchHotel");
		
		int column = read.getColumnCount("SearchHotel");
		
		Object[][] data = new Object[row][column];
	
		for(int i = 0; i < row; i++){
			
			for(int j = 0; j < column; j++){
				
				data[i][j]= read.getData("SearchHotel", i+1, j);
			}
		}
		return data;	
	}
	
	@Test(dataProvider="readExcelData")
	public void searchFlightOneWay(String SalesChannel, String EmpID, String TravellerName, String TravelType, String PolicyType, String DestCity) throws Exception
	{
		
		BaseLib.backToFrames();
		
		SearchFlightBOCA.searchFlight(SalesChannel, EmpID, TravellerName, TravelType, PolicyType, DestCity);
		
		

		
	}

}
