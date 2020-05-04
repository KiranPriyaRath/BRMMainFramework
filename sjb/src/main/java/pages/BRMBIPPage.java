package pages;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
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
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@SuppressWarnings("deprecation")
public class BRMBIPPage extends PageObject {
	@Steps
    private ReadWorkbook readWorkbook;
	
	@Steps
	private common common1;
	@Steps
	private SikuliUtility sikuliUtility;
	
	@Steps
	private ReportMessege report; 
	  public static boolean isAlertPresent(WebDriver driver) {
          try{
                driver.switchTo().alert();
                return true;
          }catch(NoAlertPresentException ex){
                return false;
          }
    }


	@FindBy(linkText="Home")
	 WebElementFacade BIPHomeLink;
    
    @FindBy(xpath=".//a[text()='Catalog Folders']/img")
	 WebElementFacade CatalogFolder;
    
    @FindBy(xpath=".//*[@id='catalogtree']/table/tbody/tr/td/table[2]/tbody/tr/td[1]/a/img")
	 WebElementFacade Collapse1;
    
    @FindBy(xpath=".//*[@id='catalogtree']/table/tbody/tr/td/table[6]/tbody/tr/td[2]/a/img")
	 WebElementFacade Collapse2;
    
    @FindBy(xpath="(.//*[@id='treeLink']/div)[7]")
	 WebElementFacade pin01Invoice;
    
    @FindBy(linkText="0.0.0.1")
	 WebElementFacade Pin01link;
    
    @FindBy(xpath=".//*[@title='View Report']")
	 WebElementFacade ViewReportList;
    
    @FindBy(linkText="BRM_Bursting_Invoice_Report")
	 WebElementFacade BRM_Bursting_Invoice_Report;
    
    @FindBy(linkText="PostPaid_invoice")
	 WebElementFacade PostPaid_invoice;
    
    @FindBy(linkText="EBU_invoice")
	 WebElementFacade EBU_invoice;
    
    @FindBy(xpath=".//*[@id='_paramsp_Inv_MIN_POID']")
	 WebElementFacade PoidIdText1;
    
    @FindBy(xpath=".//*[@id='_paramsp_Inv_MAX_POID']")
	 WebElementFacade PoidIdText2;
    
    @FindBy(xpath=".//*[@title='Apply']")
	 WebElementFacade ApplyButton;
    
    @FindBy(xpath="(.//div/div/ul/li[2]/div/a/div)[1]")
	 WebElementFacade PDFFormat;
    
    @FindBy(xpath=".//a[@class='imageButton1L']/img[@src='/xmlpserver/resource/blafplus/toolbar/dropdown_md.png']")
	 WebElementFacade ForPDFSave;
    
    @FindBy(xpath="(.//a[@class='masterMenuItem item']/div[@class='itemTxt' and text()='PDF'])[1]")
	 WebElementFacade ClickOnPDF;
    
