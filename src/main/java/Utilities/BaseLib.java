package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.errorprone.annotations.Var;




public class BaseLib {

	public static WebDriver driver;
	public static File file;
	public static Properties p;
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;

	public static void loadFiles() throws IOException {
		p = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\config.properties");
		p.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\loc.properties");
		p.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\SearchCorporate.properties");
		p.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\StaffManagement.properties");
		p.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\searchflight.properties");
		p.load(fis);
		
		file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\excelfiles\\Hotel.xlsx"); 
	}

	public static void userEnvironment(String env) throws Exception {
		
		driver = selectBrowser(p.getProperty("Browser"));
		
		if (env.equalsIgnoreCase("QA")) {
			
			driver.get(p.getProperty("StagingURL"));
			getWebElement("CompID").sendKeys(p.getProperty("SCompID"));
			getWebElement("Username").sendKeys(p.getProperty("SUSername"));
			getWebElement("Password").sendKeys(p.getProperty("SPassword"));
			getWebElement("LoginButton").click();
		} else {
			
			driver.get(p.getProperty("Pre-ProdURL"));
			getWebElement("CompID").sendKeys(p.getProperty("PCompID"));
			getWebElement("Username").sendKeys(p.getProperty("PUSername"));
			getWebElement("Password").sendKeys(p.getProperty("PPassword"));
			getWebElement("LoginButton").click();
		}
	}

	public static WebDriver selectBrowser(String browser) {

		if (browser.equals("Firefox") || browser.equals("FIREFOX")) {
			System.out.println("Firefox browser");
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\browsers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		}

		if (browser.equals("chrome") || browser.equals("CHROME")) {
			System.out.println("Chrome Browser");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\browsers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		}
		return null;
	}

	public static WebElement getLocator(String locator) throws Exception

