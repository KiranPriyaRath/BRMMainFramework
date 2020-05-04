package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.common;
import utilities.ReportMessege;
import utilities.SikuliUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class OSMHomePage extends PageObject {
	
	private static final TimeUnit SECONDS = null;
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private common common1;

	@Steps
	private SikuliUtility SikuliUtility;
	@Steps
	private DatabasePage Database;
	
	@Steps
	ReportMessege report;
	
	
	String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
	String SikulifilePathForSavingFile = "";
	Screen  s=new Screen();
	String currentDir = System.getProperty("user.dir");
	 public static boolean isAlertPresent(WebDriver driver) {
	      try{
	            driver.switchTo().alert();
	            return true;
	      }catch(NoAlertPresentException ex){
	            return false;
	      }
	}
	 
	 @FindBy(xpath=".//*[@name='/CustomerHeaders/Identification/ID_665']")
	 WebElementFacade OrderNumberTextBox;
	 
	 @FindBy(xpath=".//*[@name='/CustomerHeaders/Identification/ID_665']//preceding::*[@title='Search']")
	    WebElementFacade SearchButton;
	 
	 
	 
	
	@Step
	public void SearchOrderNumberOSM(String Order) throws InterruptedException, IOException, FindFailed, AWTException{
		String sOrderNum = "";
		if (Order.equals("")){
			sOrderNum = Serenity.sessionVariableCalled("OrderNo").toString();
			OrderNumberTextBox.type(sOrderNum);
		}
		else{
			sOrderNum = Order;
			OrderNumberTextBox.type(sOrderNum);
		}
		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		//OrderNumberTextBox.type(Order);
		SearchButton.click();
		
		//List<WebElement> rows = getDriver().findElement(By.xpath("//table[@class='table table-condensed table-hover event-list']/tbody/tr"));
		int rows =(findAll(By.xpath("//table[@class='templateContainer']/tbody/tr"))).size();
        	
		if(rows>1){
            report.Info("Order Number Query Result are displayed successfully.");
            SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
      }
      else{
            Assert.assertTrue("Order Number Query Result are not displayed successfully.", rows>1);
            SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
      }
	}
	
	
	//(.//*[@id='completed'])[1]
		//	(.//*[@id='completed'])[9]

	@FindBy(xpath="(.//*[@id='completed'])[9]")
	 WebElementFacade OrderState;
	 
	//@FindBy(xpath="(.//*[@id='completed'])[1]")
	//    WebElementFacade TableActionDrillDown1stButton;
	
	
			@FindBy(xpath="((.//*[@id='completed'])//following::*[@value='...'])[1]")
    WebElementFacade TableActionDrillDown1stButton;
	
	@FindBy(xpath="(.//*[@value='processHistory']")
    WebElementFacade ProcessHistoryRadioButton;
	
	 
	 
	
	@Step
	public void ProcessHistory(String ScriptNum) throws InterruptedException, IOException, FindFailed, AWTException{
		String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
		String SikulifilePathForSavingFile = "\\target\\site\\serenity";
		
		
		OrderState.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
		
		String Status = OrderState.getTextValue();
		Assert.assertTrue("Order has not Completed successfully.", Status.contains("Completed"));
		report.Info("Order has Completed successfully.");
		
		ProcessHistoryRadioButton.isCurrentlyVisible();
		
		//ProcessHistoryRadioButton.is
		getDriver().findElement(By.xpath("//input[@value='processHistory']")).click();
		TableActionDrillDown1stButton.click();
		
		Robot robot = new Robot();
		// press Ctrl+S the Robot's way
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		SikuliUtility.SaveProcessHistory(SikulifilePath,SikulifilePathForSavingFile,ScriptNum);
		report.Info("File saved successfully in the following path "+SikulifilePathForSavingFile);
        Thread.sleep(5000);
		
		
	}
	
	
}