    String SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
    @Step
	 public void  OpenBRMInvoicing(String InvoiceType) throws InterruptedException, IOException, AWTException, FindFailed{    	
    	
   	 String filePath = "\\src\\test\\resources\\data\\OracleBI.xls";
   	 String dataSheet = "OpenBRMInvoicing";
   	 
   	// String PoidID= "321972193215686521";
   	 String PoidID = Serenity.sessionVariableCalled("PoidID").toString().replace("Invoice poid is " , "");
	     Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(InvoiceType, filePath, dataSheet);
	     for (int i = 0;i < tableMap.get("RowNo").size();i++) {       	    	 
	    	                 	
        	String  sType = tableMap.get("Type").get(i);                 	
        	String iIndex = tableMap.get("Index").get(i);              	                 	
        	
        	if(iIndex.equals(""))        		
        	   iIndex = "0";

        	if(PoidID.equals("")){
        		report.Info("Poid ID is blank");
        		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
        		Assert.assertTrue(false);
        	}
        	Thread.sleep(2000);

        	CatalogFolder.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
        	WebElement element3 = getDriver().findElement(By.xpath(".//a[text()='Catalog Folders']/img"));
   		 JavascriptExecutor executor3 = (JavascriptExecutor)getDriver();
   		 executor3.executeScript("arguments[0].click();", element3);
   		 
            //CatalogFolder.click();
        	 Thread.sleep(2000);
        getDriver().get(getDriver().getCurrentUrl());
        	//Collapse1.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
        	 if (Collapse2.isCurrentlyVisible()){
        		 WebElement element = getDriver().findElement(By.xpath(".//*[@id='catalogtree']/table/tbody/tr/td/table[6]/tbody/tr/td[2]/a/img"));
        		 JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        		 executor.executeScript("arguments[0].click();", element);
        		 

        	//Collapse2.click();
        	 }
        	 
        	 WebElement element = getDriver().findElement(By.xpath("(.//*[@id='treeLink']/div)[7]"));
    		 JavascriptExecutor executor = (JavascriptExecutor)getDriver();
    		 executor.executeScript("arguments[0].click();", element);
    		 
    		 
        	//pin01Invoice.click();
    		 WebElement element1 = getDriver().findElement(By.linkText("BRM_Bursting_Invoice_Report"));
    		 JavascriptExecutor executor1 = (JavascriptExecutor)getDriver();
    		 executor1.executeScript("arguments[0].click();", element1);
    		 Thread.sleep(1000);
    		 
        	//BRM_Bursting_Invoice_Report.click();
        	if(sType.equals("PostPaid_invoice")){
        		//PostPaid_invoice.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
        		Thread.sleep(4000);
        		WebElement element2 = getDriver().findElement(By.linkText("PostPaid_invoice"));
       		 JavascriptExecutor executor2 = (JavascriptExecutor)getDriver();
       		 executor2.executeScript("arguments[0].click();", element2);
        		//PostPaid_invoice.click();
        	}
        	else{
        		//EBU_invoice.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
        		Thread.sleep(4000);
        		WebElement element2 = getDriver().findElement(By.linkText("EBU_invoice"));
         		 JavascriptExecutor executor2 = (JavascriptExecutor)getDriver();
         		 executor2.executeScript("arguments[0].click();", element2);
       		//EBU_invoice.click();
        	}
        	Thread.sleep(3000);
        	//PoidIdText1.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
        	PoidIdText1.type(PoidID);
        	PoidIdText2.type(PoidID);  
        	
        	WebElement element2 = getDriver().findElement(By.xpath(".//*[@title='Apply']"));
      		 JavascriptExecutor executor2 = (JavascriptExecutor)getDriver();
      		 executor2.executeScript("arguments[0].click();", element2);
      		 
        	//ApplyButton.click();                 	
        	Thread.sleep(5000);                 	
        	report.Info("Invoice for Poid :- "+PoidID); 
        	sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
        	/*WebElement element4 = getDriver().findElement(By.xpath(".//a[@class='imageButton1L']/img[@src='/xmlpserver/resource/blafplus/toolbar/dropdown_md.png']"));
     		 JavascriptExecutor executor4 = (JavascriptExecutor)getDriver();
     		executor4.executeScript("arguments[0].click();", element4);
     		 
     		 Thread.sleep(2000);
     		 element4 = getDriver().findElement(By.xpath("(.//a[@class='masterMenuItem item']/div[@class='itemTxt' and text()='PDF'])[1]"));
    		 executor4 = (JavascriptExecutor)getDriver();
    		 executor4.executeScript("arguments[0].click();", element4);*/
	     } 
    }
  
	public void BIPSave() throws InterruptedException, IOException, FindFailed, AWTException{
		String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
		//String SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString(); 
		String Randomno = Integer.toString((111 + (int)(Math.random()*2222)));  
		sikuliUtility.InvoiceSave(SikulifilePath,SikulifilePathForSavingFile,"Invoice"+Randomno);		
		report.Info("File saved successfully in the following path "+SikulifilePathForSavingFile);
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
        Thread.sleep(5000);		
		
	}	
}
