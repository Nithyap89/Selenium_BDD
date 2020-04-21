package com.cucumberFramework.stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.reporters.Files;

import com.cucumber.listener.Reporter;
//import com.cucumberFramework.enums.Browsers;
import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class ServiceHooks {

	TestBase testBase;

	Logger log = LoggerHelper.getLogger(ServiceHooks.class);

	@Before
	public void initializeTest() {
		testBase = new TestBase();
		testBase.selectBrowser("GoogleChrome");
	}
    @Before
    public void beforeScenario(Scenario scenario){
    	System.out.println("Started execution for the scenario :"+scenario.getName());
    }
	@After
	public void endTest(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				TakesScreenshot ts = (TakesScreenshot) testBase.driver;
				File sourcePath = ts.getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
				FileUtils.copyFile(sourcePath,destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (WebDriverException e) {
				e.printStackTrace();
			}

		}else {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			TakesScreenshot ts = (TakesScreenshot) testBase.driver;
			File sourcePath = ts.getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
			FileUtils.copyFile(sourcePath,destinationPath);
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
		}

		TestBase.driver.quit();
	}
	
	@After
	 public void afterScenario(Scenario scenario){
    	System.out.println("Completed execution for the scenario:"+scenario.getName());
    }
	
}
