package pages;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.common;
import utilities.SikuliUtility;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;


public class AccountSummaryPage extends PageObject {
	
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private SikuliUtility sikuliUtility;
	@Steps
	private SikuliUtility SikuliUtility;
	@Steps
	private common Common;
	private DatabasePage Database;
	

	@Steps
	ReportMessege report;
	
	@Steps
    private common common1;	
	 public static boolean isAlertPresent(WebDriver driver) {
	      try{
	            driver.switchTo().alert();
	            return true;
	      }catch(NoAlertPresentException ex){
	            return false;
	      }
	}
	
	 @FindBy(xpath=".//*[@aria-label='InstalledAssets:Modify']//following::*[@aria-label='Installed Assets:Fast Orders']")
	  WebElementFacade FastOrdersAfterModifyButton;    
	  @FindBy(xpath=".//*[@aria-label='Installed Assets:Fast Orders']")
	  WebElementFacade FastOrdersButton; 
	  @FindBy(xpath=".//*[@id='jqgh_s_1_l_Product_Name']")
	  WebElementFacade ModifyButton;
	  @FindBy(xpath=".//*[@aria-label='Account Details:Menu']")
	  WebElementFacade AccountSummaryMenuButton;
	  @FindBy(xpath="//span/ul/li[9]/a")
     WebElementFacade AccountSummaryRefreshMenuItem;
	  @FindBy(xpath=".//div[contains(@style,'display: none;')][ @id='maskoverlay']")
	    WebElement Clockobj; 
	  
	  @FindBy(xpath=".//*[@id='1_Product_Name']")
	  WebElementFacade ProductName; 
	  @FindBy(xpath=".//*[@aria-label='Installed Assets:Menu']")
	  WebElementFacade UsedProductMenuButton; 
	  @FindBy(xpath=".//*[@aria-label='Third Level View Bar']")
	  WebElementFacade AccountTabsDRopDown;
	  @FindBy(xpath=".//button[text()='Done']")
     WebElementFacade DoneButton;
	 
	  @FindBy(xpath=".//*[aria-label = 'starting with']")
     WebElementFacade TextBox;
	  
	  //Performs actions on Menu and Button in UsedProduct&Services Applet
	  // By Payel
	  
	  @FindBy(xpath=".//*[@data-tabindex='tabScreen4']")
	  WebElementFacade Ordertab;

	  @FindBy(xpath=".//*[@title='Sales Orders:Query']")
	  WebElementFacade OrderQuery;


	  @FindBy(xpath=".//*[@id='s_2_1_1_0_icon']")
	  WebElementFacade OrderListAplet;


	  @FindBy(xpath=".//*[@name='Order_Number']")
	  WebElementFacade ListAplet;

	  @FindBy(xpath=".//*[@id='s_2_1_6_0_Ctrl']")
	  WebElementFacade SalesOrderGo;


	  @FindBy(xpath=".//*[@id='1_s_2_l_Account']/a")
	  WebElementFacade ClickOnAccountname;


	  @FindBy(xpath=".//*[@id='1_s_1_l_Order_Number']/a")
	  WebElementFacade ClickOnOrderNumber;
	  
	  @FindBy(xpath=".//*[@aria-label='Messages:Accept']")
     WebElementFacade AcceptButton;
       
     @FindBy(xpath=".//*[@name='popup']")
     WebElementFacade VerifyMessages;
     
     @FindBy(xpath=".//*[@class='siebui-ctrl-btn appletButton'][contains(text(),'Ok')]")
		WebElement EconfigDoneButtonSync;
     
     @FindBy(xpath="//span[text()='Include']")
     WebElementFacade DisconnectPopupMsg;
	  
