package pages;

import java.awt.AWTException;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import jnr.ffi.Struct.key_t;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import utilities.SikuliUtility;

public class CataloguePage extends PageObject{
	private static final TimeUnit SECONDS = null;
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private common common1;
	@Steps
	private SikuliUtility sikuliUtility;
	@Steps
	private DatabasePage Database;
	
	@Steps
	ReportMessege report;

      
	
	 
	@FindBy(xpath="//a[text()='Catalogue']")
    WebElementFacade CatalogueTab;
    
    @FindBy(xpath="//a[text()='Search']")
    WebElementFacade CatalogueSearchTab;
    
    @FindBy(xpath="//input[@name='Product_20_ID']")
    WebElementFacade ProductIDTextBox;
    
    @FindBy(xpath="//button[text()='Search']")
    WebElementFacade SearchButton;
    
    @FindBy(xpath="//button[@data-display='Add Item']")
    WebElementFacade AddItemButton;
    
    @FindBy(xpath="//button[@title='Orders:New']")
    WebElementFacade NewOrder;
    @FindBy(xpath="//a[text()='Catalogue']")
    WebElement CatalogueTabWebElement;
    
    @FindBy(xpath="//td[@id='1_s_4_l_Product']")
    WebElementFacade AfterProductAddLink;
    @FindBy(xpath=".//div[contains(@style,'display: none;')][ @id='maskoverlay']")
    WebElementFacade Clockobj; 
    @FindBy(xpath="//input[@name='Product_20_ID']")
    WebElementFacade ProductIDTextBoxsync;
    @FindBy(xpath="//button[@data-display='Add Item']")
    WebElementFacade AddItemButtonsync;
    
    @Step
    public void CatalogueSearch() throws InterruptedException, IOException, AWTException{
    	common1.waitForPageLoad(getDriver());
		
		int CatalogRowCount = '0';
		
    	String ProductName = Serenity.sessionVariableCalled("PartNo").toString();
    	common1.waitForElement(CatalogueTabWebElement);
    	
       // CatalogueTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
        long startTime = System.currentTimeMillis();
        	  Thread.sleep(5000);	
        	  CatalogueTab.click();
              
              if (CatalogueSearchTab.isCurrentlyVisible()) {
            	  Thread.sleep(1000);
            	  WebElement element3 = getDriver().findElement(By.xpath("//a[text()='Search']"));
            	  JavascriptExecutor executor3 = (JavascriptExecutor)getDriver();
 	             executor3.executeScript("arguments[0].click();", element3);
            	  //Common.waitForElement(By.xpath("//a[text()='Search']"));
            	  //getDriver().findElement(By.xpath("//a[text()='Search']")).click();
            	  //CatalogueSearchTab.click();
            	  Thread.sleep(1000);
              }
              else if (!CatalogueSearchTab.isCurrentlyVisible()) {
            	  Thread.sleep(3000);
            	  CatalogueSearchTab.click();
                  }
              
              common1.WaitForClock(Clockobj);
              common1.waitForElement(ProductIDTextBoxsync);
              CatalogRowCount = findAll(By.xpath("//table[@summary='Shopping Cart']/tbody/tr")).size();
              //String ProductIDTextBoxValue = getDriver().findElement(By.xpath("//input[@name='Product_20_ID']")).getText();
              getDriver().findElement(By.xpath("//input[@name='Product_20_ID']")).clear();
              getDriver().findElement(By.xpath("//input[@name='Product_20_ID']")).sendKeys(ProductName);
              Thread.sleep(1000);
              getDriver().findElement(By.xpath("//button[text()='Search']")).click();
              
              Thread.sleep(1000);
              /*if (!ProductIDTextBoxValue.equals("")){
            	  getDriver().findElement(By.xpath("//input[@name='Product_20_ID']")).clear();
              }*/
             /* Thread.sleep(1000);
              ProductIDTextBox.typeAndEnter(ProductName);*/
              
              
  			  //Common.waitForElement(AddItemButtonsync);
  			  startTime = System.currentTimeMillis();
  			  
  			common1.WaitForClock(Clockobj);
  			  
  			WebElement element3 = getDriver().findElement(By.xpath("//button[@data-display='Add Item']"));
      	  JavascriptExecutor executor3 = (JavascriptExecutor)getDriver();
             executor3.executeScript("arguments[0].click();", element3);
              //AddItemButton.click();
             common1.WaitForClock(Clockobj);
                             
        int k = 0;
        if (CatalogRowCount < 3){
        do {        	
      	  CatalogRowCount = findAll(By.xpath("//table[@summary='Shopping Cart']/tbody/tr")).size();
      	  if (CatalogRowCount > 1){
      		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
      		long endTime = System.currentTimeMillis();
      		long totalTime = endTime - startTime;
      		common1.WritePerformanceOutput("Product Added,Total Time " + totalTime/1000);
      		  break;
      	  }
      	  Thread.sleep(2000);
      	  k++;
        }while(CatalogRowCount<2 && k<80);
        
        if (CatalogRowCount < 2){
          sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
      	  report.Info("Product is not Added successfully.");
      	  Assert.assertTrue(false);
        } 
        
        if (!LineItemsTab.isDisplayed()){
            getDriver().navigate().refresh();
        }
        return;
        }
        
        do {        	
        	  CatalogRowCount = findAll(By.xpath("//table[@summary='Shopping Cart']/tbody/tr")).size();
        	  if (CatalogRowCount > 3){
        		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        
        		  break;
        	  }
        	  Thread.sleep(2000);
        	  k++;
          }while(CatalogRowCount<4 && k<60);
          
          if (CatalogRowCount < 4){
            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	  report.Info("Product is not Added successfully.");
        	  Assert.assertTrue(false);
          } 
          
          /*if (!LineItemsTab.isDisplayed()){
              getDriver().navigate().refresh();
          } */            
                                   
    }
      
