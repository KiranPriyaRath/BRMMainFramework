package pages;

import net.serenitybdd.core.Serenity;
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

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class CustomerCommsPage extends PageObject {
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private common common1;
	@Steps
	ReportMessege report;
	private static final boolean se = false;
	@Steps
	private SikuliUtility SikuliUtility;
	
	public static boolean isAlertPresent(WebDriver driver) {
	      try{
	            driver.switchTo().alert();
	            return true;
	      }catch(NoAlertPresentException ex){
	            return false;
	      }
	}

	@FindBy(xpath=".//*[@id='j_s_sctrl_tabScreen']")
    WebElementFacade FristLevelViewBar;
	
	@FindBy(xpath=".//*[@id='ui-id-134']")
    WebElementFacade CustomerCommsList;

	@FindBy(xpath=".//*[@aria-label='Activities:Set/Reset Password']")
    WebElementFacade SetResetPassword;
	
	
	
	
@Step
//@Screenshots(beforeAndAfterEachStep=true)

	public void CustomerCommsVerification() throws InterruptedException {
	   //public void CustomerCommsVerification() throws InterruptedException{
	          
	          Thread.sleep(5000);
	          
	          FristLevelViewBar.selectByVisibleText("Customer Comms");
	          CustomerCommsList.waitUntilVisible();
	          
	          CustomerCommsList.click();
	          if (SetResetPassword.isVisible()){
	        	  report.Info("The Set/Reset Password is Visible");
	          }
	          else 
	          {
	        	  report.Info("The Set/Reset Password is Not Visible");
	        	  
	          }
	          
	          
	          
		
	}



	@FindBy(xpath="(.//*[@title='Customer Comms'])[1]")
		 WebElementFacade CustomerCommsTab;
	
	@FindBy(xpath=".//*[@id='s_1_1_19_0_Ctrl']")
		  WebElementFacade NewCustCommsBttn;
	
	@FindBy(xpath=".//*[@id='1_s_1_l_DPA_Validation']")
	   WebElementFacade DPAValidationElement;
	
	@FindBy(xpath=".//*[@id='1_DPA_Validation']")
		  WebElementFacade DPAValidationTextBox;
	
	@FindBy(xpath=".//*[@aria-label='Activities:Set/Reset PIN']")
		  WebElementFacade SetResetPin;
		
	@FindBy(xpath=".//*[@aria-label='Activities:Set/Reset Word and Hint']")
		  WebElementFacade SetResetWordHint;
	@FindBy(xpath=".//*[@title='Visibility']/select")
    WebElementFacade SelectCustomerComms; 
   // This  function is used to validate the buttons  under customer comms page
public void CustCommsButtonsValidation() throws InterruptedException
{
	Thread.sleep(5000);
	if (CustomerCommsTab.isVisible())
	{
		CustomerCommsTab.click();
	}
	else
	{
		FristLevelViewBar.selectByVisibleText("Customer Comms");
	}
	CustomerCommsList.waitUntilVisible();
	CustomerCommsList.click();
	SelectCustomerComms.selectByVisibleText("My Customer Comms");
	Thread.sleep(2000);
	NewCustCommsBttn.click();
	Thread.sleep(2000);
	DPAValidationElement.click();
	DPAValidationTextBox.type("Passed");
	Assert.assertFalse("FAIL, The Set/Reset Password is Enabled", SetResetPassword.isEnabled());
	Assert.assertFalse("FAIL, The Set/Reset Pin is Enabled", SetResetPin.isEnabled());
	Assert.assertFalse("FAIL, The Set/Reset Word/Hint is Enabled", SetResetWordHint.isEnabled());
	

	
}

		@FindBy(xpath=".//*[@id='s_2_1_13_0_Ctrl']")
		WebElementFacade CustomComsListNewButton;
		@FindBy(xpath=".//*[@id='1_s_2_l_Id']/a")
		WebElementFacade CustomComsID;
		@FindBy(xpath=".//*[@aria-labelledby='Status_Label']")
		WebElementFacade CustomerCommsFormStatus;
		@FindBy(xpath=".//*[@id='a_5']/div/table/tbody/tr[7]/td[9]/div/input")
		WebElementFacade CustCommDPAValidationTextBox;
		@FindBy(xpath=".//*[@id='s_1_1_2_0_Ctrl']")
		WebElementFacade SetResetHint;
		@FindBy(xpath=".//*[@aria-labelledby='VF_Reset_Word_Label']")
		WebElementFacade ResetWordTextBox;
		@FindBy(xpath=".//*[@aria-labelledby='VF_Reset_Hint_Label']")
		WebElementFacade ResetHintTextBox;
		@FindBy(xpath=".//*[@aria-label='Reset Word:Ok']")
		WebElementFacade ResetWordOKButton;  

@Step
public void CustomComsListNew(String CustomerComms) throws IOException, InterruptedException{
String filePath = "\\src\\test\\resources\\data\\Account.xls";
String dataSheet = "CreateNewCustComms";

Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(CustomerComms, filePath, dataSheet);
readWorkbook.testData(tableMap);
//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
for (int i = 0;i < tableMap.get("Size").size();i++) {
      String VerifyStatus = tableMap.get("VerifyStatus").get(i);
      String DPAValidationInLineItems = tableMap.get("DPAValidationInLineItems").get(i);
      

      CustomComsListNewButton.waitUntilEnabled();
      CustomComsListNewButton.click();
      Thread.sleep(5000);
      CustomComsID.waitUntilEnabled();
      CustomComsID.click();
      Thread.sleep(5000);
      SetResetHint.click();
      ResetWordTextBox.type("automation123");
      ResetHintTextBox.type("automation");
      ResetWordOKButton.click();
      Thread.sleep(5000);
      
      Alert alert=getDriver().switchTo().alert();
            String ActAlertText=alert.getText();
            report.Info("Alert messsage is:"+ActAlertText);
            Assert.assertTrue("FAIL, Expected Pop UP is not displayed ",ActAlertText.contains("Success"));                
            getDriver().switchTo().alert().accept();                    
      Thread.sleep(5000);
      CustomerCommsFormStatus.type(VerifyStatus);
      CustCommDPAValidationTextBox.type(DPAValidationInLineItems);
      String CustCommsDPAStatus = CustomerCommsFormStatus.getTextValue();
      String CustCommDPAValidation = CustCommDPAValidationTextBox.getTextValue();
      report.Info("DPA Validation is "+CustCommDPAValidation);
      report.Info("DPA status is "+CustCommsDPAStatus);

      Assert.assertTrue("FAIL, DPA validation is not Closed ",CustCommDPAValidation.equals("Passed"));
      Assert.assertTrue("FAIL, CustomerComms is not Passed ",CustCommsDPAStatus.equals("Closed"));  
      
		}
	}


	@FindBy(xpath=".//*[@aria-label='Pick Contact:OK']")
	WebElementFacade PickContactOKButton;
	
	@FindBy(xpath=".//*[@aria-label='Activities:Menu']")
	WebElementFacade CustCommsMenuIcon;
	
	@FindBy(xpath="//span/ul/li[9]/a")
	WebElementFacade RefreshRecord;

public void VerifyCustCommList(String accountType) throws InterruptedException, IOException
{
	
	String filePath = "\\src\\test\\resources\\data\\Account.xls";
    String applet = "//*[@title='Activities List Applet']";
    String table = "//table[@summary='Order Dashboard Details']";
	String dataSheet = "CreateNewCustComms";
   
	Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(accountType, filePath, dataSheet);
	readWorkbook.testData(tableMap);

	for (int i = 0;i < tableMap.get("Size").size();i++) {
	   
	   String DPAValidationInLineItems = tableMap.get("DPAValidationInLineItems").get(i);
       String UIName =  tableMap.get("UIName").get(i);
       String value =  tableMap.get("Value").get(i);                     
       String sLocateCol = tableMap.get("LocateCol").get(i);
       String sLocateColValue = tableMap.get("LocateColValue").get(i);
       String Index = tableMap.get("Index").get(i);
       String ClickOnNew = tableMap.get("ClickOnNew").get(i);
       
       if (Index.equals(""))
       {
      	 Index = "0";
       }
	   if(ClickOnNew.equals("Yes"))
	   {
		Thread.sleep(5000);
		if (CustomerCommsTab.isCurrentlyVisible())
		{
			CustomerCommsTab.click();
		}
		else
		{
			FristLevelViewBar.selectByVisibleText("Customer Comms");
		}
		Thread.sleep(10000);
		SelectCustomerComms.selectByVisibleText("My Customer Comms");
		Thread.sleep(2000);
		NewCustCommsBttn.click();
		Thread.sleep(2000);
	   }
		
        if (!sLocateCol.equals(""))
        {
           common1.selectedRow = -1;
           Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
           report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
        }
       else
       {               
    	   common1.selectedRow = 1;
       }
  	  if (!UIName.equals("")){         		 
            common1.updateSiebList(applet,table, UIName, value); 
            Thread.sleep(2000);                       	              
            report.Info("Action : "+UIName+" has perfomed "+value);   

  	  }
  	  if(PickContactOKButton.isCurrentlyVisible())
  	  {
    	  	Thread.sleep(2000);
    	  	PickContactOKButton.click();
    	  	Thread.sleep(2000);
  	  }

		DPAValidationElement.click();
		if(!DPAValidationInLineItems.equals("Passed"))
		{
		DPAValidationTextBox.type(DPAValidationInLineItems);
		Assert.assertTrue("FAIL, The Set/Reset Password is Enabled", !SetResetPassword.isEnabled());
		Assert.assertTrue("FAIL, The Set/Reset Pin is Enabled", !SetResetPin.isEnabled());
		Assert.assertTrue("FAIL, The Set/Reset Word/Hint is Enabled", !SetResetWordHint.isEnabled());
		report.Info("Buttons are Disabled as Expected");
		}
		if(DPAValidationInLineItems.equals("Passed"))
		{
			DPAValidationTextBox.type(DPAValidationInLineItems);
			CustCommsMenuIcon.click();
			RefreshRecord.click();
			Thread.sleep(5000);
			Assert.assertTrue("FAIL, The Set/Reset Password is Disabled", SetResetPassword.isEnabled());
			Assert.assertTrue("FAIL, The Set/Reset Pin is Disabled", SetResetPin.isEnabled());
			Assert.assertTrue("FAIL, The Set/Reset Word/Hint is Disabled", SetResetWordHint.isEnabled());
			report.Info("Buttons are Enabled as Expected");
			}
		
  	  
		}

	    		           		      
	}

@FindBy(xpath="//*[text()='Account Summary']")
WebElementFacade AccountSummaryTab;  
@FindBy(xpath = ".//*[@aria-label='Third Level View Bar']")
WebElementFacade AccountTabsDropDown;
@FindBy(xpath=".//*[@title='Customer Comms List:New']")
WebElementFacade NewCustomerCommsButton; 
@FindBy(xpath=".//*[@aria-labelledby='Category_Label']")
WebElementFacade CategoryTextBox; 
@FindBy(xpath=".//*[@aria-labelledby='Class_Label']")
WebElementFacade SubCategoryTextBox;
@FindBy(xpath=".//*[@aria-labelledby='Resolution_Code_Label']")
WebElementFacade ResolutionTextBox;
@FindBy(xpath=".//*[@aria-labelledby='VF_DPA_Validation_Label']")
WebElementFacade DPAValidationTextBoxCC;
@FindBy(xpath=".//table[@summary='Customer Comms List']/tbody/tr[2]/td[2]/a")
WebElementFacade NewCustomerCommsID;
@FindBy(xpath=".//*[@aria-label='Customer Comms:Create SR']")
WebElementFacade CreateSRButton;

@FindBy(xpath=".//*[@aria-labelledby='SRNumber_Label']")
WebElementFacade SRIDTextBox;
@FindBy(xpath=".//*[@aria-labelledby='INS_Product_Label']")
WebElementFacade TypeTextBox;
@FindBy(xpath=".//*[@aria-labelledby='Area_Label']")
WebElementFacade AreaTextBox;
@FindBy(xpath=".//*[@aria-labelledby='Sub-Area_Label']")
WebElementFacade SubAreaTextBox;
@FindBy(xpath="(.//*[@aria-labelledby='Description_Label'])[1]")
WebElementFacade NotesTextBox;
@FindBy(xpath=".//*[@aria-labelledby='Abstract_Label']")
WebElementFacade DescrptionTextBox;
@FindBy(xpath=".//*[@aria-labelledby='CTN_Label']/following-sibling::span")
WebElementFacade InstalledIDOpenPopUp;
@FindBy(xpath=".//*[@aria-label='Pick Asset:OK']")
	WebElementFacade OKButtonPickAssetApplet;
@FindBy(xpath=".//*[@aria-label='Service Requests:Customer Account']")
WebElementFacade CustomerAccountButtoninSR;
@FindBy(xpath="//span/ul/li[6]/a")
WebElementFacade SRSave;
@FindBy(xpath=".//*[@title='Service Requests Menu']")
WebElementFacade SRMenuButton;



public void CreateServiceRequestviaCustComms(String Row) throws InterruptedException, IOException, AWTException
{

		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		//String applet = "//*[@title='Activities List Applet']";
		//String table = "//table[@summary='Order Dashboard Details']";
		String dataSheet = "CustomerCommsServiceRequest";
		
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Row, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0;i < tableMap.get("UIName").size();i++) {
		   String sCategory =  tableMap.get("Category").get(i);
		   String sSubCategory =  tableMap.get("SubCategory").get(i);                     
		   String sResolution = tableMap.get("Resolution").get(i);
		   String sDPAValidation = tableMap.get("DPAValidation").get(i);
		   String sType = tableMap.get("Type").get(i);
		   String sArea = tableMap.get("Area").get(i);
		   String sSubArea = tableMap.get("SubArea").get(i);
		   String sSave = tableMap.get("Save").get(i);
		   Alert alert;
		   String ActAlertText=""; 
		   						
		
		   if (AccountSummaryTab.isDisplayed()){
			   AccountSummaryTab.click();	    		   
				}
				else {
					AccountTabsDropDown.selectByVisibleText("Account Summary");	   			
				} 
		   	
		   	   Thread.sleep(2000);
			   NewCustomerCommsButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   NewCustomerCommsButton.click();
				   Thread.sleep(2000);
			   NewCustomerCommsButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	
				   String NewCustomerCommID = NewCustomerCommsID.getTextValue();
				   report.Info("New Customer Comms was created. ID is : "+NewCustomerCommID);
				   
				   NewCustomerCommsID.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				   NewCustomerCommsID.click();
				  Thread.sleep(4000);
				  
				    CategoryTextBox.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		  		CategoryTextBox.type(sCategory);
		  		SubCategoryTextBox.type(sSubCategory);
		  		ResolutionTextBox.type(sResolution);
		  		DPAValidationTextBoxCC.typeAndEnter(sDPAValidation);
		  		report.Info("CustomerComms Created.");
		  		
		  	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
		  		CreateSRButton.click();
		  		
		  		
		  		CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		  		SRIDTextBox.click();
		  		TypeTextBox.type(sType);
		  		AreaTextBox.type(sArea);
		  		SubAreaTextBox.type(sSubArea);
		  		NotesTextBox.type("notes");
		  		DescrptionTextBox.type("abcdef");
		  	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		  		if(sType.equalsIgnoreCase("Disconnections")){
		  			InstalledIDOpenPopUp.click();
		  			OKButtonPickAssetApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		  		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		  			OKButtonPickAssetApplet.click();
		  		}
		  		if (sSave.equalsIgnoreCase("Yes")){
		  			Thread.sleep(1000);	 
		  			CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		           	SRMenuButton.click();
		           	Thread.sleep(1000);
		           	SRSave.click();
		           	Thread.sleep(4000);
		           	//CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		           	
		           	if(isAlertPresent(getDriver())){
		                alert=getDriver().switchTo().alert();
		                 ActAlertText=alert.getText();  
		           	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                                                
		               if (ActAlertText.contains("Please ensure the mobile number populated in the")){  
		            		
		                     alert.accept();
		               }
		               else {
		                   report.Info("Pop did not occur");
		                   Assert.assertTrue(false);
		               }
		           	}
		  			
		  		}
		  		report.Info("New SR Created.");
		  
		   }
		}

}