	{
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("class"))
			return driver.findElement(By.className(locatorValue));
		else if (locatorType.toLowerCase().equals("tag"))
			return driver.findElement(By.tagName(locatorValue));
		else if (locatorType.toLowerCase().equals("link"))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("css"))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else
			throw new Exception("Unknown Locator Type" + locatorType);

	}

	// *** This Function returns the locators from the Web Application *****

	public static WebElement getWebElement(String locator) throws Exception {
		return getLocator(p.getProperty(locator));
	}
	
	// ***  This Method is used to get the frames *****
	
		public static void frames(String frame) {
			
			driver.switchTo().frame(frame);
		
		}
	
	// ***  This Method is used to get to the Center Panel of the Application *****
	
		public static void backToFrames() {
			
			driver.switchTo().defaultContent();
			BaseLib.frames("login");
			BaseLib.frames("main");
			BaseLib.frames("frm2");
			
		}
		
		// ***  This Method is used to get to the Left Panel of the Application *****
		
		public static void backToLeftFrame() {
			
			driver.switchTo().defaultContent();
			BaseLib.frames("login");
			BaseLib.frames("leftbar");
		}
		
        public static void switchToToolHeaderFreame() {
			
        	driver.switchTo().parentFrame();
			BaseLib.frames("main");
			BaseLib.frames("toolHeader");
		}
        
        public static void getRowData(String Filepath, String SheetName, int rowNum) throws IOException
        {
        	fis= new FileInputStream(Filepath);
        	wb= new XSSFWorkbook(fis);
        	wb.getSheet(SheetName);
			sheet.getRow(rowNum);
        	
        	
        }
        
        public static void selectSalesChannel(String Channel) throws Exception
        {
        	if(Channel.equalsIgnoreCase("Corporate"))
        	{
        		getWebElement("BOCA").click();
        	}
        	else if(Channel.equalsIgnoreCase("Reseller"))
        	{
        		getWebElement("BOSA").click();
        	}
        	
        	else
        	{
        		getWebElement("BODC").click();
        	}
        }
        
        public static void selectTravellerType(String travelType, String policyType) throws Exception
        {
        	if(travelType.equalsIgnoreCase("Business"))
        	{
        		
        		getWebElement("Business_Travel").click();
        		Thread.sleep(2000);
        		
        		if(policyType.equalsIgnoreCase("Individual"))
        		{
        			getWebElement("Individual").click();
        		}
        		
        		else if(policyType.equalsIgnoreCase("Dependent"))
        		{
        			getWebElement("Dependent").click();
        		}
        		
        		else if(policyType.equalsIgnoreCase("Multi Passengers"))
        		{
        			getWebElement("MultiPax").click();
        		}
        		else if(policyType.equalsIgnoreCase("Guest"))
        		{
        			getWebElement("Guest").click();
        		}
        		else
        		{
        			System.out.println("Please select valid policy type");
        		}
        	}
        	else if(travelType.equalsIgnoreCase("Personal"))
        	{
        		getWebElement("Personal_Travel").click();
        	}
        	
        	else
        	{
        		System.out.println("Please select valid travel type");
        	}
        	
        	
        	}
        
       /* public static void selectCorporateProfileByID(String EmpID, String TravellerName) throws Exception
        {
        	if(EmpID!=null)
        	{
        		listofautosuggestion(, EmpID, TravellerName, d)
        	}
        
        	else
        	{
        		System.out.println("Sorry !! other options are not available yet.");
        	}
        }*/
        
        public static List<WebElement> listofautosuggestion(By suggestiontxt, String enterData,
    			String inputData, By d) throws InterruptedException {

    		driver.findElement(d).sendKeys(enterData);
    		Thread.sleep(3000);
    	//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    		List<WebElement> autosuggestions = driver.findElements(suggestiontxt);		
    		for (WebElement autosuggestion : autosuggestions) {
    			System.out.println(autosuggestion.getText());
    			System.out.println(inputData);
    			if (autosuggestion.getText().equalsIgnoreCase(inputData)) {
    				System.out.println("Equal");
    				Thread.sleep(3000);
    				//WebDriverWait wait = new WebDriverWait(driver,90);
    				//WebElement element = wait.until(ExpectedConditions.visibilityOf(autosuggestion));
    				autosuggestion.click();
    				break;
    			} else {
    				System.out.println("Not Equal");
    			}
    		}
    		
    		return autosuggestions;
    	}
        
        
        public static void selectCalendarDates(WebDriver driver, WebElement ele, String DateToSet)
        {
        	JavascriptExecutor js= ((JavascriptExecutor)driver);
        	js.executeScript("arguments[0].setAttribute('value','"+DateToSet+"');", ele);
        }
        
        public static void setDateUsingJavaScriptInCalendar(WebDriver driver, String value, WebElement calLocator, String domValue)
    	{
        	((JavascriptExecutor)driver).executeScript ("document.getElementById('"+domValue+"').removeAttribute('readonly',0);"); 
        	 calLocator.clear();
        	 calLocator.sendKeys(value);
    	}
        
       /* public static void selectDates()
    	{
    		List<WebElement> selectDate = driver.findElements(By.xpath("//div[@class='datepick-month-row']//td"));
    		  List<WebElement> selectMonth = driver.findElements(By.xpath("//div[@class='datepick-month-header']"));
    		 
    		 
    		 for(int i=0; i<selectMonth.size(); i++)
    		 {
    			String reqdMonthText = selectMonth.get(i).getText();
    			System.out.println(reqdMonthText);
    			while(!reqdMonthText.contains("Jul 2020"))
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
    		 
    		 
    		
    	}*/
    	
        
        
        
     /*   public String[][] getExcelData(String fileName, String sheetName) {
    		String[][] arrayExcelData = null;
    		try {
    			fis = new FileInputStream(fileName);
    			 wb = new XSSFWorkbook(fis);
    			 sheet = wb.getSheet(sheetName);

    			
    			int totalNoOfRows = sheet.getLastRowNum();
    			  
				int totalNoOfCols;
				for(int i=0; i<totalNoOfRows; i++)
    			{
    				 totalNoOfCols= sheet.getRow(i).getLastCellNum();
    			}
    			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
    			
    			for (int i= 1 ; i < totalNoOfRows; i++) {

    				for (int j=0; j < totalNoOfCols; j++) {
    					arrayExcelData[i-1][j] = sheet.getCell(j, i).getContents();
    				}

    			}
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    			e.printStackTrace();
    		} 
    		return arrayExcelData;
    	}*/
        
       /* public static void getDBConnection(String DatabaseName)
        {
        	DriverManager.getConnection(url, user, password);
        }*/
	
	
}
