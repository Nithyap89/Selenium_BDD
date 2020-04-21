package com.cucumberFramework.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumberFramework.testBase.TestBase;
import com.google.common.base.Predicate;

public class Controls extends TestBase {
public WebDriver driver;
public WebDriverWait wait;
Actions builder;
int databaseValues;

Logger log = LoggerHelper.getLogger(LoggerHelper.class);

public Controls (WebDriver driver ) //create class with parameter
{
this.driver = driver;
wait= new WebDriverWait(driver,300);
}
    //result of database query
public String[] results(String query)
    {   
String[] data = new String[10];
try {
 
    resultSet = stmt.executeQuery(query);
    ResultSetMetaData rsmd =  resultSet.getMetaData();
    int columns = rsmd.getColumnCount();
    resultSet.last();
    int count = resultSet.getRow();
    resultSet.beforeFirst();
    databaseValues = count * columns;
    data[0] = String.valueOf(count);
    int rowUpto =0;
           while(resultSet.next()){
         
     for(int i=1;i<= columns; i++)
     {   
     rowUpto++;
     data[rowUpto]=  resultSet.getString(i);
     
      
     }
     
   
        }
      return data;
} catch (SQLException e) {
log.error("Exception comes" );
return null;
}
    }

public String[] splitaArrayByComma(String value)
{
return value.split(",");
}

//click on element 
public void click(By identifier)
{
	wait.until(ExpectedConditions.elementToBeClickable(getElement(identifier))).click();
}

public void actionsClick(By identifier)
{
	// Configure the Action
	Actions action = new Actions(driver);

	// To click on the element
	action.moveToElement(getElement(identifier)).click().build().perform();
}

public void actionSendKeys(By identifier,String data){
	Actions action = new Actions(driver);
	
	action.moveToElement(getElement(identifier)).sendKeys(data).build().perform();
	
	
}



public void mousehoverElement(WebElement element1,WebElement element2){
	Actions actions =new Actions(driver);
	actions.moveToElement(element1).perform();
	actions.click(element2).perform();
	
}

public void javaScriptClick(By identifier)
{
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(identifier));
}


public void javascriptGetText(By identifier){
	
}

//clear an element
public void clear(By identifier)
{
	getElement(identifier).clear();
}

public void javaScriptClear(By identifier)
{
	((JavascriptExecutor) driver).executeScript("arguments[0].clear();", driver.findElement(identifier));
}



//wait until element is visible
public void waitUntilVisibilty(WebElement element)
{
wait.until(ExpectedConditions.visibilityOf(element));
}

//wait until element is visible
public void waitUntilVisibilty(By identifier)
{
wait.until(ExpectedConditions.visibilityOf(driver.findElement(identifier)));
}


//wait until element is to be clickable
public void waitUntilClickable(WebElement element)
{
wait.until(ExpectedConditions.elementToBeClickable(element));
}


//wait until element is to be clickable
public void waitUntilClickable(By identifier)
{
wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(identifier)));
}

//wait until staleness of element 
public void waitUntilStaleness(WebElement element)
{
wait.until(ExpectedConditions.stalenessOf(element));
}


public void waitUntilInvisibility(By by)
{
wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
}




//when element is displayed as expected true 
public boolean isDisplayedExceptedTrue(By identifier)
{
try{
return getElement(identifier).isDisplayed();
}
catch(Exception e)
{
return false;
}
}

public boolean isDisplayedExceptedTrue(WebElement element)
{
try{
return element.isDisplayed();
}
catch(Exception e)
{
return false;
}
}

public boolean isDisplayed(By identifier)
{
try{
return driver.findElement(identifier).isDisplayed();
}
catch(Exception e)
{
return false;
}
}

public boolean isSelected(By identifier){
	try{
		return driver.findElement(identifier).isSelected();
	}
	catch(Exception e){
		
		return false;
	}
	
}

//when element is displayed as expected false
public boolean isDisplayedExceptedFalse(By identifier)
{
try{
return getElementUnvisible(identifier).isDisplayed();
}
catch(Exception e)
{
return true;
}
}


//when element is enabled as expected true 
public boolean isEnabledExceptedFalse(By identifier)
{
try{
return getElement(identifier).isEnabled();
}
catch(Exception e)
{
return true;
}
}



//return element is enabled or not as expected false
public boolean isEnabledExceptedTrue(By identifier)
{
try{
return getElement(identifier).isEnabled();
}
catch(Exception e)
{
return false;
}
}


