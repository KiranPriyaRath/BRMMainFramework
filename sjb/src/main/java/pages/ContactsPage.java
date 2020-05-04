package pages;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.JavascriptExecutor;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;
import utilities.common;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;


public class ContactsPage extends PageObject {
	
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private ReportMessege report; 
	@Steps
	private SikuliUtility sikuliUtility;
	
	@Steps
    private common common1;	
	
	
	//@FindBy(xpath=".//*[@aria-label='Account name']/following::a[text()='Contacts']")
	@FindBy(xpath=".//*[@aria-label='Account Summary Selected']/following::a[text()='Contacts']")
    WebElementFacade ContactsTab;
	@FindBy(xpath=".//*[@id='1_s_1_l_VF_Online_Id']")
    WebElementFacade OnlineID;	
	@FindBy(xpath=".//*[@id='1_VF_Online_Id']")
    WebElementFacade OnlineIDCheckedBoxnew;	
	/*@FindBy(xpath=".//*[@id='1_s_1_l_VF_Online_Id']/span[1]")
    WebElementFacade OnlineIDCheckedBox;	*/
	 @FindBy(xpath=".//*[@aria-label='Third Level View Bar']")
     WebElementFacade AccountTabsDRopDown;
	 @FindBy(xpath=".//*[@title='Contact:New']")
     WebElementFacade ContactsNew;
	 
	 @FindBy(xpath=".//*[@title='Account Permissions:New']")
     WebElementFacade AccountPermissionNewButton;
	 @FindBy(xpath=".//*[@title='Account Permissions:Delete']")
     WebElementFacade AccountPermissionDeleteButton;
	 @FindBy(xpath="(.//*[@aria-label='Multiple Selection Field'])[4]")
     WebElementFacade AccountIcon;
	 @FindBy(xpath=".//*[@title='Accounts:New']")
     WebElementFacade AcountsNewButton;
	 @FindBy(xpath=".//*[@title='Accounts:Delete']")
     WebElementFacade AcountsDeleteButton;
	 @FindBy(xpath=".//*[@title='Accounts:OK']")
     WebElementFacade AcountsOkButton;
	 @FindBy(xpath=".//*[@id='s_sctrl_tabScreen']/ul/li[3]/a")
     WebElementFacade MainContactsTab;
	 @FindBy(xpath="(.//*[@aria-label='Multiple Selection Field'])[2]")
     WebElementFacade AccountTabIcon;
	 @FindBy(xpath=".//*[@class='siebui-icon-dropdown']")
     WebElementFacade Dropdown;
     @FindBy(xpath="(.//*[@data-lovtype='VF Account Contact Affiliation:Account Permissions'])[1]")
     WebElementFacade SelectLevel;
	 
	 @Screenshots(forEachAction=true)
@Step
public void Contacts_LastName(String Validation) throws InterruptedException, IOException, AWTException{
	//Homepage.waitUntilPresent();
	
		  String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
		   String filePath = "\\src\\test\\resources\\data\\Account.xls";
	      String dataSheet = "ContactsLastName";
	      String applet = "//*[@title='Contacts List Applet']";
	      String table = "//table[@summary='Admin Account Automatic Debit Details']";  
    
	      ContactsTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	
			if (ContactsTab.isDisplayed()){
				ContactsTab.click();
			}
			else {
				AccountTabsDRopDown.selectByVisibleText("Contacts");
			}
			
	      Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Validation, filePath, dataSheet);
	      readWorkbook.testData(tableMap);
	      
	      for (int i = 0;i < tableMap.get("LocateCol").size();i++) {	    	   
	   	   String sUIName = tableMap.get("UIName").get(i);
	   	   String sValue = tableMap.get("Value").get(i);
	   	String sNewButton = tableMap.get("NewButtoncheck").get(i);
	   	   String Online_Account_Validation = tableMap.get("OnlineAccountValidation").get(i);
	   	String sValidation = tableMap.get("Validation").get(i);
	   	   
	   	
	
	if (Online_Account_Validation.equals("Y")){
		OnlineID.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				
				OnlineID.click();
				Thread.sleep(3000);
	        	String OF = OnlineIDCheckedBoxnew.getAttribute("readonly");
	        	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				if (OF.equals("readonly")){
					report.Info("Pass : Online Flag DISABLED as expected");	       			
					}
				else {report.Info("Fail : Online Flag Enabled");}	        	
    			Thread.sleep(5000);
	
	       }
            common1.selectedRow = 1;
	   	   if (!sUIName.equals("")){
	   		common1.updateSiebList(applet,table, sUIName, sValue);   
	   	   }
	   	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	   
	   	if (sNewButton.equals("Y")){
	    	 String ButtonCheck = ContactsNew.getAttribute("disabled");//disabled
	    	 if(ButtonCheck.equalsIgnoreCase("disabled")){
	    		 report.Info("Pass : New Button is Disable as expected"); 
	    	 }
	    	 
	    	 else {report.Info("Fail : New Button is Enabled");}  
	    	 
	    	 if(sValue.equalsIgnoreCase("Account")){
	 	   		AccountIcon.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 	   		AccountIcon.click();
	 	   		if(sValidation.equalsIgnoreCase("LastNameValidation")){
	 		   		Thread.sleep(2000);
	 		   		Assert.assertFalse("Fail: Account permission New Button is enabled",AcountsNewButton.isEnabled());
	 		   		report.Info("Pass : Account permission New Button is GreyedOut");
	 		   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 		   		Assert.assertFalse("Fail: Account permission New Button is enabled",AcountsDeleteButton.isEnabled());
	 		   		report.Info("Pass : Account permission Delete Button is GreyedOut");
	 		   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 		   	} 
	 	   		AcountsOkButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 	   		AcountsOkButton.click();
	 	   	   }
	 	   	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 	   	if(sValidation.equalsIgnoreCase("AccountValidation")){
	 	   		Thread.sleep(2000);
	 	   		Assert.assertFalse("Fail: Account permission New Button is enabled",AccountPermissionNewButton.isEnabled());
	 	   		report.Info("Pass : Account permission New Button is GreyedOut");
	 	   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 	   		Assert.assertFalse("Fail: Account permission New Button is enabled",AccountPermissionDeleteButton.isEnabled());
	 	   		report.Info("Pass : Account permission Delete Button is GreyedOut");
	 	   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 	   	} 
	      }
	if(sValidation.equalsIgnoreCase("AccountPermission")){
	   		
	   		Thread.sleep(2000);
	   		Assert.assertFalse("Fail: Account permission New Button is enabled",AccountPermissionNewButton.isEnabled());
	   		report.Info("Pass : Account permission New Button is GreyedOut");
	   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   		
	   	}
	 	if(sValidation.equalsIgnoreCase("SelectLevel")){
	 		
	 		AccountPermissionNewButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 		AccountPermissionNewButton.click();
	 		//Dropdown.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 		Thread.sleep(5000);
	 		Dropdown.click();
	 		Thread.sleep(3000);
	 		SelectLevel.click();
	 		Thread.sleep(2000);
	 		Assert.assertFalse("Fail: Account permission New Button is enabled",AccountPermissionNewButton.isEnabled());
	   		report.Info("Pass : Account permission New Button is GreyedOut");
	   		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 		
	 		}
	   	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		}
	      
	}
		     
}
