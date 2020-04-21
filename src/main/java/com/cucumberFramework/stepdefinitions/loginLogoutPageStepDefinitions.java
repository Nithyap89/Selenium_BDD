package com.cucumberFramework.stepdefinitions;





import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import com.cucumberFramework.helper.ExcelReader;
import com.cucumberFramework.helper.ExtentReportListener;
import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.pageObjects.LoginLogoutPage;
import com.cucumberFramework.testBase.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class loginLogoutPageStepDefinitions extends TestBase {
	
	LoginLogoutPage loginPage = new LoginLogoutPage(driver);
    ExtentReportListener list=new ExtentReportListener();
	ExcelReader excelReader=new ExcelReader("Login.xlsx");
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	
	
	@Given("^I am on the Login page URL \"([^\"]*)\"$")
	public void i_am_on_the_Login_page_URL(String arg1) throws Throwable {
	   driver.get(excelReader.readExcelSheet("Login",1).get("Url").toString());
	   log.info("Navigated application url");
	}
	@When("^Enter username \"([^\"]*)\"$")
	public void enter_username(String arg1) throws Throwable {
		loginPage.enterUserName(excelReader.readExcelSheet("Login",1).get("email").toString());
		  log.info("Entered email");
	}

	@When("^Enter Password \"([^\"]*)\"$")
	public void enter_Password(String arg1) throws Throwable {
		 loginPage.enterPasswordName(excelReader.readExcelSheet("Login",1).get("passWord").toString());
	      log.info("Entered password");
	}

	@Then("^click on login button$")
	public void click_on_login_button() throws Throwable {
		loginPage.clickLogin();
	    log.info("Clicked on Login");
	}

	@Given("^Verify the page header \"([^\"]*)\"$")
	public void verify_the_page_header(String arg1) throws Throwable {
		 Assert.assertEquals(loginPage.verifyPageTitle(), excelReader.readExcelSheet("Login",1).get("pageHeader").toString());
		 log.info("Verified page header");
	}

	@When("^Verify the shipment link$")
	public void verify_the_shipment_link() throws Throwable {
		loginPage.clickShipmentLink();
		 log.info("Verified shipment link");
	}

	@Then("^User should be in homescreen$")
	public void user_should_be_in_homescreen() throws Throwable {
		loginPage.verifyPrepareAndprint();
		log.info("Verified prepare and print tab");
		 driver.switchTo().defaultContent();
	}


	
}