//verify value is selected
public boolean isSelectedExceptedTrue(By identifier)
{
try{
return getElement(identifier).isSelected();
}
catch(Exception e)
{
return false;
}
}


//verify value is not selected
public boolean isSelectedExceptedFalse(By identifier)
{
try{
return getElement(identifier).isSelected();
}
catch(Exception e)
{
return true;
}
}

//get by drop down value
public void selectDropDownByValue(By identifier,String value)
{
Select dropdown = new Select(getElement(identifier));
dropdown.selectByValue(value);
}


//get drop down by text
public void selectDropDownByText(By identifier,String text)
{
Select dropdown = new Select(driver.findElement(identifier));
dropdown.selectByVisibleText(text);
}

//get drop down by text
public void selectDropDownByWebElement(WebElement element,String text)
{
Select dropdown = new Select(element);
dropdown.selectByVisibleText(text);
}

//get drop down by text
public String selectedDropDownText(By identifier)
{
Select dropdown = new Select(driver.findElement(identifier));
return dropdown.getFirstSelectedOption().getText();
}

////get drop down by text on wait
//public void selectDropDownWaitByText(By identifier,String text)
//{
//Select dropdown = new Select(getElement(identifier));
//
//waitUntilSelectOptionsPopulated(dropdown);
//dropdown.selectByVisibleText(text);
//}
//
//private void waitUntilSelectOptionsPopulated(final Select select) {
//    new FluentWait<WebDriver>(driver)
//            .withTimeout(60, TimeUnit.SECONDS)
//            .pollingEvery(10, TimeUnit.MILLISECONDS)
//            .until(new Predicate<WebDriver>() {
//                public boolean apply(WebDriver d) {
//                    return (select.getOptions().size() > 1);
//                }
//            });
//}

//get dropdown by index
public void selectDropDownByIndex(By identifier,int value)
{
Select dropdown = new Select(getElement(identifier));
dropdown.selectByIndex(value);
}

//get count of drop down
public int getDropDownCount(By identifier)
{
Select dropdown = new Select(getElement(identifier));
List<WebElement> list = dropdown.getOptions();
return list.size()-1;
}


//type value in textbox
public void type(By identifier,String value)
{
getElement(identifier).clear();
getElement(identifier).sendKeys(value);
}

public void typeWithoutVisibility(By identifier)
{
//{
WebElement textBox = driver.findElement(identifier);
textBox.sendKeys("value");
}

//return text
public String getText(By identifier)
{
return getElement(identifier).getText();
}




public String getTextWithoutVisibility(By identifier)
{   
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return driver.findElement(identifier).getText();
}


//return attribute value
public String getAttribute(By identifier,String attribute)
{
return getElement(identifier).getAttribute(attribute);
}




//return css value
public String getCssValue(By identifier,String cssValue)
{
return getElement(identifier).getCssValue(cssValue);
}

//return web element
public WebElement getElement(By identifier)
{  
	
wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
return driver.findElement(identifier);
	
}



public WebElement getElementUnvisible(By identifier)
{  
return driver.findElement(identifier);
}

//get pageTitle
public String getPageTitle()
{
return driver.getTitle();
}


public String readProperty(String propertyFileName, String value){
	try{
	Properties obj = new Properties(); 
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\objectRepositry\\"+ propertyFileName + ".properties");

		obj.load(objfile);
		return obj.getProperty(value);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
		
	}
}

public void connectToFrame(String frameName){
	driver.switchTo().frame(frameName);
}
public void disconnectFrame() {
	driver.switchTo().defaultContent();
}
public void connectToIframe(WebElement element1,WebElement element2,String value) throws InterruptedException{
	
	driver.switchTo().frame(element1);
	
	//element2 =driver.findElement(identifier);
	
	element2.sendKeys(value);
	
	
}

public void connectToLatestWindow(){
	
	// Switch to new window opened
	for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
	}
	

}


public void connectToPreviousWindow(){
	
	
	String winHandleBefore = driver.getWindowHandle();

	// Perform the click operation that opens new window

	// Switch to new window opened
	for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
	}

	// Perform the actions on new window

	// Close the new window, if that window no more required
	driver.close();

	// Switch back to original browser (first window)
	driver.switchTo().window(winHandleBefore);

	// Continue with original browser (first window)

}
	


public void navigateToUrl(String url)
{
	driver.get(url);
}

public void clickByJavaScript(WebElement element)
{
JavascriptExecutor executor = (JavascriptExecutor)driver;

executor.executeScript("arguments[0].click();", element);
}