      @FindBy(xpath=".//*[@id='j_s_vctrl_div_tabScreen']")
      WebElementFacade TabDropDown;

        @FindBy(xpath=".//a[text()='Billing profile']")
        WebElementFacade BillingProfilesTab;

    @FindBy(xpath=".//*[@title='Billing profile:Check Payment Date']")
    WebElementFacade checkpaymentDateButton;
    
    @FindBy(xpath=".//a[text()='Profiles']")
    WebElementFacade ProfilesTab;
    

@Step
public void CheckPaymentDate() throws InterruptedException, IOException{
 Thread.sleep(5000);
 
 //TabDropDown.selectByVisibleText("Profiles");
 if (TabDropDown.containsText("Profiles")){
       TabDropDown.selectByVisibleText("Profiles");
       report.Info("Profile is Visible under Tab Drop Down");
   }
   else 
   {
	   ProfilesTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();      
       ProfilesTab.click();
       report.Info("Profile is Visible in Outer Tab");       
   }
		 BillingProfilesTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		 BillingProfilesTab.click();
		 checkpaymentDateButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		 checkpaymentDateButton.click();		 
		 Assert.assertTrue("FAIL, Payment button is disabled ",checkpaymentDateButton.isEnabled());
	}	

@FindBy(xpath=".//*[@id='j_s_vctrl_div_tabScreen']")
WebElementFacade AccountSummaryDropdown;
@FindBy(xpath=".//*[@title='Account Addresses:Delete']")
WebElementFacade AccountAddressDeleteIcon;



@Step
public void AddressesDeleteButtonCheck(String CheckAddressButton) throws InterruptedException, IOException {

String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
String dataSheet = "AddressesDeleteButton";
//ReadWorkbook readWorkbook = new ReadWorkbook();
Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(CheckAddressButton, filePath, dataSheet);
readWorkbook.testData(tableMap);

 if (tableMap.get("User").size()==0){
		       	report.Info(CheckAddressButton+" logical name not found in sheet.");
		       	Assert.assertTrue(false);        	
		       }
			   
//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
for (int i = 0  ;i < tableMap.get("User").size();i++){
      String Userfield = tableMap.get("User").get(i);
      
      AccountSummaryDropdown.selectByVisibleText("Addresses");
      //AccountSummaryDropdown.selectByValue("Addresses");
         Thread.sleep(50000);
      
      AccountAddressDeleteIcon.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilEnabled();
      
      if (Userfield.equals("FIXED_LINE_1")){
            Assert.assertTrue("AccountAddressDeleteIcon is disabled for Fixed Line USer", AccountAddressDeleteIcon.isCurrentlyEnabled());
      }
      
      if (Userfield.equals("TEST_FRS")){
            Assert.assertTrue("AccountAddressDeleteIcon is disabled for FRS USer.", AccountAddressDeleteIcon.isCurrentlyEnabled());
      }
      
      if (Userfield.equals("TEST_RETAIL_1")){
            Assert.assertFalse("AccountAddressDeleteIcon is enabled for Retail User.", AccountAddressDeleteIcon.isCurrentlyEnabled());
      }

}
}     
		@FindBy(xpath="(.//*[contains(@aria-label,'Selection Field') and (@class='siebui-icon-pick applet-form-pick applet-list-pick')])[8]")
		WebElementFacade OpenUserAccountPopup;
		@FindBy(xpath="(.//*[@aria-label='Selection Field'])[6]")
		WebElementFacade OpenBillingAccountPopup;
		@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
		WebElementFacade EnterAccountName;
		@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")            
		WebElementFacade EnterSearchBox;
		
