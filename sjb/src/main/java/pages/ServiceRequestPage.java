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
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import pages.DatabasePage;

public class ServiceRequestPage extends PageObject {
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
	
	public static boolean isAlertPresent(WebDriver driver) {
	      try{
	            driver.switchTo().alert();
	            return true;
	      }catch(NoAlertPresentException ex){
	            return false;
	      }
	}

	@FindBy(xpath=".//*[@title='Service Requests']")
	  WebElementFacade ServiceRequestsTab;
	  
	  @FindBy(xpath=".//*[@aria-label='First Level View Bar']")
	  WebElementFacade HomepageTabDropdown;
	  
	  @FindBy(xpath="//select[@title='Visibility']")
	  WebElementFacade ServiceRequestList;
	  
	  @FindBy(xpath=".//*[@title='Service Requests List Applet']")
	  WebElementFacade ServiceRequestApplet;
	  
	 	@FindBy(xpath=".//*[@title='Service Requests:New']")
	    WebElementFacade NewServiceRequest;
	    
	    @FindBy(xpath=".//div[contains(@id,'INS_Product')]/following::td[contains(@id,'INS_Product')][1]")
	    WebElementFacade ServiceRequestTypeElement;
	    
	    @FindBy(xpath="(.//*[contains(@aria-labelledby,'INS_Product')])[1]/following-sibling::span")
	    WebElementFacade SRTypeDropDownIcon;
	    
	    @FindBy(xpath=".//div[contains(@id,'VF_SLA')]/following::td[contains(@id,'VF_SLA')][1]")
	    WebElementFacade SLAElement;
	    
	    @FindBy(xpath=".//*[@title='Pick Contact:OK']")
	    WebElementFacade ContactOKButton;
	    
	    @FindBy(xpath=".//*[@title='Pick Account:OK']")
	    WebElementFacade AccountOKButton;
	    
	    @FindBy(xpath=".//*[@title='Pick Asset:OK']")
	    WebElementFacade AssetOKButton;
	    
	    @FindBy(xpath=".//input[@name='SR_Number']")
	    WebElementFacade IDTextBox;

	    @FindBy(xpath=".//*[@aria-label='Resolution Comment']")
	    WebElementFacade ResolutionCommentTextBox;
	    
	    @FindBy(xpath=".//*[@aria-label='SR Resolution Code']")
	    WebElementFacade ResolutionCodeTextBox;
	    
	    @FindBy(xpath=".//*[@title='Service Requests:SR Resolved']")
	    WebElementFacade SRResolvedButton;
	    
	    @FindBy(xpath=".//*[@title='Service Requests:Amend/Work SR']")
	    WebElementFacade SRAmmendButton;
	    
	    @FindBy(xpath=".//*[@title='Service Requests Menu']")
	    WebElementFacade SRMenuButton;
	  
	    @FindBy(xpath="//span/ul/li[6]/a")
	    WebElementFacade SRSave;

	    @FindBy(xpath=".//*[@title='Service Requests Form Applet']")
	    WebElementFacade SRSavedApplet;
	    
	    @FindBy(xpath=".//div[contains(@id,'VF_SLA')]/following::td[contains(@id,'SR_Number')][1]")
	    WebElementFacade SRInstalledIDElement;  
	    
	    @FindBy(xpath=".//*[@title='Service Requests:New']//following::button[@title='Service Requests:Go'][1]")
	    WebElementFacade ServiceRequestGoButton;
	    
	    @FindBy(xpath="(.//*[@aria-label='Pick Contact:Go'])[2]")
	    WebElementFacade ContactGOButton;
	    
	    @FindBy(xpath=".//*[@aria-label='Pick Asset:OK']")
  		WebElementFacade OKButtonPickAssetApplet;
	    
	    @FindBy(xpath="(.//*[@aria-label='Find'])[2]")
  		WebElementFacade ContactAppletDropDown;
	    