public void valueByJavaScript(WebElement element)
{
JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("arguments[0].value();", element);
}

public void clearcalender(By loctorClear){
	
	getElement(loctorClear).click();
	
}


public void handleCalender(By loctorClear,By locatorMainButton,By previous,By next,By mid,By months,By days,String dateTime) throws InterruptedException
{
	WebElement calender = getElement(locatorMainButton);
	calender.click();

	getElement(loctorClear).click();
	
	getElement(locatorMainButton).click();
	
    WebElement midLink = getElement(mid);
    //button to move previous month in calendar
 
   
  //Split the date time to get only the date part
    
    String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

    //get the year difference between current year and year to set in calander

    int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);

   midLink.click();

    if(yearDiff!=0){
    	
    	Thread.sleep(1000);

        //if you have to move next year

        if(yearDiff>0){

            for(int i=0;i< yearDiff;i++){

               
            	 getElement(next).click();

            }

        }

        //if you have to move previous year

        else if(yearDiff<0){

            for(int i=0;i< (yearDiff*(-1));i++){
                
                getElement(previous).click();
                

            }

        }

    }

     
    //midLink.click();
    
    
    Thread.sleep(1000);
    
  //Get all months from calendar to select correct one
    
    List<WebElement> list_AllMonthToBook = driver.findElements(months);
     

    list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();

     

    Thread.sleep(1000);

    //get all dates from calendar to select correct one

    List<WebElement> list_AllDateToBook = driver.findElements(days);
     
for (int date = Integer.parseInt(date_dd_MM_yyyy[0])-1;date< 42; date++ )
{
    
   if((((list_AllDateToBook.get(date)).getText()).equals(date_dd_MM_yyyy[0])))
   {   
	   
	   list_AllDateToBook.get(date).click();
	   break;
	   
   }
}

       
}
     

public void uploadFile(String imageName,By identifier)
{   
	
	String filePath = System.getProperty("user.dir") + "\\testData\\uploadFiles\\" + imageName;
    
	driver.findElement(identifier).sendKeys(filePath);
}

public void uploadFile1(String imageName,WebElement element)
{   
	/*String workingDir = System.getProperty("user.dir");
	String filepath = workingDir + "\\testData\\uploadFiles\\"+imageName;
	driver.get(filepath);
	
	WebElement fileInput = element;
	
	fileInput.sendKeys(filepath);*/


	String filePath = System.getProperty("user.dir") + "\\testData\\uploadFiles\\" + imageName;
    
	element.sendKeys(filePath);
	
	
}

public List<WebElement> displayedElements(By identifier)
{
	return driver.findElements(identifier);
	
}
public void scrolldown() {
	JavascriptExecutor js= (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,1000)");
}
public void drop(){
	driver.quit();
}
public void selectDropDownByText1(WebElement element, By identifier,String text) {
	// TODO Auto-generated method stub
	Select dropdown = new Select(driver.findElement(identifier));
	dropdown.selectByVisibleText(text);
}



//    ///FOR TIME
//
//    WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));
//
//    //click time picker button
//
//    selectTime.click();
//
//    //get list of times
//
//    List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));
//
//   
//
//    dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];
//
// //select correct time
//
//    for (WebElement webElement : allTime) {
//
//        if(webElement.getText().equalsIgnoreCase(dateTime))
//
//        {
//
//            webElement.click();
//
//        }
//
//    }
	



// public static List<WebElement> findDisplayedElements(WebDriver driver, By locator) {
//    List <WebElement> elementOptions = driver.findElements(locator);
// //    List <WebElement> displayedOptions = new List<WebElement>();
//    for (WebElement option : elementOptions) {
//        if (option.isDisplayed()) {
//            displayedOptions.add(option);
//        }
//    }
//    return displayedOptions;
// }
// public void testTable() {
//   WebElement simpleTable = driver.findElement(By.id("items"));
//  
//   //Get all rows
//   List rows = simpleTable.findElements(By.tagName("tr"));
//   assertEquals(3, rows.size());
//  
//   //Print data from each row
//   for (WebElement row : rows) {
//      List cols = row.findElements(By.tagName("td"));
//      for (WebElement col : cols) {
//         System.out.print(col.getText() + "\t");
//      }
//   System.out.print();
//   }
// }
//
// Using jQuery selectors
//
// Locate all the Checkbox which are checked by calling jQuery find() method.
// find() method returns elements in array
// 1
// 2
//
// List elements =
//        (List) js.executeScript("return jQuery.find(':checked')");
}