	  @Step      
	   public void UsedProductServices(String action) throws IOException, InterruptedException, FindFailed, AWTException{
		  Common.waitForPageLoad(getDriver());

		   String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
		   String filePath = "\\src\\test\\resources\\data\\Account.xls";
	       String dataSheet = "UsedProductServices";
	       String applet = "//*[@title='Installed Assets List Applet']";
	       String table = "//table[@summary='SIS OM Products & Services Root List (Service) - Tiny']";   	

	       
	       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(action, filePath, dataSheet);
	       readWorkbook.testData(tableMap);
	     	       
	       for (int i = 0;i < tableMap.get("LocateCol").size();i++) { 	  
	    	   String sLocateCol = tableMap.get("LocateCol").get(i);
	    	   String sLocateColValue = tableMap.get("LocateColValue").get(i);
	    	   if (sLocateColValue.equals("Promotion")){
	    		   sLocateColValue = Serenity.sessionVariableCalled("ProductName").toString();
	    	   }
	    	   if (sLocateColValue.equals("InstalledId")){
	    		   sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN").toString();
	    	   } 
	      		if (sLocateColValue.contains("RootProduct0")){
        			//sLocateColValue = sLocateColValue.replace("RootProduct0", Serenity.sessionVariableCalled("RootProduct0").toString());
        			sLocateColValue = "PAYM";
    			}
	    	   String sIndex = tableMap.get("Index").get(i);
	    	   String sAction = tableMap.get("Action").get(i);
	    	   String sEnableAction = tableMap.get("EnableAction").get(i);
	    	   String sEnable = tableMap.get("Enable").get(i);
	    	   String sUIName = tableMap.get("UIName").get(i);
	    	   String sValue = tableMap.get("Value").get(i);
	    	   String sPopUp = tableMap.get("PopUp").get(i);
	    	   
	    	   long startTime = System.currentTimeMillis();
	    	   if (AccountSummaryTab.isDisplayed()){
	    		   AccountSummaryTab.click();	    		   
		   		}
		   		else {
	   			AccountTabsDRopDown.selectByVisibleText("Account Summary");	   			
		   		} 
	    	   
	    	   ///Code to refresh Account summary page till Used product Service has rows in the applet
	    	   
	    	   int k=0;
			   	 int RowCount = '0';
		          do {
	        	     /* ProductServicesMenuIcon.click();
			    	  Thread.sleep(1000);
			    	  RefreshMenuItem.click();
			    	  Thread.sleep(1000);*/
		        	  //getDriver().navigate().refresh();
		        	  RowCount = findAll(By.xpath("//table[@summary='SIS OM Products & Services Root List (Service) - Tiny']/tbody/tr")).size();
		        	  if (RowCount > 2){		        		  
		        		  long endTime = System.currentTimeMillis();
		        		  long totalTime = endTime - startTime;
		        		  //Common.WritePerformanceOutput("Used Product Rows found successfully,Total Time " + totalTime/1000);
		        		  
		        		  break;
		        	  }
		        	  Common.waitForPageLoad(getDriver());
		        	  k++;
		          }while(RowCount<3 && k<20);
		          
		          if (RowCount < 3){
		        	  report.Info("Used Product Rows is not added successfully.");
		        	  Assert.assertTrue(false);
		          }
	    		    	  
		   	 /*int k=0;
			    if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")&& !sLocateColValue.equals("Null")){
			   	    do{
			   	    	
			   	    	ProductServicesMenuIcon.click();
				    	Thread.sleep(1000);
				    	RefreshMenuItem.click();
			   	    	getDriver().navigate().refresh();
				    	Thread.sleep(15000);	   	    	
			   	    	k++;
			   	    	}while(common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true") && (k<10));
			    }
	    	   */
	    	   
	    	   //ModifyButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		          Common.waitForPageLoad(getDriver());

	    	   
	    	   if(sAction.equalsIgnoreCase("FastOrder")){
		    	   
		    	   if(FastOrdersAfterModifyButton.isPresent()){
		    		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		    		   report.Info("Fast Order button is present on right side of Modify button as expected");
		    		   break;
		    	   }
		    	   else{
		    		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		    		   report.Info("Fast Order button is not present on right side of Modify button.");
		    		   break;
		    	   } 
													  				   
	    	   }
	    	   
	    	   
	    	   if (sIndex.equals("")){
	    		   sIndex = "0";
	            }
               
              // String LocateColMessege = sLocateColValue+" Not Found";             

               if (!sLocateCol.equals("")){
         
               	 common1.selectedRow = -1;
                    String LocateColMessege = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex);
                    Assert.assertTrue(LocateColMessege, LocateColMessege.equals("true"));       
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  							 
                    }
               else{
                     common1.selectedRow = 1;
               }
	    	    	    	   	    	   
	    	   if (!sUIName.equals("")){
	    		   if (sValue.equals("AgreementRowID")){
	    			   sValue = Serenity.sessionVariableCalled("AgreementRowID").toString();
	    		   }//do not delete
	    		common1.updateSiebList(applet,table, sUIName, sValue);
	    		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	    	   }
	   		
	    	   String sActEnable = "" ;
	   			if  (!sEnableAction.equals("")) {
	   				String FO = FastOrdersButton.getAttribute("disabled");
					/*if (FO.equals("true")){
						sActEnable = "No";
						ProductName.click();
						//screenshot
					
					}*/
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	   				
	   				if (FastOrdersButton.isEnabled()){
						sActEnable = "Yes";
						//ProductName.click();
					} 
					else {
						sActEnable = "No";
						//screenshot
						
					}	
	   			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue("FAIL : "+sEnableAction+" existence is "+sActEnable+"  but expected was "+sEnable , sActEnable.equalsIgnoreCase(sEnable));
			
				report.Info("PASS : "+sEnableAction+" existence is "+sActEnable+" as expected");
				break;									   				   			
	   			}
	   			
	   			if (!sAction.equals("")){
	   				//SessionVariableSetter UsedProdAction = Serenity.setSessionVariable(System.currentTimeMillis());
					Serenity.setSessionVariable("UsedProdAction").to(System.currentTimeMillis());
	   				if ((sAction.equals("Modify"))|| (sAction.equals("Fast Orders"))){
	   					//This will click on Modify or Fast Orders
	   					String Button = ".//*[@title='Installed Assets:"+sAction+"']" ;	   				
	   					find(By.xpath(Button)).click();	   					
	   					report.Info("Info : Clicked on Menu "+sAction);
	   					
	   					Thread.sleep(5000);
	   					//Common.waitForPageLoad(getDriver());
	   					Common.WaitForClock(Clockobj); 
	   					//sPopUpImage = "FraudRiskConfirmButton";
	   					if (sAction.equals("Modify")){	   						
							startTime = System.currentTimeMillis();
							//find(By.xpath(Button)).click();	 
							if(isAlertPresent(getDriver())){
								Alert alert = getDriver().switchTo().alert();
								String ActAlertText = alert.getText(); 
																  				  						
								if (ActAlertText.contains("FRAUD RISK")){
									alert.accept();
									System.out.println("alert messsage is"+ActAlertText);
								}                                                                                       
								                     
							}
							Common.waitForElement(EconfigDoneButtonSync);

							long endTime = System.currentTimeMillis();
							long totalTime = endTime - startTime;

							Common.WritePerformanceOutput("Modify Clicked "+sAction+" ,Total Time " + totalTime/1000);
	   					}
	   					if (sAction.equals("Modify Promo")){
	   						sikuliUtility.ClickModPromokAlert(SikulifilePath);
	   					}
	   						   					   				
	   				}	
	   				else{
	   					//Thread.sleep(3000);
	   					UsedProductMenuButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	   					UsedProductMenuButton.click();
	   					//String Menu = "//a[text()='"+sAction+"']" ;	   
	   					String Menu = "//a[contains(text(),'"+sAction+"')]" ;
	   					
	   					getDriver().findElement(By.xpath(Menu)).click();
	   					report.Info("Info : Clicked on Menu "+sAction);
	   					//Thread.sleep(3000);
	   					
	   					if (sAction.equals("Modify Promo")){
	   						if (sUIName.equals("Prepay")){
	   							break;
	   						}
	   						sikuliUtility.ClickModPromokAlert(SikulifilePath);
	   					}
	   					
	   					
	   				}
	   				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   			}

	   			if ((sAction.equals("Disconnect"))||(sAction.equals("Post to Pre Migration"))||(sAction.equals("Pre to Post Migration"))){
	   				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   				
	   			 if (DisconnectPopupMsg.isVisible()){
	   	            	DisconnectPopupMsg.click();
	   	            	//Thread.sleep(3000);
	   	            	report.Info("Disconnection Pop button Clicked");
	   	            	Common.waitForElement(AcceptButton);
	   	               }
	   				
	   				if (AcceptButton.isCurrentlyVisible()){
                       AcceptButton.click();
                         report.Info("Verify Message Accept button Clicked");
                       
                       }
	   				
	   			}				
	       }		   
	   }


		@FindBy(xpath=".//a[text()='Profiles']")
		WebElementFacade ProfilesTab;
		@FindBy(xpath=".//a[text()='Billing profile']")
		WebElementFacade BillingProfilesTab;
		@FindBy(xpath=".//*[@id='1_s_6_l_VF_Payment_Terms']")
		WebElementFacade ClickPaymentTermsElement;
		@FindBy(xpath=".//*[@id='1_VF_Payment_Terms']")
		WebElementFacade PaymentTermsTextBox;
		@FindBy(xpath=".//*[contains(@data-display,'New')]")
		WebElementFacade NewBillProfile;

		public void PaymentTerms() throws InterruptedException, IOException, AWTException{
			Common.waitForPageLoad(getDriver());

			if (ProfilesTab.isVisible())
			{
				ProfilesTab.click();
			}
			else
			{
				TabDropDown.selectByVisibleText("Profiles");
			} 
				
			BillingProfilesTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			BillingProfilesTab.click();
			NewBillProfile.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();			
			NewBillProfile.click();
			ClickPaymentTermsElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();		
			ClickPaymentTermsElement.click();
			PaymentTermsTextBox.type("10");
			report.Info("PASSED, Check Payment Terms is Editable");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
		}
		
		@FindBy(xpath=".//*[@id='j_s_vctrl_div_tabScreen']")
		WebElementFacade TabDropDown;
		@FindBy(xpath=".//*[@id='s_3_1_14_0_icon']")
		WebElementFacade AccountAddressesList;
		@FindBy(xpath=".//li[text()='For the attention of']")
		WebElementFacade FortheAttention;
		public void AccountAddressesList() throws InterruptedException, IOException, AWTException{
			Common.waitForPageLoad(getDriver());

		TabDropDown.selectByVisibleText("Addresses");
		AccountAddressesList.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	
		AccountAddressesList.click();
				
		if (FortheAttention.isCurrentlyVisible()){
			report.Info("FAIL, For the attention of Value is present");
		}
		else
		{
			report.Info("PASS, For the attention of Value is not present as expected");
		}
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	}

		  @FindBy(xpath="//*[text()='Account Summary']")
		  WebElementFacade AccountSummaryTab;  
		  @FindBy(xpath=".//*[@title='Customer Comms List:New']")
		  WebElementFacade NewCustomerCommsButton; 
		  @FindBy(xpath=".//table[@summary='Customer Comms List']/tbody/tr[2]/td[2]/a")
		  WebElementFacade NewCustomerCommsID; 
		  @FindBy(xpath=".//*[@title='Contacts:Set / Reset PIN']")
		  WebElementFacade SetResetPINButton; 
		  @FindBy(xpath=".//*[@aria-labelledby='VF_PIN_1_Label']")
		  WebElementFacade PIN1; 
		  @FindBy(xpath=".//*[@aria-labelledby='VF_PIN_2_Label']")
		  WebElementFacade PIN2; 
		  @FindBy(xpath=".//*[@aria-labelledby='VF_PIN_3_Label']")
		  WebElementFacade PIN3; 
		  @FindBy(xpath=".//*[@aria-labelledby='VF_PIN_4_Label']")
		  WebElementFacade PIN4;
		  @FindBy(xpath=".//*[@title='Reset PIN:Ok']")
		  WebElementFacade ResetPINOKButton;
		  @FindBy(xpath=".//*[@title='Contacts:Set/ Reset Word and Hint']")
		  WebElementFacade SetResetWordHintButton; 
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Reset_Word_Label']")
		  WebElementFacade SetResetWordTextBox;
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Reset_Hint_Label']")
		  WebElementFacade SetResetHintTextBox;
		  @FindBy(xpath=".//*[@title='Reset Word:Ok']")
		  WebElementFacade ResetHintTextOKButton;
		  @FindBy(xpath=".//*[@title='Contacts:Set / Reset Password']")
		  WebElementFacade ResetPasswordButton;
	  
		  @Step  
		//This Function Resets PIN/ Memorable Word Hint/ Password in Customer Comms Tab
		  //By Payel
		   public void ResetCustomerComms(String ResetAction) throws IOException, InterruptedException, AWTException{
			  Common.waitForPageLoad(getDriver());
			  

			   String filePath = "\\src\\test\\resources\\data\\Account.xls";
		       String dataSheet = "Reset";		       
		       String applet = "//*[@title='Contacts List Applet']";
		       String table = "//table[@summary='Contacts']";
		       
		       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(ResetAction, filePath, dataSheet);
		       readWorkbook.testData(tableMap);
		       
		       if (tableMap.get("Reset").size()==0){
		           	report.Info(ResetAction+" logical name not found in sheet.");
		           	Assert.assertTrue(false);        	
		           }
		       
		       for (int i = 0;i < tableMap.get("Reset").size();i++) { 	  
		    	   String sReset = tableMap.get("Reset").get(i);
		    	   String sCreateNewCustComms = tableMap.get("CreateNewCustComms").get(i);
		    	   String sPopup = tableMap.get("Popup").get(i);
		    	   String sLocateCol = tableMap.get("LocateCol").get(i);
		    	   String sLocateColValue = tableMap.get("LocateColValue").get(i);	    	   
	    	   
		    		String indexValue = "0";
		    	   
		    	   
		    	   if (sCreateNewCustComms.equals("Y")){
		    		   AccountSummaryTab.click();
		    		   Thread.sleep(2000);
		    		   NewCustomerCommsButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	      			   NewCustomerCommsButton.click();
	          		   String NewCustomerCommID = NewCustomerCommsID.getTextValue();
	          		   report.Info("New Customer Comms was created. ID is : "+NewCustomerCommID);
	          		   NewCustomerCommsID.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	          		 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	          		   NewCustomerCommsID.click();
	          		  Thread.sleep(4000);
					  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	        } 
		    	   
		    	//For PIN RESET   
		    	   if(sReset.equalsIgnoreCase("Pin")){		    		   
		    		   SetResetPINButton.click();
		    		   
		    		   Thread.sleep(4000);
		    		   PIN1.type("4");
		    		   PIN2.type("3");
		    		   PIN3.type("2");
		    		   PIN4.type("2");
		    		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		    		   ResetPINOKButton.click();
		    		   
		    		   Thread.sleep(2000);
		    		 
		    		   Alert alert=getDriver().switchTo().alert();
		    			String ActAlertText=alert.getText();
		    			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		    			report.Info("alert messsage is"+ActAlertText);
		    			
		    			if(ActAlertText.contains("Succes"))
		    			{
		    				report.Info("PIN Resest done successfully Pop up is : "+ActAlertText);
		    			
		    			}else{
		    				report.Info("PIN did not reset. Pop up is : "+ActAlertText);
		    				
		    			} 
			    	     
		    	   } 
		    	   
		    	 //For Memorable Hint Word RESET   
		    	   if(sReset.equalsIgnoreCase("Memorable")){
		    		   Thread.sleep(2000);
		    		   SetResetWordHintButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();		    		  
		    		   SetResetWordHintButton.click();
		    		   Thread.sleep(2000);
		    		   SetResetWordTextBox.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible();		    		   
		    		   SetResetWordTextBox.type("automation43");
		    		   SetResetHintTextBox.type("automation");
		    		   ResetHintTextOKButton.click();
		    		   
		    		   Thread.sleep(2000);
		    		 
		    		   //Alert alert=getDriver().switchTo().alert();
		    			//String ActAlertText=alert.getText();
		    		   common1.HandleErrorPopUp(sPopup);
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
		    			report.Info("alert messsage is"+sPopup);
		    			if(sPopup.contains("Success"))
		    			{
		    				report.Info("Word/Hint Retest done successfully Pop up is : "+sPopup);
		    			
		    			}else{
		    				report.Info("Word/Hint did not reset. Pop up is : "+sPopup);
		    				
		    			} 
			    	     
		    	   } 
		    	   
		    	   //For Reset Password
	               if(sReset.equalsIgnoreCase("Password")){
	            	   ResetPasswordButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	                     
                       ResetPasswordButton.click(); 
	                   Thread.sleep(3000);
	                     
	                   Alert alert=getDriver().switchTo().alert();
	                   String ActAlertText=alert.getText();
	                   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	                   report.Info("alert messsage is"+ActAlertText);
	                        	                        
	                  Assert.assertTrue("Password Reset not done Successfully",ActAlertText.contains("Succes"));
	                        	                     
	               }
	               // for Account permission value
	               if(sReset.equalsIgnoreCase("AccountPermission")){
	    				
	    				if (!sLocateCol.equals("")){
	    		            Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
	    		            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	    		            report.Info(sLocateCol+ "feild is displayed Full Access");
	    		            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                      }
	    			}	    	   
	             }
		       }
		       
		  @FindBy(xpath=".//a[text()='Owned Product/Service']")
          WebElementFacade OwnedProductService;
          @FindBy(xpath=".//*[@aria-label='Third Level View Bar']")
          WebElementFacade AccountSummaryDropdown;
          
          @FindBy(xpath=".//*[contains(text(),'Customer Summary')]")
          WebElementFacade Customersummary;
          @FindBy(xpath=".//*[@id='1_s_3_l_CmplxHierarchy']")
          WebElementFacade CustomerSummaryValueClick;
          @FindBy(xpath=".//*[@id='1_CmplxHierarchy']")
          WebElementFacade CustomerSummaryValueCheck;
         
        //This function is used for navigate to customer summary page and validate the fields under summary page  
          
        @Step
        public void CustomerSummary(String rowname) throws IOException, InterruptedException, AWTException
        {
        	Common.waitForPageLoad(getDriver());
          String filePath = "\\src\\test\\resources\\data\\Account.xls";
            String dataSheet = "CustomerSummary";
            
            Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
            readWorkbook.testData(tableMap);
            
            if (tableMap.get("Validation").size()==0){
	           	report.Info(rowname+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
            
            String applet = "//*[@title='Customer Experience List Applet']";
            String table = "//table[@summary='Customer Experience']";

            for (int i = 0;i < tableMap.get("Validation").size();i++) {
                  String ownedProduct = tableMap.get("ClickOnOwnedProduct").get(i);
                  String sValidation = tableMap.get("Validation").get(i);
                  String sView = tableMap.get("View").get(i);
                  String sLocateCol = tableMap.get("LocateCol").get(i);
                  String sLocateColValue = tableMap.get("LocateColValue").get(i);
                  String UIName = tableMap.get("UIName").get(i);
                  String value = tableMap.get("Value").get(i);
                  String indexValue = tableMap.get("Index").get(i);
                  if (indexValue.equals("")){
                      indexValue = "0";
                }
                 Customersummary.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();                                   
                if(Customersummary.isPresent()){                      
                  Customersummary.click();
                  Thread.sleep(2000);
                }else{
                      AccountSummaryDropdown.selectByVisibleText("Customer Summary");
                      Thread.sleep(6000);
                }
                //AccountSummaryDropdown.selectByVisibleText("Customer Summary");
                report.Info(" Customer Summary is selected successfully from dropdown");
                sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                Thread.sleep(8000);
                
                  if(ownedProduct.equalsIgnoreCase("Yes"))
                  {
                       //validating owened product service button under customer summary
                        Assert.assertTrue("Owned product/service is not present under customer summary",OwnedProductService.isDisplayed());
                        
                     }
                  if(sValidation.equalsIgnoreCase("Y")){
                        //for clicking check box 
                        if(sView.equalsIgnoreCase("Customer Summary")){
                              
                              if (!sLocateCol.equals("")){
                                  common1.selectedRow = -1;
                                  Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));                                    
                                  //report.Info(sLocateCol+" found"); 
                                  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                  }
                              else{
                                  common1.selectedRow = 1;
                              }
                              if (!UIName.equals("")){
                                  common1.updateSiebList(applet,table, UIName, value);  
                                  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                  //report.Info(sLocateCol+" found");                                      
                                  }
                              //for drill down on user account
                           }else if(sView.equalsIgnoreCase("User Account")){
                                        String LocateColMessege = sLocateCol+"Not Found";
                                        if (!sLocateCol.equals("")){
                                          common1.selectedRow = -1;
                                         Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
                                        report.Info(sLocateCol+"Customer summary applet found using Locate Col");
                                        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                        }
                                        else 
                                        {
                                         common1.selectedRow = 1;
                                         report.Info("Customer summary applet found");
                                        }//report.Info(sLocateCol+" found");
                                        if (!UIName.equals("")){                                              
                                        common1.updateSiebList(applet,table, UIName, value);
                                        Thread.sleep(2000);
                                        report.Info("Clicked on User Account sucessfully");
                                        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                        
                                        
                                        }     
                                  }
                     }
            }
        }
        @FindBy(xpath=".//*[@aria-label='Account Permissions:New']")
        WebElementFacade NewAccountPermissionButton;
       
     public void AddVerifyContacts(String row) throws IOException, InterruptedException, AWTException{
      	  String filePath = "\\src\\test\\resources\\data\\Account.xls";
          String dataSheet = "AddVerifyContacts";
          
          String applet = "//*[@title='Account Permissions List Applet']";
          String table = "//table[@summary='Account Permissions']";

          
          Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(row, filePath, dataSheet);
          readWorkbook.testData(tableMap);
          
          if (tableMap.get("RowNo").size()==0){
 	           	report.Info(row+" logical name not found in sheet.");
 	           	Assert.assertTrue(false);        	
 	           }
          
          for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
                
            String sAddContact = tableMap.get("AddContact").get(i);
            String sNewPermission = tableMap.get("NewPermission").get(i);     
            String sContact = tableMap.get("Contact").get(i);
            String sPopup = tableMap.get("PopUp").get(i);         
            String sPopup2 = tableMap.get("PopUp2").get(i); 
            String sPopup3 = tableMap.get("PopUp3").get(i);
            
            String sLocateCol = tableMap.get("LocateCol").get(i);
            String sLocateColValue = tableMap.get("LocateColValue").get(i);             
            String Index = tableMap.get("Index").get(i);
            String UIName = tableMap.get("UIName").get(i);
            String value = tableMap.get("Value").get(i);
            Alert alert;
            String ActAlertText="";
            if(ContactsTab.isDisplayed()){
     		   ContactsTab.click();
                }
                else{
                      AccountDropDown.selectByVisibleText("Contacts");
                }     
            report.Info("Contacts Tab Clicked");
            
            if(sAddContact.equalsIgnoreCase("Y")){
            	AddContact.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();				
 				AddContact.click();			
 				LastnameElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	
 				LastnameElement.click();		
 				LastnameTextBox.waitUntilEnabled();
 				LastnameTextBox.typeAndEnter(sContact);
 				Thread.sleep(2000); 
 				OKButton.click();
 				Thread.sleep(2000);
 				//PopUp 1
 				if(isAlertPresent(getDriver())&& (!sPopup2.equalsIgnoreCase("No"))){
       	         alert=getDriver().switchTo().alert();
       	          ActAlertText=alert.getText(); 
 				  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  				  
       	          String sPopup1="";
       	          if (sPopup2.contains("OPTIONAL:")){
       	               sPopup1= sPopup2.substring(9);
       	           }                                                                                       
       	                                                           
       	        if (ActAlertText.contains(sPopup1)){                            
       	              alert.accept();
       	              System.out.println("alert messsage is"+ActAlertText);
       	            //  report.Info("alert messsage is"+ActAlertText);
       	        }                        
       	        }
       	        else if (!isAlertPresent(getDriver())&& (sPopup2.contains("OPTIONAL:"))){
       	                    report.Info("OPTIONAL Pop did not occur");
       	                    Assert.assertTrue(true);                  
       	        }
       	         else if (!isAlertPresent(getDriver())&& (!sPopup2.equalsIgnoreCase("No"))){
       	                    report.Info("Expected Pop did not occur");
       	                    Assert.assertTrue(false);                                  
       	         }
       	         else if (isAlertPresent(getDriver())&& (sPopup2.equalsIgnoreCase("No"))){
       	                    report.Info("UnExpected Pop did occured");
 							sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
       	                    Assert.assertTrue(false);                                  
       	         } 
 				
 				//PopUp 2
 				if(isAlertPresent(getDriver())&& (!sPopup3.equalsIgnoreCase("No"))){
 	      	         alert=getDriver().switchTo().alert();
 	      	          ActAlertText=alert.getText(); 
 					  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  				  
 	      	          String sPopup1="";
 	      	          if (sPopup3.contains("OPTIONAL:")){
 	      	               sPopup1= sPopup3.substring(9);
 	      	           }                                                                                       
 	      	                                                           
 	      	        if (ActAlertText.contains(sPopup1)){                            
 	      	              alert.accept();
 	      	              report.Info("alert messsage is"+ActAlertText);
 	      	        }                        
 	      	        }
 	      	        else if (!isAlertPresent(getDriver())&& (sPopup3.contains("OPTIONAL:"))){
 	      	                    report.Info("OPTIONAL Pop did not occur");
 	      	                    Assert.assertTrue(true);                  
 	      	        }
 	      	         else if (!isAlertPresent(getDriver())&& (!sPopup3.equalsIgnoreCase("No"))){
 	      	                    report.Info("Expected Pop did not occur");
 	      	                    Assert.assertTrue(false);                                  
 	      	         }
 	      	         else if (isAlertPresent(getDriver())&& (sPopup3.equalsIgnoreCase("No"))){
 	      	                    report.Info("UnExpected Pop did occured");
 								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
 	      	                    Assert.assertTrue(false);                                  
 	      	         } 
            }
            
            if(sNewPermission.equalsIgnoreCase("Y")){
            	NewAccountPermissionButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            	NewAccountPermissionButton.click();
            }
           
            if(sNewPermission.equalsIgnoreCase("ValidateAccountPermissions")){
            	//add code
            }
            
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
               common1.updateSiebList(applet,table, UIName, value); 
               Thread.sleep(2000);
                
               if(value.equalsIgnoreCase("Level 0")){
            	   if(isAlertPresent(getDriver())&& (!sPopup.equalsIgnoreCase("No"))){
            	         alert=getDriver().switchTo().alert();
            	          ActAlertText=alert.getText();  
 					  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
            	          String sPopup1="";
            	          if (sPopup.contains("OPTIONAL:")){
            	               sPopup1= sPopup.substring(9);
            	           }                                                                                       
            	                                                           
            	        if (ActAlertText.contains(sPopup1)){                            
            	              alert.accept();
            	              report.Info("alert messsage is"+ActAlertText);
            	        }                        
            	        }
            	        else if (!isAlertPresent(getDriver())&& (sPopup.contains("OPTIONAL:"))){
            	                    report.Info("OPTIONAL Pop did not occur");
            	                    Assert.assertTrue(true);                  
            	        }
            	         else if (!isAlertPresent(getDriver())&& (!sPopup.equalsIgnoreCase("No"))){
            	                    report.Info("Expected Pop did not occur");
            	                    Assert.assertTrue(false);                                  
            	         }
            	         else if (isAlertPresent(getDriver())&& (sPopup.equalsIgnoreCase("No"))){
            	                    report.Info("UnExpected Pop did occured");
 								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
            	                    Assert.assertTrue(false);                                  
            	         }   
            	  
               }
               report.Info("Row updated");
      	  }
      	  
            report.Info("");   
 		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
           
      }
     }

        @FindBy(xpath = ".//*[@aria-label='Address Line 1']/following-sibling::span")
		WebElementFacade addrPopUp;	  
	  
	  @FindBy(xpath = ".//*[@aria-label='Third Level View Bar']")
		WebElementFacade AccountTabsDropDown;
	  
	  @FindBy(xpath = "//button[@title='Account Addresses:Validate']")
		WebElementFacade addressValidateButton;
	  
	  @FindBy(xpath = ".//*[@title='Address History:Validate']")
		WebElementFacade CreditVettingValidateButton;
	  
	  @FindBy(xpath = ".//*[@title='Address History:New']")
		WebElementFacade AddressHistoryNewButton;
	  
	  @FindBy(xpath = ".//*[@title='Account Addresses:New']")
		WebElementFacade AcountAddressNew;
	  
	  @FindBy(xpath = ".//*[@title='Contacts:Add']")
		WebElementFacade AddContact;
	  
	  @FindBy(xpath = ".//*[contains(@aria-describedby,'Last_Name')]")
		WebElementFacade LastnameElement;
	  
	  @FindBy(xpath = ".//*[@ name='Last_Name']")
		WebElementFacade LastnameTextBox;	  
	  
	  @FindBy(xpath = ".//*[@title='Add Contacts:OK']")
		WebElementFacade OKButton;
	  
	  @FindBy(xpath = ".//*[@ title='Account Addresses:OK']")
		WebElementFacade AddressOKButton;
	  
	  @FindBy(xpath = "//*[text()='Account Addresses']/following-sibling::button[@title='Close']")
		WebElementFacade AddressCloseButton;
	  
	  @FindBy(xpath = ".//*[text()='Addresses']")
		WebElementFacade AddressesTab;
	  
	  @FindBy(xpath = ".//*[@aria-label='Account name']/following::a[text()='Contacts']")
		WebElementFacade ContactsTab;
	  
	  @FindBy(xpath = ".//*[text()='Age and ID Verification']")
		WebElementFacade AgeIDVerificationTab;
	  
	  @FindBy(xpath = ".//*[text()='Credit Vetting']")
		WebElementFacade CreditVettingTab;
	  
	  @FindBy(xpath = "//*[@name='Postal_Code']")
		WebElementFacade PostCodeTextBox;
	  
	  @FindBy(xpath = ".//*[contains(@aria-describedby,'Postal_Code')]")
		WebElementFacade PostCodeElement;
	    
	    
	    
	    @Step 
		  public void AddressEditable(String logic) throws InterruptedException, IOException, AWTException{
			 //Thread.sleep(9000);
	    	Common.waitForPageLoad(getDriver());
				String filePath = "\\src\\test\\resources\\data\\Account.xls";
				String dataSheet = "AddressUpdate";

				Map<String, Map<Integer, String>> tableMap = readWorkbook.readRow(logic, filePath, dataSheet);
				readWorkbook.testData(tableMap);
				
				 if (tableMap.get("Editable").size()==0){
			           	report.Info(logic+" logical name not found in sheet.");
			           	Assert.assertTrue(false);        	
			           }

				for (int i = 0; i < tableMap.get("Editable").size(); i++) {
					String sEditable = tableMap.get("Editable").get(i);
					String sLastName = tableMap.get("LastName").get(i);
					String sPost_Code = tableMap.get("Post_Code").get(i);
					
				if(sEditable.equalsIgnoreCase("Address Line 1")){
					addrPopUp.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();					
					addrPopUp.click();
					Thread.sleep(4000);
					if(!addressValidateButton.isEnabled()){
						report.Info("Validate button is disabled for Anonymous customer at Address Line 1 under Account Address window");
						
					}
					else{
						Assert.assertTrue("Fail-Validate button is enabled for Anonymous customer at Address Line 1 under Account Address window.",addressValidateButton.isEnabled());
						
					}
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
					AddressCloseButton.click();
					Alert alert=getDriver().switchTo().alert();
					String ActAlertText=alert.getText();	
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  					
	    			if(ActAlertText.contains("Click OK to discard unsaved"))
	    			{
	    				getDriver().switchTo().alert().accept();
	    				report.Info("Alert messsage is:"+ActAlertText);
	    			}
				}
				
				if(sEditable.equalsIgnoreCase("Address Line 1 NonAnonymous")){
					addrPopUp.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();					
					addrPopUp.click();
					PostCodeElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
					PostCodeElement.click();
					Thread.sleep(1000);
					report.Info("Postcode is: "+sPost_Code);
					PostCodeTextBox.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilEnabled();				
					PostCodeTextBox.typeAndEnter(sPost_Code);
					Thread.sleep(1000);
					AddressOKButton.click();
					Thread.sleep(3000);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
				}
				
				if(sEditable.equalsIgnoreCase("Addresses")){
					if(AddressesTab.isDisplayed()){
						AddressesTab.click();
						
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Addresses");
					}
					Thread.sleep(60000);
					//addressValidateButton.waitUntilPresent();

					
					if(!addressValidateButton.isEnabled()){
						report.Info("Validate button is disabled for Anonymous customer at Addresses tab.");
					}
					else{
						Assert.assertTrue("Fail - Validate button is enabled for Anonymous customer at Addresses tab.",addressValidateButton.isEnabled());
						
					}
				}
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
				if(sEditable.equalsIgnoreCase("Contacts NonAnonymous")){
					Thread.sleep(6000);
					
					if(ContactsTab.isDisplayed()){
						ContactsTab.click();
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Contacts");
					}
					//Thread.sleep(40000);
				AddContact.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
					
					AddContact.click();
					//Thread.sleep(4000);
					LastnameElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
					
					LastnameElement.click();
					Thread.sleep(1000);
					report.Info("Last Name is: "+sLastName);
					LastnameTextBox.waitUntilEnabled();
					LastnameTextBox.typeAndEnter(sLastName);
					Thread.sleep(3000);
					OKButton.click();
				}
				
				
				if(sEditable.equalsIgnoreCase("Age and Id Verification")){
					
					if(AgeIDVerificationTab.isDisplayed()){
						AgeIDVerificationTab.click();
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Age and ID Verification");
					}
					Thread.sleep(4000);
					addressValidateButton.waitUntilPresent();
					
					if(!addressValidateButton.isEnabled()){
						report.Info("Validate button is disabled for Anonymous customer at Age and ID Verification tab under Account addresses.");
					}
					else{
						Assert.assertTrue("Fail - Validate button is enabled for Anonymous customer at Age and ID Verification tab under Account addresses.",addressValidateButton.isEnabled());
						
					}
				}
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
				if(sEditable.equalsIgnoreCase("Age and Id Verification NonAnonymous")){
					if(AgeIDVerificationTab.isDisplayed()){
						AgeIDVerificationTab.click();
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Age and ID Verification");
					}
					Thread.sleep(4000);
					AcountAddressNew.waitUntilPresent();
					if(AcountAddressNew.isEnabled()){
						report.Info("Pass - New Button is editable for Age and Id Verification NonAnonymous");
					}
					else{
						Assert.assertTrue("Fail - New Button is not editable for Age and Id Verification NonAnonymous",!AcountAddressNew.isEnabled());
						
					}
				}
				
				if(sEditable.equalsIgnoreCase("Credit Vetting")){
					if(CreditVettingTab.isDisplayed()){
						CreditVettingTab.click();
						
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Credit Vetting");
						
					}
					
					Thread.sleep(5000);
					Alert alert=getDriver().switchTo().alert();
	    			String ActAlertText=alert.getText();
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	    			
	    			if(ActAlertText.contains("IF A CREDIT VET IS REQUIRED"))
	    			{
	    				getDriver().switchTo().alert().accept();
	    				report.Info("Alert messsage is:"+ActAlertText);
	    			}
					
					
					Thread.sleep(8000);
					CreditVettingValidateButton.waitUntilPresent();
					Thread.sleep(5000);
					if(!CreditVettingValidateButton.isEnabled()){
						report.Info("Validate button is disabled for Anonymous customer at Credit Vetting  tab under Address History.");
					}
					else{
						
						Assert.assertTrue("Fail - Validate button is enabled for Anonymous customer at Credit Vetting  tab under Address History.",CreditVettingValidateButton.isEnabled());
					}
				}
				
				if(sEditable.equalsIgnoreCase("CreditVetting_NonAnonymous")){
					if(CreditVettingTab.isDisplayed()){
						CreditVettingTab.click();
					}
					else{
						AccountTabsDropDown.selectByVisibleText("Credit Vetting");
					}
					Thread.sleep(5000);
					Alert alert=getDriver().switchTo().alert();
					String ActAlertText=alert.getText();
	    			
	    			if(ActAlertText.contains("IF A CREDIT VET IS REQUIRED"))
	    			{
	    				getDriver().switchTo().alert().accept();
	    				report.Info("Alert messsage is:"+ActAlertText);
	    			}
					Thread.sleep(8000);
					AddressHistoryNewButton.waitUntilPresent();
					Thread.sleep(5000);
					if(AddressHistoryNewButton.isEnabled()){
						report.Info("Pass - New Button is editable for CreditVetting_NonAnonymous");
					}
					else{
						Assert.assertTrue("Fail - New Button is not editable for CreditVetting_NonAnonymous",!AddressHistoryNewButton.isEnabled());
						
					}
				}
				}
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			}
		  
	   
	    @FindBy(xpath="//*[@name='Postal_Code']")
	     WebElementFacade PostCodeText;
	    @FindBy(xpath=".//*[@title='Account Addresses:New']")
		WebElementFacade NewAddressButton;
	    // This function is used to navigate to account summary page and select address then click on new button and verifies the applet is open in query mode.
	    @Step
	    public void gotoAddresss() throws InterruptedException, IOException, AWTException
	    {
	      Thread.sleep(2000);
	      AccountSummaryDropdown.selectByVisibleText("Addresses");
	      Thread.sleep(40000);
	            
	            NewAddressButton.withTimeoutOf(180,TimeUnit.SECONDS).waitUntilClickable();
	            NewAddressButton.click();
	            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	            PostCodeElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	            PostCodeElement.click();
	            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	            Assert.assertTrue("Addresss applet is not opened in query mode", PostCodeText.isEnabled());
	            
	    }
	    
	    @FindBy(xpath=".//*[@aria-label='Verification type']")
		WebElementFacade VerificationTypeTextBox;
		
		@FindBy(xpath=".//*[@aria-label='Application Type']")
		WebElementFacade ApplicationTypeTextBox;
		
		@FindBy(xpath=".//*[@aria-label='Proof of ID type']")
		WebElementFacade IDTypeTextBox;
		
		@FindBy(xpath=".//*[@aria-label='Proof of address type']")
		WebElementFacade AddressTypeTextBox;
		
		@FindBy(xpath=".//*[@data-display='Submit']")
		WebElementFacade AgeIDSubmitButton;
		
		@FindBy(xpath=".//a[Text()='Age and ID Verification']")
		WebElementFacade AgeIDTab;
		
		@FindBy(xpath=".//*[@aria-label='Override verification']")
		WebElementFacade OverrideVerificationTextBox;