		@FindBy(xpath=".//*[@title='Pick Account:OK']")
		WebElementFacade EnterSearchBoxOKbutton;
		@FindBy(xpath="(.//span[contains(@title,'Show more')])[1]")
		WebElementFacade OrdersPageShowMoreButton;
		@FindBy(xpath="(.//span[contains(@title,'Show less')])[1]")
		WebElementFacade OrdersPageShowLess;
		@FindBy(xpath="(.//div[contains(@title,'List Applet') and @class='siebui-applet siebui-collapsible-applet siebui-list Selected siebui-active siebui-applet-active siebui-hilight'])")
		WebElementFacade PickAccountlistApplet;
		@FindBy(xpath=".//*[@aria-label='Pick Account:Cancel']")
		WebElementFacade EnterSearchBoxCancelbutton;		
		@FindBy(xpath=".//*[contains(@aria-label,'Customer Account')]")
		WebElementFacade CustomerAccountButton;
		@FindBy(xpath="(.//*[@aria-label='Selection Field'])[7]")
		WebElementFacade OpenBillingProfileAccountPopup;
		@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']//following::*[@class='siebui-icon-dropdown applet-form-combo applet-list-combo']")
		WebElementFacade SearchTypeSelection;
		
		@FindBy(xpath="(//button[contains(@aria-label,'Query') and contains(@class,'appletButton')])[4]")
		WebElementFacade PickAccountQueryButton;
		@FindBy(xpath="(//button[contains(@aria-label,'Go') and contains(@class,'appletButton')])[4]")
		WebElementFacade PickAccountGoButton;
		@FindBy(xpath=".//button[contains(@title,'OK') and contains(@class,'appletButton')]")
		WebElementFacade EnterSearchBoxGobutton;
		@FindBy(xpath="//*[@name='Account_Number']")
		WebElementFacade AccountNoTextbox;
		@FindBy(xpath="(.//button[@title='Billing profile:Go'])[1]")
		WebElementFacade BillingProfileGo;
		
		
		
		
		@Step
		public void OrderBillingServiceAccountSelection(String Account) throws InterruptedException, IOException, AWTException {		      
		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "BillingServiceAccount";
		String applet ="" ;
		String table = ""; 
		
		
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Account, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		
		
		for (int i = 0  ;i < tableMap.get("RowNo").size();i++){
		  String sAccountSelection = tableMap.get("AccountSelection").get(i);
		 // String sAccountSet= tableMap.get("AccountSet").get(i);   
		 // String sFind= tableMap.get("Find").get(i); 		 
		  String UIName = tableMap.get("UIName").get(i);
		  String value = tableMap.get("Value").get(i);
		  
		  
		  if  (value.equalsIgnoreCase("ACCOUNT NO")){
			  value = Serenity.sessionVariableCalled("AccountNo").toString();			  
		  }
		  else if (value.equalsIgnoreCase("CHILDACCOUNT")){				
			  value = Serenity.sessionVariableCalled("PrePostAccountNo").toString();
		  }
		  else if (value.equalsIgnoreCase("PAYINGCHILDACCOUNT")){			
			  value = Serenity.sessionVariableCalled("PayingPrePostAccNo").toString();
		  }
		
		  
		CustomerAccountButton.withTimeoutOf(160,TimeUnit.SECONDS).waitUntilClickable();
		
		if(OrdersPageShowMoreButton.isVisible()){
			OrdersPageShowMoreButton.click();
			common1.WaitForClock(Clockobj);
		}
		
		if (sAccountSelection.equals("Billing Profile")){
		     
					if (OrdersPageShowLess.isVisible() && OpenBillingProfileAccountPopup.isVisible()){	               
						OpenBillingProfileAccountPopup.click();
				            Thread.sleep(1000);
				            report.Info("Billing Account Popup is clicked successfully");
				      }
				else {
				      OrdersPageShowMoreButton.click();
				      Thread.sleep(2000);
				      common1.WaitForObjectPresence("(.//*[@aria-label='Selection Field'])[7]");
				      OpenBillingProfileAccountPopup.click();               
				      }
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					applet = "//*[@title='Billing profile List Applet']";
				    table = "//table[@summary='Billing profile']";
		}
		
		if (sAccountSelection.equals("Billing Account")){
		     
					if (OrdersPageShowLess.isVisible() && OpenBillingAccountPopup.isVisible()){	               
				            OpenBillingAccountPopup.click();
				            Thread.sleep(1000);
				            common1.WaitForClock(Clockobj);
				            report.Info("Billing Account Popup is clicked successfully");
				      }
					else if (OrdersPageShowMoreButton.isVisible() && !OpenBillingAccountPopup.isVisible()) {
				      OrdersPageShowMoreButton.click();
				      Thread.sleep(1000);
				      common1.WaitForClock(Clockobj);				      
				      common1.WaitForObjectPresence("(.//*[@aria-label='Selection Field'])[6]");
				      OpenBillingAccountPopup.click();                
				      }
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					applet = "//*[@title='Pick Account List Applet']";
				    table = "//table[@summary='Pick Account']"; 
		}
		     
		if (sAccountSelection.equals("Service Account")){ 
				    if (OrdersPageShowLess.isVisible() && OpenUserAccountPopup.isVisible()){
				        OpenUserAccountPopup.click();
				        common1.WaitForClock(Clockobj);
				        report.Info("User Account Popup is clicked successfully");
				    }
				    else if (OrdersPageShowMoreButton.isVisible() && !OpenUserAccountPopup.isVisible()) {
				        OrdersPageShowMoreButton.click();
				        Thread.sleep(1000);
				        common1.WaitForClock(Clockobj);
				        //common1.waitForElement(OpenBillingAccountPopup);
				        common1.WaitForObjectPresence("(.//*[@aria-label='Selection Field'])[6]");
				        OpenUserAccountPopup.click();              
						  } 
				    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				    applet = "//*[@title='Pick Account List Applet']";
				    table = "//table[@summary='Pick Account']";
		}
		Thread.sleep(1000);
		PickAccountlistApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible();
		if (PickAccountlistApplet.isCurrentlyVisible()){
						
			EnterAccountName.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilEnabled();
							
			
			PickAccountQueryButton.click();
			common1.selectedRow = 1;
			if (!UIName.equals("")){ 
		    	Thread.sleep(2000);
		    	//PickAccountGoButton.withTimeoutOf(160,TimeUnit.SECONDS).waitUntilClickable();
		    	common1.updateSiebList(applet,table, UIName, value);
		    	//AccountNoTextbox.sendKeys(Keys.ENTER);    
		        //PickAccountGoButton.click();
		        Thread.sleep(3000);
		        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		       
	        if (EnterSearchBoxGobutton.isCurrentlyVisible()){
		        common1.waitForElement(EnterSearchBoxGobutton);
		        getDriver().findElement(By.xpath(".//button[contains(@title,'OK') and contains(@class,'appletButton')]")).click();
		        Thread.sleep(1000);
			    }
	        else{
	        	BillingProfileGo.sendKeys(Keys.ENTER);
	        	Thread.sleep(3000);
		        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	        	getDriver().findElement(By.xpath(".//button[contains(@title,'OK') and contains(@class,'appletButton')]")).click();
		        Thread.sleep(1000);
	        }
		        CustomerAccountButton.withTimeoutOf(160,TimeUnit.SECONDS).waitUntilClickable();
				
			}  		    
		}
		                  
		}
		
		getDriver().navigate().refresh();
		Thread.sleep(3000);
		
	} 


@Step
public void OrderSubAccountsBillingProfileSelection(String Account) throws InterruptedException, IOException {
      
      String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
      String dataSheet = "BillingAccountEnableDisable";
      Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Account, filePath, dataSheet);
      readWorkbook.testData(tableMap);
	  
