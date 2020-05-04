package pages;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import pages.DatabasePage;

public class PremisePage extends PageObject {
	private static final TimeUnit SECONDS = null;
	@Steps
    private ReadWorkbook readWorkbook;
	@Steps
	private common common;
	@Steps
	private SikuliUtility sikuliUtility;
	@Steps
	private DatabasePage Database;	
	@Steps
	ReportMessege report;
	@Step
	 public static boolean isAlertPresent(WebDriver driver) {
	      try{
	            driver.switchTo().alert();
	            return true;
	      }catch(NoAlertPresentException ex){
	            return false;
	      }
	}
	
	@FindBy(xpath=".//div[contains(@style,'display: none;')][ @id='maskoverlay']")
    WebElement Clockobj; 
	
	@FindBy(xpath=".//*[@aria-label='Line Items:BookAppointment']")
	 WebElementFacade BookAppointmentIcon;
	
    @FindBy(xpath="(.//*[text()='Book Appointment'])[1]")
	 WebElementFacade BookAppointmentApplet;
    
  
    @FindBy(xpath=".//*[@title='Book Appointment:Book Appointment']")
	 WebElementFacade BookAppointmentClick;
    
   
    @FindBy(xpath=".//*[@title='Line Items:Reserve Next Number']")
	 WebElementFacade ReserveNextNumbers;
    
    @FindBy(xpath=".//*[contains(text(),'Halo - please read')]")
	  WebElementFacade ErrorTitleBox;
	 @FindBy(xpath=".//div[contains(@class,'popup-info')]  ")
	  WebElementFacade ErrorMsg; 
	 @FindBy(xpath=".//button[contains(@class,'confirm-popup')]")
	  WebElementFacade ErrorOkButton;
			
	@Step
	 public void BookAppointment(String Action) throws IOException, InterruptedException, FindFailed, ClassNotFoundException, SQLException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Premise.xls";
		String dataSheet = "FilterPromotion";
		String applet = "//*[@title='Line Items List Applet']";
		String table = "//table[@summary='Order Entry - Line Item List (Sales)']";    