//desc : This function is used to click on Age and ID verification tab and Enter details as required.
	
		@FindBy(xpath=".//*[@id='s_2_1_13_0_Ctrl']")
	  	WebElementFacade CustCommsNewButton;
	  	@FindBy(xpath=".//*[@id='1_s_2_l_Id']/a")
	  	WebElementFacade IdDrillDown;
	  	@FindBy(xpath=".//*[@aria-label='Installed ID']/following::span[1]")
	  	WebElementFacade InstalledIdPickList;
	  	//@FindBy(xpath=" .//*[@id='1_s_9_l_Serial_Number']")
	  	//WebElementFacade InstalledIdTextBox;
	  	@FindBy(xpath=".//table[@summary='Pick Asset']/descendant::td[contains(@id,'Serial_Number')]")
	  	WebElementFacade InstalledIdElement;
	  	@FindBy(xpath="//input[contains(@id,'Serial_Number')]")
	  	WebElementFacade InstalledIdTextBox;
	  	@FindBy(xpath=".//span[text()='Pick Asset']/following::button[@title='Pick Asset:Go'][1]")
	  	WebElementFacade GoButton;
	  	@FindBy(xpath=".//*[@title='Pick Asset:OK']")
	  	WebElementFacade PickAssetOK;
	  	@FindBy(xpath=".//*[@aria-label='Category']")
	  	WebElementFacade CategoryTextBox;
	  	@FindBy(xpath=".//*[@aria-label='Sub-category']")
	  	WebElementFacade SubCategoryTextBox;
	  	@FindBy(xpath=".//*[@aria-label='Resolution']")
	  	WebElementFacade ResolutionTextBox;
	  	@FindBy(xpath=".//*[@aria-label='DPA validation']")
	  	WebElementFacade DPAValidationTextBox;
	  	@FindBy(xpath=".//*[@aria-label='Memorable word']/preceding::input[@aria-label='Type']")
	  	WebElementFacade TypeTextBox;
	  	@FindBy(xpath=".//*[@aria-label='Appointment Date']")
	  	WebElementFacade AppointmentDate;
	  	@FindBy(xpath=".//*[@data-handler='today']")
	  	WebElementFacade DateFieldNowButton;
	  	@FindBy(xpath=".//*[@data-handler='hide']")
	  	WebElementFacade DateFieldDoneButton;
	  	@FindBy(xpath=".//*[@aria-label='Appointment Slot']")
	  	WebElementFacade AppointmentSlotTextBox;
	  	@FindBy(xpath=".//*[@title='Customer Comms:Close']")
	  	WebElementFacade CustomerCommsCloseButton;
	  	@FindBy(xpath=".//*[@aria-label='Status']")
	  	WebElementFacade StatusVerify;
	  	@FindBy(xpath=".//*[@id='s_5_1_16_0_icon']")
	  	WebElementFacade OrderNumPickList;
	  	@FindBy(xpath=".//*[@title='Pick Order:OK']")
	  	WebElementFacade PickOrderOkButton;
	  	@FindBy(xpath=".//button[@title='Customer Comms:Send OTAC SMS']")
	  	WebElementFacade SendOTACSMSButton;
	  	@FindBy(xpath="//button[@title='Send OTAC:OK']")
	  	WebElementFacade SendOTACOKButton;
	  	@FindBy(xpath="//tr[@id='1']/td[3]/a[@name='Name']")
	  	WebElementFacade AccountDrillDownCustComms;
	  	@FindBy(xpath="//button[@title='Customer Comms:Check Eligibility']")
	  	WebElementFacade CheckEligibilityButton;
	  	
	  	
	  //Description : This function is used to used to create new customer comms on account summary page
			 @Step
			      	public void CreateCustComms(String CustomerComms ) throws IOException, InterruptedException, AWTException {
				 Common.waitForPageLoad(getDriver());
			      		String filePath = "\\src\\test\\resources\\data\\Account.xls";
			             String dataSheet = "CreateNewCustComms";
			             
			             
			             Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(CustomerComms, filePath, dataSheet);
			             readWorkbook.testData(tableMap);
			             
			             if (tableMap.get("Category").size()==0){
					           	report.Info(CustomerComms+" logical name not found in sheet.");
					           	Assert.assertTrue(false);        	
					           }
			   
			             for (int i = 0;i < tableMap.get("Category").size();i++) {
			             	 String Category = tableMap.get("Category").get(i);
			             	 String Subcategory = tableMap.get("Subcategory").get(i);
			             	 String Resolution = tableMap.get("Resolution").get(i);
			             	 String DPA_validation = tableMap.get("DPA_validation").get(i);
			             	 String Type = tableMap.get("Type").get(i);
			             	 String VerifyStatus = tableMap.get("VerifyStatus").get(i);
			             	 String ClickAccount = tableMap.get("ClickAccount").get(i);
			             	 String CheckEligibility = tableMap.get("CheckEligibility").get(i);
			             	 String DPAValidationInLineItems = tableMap.get("DPAValidationInLineItems").get(i);
			             	 String SendOTACSMS = tableMap.get("SendOTACSMS").get(i);
			             	 String EnabledOTACSMS = tableMap.get("EnabledOTACSMS").get(i);
			             	String sPopup = tableMap.get("Popup").get(i);
			             	
			             	 //String InstalledID = "447464575157"; 
			             	String InstalledID = "";
			             	
			             	try{
			             		InstalledID = Serenity.sessionVariableCalled("ACCNTMSISDN").toString();
			             	}
			             	catch(NullPointerException e){
			             		
			             	}
			             	//String InstalledID = Serenity.sessionVariableCalled("ACCNTMSISDN").toString();//ftch ACCNTMSISDN
			             	 
			             	CustCommsNewButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			             	CustCommsNewButton.click();
			             	Thread.sleep(5000);
			             	 IdDrillDown.click();
			             	 Thread.sleep(3000);
			             	 report.Info("");
			             	 if(DPAValidationInLineItems.equalsIgnoreCase("Passed")){
			             		DPAValidationTextBox.type(DPAValidationInLineItems);
			             	 }
			             	 
			             	 if(!InstalledID.isEmpty()){
			             		InstalledIdPickList.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			             		InstalledIdPickList.click();
				             	Thread.sleep(2000);
				             	//InstalledIdElement.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				             	//InstalledIdElement.click();
				             	InstalledIdTextBox.type(InstalledID);
				             	report.Info("");
				             	GoButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				             	GoButton.click();			             
				             	PickAssetOK.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				             	PickAssetOK.click();
				             	report.Info("");
				             	Thread.sleep(2000);
								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			             	 }
			             	 
			             	 if(!Category.isEmpty()){
			             		Thread.sleep(5000);
			             		CategoryTextBox.type(Category);
			             		Thread.sleep(1000);
				             	SubCategoryTextBox.type(Subcategory);
				             	Thread.sleep(1000);
				             	ResolutionTextBox.type(Resolution); 
				             	Thread.sleep(1000);
				             	if (Category.equals("Account Management")){
	                                
	                                OrderNumPickList.click();
	                                PickOrderOkButton.click();
	                                
				             	}
				             	DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
				             	Date date = new Date();
				             	String date1= dateFormat.format(date);
				             	AppointmentDate.type(date1);
				             	AppointmentSlotTextBox.type("AM");
				             	
				             	if(!InstalledID.isEmpty()){
					             		InstalledIdPickList.click();
						             	Thread.sleep(2000);
						             	InstalledIdElement.click();
						             	InstalledIdTextBox.type(InstalledID);
						             	report.Info("");
						             	GoButton.click();
						             	report.Info("");
						             	PickAssetOK.click();
						             	Thread.sleep(2000);
				             	 	}
				             	 
				             	if(!DPA_validation.isEmpty()){
				             		DPAValidationTextBox.type(DPA_validation);
				             		}
				             	
				             	if(!VerifyStatus.isEmpty()){
					             		if(VerifyStatus.equalsIgnoreCase("Closed")){
					             			CustomerCommsCloseButton.click();
					             			CustomerCommsCloseButton.click();
					             			Thread.sleep(5000);
					             			//DPAValidationTextBox.sendKeys("F5");
					             		}
					             		String actVerify_Status=StatusVerify.getTextValue();
						             	if(actVerify_Status.equalsIgnoreCase(VerifyStatus)){
						             		report.Info("Pass - Customer Comms Status is "+actVerify_Status+" as expected");				              		 
						             	 }
						             	 else 
						             	 {
						             		Assert.assertTrue("Customer Comms Status is not as expected", actVerify_Status.equalsIgnoreCase(VerifyStatus)); 			          
						             	 }
						             	
				             	}
								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
				             	
				             	if(EnabledOTACSMS.equalsIgnoreCase("Y")){
				             			if(SendOTACSMSButton.isEnabled()){
				             				report.Info("Send OTAC SMS button is enabled when status is In-progress");
				             			}
				             			else{
				             				Assert.assertTrue("Send OTAC SMS button is not enabled when status is In-progress", SendOTACSMSButton.isEnabled());
				             			}
				             			
				             	}
				             	
				             	if(SendOTACSMS.equalsIgnoreCase("Y")){
	                                SendOTACSMSButton.click();
	                                Thread.sleep(10000);
	                                SendOTACOKButton.click();
	                                Thread.sleep(10000);
	                                Alert alert=getDriver().switchTo().alert();
	                                  String ActAlertText=alert.getText();                           
	                                        System.out.println("Alert messsage is:"+ActAlertText);
	                                        Assert.assertTrue(" FAIL, PopUp Did not Occur", ActAlertText.contains(sPopup));
	                                        if(ActAlertText.contains(sPopup))
	                                        {
	                                              alert.accept();                                        
	                                        }  
	                                AccountDrillDownCustComms.click();
	                                report.Info("");
									sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	                                Thread.sleep(10000);
	                          }

			             	 }
			             	 
			             	 
			             	 if(CheckEligibility.equalsIgnoreCase("Y")){
			             		CheckEligibilityButton.click();
			             	 }
			             	 
			             	 if(ClickAccount.equalsIgnoreCase("Y")){
			             		AccountDrillDownCustComms.click();
			             		Thread.sleep(3000);
			             	 }
			          		             	 
			             	 Thread.sleep(4000);		             	      	 
			             	 
			             }
						 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			             
			      	}



			 @FindBy(xpath=".//td[contains(@id,'Payment_Method')]")
		  WebElementFacade PaymentMethod;	
			 @FindBy(xpath=".//*[@title='Postpaid']")
			  WebElementFacade PostpaySelect;
		  @FindBy(xpath=".//*[@name='Payment_Method']")
		  WebElementFacade PaymentMethodDropDown;
		  @FindBy(xpath=".//*[@title='Automatic Debit:Update Bank Details']")
		  WebElementFacade UpdateBankDetailsButton;
	     @FindBy(xpath=".//*[@aria-labelledby='VF_Bank_Name_Label']")
		  WebElementFacade BankNameinPopUp;
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Bank_Branch_Label']")
		  WebElementFacade BankBranchinPopUp;
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Bank_Sort_Code_Label']")
		  WebElementFacade BanksortCodeinPopUp;
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Bank_Account_Name_Label']")
		  WebElementFacade BankAccountNameinPopUp;
		  @FindBy(xpath=".//*[@aria-labelledby='VF_Bank_Account_Number_Label']")
		  WebElementFacade BankAccNumberinPopUp;
	      @FindBy(xpath=".//*[@aria-label='Siebel:OK']")
		  WebElementFacade OKButtoninPopUp;
	      @FindBy(xpath=".//span[text()='Direct Debit']")
		  WebElementFacade DirectDebitImage;
	      @FindBy(xpath=".//a[text()='Billing profile']")
		  WebElementFacade ProfilesBillingProfileTab;
	      @FindBy(xpath=".//*[@aria-label='Automatic Debit:Validate Bank Details']")
		  WebElementFacade ValidateBankDetails;
	      	  
  
		  
		  @Step   
		
		  public void DirectDebit(String DirectDebitAction) throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{
			   
			   String filePath = "\\src\\test\\resources\\data\\Account.xls";
		       String dataSheet = "DirectDebit";
		       
		       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(DirectDebitAction, filePath, dataSheet);
		       readWorkbook.testData(tableMap);
		        
		       for (int i = 0;i < tableMap.get("PaymentMethod").size();i++) { 	  
		    	   String sPaymentMethod = tableMap.get("PaymentMethod").get(i);
		    	   String sSortCode = tableMap.get("SortCode").get(i);
		    	   String sAccountNumber = tableMap.get("AccountNumber").get(i);
		    	   String sAccountName = tableMap.get("AccountName").get(i);
		    	   String sBranch = tableMap.get("Branch").get(i);
		    	   String sBankName = tableMap.get("BankName").get(i);
		    	   String sDBRefresh = tableMap.get("DBRefresh").get(i);
		    	   
		    	   
		    	   ProfilesTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		  			if (ProfilesTab.isCurrentlyVisible()){
		  				ProfilesTab.click();
		  			}
		  			else {
		  				AccountTabsDRopDown.selectByVisibleText("Profiles");
		  			}
		  		
		  			Thread.sleep(2000);
		  			
		  			if (ProfilesBillingProfileTab.isCurrentlyVisible()){
		  				ProfilesBillingProfileTab.click();
		  			}
		  			Thread.sleep(3000);
		  			PaymentMethod.click();
		  			PaymentMethodDropDown.type(sPaymentMethod);
		  			Thread.sleep(1000);
		  			PostpaySelect.click();
		  			Thread.sleep(2000);
			       UpdateBankDetailsButton.click();
			       Thread.sleep(2000);
		          BankNameinPopUp.type(sBankName);
    			  BankBranchinPopUp.type(sBranch);
    			  BanksortCodeinPopUp.type(sSortCode);
    			  BankAccountNameinPopUp.type(sAccountName);
    			  BankAccNumberinPopUp.type(sAccountNumber);
    			  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
    			  OKButtoninPopUp.click();
    			  /*ValidateBankDetails.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
    			  								  
    			   ValidateBankDetails.click();*/
    			  	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
    			   Thread.sleep(3000);
    			   report.Info("PASS : BANK Details Populated");
    			  
    			  if (!sDBRefresh.equals("")){
    				  Database.ExecuteDBQuery("DDPayTypecheck");
    				  String PAYTYPE = Serenity.sessionVariableCalled("PAY_TYPE0").toString();
    				  String PAYTYPE0 = null;
    				  if (PAYTYPE.equals("10005")) {
    					  return;
    				  }
    				  else if (!PAYTYPE.equals("10005")) {
 		    			 for (int x = 0;x < 10;x++) {
 		    				 Thread.sleep(10000);
 		    				  Database.ExecuteDBQuery("DDPayTypecheck");
 		    				 PAYTYPE0 = Serenity.sessionVariableCalled("PAY_TYPE0").toString();
 		    			  	  if (PAYTYPE0.equals("10005")){
 		    		    			 break;
 		    		    		 }
 		    			  	  }
 		    			 if (!PAYTYPE0.equals("10005")){
 		    				report.Info("Paytype is not 10005");
 							Assert.assertTrue(false);
    		    		 } 
 		    		 }
    				  

    			  }
		  			
		 
		       }								  				
		   }
		  
		  @FindBy(xpath=".//*[@aria-labelledby='s_6_l_Status s_6_l_altCombo']")
		  WebElementFacade BillingProfileStatus;
		  @FindBy(xpath=".//table[@summary='Billing profile']/tbody/tr")
		  WebElementFacade BillingProfileTable;
		  /*@FindBy(xpath=".//div[@title='Billing profile List Applet']")
		  WebElementFacade BillingProfileApplet;*/
		  
		  @FindBy(xpath=".//*[@aria-label='Row #']")
	       	WebElementFacade rowId;
	       	
	       	//@FindBy(xpath=".//*[@id='s_5_1_274_0_Ctrl']")
	       	@FindBy(xpath=".//*[@aria-label='About Record:OK']")
	       	WebElementFacade aboutRecordOkButton;
	       	
	       	@FindBy(xpath="//span/ul/li[11]/a")
	       	WebElementFacade aboutRecord;
	       	@FindBy(xpath=".//button[@title='Billing profile Menu']")
	       	WebElementFacade BillingPofilemenuItemIcon;
	       	@FindBy(xpath="(//tr[@id='1']//td[contains(@id,'Status')])[1]")
	       	WebElementFacade BillingPofilestatus;
	       	@FindBy(xpath="//span/ul/li[9]/a")
			WebElementFacade RefreshMenuItem;
	       	@FindBy(xpath=".//*[text()='Billing profile']")
	        WebElementFacade BillingProfiletab;
	       	
			  
			  @Step 
			  //This Function changes payment method in Profiles Tab
			  //By Payel
			  public void ChangePaymentMethod(String logicalName) throws IOException, InterruptedException, AWTException{
				  Common.waitForPageLoad(getDriver());
	              Thread.sleep(5000);
	              String filePath = "\\src\\test\\resources\\data\\Account.xls";
	            String dataSheet = "ChangePaymentMethod";
	            String table = "//table[@summary='Billing profile']";             
	            String applet = "//*[@title='Billing profile List Applet']";            
	          
	            Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(logicalName, filePath, dataSheet);
	            readWorkbook.testData(tableMap);
	            
	            
	            for (int i = 0;i < tableMap.get("Popup").size();i++) {       
	              String sNewPaymentMethod = tableMap.get("NewPaymentMethod").get(i);
	              String sOldPaymentMethod = tableMap.get("OldPaymentMethod").get(i);
	              String sAboutRecord = tableMap.get("AboutRecord").get(i);
	              String sPopup = tableMap.get("Popup").get(i);
	              String sLocateCol = tableMap.get("LocateCol").get(i);
	              String sLocateColValue = tableMap.get("LocateColValue").get(i);
	              String sUIName = tableMap.get("UIName").get(i);
	              String sValue = tableMap.get("Value").get(i);
	              String indexValue = tableMap.get("Index").get(i);
	              String sBillingStatus = tableMap.get("BillingStatus").get(i);
	                         if(indexValue.equals("")){
	                        	 indexValue="0";
	                         }
	              
	              
	                 if (ProfilesTab.isDisplayed()){
	                       ProfilesTab.click();
	                       Thread.sleep(10000);
	                 }
	                 else {
	                       AccountTabsDRopDown.selectByVisibleText("Profiles");
	                 }
	              
	                 /*if (!sLocateCol.equals("")){
	                	 common1.selectedRow = -1;
	 		            Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
	 		            report.Info(sLocateCol+"Billing profile applet found using Locate Col");
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	                 }
	                 else 
	                 {
	                  common1.selectedRow = 1;
	                  report.Info("Billing profle applet found");
	                 }
	                */
	                 
	                 BillingProfiletab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();                                   
	                 if(BillingProfiletab.isPresent()){                      
	                 	BillingProfiletab.click();
	                   Thread.sleep(2000);
	                 }else{
	                 	report.Info("Billing profile applet not found");
	                 }
	                 	
	                 if(sAboutRecord.equalsIgnoreCase("Yes")){
	             	    	BillingPofilemenuItemIcon.click();
	             	    	aboutRecord.click();
	             	    	report.Info("About Record displayed");
	             	
	             			String sRowId = rowId.getTextValue();
	             			report.Info("Row Id - "+sRowId);
	             			Serenity.setSessionVariable("RowId").to(sRowId);
	             			Thread.sleep(5000);
	             			aboutRecordOkButton.click();
	             			System.out.println(sRowId+" Row id");
	             			//return sRowId;
	             		    
	             		
	             	    }
	 
	             	  //checking billing status
	             	    if(sBillingStatus.equalsIgnoreCase("Yes")){
	             	    	String Status=BillingPofilestatus.getTextValue();
	             	    	i = 0;
	             	    	do{
	             	    		Thread.sleep(7000);
	             	    		BillingPofilemenuItemIcon.click();
	             	    		RefreshMenuItem.click();
	             	    		Status = BillingPofilestatus.getTextValue();
	              				if(Status.equals("Inactive")){
	              					report.Info(" Billing Status is "+Status+ "");
	              					break;              					
	              				}
	             	    		
	             	    		i++;
	             	    	}while(!Status.equals("Inactive") && i <=40);
	             	    }
	             	    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	          
	             	   if (!sLocateCol.equals("")){
		                	 common1.selectedRow = -1;
		 		            Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
		 		            report.Info(sLocateCol+"Billing profile applet found using Locate Col");
							sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
		                 }
		                 else 
		                 {
		                  common1.selectedRow = 1;
		                  report.Info("Billing profle applet found");
	
	             	    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
		                 }
	                             int tablesize= findAll(By.xpath("//table[@summary='Billing profile']/tbody/tr")).size();
	                         report.Info("the number of rows:"+tablesize);
	                             Assert.assertTrue("FAIL : No Rows in Table ", tablesize>1 );
	                         //report
	             for (int j =1;j <tablesize; j++ ){
	                   if ((!sNewPaymentMethod.equals("")) & (!sOldPaymentMethod.equals(""))){
	                         String PM = PaymentMethod.getTextValue();
	                         if (PM.equals(sOldPaymentMethod)){
	                                   PaymentMethod.click();
	                                      PaymentMethodDropDown.typeAndEnter(sNewPaymentMethod);
	                                   BillingProfileStatus.click();
	                                   String PMnew = PaymentMethod.getTextValue();                              
	                                   Assert.assertTrue("FAIL : Payment Method is not changed to "+sNewPaymentMethod, PMnew.equals(sNewPaymentMethod)); 
	                                // report.Info("Payment Method Changed from "+sOldPaymentMethod+" to "+sNewPaymentMethod); 
	                             }
	                   }
	                        
	             }
	             
	            
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	              

	            }
	            }
		  
		  	@FindBy(xpath=".//*[@aria-label='Selection Field']")
			WebElementFacade AgeVerifiedPopUp;
			
			@FindBy(xpath=".//*[@aria-label='Pick Contact:OK']")
			WebElementFacade PickContactOKButton;
			
			@FindBy(xpath=".//*[@aria-labelledby='VF_Outcome_Label']")
			WebElementFacade Outcome;
		  
		  public void AgeIDVerification(String sLogicalName) throws IOException, InterruptedException, AWTException
	    	{
	    		String filePath = "\\src\\test\\resources\\data\\Account.xls";
	            String dataSheet = "AgeIDVerification";
	            
	            Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(sLogicalName, filePath, dataSheet);
	            readWorkbook.testData(tableMap);
	            
	            if (tableMap.get("VerificationType").size()==0){
		           	report.Info(sLogicalName+" logical name not found in sheet.");
		           	Assert.assertTrue(false);        	
		           }
	            
	            for (int i = 0;i < tableMap.get("VerificationType").size();i++) {
	            	   String sVerificationType = tableMap.get("VerificationType").get(i);
	                   String sApplicationType = tableMap.get("ApplicationType").get(i);
	                   String sOverrideVerification = tableMap.get("OverrideVerification").get(i);
	                   String sOutcome = tableMap.get("Outcome").get(i);
	                   String sAddressType = tableMap.get("AddressType").get(i);
	                   String sIDType = tableMap.get("IDType").get(i);
	                   String sAgeVerified = tableMap.get("AgeVerified").get(i);
	                   String sPopup = tableMap.get("Popup").get(i);
	                   report.Info("sAddressType is"+sAddressType);
	                   Thread.sleep(2000);
	            	   if (AgeIDTab.isPresent())
	            	   {
	            		   AgeIDTab.click();
	            		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	            	   }
	            	   else
	            	   {   
	            		   TabDropDown.selectByVisibleText("Age and ID Verification");
	            	   }
	                	   Thread.sleep(2000);
	                   if (!sVerificationType.equals(""))
	                   {
	                	   VerificationTypeTextBox.type(sVerificationType);
	                   }
	                   if (!sApplicationType.equals(""))
	                   {
	                	   ApplicationTypeTextBox.type(sApplicationType);
	                   }
	                   if (!sIDType.equals(""))
	                   {
	                	   IDTypeTextBox.type(sIDType);
	                   }
	                   if (!sAddressType.equals(""))
	                   {
	                	   AddressTypeTextBox.type(sAddressType);
	                   }
	                   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	                   AgeIDSubmitButton.click();   
	                   Thread.sleep(5000);
	               	   if(!sOverrideVerification.equals(""))
	               	   {
	               		OverrideVerificationTextBox.click();
	               	   }
	               	   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	               	   if (!sPopup.equals("No"))
	               	   {
		     				  Alert alert=getDriver().switchTo().alert();
		     				  String ActAlertText=alert.getText();	     				
		       				  report.Info("Alert messsage is:"+ActAlertText);
		       				  Assert.assertTrue(" FAIL, PopUp Did not Occur", ActAlertText.contains(sPopup));
		       				  if(ActAlertText.contains(sPopup))
		       				  {
		       					  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		       					  alert.accept();       					   
		       				  }  
	                	}
	               	   if (sAgeVerified.equals("Yes"))
	               	   {
	               		AgeVerifiedPopUp.click();
	               		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	               		Thread.sleep(5000);
	               		Assert.assertTrue("FAIL, Open Contact Applet Did not occur", PickContactOKButton.isVisible());
	               		PickContactOKButton.click();
	               		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	            	   }
	               	   if(!sOutcome.equals(""))
	               	   {
	               		String sOutcomeActual = Outcome.getTextValue();
	               		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	               		Assert.assertTrue("FAIL, Outcome did not matched with expected",sOutcomeActual.equals(sOutcome));
	               	   }   
	                   }
	            }
		  
		  @FindBy(xpath=".//*[@name='Name']")
			WebElementFacade ProfileTabNameColumn;
			
			@FindBy(xpath=".//*[@aria-label='Bills:Email Copy Bill']")
			WebElementFacade EmailCopyBillButton;
		
			@FindBy(xpath=".//*[@aria-label='Select Email Address:Send Email']")
			WebElementFacade SendEmailButton;
			
	
		public void BillsEmail(String sLogicalName) throws IOException, InterruptedException, AWTException
		{
  		String filePath = "\\src\\test\\resources\\data\\Account.xls";
          String dataSheet = "BillsEmail";
          
          Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(sLogicalName, filePath, dataSheet);
          readWorkbook.testData(tableMap);
          
          if (tableMap.get("Action").size()==0){
	           	report.Info(sLogicalName+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
          for (int i = 0;i < tableMap.get("Action").size();i++) {
          		//String sLogicalName = tableMap.get("LogicalName").get(i);
          	   String sAction = tableMap.get("Action").get(i);
                 String sPopup = tableMap.get("Popup").get(i);
                 String sEmail = tableMap.get("Email").get(i);
                 String sEmail1 = tableMap.get("Email1").get(i);
                 String sEmail2 = tableMap.get("Email2").get(i);
                 Thread.sleep(20000);
                 if (ProfilesTab.isPresent())
                 {
              	   ProfilesTab.click();
              	   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                 }
                 else
                 {
              	   TabDropDown.selectByVisibleText("Profiles");
                 }
                 Thread.sleep(2000);
                 BillingProfilesTab.click();
                 Thread.sleep(2000);
                 ProfileTabNameColumn.click();
                 Thread.sleep(5000);
                 EmailCopyBillButton.click();
                 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                 Thread.sleep(3000);
                 SendEmailButton.click();
             	   if (!sPopup.equals(""))
             	   {
	     				  Alert alert=getDriver().switchTo().alert();
	     				  String ActAlertText=alert.getText();	     				
	       				  Assert.assertTrue(" FAIL, PopUp Did not Occur", ActAlertText.contains(sPopup));
	       				  if(ActAlertText.contains(sPopup))
	       				  {
	       					  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	       					  alert.accept();       					   
	       				  }  
	       				  report.Info("Alert messsage is:"+ActAlertText);
              	}

		}  
		}
	   	@FindBy(xpath=".//*[@id='s_at_m_2']")
    	WebElementFacade Button;
    	@FindBy(xpath=".//span/ul/li[8]/a")
    	WebElementFacade NewQuery;
    	//@FindBy(xpath=".//*[@title='Accounts']")
    	@FindBy(xpath=".//*[@data-tabindex='tabScreen1']")
    	WebElementFacade AccountsTab;
    	
    	public void AboutRecord() throws InterruptedException{
    		
    		Thread.sleep(3000);
    		AccountsTab.click();
    		Thread.sleep(3000);
    		Button.click();
    		NewQuery.click();
    		report.Info("Yes");
    		Thread.sleep(30000);

    	}
		

public void captureComments_CustComms(String rowname) throws IOException, InterruptedException, AWTException
{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "CustCommsComments";
		String applet = "//*[@title='Customer Comms List List Applet']";
		String table = "//table[@summary='Customer Comms List']";
		
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		
		 if (tableMap.get("Message").size()==0){
	           	report.Info(rowname+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
		
		for (int i = 0;i < tableMap.get("Message").size();i++) {
		      String sLocateCol = tableMap.get("LocateCol").get(i);
		     String sLocateColValue = tableMap.get("LocateColValue").get(i);
		     String UIName  = tableMap.get("UIName").get(i);
		     String value  = tableMap.get("Value").get(i);
		     String indexValue = "0";
		     
		     AccountSummaryTab.waitUntilPresent();
		     if (AccountSummaryTab.isDisplayed()){
		    	 AccountSummaryTab.click();
				}
				else {
					AccountSummaryTab.selectByVisibleText("Account Summary");
				} 
		     
		       Thread.sleep(4000);
		       // validate the comments under customer comms
		       
		       String LocateColMessege = sLocateCol+"Not Found";
		       if (!sLocateCol.equals("")){
		         common1.selectedRow = -1;
		        Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
		       report.Info(sLocateCol+"Customer summary applet found using Locate Col");
		       sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		       }
		       else 
		       {
		        common1.selectedRow = 1;
		        report.Info("Customer summary applet found");
		        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		       }
		       
		  }
		}

@FindBy(xpath=".//*[contains(@aria-label,'DD Transaction Type')]")
WebElementFacade DDTransactionType;
@Step
public void Profiles_DirectDebit() throws InterruptedException, IOException, AWTException{
	Thread.sleep(2000);
	if(ProfilesTab.isPresent()){
		ProfilesTab.click();
	     }else{
	       AccountSummaryDropdown.selectByVisibleText("Profiles");
	     }
	Thread.sleep(3000);
	BillingProfilesTab.click();
	DDTransactionType.waitUntilPresent();
	String sDDtransacionType=DDTransactionType.getTextValue();
	//report.Info(sDDtransacionType);
	report.Info(sDDtransacionType);
	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	Assert.assertTrue("DD Transaction Type value is not changed to 0C after changing payment type fron DD to Bill Me",sDDtransacionType.contains("0C"));
	report.Info("DD Transaction Type value is changed to "+ sDDtransacionType +" after changing payment type fron DD to Bill Me.");
	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
  }
 @FindBy(xpath=".//*[text()='Used Product/Service']")
  WebElementFacade UsedProductService ;
 @FindBy(xpath=".//*[text()='Billed Product/Service']")
  WebElementFacade BilledProductServices;
 @FindBy(xpath="//table[@id=s_2_l]/tbody/tr/th")
  List<WebElementFacade> Colcount;
 
 @Step
 public void VerifyCustomerSummary(String rowname) throws IOException, InterruptedException, AWTException{
	 Common.waitForPageLoad(getDriver());
     String filePath = "\\src\\test\\resources\\data\\Account.xls";
     String dataSheet = "VerifyCustomerSummary";
     String applet = "";
     String table = "";
     
     
     Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
     readWorkbook.testData(tableMap);
     
     if (tableMap.get("HistoryCheck").size()==0){
        	report.Info(rowname+" logical name not found in sheet.");
        	Assert.assertTrue(false);        	
        }
     for (int i = 0;i < tableMap.get("HistoryCheck").size();i++) {
    	     String sLocateCol = tableMap.get("LocateCol").get(i);
             String sLocateColValue = tableMap.get("LocateColValue").get(i);
             String UIName  = tableMap.get("UIName").get(i);
             String value  = tableMap.get("Value").get(i);
             String sGoToCustomerSummary = tableMap.get("GoToCustomerSummary").get(i);
             String sOwnedProdService = tableMap.get("OwnedProdService").get(i);
             String sUsedProdService = tableMap.get("UsedProdService").get(i);
             String sBilledProdService = tableMap.get("BilledProdService").get(i);
             String sHistoryCheck = tableMap.get("HistoryCheck").get(i);
               
             String indexValue = "0";
  
             if(sHistoryCheck.equalsIgnoreCase("Y"))
		     {
		      applet = "//*[@title='History List Applet']";
		      table = "//table[@summary='History']";
		     }else{
		    	  applet = "//*[@title='Customer Experience List Applet']";
			      table = "//table[@summary='Customer Experience']";
		     }
           //Navigate to Customer summary page  
            if(sGoToCustomerSummary.equalsIgnoreCase("Y"))
              {
            	Thread.sleep(3000);
                  if(Customersummary.isPresent()){
                  	
                  	Customersummary.click();
                  	Thread.sleep(2000);
                  }else{
                  	AccountSummaryDropdown.selectByVisibleText("Customer Summary");
                       }
                  report.Info(" Customer Summary is selected successfully");
                  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                  Thread.sleep(5000);
              }
           // click on Owned product Service
            if(sOwnedProdService.equalsIgnoreCase("Y"))
            {
            	OwnedProductService.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            	OwnedProductService.click();
            	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
            	
            }
          //click on usedProduc service tab 
            if(sUsedProdService.equalsIgnoreCase("Y"))
                {
            	UsedProductService.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            	UsedProductService.click();
            	sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
            	
                }
         //click on Billed product service 
            if(sBilledProdService.equalsIgnoreCase("Y"))
                {
            	BilledProductServices.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            	 BilledProductServices.click();
            	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
            	 
                 }
          //After navigate to particular tab perform the drill down on the particular fields
            
            	String LocateColMessege = sLocateCol+"Not Found";
               if (!sLocateCol.equals("")){
                 common1.selectedRow = -1;
                Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
               report.Info(sLocateCol+"Customer summary applet found using Locate Col");
               sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
               }
               else 
               {
                common1.selectedRow = 1;
                report.Info("Customer summary applet found");
               }//report.Info(sLocateCol+" found");
               if (!UIName.equals("")){                                              
               common1.updateSiebList(applet,table, UIName, value);
               Thread.sleep(10000);
               report.Info("Dilled Down on "+UIName+" successfully");
               sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
               
               }     

                 Thread.sleep(5000);
              
            
    }
     
 }
 /*@FindBy(xpath=".//*[@aria-label='Installed Assets:Modify']//following::*[@aria-label='Installed Assets:Fast Orders']")
 WebElementFacade FastOrdersAfterModifyButton;    
 @FindBy(xpath=".//*[@aria-label='Installed Assets:Fast Orders']")
 WebElementFacade FastOrdersButton; 
 @FindBy(xpath=".//*[@id='1_Product_Name']")
 WebElementFacade ProductName; */
 @FindBy(xpath=".//*[@aria-label='Owned Product/Service:Menu']")
 WebElementFacade OwnedProductMenuButton; 
 @FindBy(xpath="//a[contains(text(),'Reconnection')]")
 WebElementFacade ReconnectionMenuItem;




//Performs actions on Menu and Button in OwnedProduct&Services Applet
 
 @Step      
  public void OwnedProductServices(String action) throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{
        
       // Thread.sleep(5000);
	 Common.waitForPageLoad(getDriver());
        String filePath = "\\src\\test\\resources\\data\\Account.xls";
      String dataSheet = "UsedProductServices";
      String applet = "//*[@title='Owned Product/Service List Applet']";
      String table = "//table[@summary='Owned Product/Service']";

      
      Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(action, filePath, dataSheet);
      readWorkbook.testData(tableMap);
      
      
      for (int i = 0;i < tableMap.get("LocateCol").size();i++) {   
            String Menu = "";
        String sLocateCol = tableMap.get("LocateCol").get(i);
        String sLocateColValue = tableMap.get("LocateColValue").get(i);
        if (sLocateColValue.contains("Promotion")){
  		   sLocateColValue = sLocateColValue.replace("Promotion", Serenity.sessionVariableCalled("ProductName").toString());
  	   }
 	   if (sLocateColValue.equals("InstalledId")){
 		   sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN").toString();
 	   }         
 	   
 	  if (sLocateColValue.equals("InstalledIdChildAcc")){
		   sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
 		 
	   }
 	  if (sLocateColValue.equals("InstalledIdChild")){
		   sLocateColValue = Serenity.sessionVariableCalled("MSISDN").toString();
	   }
 	   
        String sIndex = tableMap.get("Index").get(i);
        String sAction = tableMap.get("Action").get(i);
        //String sEnableAction = tableMap.get("EnableAction").get(i);
        String sEnable = tableMap.get("Enable").get(i);
        String sUIName = tableMap.get("UIName").get(i);
        String sValue = tableMap.get("Value").get(i);
        //String sPopUp = tableMap.get("PopUp").get(i);
        if (AccountSummaryTab.isDisplayed()){
              AccountSummaryTab.click();
             // Thread.sleep(5000);
              Common.waitForPageLoad(getDriver());
                       }
                       else {
                             AccountTabsDRopDown.selectByVisibleText("Account Summary");
                             //Thread.sleep(5000);
                             Common.waitForPageLoad(getDriver());
                       } 
        int k=0;
	    if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")){
	   	    do{
	   	    	
	   	    	getDriver().navigate().refresh();
		    	Thread.sleep(2000);	   	    	
	   	    	k++;
	   	    	}while(common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true") && (k<10));
	    }
	    
        if (sIndex.equals("")){
              sIndex = "0";
           }
       
           Thread.sleep(3000);
                  
         String LocateColMessege = sLocateCol+"Not Found";             

         if (!sLocateCol.equals("")){
              common1.selectedRow = -1;
              Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
              report.Info(sLocateCol+" found");                    
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
              }
         else{
               common1.selectedRow = 1;
         }
      
        if (!sUIName.equals("")){
             /** if (sValue.equals("ID")){
                    sValue =  Trim(Replace(sValue,"ID",DictionaryTest_G.Item("AgreementId")))
              }*/
           common1.updateSiebList(applet,table, sUIName, sValue);
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  		   
        }
                 
                       //Check Reconnection is enable or disable
                 if (sEnable.equals("Yes")&& sValue.equals("Reconnection") ){
                             
                       OwnedProductMenuButton.click();
                       Thread.sleep(2000);
                       Assert.assertTrue("FAIL : Reconnection is not enabled",ReconnectionMenuItem.isEnabled());
                     report.Info("PASS : Reconnection is enabled");
                     break;
                 }
                 
               //Check Reconnection is enable or disable               
                 if (sEnable.equals("No")&& sValue.equals("Reconnection") ){
                             
                       OwnedProductMenuButton.click(); 
                       Thread.sleep(2000);
                       Assert.assertFalse("FAIL : Reconnection is not disabled",ReconnectionMenuItem.isEnabled());
                     report.Info("PASS : Reconnection is disabled");
                     break;
                     
                 } 
  
                 
                 //Performs the action   
                 if (!sAction.equals("")){
                       if ((sAction.equals("Modify"))|| (sAction.equals("Modify Promo"))){
            //This will click on Modify or Modify Promo
                             String Button = ".//*[@title='Installed Assets:"+sAction+"']" ;                               
                             getDriver().findElement(By.xpath(Button)).click();
                             System.out.println("INFO : Clicked on Menu "+sAction);
                             Thread.sleep(3000);
                              
                       }     
                       else{
                             Thread.sleep(3000);
                             OwnedProductMenuButton.click();
                             //String Menu = "//a[text()='"+sAction+"']" ;      
                              Menu = "//a[contains(text(),'"+sAction+"')]" ;
                             //
                             getDriver().findElement(By.xpath(Menu)).click();
                             System.out.println("INFO : Clicked on Menu "+sAction);
                             Thread.sleep(3000);
                             
                                                          
                       }   
                       sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());	
                       
                       if(isAlertPresent(getDriver())){
							Alert alert = getDriver().switchTo().alert();
							String ActAlertText = alert.getText(); 
															  				  						
							if (ActAlertText.contains("FRAUD RISK")){
								alert.accept();
								System.out.println("alert messsage is"+ActAlertText);
							}                      
						}
                       
                       //Handles Recconection's PopUp after performing Reconnection
                       if (sValue.equals("Reconnection") && sEnable.equals("")){
                     
                             //Thread.sleep(5000);
                    	   Common.waitForPageLoad(getDriver());
                             Alert alert=getDriver().switchTo().alert();
                             String ActAlertText=alert.getText();
                             System.out.println("alert messsage is"+ActAlertText);
                             Assert.assertTrue("Expected pop is for 90days violation pop is not present", (ActAlertText.contains("The MSISDN you are trying")));
                     
                                         report.Info("");       
                                         break;
                         }                                                                                
                 }
                 
            

                 if ((sAction.equals("Disconnect"))||(sAction.equals("Post to Pre Migration"))||(sAction.equals("Pre to Post Migration"))){
 	   				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 	   				
 	   			 if (DisconnectPopupMsg.isVisible()){
 	   	            	DisconnectPopupMsg.click();
 	   	            	//Thread.sleep(3000);
 	   	            	report.Info("Disconnection Pop button Clicked");
 	   	            	Common.waitForElement(AcceptButton);
 	   	               }
 	   				
 	   				if (AcceptButton.isCurrentlyVisible()){
                        AcceptButton.click();
                          report.Info("Verify Message Accept button Clicked");
                        
                        }
                
                
      }  
sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());	
}							  	  
  }
 
 
public void CustCommsMsgVerify(String VerifyMessage) throws IOException, AWTException, InterruptedException {
     
     String filePath = "\\src\\test\\resources\\data\\Account.xls";
     String dataSheet = "CustCommsMsgVerify";
     String applet = "//*[@title='Customer Comms List List Applet']";
     String table = "//table[@summary='Customer Comms List']";

     Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(VerifyMessage, filePath, dataSheet);
     readWorkbook.testData(tableMap);        
       
     for (int i = 0;i < tableMap.get("RowNo").size();i++) {
           String sLocateCol = tableMap.get("LocateCol").get(i);
           String sLocateColValue = tableMap.get("LocateColValue").get(i);           
           String indexValue = tableMap.get("Index").get(i);
           
	    if (indexValue.equals("")){
	        indexValue = "0";
	    }
           
            String LocateColMessege = sLocateCol+"Not Found";
            if (!sLocateCol.equals("")){
              common1.selectedRow = -1;
              common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue);
             //Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
            report.Info(sLocateCol+"Customer summary applet found using Locate Col");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
            }
            else 
            {
             common1.selectedRow = 1;
             report.Info("Customer summary applet found");
            
            }

     }
}


 @FindBy(xpath=".//*[@aria-label='Billing Profiles:Menu']")

 WebElementFacade MenuBillingProfile;

 @FindBy(xpath="//span/ul/li[11]/a")

 WebElementFacade MenuAboutRecord;

 @FindBy(xpath="//span/ul/li[8]/a")

 WebElementFacade MenuRunQuery;

 public void MenuBillingProfile(String MenuProfile) throws IOException, InterruptedException, AWTException{

       // Thread.sleep(5000);
	 Common.waitForPageLoad(getDriver());

       String filePath = "\\src\\test\\resources\\data\\Account.xls";

       String dataSheet = "MenuBillingProfile";


      Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(MenuProfile, filePath, dataSheet);

     readWorkbook.testData(tableMap);

      for (int i = 0;i < tableMap.get("MenuItem").size();i++) {   

      String sMenuItem = tableMap.get("MenuItem").get(i); 

     Thread.sleep(3000); 

     MenuBillingProfile.click();

     if(sMenuItem.equalsIgnoreCase("About Record")) {
          
           Thread.sleep(2000);

          MenuAboutRecord.click();

     }

    if(sMenuItem.equalsIgnoreCase("Run Query")) {

       Thread.sleep(2000); 

       MenuRunQuery.click();
    		}
     }
	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
}



//By Payel
//Verify Product and Services line items
	@FindBy(xpath="//*[text()='Product / Services']")
	WebElementFacade ProductAndServicesTab;
	@FindBy(xpath=".//*[@title='Products and Services:Show more']")
	WebElementFacade ProductandSeviceAppletShowMoreButton;
	@FindBy(xpath=".//*[@aria-label='Sales Order:Customer Account']")
	WebElementFacade CustomerAccountButton;
	@FindBy(xpath=".//*[@aria-label='Products and Services:Menu']")
	WebElementFacade ProductServicesMenuIcon;
	@FindBy(xpath=".//a[text()='Line Items']")
	WebElementFacade LineItemsTab;
	@FindBy(xpath="//span/ul/li[9]/a")
	WebElementFacade RefreshRecord;
	
	@FindBy(xpath="(.//*[contains(@id,'Product_Name')])[1]")
	WebElementFacade WaitforElement;
	   @FindBy(xpath=".//*[@title='Products and Services Menu']")
	   WebElementFacade ProductServiceMenuList;
	 		  
	   @FindBy(xpath=".//span/ul/li[12]/a")
	   WebElementFacade MenulistAboutRecord;
	
	
	public void ProductServicesVerify(String Action) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
	    String dataSheet = "ProductServices";
	    String applet = "//*[@title='Products and Services List Applet']";
	    String table = "//table[@summary='Products & Services Root List (Service)']";
	    
	    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
	    readWorkbook.testData(tableMap);
	    
	    if (tableMap.get("LocateCol").size()==0){
	       	report.Info(Action+" logical name not found in sheet.");
	       	Assert.assertTrue(false);        	
	       }
	    
	    
	    /*LineItemsTab.click();
	    Thread.sleep(3000);
	    
	    CustomerAccountButton.waitUntilClickable();
	    CustomerAccountButton.click();
	    */
	    
	    
	    if (ProductAndServicesTab.isDisplayed()){
	    	ProductAndServicesTab.waitUntilClickable();
			ProductAndServicesTab.click();
			Thread.sleep(3000);
		}
		else {
			AccountTabsDRopDown.selectByVisibleText("Product / Services");
			Thread.sleep(2000);
		}
	    
	    Common.waitForPageLoad(getDriver());
	    
	    if (ProductandSeviceAppletShowMoreButton.isDisplayed()){
	    	ProductandSeviceAppletShowMoreButton.click();
	    	Thread.sleep(3000);
	    	
	    }
	    String sLocateColValue = tableMap.get("LocateColValue").get(0);
	    int k=0;
	    if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")&& !sLocateColValue.equals("Null")){
	   	    do{
	   	    	//ProductServicesMenuIcon.click();
		    	//Thread.sleep(1000);
		    	//RefreshMenuItem.click();
	   	    	getDriver().navigate().refresh();
		    	Thread.sleep(5000);	   	    	
	   	    	k++;
	   	    	}while(common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true") && (k<10));
	    }

	    
	    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
	    	String sLocateCol = tableMap.get("LocateCol").get(i);
	        sLocateColValue = tableMap.get("LocateColValue").get(i);
	       // sLocateColValue = Replace(sLocateColValue,"InstalledId",DictionaryTest_G.Item("ACCNTMSISDN"))
	        String sExist = tableMap.get("Exist").get(i);
	        String sLocateColExpand = tableMap.get("LocateColExpand").get(i);
	        String sLocateColExpandValue = tableMap.get("LocateColExpandValue").get(i);
	        String sCollapse = tableMap.get("Collapse").get(i);
	        String sUIName = tableMap.get("UIName").get(i);
	        String sValue = tableMap.get("Value").get(i);
	        
	        String indexValue = "0";
	        
	        if (sLocateColValue.contains("Promotion")){
	     	   sLocateColValue = sLocateColValue.replace("Promotion", Serenity.sessionVariableCalled("ProductName").toString());
	     	   //sLocateColValue = Serenity.sessionVariableCalled("ProductName").toString();
	 		}
	        
	        String LocateColMessege = sLocateCol+"Not Found";
	        
	     //Locate Col to Expand   
        if (!sLocateColExpand.equals("")){
       	 if (sLocateColExpandValue.equals("RootProduct0")){
    			sLocateColExpandValue = Serenity.sessionVariableCalled("RootProduct0").toString();
    		}
    		
          common1.selectedRow = -1;
          Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateColExpand, sLocateColExpandValue, indexValue).equals("true"));
          report.Info(sLocateColExpand+"found");
          common1.ExpandRow(applet,table);
          report.Info(sLocateColExpand+"found and expanded");
		  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
        }
     
       //Local Col
        if (!sLocateCol.equals("")){
             common1.selectedRow = -1;
             String res  = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue);
           //  Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
             report.Info(sLocateCol+"found");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			if(sValue.equalsIgnoreCase("GetRecord")){
            	
	             ProductServiceMenuList.click();
	           	  Thread.sleep(1000);	
	           	  MenulistAboutRecord.click();
	           	  Thread.sleep(2000);

	             }
			     if(!sUIName.equals("")){
            	   common1.updateSiebList(applet,table, sUIName, sValue);
                   report.Info("Row updated: "+sUIName);
                   Serenity.setSessionVariable("AgreementId").to(sValue);
					   report.Info("Agreement Id value captured from ProductServices view is : " +sValue);
					   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
						                	   
               	}
           if(sExist.equals("")){            
        	    Assert.assertTrue(res+ " - " + sLocateCol + "-" + sLocateColValue + " not found in the list.", res.equalsIgnoreCase("True"));
        	   	report.Info(sLocateCol + "-" + sLocateColValue + " found in the list as expected");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
           		}
           else{
        	   if(sExist.equalsIgnoreCase("False")){
                sExist=  "False-Row Not Exist";
        	   }
        	   	Assert.assertTrue(sLocateCol + "-" + sLocateColValue + " existence is " + res + " but expected is " + sExist, res.equalsIgnoreCase(sExist));
        	   	report.Info(sLocateCol + "-" +  sLocateColValue + " existence is " + sExist + " as expected");
           		}
           //String sAction = "Run Query [Alt+ENTER]";
       		if (!sCollapse.isEmpty()){
       			ProductServicesMenuIcon.click();
       			Thread.sleep(1000);
       			RefreshRecord.click();
				report.Info("Info : Clicked on Menu Run Query Button.");
				Thread.sleep(1000);
       	}

        }
	    }
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	}


	@FindBy(xpath=".//a[text()='Line Items']")
	WebElement LineItemsTabWebElement;

	@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
	WebElementFacade PromotionList;
	@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")	  
	WebElementFacade PromotionSearchTextBox;	  

	@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")	  
	WebElement PromotionSearchTextBoxWebElement;	

	@FindBy(xpath=".//*[@aria-labelledby='s_11_l_Target_Promotion_Name']")
	WebElementFacade TargetPromotion;
	@FindBy(xpath=".//input[@aria-labelledby='PopupQuerySrchspec_Label']/following::button[@aria-label='Promotion Upgrades:Go']")
	WebElementFacade PromotionUpgradeGoButton;
	@FindBy(xpath=".//button[contains(@title,':OK')]") 
	WebElementFacade PromotionUpgradeOKButton;


	@Step
	public void PromotionUpgrade() throws InterruptedException, IOException, AWTException{
		
		String dataSheet = "ProductServices";
		String applet = "//*[@title='Promotion Upgrades List Applet']";
		String table = "//table[@summary='Promotion Upgrades']";
		
		/*String applet = "//*[contains(@class,'siebui-applet siebui-collapsible-applet siebui-list')]";
		String table = "//table[@class='ui-jqgrid-btable']";*/
		String validation ="";
		try {
		 validation = Serenity.sessionVariableCalled("TariffMigJourney").toString();
		}
		catch(Exception e) {
			validation ="";
		}
		Common.WaitForClock(Clockobj);
		
		if(isAlertPresent(getDriver())){
			Alert alert=getDriver().switchTo().alert();
			String ActAlertText=alert.getText();  
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			                                                                              
			if (ActAlertText.contains("SIMO Flex")){ 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				alert.accept();
				report.Info("alert messsage is"+ActAlertText);
			}  
			if (ActAlertText.contains("Eligible to Recommit")){
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				alert.accept();
				System.out.println("alert messsage is"+ActAlertText);
			}
		}
		Common.WaitForClock(Clockobj);
		String sStartingWith = Serenity.sessionVariableCalled("ProductName").toString();

		//Thread.sleep(45000);
		//PromotionSearchTextBox.withTimeoutOf(300,TimeUnit.SECONDS).waitUntilPresent();
		Common.WaitForClock(Clockobj);
		Common.waitForElement(PromotionSearchTextBoxWebElement);
		try{
			if (!Serenity.sessionVariableCalled("UsedProdAction").equals("")){
				long UsedProdstartTime = Serenity.sessionVariableCalled("UsedProdAction");
				long UsedProdendTime = System.currentTimeMillis();
			long UsedProdtotalTime = UsedProdendTime - UsedProdstartTime;
				Common.WritePerformanceOutput("Promotion Upgrade Applet visible,Total Time " + UsedProdtotalTime/1000);

			}      
		}
		catch(Exception E){

		}
		long PromoUpgstartTime = System.currentTimeMillis();
		//PromotionList.type("Promotion Name");
            //PromotionSearchTextBox.type(sStartingWith);
            //PromotionUpgradeGoButton.click();
		if(validation.equalsIgnoreCase("BlankAppletGigacube")) {
			sStartingWith="*SIMO*";
		}
		 PromotionSearchTextBox.sendKeys(sStartingWith);
         PromotionUpgradeGoButton.click();
         Thread.sleep(1000);
         
    //Commented java script code     
		/*JavascriptExecutor JSE = (JavascriptExecutor)getDriver();
		JSE.executeScript("arguments[0].value = '"+sStartingWith+"';", PromotionSearchTextBox);
		JSE.executeScript("arguments[0].click()", PromotionUpgradeGoButton);*/
		//Thread.sleep(1000);

		Common.waitForPageLoad(getDriver());
		Common.WaitForObjectPresence("//table[@summary='Promotion Upgrades']/tbody/tr");

		//int PromotionUpgardeTableSize= findAll(By.xpath("//table[@summary='Promotion Upgrades']/tbody/tr")).size();
		int PromotionUpgardeTableSize= findAll(By.xpath("//table[@summary='Promotion Upgrades']/tbody/tr[contains(@class,'content')]")).size();
		
		if(validation.equalsIgnoreCase("BlankAppletGigacube")) {
			
			Assert.assertTrue("Pass : No Rows in Table ", PromotionUpgardeTableSize==1);
				report.Info("the number of rows:"+PromotionUpgardeTableSize);
		}
		
	
		if(PromotionUpgardeTableSize>1){
			Common.selectedRow = -1;
	//UnCommenting this code as clash with CTR15 promotions is  there 
			Assert.assertTrue(Common.locateColumn(applet,table,"Target_Promotion_Name",sStartingWith, "0").equals("true"));
			report.Info("Promotions are available");
		}
		else{
			Assert.assertTrue("No rows displayed after clicking on Go button- Promotions not available", PromotionUpgradeOKButton.isVisible());
		}
		long startTime = System.currentTimeMillis();
		PromotionUpgradeOKButton.click();
		Common.waitForPageLoad(getDriver());

		//	Thread.sleep(20000);
		Common.WaitForClock(Clockobj);
		Common.waitForElement(LineItemsTabWebElement);
		Common.WaitForObjectPresence(".//a[text()='Line Items']");
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		Common.WritePerformanceOutput("Target promotion selected,Total Time " + totalTime/1000);
		//  	LineItemsTab.withTimeoutOf(240,TimeUnit.SECONDS).waitUntilClickable(); 
		/*long PromoUpgendTime = System.currentTimeMillis();
    	   	long PromoUpgtotalTime = PromoUpgendTime - PromoUpgstartTime;
    	   	Common.WritePerformanceOutput("Target promotion selected,Total Time " + PromoUpgtotalTime/1000);*/


		//LineItemsTab.waitUntilClickable();
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	}
	
	  @FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']//following::*[@title,'Packages:Go']")//Promotion Upgrades:Go']")
      WebElementFacade PackagesGoButton;
	//td[contains(@aria-labelledby,'Payment_Method')]
	  
	  @FindBy(xpath=".//*[@title='Packages:OK']")//Promotion Upgrades:OK']")
	 // @FindBy(xpath=".//*[Contains(@title='s:OK')]")	 
      WebElementFacade PackagesOKButton;
	  
      @Step
      public void PackagesNLFL() throws InterruptedException, IOException, AWTException{
    	  
    	  
    	   String sStartingWith = Serenity.sessionVariableCalled("ProductName").toString();
    	   
    	   Common.waitForPageLoad(getDriver());
            //Thread.sleep(45000);
            PromotionSearchTextBox.waitUntilVisible();
            PromotionList.type("Promotion Name");
            PromotionSearchTextBox.typeAndEnter(sStartingWith);
            //PromotionUpgradeGoButton.withTimeoutOf(20,TimeUnit.SECONDS).waitUntilClickable();
           // PromotionUpgradeGoButton.click();
            //Thread.sleep(1000);
            Common.waitForPageLoad(getDriver());
            
            int PromotionUpgardeTableSize= findAll(By.xpath("//table[@summary='Packages']/tbody/tr")).size();
            

            if(PromotionUpgardeTableSize>1){
          	  report.Info("Promotions are available");
                  }
            else{
          	  Assert.assertTrue("No rows displayed after clicking on Go button- Promotions not available", PromotionUpgardeTableSize>1);
            }

            PackagesOKButton.click();
    	   	Thread.sleep(20000);
    	   	
    	   	LineItemsTab.waitUntilClickable();
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
       	
      }
      
      @FindBy(xpath="//*[@aria-label='New Account']/following-sibling::span")
      WebElementFacade OpenPopUpNewAccount;
      @FindBy(xpath=".//*[@title='Pick Account:Go']")
      WebElementFacade PickAccountGoButton;
    	
      @FindBy(xpath=".//*[@aria-label='Pick Account:OK']")
        WebElementFacade PickAccountOkButton;
      @FindBy(xpath="//*[@aria-label='New Billing Profile']/following-sibling::span")
        WebElementFacade OpenPopUpNewBillingProfile;
      @FindBy(xpath=".//*[@aria-label='Pick Billing Profile:OK']")
        WebElementFacade BillingProfileOkButton;
      @FindBy(xpath=".//*[@title='Transfer of Ownership:Continue']")
      	WebElementFacade TOOContinueButton;
      @FindBy(xpath="//input[@aria-label='Status']")
    	WebElementFacade OrderStatus;
      
      @FindBy(xpath="//*[text()='Pick Account']")
  	WebElementFacade TOONewAccount;
    
      //@Tarun
      public void TransferOfOwnerShip() throws InterruptedException, IOException, AWTException {
      	
      	String applet = "//*[@title='Pick Account List Applet']";
          String table = "//table[@summary='Pick Account']";
          
             
      	String sAccountNumber = Serenity.sessionVariableCalled("AccountNo").toString();
      	Common.waitForPageLoad(getDriver());
      	//String sAccountNumber="7000289825";
            //Thread.sleep(10000);
     
        	TOOContinueButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible(); 
            //TOOContinueButton.waitUntilVisible();
            OpenPopUpNewAccount.click();
            TOONewAccount.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible(); 
            //Thread.sleep(5000);
            common1.selectedRow = 1;
            
            common1.updateSiebList(applet,table,"Text|Account_Number", sAccountNumber);
            
            //Thread.sleep(1000);
            PickAccountGoButton.click();
            Thread.sleep(2000);
            
            int AccountTableSize= findAll(By.xpath("//table[@summary='Pick Account']/tbody/tr")).size();

            if(AccountTableSize>1){
          	  report.Info("Account Number is searched successfully "+sAccountNumber);
                  }
            else{
          	  Assert.assertTrue("No rows displayed after clicking on Go button. ", AccountTableSize>1);
            }
          	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
            PickAccountOkButton.click();
            Thread.sleep(1000);
            OpenPopUpNewBillingProfile.click();
           // BillingProfileOkButton.waitUntilVisible();
            Thread.sleep(4000);
            int BillingProfileTableSize= findAll(By.xpath("//table[@summary='Billing profile']/tbody/tr")).size();

            if(BillingProfileTableSize>1){
          	  report.Info("Blling Profile is displayed successfully for Account Number "+sAccountNumber);
                  }
            else{
          	  Assert.assertTrue("No rows displayed after clicking on billing profile Go button. ", BillingProfileTableSize>1);
            }
            
            BillingProfileOkButton.click();
            //Thread.sleep(1000);
            TOOContinueButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            TOOContinueButton.click();
            OrderStatus.withTimeoutOf(240,TimeUnit.SECONDS).waitUntilVisible();
            try{
                if (!Serenity.sessionVariableCalled("UsedProdAction").equals("")){
                    long UsedProdstartTime = Serenity.sessionVariableCalled("UsedProdAction");
              	  long UsedProdendTime = System.currentTimeMillis();
                    long UsedProdtotalTime = UsedProdendTime - UsedProdstartTime;
                    Common.WritePerformanceOutput("TOO Done,Total Time " + UsedProdtotalTime/1000);
                    	  
                }      
          }
          catch(Exception E){
          	
          }
			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
          }
       
       
        public void CustomerAccount() throws InterruptedException, IOException, AWTException{
        	if (CustomerAccountButton.isCurrentlyVisible()){
        		CustomerAccountButton.click();
        	}
        	else if (!CustomerAccountButton.isCurrentlyVisible()){
        		Common.waitForPageLoad(getDriver());
        		//LineItemsTab.withTimeoutOf(160,TimeUnit.SECONDS).waitUntilClickable();        	 
      	  	    LineItemsTab.click();
          	  	CustomerAccountButton.withTimeoutOf(160,TimeUnit.SECONDS).waitUntilClickable();     	      
          	    CustomerAccountButton.click();
          	  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	}        	   	  
        }
     
  
       @FindBy(xpath="(.//*[text()='Bulk Modify'])[1]")
        WebElementFacade BulkModifyTab;
        
        @FindBy(xpath=".//*[@id='jqgh_s_1_l_Product_Name']")
        WebElementFacade WaitForElement;
        @FindBy(xpath=".//button[@title='Bulk Modify Menu']")
        WebElementFacade BulkModMenu;
        @FindBy(xpath="(.//*[text()='Bulk Modify'])[2]")
        WebElementFacade BulkModifyText;
       

		public void BulkMOdify(String rowname) throws InterruptedException, IOException, AWTException{
			
			String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
		      String filePath = "\\src\\test\\resources\\data\\Account.xls";
		      String applet = "//*[@title='Bulk Modify List Applet']";
		      String table = "//table[@summary='Bulk Modify']";
		      String dataSheet = "BulkModify";
		      if (BulkModifyTab.withTimeoutOf(60,TimeUnit.SECONDS).isCurrentlyVisible()&& !BulkModifyText.isCurrentlyVisible()){
		    	  BulkModifyTab.click();
		    	  Thread.sleep(2000);
		    	  WaitForElement.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
		      }
		      else if (!BulkModifyTab.withTimeoutOf(60,TimeUnit.SECONDS).isCurrentlyVisible()&& !BulkModifyText.isCurrentlyVisible()){
		    	  AccountTabsDRopDown.click();
		    	  Thread.sleep(2000);
		    	  WaitForElement.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
		      }
		      if ((common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true"))){
		    	  int k=0;
			   	    do{
			   	    	getDriver().navigate().refresh();
			   	    	//BulkModMenu.click();
			   	    	//Thread.sleep(1000);
			   	    	//RefreshMenuItem.click();	
			   	    	WaitForElement.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
			   	    	k++;
			   	    	}while(common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true") && (k<11));
		      }
		      
		       
		    	   
		    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
		    readWorkbook.testData(tableMap);
		  
		    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
		    
			 String sUIName =  tableMap.get("UIName").get(i);
		      String sValue =  tableMap.get("Value").get(i);		      
		      String sLocateCol = tableMap.get("LocateCol").get(i);
		      String sLocateColValue = tableMap.get("LocateColValue").get(i);
		      String sIndex = tableMap.get("Index").get(i);
		      if (sLocateColValue.equals("Promotion")){
	    		   sLocateColValue = Serenity.sessionVariableCalled("ProductName").toString();
	    	   }
		      if(sIndex.equals("")){
                  sIndex="0";
                }
                if (sLocateColValue.equals("RootProduct0")){
 	    		   sLocateColValue = Serenity.sessionVariableCalled("RootProduct0").toString();
 	    	   }
		 
		      
		      String LocateColMessege = sLocateCol+"Not Found";
			 if (!sLocateCol.equals("")){
                 common1.selectedRow = -1;
                 Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
                 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								               
                 }
            else{
                  common1.selectedRow = 1;
            }

    	   if (!sUIName.equals("")){    	    		
    		common1.updateSiebList(applet,table, sUIName, sValue);   
			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
    	   }
		}

	}
		
		@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
        WebElementFacade BulkModProductNameList;
        
        @FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")
        WebElementFacade SearchProductTextBox;
        
        @FindBy(xpath=".//*[@class='siebui-popup-button']/button[@title='Selected:Add >']")
        WebElementFacade AddProduct;
        
        @FindBy(xpath=".//*[@class='siebui-popup-button']/button[@title='Selected:OK']")
        WebElementFacade OkButton;
                
        @FindBy(xpath=".//*[@title='Bulk Modify:Modify']")
        WebElementFacade BulkModifyButton;
        
        @FindBy(xpath=".//*[@title='Bulk Modify:Transfer Of OwnerShip']")
        WebElementFacade BulkTOOButton;
        

		public void BulkMOdifyAddProducts(String rowname) throws InterruptedException, IOException, FindFailed, AWTException{
			
			String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
		     String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		     String applet = "//*[@title='Bulk Modify List Applet']";
		     String table = "//table[@summary='Bulk Modify']";
		    String dataSheet = "BulkModifyAddProducts";
		    
		    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
		    readWorkbook.testData(tableMap);
		    
		    if (tableMap.get("PopUp").size()==0){
		       	report.Info(rowname+" logical name not found in sheet.");
		       	Assert.assertTrue(false);        	
		       }
		  
		    for (int i = 0;i < tableMap.get("PopUp").size();i++) {
		    
		      String sModify = tableMap.get("Modify").get(i);
		      String sProductName = tableMap.get("ProductName").get(i);
		      String sTransferOfOwnership = tableMap.get("TransferOfOwnership").get(i);
		      String sProcessStatus = tableMap.get("ProcessStatus").get(i);
		      String sPopUp = tableMap.get("PopUp").get(i);
		   /*   String sLocateCol = tableMap.get("LocateCol").get(i);
	    	  String sLocateColValue = tableMap.get("LocateColValue").get(i);
	    	  String sUIName = tableMap.get("UIName").get(i);
	    	  String sValue = tableMap.get("Value").get(i);
	    	  String sIndex = tableMap.get("Index").get(i);*/
	    	/*   if (sLocateColValue.equals("Promotion")){
	    		   sLocateColValue = Serenity.sessionVariableCalled("ProductName").toString();
	    	   }*/
	    	   
	    	  /*    if (!sLocateCol.equals("")){
                      common1.selectedRow = -1;
                      Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                                    
                      //report.Info(sLocateCol+" found");    
                      }
                  else{
                      common1.selectedRow = 1;
                  }
                  if (!sUIName.equals("")){
                      common1.updateSiebList(applet,table, sUIName, sValue);                                      
                      //report.Info(sLocateCol+" found");                                      
                      }*/
		      
		      if (sModify.equalsIgnoreCase("N")){
		    	  BulkModProductNameList.type("Product Name");
		    	  SearchProductTextBox.typeAndEnter(sProductName);
		    	  AddProduct.click();
		    	  Thread.sleep(1000);
		    	  OkButton.click();
		    	  Thread.sleep(2000);
		      }
		      if (sModify.equalsIgnoreCase("Y")){
		    	  BulkModifyButton.click();
		    	  Thread.sleep(2000);
		    	  sikuliUtility.ClickFraudRiskAlert(SikulifilePath);
		      }
		      if (sTransferOfOwnership.equalsIgnoreCase("Y")){
		    	  BulkTOOButton.click();
		    	  Thread.sleep(2000);
		    	  //sikuliUtility.ClickFraudRiskAlert(SikulifilePath);
		      }
		  }
		   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
	}
		 @FindBy(xpath="(.//*[@aria-labelledby='s_1_l_VF_Bulk_Process_Status'])[1]")
	        WebElementFacade ProcessStatus;

			public void BulkViewProcessStatusValidation(String rowname) throws InterruptedException, IOException, AWTException{
				
				String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
			      String filePath = "\\src\\test\\resources\\data\\Account.xls";
			      String applet = "//*[@title='Bulk Modify List Applet']";
			      String table = "//table[@summary='Bulk Modify']";
			    String dataSheet = "BulkModify";
			    
			    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
			    readWorkbook.testData(tableMap);
			    
			    if (tableMap.get("LocateCol").size()==0){
			       	report.Info(rowname+" logical name not found in sheet.");
			       	Assert.assertTrue(false);        	
			       }
			    
			    if (BulkModifyTab.withTimeoutOf(60,TimeUnit.SECONDS).isCurrentlyVisible()){
			    	  BulkModifyTab.click();
			    	  Thread.sleep(2000);
			    	  WaitForElement.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
			      }
			    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
			    			 		      
			      String sLocateCol = tableMap.get("LocateCol").get(i);
			      String sLocateColValue = tableMap.get("LocateColValue").get(i);
			      String sIndex = tableMap.get("Index").get(i);
			      String LocateColMessege = sLocateCol+"Not Found";
			      String getProcessStatus = "";
				   if(sIndex.equals("")){
			    	  sIndex="0";
			      }
			      int j = 0;
			      do {		    	 
				 if (!sLocateCol.equals("")){
	                 common1.selectedRow = -1;
	                 Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                                                         
	                 }

				  	if (BulkModMenu.isCurrentlyVisible()){
				  		BulkModMenu.click();
			    		Thread.sleep(1000);
			    		RefreshMenuItem.click();
				  	}
		    		
				 	getProcessStatus = ProcessStatus.getTextValue();
				 	
				 	if (getProcessStatus.equals("Completed")){
				 		break;
				 	}
				 }while (getProcessStatus.equals("Completed")&&(j<10));
	    	
			}
			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
		}
		
			@FindBy(xpath=".//*[text()='Companies']")
			WebElementFacade CompaniesLabel;
			@FindBy(xpath=".//*[@title='Accounts:New']")
			WebElementFacade AccounstNewButon;
			@FindBy(xpath=".//*[@aria-label='Pick Resource:OK']")
			WebElementFacade PickMSISDNOKButton;		
			@FindBy(xpath=".//*[@title='Pick Promotion:OK']")
			WebElementFacade PickpromotionOKButton;
			@FindBy(xpath=".//*[contains(@aria-label,'More Info')]/following::*[text()='Accounts']")
			WebElementFacade ContactsAccountsTab;		
			@FindBy(xpath=".//*[@id='1_s_3_l_Account_Number']")
			WebElementFacade AccNoElement;		
			@FindBy(xpath=".//*[@name='Account_Number']")
			WebElementFacade AccNoTextBox;		
			@FindBy(xpath=".//*[@title='Pick Account:Cancel']")
			WebElementFacade AccNoCancelButton;
			@FindBy(xpath=".//*[@title ='Pick Account:OK']")
			WebElementFacade AccNoOKButton;		
			@FindBy(xpath=".//*[@id='s_at_m_1']")
			WebElementFacade MenuButton;

			@Step
			public void AccountsPrePost(String LineItemEntry) throws IOException, InterruptedException, AWTException{
				String filePath = "\\src\\test\\resources\\data\\Account.xls";
			    String dataSheet = "AccountsPrePost";
			    String applet = "//*[@title='Accounts List Applet']";
			    String table = "//table[@summary='Query, Modify, Create Accounts related to Contacts']";   
			    
			    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(LineItemEntry, filePath, dataSheet);
			    readWorkbook.testData(tableMap);
			    
			    /*if (tableMap.get("LocateCol").size()==0){
			       	report.Info(LineItemEntry+" logical name not found in sheet.");
			       	Assert.assertTrue(false);        	
			       }*/
			    
			    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
					String sClickNew = tableMap.get("ClickNew").get(i);
				   	String sLocateCol = tableMap.get("LocateCol").get(i);
					String sLocateColValue = tableMap.get("LocateColValue").get(i);
					String UIName = tableMap.get("UIName").get(i);
					String value = tableMap.get("Value").get(i);
					String Index = tableMap.get("Index").get(i);
					String sPayingChild = tableMap.get("PayingChild").get(i);
					if(Index.equals("")){
						Index="0";
					}
					
					String sParentAcountNo = Serenity.sessionVariableCalled("AccountNo").toString();

					if (sClickNew.equalsIgnoreCase("Yes")){
						ContactsAccountsTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
						ContactsAccountsTab.click();
						CompaniesLabel.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible();	
						AccounstNewButon.click();					
						}
			        if (!sLocateCol.equals("")){
			            common1.selectedRow = -1;
			           Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
			           report.Info("Row explanded: "+sLocateCol);
					    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			            }
			       else{
			             common1.selectedRow = 1;
			       }
			        	if (!UIName.equals("")){ 
			        	
			           String sValue =  (common1.updateSiebList(applet,table, UIName, value)); 
				           if (UIName.contains("CaptureTextValue|Account_Number")){	
				        	   Serenity.setSessionVariable("PrePostAccountNo").to(sValue);
				           }
				           
				           if (sPayingChild.contains("Yes")){
				        	   Serenity.setSessionVariable("PayingPrePostAccNo").to(sValue);
				           }
				        	   
				           	
				           if (UIName.contains("OpenPopUp|Parent")){
				        	   //AccNoCancelButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				        	  // Thread.sleep(3000);
				        	   Common.waitForPageLoad(getDriver());
				        	   AccNoElement.click();
				        	   AccNoTextBox.typeAndEnter(sParentAcountNo);
				        	   Thread.sleep(1000);
				        	   AccNoOKButton.click();
				        	   
				        	   
				           }	
				            Thread.sleep(2000);
				            report.Info("Row updated successfully"); 
				            
				            if (UIName.contains("OpenPopUp|StreetAddress")){
								applet = "//*[@title='Add Address List Applet']";
							   	table = "//table[@summary='Add Address']"; 
								
								//AddressPopUp.click();
			    				Thread.sleep(4000);	   	    		
			    				Common.selectedRow = 1;
			    				
								Common.updateSiebList(applet,table, "Text|Status", "Validated");   					
								AddressStatusElement.click();
								AddressStatusTextBox.sendKeys(Keys.ENTER);
								
								if (AddressStatusTextBox.isCurrentlyVisible() ) {
									AddressStatusTextBox.sendKeys(Keys.ENTER); //for chrome
								}
								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			    				AddAddress.click();
			    				Thread.sleep(1000);
			    				AddressOKButton.click();
			    				Thread.sleep(2000);
			    			}
			            }
			    }
			    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			       	//MenuButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			       	//MenuButton.click();
			       	//RefreshMenuItem.click();
				 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
			}	
		
	   	public void VerifyAttributesTab(String Action) throws InterruptedException, IOException, AWTException{
    		String filePath = "\\src\\test\\resources\\data\\Account.xls";
    	    String dataSheet = "ProductServices";
    	    String applet = "//*[@title='Attributes List Applet']";
    	    String table = "//table[@summary='Asset Mgmt - Asset XA List']";
    	    
    	    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
    	    readWorkbook.testData(tableMap);
    	    
    	    if (tableMap.get("LocateCol").size()==0){
		       	report.Info(Action+" logical name not found in sheet.");
		       	Assert.assertTrue(false);        	
		       }
    	    
    	    
    	    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
    	    	String sLocateCol = tableMap.get("LocateCol").get(i);
    	        String sLocateColValue = tableMap.get("LocateColValue").get(i);
    	        String sUIName = tableMap.get("UIName").get(i);
    	        String sValue = tableMap.get("Value").get(i);
    	        
    	        String indexValue = "0";
    	        
    	        if (indexValue.equals("")){
    	        	indexValue = "0";
    	         }
    	        
    	        if (!sLocateCol.equals("")){
    	            common1.selectedRow = -1;
    	           Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
    	           report.Info("Row explanded: "+sLocateCol);
    	           //common1.updateSiebList(applet,table, UIName, value);
    	           //report.Info("Row updated: "+UIName);
    	            }
    	       else{
    	             common1.selectedRow = 1;
    	       }
    	        	if (!sUIName.equals("")){                                              
    	            common1.updateSiebList(applet,table, sUIName, sValue);
    	            Thread.sleep(1000);
    	            report.Info("Row updated successfully"); 
    	            //RefreshRecord.click();
    	            Thread.sleep(2000);
    	            }
    	    	}
				 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
    	}
        @FindBy(xpath=".//*[@title='Bulk Modify Menu']")
        WebElementFacade BulkModifyMenuIcon;
        @FindBy(xpath=".//ul[@id='s_at_m_1-menu']/li[41]/a")
        WebElementFacade BulkModifyMenuModify;
        @FindBy(xpath=".//*[@title='Bulk Modify:Modify']")
        WebElementFacade BulkModifyModifyButon;
        @FindBy(xpath=".//ul[@id='s_at_m_1-menu']/li[9]/a")
        WebElementFacade BulkModifyRunQuery;
        @FindBy(xpath="//span/ul/li[9]/a")
        WebElementFacade BulkModifyRefreshMenuItem;
        @FindBy(xpath="//span/ul/li[31]/a")
        WebElementFacade Cascademenu;
           public void MenuBulkModify(String rowname) throws InterruptedException, IOException, FindFailed, AWTException{
            
                  String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
                  String applet = "//*[@title='Bulk Modify List Applet']";
                  String table = "//table[@summary='Bulk Modify']";
                  String dataSheet = "MenuBulkModify";
                  String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
                
                Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
                readWorkbook.testData(tableMap);
                
                if (tableMap.get("LocateCol").size()==0){
    		       	report.Info(rowname+" logical name not found in sheet.");
    		       	Assert.assertTrue(false);        	
    		       }


              
                for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
                
                   String sMenuItem =  tableMap.get("MenuItem").get(i);
                  String sModify =  tableMap.get("Modify").get(i);                  
                  String sLocateCol = tableMap.get("LocateCol").get(i);
                  String sLocateColValue = tableMap.get("LocateColValue").get(i);
                  String sIndex = tableMap.get("Index").get(i);
                  if(sIndex.equals("")){
                    sIndex="0";
                  }
                  if (sLocateColValue.equals("RootProduct0")){
      	    		   sLocateColValue = Serenity.sessionVariableCalled("RootProduct0").toString();
      	    	   }
                 
                  String LocateColMessege = sLocateCol+"Not Found";
                  if (!sLocateCol.equals("")){
                 common1.selectedRow = -1;
                 Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
                 report.Info(sLocateCol+"Customer summary applet found using Locate Col");  
                  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
                 }
            else{
                  common1.selectedRow = 1;
                  report.Info("Customer summary applet found");
            }
                  Common.waitForPageLoad(getDriver());
                  BulkModifyMenuIcon.waitUntilClickable();
                  BulkModifyMenuIcon.click();
                  BulkModifyRefreshMenuItem.click();
                  BulkModifyMenuIcon.click();
                  Cascademenu.click();
                  Thread.sleep(2000);
                  report.Info("Menu item selecteed successfully");
                  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                  
                   if(sModify.equalsIgnoreCase("yes")){
                        BulkModifyModifyButon.isVisible();
                        BulkModifyModifyButon.waitUntilClickable();
                        BulkModifyModifyButon.click();
                        sikuliUtility.ClickFraudRiskAlert(SikulifilePath);
                        report.Info("Modify done sesuccessfully");
                        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                  }
                  BulkModifyMenuIcon.waitUntilClickable();
                  BulkModifyMenuIcon.click();
                  BulkModifyRunQuery.click();
            }
      }
           
           @FindBy(xpath=".//*[@class='siebui-icon-pick']")
           WebElementFacade TargetPromotionIcon;
           @FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
           WebElementFacade AllpromotionPickList;
           @FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")
           WebElementFacade TargetPromotionTextBox;
           @FindBy(xpath=".//*[@title='All Promotions:Go']")
           WebElementFacade AllPromotionGobutton;
           public void BulkModify_TargetPromotion_AllPromotions() throws IOException, AWTException, InterruptedException{
               
               String sPromotionName=Serenity.sessionVariableCalled("ProductName").toString();
               
               if(AllpromotionPickList.isVisible()){
                     AllpromotionPickList.clear();
                     AllpromotionPickList.type("Promotion Name");
                     TargetPromotionTextBox.type(sPromotionName);
                     AllPromotionGobutton.click();
                     report.Info(" Pass: All promotion go butoon cliceked successfully");
                     sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
               }else{
                     report.Info("Fail: Target Promotion pop up is not clicked successfully");
                     sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
               }
           }
           //@FindBy(xpath=".//*[@aria-label='Account Details:Query']")
         	//WebElementFacade Query;  
           @FindBy(xpath="(.//*[contains(@aria-label,'Query')])[1]")
       	WebElementFacade Query;
         	@FindBy(xpath=".//*[@aria-labelledby='Customer_Code_Label']")
         	WebElementFacade AccountNumber;
         	@FindBy(xpath=".//*[@title='Account Details:Go']")
         	WebElementFacade Go;
         	@FindBy(xpath=".//a[@name='Name']") 
  		WebElementFacade accountName;
         	
             public void SearchAccountPrePost(String AccountNo) throws IOException, AWTException, InterruptedException{
          	  String sAccountNo = Serenity.sessionVariableCalled("PrePostAccountNo").toString();
            	// Serenity.setSessionVariable("PrePostAccountNo").to("7000675530");
            	//String sAccountNo = "7000675530";
          	AccountsTab.waitUntilEnabled();
         		AccountsTab.click();
         		Common.WaitForClock(Clockobj);
         		Query.click();
         		AccountNumber.waitUntilEnabled();
         		if (sAccountNo.equals("")){
         			sAccountNo = Serenity.sessionVariableCalled("PrePostAccountNo").toString();
             		AccountNumber.type(sAccountNo);
         		}
         		else{
         			AccountNumber.type(sAccountNo);
         		}
         		
         		Go.waitUntilEnabled();
         		Go.click();
         		Common.waitForPageLoad(getDriver());
         		//accountName.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
         		//Thread.sleep(3000);
         		accountName.click();
         		//AccountSummaryTab.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
  			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
             }
           
           
             @FindBy(xpath="(.//span[@aria-label='Multiple Selection Field'])[1]")
             WebElementFacade AddressPopUp;
             @FindBy(xpath="//*[@title='Add Address:Cancel']")
             WebElementFacade AddressCancel;           
             @FindBy(xpath="//*[@title='Account Addresses:Add >']")
             WebElementFacade AddAddress; 
             @FindBy(xpath=".//*[contains(@aria-describedby,'Status')]")
      		WebElement AddressStatusElement;
             @FindBy(xpath="   .//*[@id='1_s_12_l_Postal_Code']")
             WebElementFacade PostCodeElemnt; 
             @FindBy(xpath="   .//*[@id='1_Postal_Code']")
             WebElementFacade PostCodeTxtBox; 
             @FindBy(xpath=".//*[contains(@aria-describedby,'Status')]/input")
             WebElementFacade AddressStatusTextBox;
             @FindBy(xpath=".//span[@class='siebui-popup-button']/button[@title='Account Addresses:OK']")
             WebElementFacade AddrssOKButton;
            public void Accounts_AddressLine() throws InterruptedException{
                String applet = "//*[@title='Add Address List Applet']";
                String table = "//table[@summary='Add Address']"; 
                
                
                AccountDropDown.selectByVisibleText("Addresses");
                Common.waitForPageLoad(getDriver());
                
                AddressPopUp.click();
                Thread.sleep(4000);                       
                Common.selectedRow = 1;
                Common.updateSiebList(applet,table, "Text|Status", "Validated");
                AddressStatusElement.click();
                AddressStatusTextBox.sendKeys(Keys.ENTER);
				AddressStatusTextBox.sendKeys(Keys.ENTER); //for chrome
                AddAddress.click();
                Thread.sleep(1000);
                AddressOKButton.click();
                   
            }            
            
           	@FindBy(xpath="//*[@aria-label='New Billing Account']/following-sibling::span")
           	WebElementFacade NewBillAccountPopUp;
            @FindBy(xpath="//*[@aria-label='New Billing Profile']/following-sibling::span")
           	WebElementFacade NewBillProfPopUp;       	
            @FindBy(xpath="//*[@aria-label='Target Promotion']/following-sibling::span")
           	WebElementFacade TargetPromotionPopUp;  
           	@FindBy(xpath=".//*[@id='1_Account_Number']")
            	WebElementFacade AccountNoTextBox;          	
           	@FindBy(xpath="//*[@title='Pick Account:OK']")
            	WebElementFacade AccountNoOKButton;          	
           	@FindBy(xpath="//*[@title='Pick Account:Cancel']")
            	WebElementFacade AccountNoCancelButton;          	
           	@FindBy(xpath=".//*[@title='Pick Billing Profile:OK']")
            	WebElementFacade BillingProfOKButton;          	
         	@FindBy(xpath="//*[text()='Billing profile']")
            	WebElementFacade BillingProfileLabel;        	
         	@FindBy(xpath="//*[@title='Next record']")
            	WebElementFacade TargetPromNextRecordButton;  
         	
         	@FindBy(xpath=".//*[@title='Pre to Post Migration:OK']")
        	WebElementFacade PromotionOKButton;  
         	@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
            	WebElementFacade PromotionNameList;        	
         	@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")
            	WebElementFacade PromotionNameTextBox;        	
         	@FindBy(xpath="//*[contains(@title,'Continue')]")
            	WebElementFacade ContinueButton;    	
         	@FindBy(xpath=".//*[@id='1_s_12_l_Account_Number']")
            	WebElementFacade AccountNoElement;  
         	

         	public void PrePost_Upgrade() throws InterruptedException, IOException, AWTException{
           	  String sStartingWith = Serenity.sessionVariableCalled("ProductName").toString();
           	  String sAccountNo = Serenity.sessionVariableCalled("PrePostAccountNo").toString();
           	  String applet = "//*[@title='Installed Assets List Applet']";
                 String table = "//table[@summary='Pick Account']";
                 String sUIName = "List|Account_Number";
                 String sValue = sAccountNo;
           	  
           	  NewBillAccountPopUp.click();
           	  AccountNoCancelButton.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
           	  AccountNoElement.click();
           	  AccountNoTextBox.typeAndEnter(sAccountNo);
           	  AccountNoOKButton.click();
           	  NewBillProfPopUp.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
           	  NewBillProfPopUp.click();
           	Common.waitForPageLoad(getDriver());
           	 // BillingProfOKButton.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
           	  BillingProfOKButton.click();
           	  Thread.sleep(3000);
           	  TargetPromotionPopUp.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
           	  TargetPromotionPopUp.click();
           	 Thread.sleep(30000);
           	  
           	PromotionNameList.withTimeoutOf(80,TimeUnit.SECONDS).waitUntilClickable();    
           	Common.waitForElement(PromotionNameList);
           	
           	getDriver().findElement(By.xpath(".//*[@aria-labelledby='PopupQuerySrchspec_Label']")).sendKeys(sStartingWith);
              Thread.sleep(1000);
              getDriver().findElement(By.xpath(".//*[@aria-labelledby='PopupQuerySrchspec_Label']")).sendKeys(Keys.ENTER);
              //getDriver().findElement(By.xpath(".//*[@aria-labelledby='PopupQuerySrchspec_Label']")).sendKeys(Keys.ENTER);

           	  Thread.sleep(4000);
				   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
           	 // ContinueButton.waitUntilClickable();
				/* Common.waitForElement(PromotionOKButton); 
				 PromotionOKButton.click();
				 Common.waitForElement(ContinueButton);*/
				   
				   if (PromotionUpgradeOKButton.isCurrentlyVisible()) {
					   getDriver().findElement(By.xpath("//*[contains(@title,':OK')]")).click();
					   Thread.sleep(3000);
					   
				   }
			 
			 Thread.sleep(1000);
           	 getDriver().findElement(By.xpath("//*[contains(@title,'Continue')]")).click();
           	  //ContinueButton.click();

           	  LineItemsTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
           	  
				   sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
           	  Thread.sleep(3000);
           	  
           	  if (!LineItemsTab.isCurrentlyEnabled()){
           		  ContinueButton.click();
           		  Thread.sleep(3000);
           		  Common.waitForElement(LineItemsTab);
           	  }

             } 
         	
              @FindBy(xpath=".//*[@title='Import Dialog:OK']")
              WebElementFacade ImportDialogOkButton;
              
               public void BulkModify_TargetPromotionProcess_ImportDialog(String rowname) throws InterruptedException, IOException, AWTException{
                     
                     String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
                     String filePath = "\\src\\test\\resources\\data\\Account.xls";
                     String applet = "//*[@title='Import Dialog List Applet']";
                     String table = "//table[@summary='Import Dialog']";
                     String dataSheet = "BulkModify";
                   
                   Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
                   readWorkbook.testData(tableMap);
				   
				    if (tableMap.get("LocateCol").size()==0){
	           	report.Info(rowname+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
                 
                   for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
                   
                                       
                     String sLocateCol = tableMap.get("LocateCol").get(i);
                     String sLocateColValue = tableMap.get("LocateColValue").get(i);
                     String sIndex = tableMap.get("Index").get(i);
                     if(sIndex.equals("")){
                     sIndex="0";
                     }
                     String LocateColMessege = sLocateCol+"Not Found";
                      if (!sLocateCol.equals("")){
                       common1.selectedRow = -1;
                       Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
                       report.Info(sLocateCol+"Customer summary applet found using Locate Col"); 
                       sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                       }
                  else{
                        common1.selectedRow = 1;
                     }  
                     ImportDialogOkButton.waitUntilClickable();
                     ImportDialogOkButton.click();
                     report.Info("Target Promotion Process is done successfully");
                     sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
             }

         }  
               
               
               @FindBy(xpath = ".//*[@aria-label='Third Level View Bar']")
               WebElementFacade AccountDropDown;
             @FindBy(xpath = ".//*[@aria-label='Media Type:New']")
               WebElementFacade CreateNewMediaType;
             @FindBy(xpath =".//input[@name='Media_Type']")
             WebElementFacade MediaTypeList;
             @FindBy(xpath =".//*[@aria-label='Media Type:Delete']")
             WebElementFacade DeleteMediaTypeButton;
             
             
         public void AddDeleteMedia(String LogicalName) throws InterruptedException, IOException, AWTException{
               String filePath = "\\src\\test\\resources\\data\\Account.xls";
               String dataSheet = "AddDeleteMedia";

               Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(LogicalName, filePath, dataSheet);
               readWorkbook.testData(tableMap);
               
               if (tableMap.get("EX").size()==0){
   		       	report.Info(LogicalName+" logical name not found in sheet.");
   		       	Assert.assertTrue(false);        	
   		       }
               
               for (int i = 0;i < tableMap.get("EX").size();i++) {
                   String sMediatype = tableMap.get("MediaType").get(i);
                  
                   String sAction = tableMap.get("Action").get(i);
                   String sPopup = tableMap.get("Popup").get(i);
                   
                   Thread.sleep(30000);
                   
                   
               if(ProfilesTab.isDisplayed()){
                     ProfilesTab.click();
                     }
                     else{
                           AccountDropDown.selectByVisibleText("Profiles");
                     } 
                 Common.waitForPageLoad(getDriver());
                      //Thread.sleep(6000);
               //    CreateNewMediaType.click();
                     
                 if(sAction.equalsIgnoreCase("Delete")){
                     
                     
                     int RowCount = findAll(By.xpath("//table[@summary='Media Type']/tbody/tr")).size();
           
                     
                     for (int x=0;x<RowCount-1;x++) {
                           
                           CreateNewMediaType.click();
                           Thread.sleep(3000);
                           MediaTypeList.type(sMediatype);
                           Thread.sleep(3000);
                           
                            if(RowCount>=1){
                                  
                                  Assert.assertTrue("Delete Button is not Enabled", DeleteMediaTypeButton.isEnabled()); 
                                 //DeleteMediaTypeButton.isEnabled();
                                 DeleteMediaTypeButton.click();      
                                 
                           }
                           

                           Alert alert=getDriver().switchTo().alert();
                           String ActAlertText=alert.getText();
						    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
                           System.out.println("alert messsage is"+ActAlertText);
                           if(ActAlertText.contains(sPopup)){
                                 alert.accept();
                           }
                     }
                     
                 }
                 
               }    
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  			   
                     
         }
         @FindBy(xpath=".//li[@aria-label='Customer Comms Selected']/a")
         WebElementFacade CustomerComms;
         @FindBy(xpath=".//*[@title='Visibility']/select")
         WebElementFacade CustomerCommsDropDown;
         @FindBy(xpath=".//*[@aria-label='Customer Comms List:Menu']")
         WebElementFacade AccountSummaryMenuIcon;
         
         
         @Step
         public void ValidateUpdatedByField(String rowname) throws IOException, InterruptedException, AWTException{
             String filePath = "\\src\\test\\resources\\data\\Account.xls";
             String dataSheet = "ValidateUpdateByAllCustComms";
             String applet = "";
             String table = "";
             
             
             Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
             readWorkbook.testData(tableMap);
            
            if (tableMap.get("View").size()==0){
	           	report.Info(rowname+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
			   
             for (int i = 0;i < tableMap.get("View").size();i++) {
            	     String sLocateCol = tableMap.get("LocateCol").get(i);
                     String sLocateColValue = tableMap.get("LocateColValue").get(i);
                     //String UIName  = tableMap.get("UIName").get(i);
                     //String value  = tableMap.get("Value").get(i);
                     String Apletview = tableMap.get("Apletview").get(i);
                     String sView = tableMap.get("View").get(i);
                     
                     
                     String indexValue = "0";
          
                     if(Apletview.equalsIgnoreCase("Yes"))
        		     {
        		      applet = "//*[@title='Customer Comms List List Applet']";
        		      table = "//table[@summary='Customer Comms List']";
        		     }else if(Apletview.equalsIgnoreCase("activites")){
        		    	  applet = "//*[@title='Activities List Applet']";
        			      table = "//table[@summary='Activities']";
        		     }else{
        		    	  applet = "//*[@title='Activities List Applet']";
      			      table = "//table[@summary='Order Dashboard Details']";
        		     }
                     
                     if(!sView.equalsIgnoreCase("")){
                  	   
                  	   if(sView.equalsIgnoreCase("AccountSummaryCustComms")){
                  		   //AccountSummaryTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();  
                  		 Common.waitForPageLoad(getDriver());
                      	   AccountSummaryTab.click();
                      	   //AccountSummaryMenuIcon.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                      	 Common.waitForPageLoad(getDriver());
                      	   report.Info("Customer comms List applet is present");
                      	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                  	   }else
                  	   {
                  	   
                  	   if (CustomerComms.isCurrentlyVisible()){
                  		 CustomerComms.click();  
                  	   }
                  	   else if (!CustomerComms.isCurrentlyVisible()){
                  		 //Dropicon.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
                  		 Common.waitForPageLoad(getDriver());
                    	   Dropicon.selectByVisibleText("Customer Comms");
                    	   // Thread.sleep(30000);
                    	   Common.waitForPageLoad(getDriver());
                    	    
                  	   }
                  	 Common.waitForPageLoad(getDriver());
                  	  // Thread.sleep(5000);
                  	   CustomerCommsDropDown.selectByVisibleText(sView);
                  	   //Thread.sleep(4000);
                  	 Common.waitForPageLoad(getDriver());
                  	   
                  	   }
                     }

                    	String LocateColMessage = sLocateColValue+" Not Found";
                       if (!sLocateCol.equals("")){
                         common1.selectedRow = -1;
                        Assert.assertTrue(LocateColMessage, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
                        report.Info("Updated By field is present on customer comms view");
                        sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                       }
              
            }
             
         }

         @FindBy(xpath=".//*[@aria-label='First Level View Bar']")
         WebElementFacade Dropicon;
		 @Step
         public void ValidateCustComms(String rowname) throws IOException, InterruptedException, AWTException{
             String filePath = "\\src\\test\\resources\\data\\Account.xls";
             String dataSheet = "ValidateCustComms";
             String applet = "";
             String table = "";
             
             
             Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
             readWorkbook.testData(tableMap);
             
			 
			 if (tableMap.get("View").size()==0){
	           	report.Info(rowname+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
			   
			   
             for (int i = 0;i < tableMap.get("View").size();i++) {
            	     String sLocateCol = tableMap.get("LocateCol").get(i);
                     String sLocateColValue = tableMap.get("LocateColValue").get(i);
                     String UIName  = tableMap.get("UIName").get(i);
                     String value  = tableMap.get("Value").get(i);
                     String Apletview = tableMap.get("Apletview").get(i);
                     String sView = tableMap.get("View").get(i);
                     
                     
                     String indexValue = "0";
          
                     if(Apletview.equalsIgnoreCase("Yes"))
        		     {
        		      applet = "//*[@title='Customer Comms List List Applet']";
        		      table = "//table[@summary='Customer Comms List']";
        		     }else if(Apletview.equalsIgnoreCase("activites")){
        		    	  applet = "//*[@title='Activities List Applet']";
        			      table = "//table[@summary='Activities']";
        		     }else{
        		    	  applet = "//*[@title='Activities List Applet']";
      			      table = "//table[@summary='Order Dashboard Details']";
        		     }
                     
                     if(!sView.equalsIgnoreCase("")){
                    	   
                    	   if(sView.equalsIgnoreCase("AccountSummaryCustComms")){
                        	   AccountSummaryTab.isDisplayed();
                        	   AccountSummaryTab.click();
                        	   report.Info("Customer comms List apllet is present");
                    	   }else if(CustomerComms.isVisible())
                    	   {
                    	   //CustomerComms.isDisplayed();
                    	   //CustomerComms.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
                    		 Common.waitForPageLoad(getDriver());
                    	   CustomerComms.click();
                    	   Common.waitForPageLoad(getDriver());
                    	   //Thread.sleep(10000);
                    	   CustomerCommsDropDown.selectByVisibleText(sView);
                    	   Common.waitForPageLoad(getDriver());
                    	   //Thread.sleep(6000);
                    	   
                    	   }else{
                    		   Common.waitForPageLoad(getDriver());
                    		// Dropicon.withTimeoutOf(60,TimeUnit.SECONDS).waitUntilClickable();
                      	   Dropicon.selectByVisibleText("Customer Comms");
                      	    //Thread.sleep(30000);
                      	 Common.waitForPageLoad(getDriver());
                      	    CustomerCommsDropDown.selectByVisibleText(sView);
                         	   Thread.sleep(6000);
                    	   }
                       }

                    	String LocateColMessege = sLocateCol+"Not Found";
                       if (!sLocateCol.equals("")){
                         common1.selectedRow = -1;
                        Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, indexValue).equals("true"));
                       report.Info("Account Name is present on customer comms view");
                       }
              
            }
			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
             
         }
         
	
         @FindBy(xpath=".//*[@title='Account Details:SecureNet Care Portal']")
         WebElementFacade SecureNetCarePortalButton;
         @FindBy(xpath=".//*[@id='s_vctrl_div_tabScreen']/ul/li[10]/a")
         WebElementFacade OrdersTab;
	   
         @Step
         public void SecureNetPortalValidation() throws InterruptedException, IOException, AWTException{
        	 
        	 if (AccountSummaryTab.isCurrentlyVisible()){
	    		   AccountSummaryTab.click();	    		   
		   		}
		   		else {
	   			AccountTabsDRopDown.selectByVisibleText("Account Summary");	   			
		   		} 
        	 report.Info("Navigated to Account Summary page");
        	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	 SecureNetCarePortalButton.isDisplayed();
        	 report.Info("SecreNet care Portal is present under Account summary page");
        	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	 Common.waitForPageLoad(getDriver());
        	 //SecureNetCarePortalButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
        	 SecureNetCarePortalButton.click();
        	 Thread.sleep(2000);
        	 Set<String> windowhadle =  getDriver().getWindowHandles();
         
           System.out.println("*****Handles are*******");
           String handle[]=new String[windowhadle.size()];
           int j=0;
           for(String s:windowhadle)
           {
             System.out.println(s);
             handle[j]=s;
             j++;
           
           }
             //switch driver focus from parent window to child window
                   getDriver().switchTo().window(handle[1]);
                   getDriver().close();
                   getDriver().switchTo().window(handle[0]);

        	
        	 report.Info("SecreNet care Portal clicked successfully");
        	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	 Thread.sleep(2000);
        	 if(OrdersTab.isPresent()){
        	 OrdersTab.click();
        	 }else{
        		 AccountTabsDRopDown.selectByVisibleText("Orders");
        	 }
        	 report.Info("Navigated to Order Summary page");
        	 SecureNetCarePortalButton.isDisplayed();
        	 report.Info("SecreNet care Portal is present under Account summary page");
        	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	 SecureNetCarePortalButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
        	 SecureNetCarePortalButton.click();
        	 Thread.sleep(2000);
        	 Set<String> windows =  getDriver().getWindowHandles();
        	 
        	 String Windowhadles[]=new String [windows.size()];
        	 int k=0;
             for(String s:windows)
             {
               System.out.println(s);
               Windowhadles[k]=s;
               k++;
             
             }
        	
        	 getDriver().switchTo().window(Windowhadles[1]);
        	 getDriver().close();
        	 getDriver().switchTo().window(Windowhadles[0]);
        	 report.Info("SecreNet care Portal clicked successfully");
        	 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	
         }

       //payel  
         @FindBy(xpath="(.//a[text()='Service Requests'])[2]")
  		WebElementFacade ServiceRequestsTab;
  		@FindBy(xpath=".//button[@aria-label='Service Requests:New']")
  		WebElementFacade NewButtonSR;
  		@FindBy(xpath=".//td[contains(@aria-labelledby,'SR_Type')]")
  		WebElementFacade SRTypeTextBox;
  		@FindBy(xpath=".//*[@class='siebui-icon-dropdown']")
  		WebElementFacade SRTypeDropDown;
  		@FindBy(xpath=".//*[@aria-label='Pick Asset:OK']")
  		WebElementFacade OKButtonPickAssetApplet;
  		@FindBy(xpath=".//*[@aria-label='Service Requests:Menu']")
  		WebElementFacade MenuButtonSR;
  		@FindBy(xpath="(.//td[contains(@id,'Status')])[1]")
  		WebElementFacade SRStatusTextBox;
  		@FindBy(xpath="//span/ul/li[1]/a")
	    WebElementFacade SRUndo;  		
  		@FindBy(xpath=".//*[contains(text(),'Error message')]")
		WebElementFacade ErrorTitleBox;  
		@FindBy(xpath=".//div[contains(@class,'popup-info')]")
		WebElementFacade ErrorMsg; 
		@FindBy(xpath=".//button[contains(@class,'confirm-popup')]")
		WebElementFacade ErrorOkButton;
		
		@FindBy(xpath=".//*[@aria-label='Pick Service Request Owner:OK']")
		 WebElementFacade OKButtoninPickOwner;
		 @FindBy(xpath="(.//*[@aria-label='Pick Service Request Owner:Go'])[1]")
		 WebElementFacade GoButtoninPickOwner;	    
		 @FindBy(xpath=".//*[@aria-label='Pick Service Request Owner:Query']")
		 WebElementFacade QueryButtoninPickOwner;
		 @FindBy(xpath=".//*[@name='Last_Name']")
		 WebElementFacade LastNameTextBox;
  		 		
public void CreateServiceRequestviaAccount(String Action) throws InterruptedException, IOException, AWTException{
 			
 			//ContactsTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	         Common.waitForPageLoad(getDriver());
 			if (ServiceRequestsTab.isCurrentlyVisible()){
 				ServiceRequestsTab.click();
 				//MenuButtonSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
 				Common.waitForPageLoad(getDriver());
 			}
 			else {
 				AccountTabsDRopDown.selectByVisibleText("Service Requests");
 				//MenuButtonSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
 				Common.waitForPageLoad(getDriver());
 			}
 			        		
 				String filePath = "\\src\\test\\resources\\data\\Account.xls";
                String dataSheet = "CreateNewServiceRequest";
                
                
                Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
                readWorkbook.testData(tableMap);
				
				if (tableMap.get("Row").size()==0){
	           	report.Info(Action+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
                
                for (int i = 0;i < tableMap.get("Row").size();i++) {
                      String sValue = tableMap.get("Value").get(i);
                      String sUIName = tableMap.get("UIName").get(i);
                      String sSelectMenu = tableMap.get("SelectMenu").get(i);
                      String sClickNew = tableMap.get("ClickNew").get(i);
                      String Validate = tableMap.get("Validate").get(i);
                      String sPopup = tableMap.get("Popup").get(i);
                      String sButton = tableMap.get("Button").get(i);
                      Alert alert;
                      String ActAlertText="";
                      
                      String applet = "//*[@title='Service Requests List Applet']";
                      String table = "//table[@summary='Service Request List ( Account )']";
                  
	            if (sClickNew.equalsIgnoreCase("Yes")){
	            	//NewButtonSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	            	Common.waitForPageLoad(getDriver());
	          		NewButtonSR.click();
	          		Thread.sleep(2000);
	          		//MenuButtonSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable(); 
	          		Common.waitForPageLoad(getDriver());
	          		SRStatusTextBox.click();
	          		SRTypeTextBox.click();
	          		SRTypeDropDown.click();
	          		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	          		report.Info("Info, LOVs in SR Type drop down");
	            	
	            }
	            
	            if(Validate.equalsIgnoreCase("Disabled")){
					String FieldtoValidate = tableMap.get("UIName").get(i);
					String ElementXpath = ".//table[@summary='Service Request List ( Account )']/tbody/tr/following::td[contains(@id,'"+sValue+"')][1]";
											
					String TextXpath = ".//table[@summary='Service Request List ( Account )']/tbody/tr/following::td[contains(@id,'"+sValue+"')][1]/input";
					
					
					getDriver().findElement(By.xpath(ElementXpath)).click();
					String readonlyValue =getDriver().findElement(By.xpath(TextXpath)).getAttribute("aria-readonly");
					SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					if(readonlyValue.equalsIgnoreCase("true")){
						report.Info("The field "+FieldtoValidate+"is disabled as expected"); 
						return;
					}
					else{
						Assert.assertTrue("The field "+FieldtoValidate+"is not disabled as expected",readonlyValue.equalsIgnoreCase("true"));
						return;
					}
	            }
                                                
     if (sSelectMenu.equals("")){	 			             
 	    	  common1.selectedRow = 1;
 	      
 	    	  if (!sUIName.equals("")){
 	              common1.updateSiebList(applet,table, sUIName, sValue); 
 	              Thread.sleep(4000); 
 	             SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 	    	  }
 	    	 if (sUIName.contains("CTN")){
  	    		Thread.sleep(2000);
  	    		//OKButtonPickAssetApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
  	    		Common.waitForPageLoad(getDriver());
  	    		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
  	    		OKButtonPickAssetApplet.click();
 	             
 	    	  } 
 	    	if(sUIName.equalsIgnoreCase("OpenPopUp|Owner")){
    	  		//QueryButtoninPickOwner.withTimeoutOf(120,TimeUnit.SECONDS).isPresent();
 	    		Common.waitForPageLoad(getDriver());
            	 
 	            		applet = "//*[@title='Pick Service Request Owner List Applet']";
 	            		table = "//table[contains(@summary,'Pick Service Request Owner')]";
            	
 	            	QueryButtoninPickOwner.click();	
 	            	//LastNameTextBox.type(sValue);
 	            	common1.updateSiebList(applet,table, "Text|Last_Name",sValue);
	 	                GoButtoninPickOwner.click();
	 	            	
	 	            	int PickOwnerTableSize= findAll(By.xpath("//table[@summary='Pick Service Request Owner']/tbody/tr")).size();
	 	            	if(PickOwnerTableSize>1){
			   	           	  report.Info("Owner available: "+sValue);
	   	                   }
	   	             else{
	   	            	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	           	    Assert.assertTrue("No rows displayed after clicking on Go button- Owner: "+sValue+ " not available", PickOwnerTableSize>1);
	   	             }
	 	            SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 	            OKButtoninPickOwner.click();
            	 	}
 	    	if (!sPopup.equals("")){
 	    		Thread.sleep(1000);
 	    			
 	    	 
 		 	    	if(isAlertPresent(getDriver())){
 		                alert=getDriver().switchTo().alert();
 		                 ActAlertText=alert.getText();  
 		                SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                                                
 		               if (ActAlertText.contains(sPopup)){   
 		            	  
 		                     alert.accept();
 		                    report.Info("Pop did occurred : " +ActAlertText);
 		               }
 		               else {
                            report.Info("Pop did not occur");
                            Assert.assertTrue(false);
 		               }
 		               
 		               
 	    	  	if ((sValue.equalsIgnoreCase("Payments"))||(sValue.equalsIgnoreCase("Customer Relations"))){
 	    	  		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 	    	  		report.Info("Info, Service Request created ");
 	    	  	} 	    	 
 		 	 } 
 		 	    	
 		 	    	if(ErrorTitleBox.isCurrentlyVisible()){
 		    			String ErrorInfo= ErrorMsg.getText();
 		    			if(ErrorInfo.contains(sPopup)){	
 		    				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 		    				ErrorOkButton.click();
 		    				report.Info("The alert meaasage is:"+ErrorInfo);
 		    			}
 		    			else{
 		    				report.Info("The alert meassage not found:"+ErrorInfo);
 		    				Assert.assertTrue(false);
 		    			}
 		           	}
 	    }
 	    	
 	    	if (sButton.equalsIgnoreCase("Undo Button")){
 	    		Thread.sleep(1000);
 	    		//MenuButtonSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
 	    		Common.waitForPageLoad(getDriver());
 	    		MenuButtonSR.click();
 	    		Thread.sleep(1000);
 	    		SRUndo.click();
 	    		report.Info("Info, Service Request deleted ");	    		
 	    	}
     }
     else{
    	 			SRTypeDropDown.click();  
			 		List<WebElement> options = getDriver().findElements(By.xpath(".//div[@title='Service Account List']/ul[@role='combobox']/li")); 	        
			 	     List<String> text = new ArrayList<String>();
			 	       
		 	        for(int j=0; j<options.size(); j++) {
		 	            text.add(options.get(j).getText());
		 	            
		 	        } 
		 	        	Boolean  flag = false;
		 	        	Iterator<String> itr = text.iterator();
		 	            while(itr.hasNext()) {
		 	            	
		 	             String currentValue= itr.next();
		 	             if (!currentValue.equalsIgnoreCase("Customer Relations")){ 
		 	            	flag = true;	
		 	             }  					 	         
                }
		 	            Assert.assertTrue("Customer Relations found in list",flag);
			 	        report.Info("Info, Customer Relations not found in list");
    	}
     			
 	}
 }

	@FindBy(xpath=".//button[@aria-label='Service Request:New']")
	WebElementFacade NewButtonSRinAccountSummary;
	@FindBy(xpath=".//*[@aria-labelledby='s_5_l_Type s_5_l_altCombo']")
	WebElementFacade SRTypeTextBoxinAccountSummary;
	@FindBy(xpath=".//*[@class='siebui-icon-dropdown']")
	WebElementFacade SRTypeDropDowninAccountSummary;
	@FindBy(xpath=".//*[text()='Pick Service Request Owner']//following::input[@aria-label='Find']")
	WebElementFacade OwnerPopupQueryTextBox;	
	@FindBy(xpath=".//*[text()='Pick Service Request Owner']//following::input[@aria-label='Starting with']")
	WebElementFacade OwnerPopupStartingTextBox;	
	@FindBy(xpath=".//*[@title='Pick Service Request Owner:OK']")
	WebElementFacade OwnerOKButton;
	@FindBy(xpath=".//*[@aria-label='Service Request:Menu']")
	WebElementFacade MenuButtonSRAccountSummary;
	@FindBy(xpath=".//td[contains(@id,'l_SR_Number')]")
	WebElementFacade SRInstalledIDElement;
public void CreateServiceRequestviaAccountSummary(String Action) throws InterruptedException, IOException, AWTException{
	
	ContactsTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable(); 			
	if (AccountSummaryTab.isCurrentlyVisible()){
   AccountSummaryTab.click();	    		   
	}
	else {
	AccountTabsDRopDown.selectByVisibleText("Account Summary");	   			
	} 
	
	String filePath = "\\src\\test\\resources\\data\\Account.xls";
String dataSheet = "CreateNewServiceRequest";
String applet = "//*[@title='Service Request List Applet']";
String table = "//table[@summary='Service Requests']";

Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
readWorkbook.testData(tableMap);

if (tableMap.get("Row").size()==0){
       	report.Info(Action+" logical name not found in sheet.");
       	Assert.assertTrue(false);        	
       }

for (int i = 0;i < tableMap.get("Row").size();i++) {
      String sValue = tableMap.get("Value").get(i);
      String sUIName = tableMap.get("UIName").get(i);
      String sSelectMenu = tableMap.get("SelectMenu").get(i);
      String sClickNew = tableMap.get("ClickNew").get(i);
      String sPopup = tableMap.get("Popup").get(i);
      Alert alert;
      String ActAlertText="";
  
if (sClickNew.equalsIgnoreCase("Yes")){
	//NewButtonSRinAccountSummary.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	Common.waitForPageLoad(getDriver());
	NewButtonSRinAccountSummary.click();
	//SRTypeTextBoxinAccountSummary.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed(); 
	Thread.sleep(4000);
	String InstalledId=SRInstalledIDElement.getAttribute("title");
	Serenity.setSessionVariable("SRInstalledID").to(InstalledId);
	report.Info("SR ID Created : "+InstalledId);
	
	SRTypeDropDowninAccountSummary.click();
	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		report.Info("Info, LOVs in SR Type drop down");	           	
}
                               
if (sSelectMenu.equals("")){	 			             
 	  common1.selectedRow = 1;		 	      
 	  if (!sUIName.equals("")){
           common1.updateSiebList(applet,table, sUIName, sValue); 
           Thread.sleep(4000);
          SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 	  }
 	 if (sUIName.contains("Installed_Id")){
   		Thread.sleep(2000);
   		//OKButtonPickAssetApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
   		Common.waitForPageLoad(getDriver());
   		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
   		OKButtonPickAssetApplet.click();
           
  	  } 
 	if (sUIName.contains("OpenPopUp|Owner")){
   		//Thread.sleep(2000);
   		Common.waitForPageLoad(getDriver());
   		//OwnerOKButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
   		OwnerPopupQueryTextBox.type("User ID");
   		OwnerPopupStartingTextBox.typeAndEnter("test_agent_nba_04");
   		//OwnerOKButton.click();
   		report.Info("User login is changed from test_retail_1 to test_agent_nba_04"); 	             
  	  }
 	if (!sPopup.equals("")){
 		if(ErrorTitleBox.isCurrentlyVisible()){
			String ErrorInfo= ErrorMsg.getText();
			if(ErrorInfo.contains(sPopup)){	
				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				ErrorOkButton.click();
				report.Info("The alert meaasage is:"+ErrorInfo);
			}
			else{
				report.Info("The alert meassage not found:"+ErrorInfo);
				Assert.assertTrue(false);
			}
       	}
 	    	if(isAlertPresent(getDriver())){
                alert=getDriver().switchTo().alert();
                 ActAlertText=alert.getText();  
               SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                                                
               if (ActAlertText.contains(sPopup)){                            
                     alert.accept();
                   report.Info("Pop did occurred : " +ActAlertText);
               }
               else {
                 report.Info("Pop did not occur");
                 Assert.assertTrue(false);
               }
 	    	}
	}
 	  if ((sValue.equalsIgnoreCase("Payments"))||(sValue.equalsIgnoreCase("Customer Relations"))){
 	  	  report.Info("Info, Service Request created ");
 	  	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
 	  	}
 	 
		}     
}

}

public void VerifyCustComContacts(String row) throws IOException, AWTException, InterruptedException{
	String filePath = "\\src\\test\\resources\\data\\Account.xls";
    String dataSheet = "AddVerifyContacts";
    
    String applet = "//*[@title='Contacts List Applet']";
    String table = "//table[@summary='Contacts']";
    
    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(row, filePath, dataSheet);
    readWorkbook.testData(tableMap);
    
    for (int i = 0;i < tableMap.get("RowNo").size();i++) {          
      String sLocateCol = tableMap.get("LocateCol").get(i);
      String sLocateColValue = tableMap.get("LocateColValue").get(i);             
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
   
    }       
    
 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
}

@Step      
public void BilledProductServices(String action) throws IOException, InterruptedException, AWTException{
      
      Thread.sleep(5000);
      String filePath = "\\src\\test\\resources\\data\\Account.xls";
    String dataSheet = "UsedProductServices";
    String applet = "//*[@title='Billing Items List Applet']";
    String table = "//table[@summary='SIS OM Products & Services Root List (Billing) - Tiny']";

    
    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(action, filePath, dataSheet);
    readWorkbook.testData(tableMap);
	
	if (tableMap.get("LocateCol").size()==0){
	           	report.Info(action+" logical name not found in sheet.");
	           	Assert.assertTrue(false);        	
	           }
    
    for (int i = 0;i < tableMap.get("LocateCol").size();i++) {   
          
      String sLocateCol = tableMap.get("LocateCol").get(i);
      String sLocateColValue = tableMap.get("LocateColValue").get(i);
       
      String sIndex = tableMap.get("Index").get(i);
      String sUIName = tableMap.get("UIName").get(i);
      String sValue = tableMap.get("Value").get(i);
      //String sPopUp = tableMap.get("PopUp").get(i);
      
      int k=0;
       if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")){
             do{
               
               getDriver().navigate().refresh();
               Thread.sleep(2000);                 
               k++;
               }while(common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true") && (k<10));
       }
       
      if (sIndex.equals("")){
            sIndex = "0";
         }
     
         Thread.sleep(3000);
                
       String LocateColMessege = sLocateCol+"Not Found";             

       if (!sLocateCol.equals("")){
            common1.selectedRow = -1;
            Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
            report.Info(sLocateCol+" found"); 
            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

            }
       else{
             common1.selectedRow = 1;
       }
    
      if (!sUIName.equals("")){
          if (sValue.equals("AgreementID")){
                  sValue = Serenity.sessionVariableCalled("AgreementID").toString();
                  
            }
         common1.updateSiebList(applet,table, sUIName, sValue); 
         report.Info("Row updated: "+sUIName);
         sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
      }
}

}
@FindBy(xpath=".//*[@aria-label='Address Line 1']")
WebElementFacade AddressLine1TextBox;

public void AnonymousAddress(String action) throws IOException, AWTException, InterruptedException{
	String filePath = "\\src\\test\\resources\\data\\Account.xls";
    String dataSheet = "AnonymousAddress";
    String applet = "";
    String table = "";
    String tab="";
	  Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(action, filePath, dataSheet);
      readWorkbook.testData(tableMap);
      
      for (int i = 0;i < tableMap.get("Editable").size();i++) { 	  
    	  String sEditable = tableMap.get("Editable").get(i);
          String sExpectedAddressVal = tableMap.get("AddressValue").get(i);
          String sLocateCol = tableMap.get("LocateCol").get(i);
          String sLocateColValue = tableMap.get("LocateColValue").get(i);          
          String sIndex = tableMap.get("Index").get(i);
          String sUIName = tableMap.get("UIName").get(i);
          String sValue = tableMap.get("Value").get(i);
          String ActualValue="";
          if(sEditable.equalsIgnoreCase("Address Line 1")){
        	  AddressLine1TextBox.click();
        	  String sAddressVal = AddressLine1TextBox.getTextValue();
        	  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        	  if(sAddressVal.equalsIgnoreCase(sExpectedAddressVal)){
        		  report.Info("Address Value is :"+sAddressVal+" for anonymous customer for Address Line 1 as expected.");
        	  }
        	  else{
        		  Assert.assertTrue("Address Value is :"+sAddressVal+" for anonymous customer but it should be :"+sExpectedAddressVal,sAddressVal.equalsIgnoreCase(sExpectedAddressVal));
        	  }
          }  
          
          if(sEditable.equalsIgnoreCase("Profiles")){
	        	  if (ProfilesTab.isCurrentlyVisible())
	  			{
	  				ProfilesTab.click();
	  			}
	  			else
	  			{
	  				TabDropDown.selectByVisibleText("Profiles");
	  			} 
	        	 applet = "//*[@title='Billing profile List Applet']";
	        	 table = "//table[@summary='Billing profile']";
	        	 tab="Profiles";
          }	
          
          if(sEditable.equalsIgnoreCase("Credit Vetting")){
         	  if(CreditVettingTab.isCurrentlyVisible()){
					CreditVettingTab.click();				
				}
				else{
					AccountTabsDropDown.selectByVisibleText("Credit Vetting");					
				} 
        	  Thread.sleep(5000);
        	  if(isAlertPresent(getDriver())){
	        	  Alert alert=getDriver().switchTo().alert();
	  			  String ActAlertText=alert.getText();
	  			
	        	  if(ActAlertText.contains("IF A CREDIT VET IS REQUIRED"))
	  				{
	  				getDriver().switchTo().alert().accept();
	  				report.Info("Alert messsage is:"+ActAlertText);
	  				}
        	  }
        	  applet = "//*[@title='Address History List Applet']";
        	  table = "//table[@summary='Address History']";
        	  tab="Credit Vetting";
          }
          
          
          if(sEditable.equalsIgnoreCase("Addresses")){
        	  if(AddressesTab.isCurrentlyVisible()){
					AddressesTab.click();					
				}
				else{
					AccountTabsDropDown.selectByVisibleText("Addresses");
				}
        	  Common.waitForPageLoad(getDriver());
        	  //Thread.sleep(60000);
        	 applet = "//*[@title='Account Addresses List Applet']";
        	 table = "//table[@summary='Account Fraud Events List']";
        	 tab="Addresses";
          }
          	
             	if (sIndex.equals("")){
	               sIndex = "0";
	            }
	        	String LocateColMessege = sLocateCol+"Not Found";  
	        	if (!sLocateCol.equals("")){
	        		find(By.xpath(applet)).withTimeoutOf(350,TimeUnit.SECONDS).isCurrentlyVisible();
	                common1.selectedRow = -1;
	                Assert.assertTrue(LocateColMessege, common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));                     
	                report.Info(sLocateCol+" found"); 
	                sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

	                }
	           else{
	                 common1.selectedRow = 1;
	           }
	        	if (!sUIName.equals("")){
	 	             ActualValue=  common1.updateSiebList(applet,table, sUIName, sValue); 
	 	              Thread.sleep(4000); 	 
	 	             if(sEditable.equalsIgnoreCase("Profiles")){
	 	            	String cellPath = applet+table+"//tr[@id='"+common1.selectedRow+"']//td[contains(@id,'Address')]"; 
	 	            	find(By.xpath(cellPath)).click();
	 	             }
	 	             SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	 	             if(ActualValue.equalsIgnoreCase(sExpectedAddressVal)){
		        		  report.Info("Address Value is :"+ActualValue+" for anonymous customer under "+tab+" tab.");
		        		}
		        	  else{
		        		  Assert.assertTrue("Address Value is :"+ActualValue+" for anonymous customer under "+tab+" tab but it should be :"+sExpectedAddressVal,ActualValue.equalsIgnoreCase(sExpectedAddressVal));
		        	  }
	        	}
       
      }
}

@Step      
public void validateJobDetails(String action) throws IOException, InterruptedException, FindFailed, AWTException{
	
	   String filePath = "\\src\\test\\resources\\data\\Account.xls";
    String dataSheet = "ValidateCustComms";
    String applet = "//*[@title='Customer Comms List List Applet']";
    String table = "//table[@summary='Customer Comms List']";   	

    
    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(action, filePath, dataSheet);
    readWorkbook.testData(tableMap);
    Common.waitForPageLoad(getDriver());
    
    if (tableMap.get("LocateCol").size()==0){
    	report.Info(action+" logical name not found in sheet.");
    	Assert.assertTrue(false);        	
    }
    
    for (int i = 0;i < tableMap.get("LocateCol").size();i++) { 	  
 	     String sLocateCol = tableMap.get("LocateCol").get(i);
 	     String sLocateColValue = tableMap.get("LocateColValue").get(i);
 	     String sIndex = tableMap.get("Index").get(i);
 	  
 	 if (sIndex.equals("")){
		   sIndex = "0";
      }
 	  
 	 if (!sLocateCol.equals("")){
    	 common1.selectedRow = -1;
    	 Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true"));
         sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
         report.Info(sLocateCol+ "feild is displayed as expeted");								  							 
         }
    else{
          common1.selectedRow = 1;
    }

        }

}

@Step	                
public void ValidateDDdetails() throws IOException, AWTException, InterruptedException, ClassNotFoundException, SQLException {

	String OldDebitCard = "Yz24JK/GaaZey+EVETaiaw==";
	String OldBankNo  = "BXTr3pCQdYguXpyiF/eJ4w==";
	//String OldDebitCard = Serenity.sessionVariableCalled("X_VF_BANK_SORT_CODE0").toString();
	//String OldBankNo = Serenity.sessionVariableCalled("BK_ACCNT_NUM0").toString();
	Database.ExecuteDBQuery("SiebelAccNumDebitDetailsCheck");
	Assert.assertTrue("FAIL :  Expected value machtes with actual one" , !OldDebitCard.equalsIgnoreCase(Serenity.sessionVariableCalled("X_VF_BANK_SORT_CODE0").toString()));
	Assert.assertTrue("FAIL :  Expected value machtes with actual one" , !OldBankNo.equalsIgnoreCase(Serenity.sessionVariableCalled("BK_ACCNT_NUM0").toString()));		

}
}