	    @FindBy(xpath=".//*[@title='Pick Contact:Query']")
  		WebElementFacade QueryButtoninPickContact;
	    @FindBy(xpath="(.//*[@title='Pick Contact:Go'])[1]")
  		WebElementFacade GoButtoninPickContact;
	    
	    
	     public void CreateServiceRequestViaServiceRequest(String rowname) throws InterruptedException, IOException, AWTException{

	      	  String filePath = "\\src\\test\\resources\\data\\Account.xls";
	   		  String dataSheet = "CreateNewServiceRequest";
	   		  String applet = "";
	            String table = "";
	            Robot robot = new Robot();   
	   		    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
	   		    readWorkbook.testData(tableMap);
	   		    
	   		    for (int i = 0;i < tableMap.get("Row").size();i++) {		    	
	   	            String sValue = tableMap.get("Value").get(i);
	   	            String sClickNew = tableMap.get("ClickNew").get(i);
	   	            String sUIName = tableMap.get("UIName").get(i);
	   	            String sSave = tableMap.get("Save").get(i);
	   	            String sButton = tableMap.get("Button").get(i);
	   	            String sClickQuery = tableMap.get("ClickQuery").get(i);
	   	            String sSelectMenu = tableMap.get("SelectMenu").get(i);
	   	            String sSelectView = tableMap.get("SelectView").get(i);
	   	            Boolean  flag = false;
	   	            Boolean  CustomerRelationFlag = false;
	   	            String InstalledId = "";
	   	            String sPopup = tableMap.get("Popup").get(i);
		   	        Alert alert;
	                String ActAlertText="";	
	   	         if(!sSelectView.equals("")){
		   	        	if(ServiceRequestsTab.isVisible()){
	   			    		ServiceRequestsTab.click();
	   			    	}
	   			    	else{
	   			    		HomepageTabDropdown.selectByVisibleText("Service Requests");
	   			    	}
		   	        	Thread.sleep(3000);
	   			    	ServiceRequestList.withTimeoutOf(220,TimeUnit.SECONDS).isPresent();
	   			    	String selectedValue = ServiceRequestList.getSelectedValue();
	   			  
	   			    	if(!selectedValue.equalsIgnoreCase(sSelectView)){
	   			    	
	   			    		ServiceRequestList.selectByVisibleText(sSelectView);
	   				    	Thread.sleep(5000);
	   				    	SRMenuButton.withTimeoutOf(220,TimeUnit.SECONDS).waitUntilClickable();
	   			    	}
	   			    	
	   			    	
	   			    	if(ServiceRequestApplet.isPresent()){
	   			    		System.out.println("Service Request screen is displayed successfully for "+sSelectView+" view");
	   			    		report.Info("Service Request screen is displayed successfully for "+sSelectView+" view");
	   			    	}
	   			    	else{
	   			    		Assert.assertTrue("Service Request screen is not displayed successfully for "+sSelectView+" view", ServiceRequestApplet.isPresent());
	   			    		break;
	   			    	}
	   	            } 
	   	            
	   	            
	   	            if (sClickNew.equalsIgnoreCase("Yes")){	    	
	   			    	NewServiceRequest.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	   		    		NewServiceRequest.click();
	   		    		
	   		    		InstalledId=SRInstalledIDElement.getAttribute("title");
	   		    		Serenity.setSessionVariable("SRInstalledID").to(InstalledId);
	   		    		report.Info("SR ID Created : "+InstalledId);
	   		    		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   		    		   if (sUIName.equals("")){
	   		    			   ServiceRequestTypeElement.click();	
	   					       SRTypeDropDownIcon.click();
	   					    SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   			          	   report.Info("Info, LOVs in SR Type drop down");
	   		    		   }    		
	   	            }
	   	       
	   	            
	   	            
	   	            if(sButton.equalsIgnoreCase("SR Tab")){
	   	            	if(ServiceRequestsTab.isVisible()){
	   			    		ServiceRequestsTab.click();
	   			    	}
	   			    	else{
	   			    		HomepageTabDropdown.selectByVisibleText("Service Requests");
	   			    	}
	   	            }
	   	         if (sButton.equals("TypeDropDown")){
					       SRTypeDropDownIcon.click();
					       SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			          	   report.Info("Info, LOVs in SR Type drop down");
		    		   }
	   	            
	   	            common1.selectedRow = 1;
	   	            if (!sUIName.equals("")){
	   	            	  SLAElement.click();
	   	            	applet = "//*[@title='Service Requests List Applet']";
	   		            table = "//table[@summary='Service Request List']";
	   	            	  if(sUIName.equalsIgnoreCase("Text|VF_Sla_Calculated")){
	   	            		GregorianCalendar calendar = new GregorianCalendar();
	   	      	            calendar.add(calendar.DAY_OF_MONTH, 1);
	   	      	            Date tomorrow = calendar.getTime();
	   	      	            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");		        
	   	      	            String formattedDate = dateFormat.format(tomorrow);
	   	      	            sValue=formattedDate;
	   	            	  }
	   	            	  
	   	 	              String getText = common1.updateSiebList(applet,table, sUIName, sValue); 
	   	 	              Thread.sleep(4000);
	   	 	              
	   	 	              
	   	 	          if(sUIName.equalsIgnoreCase("OpenPopUp|Contact_Last_Name")){
	   	 	            	 ContactOKButton.withTimeoutOf(120,TimeUnit.SECONDS).isPresent();
		   	 	            	 if(sValue.equalsIgnoreCase("Account No")){
			   	 	            		applet = "//*[@title='Pick Contact List Applet']";
			   	 	            		table = "//table[contains(@summary,'Pick Contact')]";
		   	 	            	
				   	 	            	QueryButtoninPickContact.click();	
				   	 	            	String sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();  
							        	//String sAccountNo = "7000244469";	
				   	 	            	common1.updateSiebList(applet,table, "Text|Account_Number", sAccountNo);
				   	 	            	GoButtoninPickContact.click();
				   	 	            	
				   	 	            	int PickContactTableSize= findAll(By.xpath("//table[@summary='Pick Contact']/tbody/tr")).size();
				   	 	            	if(PickContactTableSize>1){
								   	           	  report.Info("Account Number available: "+sAccountNo);
						   	                   }
						   	             else{
						   	           	  Assert.assertTrue("No rows displayed after clicking on Go button- Account not "+sAccountNo+ "available", PickContactTableSize>1);
						   	             }
				   	 	            ContactOKButton.click();
		   	 	            	 	}
		   	 	            	
		   	 	            	 else{
		   	 	            		ContactOKButton.click();
		   	 	            	 }
	   	 	            	 
	   	 	              }
	   	 	              
	   	 	          if (sUIName.contains("CTN")){
		   	  	    		Thread.sleep(2000);
		   	  	    		OKButtonPickAssetApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		   	  	    		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		   	  	    		OKButtonPickAssetApplet.click();
	   	 	          }
	   	 	          
	   	 	             if(sUIName.equalsIgnoreCase("OpenPopUp|Account")){
	   	 	            	AccountOKButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	   	 	            	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	 	            	AccountOKButton.click();
	   	 	              }
	   	 	            if(sUIName.equalsIgnoreCase("OpenPopUp|Asset_Number")){
	   	 	            	AssetOKButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
	   	 	            	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	 	            	AssetOKButton.click();
	   	 	              }
	   	 	            report.Info("Action : "+sUIName+" has perfomed "+sValue);  
	   	 	            
	   	 	            if(sUIName.equalsIgnoreCase("CaptureTextValue|Status")){
	   	 	            	if(getText.equalsIgnoreCase(sValue)){
	   		 	            	report.Info("Status of SR is "+getText+" as expected");
	   		 	            }
	   	 	            	else{
	   	 	            		Assert.assertTrue("Status of SR "+getText+" does not match expected value",getText.equalsIgnoreCase(sValue));
	   	 	            	}
	   	 	            }
	   	 	           if(sUIName.equalsIgnoreCase("CaptureTextValue|Closed_Date")){
	   	 	        	   if(sValue.equalsIgnoreCase("Populated")){
	   		 	            	if(!getText.equals("")){
	   		 	            		report.Info("Closed date is populated as expected, closed date is"+getText);
	   		 	            	}
	   		 	            	else{
	   		 	            		Assert.assertTrue("Closed date is not populated as expected",!getText.equals(""));
	   		 	            	}
	   	 	        	   }
	   	 	            }
	   	 	          if(sUIName.equalsIgnoreCase("DrillDown|SR_Number")){ 	
		   	 	        	if(isAlertPresent(getDriver())){
	   	 		                alert=getDriver().switchTo().alert();
	   	 		                 ActAlertText=alert.getText();  
	   	 		                 SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                                                
	   	 		               if (ActAlertText.contains(sPopup)){                            
	   	 		                     alert.accept();
	   	 		               }
	   	 		               else {
	   	                            report.Info("Pop did not occur");
	   	                            Assert.assertTrue(false);
	   	 		               }
	   	 	        	  	  }
	   	 	            	SRSavedApplet.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
	   	 	            	if(SRSavedApplet.isVisible()){
	   	 	            		ResolutionCommentTextBox.click();
	   	 	            		report.Info("SR is saved successfully");
	   	 	            	}
	   	 	            	else{
	   	 	            		Assert.assertTrue("SR is not saved",SRSavedApplet.isVisible());
	   	 	            	}
	   	 	            }
	   	 	             	 	             
	   	 	    	  }
	   	            else{
	   	            	if(!sValue.equals("")){
	   	            		List<WebElement> options = getDriver().findElements(By.xpath(".//div[@name='_sweview']/ul[@role='combobox']/li")); 	        
	   			 	        List<String> text = new ArrayList<String>();
	   		 	       
	   				 	        for(int j=0; j<options.size(); j++) {
	   				 	            text.add(options.get(j).getText());		 	            
	   				 	        } 
	   				 	       Iterator<String> itr = text.iterator();
	   				 	      
	   			 	            while(itr.hasNext()) {		 	            	
	   			 	             String currentValue= itr.next();		 	             	 	            	 
	   				 	            if(currentValue.equalsIgnoreCase(sValue)){
	   				 	            	flag=true;	
	   				 	            	report.Info(currentValue+" is present in SR Type drop down" );
	   				 	            	System.out.println(currentValue+" is present in SR Type drop down"); 
	   				 	            	break;
	   				 	            }
	   			 	            }
	   				 	            
			 	            	   			 	           
	   			 	          Assert.assertTrue(sValue+" is not present in SR Type drop down",flag==true);
	   			 	         
	   	            	}	         
	   	            }  
	   	         if(sSelectMenu.equalsIgnoreCase("Validate")){
	   	        	 	List<WebElement> options = getDriver().findElements(By.xpath(".//div[@name='_sweview']/ul[@role='combobox']/li")); 	        
			 	        List<String> text = new ArrayList<String>();
		 	       
				 	        for(int j=0; j<options.size(); j++) {
				 	            text.add(options.get(j).getText());		 	            
				 	        } 
				 	       Iterator<String> itr = text.iterator();
		 	            while(itr.hasNext()) {
		 	            	String currentValue= itr.next();
			 	            if(currentValue.equalsIgnoreCase("Customer Relations")){
			 	            	CustomerRelationFlag=true;
			 	            }
		 	            }
		 	           report.Info("Customer Relation is not present in SR Type drop down");
		 	           Assert.assertTrue("Customer Relation is present in SR Type drop down",CustomerRelationFlag==false); 
	 	            }	   	        
	   	         
	   	            if(sSave.equalsIgnoreCase("yes")){		            	
	   	            	Thread.sleep(3000);	 
	   	            	SRMenuButton.click();
	   	            	Thread.sleep(1000);
	   	            	SRSave.click();
	   	            	//SLAElement.sendKeys(Keys.chord(Keys.CONTROL, "s"));
	   	            }
	   	            
	   	            if(sClickQuery.equalsIgnoreCase("Yes")){	            	
	   	            	Thread.sleep(3000);
	   	            	SRMenuButton.withTimeoutOf(220,TimeUnit.SECONDS).waitUntilClickable();
	   	            	SLAElement.sendKeys(Keys.chord(Keys.ALT, "q"));
	   	            	Thread.sleep(3000);
	   	            	report.Info("Search query clicked");
	   	        		String IDValue = Serenity.sessionVariableCalled("SRInstalledID").toString();
	   	        		IDTextBox.typeAndEnter(IDValue);
	   	        		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	        		Thread.sleep(1000);
	   	        		//ServiceRequestGoButton.click();	 
	   	        		report.Info("Go button clicked");
	   	        		SLAElement.click();
	   	            }
	   	            if(sButton.equalsIgnoreCase("SR Resolved")){
	   	            	ResolutionCodeTextBox.type("Answered Question");
	   	            	Thread.sleep(1000);
	   	            	ResolutionCommentTextBox.type("tested");
	   	            	report.Info("");
	   	            	SRResolvedButton.click();
	   	            	
	   	            	report.Info("SR Resolved button clicked");
	   	            	Thread.sleep(1000);
	   	            	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	            }
	   	         if(sButton.equalsIgnoreCase("SR Ammend")){	   	          
	   	            	SRAmmendButton.click();
	   	            	
	   	            	report.Info("SR Ammend button clicked");
	   	            	Thread.sleep(3000);
	   	            	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	   	            }  
	   	         if(!sPopup.equals("")){
	            	if(isAlertPresent(getDriver())){
	            	alert=getDriver().switchTo().alert();
                   ActAlertText=alert.getText(); 
                   SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                   if (ActAlertText.contains(sPopup)){                    	
                   	alert.accept();
                   	System.out.println("Message is: "+ActAlertText);
                   	report.Info("alert messsage is"+ActAlertText);
                   	System.out.println("Message is: "+ActAlertText);
                   	}                                 
	            	}  
	            	else{
	            		 Assert.assertTrue("Expected pop-up did not occur",isAlertPresent(getDriver()));
	            	}
	            }
	       }
	     }
	     
public void ServiceRequestFieldValidation(String rowname) throws IOException, AWTException, InterruptedException{
	 
	  String filePath = "\\src\\test\\resources\\data\\Account.xls";
		  String dataSheet = "CreateNewServiceRequest";
		  
		    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowname, filePath, dataSheet);
		    readWorkbook.testData(tableMap);
		    
