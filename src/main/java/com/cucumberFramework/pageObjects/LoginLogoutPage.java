package com.cucumberFramework.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumberFramework.helper.Controls;
import com.cucumberFramework.testBase.TestBase;



public class LoginLogoutPage extends TestBase {
	
	public WebDriver driver;
	public WebDriverWait wait;
	Controls controls;
	
	
	public LoginLogoutPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, time);
		controls = new Controls(driver);

	}
	

	
	public void enterUserName(String data){
		controls.type(By.id(controls.readProperty("login", "email")),data);
	}
	public void enterPasswordName(String data) {
		controls.type(By.id(controls.readProperty("login", "passWord")), data);
	}
	
	public void clickLogin() throws InterruptedException {
		 controls.click(By.cssSelector(controls.readProperty("login", "loginButton")));
		
	}
	public String verifyPageTitle() {
	   controls.connectToFrame("contentIFrame");
	   String text= controls.getText(By.xpath(controls.readProperty("login", "pageHeader")));
	   controls.disconnectFrame();
	   return text;
	}
	
	public void clickShipmentLink() {
		 controls.click(By.partialLinkText(controls.readProperty("login", "shipmentLink")));
	}
	
    public boolean verifyPrepareAndprint() {
    	WebElement element=driver.findElement(By.id("contentIFrame"));
    	controls.connectToFrame(element.toString());
    	return controls.isDisplayed(By.cssSelector(controls.readProperty("login", "prepareAndPrint")));
    	
    }
}
