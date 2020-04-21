package com.cucumberFramework.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener {
	public static ExtentHtmlReporter report=null;
	public static ExtentReports extent=null;
	public static ExtentTest test=null;
	
	
	public static ExtentReports setup() {
		String reportLocation="";
		report =new ExtentHtmlReporter(reportLocation);
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.STANDARD);
		System.out.println("Extent Report Location Initialized..");
		report.start();
		extent =new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("TC Global", "Login");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");
		return extent;
		
}
	
	public static void testStepHandel(String teststatus,WebDriver driver,ExtentTest extenttest,Throwable throwable) {
	switch (teststatus){
	case "FAIL":
	extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
	extenttest.error(throwable.fillInStackTrace());
	try {
		extenttest.addScreenCaptureFromPath(capturescreenshot(driver));
		
	}catch (Exception e) {
		
	}
	if(driver !=null) {
		driver.quit();
	}
	break;
	case "PASS":
	
	extenttest.pass(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.GREEN));
	break;
	default:
	break;
		
	}
}
	public static String capturescreenshot(WebDriver driver)throws IOException{
		TakesScreenshot screen=(TakesScreenshot) driver;
		File src=screen.getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"\\Screenshot\\"+ getCurrentDateAndTime() + ".png";
		File target=new File(dest);
		FileUtils.copyFile(src, target);	
		return dest;
		
	}
	
	public static String getCurrentDateAndTime() {
		String str=null;
		try {
			DateFormat dateFormate=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date=new Date();
			str=dateFormate.format(date);
			str=str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");	
		}catch(Exception e){
			
		}
		return str;
		
	}
}