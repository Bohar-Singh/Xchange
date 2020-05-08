package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class BaseLib {

	public static WebDriver driver;
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
        	sheet.getRow(1);
        	
        }
        
       /* public static void getDBConnection(String DatabaseName)
        {
        	DriverManager.getConnection(url, user, password);
        }*/
	
	
}