		    for (int i = 0;i < tableMap.get("Row").size();i++) {		    	
	            String Value = tableMap.get("Value").get(i);
	            String Validate = tableMap.get("Validate").get(i);
	            String FieldtoValidate = tableMap.get("UIName").get(i);
    			String ElementXpath = ".//div[contains(@id,'VF_SLA')]/following::td[contains(@id,'"+Value+"')][1]";
    			String TextXpath = ".//div[contains(@id,'VF_SLA')]/following::td[contains(@id,'"+Value+"')][1]/input";
    			
    			if(Validate.equalsIgnoreCase("Disabled")){
    				getDriver().findElement(By.xpath(ElementXpath)).click();
    				String readonlyValue =getDriver().findElement(By.xpath(TextXpath)).getAttribute("aria-readonly");
    				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
    				if(readonlyValue.equalsIgnoreCase("true")){
    					report.Info("The field "+FieldtoValidate+"is disabled as expected");   					
    				}
    				else{
    					Assert.assertTrue("The field "+FieldtoValidate+"is not disabled as expected",readonlyValue.equalsIgnoreCase("true"));
    				}
    			}
    			
    		if(Validate.equalsIgnoreCase("Populated")){
    			getDriver().findElement(By.xpath(ElementXpath)).click();
				String getText =getDriver().findElement(By.xpath(ElementXpath)).getAttribute("title");
				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				if(!getText.equals("")){
	            		report.Info(FieldtoValidate+" is populated as expected, value is"+getText);
	            	}
	            	else{
	            		Assert.assertTrue(FieldtoValidate+" is not populated as expected",!getText.equals(""));
	            	}
    		}
    		
    		if(Validate.equalsIgnoreCase("Closed")){    			
				String getText =getDriver().findElement(By.xpath(ElementXpath)).getText();
				SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				if(getText.equals(Validate)){
	            		report.Info(FieldtoValidate+" is "+Validate+" as expected");
	            	}
	            	else{
	            		Assert.assertTrue(FieldtoValidate+" is not as expected, value is "+getText,getText.equals(Validate));
	            	}
    		}
    			
		    }
}

		@FindBy(xpath="//a[text()='Activity Plan']")
		WebElementFacade ActivityPlanTab; 
		@FindBy(xpath="//a[text()='Activities']")
		WebElementFacade ActivitiesTab; 
		@FindBy(xpath=".//*[@aria-labelledby='Owner_Group_Label']")
		WebElementFacade GroupTextBox;
		@FindBy(xpath=".//*[@aria-label='Service Requests:SR Resolved']")
		WebElementFacade SRResolveButton;
		@FindBy(xpath=".//*[@aria-labelledby='Status_Label']")
		WebElementFacade StatusTextBox;
		@FindBy(xpath=".//*[@aria-label='Service Requests:Customer Account']")
		WebElementFacade CustomerAccountButtoninSR;
		@FindBy(xpath=".//*[@aria-labelledby='Resolution_Comment_Label']")
		WebElementFacade ResolutionTextBox;
		@FindBy(xpath="(.//*[@data-tabindex='tabScreen4'])[2]")
		WebElementFacade MoreInfoTab;		
		@FindBy(xpath=".//*[@aria-label='HelpDesk Service Request:New Address']")
		WebElementFacade NewAddressButton;		
		@FindBy(xpath=".//*[@aria-describedby='s_3_l_Postal_Code']")
		WebElementFacade PostCodeElement;		
		@FindBy(xpath=".//*[@aria-labelledby='s_3_l_Postal_Code ']")
		WebElementFacade PostCodeTextBox;		
		@FindBy(xpath=".//*[@aria-label='Postcode']")
		WebElementFacade GetPostCodeValue;
		@FindBy(xpath=".//*[@aria-label='VF SR Address Association pick Applet:OK']")
		WebElementFacade OKButton;			
		@FindBy(xpath=".//*[@aria-label='Postcode']")
		WebElementFacade ComparePostCode;
		@FindBy(xpath=".//*[@aria-label='Activities:New']")
		WebElementFacade ActivitiesNewButton;
		@FindBy(xpath=".//*[@aria-label='Activities:Duplicate Activity']")
		WebElementFacade DuplicateActivityButton;
		@FindBy(xpath=".//*[@aria-labelledby='SRNumber_Label']")
		WebElementFacade SRIDTextBox;
		
		@FindBy(xpath=".//*[@aria-labelledby='Owner_Label']/following-sibling::span")
		WebElementFacade OwnerLabelOpenPopUpButton;
		@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
		WebElementFacade ProductDropdown;
		@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")
		WebElementFacade SearchText;
		@FindBy(xpath="(.//*[@title='Pick Service Request Owner:Go'])[2]")
		WebElementFacade GoButtoninPickOwnerApplet;
		
		@FindBy(xpath=".//*[@aria-label='Service Requests:Menu']")
		WebElementFacade SRMenuItemIcon;
		@FindBy(xpath="//span/ul/li[6]/a")
		WebElementFacade SaveRecord;
		@FindBy(xpath=".//*[@aria-labelledby='Abstract_Label']")
		WebElementFacade DescrptionTextBox;
		