	  if (tableMap.get("Case").size()==0){
		       	report.Info(Account+" logical name not found in sheet.");
		       	Assert.assertTrue(false);        	
		       }
      
      for (int i = 0  ;i < tableMap.get("Case").size();i++){
            String sCase = tableMap.get("Case").get(i);
            
            

            if (OrdersPageShowMoreButton.isVisible() && OpenBillingAccountPopup.isVisible()){
                  
                if(sCase.equals("Enabled"))
                {
                  OpenBillingAccountPopup.click();
                  Assert.assertTrue("Fail, OK Button is Disabled", EnterSearchBoxOKbutton.isEnabled());
                }
               
            }
            
            else if (OrdersPageShowMoreButton.isVisible() && !OpenBillingAccountPopup.isVisible()) {
                  OrdersPageShowMoreButton.click();
                  
                  {
                        OpenBillingAccountPopup.click();
                        Assert.assertTrue("Fail, OK Button is Disabled", EnterSearchBoxOKbutton.isEnabled());
                  }
                 
              }
                  
            
            
 if (OrdersPageShowMoreButton.isVisible() && OpenBillingAccountPopup.isVisible()){
                  
                if(sCase.equals("Disabled"))
                {
                  OpenBillingAccountPopup.click();
                  Assert.assertTrue("Fail, OK Button is Enabled", !EnterSearchBoxOKbutton.isEnabled());
                }
               
            }
            
            else if (OrdersPageShowMoreButton.isVisible() && !OpenBillingAccountPopup.isVisible()) {
                  OrdersPageShowMoreButton.click();
                  
                  {
                        OpenBillingAccountPopup.click();
                        Assert.assertTrue("Fail, OK Button is Enabled", !EnterSearchBoxOKbutton.isEnabled());
                  }
                 
              }
      }
   }


	@FindBy(xpath=".//*[@aria-label='Installed Assets:Fast Orders']")
 WebElementFacade FastOrdersButton;
 
 @FindBy(xpath="//input[@aria-label='Product Type']")
 WebElementFacade ProductType;
 
 @FindBy(xpath=".//*[@aria-label='Add Remove Products:Go']")
 WebElementFacade ProductTypeGoButton;
 
 @FindBy(xpath=".//*[@aria-label='Add Remove Products:OK']")
 WebElementFacade AddRemoveProductsOKButton;


 
 @Step
 public void FastOrdersProducts(String LocateCol) throws InterruptedException, IOException{        
      
       String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
       String dataSheet = "FastOrdersProducts";
       String applet = "//*[@title='Add Remove Products List Applet']";
       String table = "//table[@summary='Add Remove Products']";   
       
       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(LocateCol, filePath, dataSheet);
       readWorkbook.testData(tableMap);
       
	          ProductTypeGoButton.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
	          String sProductType = tableMap.get("ProductType").get(0); 
	          ProductType.type(sProductType);
	          ProductTypeGoButton.click();
       
       for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
       	    	
   	   	String sLocateCol = tableMap.get("LocateCol").get(i);
   		String sLocateColValue = tableMap.get("LocateColValue").get(i);
   		String sUIName = tableMap.get("UIName").get(i);
   		String sValue = tableMap.get("Value").get(i);

   		String Index = "0";
   		
   	//NewOrder.click();
   		
   		
   		if (!sLocateCol.equals("")){
             common1.selectedRow = -1;
            Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
            report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

             }
        else{
              common1.selectedRow = 1;
        }
         	if (!sUIName.equals("")){ 
             common1.updateSiebList(applet,table, sUIName, sValue);
             report.Info("Row updated successfully"); 
             }
       }
         	AddRemoveProductsOKButton.click();	
         	
       
   }
 
 
 @Step
 public void SubAccountBillingAccountNameSelection(String Account) throws InterruptedException, IOException{        
      
       String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
       String dataSheet = "BillingAccountNameSelection";
       String applet = "//*[@title='User Accounts List Applet']";
       String table = "//table[@summary='User Accounts']";
       
       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Account, filePath, dataSheet);
       readWorkbook.testData(tableMap);
       
       for (int i = 0;i < tableMap.get("LocateCol").size();i++) {    	
   	   	String sLocateCol = tableMap.get("LocateCol").get(i);
   		String sLocateColValue = tableMap.get("LocateColValue").get(i);
   		String sUIName = tableMap.get("UIName").get(i);
   		String sValue = tableMap.get("Value").get(i);
   		

   		String Index = "0";
   		
   		
   		/*if (!sLocateCol.equals("")){
             common1.selectedRow = -1;
            Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
            report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

             }
        else{*/
              common1.selectedRow = 1;
       // }
         	if (!sUIName.equals("")){ 
             common1.updateSiebList(applet,table, sUIName, sValue);
             Thread.sleep(3000);
             report.Info("Row updated successfully"); 
             }
         
         	
         	
       }
   }
 @Step
 public void BillingProfileNameSelection(String RowName) throws IOException, InterruptedException{
 	String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
     String dataSheet = "BillingProfileNameSelection";
     String applet = "//*[@title='Billing profile List Applet']";
     String table = "//table[@summary='Billing profile']";  
     String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
    
     
     Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RowName, filePath, dataSheet);
     readWorkbook.testData(tableMap);
     
     if (ProfilesTab.isPresent())
     {
     ProfilesTab.click();
     }
     else
     {
     TabDropDown.selectByVisibleText("Profiles");
     }
     
     BillingProfilesTab.click();
     
     
     for (int i = 0;i < tableMap.get("Row").size();i++) {
    	 
 	   	String sLocateCol = tableMap.get("LocateCol").get(i);
 		String sLocateColValue = tableMap.get("LocateColValue").get(i);
 		String UIName = tableMap.get("UIName").get(i);
 		String value = tableMap.get("Value").get(i);
 		String Index = tableMap.get("Index").get(i);
 
         if (Index.equals("")){
           	 Index = "0";
            }
            
            if (!sLocateCol.equals("")){
                common1.selectedRow = -1;
                Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
                report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

                }
           else{
                 common1.selectedRow = 1;
           }
        	if (!UIName.equals("")){ 
        	//Thread.sleep(5000);
            common1.updateSiebList(applet,table, UIName, value);           
             
            }

}	
 }
     @FindBy(xpath="//a[text()='Line Items']")
     WebElementFacade LineItemsTab;
     @FindBy(xpath=".//*[@aria-labelledby='UnitPrice_Label']")
     WebElementFacade CostOverRideVATS;
     @FindBy(xpath=".//*[@aria-label='Discount Reason']")
     WebElementFacade DiscountReason;
     
 @Step
 public void OrdersLineItemsLineDetailDiscount(String Action) throws IOException, InterruptedException{
 String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
 String dataSheet = "OrdersLineDetailDiscount";
    
 
 Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
 readWorkbook.testData(tableMap);

 
 for (int i = 0;i < tableMap.get("Row").size();i++) {
	 
		String OverrideCost = tableMap.get("OverrideCost").get(i);
		String Row = tableMap.get("Row").get(i);
		
		LineItemsTab.click();
	Thread.sleep(3000);
 
	CostOverRideVATS.type(OverrideCost);
	Thread.sleep(2000);
	DiscountReason.typeAndEnter("MNP");
	Thread.sleep(2000);

 }
 } 
}	
	

