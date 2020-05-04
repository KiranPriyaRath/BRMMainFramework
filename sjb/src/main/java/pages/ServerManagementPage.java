package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;
import utilities.common;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class ServerManagementPage extends PageObject {
                @Steps
                private ReadWorkbook readWorkbook;
                @Steps
            	private common common1;
                @Steps
            	ReportMessege report;
                @Steps
            	private SikuliUtility sikuliUtility;
                
                @FindBy(xpath=".//*[@name='SiteMap']/span")
                WebElementFacade SiteMap;
                @FindBy(xpath=".//*[text()='My BIP Reports']//following::a[text()='Administration - User']")
                WebElementFacade AdministrationUser;
                //@FindBy(xpath=".//*[@id='_svf0']/div/ul/li[21]//.[text()='Employees']")
                //WebElementFacade EmployeesTab;
                @FindBy(xpath=".//*[@id='s_1_1_11_0_icon']")
                WebElementFacade EmployeeDropDown;
                @FindBy(xpath=".//li[text()='User ID']")
                WebElementFacade EmployeeDropDownItem;
                @FindBy(xpath=".//a[text()='Employees']")
                WebElementFacade EmployeesTab;
                @FindBy(xpath=".//*[@id='a_1']/div/div/input[1]")         
                WebElementFacade EmployeeList;
                @FindBy(xpath=".//*[@name='s_1_1_12_0']")
                WebElementFacade UserIDTextBox;
                @FindBy(xpath=".//*[@name='s_1_1_10_0']")
                WebElementFacade GoButton;
                @FindBy(xpath=".//*[@id='1_s_1_l_Position']")
                WebElementFacade Position;
                
                
                @Step
                public void NavigationToEmployees() throws InterruptedException, IOException, AWTException {
                                SiteMap.click();
                                Thread.sleep(3000);
                                AdministrationUser.click();
                                sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                Thread.sleep(5000);
                                
                                
                }
                
                @Step
                public void AdministratorDivision(String Division) throws InterruptedException, IOException {
                    String filePath = "\\src\\test\\resources\\data\\ServerManagement.xls";
                                String dataSheet = "Division";
                                //ReadWorkbook readWorkbook = new ReadWorkbook();
                                Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Division, filePath, dataSheet);
                                readWorkbook.testData(tableMap);
                                //Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
                                for (int i = 0  ;i < tableMap.get("User").size();i++){
                                                String Userfield = tableMap.get("User").get(i);
                                                String Divisionfield = tableMap.get("Division").get(i);
                                                Thread.sleep(5000);
                                                EmployeesTab.click();
                                                Thread.sleep(2000);
                                                EmployeeDropDown.click();
                                                EmployeeDropDownItem.click();
                                                Thread.sleep(3000);
                                                UserIDTextBox.sendKeys(Userfield);
                                                Thread.sleep(2000);
                                                GoButton.click();
                                                Thread.sleep(3000);
                                                
                                                String actPostion_Verify=Position.getTextValue();
                                                
                                                Assert.assertTrue("Postion value is not as expected" ,Divisionfield.equals(actPostion_Verify));
                                                
                     }
                                
                }
                
                
                @FindBy(xpath="(.//a[text()='Administration - Server Management'])[1]")
            	WebElementFacade AdminServerManagement;
            	@FindBy(xpath=".//a[text()='Jobs']")
            	WebElementFacade AdminServerManagementJob;
            	@FindBy(xpath=".//*[@class='siebui-button-toolbar siebui-closed']")
            	WebElementFacade Iconbar;
            	@FindBy(xpath=".//div[contains(@style,'display: none;')][ @id='maskoverlay']")
        	    WebElement Clockobj;
                

                @Step
               
                public void NavigationToJobs() throws InterruptedException, IOException, AWTException {

                		if(SiteMap.isCurrentlyVisible()){
                			//SiteMap.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                			common1.waitForPageLoad(getDriver());
                			SiteMap.click();
                			Thread.sleep(2000);

                		}else
                		{
                			//Iconbar.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible();
                			common1.waitForPageLoad(getDriver());
                			Iconbar.click();
                			Thread.sleep(2000);

                			SiteMap.click();
                			Thread.sleep(2000);
                		}
                		common1.WaitForClock(Clockobj);
                		
                		AdminServerManagement.click();
                		AdminServerManagementJob.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                		common1.waitForPageLoad(getDriver());
                		AdminServerManagementJob.click();
                		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                	}
                
              
                
                @FindBy(xpath=".//*[@aria-label='Jobs:New']")
                WebElementFacade NewJobcreate;
                
                @FindBy(xpath=".//*[@name='Job_Name']")
                WebElementFacade OpenPopUp;

                @FindBy(xpath=".//*[@title='Jobs Menu']")
                WebElementFacade JobMenu;
                @FindBy(xpath="//span/ul/li[9]/a")
                WebElementFacade RunQuery ;
                @FindBy(xpath=".//*[@aria-label='Jobs:Go']")
                WebElementFacade Gobutton;
                @FindBy(xpath=".//*[@title='Jobs:Submit Job']")
                WebElementFacade SubmitJobButton;
                @FindBy(xpath="//span/ul/li[10]/a")
            	WebElementFacade RefineQuery;
                @FindBy(xpath=".//*[@aria-label='Component/Job']")
            	WebElementFacade ComponentTextBox;
                @FindBy(xpath=".//td[contains(@aria-labelledby,'Status_Displayed')]")
            	WebElementFacade StatusField;
                
                
                @Step
                public void CreateNewJob(String UIName) throws InterruptedException, IOException, AWTException {
                    String filePath = "\\src\\test\\resources\\data\\ServerManagement.xls";
                    String dataSheet = "CreateNewJob";
                    String table = "//table[@summary='Jobs']";
                    String applet = "//*[@title='Jobs List Applet']";
                    
                   // if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")){
                    	//NewJobcreate.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible(); 			   	    
    			    //}

                    Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(UIName, filePath, dataSheet);
                    readWorkbook.testData(tableMap);
                    
                    for (int i = 0;i < tableMap.get("UIName").size();i++) {
                      String UIName1 = tableMap.get("UIName").get(i);
                      String Query = tableMap.get("Query").get(i);
                      String NewJob = tableMap.get("NewJob").get(i);
                      String Go = tableMap.get("Go").get(i);
                      String SubmitJob = tableMap.get("SubmitJob").get(i);   
                      String Value = tableMap.get("Value").get(i);
                      String Status = tableMap.get("Status").get(i);
                      
                      
                      
                      if  (NewJob.equals("Yes")){
          				//NewJobcreate.withTimeoutOf(120,TimeUnit.SECONDS).isPresent();
                    	  common1.waitForPageLoad(getDriver());
          				NewJobcreate.waitUntilClickable();
          				NewJobcreate.click();
          				report.Info("Creating New Jobs");
          				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
          				common1.WaitForClock(Clockobj);
          			}
                      
                      if  (Query.equals("Yes")){
          				JobMenu.click();
          				common1.WaitForClock(Clockobj);
          				RefineQuery.click();
          				report.Info("Query Clicked successfully");
          				common1.WaitForClock(Clockobj);
          				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
          				if (Value.equalsIgnoreCase("JobID")){
          					Value = Serenity.sessionVariableCalled("JobID").toString(); 
          				}   
          				
          			}
                      
                      if (Value.contains("Value")){
                      	  Value = Value.replace("JobId", Serenity.sessionVariableCalled("JobId").toString()); 
                        }
                        if (Value.equalsIgnoreCase("AgreementRowID")){
            				Value = Value.replace("AgreementRowID", Serenity.sessionVariableCalled("AgreementRowID").toString());
            				// report.Info("Query Clicked successfully");
            			}
            			if (Value.equalsIgnoreCase("ProductandServiceRowID")){
            				Value = Value.replace("ProductandServiceRowID", Serenity.sessionVariableCalled("ProductandServiceRowID").toString()); 
            			}

                      common1.selectedRow = 1;
                     
                      if (!UIName1.equals("")){
                    	  Thread.sleep(2000);
                    	  String IDvalue=common1.updateSiebList(applet,table, UIName1, Value);
          				  /*Serenity.setSessionVariable("ProductandServiceRowID").to(IDvalue);
          				  report.Info("Row Id : + IDvalue");*/
          				if(Value.equalsIgnoreCase("JobID")){
          					Serenity.setSessionVariable("JobID").to(IDvalue);
          					String ID = Serenity.sessionVariableCalled("JobID").toString();
          					report.Info("Job ID is : "+IDvalue); 
          					System.out.println(ID);
          				}
          				Thread.sleep(2000);
          				report.Info("Row updated successfully");
          				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                         
          			}
                          
                      
                      if  (Go.equals("Yes")){
          				//Gobutton.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible();
                    	  Thread.sleep(2000);
          				Gobutton.click();
          				report.Info("Go button Clicked successfully");
          				common1.WaitForClock(Clockobj);

          			}
                      
                      if  (SubmitJob.equals("Yes")){
          				SubmitJobButton.withTimeoutOf(120,TimeUnit.SECONDS).isCurrentlyVisible();
          				SubmitJobButton.click();
          				report.Info("Submit button Clicked successfully");
          				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
          				common1.WaitForClock(Clockobj);
          			}
                      
                      if(Status.equalsIgnoreCase("Yes")){
          				String ExpStatus=StatusField.getTextValue();
          				i=0;
          				do{
          					Thread.sleep(2000);
          					getDriver().findElement(By.xpath(".//*[@aria-label='Jobs:Menu']")).sendKeys(Keys.ALT,Keys.ENTER);
          					Thread.sleep(1000);
          					common1.WaitForClock(Clockobj);
          					ExpStatus=StatusField.getTextValue();
          					if(ExpStatus.equals("Success")){
          						report.Info("Job Status is Success");
          						break;
          					}
          					i++;
          				}while(!ExpStatus.equals("Success") && i<200);//do not change this counter as Post to pre Job takes time to complete
          				Assert.assertTrue("Status is "+ExpStatus+ " ",ExpStatus.equals("Success")); 
          				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                    	  
          			}  
                      
                    }

                    }
                @FindBy(xpath=".//*[@name='popup']")
            	WebElementFacade ComponentsJobPopUp;
            	@FindBy(xpath=".//*[@aria-labelledby='PopupQueryCombobox_Label']")
            	WebElementFacade ComponentsListApplet ;
            	@FindBy(xpath=".//*[@aria-labelledby='PopupQuerySrchspec_Label']")
            	WebElementFacade ComponentsListAppletvalue;
            	@FindBy(xpath=".//*[@title='Components/Jobs:OK']")
            	WebElementFacade ComponentOkButton;
                
                
                    
                    @Step
                    public void SelectJob(String UIName) throws InterruptedException, IOException, AWTException {
                        String filePath = "\\src\\test\\resources\\data\\ServerManagement.xls";
                        String dataSheet = "SelectJob";
                        String table = "//table[@summary='Components/Jobs']";
                        String applet = "//*[@title='Components/Jobs List Applet']";

                        Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(UIName, filePath, dataSheet);
                        readWorkbook.testData(tableMap);
                        
                        for (int i = 0;i < tableMap.get("RowNo").size();i++) {
                          String sFind = tableMap.get("Find").get(i);
                          String sStartWith = tableMap.get("StartWith").get(i);
                          String sLocateCol = tableMap.get("LocateCol").get(i);
                          String sLocateColValue = tableMap.get("LocateColValue").get(i);
                          String sUIName = tableMap.get("UIName").get(i);
           	    	      String sValue = tableMap.get("Value").get(i);
           	    	      String sIndex = tableMap.get("Index").get(i);
                          
                          if (sIndex.equals("")){
           	    		   sIndex = "0";
           	            }
                          
                              ComponentsJobPopUp.isCurrentlyVisible(); 
                              ComponentsListApplet.clear();
                              ComponentsListApplet.type(sFind);
                              //ComponentsListAppletvalue.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                              ComponentsListAppletvalue.typeAndEnter(sStartWith);
                              sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                              
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
                 	    		common1.updateSiebList(applet,table, sUIName, sValue);
                 	    		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                 	    	   }
                 	    	   
                 	    	   if (ComponentsJobPopUp.isCurrentlyVisible()){
                 	    		   Thread.sleep(2000);
                 	    		  ComponentOkButton.click();
                 	    		  sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());  
                 	    		 Thread.sleep(2000);
                 	    	   }
     
                        }
      }
                    


                    @Step
                    public void JobParameters(String UIName) throws InterruptedException, IOException, AWTException {
                        String filePath = "\\src\\test\\resources\\data\\ServerManagement.xls";
                        String dataSheet = "JobParameters";
                        String table = "//table[@summary='Job Parameters']";
                        String applet = "//*[@title='Job Parameters List Applet']";

                        Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(UIName, filePath, dataSheet);
                        readWorkbook.testData(tableMap);
                        
                        for (int i = 0;i < tableMap.get("Size").size();i++) {
                          String Size = tableMap.get("Size").get(i);
                          String sValue = tableMap.get("Value").get(i);
                          String UIName1 = tableMap.get("UIName").get(i);
                          String sLocateCol = tableMap.get("LocateCol").get(i);
                          String sLocateColValue = tableMap.get("LocateColValue").get(i);
                          
                          if (sValue.contains("[Id]='SRRowId'")){
                        	  sValue = sValue.replace("SRRowId", Serenity.sessionVariableCalled("AgreementID").toString()); 
                          }
                        	else if (sValue.contains("[Id]='RowId'"));{
                            sValue = sValue.replace("RowId", Serenity.sessionVariableCalled("AgreementID").toString());	  
                   		}
                                                    
                          
						if (!sLocateCol.equals("")){
                              common1.selectedRow = -1;
                             Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue,"0" ).equals("true"));
                             sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                              }
                         else{
                               common1.selectedRow = 1;
                         }
                         if (!UIName1.equals("")){                                              
                             common1.updateSiebList(applet,table, UIName1, sValue);
                             Thread.sleep(2000);
                             getDriver().findElement(By.xpath(".//*[@title='Jobs Menu']")).sendKeys(Keys.ALT,Keys.ENTER);
                             }
                          
                         sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                        }         
                    }
                    
                    
                    @FindBy(xpath="(.//a[text()='Administration - Data'])[1]")
                	WebElementFacade AdminData;
                    
                    @FindBy(xpath="(.//a[text()='List of Values'])[2]")
                	WebElementFacade ListOfValue;
                    @FindBy(xpath=".//*[@aria-label='List of Values:Query']")
                	WebElementFacade ListOfValueSearchbox;
                    
                    @FindBy(xpath=".//*[@aria-label='Pick Value Type:OK']")
                	WebElementFacade ListOfValueOkButton;
                    
                    
     
                    @Step
                    public void CreateListofValue(String UIName) throws InterruptedException, IOException, AWTException, Exception {
                        String filePath = "\\src\\test\\resources\\data\\ServerManagement.xls";
                        String dataSheet = "CreateNewJob";
                        String table = "//table[@summary='List of Values']";
                        String applet = "//*[@title='List of Values List Applet']";

                        Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(UIName, filePath, dataSheet);
                        readWorkbook.testData(tableMap);
                        
                        if(SiteMap.isCurrentlyVisible()){
                  			common1.waitForPageLoad(getDriver());
                  			SiteMap.click();
                  			Thread.sleep(2000);

                  		}else
                  		{
                  			
                  			common1.waitForPageLoad(getDriver());
                  			Iconbar.click();
                  			Thread.sleep(2000);

                  			SiteMap.click();
                  			Thread.sleep(2000);
                  		}
                  		common1.WaitForClock(Clockobj);
                  		
                  		AdminData.click();
                  		ListOfValue.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
                  		common1.waitForPageLoad(getDriver());
                  		ListOfValue.click();
                  		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                        
                        int k=0;
                        
                        for (int i = 0;i < tableMap.get("RowNo").size();i++) {
                          String sQuery = tableMap.get("Query").get(i);
                          String sUIName = tableMap.get("UIName").get(i);
           	    	      String sValue = tableMap.get("Value").get(i);
           	    	      String sIndex = tableMap.get("Index").get(i);
                          
                          if (sIndex.equals("")){
           	    		   sIndex = "0";
           	            }

                          if (!sQuery.equals("")) {
                        	  Thread.sleep(2000);
                        	  ListOfValueSearchbox.click();
                          }

                               common1.selectedRow = 1;
                                  
                               String TimeCapture = "";
             	    	   if (!sUIName.equals("")){
             	    		   Thread.sleep(2000);
             	    		   
             	    		  common1.updateSiebList(applet,table, sUIName, sValue);
             	    		  
             	    		   if (k==1) {
             	    		  TimeCapture=common1.updateSiebList(applet,table, sUIName, sValue);
             	    		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
             	    	   
             	    		   
                     	    	
             	    	   if (!TimeCapture.equals("")) {
             	    		   
             	    		    String[] Time = TimeCapture.split(Pattern.quote(" "));
             	    			String sTimeDate1 = Time[0];
             	    			String sTimeDate2 = Time[1];
             	           		report.Info("Date is :" +sTimeDate1);
             	           		report.Info("Time is :" +sTimeDate2);
             	           		
             	           		String UpdateTime = sTimeDate2;
             	           		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
             	           		Date d = df.parse(UpdateTime);
             	           		Calendar cal = Calendar.getInstance();
             	           		cal.setTime(d);
             	           		cal.add(Calendar.MINUTE, 10);
             	           		String newTime = df.format(cal.getTime());
             	           		report.Info("Time is :" +newTime);

             	           		String NewUpdatedTime = sTimeDate1.concat(" "+newTime);
             	           		report.Info("Time is :" +NewUpdatedTime);

             	           		Serenity.setSessionVariable("NewUpdatedTime").to(NewUpdatedTime);
	             	          
             	    	   }
             	    	   }
             	    	   }
             	    	   
             	    	  k++;

                            }
                        
          }
                     
}

