package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.reporters.TemplateableViewGenerator.Report;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.findby.By;

public class BRMODIPage extends PageObject {
	private static final TimeUnit SECONDS = null;
	@Steps
	private ReadWorkbook readWorkbook;	
	@Steps
	ReportMessege report;
	@Steps
	SikuliUtility sikuliUtility;
	
	String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
	
	@FindBy(xpath=".//*[@id='shell:browse_region:0:browsertree:0::di']")
	WebElementFacade RunTimeExpand;
	
	@FindBy(xpath=".//*[@id='shell:browse_region:0:browsertree:3::di']")
	WebElementFacade BrowseScenariosExpand;
	
	@FindBy(xpath=".//*[@id='shell:browse_region:0:browsertree:5::di']")
	WebElementFacade BrowseScenariosSelection;
	
	@FindBy(xpath=".//span[contains(text(),'CREDITALERTING - 002')]")
	WebElementFacade CREDITALERTING_002;
	
	@FindBy(xpath=".//*[@id='shell:browse_region:0:ctb8_mainTab::icon']")
	WebElementFacade ExecuteButton;
	
	@FindBy(xpath=".//span[contains(text(),'DOCSTORE - 002')]")
	WebElementFacade DOCSTORE_002;
	
	String SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
	
	@SuppressWarnings("deprecation")
	@Step
	public void  BrowseScenarios_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{
	  	 
		String filePath = "\\src\\test\\resources\\data\\ODI.xls";
  	 	String dataSheet = "BrowseScenarios";  	 
  	 	Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
	     for (int i = 0;i < tableMap.get("RowNo").size();i++) {      	    	 
	    	                 	
       	String  sScenario = tableMap.get("Scenario").get(i);              	
       	WebDriverWait wait = new WebDriverWait(getDriver(),120);
       	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='shell:browse_region:0:browsertree:0::di']")));       	
       	
       	if(getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:0::di']")).isDisplayed()){       		
       		getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:0::di']")).click();
       	}
       	else{
       		report.Info("RunTimeExpand element not visisble");
       		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       		Assert.assertTrue(false);
       	}
       	
       	Thread.sleep(3000);
       	if (getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:3::di']")).isDisplayed()){       		
       		getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:3::di']")).click();
       	}
       	else{
       		report.Info("BrowseScenariosExpand element not visisble");
       		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       		Assert.assertTrue(false);
       	}
       	Thread.sleep(2000);
       	if (getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:5::di']")).isDisplayed()){       		
       		getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:browsertree:5::di']")).click();
       		Thread.sleep(2000);
       	}    	
       	else{
       		report.Info("BrowseScenariosSelection element not visisble");
       		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       		Assert.assertTrue(false);
       	}
       	
       	if(sScenario.equals("CREDITALERTING - 002")){
    		if (getDriver().findElement(By.xpath(".//span[contains(text(),'CREDITALERTING - 002')]")).isDisplayed()){       		
    			getDriver().findElement(By.xpath(".//span[contains(text(),'CREDITALERTING - 002')]")).click();
           		Thread.sleep(2000);
           	} 
       	else{
       		report.Info("CREDITALERTING_002 element not visisble");
       		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       		Assert.assertTrue(false);
       	}
    	
    	}

       	if(sScenario.equals("VF_PAYMENT_SCEN - 001")){
       		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", PaymentScen);
       		if (getDriver().findElement(By.xpath(".//span[contains(text(),'VF_PAYMENT_SCEN - 001')]")).isEnabled()){       		
       			getDriver().findElement(By.xpath(".//span[contains(text(),'VF_PAYMENT_SCEN - 001')]")).click();
       			Thread.sleep(2000);
       		} 
       		else{
       			report.Info("VF_Payment_Scen element not visisble");
       			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       			Assert.assertTrue(false);
       		}

       	}
    	if(sScenario.equals("DOCSTORE - 002")){
    		if (getDriver().findElement(By.xpath(".//span[contains(text(),'DOCSTORE - 002')]")).isDisplayed()){       		
    			getDriver().findElement(By.xpath(".//span[contains(text(),'DOCSTORE - 002')]")).click();
           		Thread.sleep(2000);
           	} 
           	else{
           		report.Info("DOCSTORE_002 element not visisble");
           		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
           		Assert.assertTrue(false);
           	}        	
        	}
    	
    	if(sScenario.equals("ADDACS_SCEN")){
    		if (getDriver().findElement(By.xpath(".//span[contains(text(),'ADDACS_SCEN - 002')]")).isDisplayed()){       		
    			getDriver().findElement(By.xpath(".//span[contains(text(),'ADDACS_SCEN - 002')]")).click();
           		Thread.sleep(2000);
           	} 
       	else{
       		report.Info("ADDACS_SCEN element not visisble");
       		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
       		Assert.assertTrue(false);
       	}
    	
    	}
    	if(sScenario.equals("AUDDIS_SCEN")){
    		if (getDriver().findElement(By.xpath(".//span[contains(text(),'AUDDIS_SCEN - 002')]")).isDisplayed()){       		
    			getDriver().findElement(By.xpath(".//span[contains(text(),'AUDDIS_SCEN - 002')]")).click();
           		Thread.sleep(2000);
           	} 
           	else{
           		report.Info("AUDDIS_SCEN element not visisble");
           		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
           		Assert.assertTrue(false);
           	}        	
        	}
  
    	if (getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:ctb8_mainTab::icon']")).isDisplayed()){       		
    		getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:ctb8_mainTab::icon']")).click();
       		Thread.sleep(2000);
       	}
    	else if(getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:ctb8']")).isDisplayed()){{
    		getDriver().findElement(By.xpath(".//*[@id='shell:browse_region:0:ctb8_mainTab::icon']")).click();
       		Thread.sleep(2000);
    	
   }
    	}
	}
	}
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:execscen_agent::content']")
	WebElementFacade  AgentSelection;
	
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:execscen_context::content']")
	WebElementFacade  ContextSelection;
	
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:execscen_loglevel::content']")
	WebElementFacade  LogLevelSelection;
	
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:exescen_vars:0:it_varvalue::content']")
	WebElementFacade  DocstoreValue1;
	
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:exescen_vars:1:it_varvalue::content']")
	WebElementFacade  DocstoreValue2;
	
	@FindBy(xpath=".//*[@id='shell:dialogRegion:1:p_executeScenario']")
	WebElementFacade  ExecuteButton1;
	
	@FindBy(xpath=".//*[@id='d1::msgDlg::cancel']")
	WebElementFacade  ClosePopup;
	
	@FindBy(xpath=".//*[contains(text(),'Scenario submitted successfully')]")
	WebElementFacade  TextMassageCapture;
	
	
	@SuppressWarnings("deprecation")
	@Step
	public void  ExecuteScenario_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{
	 	 
		String filePath = "\\src\\test\\resources\\data\\ODI.xls";
 	 	String dataSheet = "ExecuteScenario";  	 
 	 	Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
	     for (int i = 0;i < tableMap.get("RowNo").size();i++) {      	    	 
	    	                 	
      	String  sAgent = tableMap.get("Agent").get(i);  
      	String  sContext = tableMap.get("Context").get(i);
      	String  sLogLevel = tableMap.get("LogLevel").get(i);
      	String  sDocStoreValue = tableMap.get("DocStoreValue").get(i);
      	String  sSlot1 = tableMap.get("Slot1").get(i);
      	String  sSlot2 = tableMap.get("Slot2").get(i);
      	
      	WebDriverWait wait = new WebDriverWait(getDriver(),120);
       	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='shell:dialogRegion:1:execscen_agent::content']")));
       	Thread.sleep(2000);
 
       	getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:execscen_agent::content']")).sendKeys(sAgent);
       	Thread.sleep(1000);
       	getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:execscen_context::content']")).sendKeys(sContext);
       	Thread.sleep(1000);
       	getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:execscen_loglevel::content']")).sendKeys(sLogLevel);
      	
      	if(sDocStoreValue.equals("Y")){
      		getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:exescen_vars:0:it_varvalue::content']")).sendKeys(sSlot1);
      		getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:exescen_vars:1:it_varvalue::content']")).sendKeys(sSlot2);   		
      	}
      	
      	report.Info("All details setup according to selected scenario");
      	sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
      	getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:p_executeScenario']")).click();   	
      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[contains(text(),'Scenario submitted')]")));
      	if(getDriver().findElement(By.xpath(".//*[@id='shell:dialogRegion:1:p_executeScenario']")).isDisplayed()){      	
	      	String TextMassage = getDriver().findElement(By.xpath(".//*[contains(text(),'Scenario submitted successfully')]")).getText();
	      	String[] SessionId =TextMassage.split(":");
	      	Serenity.setSessionVariable("SessionId0").to(SessionId[1]);
	      	report.Info("Scenario submitted successfully. Created session with id: " +SessionId[1]);
	      	sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='d1::msgDlg::cancel']")));
	      	getDriver().findElement(By.xpath(".//*[@id='d1::msgDlg::cancel']")).click();
	      	Thread.sleep(1000);
	      	getDriver().findElement(By.xpath(".//button[@id='shell:dialogRegion:1:execscen_cb11']")).click();
      	}
      	else{
      		report.Info("Scenario PopUp is not visible");
      		Assert.assertTrue(false);
      	}
  }     
	}

	@FindBy(xpath=".//a[text()='Sessions']")
	WebElementFacade SessionTab;
	
	@FindBy(xpath=".//*[@id='shell:regtab1:0:sessionsearchbutton']")
	WebElementFacade SearchButton;
	
	@FindBy(xpath=".//*[@id='shell:regtab1:0:search_sessionId::content']")
	WebElementFacade SessionIdTextBox;
	
	@FindBy(xpath=".//*[@src='/odiconsole/resources/images/approved.png']")
	WebElementFacade SuccessImage;
	
	@FindBy(xpath=".//*[@title='Expand Search Form']']")
	WebElementFacade Expand_Search_Form;
	
	
	@SuppressWarnings("deprecation")
	@Step
	
	public void  SearchSessionStatus_fn() throws InterruptedException, IOException, AWTException, FindFailed{
		 
		String SessionIDCapture = Serenity.sessionVariableCalled("SessionId0").toString();
		Thread.sleep(4000);
		getDriver().findElement(By.xpath(".//a[text()='Sessions']")).click();
		Thread.sleep(3000);
		String Flag = "N";
		getDriver().findElement(By.xpath(".//*[@id='shell:regtab1:0:search_sessionId::content']")).click();
		Screen sc = new Screen();
		sc.type(SessionIDCapture);
		report.Info("Session id : "+SessionIDCapture);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(".//button[text()='Search']")).click();
		
		for (int i = 0; i < 200; i++) {
			Thread.sleep(2000);
			if (!getDriver().findElement(By.xpath(".//img[@title='Error']")).isDisplayed()) {
				if (getDriver().findElement(By.xpath(".//img[@title='Done']")).isDisplayed()){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Flag = "Y";
					break;
				}
				else {
					getDriver().findElement(By.xpath(".//*[@title='Expand Search Form']")).click();
					Thread.sleep(3000);
					getDriver().findElement(By.xpath(".//button[text()='Search']")).click();
				}			

				if(Flag.equals("N")){
					report.Info("Session is not passed");
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Assert.assertTrue(false);
				}
			}
			else {
				report.Info("Session got error out");
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
		}
		
	}
	
	@FindBy(xpath=".//*[@id='MENU_A_SEARCH']/a")
	WebElementFacade ClickOnSearch;
	
	@FindBy(linkText ="Invoices")
	WebElementFacade ClickOnInvoices;
	
	@FindBy(linkText ="Standard Search")
	WebElementFacade ClickOnStandardSearch;
	
	@FindBy(xpath=".//*[@name='xBillNo']")
	WebElementFacade BillNoSet;
	
	@FindBy(xpath="(.//*[@value='Search'])[2]")
	WebElementFacade InvoiceSearchButton;
	
	@FindBy(xpath=".//div[1]/a[contains(text(),'UCM')]")
	WebElementFacade InvoiceUploadValidation;
	
	@Step
	 public void  SearchInvoiceWCCfn() throws InterruptedException, IOException, AWTException{
	 
		String BillNo = Serenity.sessionVariableCalled("BILLNUMBER0").toString();
		//String BillNo ="B1-953812";
		
		/*if(ClickOnSearch.isDisplayed()){
		ClickOnSearch.click();
		ClickOnInvoices.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
		ClickOnInvoices.click();
		}
		else{
			report.Info("WCC Login is not done successfully");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}*/
		
		if(ClickOnSearch.isDisplayed()){
			ClickOnSearch.click();
			ClickOnStandardSearch.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			ClickOnStandardSearch.click();
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			}
			else{
				report.Info("WCC Login is not done successfully");
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
		
		
		if(BillNoSet.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed()){
			BillNoSet.type(BillNo);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			InvoiceSearchButton.click();
			report.Info("Bill Number : " + BillNo +" is entered successfully.");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		}
		else{
			report.Info("WCC Invoice Search is not done successfully");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		
		if(InvoiceUploadValidation.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed()){
			InvoiceUploadValidation.click();
			Thread.sleep(2000);
			report.Info("WCC Invoice link is displayed successfully after entering Bill Number.");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		}
		else{
			report.Info("WCC Invoice link is not displayed after entering Bill Number.");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		
	}
}