		Thread.sleep(4000);
		LineItemsTab.withTimeoutOf(180,TimeUnit.SECONDS).waitUntilClickable();
		LineItemsTab.click();     

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0;i < tableMap.get("LocateCol").size();i++) {

			String sRowNum = tableMap.get("RowNum").get(i);
			String sSelectItem = tableMap.get("SelectItem").get(i);

			String sReserveNextNumber = tableMap.get("ReserveNextNumber").get(i);	 			 	
			String sBookAppointment = tableMap.get("BookAppointment").get(i);
			String sPopup3 = tableMap.get("Popup3").get(i);
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("SelectItem").get(i);
			String sIndex = tableMap.get("Index").get(i);

			if (sIndex.equals("")){
				sIndex = "0";
			}
			String LocateColMessege = sLocateCol+"Not Found";


			if (sBookAppointment.equals("Y")){
				sLocateCol = "Product";
				// sLocateColValue = tableMap.get("SelectItem").get(i);
				if (!sLocateCol.equals("")){
					common.selectedRow = -1;
					Assert.assertTrue(LocateColMessege, common.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true")); 

				}

				BookAppointmentIcon.withTimeoutOf(180,TimeUnit.SECONDS).waitUntilClickable();
				BookAppointmentIcon.click();
				BookAppointmentApplet.withTimeoutOf(180,TimeUnit.SECONDS).isVisible();
				BookAppointmentClick.withTimeoutOf(180,TimeUnit.SECONDS).waitUntilClickable();
				BookAppointmentClick.click();
				Thread.sleep(10000);
				report.Info("BookAppointment Done");				   
			}



			if (sReserveNextNumber.equals("Y")){
				sLocateCol = "Product";
				//sLocateColValue = tableMap.get("SelectItem").get(i);
				if (!sLocateCol.equals("")){
					common.selectedRow = -1;
					Assert.assertTrue(LocateColMessege, common.locateColumn(applet,table, sLocateCol, sLocateColValue, sIndex).equals("true")); 

				}

				//if(ReserveNextNumbers.isEnabled()){


				ReserveNextNumbers.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				ReserveNextNumbers.click();
				report.Info("ReserveNextNumber Done");


				Thread.sleep(20000);
				int j;
				for(j=0;j<3;j++){
					if(!sPopup3.equalsIgnoreCase("No")){
						
						if(ErrorTitleBox.isCurrentlyVisible()&& (!sPopup3.equalsIgnoreCase("No"))){
							String ErrorInfo= ErrorMsg.getText();  
							String sPopup1=sPopup3;
							if (sPopup1.contains("OPTIONAL:")){
								sPopup1= sPopup1.substring(9);
							}
							if(ErrorInfo.contains(sPopup1)){	
								sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
								ErrorOkButton.click();
								report.Info("The alert meaasage is:"+ErrorInfo);
								if(ReserveNextNumbers.isCurrentlyEnabled()){
									ReserveNextNumbers.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
									ReserveNextNumbers.click();
									report.Info("ReserveNextNumber Done"); 	
									Thread.sleep(20000);
								}
							}

						}
						else if (!ErrorTitleBox.isCurrentlyVisible()&& (sPopup3.contains("OPTIONAL:"))){ 
							report.Info("OPTIONAL Pop did not occur");
							Assert.assertTrue(true);                                      
						}
						else if (!ErrorTitleBox.isCurrentlyVisible()&& (!sPopup3.equalsIgnoreCase("No"))){
							report.Info("Expected Pop did not occur");
							Assert.assertTrue(false);                                  
						}
						else if (ErrorTitleBox.isCurrentlyVisible()&& (sPopup3.equalsIgnoreCase("No"))){
							report.Info("UnExpected Pop did occured");
							Assert.assertTrue(false);                                  
						}

					}
				}

			}

		}
	}
				
   
	 @FindBy(xpath="//a[text()='Premise']")
	 WebElementFacade PremiseTab;
	 @FindBy(xpath=".//*[@title='Premise:Service Availability Check']")
	 WebElementFacade ServiceAvalCheckBtn;
	 @FindBy(xpath=".//*[@aria-label='Directory Number']")
	 WebElementFacade DirectoryNumber;
	 @FindBy(xpath=".//*[@aria-label='Availability Check Results:Use Existing Line']")
	 WebElementFacade UseExistingLine;
	 @FindBy(xpath=".//*[@title='Availability Check Results Menu']")
	 WebElementFacade GSAMenuIcon;
	 @FindBy(xpath=".//span/ul/li[9]/a")
	 WebElementFacade GSARunQuery;
	 @FindBy(xpath=".//span/ul/li[28]/a")
	 WebElementFacade GSAInstallNewLine; 
	 @FindBy(xpath=".//*[@aria-label='Line Info']")
	 WebElementFacade LineInfo;
	 @FindBy(xpath=".//*[@title='Service Points:Create Order']")
	 WebElementFacade CreateOrder;
	 @FindBy(xpath=".//input[@aria-label='Customer Type']")
	 WebElementFacade CustomerTypeTextBox;
	 
	 
	 
	 @Step
	 public void FilterPromotion(String Action) throws IOException, InterruptedException, FindFailed{
	 	String filePath = "\\src\\test\\resources\\data\\Premise.xls";
	     String dataSheet = "FilterPromotion";
	     String applet = "//*[@title='Availability Check Results List Applet']";
	     String table = "//table[@summary='Availability Check Results']";	     
	     
	     String sDirectoryNumber = Serenity.sessionVariableCalled("RetentionNumber").toString();	     
	     
	     Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
	     readWorkbook.testData(tableMap);
	     
	     for (int i = 0;i < tableMap.get("LocateCol").size();i++) {
	     	String sAction = tableMap.get("Action").get(i);	 		
	 		String sLineInfo = tableMap.get("LineInfo").get(i);
	 	   	String sLocateCol = tableMap.get("LocateCol").get(i);
	 		String sLocateColValue = tableMap.get("LocateColValue").get(i);
	 		String sNumRetn = tableMap.get("NumRetn").get(i);	 			 	
	 		String sPopup = tableMap.get("Popup").get(i);
	 		String sPopup2 = tableMap.get("Popup2").get(i);
	 		String sCustomerType = Serenity.sessionVariableCalled("CustomerType").toString();
	 		
	 			 	    
	 	   PremiseTab.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 	   PremiseTab.click();
		   
	 	   
	 	   if (sNumRetn.equalsIgnoreCase("Y")){
	 		  DirectoryNumber.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 		 DirectoryNumber.click();
	 		 DirectoryNumber.clear();
	 		 Thread.sleep(5000);
	 		 DirectoryNumber.type(sDirectoryNumber);
	 	   }
	 	   
	 	   else if (sNumRetn.equalsIgnoreCase("MSISDN")){
	 		 sDirectoryNumber = Serenity.sessionVariableCalled("MSISDN").toString();  
	 		 sDirectoryNumber= sDirectoryNumber.substring(3);
	 		 sDirectoryNumber = "0"+sDirectoryNumber;
	 		 DirectoryNumber.type(sDirectoryNumber);
	 	   }
	 	   
	 	  if(sCustomerType.equalsIgnoreCase("BUSINESS")){
	 		  common.waitForPageLoad(getDriver());
	 		  Thread.sleep(2000);
	 		 CustomerTypeTextBox.click();
	 		  CustomerTypeTextBox.clear();
	 		 CustomerTypeTextBox.type(sCustomerType);
	 	   }else{
	 		  Thread.sleep(2000);
	 		  CustomerTypeTextBox.click();
	 		 Thread.sleep(2000);
	 		  CustomerTypeTextBox.clear();
	 		 CustomerTypeTextBox.type("Individual");
	 	   }
	 	   
	 	   Thread.sleep(5000);
	 	   ServiceAvalCheckBtn.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	 	   ServiceAvalCheckBtn.click();
	 	   Thread.sleep(3000);
	 	   
	 	  Alert alert;
          String ActAlertText="";
               if(isAlertPresent(getDriver())&& (!sPopup.equalsIgnoreCase("No"))){
                     alert=getDriver().switchTo().alert();
                ActAlertText=alert.getText();  
                     if (sPopup.contains("OPTIONAL:")){
                    sPopup= sPopup.substring(9);
                  }                                                                                     
              if (ActAlertText.contains(sPopup)){ 
            	    Thread.sleep(3000);
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
                                      Assert.assertTrue(false);                            
                    }  

		 	  
		 	 int k=0;
			    if (common.locateColumn(applet,table, "Product_Type", "Null", "0").equals("true")){
			   	    do{			   	    	
				   	    ServiceAvalCheckBtn.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   	    Thread.sleep(5000);
				 	 	ServiceAvalCheckBtn.click();
				 	 	Thread.sleep(5000);
			   	    	k++;
		   	    	  }while(common.locateColumn(applet,table, "Product_Type", "Null", "0").equals("true") && (k<10));
			    }
		 	
			    if (common.locateColumn(applet,table, "Product_Type", "Null", "0").equals("true")){
			    	report.Info("No rows displayed after clicking on Service Avalability check button.");
			    	Assert.assertTrue(false);
			    }
	 		
	 		String Match = "N";	 		
	        String Index = "0";
	        int j=1;
	        String sActLineInfo= "";
	        do{
	        	         	        	       
	         if (!sLocateCol.equals("")){
	             common.selectedRow = -1;
	            Assert.assertTrue(common.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
	            report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

	             }
	        else{
	        	common.selectedRow = 1;
	        }
	         Index = Index+1;
	         String checkLineInfo="N";
	         
	         // Use Existing Line
	         if (sAction.equalsIgnoreCase("Use Existing Line")){
	        	if (UseExistingLine.isCurrentlyEnabled()){
	        		UseExistingLine.click();
	        		//report.Info("Use Existing Line Button is clicked.");
	        	}
	        	else if (!UseExistingLine.isCurrentlyEnabled()){
	        		report.Info("Use Existing Line Button is disabled.");
	        	}
	        		        	
	 		 	  if(isAlertPresent(getDriver())&& (!sPopup2.equalsIgnoreCase("No"))){
	 	    	   alert=getDriver().switchTo().alert();
	 	           ActAlertText=alert.getText();  
	 			    	 	                                                                              	
	 	           	if (ActAlertText.contains(sPopup2)){                		
	 	           	 alert.accept();
	 	           	 report.Info("Pop up messsage for Use Existing Line is "+ActAlertText+ " displayed successfully.");
	 	           	}                        
	 			   }
	 		 	checkLineInfo="Y";
	          }
	         
	         // Install New Line
	         if (sAction.equalsIgnoreCase("Install New Line")){
	        	 GSAMenuIcon.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	        	 GSAMenuIcon.click();
	        	 Thread.sleep(2000);				 
	        	 GSAInstallNewLine.click();
		        		        	
	 		 	  if(isAlertPresent(getDriver())&& (!sPopup2.equalsIgnoreCase("No"))){
	 	    	   alert=getDriver().switchTo().alert();
	 	           ActAlertText=alert.getText();  
	 			    	 	                                                                              	
	 	           	if (ActAlertText.contains(sPopup2)){                		
	 	           	 alert.accept();
	 	           	 report.Info("Pop up messsage for Install New Line is "+ActAlertText+ " dispalyed successfully.");
	 	           	}                        
	 			   }
		 		 	checkLineInfo="Y";
		       }
	                  	
	     	 if (checkLineInfo.equalsIgnoreCase("Y")){
	     		 getDriver().navigate().refresh();
	     		 sActLineInfo = LineInfo.getTextValue();
	     		 if (sActLineInfo.equalsIgnoreCase(sLineInfo)){
	     			report.Info("Line Info: " +sActLineInfo+" is same as exepected: " +sLineInfo);
	     			Match = "Y";
	     			CreateOrder.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	     			CreateOrder.click();
	     			break;
	     		 }
	     		 
	     	 }
	     	 if (Match.equalsIgnoreCase("N") && checkLineInfo.equalsIgnoreCase("Y")){
	     		report.Info("Line Info: " +sActLineInfo+" is not matching to exepected value: " +sLineInfo);
	     		Assert.assertTrue(false);
	     	 }
	     	 j++;
	        }while((!sActLineInfo.equalsIgnoreCase(sLineInfo)) && (j<3));
	     	
	        if (Match.equalsIgnoreCase("N")){
	        	report.Info("Line Info: " +sActLineInfo+" is not matching to exepected value: " +sLineInfo);
	        	Assert.assertTrue(false);
	        }
	     	

	 }
	   
	 }
	 
	 
	 @FindBy(xpath=".//*[@aria-labelledby ='VF_Primary_Fname_Label']")
   WebElementFacade PrimaryFirstName;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Alternate_Fname_Label']")
   WebElementFacade AletrnateFirstName;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Primary_Lname_Label']")
   WebElementFacade PrimaryLastName;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Alternate_Lname_Label']")
   WebElementFacade AletrnateLastName;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Primary_Phno_Label']")
   WebElementFacade PrimaryPhoneNo;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Alternate_Phno_Label']")
   WebElementFacade AletrnatePhoneNo;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Primary_Altphno_Label']")
   WebElementFacade PrimaryAltPhoneNo;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Alternate_Altphno_Label']")
   WebElementFacade AletrnateAltPhoneNo;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Primary_Email_Label']")
   WebElementFacade PrimaryEmail;
   @FindBy(xpath=".//*[@aria-labelledby ='VF_Alternate_Email_Label']")
   WebElementFacade AletrnateEmail;
  
	  @FindBy(xpath=".//*[@aria-label='Sales Order:Buyback']")
	  WebElementFacade BuyBackButton;
   	  @FindBy(xpath=".//*[@aria-label='Terms and conditions']")
      WebElementFacade TandCheckBox;
      @FindBy(xpath=".//*[@aria-label='Sales Order:Verify']")
      WebElementFacade VerifyButton;
      @FindBy(xpath=".//*[@aria-labelledby='Message_Text_Label']")
      WebElementFacade VerifyMessage;
      @FindBy(xpath=".//*[@aria-label='Messages:Close']")
      WebElementFacade VerifyMessageCloseButton;
      @FindBy(xpath=".//*[@aria-labelledby='Verified_Label']")
      WebElementFacade VerifyCheckBox;
      @FindBy(xpath=".//*[@aria-label='Sales Order:Submit']")
      WebElementFacade SubmitButton;
      @FindBy(xpath=".//*[@aria-label='Return reason']")
      WebElementFacade ReturnReasonTextBox;
      @FindBy(xpath=".//*[@aria-label='Disconnection reason']")
      WebElementFacade DisconnectionReasonTextBox;
      @FindBy(xpath=".//*[@ title='Sales Order:Show more']")
      WebElementFacade OrdersPageShowMoreButton;
      @FindBy(xpath=".//*[@aria-label='Verified']//preceding::*[@aria-label='Status']")
      WebElementFacade OrdersStatus;            
      @FindBy(xpath=".//*[@aria-label='Sales Order:Menu']")
      WebElementFacade OrderHeaderMenuButton;
      @FindBy(xpath="//span/ul/li[9]/a")
      WebElementFacade RefreshMenuItem;
      @FindBy(xpath=".//*[@title='Sales Order:Asset All']")
      WebElementFacade AssetAllButton;
      @FindBy(xpath="//a[text()='Line Items']")
      WebElementFacade LineItemsTab;
      @FindBy(xpath=".//*[@class='siebui-applet-title'][text()='Line Items']")
      WebElementFacade LineItemsApplet;
      @FindBy(xpath="//input[@aria-label='Terms and conditions']")
      WebElementFacade TermsandConditionsCheckBox;  


   
   
   
   @Step
   public void FLModifyOrderFormEntry(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException, SQLException{
         String filePath = "\\src\\test\\resources\\data\\Order.xls";
         String dataSheet = "FLModifyOrderFormEntry";
         String applet = "//*[@title='Line Items List Applet']";
         String table = "//table[@summary='Order Entry - Line Item List (Sales)']";
       
       
       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
       readWorkbook.testData(tableMap);
       
       for (int i = 0;i < tableMap.get("Popup").size();i++) {
        String sPopup = tableMap.get("Popup").get(i);
        String sLocateCol = tableMap.get("LocateCol").get(i);
        String sLocateColValue = tableMap.get("LocateColValue").get(i);
        String sDisconnectionReason = tableMap.get("DisconnectionReason").get(i);
        String sReturnReason = tableMap.get("ReturnReason").get(i);
        String sJourney = tableMap.get("Journey").get(i);
        String Index = tableMap.get("Index").get(i);
        
        Alert alert;
        String ActAlertText="";
        
        if (sPopup.equals("")){
      	  sPopup = "No";
        }
        
        
        if (Index.equals("")){
          	 Index = "0";
        } 
          	 
        if(!LineItemsApplet.isVisible()){
            LineItemsTab.click();
            //Thread.sleep(12000);
      }  
		
        TermsandConditionsCheckBox.click(); 
          	
          	
          	 //For Disconnection scenario            
            if (!sDisconnectionReason.equals("")){
                if (DisconnectionReasonTextBox.isCurrentlyVisible()){                                  
                	DisconnectionReasonTextBox.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilEnabled();                                   
                      DisconnectionReasonTextBox.type(sDisconnectionReason);
                      report.Info("Disconnection Reason entered : "+sDisconnectionReason);
                }
                else if (!DisconnectionReasonTextBox.isCurrentlyVisible()) {
                	OrdersPageShowMoreButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                      OrdersPageShowMoreButton.click();
                      DisconnectionReasonTextBox.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilEnabled();
                      DisconnectionReasonTextBox.type(sDisconnectionReason);
                      report.Info("Disconnection Reason entered : "+sDisconnectionReason);
                      }                                                     
                }
         
            
            if (!sLocateCol.equals("")){
                common.selectedRow = -1;
               Assert.assertTrue(common.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
               report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);
               
                }
            
            String sPrimaryFirstName = PrimaryFirstName.getTextValue();
            if (sPrimaryFirstName.equals("")){

            	String PrimaryFirstNamereadonly= PrimaryFirstName.getAttribute("aria-readonly");
            	if(!PrimaryFirstNamereadonly.equalsIgnoreCase("true")){
            		PrimaryFirstName.type("FIXED");
            	}
	
            }
            
            String sAletrnateFirstName = AletrnateFirstName.getTextValue();
            if (sAletrnateFirstName.equals("")){
            	
            	String AletrnateFirstNamereadonly= AletrnateFirstName.getAttribute("aria-readonly");
            	if(!AletrnateFirstNamereadonly.equalsIgnoreCase("true")){
            		AletrnateFirstName.type("FIXED1");
            	}
	
            }
            
            String sPrimaryLastName = PrimaryLastName.getTextValue();
            if (sPrimaryLastName.equals("")){
            	
               	String PrimaryLastNamereadonly= PrimaryLastName.getAttribute("aria-readonly");
            	if(!PrimaryLastNamereadonly.equalsIgnoreCase("true")){
                	PrimaryLastName.type("LINE");
            	}
            	
            }
            
            String sAletrnateLastName = AletrnateLastName.getTextValue();
            if (sAletrnateLastName.equals("")){
            	
              	String AletrnateLastNamereadonly= AletrnateLastName.getAttribute("aria-readonly");
            	if(!AletrnateLastNamereadonly.equalsIgnoreCase("true")){
                	AletrnateLastName.type("LINE1");
            	}
            	
            }
            
            String sPrimaryPhoneNo = PrimaryPhoneNo.getTextValue();
            if (sPrimaryPhoneNo.equals("")){
            	
            	String phonenumberreadonly= PrimaryPhoneNo.getAttribute("aria-readonly");
            	if(!phonenumberreadonly.equalsIgnoreCase("true")){
            		PrimaryPhoneNo.type("9999995642");
            	}
	
            }
            
            String sAletrnatePhoneNo = AletrnatePhoneNo.getTextValue();
            if (sAletrnatePhoneNo.equals("")){
            	
            	String AletrnatePhoneNoreadonly= AletrnatePhoneNo.getAttribute("aria-readonly");
            	if(!AletrnatePhoneNoreadonly.equalsIgnoreCase("true")){
                   	AletrnatePhoneNo.type("9999995641");
            	}
            	
            }
            
            String sPrimaryAltPhoneNo = PrimaryAltPhoneNo.getTextValue();
            if (sPrimaryAltPhoneNo.equals("")){
            	
            	String PrimaryAltPhoneNoreadonly= PrimaryAltPhoneNo.getAttribute("aria-readonly");
            	if(!PrimaryAltPhoneNoreadonly.equalsIgnoreCase("true")){
                	PrimaryAltPhoneNo.type("9999995643");
            	}
            	
            }
            
            String sAletrnateAltPhoneNo = AletrnateAltPhoneNo.getTextValue();
            if (sAletrnateAltPhoneNo.equals("")){
            	
            	String AletrnateAltPhoneNoreadonly= AletrnateAltPhoneNo.getAttribute("aria-readonly");
            	if(!AletrnateAltPhoneNoreadonly.equalsIgnoreCase("true")){
            		AletrnateAltPhoneNo.type("9999995644");
            	}
	
            }
            
            String sPrimaryEmail = PrimaryEmail.getTextValue();
            if (sPrimaryEmail.equals("")){
            	
            	String PrimaryEmailreadonly= PrimaryEmail.getAttribute("aria-readonly");
            	if(!PrimaryEmailreadonly.equalsIgnoreCase("true")){
                	PrimaryEmail.type("abc@sqcmail.uk");
            	}
            	
            }
            
            String sAletrnateEmail = AletrnateEmail.getTextValue();
            if (sAletrnateEmail.equals("")){
            	
            	String AletrnateEmailreadonly= AletrnateEmail.getAttribute("aria-readonly");
            	if(!AletrnateEmailreadonly.equalsIgnoreCase("true")){
                	AletrnateEmail.type("abc1@sqcmail.uk");
            	}
            	
            }
                         
    			VerifyButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();                                  
				VerifyButton.click();
                Thread.sleep(5000);
                if(isAlertPresent(getDriver())&& (!sPopup.equalsIgnoreCase("No"))){
                 alert=getDriver().switchTo().alert();
                  ActAlertText=alert.getText();  
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
                            Assert.assertTrue(false);                                  
                 }    
                if (VerifyMessage.isDisplayed()){
                    VerifyMessageCloseButton.waitUntilClickable();
                    VerifyMessageCloseButton.click();
                    report.Info("Verify Message close button Clicked");                                
                  }
                
                
            //Click Submit Button                
            SubmitButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
            SubmitButton.click();                                            
              if(isAlertPresent(getDriver())&& (!sPopup.equalsIgnoreCase("No"))){
                 alert=getDriver().switchTo().alert();
                 ActAlertText=alert.getText();  
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
                                  Assert.assertTrue(false);                            
                }  
              report.Info("Submit button Clicked");
                        
                   
               }

    } 
  
   @FindBy(xpath = ".//*[@title='All Premises:New Premise']")
   WebElementFacade NewPremiseButton; 
   @FindBy(xpath = ".//*[@title='New Premise:Go']")
   WebElementFacade NewPremiseGoButton;
   @FindBy(xpath = ".//*[@aria-label='Third Level View Bar']")
   WebElementFacade AccountDropDown; 
   @FindBy(xpath = ".//*[@aria-describedby='s_7_l_Post_Code']")
   WebElementFacade PostCodeElement; 
   @FindBy(xpath = ".//*[@name='Post_Code']")
   WebElementFacade PostCodeTextBox; 
   
   @FindBy(xpath = ".//*[@name='popup']")
   WebElementFacade NewPremiseApplet; 
   @FindBy(xpath = ".//*[@title='New Premise:Go']")
   WebElementFacade PremiseGoButton; 
   @FindBy(xpath = ".//*[@title='New Premise:Create Premise']")
   WebElementFacade CreatePremise; 
   
   @FindBy(xpath="(.//*[text()='All Premises'])[1]")
	WebElementFacade AllPremisesTab;
   
   
   public void CreateNewPremiseviaALLPremiseTab(String Action) throws IOException, InterruptedException, AWTException{
       String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";              
       String dataSheet = "Address";
       String applet = "//*[@title='New Premise List Applet']";
       
       String table = "//table[@summary='New Premise']";
       if(AllPremisesTab.isCurrentlyVisible()){
       	AllPremisesTab.click();
       	
       }
       
       NewPremiseButton.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible();
       NewPremiseButton.click();
       Thread.sleep(2000);
       
       Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
       readWorkbook.testData(tableMap);
       
       for (int i = 0;i < tableMap.get("AccountType").size();i++) {
           // String sCustomerType = tableMap.get("CustomerType").get(i);
          String sAddress = Serenity.sessionVariableCalled("ADDRESS").toString();
          /*String sPostCode = sAddress.substring(0,8);
          String sHouseNumber = sAddress.substring(9,11);*/
          String sPostCode;
          String sHouseNumber;
          String SAddressRef;
       /*   int Addreslength=sAddress.length();
          if(Addreslength==22) {
              sPostCode = sAddress.substring(0,6);
              sHouseNumber = sAddress.substring(7,9);
              }
              else if(Addreslength==23){
            	  sPostCode = sAddress.substring(0,7);
                  sHouseNumber = sAddress.substring(8,10);
              }else {
            	  sPostCode = sAddress.substring(0,8);
                  sHouseNumber = sAddress.substring(9,11);
              }*/
          
          String[] AddressArr = sAddress.split(Pattern.quote("|"));
          
          
			 sPostCode = AddressArr[0];
			 sHouseNumber = AddressArr[1];
			 SAddressRef = AddressArr[2];

       String sLocateCol = tableMap.get("LocateCol").get(i);
       String sLocateColValue = tableMap.get("LocateColValue").get(i);
       String UIName = tableMap.get("UIName").get(i);
       String value = tableMap.get("Value").get(i);
       String Index = tableMap.get("Index").get(i);
       
       if (Index.equals("")){
           Index = "0";
       }
       if (value.equalsIgnoreCase("PostCode")){
     	  value = sPostCode;
       }
       if (value.equalsIgnoreCase("HouseNumber")){
     	  value = sHouseNumber;
       }
       
       if (value.equalsIgnoreCase("AddressReference")){
     	  value = SAddressRef;
       }
       
       if (!sLocateCol.equals("")){
           common.selectedRow = -1;
          Assert.assertTrue(common.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
          sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
          report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
           }
      else{               
            common.selectedRow = 1;
      }      
      if (!UIName.equals("")){                 
           common.updateSiebList(applet,table, UIName, value); 
           sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
           Thread.sleep(2000);                                          
           report.Info("Row is successfully updated");                                                  
      }
    
    }

 PremiseGoButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
  PremiseGoButton.click();
  Thread.sleep(5000);
 
 CreatePremise.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
 CreatePremise.click();
 Thread.sleep(3000);
 
}
          
          
       

   public void CreateNewPremise(String Action) throws IOException, InterruptedException, AWTException{
         String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";              
         String dataSheet = "Address";
         String applet = "//*[@title='New Premise List Applet']";
         
       String table = "//table[@summary='New Premise']"; 
       if(PremiseTab.isDisplayed()){
           PremiseTab.click();
             }
             else{
                   AccountDropDown.selectByVisibleText("Premise");
             } 
         
         NewPremiseButton.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible();
         NewPremiseButton.click();
         
      Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
      readWorkbook.testData(tableMap);
      
      for (int i = 0;i < tableMap.get("AccountType").size();i++) {
           // String sCustomerType = tableMap.get("CustomerType").get(i);
          String sAddress = Serenity.sessionVariableCalled("ADDRESS").toString();
          /*String sPostCode = sAddress.substring(0,8);
          String sHouseNumber = sAddress.substring(9,11);*/
          String sPostCode;
          String sHouseNumber;
          String SAddressRef;
       /*   int Addreslength=sAddress.length();
          if(Addreslength==22) {
              sPostCode = sAddress.substring(0,6);
              sHouseNumber = sAddress.substring(7,9);
              }
              else if(Addreslength==23){
            	  sPostCode = sAddress.substring(0,7);
                  sHouseNumber = sAddress.substring(8,10);
              }else {
            	  sPostCode = sAddress.substring(0,8);
                  sHouseNumber = sAddress.substring(9,11);
              }*/
          
          String[] AddressArr = sAddress.split(Pattern.quote("|"));
          
			 sPostCode = AddressArr[0];
			 sHouseNumber = AddressArr[1];
			 SAddressRef = AddressArr[2];

          String sLocateCol = tableMap.get("LocateCol").get(i);
          String sLocateColValue = tableMap.get("LocateColValue").get(i);
          String UIName = tableMap.get("UIName").get(i);
          String value = tableMap.get("Value").get(i);
          String Index = tableMap.get("Index").get(i);
          
          if (Index.equals("")){
              Index = "0";
          }
          if (value.equalsIgnoreCase("PostCode")){
        	  value = sPostCode;
          }
          if (value.equalsIgnoreCase("HouseNumber")){
        	  value = sHouseNumber;
          }
          
          if (value.equalsIgnoreCase("AddressReference")){
        	  value = SAddressRef;
          }
            
            if (!sLocateCol.equals("")){
                common.selectedRow = -1;
               Assert.assertTrue(common.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
               sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
               report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
                }
           else{               
                 common.selectedRow = 1;
           }      
           if (!UIName.equals("")){                 
                common.updateSiebList(applet,table, UIName, value); 
                sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                Thread.sleep(2000);                                          
                report.Info("Row is successfully updated");                                                  
           }
         
         }
   
      PremiseGoButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	   PremiseGoButton.click();
	   Thread.sleep(5000);
	  
      CreatePremise.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
      CreatePremise.click();
      Thread.sleep(3000);
      
  }
   
}

         
