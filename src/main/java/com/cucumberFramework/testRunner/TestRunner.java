package com.cucumberFramework.testRunner;





import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;
import com.cucumberFramework.helper.ExtentReportListener;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:/Users/pc/Documents/Selenium Framwork/Cucumber-BDD-Automation-Framework-master/src/test/resources/features/login",
glue =  {"classpath:com.cucumberFramework.stepdefinitions"}, 
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
		)
public class TestRunner {
	
	private TestNGCucumberRunner testNGCucumberRunner;
	   
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}
	

	@Test(groups = "cucumber", description = "Runs cucmber Features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

     @AfterClass
     public static void setup() throws IOException
     {
     Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\Config\\Report.xml"));
     }

	@AfterClass(alwaysRun = true)
	public void testDownClass() {
		testNGCucumberRunner.finish();
	}

}