public void ServiceRequestValiadtionInServiceRequest(String Action) throws InterruptedException, IOException, AWTException{
			
	  String filePath = "\\src\\test\\resources\\data\\Account.xls";
	  String dataSheet = "ServiceRequest_Activities";
	  
	  Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Action, filePath, dataSheet);
	  readWorkbook.testData(tableMap);
	  
	  String applet = "";
  	  String table = "";
	  
	  for (int i = 0;i < tableMap.get("Row").size();i++) {
		  	String sValue = tableMap.get("Value").get(i);
		  	String sUIName = tableMap.get("UIName").get(i);
	        String sLocateCol = tableMap.get("LocateCol").get(i);
	        String sLocateColValue = tableMap.get("LocateColValue").get(i);
	        String Index = tableMap.get("Index").get(i);   
	        String sGroupValidation = tableMap.get("GroupValidation").get(i);
	        String sStatusValidation = tableMap.get("StatusValidation").get(i);
	        String sNavigation = tableMap.get("Navigation").get(i);
	        String sActivityTemplateValidation = tableMap.get("ActivityTemplateValidation").get(i);
	        String sServiceRequestClick = tableMap.get("ServiceRequestClick").get(i);
	        String sClickNew = tableMap.get("ClickNew").get(i);
	        String sButtonValidation = tableMap.get("ButtonValidation").get(i);
	        String sPopup = tableMap.get("Popup").get(i);
	        Alert alert;
            String ActAlertText=""; 
	        
	        if(sServiceRequestClick.equalsIgnoreCase("Yes"))
	        {
		   		  applet = "//*[@title='Service Requests List Applet']";
		          table = "//table[@summary='Service Request List']";
		   	        	if(ServiceRequestsTab.isVisible()){
	   			    		ServiceRequestsTab.click();
	   			    	}
	   			    	else{
	   			    		HomepageTabDropdown.selectByVisibleText("Service Requests");
	   			    	}
						   common1.selectedRow = -1;
	        }
	        	        
	        if (!sGroupValidation.equals("")){
	        			SRIDTextBox.click();
	        			String InstalledId=SRIDTextBox.getTextValue();
				        Serenity.setSessionVariable("SRInstalledID").to(InstalledId);
				        report.Info("SR ID Created : "+InstalledId);
			        	ResolutionTextBox.click();
			          	String strGroup = GroupTextBox.getTextValue();
			          	Assert.assertTrue("Current Group value is : "+strGroup+" and does not match the expected value  : "+sGroupValidation,strGroup.equals(sGroupValidation));				          	
			          	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			          	report.Info("Pass, Current Group value is as expected : "+strGroup);	
	          } 
	        
	        if (!sStatusValidation.equals("")){
	        			SRIDTextBox.click();
			          	String strStatus = StatusTextBox.getTextValue();
			          	Assert.assertTrue("Current Status is : "+strStatus+" and does not match the expected value  : "+sStatusValidation,strStatus.equals(sStatusValidation));				          	
					    SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			          	report.Info("Pass, Current Status is as expected : "+strStatus);	
	          }
	              
	        if (sNavigation.equalsIgnoreCase("Activity plan")){	 
		           	   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		           	   applet = "//*[@title='Activity Plans List Applet']";
		           	   table = "//table[@summary='FS Template Activity Plan List']";
			   	
					   ActivityPlanTab.click();
					   Thread.sleep(4000);
					   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	  
	   		} 
	        
	        if (sNavigation.equalsIgnoreCase("Activities")){	 
		           	  CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		           	  applet = "//*[@title='Activities List Applet']";
		           	  table = "//table[@summary='Service Request Activity List']";
		   	
		           	   ActivitiesTab.click();
					   Thread.sleep(4000);
					   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   
					   if (sClickNew.equalsIgnoreCase("Yes")){
						   ActivitiesNewButton.click();
						   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
							SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					   }
				   
					   common1.selectedRow = -1;//need this
			      
			    	  if (sButtonValidation.equalsIgnoreCase("Duplicate Activity")){
			    		  DuplicateActivityButton.click();
			    		  CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			    		  report.Info("Duplicate Activity Button clicked");	  
					      SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			    	  }
	    	  	  
	   		} 
	   
			   if (sActivityTemplateValidation.equalsIgnoreCase("No Mandatory")){	
				   ResolutionTextBox.type("Resolved");
				   SRResolveButton.click();
				   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   Thread.sleep(4000);
				   String strStatus = StatusTextBox.getTextValue();
	               Assert.assertTrue("Current Status is : "+strStatus+" and does not match the expected value : CLOSED: ",strStatus.equals("Closed"));
					SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	               report.Info("Pass, Current Status is as expected : "+strStatus);
					           
			   		}
			   
			   if (sActivityTemplateValidation.equalsIgnoreCase("Mandatory")){	
				   ResolutionTextBox.type("Resolved");
				   SRResolveButton.click();
				   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   Thread.sleep(4000);
				   String strStatus = StatusTextBox.getTextValue();
					SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	               Assert.assertTrue("Current Status is : "+strStatus+" and does not match the expected value : CLOSED: ",strStatus.equals("Closed"));
	             	
	               report.Info("Pass, Current Status is as expected : "+strStatus);
			   		}
			   
			   if (sActivityTemplateValidation.equalsIgnoreCase("Disconnection Mobile")){
		           SRIDTextBox.click();
		           String InstalledId=SRIDTextBox.getTextValue();
			        Serenity.setSessionVariable("SRInstalledID").to(InstalledId);
			        report.Info("SR ID Created : "+InstalledId);
		           String ReadOnly = ResolutionTextBox.getAttribute("aria-readonly");
		           
		           if (ReadOnly.equals("false")){
		        	   ResolutionTextBox.type("Resolved"); 
		           }
		           DescrptionTextBox.type("abcdef");
				   SRResolveButton.click();
				   Thread.sleep(4000);
				   if(isAlertPresent(getDriver())){
		                alert=getDriver().switchTo().alert();
		                 ActAlertText=alert.getText();  
						  SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                                                
		               if (ActAlertText.contains(sPopup)){  
						            		
		                     alert.accept();
		               }
		               else {
                           report.Info("Pop did not occur");
                           Assert.assertTrue(false);
		               }
				   } 
				   
				   if (!sServiceRequestClick.equalsIgnoreCase("Pick Owner")){
					   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
					    SRIDTextBox.click();
			          	String strStatus = StatusTextBox.getTextValue();
			          	SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			          	Assert.assertTrue("Current Status is : "+strStatus+" and does not match the expected value : CLOSED: ",strStatus.equals("Closed"));				          	
			        	report.Info("Pass, Current Status is as expected : "+strStatus);
				   }
		           if (sServiceRequestClick.equalsIgnoreCase("Pick Owner")){
		        	   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		        	   OwnerLabelOpenPopUpButton.click();
		        	   Thread.sleep(2000);
		        	   GoButtoninPickOwnerApplet.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilVisible();
		        	   ProductDropdown.type("Last name");
		        	   SearchText.type("TEST_AGENT_NBA_04");
		        	   report.Info("TEST_AGENT_NBA_04 is selected for Last Name.");
						SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		        	   GoButtoninPickOwnerApplet.click();
		        	   
		        	   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
		        	 
		        	   SRMenuItemIcon.click();
		        	   Thread.sleep(2000);
		        	   SaveRecord.click();
		        	   Thread.sleep(2000);
		        	   
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
   }		   
    		   if (sLocateColValue.equalsIgnoreCase("LastName")){
   	    			sLocateColValue = Serenity.sessionVariableCalled("LastName").toString(); 
   	    			report.Info("Last Name "+sLocateColValue+" is to be located.");
   	    				
    			   
    		   }
		       if (Index.equals("")){
		    	   Index = "0";
		       	}
		       if (!sLocateCol.equals("")){
		           common1.selectedRow = -1;
                   String LocateColMessege = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index);
                   Assert.assertTrue(LocateColMessege, LocateColMessege.equals("true"));
                   
	                 
		       }    
	    	  if (!sUIName.equals("")){
	              common1.updateSiebList(applet,table, sUIName, sValue); 
	              
	              Thread.sleep(4000);
	              SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	           }
		    	
	    	  if (sNavigation.equalsIgnoreCase("Activity plan")){
				   ActivityPlanTab.click();
				   Thread.sleep(4000);
				   CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();	  
   		} 
		    	  
		        if (sNavigation.equalsIgnoreCase("More Info")){	 			             
				   	
	        	   MoreInfoTab.click();
				   Thread.sleep(4000);
				   NewAddressButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   NewAddressButton.click();
				   
				   PostCodeElement.click();
				   PostCodeTextBox.typeAndEnter("CA1 2AA");
				   SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				   OKButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				   OKButton.click();
				   SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				   String NewAddress = GetPostCodeValue.getTextValue();
  
			       ActivitiesTab.click();
			       CustomerAccountButtoninSR.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			       CustomerAccountButtoninSR.click();
			       
			       String PrimaryAddress = ComparePostCode.getTextValue();
			       SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			       Assert.assertTrue("Fail, Primary address is same as New Address",!NewAddress.equals(PrimaryAddress));
	  		}
		        
		        
	  }
	  
	}	  
		  

}