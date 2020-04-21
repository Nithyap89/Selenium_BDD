package com.cucumberFramework.testBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CapabilityType.ForSeleniumServer;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import com.cucumberFramework.enums.Browsers;
//import com.cucumberFramework.enums.OS;
import com.cucumberFramework.helper.LoggerHelper;


public class TestBase {
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	public  static WebDriver driver;
	public static ResultSet resultSet;
	protected String mainWindowHandle;
	public static Statement stmt;
	public static int time = 100;
	String projectPath = System.getProperty("user.dir");
    String IEfilepath    =projectPath +"/src/test/resources/drivers/geckodriver.exe";        
    String chromeFilePath  = projectPath +"/src/test/resources/drivers/chromedriver.exe";
    String operaFilePath  = projectPath +"/src/test/resources/drivers/geckodriver.exe";
	DesiredCapabilities capabilities=new DesiredCapabilities();
	public WebDriver selectBrowser(String browser) {
		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
		if (browser.equalsIgnoreCase("firefox")) {
			capabilities.setBrowserName("firefox");
			driver = new FirefoxDriver();
			log.info("Firefox driver instantiated");
			}
		else if (browser.equalsIgnoreCase("GoogleChrome")) {
			System.setProperty("webdriver.chrome.driver", chromeFilePath);
			capabilities = DesiredCapabilities.chrome();
			driver = new ChromeDriver(capabilities);
			log.info("Google chrome driver instantiated");
		}
		else if(browser.equalsIgnoreCase("internetExplorer")) {
			System.setProperty("webdriver.ie.driver",IEfilepath);
			capabilities = DesiredCapabilities.internetExplorer();
			driver = new InternetExplorerDriver();
			log.info("Internet Explorer  driver instantiated");
		}
		
		capabilities.setCapability(ForSeleniumServer.PROXYING_EVERYTHING, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		
		@SuppressWarnings("unused")
		org.openqa.selenium.Dimension defaultDim;  
		@SuppressWarnings("unused")
		org.openqa.selenium.Dimension maximizeDim;  
		//Display the current screen dimensions  
		defaultDim=driver.manage().window().getSize();
		//maximize the window using web driver method  
	//	
		//Display the maximized window dimensions  
		maximizeDim=driver.manage().window().getSize();
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		mainWindowHandle = driver.getWindowHandle();
		System.out.println("Launched new Window with handle:  "+ mainWindowHandle);
		
		PageSetup();
		
		driver.manage().window().maximize();  
		return driver;
	}
	
	@BeforeSuite
	public void Database() throws Exception
	 { 
	try{
	DOMConfigurator.configure("log4j.xml");
//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	        connection = DriverManager.getConnection("jdbc:sqlserver://ARLMSAGQA02:10433;user=webusercima;password=webusercima;database=DBS4PORTAL");
//	        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
//	          ResultSet.CONCUR_READ_ONLY);
	        log.info("Environment Configuration Ready");
	}
	catch (Exception e)
	 {
	  e.printStackTrace();
	  log.info("Environment Configuration error");
	  }
	 }
	
	@AfterSuite
	public void tearDown() throws Exception
	{  
//	if (connection != null) 
//	{
//	connection.close();
//	Log.info("Database connection closed");
//	}
	}
	
	@AfterClass
	public void drop()
	{
	driver.quit();
	}
	
	public void PageSetup() 
	{ 
	}
}

	
	