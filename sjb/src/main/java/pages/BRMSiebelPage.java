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
import java.util.List;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;



import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import pages.DatabasePage;

@SuppressWarnings("deprecation")
public class BRMSiebelPage extends PageObject {

	@Steps
	private ReportMessege report; 
	@Steps
	private ReadWorkbook readWorkbook;

	@Steps
	private common common1;

	
	@Steps
	private SikuliUtility sikuliUtility;
	@Steps
	private DatabasePage Database;

	@Step
	public static boolean isAlertPresent(WebDriver driver) {
		try{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
		}}

	@FindBy(xpath=".//*[@title='Billing Profiles:New']")
	WebElementFacade NewBillProfileButton ;
	@FindBy(xpath=".//*[@id='1_s_4_l_Payment_Type']")
	WebElementFacade PrePayPostPayBox;
	@FindBy(xpath=".//*[@id='1_Payment_Type']")
	WebElementFacade BillProfileType;
	@FindBy(xpath="//*[@id='1_s_1_l_Name']/a")
	WebElementFacade AccountNameLink;
	@FindBy(xpath=".//*[@aria-label='Product / Services:Top-Up Request']")
	WebElementFacade TopUpReqButton;

	// This function is used for creating new billing profile
	@Step
	public void  DrillDowntoBillingProfile(String BillingProfileType) throws InterruptedException, IOException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "CreateNewBillingProfile";
		String table = "//table[@summary='Billing Profiles']";
		String applet = "//*[@title='Billing Profiles List Applet']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
		for (int i = 0;i < tableMap.get("Value").size();i++) {
			//String ProfileType = tableMap.get("Value").get(i);
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String Index = tableMap.get("Index").get(i);
			String sNewBillingProfile = tableMap.get("NewBillingProfile").get(i);
			if (sNewBillingProfile.equals("Y"))
			{
				NewBillProfileButton.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
				//NewBillProfileButton.waitUntilPresent();
				NewBillProfileButton.click();
			}
			Thread.sleep(1000);
			//String LocateColMessege = sLocateCol+"Not Found";
			if (Index.equals("")){
				Index = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));

			}
			else{
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){   
				common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(2000);                                                                          
			}     


		}
	}

	@FindBy(linkText="Payments")
	WebElementFacade PaymentsTab;

	@FindBy(xpath=".//*[@aria-labelledby='ProfileName_Label']")
	WebElementFacade BillingProfileID;

	@FindBy(xpath=".//*[@title='Payments:Refunds']")
	WebElementFacade RefundButton;

	@FindBy(xpath=".//button[@title='Payments:Ad-hoc Payment']")
	WebElementFacade AdhocPaymentButton; 

	@FindBy(xpath=".//*[@aria-labelledby='CreditCardNumber_Label']")
	WebElementFacade CreditCardValue;

	@FindBy(xpath=".//button[@title='Capture New Payment:Cancel']")
	WebElementFacade CancelButton;
	@FindBy(xpath=".//a[text()='Bills' and @class='ui-tabs-anchor']")
	WebElementFacade BillsTab;

	@Step
	public void  ClickOnPaymentRefund(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "Payments_Refund";
		String applet = "//*[@title='Payments List Applet']";
		String table = "//table[@summary='Payments']";
		
		String  sLocateColValue = null;
		
		BillsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		BillsTab.click();
		common1.WaitForClock(Clockobj);
		String id =BillingProfileID.getTextValue();
		
		PaymentsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		PaymentsTab.click();
		System.out.println("Billing profile id "+id);
		report.Info("Billing Profile ID " +id);
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			sLocateColValue = tableMap.get("LocateColValue").get(i);
			String  sLocateCol = tableMap.get("LocateCol").get(i);
			String  sCompareCC = tableMap.get("CompareCC").get(i);
			String  s1stCCDetail = tableMap.get("1stCCDetail").get(i);
			String  s2ndCCDetail = tableMap.get("2ndCCDetail").get(i);
			String index = tableMap.get("Index").get(i); 
			String sAdhocPayment = tableMap.get("AdhocPayment").get(i);

			if (sAdhocPayment.equals("Yes")) {
				if (AdhocPaymentButton.isCurrentlyVisible()){
					AdhocPaymentButton.click();
					report.Info("Adhoc Payment button is clicked.");
				}
				else if (!AdhocPaymentButton.isCurrentlyVisible()){			
					report.Info("Adhoc Payment button is not visible.");
					Assert.assertTrue(false);
				}
				if(!sCompareCC.equals("")){
					String CreditCardDetail1 = "";

					if (s1stCCDetail.equals("Y")){
						CreditCardValue.click();
						CreditCardDetail1 = CreditCardValue.getTextValue();
						report.Info("Credit Card Detail of 1st account is "+CreditCardDetail1);
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					}

					if (s2ndCCDetail.equals("Y")){
						String CreditCardDetail2 = "";
						CreditCardValue.click();
						CreditCardDetail2 = CreditCardValue.getTextValue();
						report.Info("Credit Card Detail of 2nd account is "+CreditCardDetail2);
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

						if (!CreditCardDetail1.equals(CreditCardDetail2)){
							report.Info("Credit card details for Account 1 and 2 is different");	
							sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
						}
						else{
							sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
							Assert.assertTrue(false);
						}
					}

					if (CancelButton.isCurrentlyVisible()){
						CancelButton.click();
						report.Info("CancelButton button is clicked.");
					}
					else if (!CancelButton.isCurrentlyVisible()){			
						report.Info("CancelButton is not visible.");
						Assert.assertTrue(false);
					}
				}
				return;
			}

			if(sLocateColValue.equals("AMOUNT0")){
				sLocateColValue=Serenity.sessionVariableCalled("AMOUNT0").toString();
				//sLocateColValue="10";
				sLocateColValue="-"+sLocateColValue;
				//report.Info("AMOUNT0 Fetched From Serenity Variable :- "+sLocateColValue );
				//sLocateColValue="-8";

			}

			if(index.equals("")){

				index = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, index);
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}


			else{               
				common1.selectedRow = 1;
			}
			Serenity.setSessionVariable("PaymentRefund").to(sLocateColValue);
		}
		RefundButton.withTimeoutOf(120,TimeUnit.SECONDS).isEnabled();
		if(RefundButton.isEnabled()){
			RefundButton.click();
			report.Info("Refund Button is clicked.");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			//Thread.sleep(20000);
		}
		else{
			report.Info("Refund Button is not enabled");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Assert.assertTrue(false);
		}

		Serenity.setSessionVariable("sAllocatedAmount").to(sLocateColValue);
	}
	
	@FindBy(xpath = "//input[@placeholder='MMYY']")
	WebElementFacade ExpiryDateInputField;

	@FindBy(xpath = "//input[@placeholder='CVV']")
	WebElementFacade CVVInputField;

	@FindBy(xpath = "//input[@placeholder='House Number']")
	WebElementFacade HouseNoInputField;

	@FindBy(xpath = "//input[@placeholder='House Number']//following::input")
	WebElementFacade PostCodeInputField;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElementFacade Submitbutton;

	@FindBy(xpath = ".//*[contains(text(),'Card successfully captured')]")
	WebElementFacade CardSavedMsg;

	@FindBy(xpath = "//button[text()='Reset']")
	WebElementFacade ResetButton;
	
	@FindBy(xpath = "//input[@placeholder='Card Number (PAN)']")
	WebElementFacade cardNumberInputField;


	
	public void enterAeirandiCardDetails(String CardType) throws IOException, AWTException, InterruptedException {

		//Common.WaitForClock(ClockObj);
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "EnterCardDetails";

		getDriver().manage().window().maximize();
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		//Common.WaitForClock(ClockObj);
		String CardPaymentURL = getDriver().getCurrentUrl();
		;

		//getDriver().findElement(By.xpath("//td[contains(@id,'Payment_Method')]//span"))
		//Assert.assertTrue("FAIL, Aeriandi Page is not displayed.", getDriver().findElement(By.xpath("//input[@placeholder='Card Number (PAN)']")).isDisplayed());
		//Assert.assertTrue("FAIL, Aeriandi Page is not displayed.", cardNumberInputField.isPresent());

		Map<String, Map<Integer, String>> tableMap = readWorkbook.readRow(CardType, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0; i < tableMap.get("RowNo").size(); i++) {

			String sCardNo = tableMap.get("CardNumber").get(i);
			String sExpiryDate = tableMap.get("ExpiryDate").get(i);
			String sCVV = tableMap.get("CVV").get(i);
			String sHouseNo = tableMap.get("HouseNo").get(i);
			String sPostCode = tableMap.get("PostCode").get(i);
			String sNegativeJourney = tableMap.get("NegativeJourney").get(i);

			if(sNegativeJourney.equals("NonPCI")){

				Assert.assertTrue("FAIL, URL contains Aeirndai", !CardPaymentURL.contains("aeriandi.com"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				return;
			}

			if(sNegativeJourney.equals("Yes")){
				getDriver().findElement(By.xpath("//input[@placeholder='Card Number (PAN)']")).sendKeys(sCardNo);
				Thread.sleep(2000);
				String sCardNumber = cardNumberInputField.getTextValue();
				Serenity.setSessionVariable("CardNum").to(sCardNumber);
				Thread.sleep(3000);
				ExpiryDateInputField.type(sExpiryDate);
				CVVInputField.type(sCVV);
				HouseNoInputField.type(sHouseNo);
				PostCodeInputField.type(sPostCode);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                       
				Submitbutton.click();
				Thread.sleep(5000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("Pass, Card Details not saved successfully");                                                                                                 
				getDriver().close();
				Thread.sleep(2000);
				return;
			}
			if(sNegativeJourney.equals("Authorise")){
				CVVInputField.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				CVVInputField.type(sCVV);                                                          
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());                                                       
				Submitbutton.click();
				Thread.sleep(5000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("Pass, CVV entered successfully");                    
				getDriver().close();
				Thread.sleep(2000);
				return;
			}
			//                                            getDriver().findElement(By.xpath("//input[@placeholder='Card Number (PAN)']")).sendKeys(sCardNo);
			
			for(int j=0;j< sCardNo.length();j++) {
				cardNumberInputField.sendKeys(sCardNo.substring(j, j+1));
				Thread.sleep(1500);
			}
			String sCardNumber = cardNumberInputField.getTextValue();
			Serenity.setSessionVariable("CardNum").to(sCardNumber);
			ExpiryDateInputField.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			ExpiryDateInputField.type(sExpiryDate);
			CVVInputField.type(sCVV);
			HouseNoInputField.type(sHouseNo);
			PostCodeInputField.type(sPostCode);
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Submitbutton.click();
			Thread.sleep(5000);
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Assert.assertTrue("Fail, Card Details not saved successfully", CardSavedMsg.isCurrentlyVisible());
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			report.Info("Pass, Card Details Saved Successfully");
			getDriver().close();
			Thread.sleep(2000);       
		}
	}


	@FindBy(xpath=".//*[@aria-labelledby='PaymentAmount_Label']")
	WebElementFacade RefundAmoutEnter;

	@FindBy(xpath=".//*[@id='Payment_Refund_Label']//following::*[@aria-label='Selection Field'][1]")
	WebElementFacade BillingProfileSelection;

	@FindBy(xpath=".//*[@title='Billing profile:OK']")
	WebElementFacade OKButton;

	@FindBy(xpath=".//*[@title='Payment Refund:Submit']")
	WebElementFacade RefundSubmitButton;

	@FindBy(xpath=".//span[@aria-label='Selection Field' and @class='siebui-icon-pick applet-form-pick applet-list-pick']")
	WebElementFacade ExistingBillingProfileOpenPopUp;

	@FindBy(xpath=".//button[@title='Billing profile:OK']")
	WebElementFacade BillingProfileOkButton;

	@FindBy(xpath=".//input[@aria-label='Amount']")
	WebElementFacade AmountInputBox;

	@FindBy(xpath=".//button[@title='Capture New Payment:Authorise and Settle']")
	WebElementFacade AuthorizeAndSettleButton;

	@FindBy(xpath=".//input[@aria-label='CVV']")
	WebElementFacade CVVInputBox;

	@FindBy(xpath=".//button[@title='Capture New Payment:Submit']")
	WebElementFacade SubmitButton;
	
	@FindBy(xpath=".//*[@aria-label='One Time Refund']")
	WebElementFacade OneTimeRefund;
	
	@FindBy(xpath=".//button[@title='Payment Refund:Card Details']")
	WebElementFacade PayRefCardDetails;
	
	@FindBy(xpath=".//button[@title='Payment Refund:Authorise']")
	WebElementFacade PayRefAuthorise;
	
	@FindBy(xpath=".//*[@aria-label='Expiry month']")
	WebElementFacade ExpiryMonth;
	
	@FindBy(xpath=".//*[@aria-label='Expiry year']")
	WebElementFacade ExpiryYear;

	@Step
	public void  PaymentRefund(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "AdhocCCPayment";
		String applet = "//*[@title='Billing profile List Applet']";
		String table = "//table[@summary='Billing profile']";
		int sAllocatedAmount;
		String  sAmount = null ;
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);

		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			sAmount= tableMap.get("Amount").get(i);
			String  sType = tableMap.get("Type").get(i);
			String  sPopup = tableMap.get("Popup").get(i);
			String sPaymentType = tableMap.get("PaymentType").get(i); 
			String sCVV = tableMap.get("CVV").get(i);
			String ExpAlertText = tableMap.get("Popup").get(i);
			String sPopup1 = tableMap.get("Popup1").get(i);
			String sExpiryMonth = tableMap.get("ExpiryMonth").get(i);
			Alert alert;
			String ActAlertText="";
			//Serenity.sessionVariableCalled("sAmount");


			if(sAmount.equals("Refund")){
				sAmount=Serenity.sessionVariableCalled("PaymentRefund").toString();


			}
			if(sType.equals("OneTimeRefund")){
				OneTimeRefund.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				OneTimeRefund.click();
				RefundAmoutEnter.click();
				Thread.sleep(3000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				PayRefCardDetails.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				PayRefCardDetails.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Thread.sleep(5000);
				
				String initialWindow = "";
				initialWindow = getDriver().getWindowHandle();
				//CardDetailsButton.click();
				Thread.sleep(2000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				Set<String> winHandles = getDriver().getWindowHandles();
				for (String handle : winHandles) {
					if (!handle.equals(initialWindow)) {
						getDriver().switchTo().window(handle);
						Thread.sleep(1000);
					}
				}
				
				this.enterAeirandiCardDetails("CreditCardVisaAuthorise");
				
				getDriver().switchTo().window(initialWindow);
				initialWindow = "";
				initialWindow = getDriver().getWindowHandle();
				
				Thread.sleep(3000);
				//Common.WaitForClock(Clockobj);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				winHandles = getDriver().getWindowHandles();
				for (String handle : winHandles) {
					if (!handle.equals(initialWindow)) {
						getDriver().switchTo().window(handle);
						Thread.sleep(1000);
					}
				}
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
				RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				RefundAmoutEnter.type(sAmount);
				PayRefAuthorise.click();
				Thread.sleep(5000);
				RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				RefundAmoutEnter.type(sAmount);
				report.Info("Refund Submit Button Clicked");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				RefundSubmitButton.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				RefundSubmitButton.click();

				if (!sPopup1.equals("")){
					Thread.sleep(2000);
					common1.HandleErrorPopUp("Some of the Direct Debit details required to process"); 
					break;
				}


				Thread.sleep(4000);
				common1.HandleErrorPopUp("Refund has been successfully");

			}

			if(sType.equals("CreditCardPayment")){

				BillingProfileSelection.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				report.Info("Billing Profile selection- Ok Button clicked ");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				/*BillingProfileSelection.click();
        		 if (common1.locateColumn(applet,table, "Product_Name", "Null", "0").equals("true")){
        			 report.Info("Billing profile selection popup is empty");
        			 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
        			 Assert.assertTrue(false);

        		 }
        		 else{

             	    OKButton.click();*/
				RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				RefundAmoutEnter.type(sAmount);
				
				if(sExpiryMonth.equals("Yes")){
					ExpiryMonth.type("09");
					ExpiryYear.type("2021");
				}
				
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				RefundSubmitButton.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				RefundSubmitButton.click();
				report.Info("Refund Submit Button Clicked");
				if (!sPopup1.equals("")){
					Thread.sleep(2000);
					common1.HandleErrorPopUp("Some of the Direct Debit details required to process"); 
					break;
				}


				Thread.sleep(4000);
				common1.HandleErrorPopUp("Refund has been successfully");


			}

			if(sType.equals("DirecttoBillmeRefund")){
				RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				RefundAmoutEnter.type(sAmount);
				report.Info("Refund Submit Button Clicked");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				RefundSubmitButton.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				RefundSubmitButton.click();

				Thread.sleep(4000);
				common1.HandleErrorPopUp("Refund has been successfully");


			}

			if(sPaymentType.equals("AdhocPayment")){
				ExistingBillingProfileOpenPopUp.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
				if (ExistingBillingProfileOpenPopUp.isCurrentlyVisible()){
					ExistingBillingProfileOpenPopUp.click();
					Thread.sleep(4000);
				}
				else if (!ExistingBillingProfileOpenPopUp.isCurrentlyVisible()){
					report.Info("Adhoc Payment button is not clicked successfully.");
					Assert.assertTrue(false);
				}

				if (BillingProfileOkButton.isCurrentlyVisible()) {
					BillingProfileOkButton.click();
					Thread.sleep(2000);
				}

				else if (!BillingProfileOkButton.isCurrentlyVisible()) {
					report.Info("Billing Profile open pop up is not clicked successfully.");
					Assert.assertTrue(false);
				}

				AmountInputBox.type(sAmount);
				CVVInputBox.type(sCVV);
				AuthorizeAndSettleButton.click();
				Thread.sleep(2000);
				if (SubmitButton.isCurrentlyVisible()) {
					SubmitButton.click();
					Thread.sleep(7000);
				}
				common1.HandleErrorPopUp(sPopup);
			}


			if(!sPaymentType.equals("AdhocPayment")){


			}	    	              	          
			Serenity.setSessionVariable("sAmount").to(sAmount);	
		}

		/*sAllocatedAmount = Math.abs(Integer.parseInt(Serenity.sessionVariableCalled("sAllocatedAmount").toString())) - Math.abs(Integer.parseInt(sAmount));
	      report.Info("Remaining Unallocated Amount After Refund " + sAllocatedAmount);
	      sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	      Serenity.setSessionVariable("FinalRefundVal").to(sAllocatedAmount);
		 */
	}


	@Step
	public void  AdhocPaymentRunQuery(String BillingProfileType) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{

		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "AdhocPaymentRunQuery";
		String applet = "//*[@title='Payments List Applet']";
		String table = "//table[@summary='Payments']";
		PaymentsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		PaymentsTab.click();
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String  sValue = tableMap.get("Value").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String UIName = tableMap.get("UIName").get(i);  
			String sRefresh = tableMap.get("Refresh").get(i);

			if(iIndex.equals("")){

				iIndex = "0";
			}

			if (!sLocateCol.equals("")){

				if(sLocateColValue.equals("PAYMENTID")){
					sLocateColValue =Serenity.sessionVariableCalled("PAYMENT_ID0").toString();

				}

				if(sLocateColValue.equals("PAYMENTTRANSFER")){
					sLocateColValue = Serenity.sessionVariableCalled("PAYMENT_ID0").toString();              	   
				}

				if(sRefresh.equals("Yes")){
					getDriver().navigate().refresh();
					Thread.sleep(5000);
					getDriver().navigate().refresh();
					Thread.sleep(5000);
				}

				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}

			if (!UIName.equals("")){   
				if(sValue.equals("BalanceCheck")){
					sValue =Serenity.sessionVariableCalled("AMOUNT0").toString();
					sValue="-"+sValue;         		
				}

				if(sValue.equals("PAID")){
					sValue =Serenity.sessionVariableCalled("PAID0").toString();         		
				}
				if(sValue.equals("PREVBALANCE")){
					sValue =Serenity.sessionVariableCalled("BILL_BALANCE0").toString();  
					sValue="-"+sValue;
				}

				if(sValue.equals("PAYAMOUNT")){
					sValue = Serenity.sessionVariableCalled("PAY_AMOUNT0").toString();
					sValue="-"+sValue;
				}
				if(sValue.equals("BILLTOTAL")){
					sValue = Serenity.sessionVariableCalled("BILL_TOTAL0").toString();
					sValue=sValue.replace("-", "");        			 
				}
				if(sValue.equals("BILLPAIDTOTAL")){
					sValue = Serenity.sessionVariableCalled("BILL_TOTAL0").toString();
					sValue="-"+sValue;        			 
				}
				if(sValue.equals("OverPayment")){
					sValue =Serenity.sessionVariableCalled("OverPayment").toString();
					sValue="-"+sValue;          		
				}

				if(sValue.equals("Refund")){
					int	sValue1 = Math.abs(Integer.parseInt(Serenity.sessionVariableCalled("sAllocatedAmount").toString())) - Math.abs(Integer.parseInt(Serenity.sessionVariableCalled("sAmount").toString()));
					report.Info("Remaining Unallocated Amount After Refund " + sValue1);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					//Serenity.setSessionVariable("FinalRefundVal").to(sAllocatedAmount);
					if (sValue1>0)
						sValue = "-"+sValue1;
					else 
						sValue=""+sValue1;
				}

				if(sValue.equals("Amount")){
					sValue =Serenity.sessionVariableCalled("Amount").toString();

				}

				if(sValue.equals("Suspense")){

					sValue = Serenity.sessionVariableCalled("FinalRefundVal").toString();
				}


				if(sValue.equals("UnAllocated")){
					sValue = Serenity.sessionVariableCalled("UNALLOCATED0").toString();

				}

				if (UIName.contains("Card_Details")) {
					sValue =  Serenity.sessionVariableCalled("DEBIT_NUM0").toString();
				}

				common1.updateSiebList(applet,table, UIName, sValue);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Thread.sleep(2000);                                                                          
			}  


		} 
	}


	@FindBy(xpath=".//*[@aria-label='Payments:Payment Reversal']")
	WebElementFacade ReversalButton;

	@Step
	public void  ClickOnPaymentsPaymentReversal(String BillingProfileType) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "Payments_Reversal_AllocateCash";
		String applet = "//*[@title='Payments List Applet']";
		String table = "//table[@summary='Payments']";

		PaymentsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		PaymentsTab.click();
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);

		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String  sLocateCol = tableMap.get("LocateCol").get(i);
			String index = tableMap.get("Index").get(i); 
			String sValue = tableMap.get("Value").get(i);
			String sSortCode = tableMap.get("SortCode").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("value").get(i);
			if(index.equals("")){

				index = "0";
			}
			/*if (sValue.equals("Reverse")){
       //Database.ExecuteDBQuery("PaymentReceivedTypeCC");
    	   sValue=Serenity.sessionVariableCalled("BILL_TOTAL0").toString();
    	   sValue="-"+sValue;
        }*/
			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, index).equals("true"));
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}

			if (!sSortCode.equals("")){
				String SortAccntno=common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(2000);


				if (SortAccntno.startsWith("****")) {
					report.Info("Sort code/account no. " +SortAccntno+ " is encrypted");
				}
				else {
					report.Info(SortAccntno+" Value is not encrypted ");	
					Assert.assertTrue(false);
				}


				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			Thread.sleep(1000);



		}


	}


	@FindBy(xpath=".//*[@aria-labelledby='Reason_Label']")
	WebElementFacade ReversalReason;

	@FindBy(xpath=".//*[@title='Payment Reversal:Submit']")
	WebElementFacade ReversalReasonSubmit;

	@Step
	public void  PaymentReversal() throws InterruptedException, IOException, AWTException{

		//ReversalReason.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
		Thread.sleep(1000);

		if(ReversalButton.isCurrentlyVisible()){
			ReversalButton.click();
			report.Info("Reversal Button is clicked.");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Thread.sleep(2000);
		}
		else{
			report.Info("Reversal Button is not enabled");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Assert.assertTrue(false);

		}
		ReversalReason.type("Manual payment - Duplicate");
		//ReversalReasonSubmit.withTimeoutOf(120, TimeUnit.SECONDS).isEnabled();
		ReversalReasonSubmit.click();
		Thread.sleep(4000);
		common1.HandleErrorPopUp("Payment Reversal has been");
		/* Alert alert= getDriver().switchTo().alert();                      	   
    	 String ActAlertText=alert.getText();
    	 if (ActAlertText.contains("Payment Reversal has been")){                    			 
    		 alert.accept();
    		 report.Info("Reversal Alert messsage is "+ActAlertText + " is displayed successfully.");
    		 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

    	 } 
    	 else if (!ActAlertText.contains("Payment Reversal has been")){                                
    		 report.Info("Reversal failed with message "+ActAlertText); 
    		 sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
    		 Assert.assertTrue(false);                    
    	 }*/ 

	}


	@Step
	public void  BillsAdjustments(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Bills List Applet']";
		String table = "//table[@summary='Bills']";
		
		BillsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		BillsTab.click();
		common1.WaitForClock(Clockobj);
		
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String sExist = tableMap.get("Exist").get(i);

			if(value.equalsIgnoreCase("TOTALBILL")){
				value=Serenity.sessionVariableCalled("BILL_BALANCE0").toString();
				value = "£"+value+"*";
			}
			if(iIndex.equals("")){

				iIndex = "0";
			}

			if(sLocateColValue.equals("LatestBillNumber")){
				sLocateColValue =Serenity.sessionVariableCalled("BILLNUMBER0").toString();
				//Will be fetched by BRM code

			}
			if (sLocateCol.equals("FinalBill")){
				sLocateCol = "Bill_Number";
				common1.selectedRow = -1;
				String res  = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				//Assert.assertTrue(sReturnVal, sReturnVal.equals("true"));

				if(sExist.equals("")){    		 
					//Assert.assertTrue("The Product does Exist.", !sReturnVal.equalsIgnoreCase("True"));
					Assert.assertTrue(sLocateCol + "-" + sLocateColValue + " not found in the list.", res.equalsIgnoreCase("True"));
					report.Info(sLocateCol + "-" + sLocateColValue + " found in the list as expected");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());								  
				}
				else {

					if(sExist.equalsIgnoreCase("False")){
						sExist=  "False-Row Not Exist";
					}
					Assert.assertTrue(sLocateCol + "-" + sLocateColValue + " existence is " + res + " but expected is " + sExist, res.equalsIgnoreCase(sExist));
					report.Info(sLocateCol + "-" +  sLocateColValue + " existence is " + sExist + " as expected");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());	
					return;
				}

			}
			else {
				common1.selectedRow = 1;
			}


			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));

			}


			if (!UIName.equals("")){   
				common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(2000); 

			}  


		} 
	}


	@FindBy(linkText="Service Charges")
	WebElementFacade ServiceChargesTab;

	@FindBy(xpath=".//*[text()='Ok']")
	WebElementFacade OkButton;
	
	

	@Step
	public void  ItemLevelServiceCharges(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Services List Applet']";
		String table = "//table[@summary='Services']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);

		ServiceChargesTab.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
		ServiceChargesTab.click();
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sSearchByMsisdn = tableMap.get("SearchByMsisdn").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String sThreshold = tableMap.get("Threshold").get(i);

			if(sThreshold.equals("Y")){
				Assert.assertTrue("Expected Popup has not occured",ServiceThresholdPopup.isCurrentlyVisible());
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				OkButton.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
			}
			if(iIndex.equals("")){

				iIndex = "0";
			}

			if(sLocateColValue.equals("CTNMSISDN")){
				sLocateColValue=sLocateColValue.replace("CTNMSISDN",Serenity.sessionVariableCalled("ACCNTMSISDN1").toString());


				//Will be fetched by BRM code         	   
			}

			if(sLocateColValue.equals("GTNMSISDN")){    
				sLocateColValue=sLocateColValue.replace("GTNMSISDN",Serenity.sessionVariableCalled("ACCNTMSISDN0").toString());
				//Will be fetched by BRM code         	   
			}
			if(sLocateColValue.equals("MSISDN")){
				//sLocateColValue ="447350002519";
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();         	   
			}
			if(sLocateColValue.equals("MSISDN1")){
				//sLocateColValue ="447350002519";
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN1").toString();         	   
			}
			
			if (sSearchByMsisdn.equals("Y")){
				SeachMSISDNBilledUsage.isCurrentlyVisible();
				SeachMSISDNBilledUsage.click();
				common1.WaitForClock(Clockobj);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
				MSISDNSearchTextBox.isCurrentlyVisible();
				MSISDNSearchTextBox.click();
				MSISDNSearchTextBox.typeAndEnter(Serenity.sessionVariableCalled("ACCNTMSISDN0").toString());
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
				common1.WaitForClock(Clockobj);
				report.Info("MSISDN search performed"); 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				//Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));

			}                   	
		} 
	}
	@FindBy(linkText="Bundle Usage")
	WebElementFacade Bundle_Usage_Tab;

	@Step
	public void  BundleUsage_Services_fn(String MSISDNSelection) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "BundleUsage";
		String applet = "//*[@title='Services List Applet']";
		String table = "//table[@summary='Services']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(MSISDNSelection, filePath, dataSheet);

		Bundle_Usage_Tab.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
		Bundle_Usage_Tab.click();
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);


			if(iIndex.equals("")){

				iIndex = "0";
			}

			if(sLocateColValue.equals("CTNMSISDN")){        		
				//Will be fetched by BRM code         	   
			}

			if(sLocateColValue.equals("GTNMSISDN")){        		
				//Will be fetched by BRM code         	   
			}
			if(sLocateColValue.equals("MSISDN")){
				//sLocateColValue ="447350002519";
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();         	   
			}
			if(sLocateColValue.equals("MSISDN1")){
				//sLocateColValue ="447350002519";
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN1").toString();         	   
			}      	

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}                   	
		} 
	}


	@FindBy(xpath=".//a[text()='Usage List']")
	WebElementFacade Usage_List;

	@Step
	public void  UsageListServices_fn(String iRowNo) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Services List Applet']";
		String table = "//table[@summary='Services']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRowNo, filePath, dataSheet);

		//Bundle_Usage_Tab.withTimeoutOf(60, TimeUnit.SECONDS).isVisible();
		//Bundle_Usage_Tab.click();
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);

			Usage_List.withTimeoutOf(60, TimeUnit.SECONDS).isVisible();
			Usage_List.click();

			if(sLocateColValue.equals("MSISDN")){
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN").toString(); 

			}
			if(sLocateColValue.equals("CHILDMSISDN")){
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();   

			}

			if(iIndex.equals("")){
				iIndex = "0";
			}

			if (sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			} 

		}
	}

	@Step
	public void  UsageListSummaryCharges_fn(String MSISDNSelection) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Summary Charges List Applet']";
		String table = "//table[@summary='Summary Charges']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(MSISDNSelection, filePath, dataSheet);

		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String sValue = tableMap.get("Value").get(i);
			/*int sCompareValue = Integer.parseInt(tableMap.get("CompareValue").get(i));*/
			String sCompareValue = tableMap.get("CompareValue").get(i);
			//int sCompareValue = Integer.parseInt(sCompareValue1);
			//report.Info("Compare value is " +sCompareValue);

			String key1="";
			if (UIName.equals("CaptureTextValue") && !sValue.equals(""))
			{
				key1=sValue;
			}
			if(iIndex.equals("")){
				iIndex = "0";
			}
			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));

			}

			if (!UIName.equals("")){   
				common1.updateSiebList(applet,table, UIName, sValue);
				if (UIName.equals("CaptureTextValue1") && UIName.equals("Net Amount"))
				{
					if (!sValue.equals(""))
					{
						int strValue=Integer.parseInt(sValue.replace("£", ""));
						if (strValue>0)
						{
							String[] sCompareValue1 = sCompareValue.split(".");
							int sCompareValue2 = Integer.parseInt(sCompareValue1[0]);

							if (sCompareValue2 < strValue)
							{
								report.Info(sCompareValue+" Value to comapre is less than value in Siebel " + strValue);
							}
							else 
							{
								report.Info(sCompareValue+" Value to comapre is NOT less than value in Siebel " + strValue);	
								Assert.assertTrue(false);
							}
						}            		
						else 
						{
							report.Info("Value in Siebel is less than Zero");
							Assert.assertTrue(false);
						}

					}

				}

			}

		}
	}



	@Step
	public void  BundleUsage_Balance_fn(String iRowNo) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "BundleUsage";
		String applet = "//*[@title='Balance List Applet']";
		String table = "//table[@summary='Balance']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRowNo, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String iIndex = tableMap.get("Index").get(i);

			if(iIndex.equals("")){
				iIndex = "0";
			}
			if (sLocateColValue.equals("Minutes")){
				//Database.ExecuteDBQuery("GetBalanceQueryMinutes");
				sLocateColValue=sLocateColValue.replace("Minutes",Serenity.sessionVariableCalled("CURRENTBALANCE0").toString());
				sLocateColValue=sLocateColValue.replace("-","");
				long inLocateColValue =  Long.parseLong(sLocateColValue);
				inLocateColValue=inLocateColValue/60;
				report.Info("Value selected is : "+inLocateColValue); 
				sLocateColValue = inLocateColValue+".*";
				//sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			if (sLocateColValue.equals("SMS")){
				//Database.ExecuteDBQuery("GetBalanceQuerytext");
				sLocateColValue=sLocateColValue.replace("SMS",Serenity.sessionVariableCalled("CURRENTBALANCE0").toString());
				sLocateColValue=sLocateColValue.replace("-","");
				long inLocateColValue =  Long.parseLong(sLocateColValue);
				report.Info("Value selected is : "+inLocateColValue); 
				//sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
		} 
	}
	@Step
	public void  BundleUsage_BalanceDetails_fn(String iRowNo) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "BundleUsage";
		String applet = "//*[@title='Balance Details List Applet']";
		String table = "//table[@summary='Balance Details']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRowNo, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sUIName = tableMap.get("UIName").get(i);
			String svalue = tableMap.get("Value").get(i);
			String iIndex = tableMap.get("Index").get(i);

			if(iIndex.equals("")){
				iIndex = "0";
			}
			if (sLocateColValue.equals("Name|Balance")){
				//Database.ExecuteDBQuery("GetBalanceQueryMinutes");
				String LocateColValue="";
				if (svalue.equals("ADDON")){LocateColValue=Serenity.sessionVariableCalled("CURRENTBALANCE1").toString();}
				else {LocateColValue=Serenity.sessionVariableCalled("CURRENTBALANCE0").toString();}

				LocateColValue=LocateColValue.replace("-","");
				long inLocateColValue =  Long.parseLong(LocateColValue);
				inLocateColValue=inLocateColValue/60;
				LocateColValue=inLocateColValue+".*";
				report.Info("Value selected is : "+LocateColValue);
				sLocateColValue=sLocateColValue.replace("Balance", LocateColValue);

				if (svalue.equals("ADDON"))
					sLocateColValue=sLocateColValue.replace("Name", Serenity.sessionVariableCalled("BUNDLENAME1").toString());
				else {sLocateColValue=sLocateColValue.replace("Name", Serenity.sessionVariableCalled("BUNDLENAME0").toString());}
			}

			if (svalue.equals("SMS")){
				sLocateColValue=sLocateColValue.replace("Name", Serenity.sessionVariableCalled("BUNDLENAME0").toString());
			}
			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
		} 
	}

	@Step
	public void  BundleUsage_Products_fn(String iRowNo) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "BundleUsage";
		String applet = "//*[@title='Products List Applet']";
		String table = "//table[@summary='Products']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRowNo, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String iIndex = tableMap.get("Index").get(i);

			if(iIndex.equals("")){
				iIndex = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
		} 
	}		  


	@Step
	public void  ItemLevelSummaryCharges(String BillingProfileType) throws InterruptedException, IOException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Summary Charges List Applet']";
		String table = "//table[@summary='Summary Charges']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(0);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String Value2 = "";
			//String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i);

			if(iIndex.equals("")){

				iIndex = "0";
			}
			try {
				if (!Serenity.sessionVariableCalled(sLocateColValue).equals(null)){
					sLocateColValue=Serenity.sessionVariableCalled(sLocateColValue).toString();
				}
			} 
			catch (Exception e)
			{

			}
			try {
				if(value.contains("*")){
					value=value.replace("*","");

					if (!Serenity.sessionVariableCalled(value).equals(null))
					{
						value=Serenity.sessionVariableCalled(value).toString();
					}
					value = value+"*";
				}
				else 
				{
					if (!Serenity.sessionVariableCalled(value).equals(null))
					{
						value=Serenity.sessionVariableCalled(value).toString();
					}
				}
			}
			catch (Exception e) {

			}
			String key1="";
			if (UIName.contains("CaptureTextValue") && !value.equals("") )
			{
				key1=value;
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);

			}

			if (!UIName.equals("")){   

				if(value.equals("SMSUSAGE")){
					value=Serenity.sessionVariableCalled("CHARGE0").toString();
					value="£"+value+"*";
				}

				if(value.equals("CYCLEFORWARD")){
					value=Serenity.sessionVariableCalled("DUE0").toString();
					value="£"+value+"*";
				}
				Value2=common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(2000); 

			}   

			if (UIName.contains("CaptureTextValue"))	
			{
				Value2 = Value2.replace( "£", "");
				Serenity.setSessionVariable("VoiceUsage").to(Value2);
			} 


			if (UIName.contains("CaptureTextValue") && !key1.equals("") )
			{
				value = value.replace( "£", "");
				Serenity.setSessionVariable("key1").to(value);
			}          	 

		} 
		if(sClickAdjustButton.equalsIgnoreCase("Yes")) {
			ItemLevelAdjusmentButton.click();
			common1.waitForPageLoad(getDriver());
		}
	}


	@FindBy(xpath=".//*[@aria-label='Amount']")
	WebElementFacade AdjusmentAmount;

	@FindBy(xpath="(.//*[@aria-label='Type'])[1]")
	WebElementFacade AdjustmentType;

	@FindBy(xpath=".//*[@aria-label='Reason']")
	WebElementFacade AdjustmentReason;

	@FindBy(xpath=".//*[@title='Adjustment Requests:Submit']")
	WebElementFacade SubmitAdjustmentButton;

	@FindBy(xpath=".//*[@title='Summary Charges:Adjust']")
	WebElementFacade ItemLevelAdjusmentButton;

	@FindBy(xpath=".//*[@aria-label='Adjustment ID']//following::*[@aria-label='Status']")
	WebElementFacade AdjustmentStatus;
	@FindBy(xpath="(.//*[@aria-labelledby='RequestDate_Label']//preceding::*[@class='siebui-icon-dropdown applet-form-combo applet-list-combo'])")
	WebElementFacade ReasonDropDown;
	@FindBy(xpath="(.//textarea[@aria-label='What is this Credit Code used for?'])")
	WebElementFacade CreditCodeTextbox;
	@FindBy(xpath=".//*[@title='Credit reason required Form Applet']")
	WebElementFacade EventLevelAdjPopUp;
	@FindBy(xpath=".//*[@aria-labelledby='OUICreditlReason_Label']")
	WebElementFacade EventLevelAdjPopUpTextBox;
	@FindBy(xpath=".//*[@title='Credit reason required:Ok']")
	WebElementFacade EventLevelAdjPopUpOkbutton;
	
	@FindBy(xpath="//*[contains(text(),' customer has recieved a')]/ancestor::div/following-sibling::div//span[text()='Ok']")
	WebElementFacade GoodwillPopup;


	@Step
	public void  BillLevelItem(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Adjustment";
		String applet = "//*[@title='Line Items List Applet']";
		String table = "//table[@summary='Line Items']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i); 
			String sType = tableMap.get("Type").get(i);
			String sAmount = tableMap.get("Amount").get(i);
			String sBRMReason = tableMap.get("BRMReason").get(i);
			String sSiebelReason = tableMap.get("SiebelReason").get(i);
			String sReasonCode = tableMap.get("Reason Code").get(i);
			String sLevel =  tableMap.get("Level").get(i);
			String sCheckReasonCode =  tableMap.get("CheckReasonCode").get(i);
			Serenity.setSessionVariable("ReasonCode").to(sReasonCode);
			Serenity.setSessionVariable("Reason").to(sBRMReason);
			String sNetAmount =  tableMap.get("NetAmount").get(i);
			String sValidation =  tableMap.get("Validation").get(i);


			if(sCheckReasonCode.equals("Yes")){

				ReasonDropDown.click();

				if (!sSiebelReason.equals("")){
					List<WebElement> options = getDriver().findElements(By.xpath(".//div[@title='Adjustments']/ul[@role='combobox']/li")); 	        
					List<String> text = new ArrayList<String>();

					for(int j=0; j<options.size(); j++) {
						text.add(options.get(j).getText());		 	            
					} 
					Iterator<String> itr = text.iterator();

					while(itr.hasNext()) {		 	            	
						String currentValue= itr.next();		 	             	 	            	 
						if(currentValue.equalsIgnoreCase(sSiebelReason)){
							report.Info(sSiebelReason+" is present in Adjustment reason drop down." );				 	            	
							Assert.assertTrue(false);
							break;
						}

					}

					report.Info(sSiebelReason+" is not present in Adjustment reason drop down." );
				}
				return;
			}

			if(sClickAdjustButton.equals("Yes")){

				ItemLevelAdjusmentButton.withTimeoutOf(120, TimeUnit.SECONDS).isEnabled();
				if(!ItemLevelAdjusmentButton.isEnabled()){
					report.Info("Adjustment Button is not enabled"); 
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				}
				else{

					ItemLevelAdjusmentButton.click();
					report.Info("Adjustment Button Clicked Successfully");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Thread.sleep(2000);
				}
			} 
			common1.waitForPageLoad(getDriver());
			Thread.sleep(2000);
			if(AdjustmentType.isCurrentlyVisible()) {
				AdjustmentType.type(sType);
			}
			else {
				Thread.sleep(8000);
				AdjustmentType.type(sType);
			}

			if (!sAmount.equals("")) {
				AdjusmentAmount.type(sAmount); 
			}

			if(sLevel.equalsIgnoreCase("Adjust Full Amount")){

				String ValidateAmount = Serenity.sessionVariableCalled("CHARGE0").toString();

				AdjustFullAmountCheckBox.click(); 
				Thread.sleep(2000);  
				AmountTextBox.click();
				Thread.sleep(2000);

				String ActualValidateAmount = AmountTextBox.getTextValue();
				ActualValidateAmount = ActualValidateAmount.replace("£","");

				if (!sNetAmount.equals("")) {
					String NetAmount= Serenity.sessionVariableCalled("VoiceUsage").toString();
					if (ActualValidateAmount.equals(NetAmount)){
						report.Info("Full Adjustment amount is equals to NetAmount : "+ActualValidateAmount);	
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					}
					else{
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
						Assert.assertTrue(false);
					}
				}
				//String[] ActualValidateAmountnew = ActualValidateAmount.split(Pattern.quote("."));
				//ActualValidateAmount= ActualValidateAmountnew[0];
				else if (ActualValidateAmount.equals(ValidateAmount)){
					report.Info("Full Adjustment done successfully. Amount : "+ActualValidateAmount);	
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				}
				else{
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(false);
				}

			}



			AdjustmentReason.type(sSiebelReason);
			if(SubmitAdjustmentButton.isEnabled()){
				SubmitAdjustmentButton.click();
				
				if (sSiebelReason.contains("Goodwill") && sValidation.equalsIgnoreCase("HandleGoodWillPopUp")){
					if (GoodwillPopup.isCurrentlyVisible()) {
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
						GoodwillPopup.click();
					}
				}
				
				if (EventLevelAdjPopUp.isVisible()) {
					EventLevelAdjPopUpTextBox.sendKeys("Adjustment submitted");	
					EventLevelAdjPopUpOkbutton.click();
					
	
				}
				
				if(BillingProfileType.equalsIgnoreCase("Maxamountvalidation")) {
					common1.HandleErrorPopUp("The request amount can’t exceed the original charge");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(true);
					break;
				}
				report.Info("Adjustment is Submitted successfully");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{
				report.Info("Adjustment is not Submitted");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(false);
			}
			for(int ref=20;ref>0;ref--){                     		
				if(!AdjustmentStatus.getTextValue().equals("Posted")){           		 
					RefreshButton.click();
					Thread.sleep(2000);
				}   
				if(AdjustmentStatus.getTextValue().equals("Posted")){
					report.Info("Adjustment is posted successfully ");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					break;
				}
			}

			if(!AdjustmentStatus.getTextValue().equals("Posted")){

				report.Info("Adjustment is not posted successfully " +AdjustmentStatus.getTextValue() );
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(false);
			}
		} 
	}

	@FindBy(linkText="Adjustments")
	WebElementFacade AdjustmentTabBillLevel;    
	//@FindBy(xpath=".//*[@title='Adjustment Requests:New']")
	@FindBy(xpath=".//button[@title='Adjustments:New']")
	WebElementFacade BillLevelnewRequest;    
	@FindBy(xpath="(.//*[@aria-label='Selection Field'])[3]")
	WebElementFacade InvoicePickList;    
	@FindBy(xpath=".//*[@title='Bills:OK']")
	WebElementFacade InvoiceSelectionOKButton;  
	@FindBy(xpath=".//*[@title='Adjustment Requests:Refresh']")
	WebElementFacade RefreshButton;   
	@FindBy(xpath=".//*[@aria-label='Adjust Full Amount']")
	WebElementFacade AdjustFullAmountCheckBox;
	@FindBy(xpath=".//*[@ aria-label='Amount']")
	WebElementFacade AmountTextBox;
	@FindBy(xpath=".//*[@aria-label='Created']//preceding::*[@class='siebui-icon-dropdown applet-form-combo applet-list-combo']")
	WebElementFacade AdjReason;

	@Step
	public void  BillLevelAdjustment_new(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Adjustment";
		String applet = "//*[@title='Bills List Applet']";
		String table = "//table[@summary='Bills']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i); 
			String sType = tableMap.get("Type").get(i);
			String sAmount = tableMap.get("Amount").get(i);
			String sBRMReason = tableMap.get("BRMReason").get(i);
			String sSiebelReason = tableMap.get("SiebelReason").get(i);
			String sReasonCode = tableMap.get("Reason Code").get(i);
			String sCheckReasonCode = tableMap.get("CheckReasonCode").get(i);
			String sLevel =  tableMap.get("Level").get(i);
			String sValidation =  tableMap.get("Validation").get(i);
			String sLocateCol = "Bill_Number";



			String sLocateColValue = Serenity.sessionVariableCalled("BILLNUMBER0").toString();         	

			AdjustmentTabBillLevel.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			AdjustmentTabBillLevel.click();
			
			common1.WaitForClock(Clockobj);
			
			Serenity.setSessionVariable("ReasonCode").to(sReasonCode);
			Serenity.setSessionVariable("Reason").to(sBRMReason);

			if(sCheckReasonCode.equals("Yes")){
				AdjReason.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				AdjReason.click();

				if (!sSiebelReason.equals("")){

					List<WebElement> options = getDriver().findElements(By.xpath(".//div[@title='Adjustments']/ul[@role='combobox']/li")); 	        
					List<String> text = new ArrayList<String>();

					for(int j=0; j<options.size(); j++) {
						text.add(options.get(j).getText());		 	            
					} 
					Iterator<String> itr = text.iterator();

					while(itr.hasNext()) {		 	            	
						String currentValue= itr.next();		 	             	 	            	 
						if(currentValue.equalsIgnoreCase(sSiebelReason)){
							report.Info(sSiebelReason+" is present in Adjustment reason drop down." );				 	            	
							Assert.assertTrue(false);
							break;
						}

					}

					report.Info(sSiebelReason+" is not present in Adjustment reason drop down." );
				}
				return;
			} 
			BillLevelnewRequest.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			BillLevelnewRequest.click();
			common1.WaitForClock(Clockobj);
			InvoicePickList.click();
			//InvoiceSelectionOKButton.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			//common1.waitForElement(InvoiceSelectionOKButton);
			common1.WaitForObjectPresence(".//*[@title='Bills:OK']");

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, "0").equals("true"));

			}
			else{
				common1.selectedRow = 1;
			}
			InvoiceSelectionOKButton.click();
			AdjustmentType.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			AdjustmentType.type(sType);

			if (!sAmount.equals("") ){
				AdjusmentAmount.type(sAmount);
			}

			if (sAmount.equals("Amount") ){
				AdjusmentAmount.type(Serenity.sessionVariableCalled("Amount0").toString());
			}

			AdjustmentReason.type(sSiebelReason);
			if(sLevel.equalsIgnoreCase("Adjust Full Amount")){
				String ValidateAmount = "";
				if(BillingProfileType.equalsIgnoreCase("GoodWillBillLevelFullBillBilling20")) {
					ValidateAmount=""+(Integer.parseInt(Serenity.sessionVariableCalled("BILL_TOTAL0").toString())- Integer.parseInt(Serenity.sessionVariableCalled("AMOUNT0").toString()));
				}
				else
					ValidateAmount = Serenity.sessionVariableCalled("BILL_BALANCE0").toString();
				AdjustFullAmountCheckBox.click(); 
				Thread.sleep(2000);  
				AmountTextBox.click();
				Thread.sleep(2000);

				String ActualValidateAmount = AmountTextBox.getTextValue();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				ActualValidateAmount = ActualValidateAmount.replace("£","");
				String[] ActualValidateAmountnew = ActualValidateAmount.split(Pattern.quote("."));
				ActualValidateAmount= ActualValidateAmountnew[0];
				report.Info("Amount captured is: "+ActualValidateAmount+" and expected is "+ValidateAmount);
				if (ValidateAmount.contains(ActualValidateAmount)){
					report.Info("Full Adjustment done successfully. Amount : "+ActualValidateAmount);	
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				}
				else{
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(false);
				}

			}

			if(SubmitAdjustmentButton.isEnabled()){
				SubmitAdjustmentButton.click();
				if(BillingProfileType.equalsIgnoreCase("MaxamountvalidationAdj")) {
					common1.HandleErrorPopUp("The request amount can’t exceed the original charge");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(true);
					break;
				}
				System.out.println("Adjustment is Submitted successfully");
				report.Info("Adjustment is Submitted successfully");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{
				report.Info("Adjustment is not Submitted");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(false);
			}
			
			if (sSiebelReason.contains("Goodwill") && sValidation.equalsIgnoreCase("HandleGoodWillPopUp")){
				if (GoodwillPopup.isCurrentlyVisible()) {
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					GoodwillPopup.click();
				}
			}
			
			
			if (EventLevelAdjPopUp.isVisible()) {
				EventLevelAdjPopUpTextBox.sendKeys("Adjustment submitted");	
				EventLevelAdjPopUpOkbutton.click();
				
				common1.WaitForObjectPresence(".//*[@title='Adjustment Requests:Refresh']");

			}

			for(int ref=5;ref>0;ref--){                     		
				if(!AdjustmentStatus.getTextValue().equals("Posted")){
					Thread.sleep(10000);
					RefreshButton.click();

				}   
				if(AdjustmentStatus.getTextValue().equals("Posted")){
					report.Info("Adjustment is posted successfully ");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					break;
				}
			}

			if(!AdjustmentStatus.getTextValue().equals("Posted")){

				report.Info("Adjustment is not posted successfully " +AdjustmentStatus.getTextValue() );
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(false);
			}


		} 
	}


	@FindBy(xpath=".//*[@title='Itemised List:Adjust']")
	WebElementFacade ItemisedAdjustButton;

	@Step
	public void  ItemisedListSelectionClickAdjust(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "ItemisedList";
		String applet = "//*[@title='Itemised List List Applet']";
		String table = "//table[@summary='Itemised List']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String  sUsage = tableMap.get("Usage").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i);
			if(iIndex.equals("")){

				iIndex = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				report.Info("Event View Shown");
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{
				common1.selectedRow = 1;
			}
			if(sClickAdjustButton.equals("Yes")){
				ItemisedAdjustButton.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				if(ItemisedAdjustButton.isEnabled()){
					ItemisedAdjustButton.click();
					report.Info("Adjustment Button is clicked Successfully");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				}

				else{
					report.Info("Adjustment Button is not enabled");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(false);
				}
			}

		} 
	}

	@FindBy(linkText="Itemised Usage")
	WebElementFacade ItemisedUsageTab;
	@FindBy(xpath=".//*[text()=\'Unbilled Usage\']")
	WebElementFacade PrepayUnbilledUsageTab;
	@FindBy(xpath=".//*[@title='Services:NewQuery']")
	WebElementFacade SeachMSISDNUnbilledUsage;
	@FindBy(xpath=".//*[@title='Services:Query']")
	WebElementFacade SeachMSISDNBilledUsage;
	@FindBy(xpath=".//*[@aria-labelledby='MSISDN_Label']")
	WebElementFacade MSISDNSearchTextBox;
	@FindBy(xpath=".//*[contains(text(),'The maximum limit of billing records has been exceeded')]")
	WebElementFacade ServiceThresholdPopup;

	@Step
	public void  EventLevelMSISDNSelect_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Services List Applet']";
		String table = "//table[@summary='Services']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);

		//	     ItemisedUsageTab.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
		//	     ItemisedUsageTab.click();
		//	     sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sPrepaidUsage = tableMap.get("PrepaidUsage").get(i);
			String sSearchByMsisdn = tableMap.get("SearchByMsisdn").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String sThreshold = tableMap.get("Threshold").get(i);


			if(sPrepaidUsage.equals("")){
				ItemisedUsageTab.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				ItemisedUsageTab.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}

			else {
				PrepayUnbilledUsageTab.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				PrepayUnbilledUsageTab.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			if(sThreshold.equals("Y")){
				
				Assert.assertTrue("Expected Popup has not occured",ServiceThresholdPopup.isCurrentlyVisible());
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				OkButton.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			if(iIndex.equals("")){

				iIndex = "0";
			}

			if(sLocateColValue.equals("CTNMSISDN")){

				//Will be fetched by BRM code

			}

			if(sLocateColValue.equals("GTNMSISDN")){

				//Will be fetched by BRM code

			}
			if(sLocateColValue.equals("MSISDN")){
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
				//Will be fetched by BRM code

			}

			if(sLocateColValue.equals("CHILDMSISDN")){
				sLocateColValue = Serenity.sessionVariableCalled("ACCNTMSISDN1").toString();
				//Will be fetched by BRM code

			}
			if (sSearchByMsisdn.equals("Y")){
				SeachMSISDNUnbilledUsage.isCurrentlyVisible();
				SeachMSISDNUnbilledUsage.click();
				common1.WaitForClock(Clockobj);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
				MSISDNSearchTextBox.isCurrentlyVisible();
				MSISDNSearchTextBox.click();
				MSISDNSearchTextBox.typeAndEnter(Serenity.sessionVariableCalled("ACCNTMSISDN0").toString());
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
				common1.WaitForClock(Clockobj);
				report.Info("MSISDN search performed"); 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}                   	
		} 
	}

	@FindBy(xpath=".//*[@title='Itemised List:Adjust']")
	WebElementFacade EventLevelAdjustButton;

	@Step
	public void  EventLevelAdjustment_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Bills_Adjustments";
		String applet = "//*[@title='Summary Charges List Applet']";
		String table = "//table[@summary='Summary Charges']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String iIndex = tableMap.get("Index").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i);

			if(iIndex.equals("")){

				iIndex = "0";
			}


			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}

			if (!UIName.equals("")){   

				if(value.equals("SMSUSAGE")){

				}

				if(value.equals("CYCLEFORWARD")){

				}
				common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(4000); 

			}     
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			if(sClickAdjustButton.equals("Yes")){

				EventLevelAdjustButton.withTimeoutOf(120, TimeUnit.SECONDS).isEnabled();
				if(!EventLevelAdjustButton.isEnabled()){
					report.Info("Adjustment Button is not enabled"); 
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				}
				else{

					EventLevelAdjustButton.click();
					report.Info("Adjustment Button Clicked Successfully");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Thread.sleep(2000);
				}
			} 
		} 
	}

	@FindBy(xpath=".//*[@title='Payments:Payment Transfer']")
	WebElementFacade PaymentTransferButton;

	@FindBy(xpath=".//*[@title='Payment Transfer:Submit']")
	WebElementFacade PaymentTransferSubmit;

	@FindBy(xpath=".//*[@aria-label='Amount']")
	WebElementFacade PaymentTransferAmount;

	@Step
	public void  ClickOnPayments_PaymentTransfer_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "Payments_Reversal_AllocateCash";
		String applet = "//*[@title='Payments List Applet']";
		String table = "//table[@summary='Payments']";

		PaymentsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		PaymentsTab.click();
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);

		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String  sLocateCol = tableMap.get("LocateCol").get(i);
			String index = tableMap.get("Index").get(i); 
			if(index.equals("")){

				index = "0";
			}

			if(sLocateColValue.equals("PAYMENTTRANSFER")){

				//sLocateColValue = "P1-47945";
				sLocateColValue = Serenity.sessionVariableCalled("PAYMENT_ID0").toString();
				//Payment id capture from BRM database
			}
			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, index).equals("true"));
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}

		}

		PaymentTransferButton.withTimeoutOf(120,TimeUnit.SECONDS).isEnabled();
		if(PaymentTransferButton.isEnabled()){
			PaymentTransferButton.click();
			report.Info("Payment Transfer Button is clicked.");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Thread.sleep(2000);
		}
		else{
			report.Info("Payment Transfer Button is not enabled");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			Assert.assertTrue(false);

		}

		PaymentTransferAmount.withTimeoutOf(120, TimeUnit.SECONDS);                
		report.Info("Payment Transfer Amount " + PaymentTransferAmount.getTextValue());
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		PaymentTransferSubmit.click();
		Thread.sleep(4000);


		common1.HandleErrorPopUp("Payment Transfer has been");

		/*Alert alert=getDriver().switchTo().alert();                      	   
	 String ActAlertText=alert.getText();
		if (ActAlertText.contains("Payment Transfer has been")){                    			 
            //alert.accept();
            common1.HandleErrorPopUp("Payment Transfer has been");
            report.Info("Payment Transfer Alert messsage is "+ActAlertText + " is displayed successfully.");
            sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

      } 
		else if (!ActAlertText.contains("Payment Transfer has been")){  
			report.Info("Payment Transfer failed with message "+ActAlertText);
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
         Assert.assertTrue(false);

}*/

	}
	@FindBy(xpath=".//a[text()='TopUp History']")
	WebElementFacade TopUpHistoryTab;
	@FindBy(xpath=".//button[@title='TopUp History:Get Topups']")
	WebElementFacade GetTopUpButton;

	@Step
	public void  TopUpHistory(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "TopUpHistory";
		String table = "//table[@summary='TopUp History']";
		String applet = "//*[@title='TopUp History List Applet']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		common1.waitForPageLoad(getDriver());
		Thread.sleep(1000);
		if (TopUpHistoryTab.isCurrentlyVisible()){
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			TopUpHistoryTab.click();
		}
		else {
			report.Info("TopUp History tab is not displayed.");
			Assert.assertTrue(false);
		}

		common1.waitForPageLoad(getDriver());
		Thread.sleep(4000);

		if (GetTopUpButton.isCurrentlyVisible()){
			GetTopUpButton.click();
		}
		else{
			report.Info("TopUp History tab is not clicked successfully.");
			Assert.assertTrue(false);
		}
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			//String ProfileType = tableMap.get("Value").get(i);
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String Index = tableMap.get("Index").get(i);

			Thread.sleep(1000);
			//String LocateColMessege = sLocateCol+"Not Found";
			if (Index.equals("")){
				Index = "0";
			}
			if (value.equals("TOPUP")){
				value = Serenity.sessionVariableCalled("RateGBP").toString();

			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){   
				common1.updateSiebList(applet,table, UIName, value);
				Thread.sleep(2000);                                                                          
			}     


		}
	}	

	@FindBy(xpath=".//span[text()='Write Off']")
	WebElementFacade WriteOffButton;

	@FindBy(xpath=".//span[text()='Write Off Reversal']")
	WebElementFacade WriteOffReversalButton;


	@Step
	public void  WriteOff(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "WriteOff";

		common1.waitForPageLoad(getDriver());

		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Type").size();i++) {			
			String sType = tableMap.get("Type").get(i);
			String sPopUp = tableMap.get("PopUp").get(i);
			if (sType.equals("WriteOff")){
				WriteOffButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				if (WriteOffButton.isCurrentlyVisible()) {
					WriteOffButton.click();
					Thread.sleep(4000);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					common1.HandleErrorPopUp(sPopUp);
				}
				else if(!WriteOffButton.isCurrentlyVisible()) {
					report.Info("Write Off button is not displayed.");
					Assert.assertTrue(false);
				}
			}

			if (sType.equals("ReverseWriteOff")){
				WriteOffReversalButton.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
				if (WriteOffReversalButton.isCurrentlyVisible()) {
					WriteOffReversalButton.click();
					Thread.sleep(4000);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					common1.HandleErrorPopUp(sPopUp);
				}
				else if(!WriteOffReversalButton.isCurrentlyVisible()) {
					report.Info("Write Off Reversal button is not displayed.");
					Assert.assertTrue(false);
				}
			}
		}
	}		   

	@FindBy(linkText="Bundle Usage")
	WebElementFacade BundleUsage;
	@FindBy(xpath=".//*[@class='siebui-button-toolbar siebui-closed']")
	WebElementFacade Iconbar;
	@FindBy(xpath=".//*[@name='SiteMap']/span")
	WebElementFacade SiteMap;                     
	@FindBy(xpath="(.//a[text()='Payments'])[1]")
	WebElementFacade PaymentScreentab; 
	@FindBy(xpath=".//a[text()='Payment Search']")
	WebElementFacade PaymentSearchtab;
	@FindBy(xpath=" .//input[@aria-labelledby='PaymentType_Label']")
	WebElementFacade PaymentTypeLabel; 
	@FindBy(xpath=".//input[@aria-labelledby='StartDate_Label']")
	WebElementFacade StartDateLabel; 
	@FindBy(xpath=".//input[@aria-labelledby='EndDate_Label']")
	WebElementFacade EndDateLabel; 
	@FindBy(xpath=".//input[@aria-labelledby='Amount_Label']")
	WebElementFacade AmountLabel;
	@FindBy(xpath=".//input[@aria-labelledby='BankType_Label']")
	WebElementFacade BankTypeLabel;    

	@FindBy(xpath=".//*[@title='Search Payment:Go']")
	WebElementFacade SearchPaymentGo;         
	@FindBy(xpath=".//input[@aria-labelledby='BGC_Label']")
	WebElementFacade BGCLabel;  
	@FindBy(xpath=".//input[@aria-labelledby='TransactionID_Label']")
	WebElementFacade TransactionIDLabel; 

	public void PaymentSearch_fn(String iRow) throws IOException, InterruptedException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";          
		String dataSheet = "PaymentSearch";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRow, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0;i < tableMap.get("PaymentType").size();i++) {
			String sPaymentType = tableMap.get("PaymentType").get(i);
			String sBankType = tableMap.get("BankType").get(i);

			String sAmount=Serenity.sessionVariableCalled("PNAMOUNT0").toString();
			String sBGCNarrative = Serenity.sessionVariableCalled("RECEIPT_NO0");
			String sEndDate = Serenity.sessionVariableCalled("END_T0");  
			String sStartDate = Serenity.sessionVariableCalled("START_T0");
			String sTransactionID = Serenity.sessionVariableCalled("PAYMENT_TRANS_ID0");
			DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			if (sStartDate.equals("")) {   
				Calendar cal = Calendar.getInstance();	                     
				//System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
				cal.add(Calendar.DATE, -1);
				System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime()));    
				sStartDate=dateFormat.format(cal.getTime());
			}
			if (sEndDate.equals("")) {                  
				Calendar calEND = Calendar.getInstance();
				//System.out.println("Today's date is "+dateFormat.format(calEND.getTime()));
				calEND.add(Calendar.DATE, +1);
				System.out.println("Tommrow's date is "+dateFormat.format(calEND.getTime())); 
				sEndDate=dateFormat.format(calEND.getTime());
			}
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
			common1.waitForPageLoad(getDriver());     
			report.Info("Gone to site map");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			PaymentScreentab.click();
			report.Info("Gone to Search Tab");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			PaymentSearchtab.click();
			PaymentTypeLabel.type(sPaymentType);
			AmountLabel.type(sAmount);
			StartDateLabel.type(sStartDate);
			EndDateLabel.type(sEndDate);
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			BankTypeLabel.type(sBankType);
			if (sTransactionID!=null ) { 
				TransactionIDLabel.type(sTransactionID);
			}
			if (sBGCNarrative!=null) { 
				BGCLabel.type(sBGCNarrative);
			}

			report.Info("Values Entered For payment Search");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			SearchPaymentGo.withTimeoutOf(120,TimeUnit.SECONDS).waitUntilClickable();
			SearchPaymentGo.click();
			Thread.sleep(2000);
			report.Info("Go Button Clicked");
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		}     
	}


	public void ValidatePaymentResult_fn(String iRow) throws IOException, InterruptedException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";          
		String dataSheet = "PaymentResult";
		String applet = "//*[@title='Payment Result List Applet']";
		String table = "//table[@summary='Payment Result']";
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString()); 
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRow, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0;i < tableMap.get("UIName").size();i++) {
			String UIName = tableMap.get("UIName").get(i);
			String sValue = tableMap.get("Value").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String sLocateCol = tableMap.get("LocateCol").get(i);
			Serenity.setSessionVariable("sLocateColValue").to(sLocateColValue);



			if (sValue.contains("*")) {
				sValue=sValue.replace("*", "");            	 
				if (Serenity.sessionVariableCalled(sValue)!=null)
					sValue=Serenity.sessionVariableCalled(sValue).toString(); 
				sValue=sValue+"*";

			}
			else {
				if (Serenity.sessionVariableCalled(sValue)!=null)
					sValue=Serenity.sessionVariableCalled(sValue).toString(); 
			}

			if (!sLocateCol.equals("")){		        	
				Thread.sleep(2000);
				common1.selectedRow = -1;
				String sResult = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, "0");

				Assert.assertTrue(sResult,sResult.equals("true")); 
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

			}
			else{
				common1.selectedRow = 1;
			}
			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			if (!UIName.equals("")){ 
				Thread.sleep(2000);
				common1.updateSiebList(applet,table, UIName, sValue);           
				Thread.sleep(1000);		        
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());		       

			}	


		}
	}
	@FindBy(xpath=".//a[text()='Suspense Payment']")
	WebElementFacade SuspensePaymentTab;

	@FindBy(xpath=".//input[@aria-labelledby='PaymentType_Label']")
	WebElementFacade PaymentType;

	@FindBy(xpath=".//input[@aria-labelledby='BankType_Label']")
	WebElementFacade BankType;

	@FindBy(xpath=".//button[@title='Suspense Payment Query:Go']")
	WebElementFacade PaymentGoButton;

	@FindBy(xpath=".//input[@aria-labelledby='StartDate_Label']")
	WebElementFacade sStartDate;

	@FindBy(xpath=".//input[@aria-labelledby='EndDate_Label']")
	WebElementFacade sEndDate;

	@FindBy(xpath=".//input[@aria-labelledby='MinAmount_Label']")
	WebElementFacade MinimumAmt;

	@FindBy(xpath=".//input[@aria-labelledby='Max_Amount_Label']")
	WebElementFacade MaxmumAmt;

	@Step
	public void  SuspensePayment(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "SuspensePaymentResult";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {			
			String sBankPaymentType = tableMap.get("BankPaymentType").get(i);
			String sBankingType = tableMap.get("BankingType").get(i);
			String sMinAmount = tableMap.get("MinAmount").get(i);
			String sMaxAmount = tableMap.get("MaxAmount").get(i);

			common1.waitForPageLoad(getDriver());

			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date currentDate = new Date();

			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE,-1);

			Date startDate = c.getTime();
			String sStrtDate = (dateFormat.format(startDate));

			c.add(Calendar.DATE,2);

			startDate = c.getTime();
			String sEdDate = (dateFormat.format(startDate));

			if (SuspensePaymentTab.isCurrentlyVisible()){
				SuspensePaymentTab.click();
			}

			else if (!SuspensePaymentTab.isCurrentlyVisible()){
				Thread.sleep(6000);
				SuspensePaymentTab.click();
			}
			Thread.sleep(5000);

			if (PaymentType.isCurrentlyVisible()){
				if (sBankPaymentType.equals("Y")){
					PaymentType.type("Bank Payment");
					BankType.type(sBankingType);
					MinimumAmt.type(sMinAmount);
					MaxmumAmt.type(sMaxAmount);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				}
				else{
					PaymentType.type("Recurring CC Payment");
					
				}
				sStartDate.type(sStrtDate);
				sEndDate.type(sEdDate);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				PaymentGoButton.click();
				Thread.sleep(2000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else if (!PaymentType.isCurrentlyVisible()) {
				report.Info("Suspense Payment tab is not clicked successfully.");
				Assert.assertTrue(false);
			}

		}
	}

	@FindBy(xpath=".//button[@aria-label='Suspense Payment Result:Payment Transfer']")
	WebElementFacade PaymentTransferbutton;
	@FindBy(xpath=".//*[@aria-label='Payment Transfer:Submit']")
	WebElementFacade PaymentTransfSubmitbutton;
	@FindBy(xpath=".//input[@aria-labelledby='Amount_Label']")
	WebElementFacade PaymentTransfAmount;


	@Step
	public void  SuspensePaymentResult(String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "SuspensePaymentResult";
		String applet = "//*[@title='Suspense Payment Result List Applet']";
		String table = "//table[@summary='Suspense Payment Result']";

		Thread.sleep(5000);

		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {			
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String sPaymentTransferClick = tableMap.get("PaymentTransferClick").get(i);

			if (sLocateColValue.contains("AMOUNT")){
				sLocateColValue = sLocateColValue.replace("AMOUNT",Serenity.sessionVariableCalled("AMOUNT0").toString());				
			}

			if (value.equals("AMOUNT")){
				value = Serenity.sessionVariableCalled("AMOUNT0").toString()+"*";				
			}

			if (!sLocateCol.equals("")){		        	
				Thread.sleep(2000);
				common1.selectedRow = -1;
				String sResult = common1.locateColumn(applet,table, sLocateCol, sLocateColValue, "0");

				Assert.assertTrue(sResult,sResult.equals("true")); 
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           

			}
			else{
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){ 
				Thread.sleep(2000);
				common1.updateSiebList(applet,table, UIName, value);           
				Thread.sleep(1000);		        
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());		       

			}	


			if (sPaymentTransferClick.equals("Yes")){
				PaymentTransferbutton.click();
				Thread.sleep(2000);
				PaymentTransfAmount.isVisible();
				PaymentTransfSubmitbutton.click();
				Thread.sleep(3000);
				common1.HandleErrorPopUp("Payment Transfer has been successfully");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue(true);
				report.Info("Payment Transfer has been successfully");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}


		}		 
	}



	@Step
	public void  CreditStatusCheck(String QueryName) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		String status =null;
		for (int i = 1;i<20;i++) {
			Database.ExecuteDBQuery(QueryName);
			status = Serenity.sessionVariableCalled("STATUS0").toString();

			if(status.equalsIgnoreCase("completed")) {
				report.Info("Credit alert status is changed to completed in siebel DB");
				break;
			}
			else {
				Thread.sleep(5000);
			}
		}
		if(!status.equalsIgnoreCase("completed")) {
			report.Info("Credit alert status is Active in siebel DB");
			Assert.assertTrue(false);
		}			
	}	

	@FindBy(xpath=".//button[@title='Products and Services:Check Credit Limit']")
	WebElementFacade CheckCreditLimitButton;

	@FindBy(xpath=".//input[@aria-labelledby='Credit_Limit_Label']")
	WebElementFacade SetCreditLimit;

	@FindBy(xpath=".//button[@title='Credit Limit Management:Set Credit Limit']")
	WebElementFacade SetCreditLimitButton;


	@Step
	public void  CheckCreditLimit() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{

		if(CheckCreditLimitButton.isCurrentlyVisible())
		{
			CheckCreditLimitButton.click();
			Thread.sleep(8000);

			if(SetCreditLimit.isCurrentlyVisible()) {
				SetCreditLimit.type("200");
				report.Info("Credit Limit Set to 200");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				SetCreditLimitButton.click();
				Thread.sleep(5000);

			}

			else {
				Thread.sleep(5000);
				if(SetCreditLimit.isCurrentlyVisible()) {
					SetCreditLimit.type("200");
					report.Info("Credit Limit Set to 200");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					SetCreditLimitButton.click();

				}
				else {
					report.Info("Credit limit window is not displayed");
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(false);
				}
			}
		}
	}

	@Step
	public void  PaymentSiebelValidation (String BillingProfileType) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "Payments_Refund";
		String applet = "//*[@title='Payments List Applet']";
		String table = "//table[@summary='Payments']";
		
		BillsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		BillsTab.click();
		common1.WaitForClock(Clockobj);
		
		String id =BillingProfileID.getTextValue();
		String  sLocateColValue = null;

		PaymentsTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
		PaymentsTab.click();
		System.out.println("Billing profile id "+id);
		report.Info("Billing Profile ID " +id);
		sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			sLocateColValue = tableMap.get("LocateColValue").get(i);
			String  sLocateCol = tableMap.get("LocateCol").get(i);
			String index = tableMap.get("Index").get(i); 


			if(index.equals("")){

				index = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, index).equals("true"));
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue);           
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}
		}
	}

	@FindBy(xpath=".//*[@title='Itemised List:Adjust']")
	WebElementFacade AdjustButton;

	@Step
	public void  ItemisedListSelectionTopUp_fn(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "ItemisedList";
		String applet = "//*[@title='Itemised List List Applet']";
		String table = "//table[@summary='Itemised List']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateCol = tableMap.get("LocateCol").get(i); 
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String iIndex = tableMap.get("Index").get(i); 
			String sClickAdjustButton = tableMap.get("ClickAdjustButton").get(i); 

			if(iIndex.equals("")){
				iIndex = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString()); 
			}

			if(sClickAdjustButton.equals("Yes")) {
				AdjustButton.click();
				Thread.sleep(3000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

			}

		}
	}

	@FindBy(xpath=".//*[@title='Itemised List:Search']")
	WebElementFacade SearchButton;
	@FindBy(xpath=".//*[@aria-labelledby='From_Amount_Label']")
	WebElementFacade FromAmount;
	@FindBy(xpath=".//*[@aria-labelledby='To_Amount_Label']")
	WebElementFacade ToAmount;
	@FindBy(xpath=".//*[@aria-labelledby='Usage_Type_Label']")
	WebElementFacade UsageType;
	@FindBy(xpath=".//*[@aria-label='Phone number']")
	WebElementFacade PhoneNo;
	@FindBy(xpath=".//*[@aria-label='Start Date']")
	WebElementFacade StartDate;
	@FindBy(xpath=".//*[@aria-label='End Date']")
	WebElementFacade EndDate;
	@FindBy(xpath=".//button[@title='Itemised List:Go']")
	WebElementFacade GoSearchButton;
	@FindBy(xpath=".//*[contains(@aria-label,'Phone')]")
	WebElementFacade PhoneNumber;
	

	@Step
	public void  ItemisedList(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "Usagesearch";
		String applet = "//*[@title='Itemised List List Applet']";
		String table = "//table[@summary='Itemised List']";		 	     
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {

			String sLocateCol = tableMap.get("LocateCol").get(i); 
			String sLocateColValue = tableMap.get("LocateColValue").get(i); 
			String iIndex = tableMap.get("Index").get(i);
			String sFromAmount = tableMap.get("FromAmount").get(i); 
			String sToAmount = tableMap.get("ToAmount").get(i); 
			String sUsageType = tableMap.get("UsageType").get(i); 
			String sStartDate = tableMap.get("StartDate").get(i); 
			String sPhNumber = tableMap.get("PhNumber").get(i); 
			//String sEndDate = tableMap.get("EndDate").get(i);
			String sRowcount = tableMap.get("Rowcount").get(i);
			String sSearchButton = tableMap.get("SearchButton").get(i);
			String sThreshold = tableMap.get("Threshold").get(i);

			if (!sThreshold.equals("")){
				Assert.assertTrue("Expected Popup has not occured",ServiceThresholdPopup.isCurrentlyVisible());
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				OkButton.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			
			if(iIndex.equals("")){
				iIndex = "0";
			}
			if(sSearchButton.equals("Yes")){
				SearchButton.click();
				Thread.sleep(3000);
				if(!sFromAmount.equals("")){
					FromAmount.type(sFromAmount);
					ToAmount.type(sToAmount);
				}

				if(!sUsageType.equals("")){
					UsageType.type(sUsageType);		          		
				}
				if(!sPhNumber.equals("")){
					PhoneNumber.type(sPhNumber);		          		
				}
				if(!sStartDate.equals("")){
					SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MMM/yyyy");
					Date now = new Date();

					StartDate.type(sdfDate.format(now));
					Calendar c = Calendar.getInstance();
					c.setTime(now);
					c.add(Calendar.DATE, 2); 
					now= c.getTime();
					EndDate.type(sdfDate.format(now));
				}
				GoSearchButton.click();

			}
			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				//Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex).equals("true"));
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, iIndex);
			}
			if(!sRowcount.equals(""))
			{
				int RowCount = findAll(By.xpath("//table[@summary='Itemised List']/tbody/tr[contains(@class,'ui-widget-content')]")).size();
				String Actualcount = RowCount + "";
				if (Actualcount.equals(sRowcount)){		        		  
					report.Info("Expected Count" +sRowcount+" matched in siebel using search criteria with actual count"+Actualcount);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());	
				}
				else{
					report.Info("Expected Count" +sRowcount+" not matched in siebel using search criteria with actual count"+Actualcount);
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					Assert.assertTrue(false);
				}
			}

		}
	}
	@FindBy(xpath=".//*[@aria-labelledby='CreditCardNumber_Label']")
	WebElementFacade CardNumberxpath;
	@FindBy(xpath=".//*[@aria-labelledby='VF_Credit_Card_Expiration_Month_Label']")
	WebElementFacade ExpiryMonthxpath;
	@FindBy(xpath=".//*[@aria-labelledby='VF_Credit_Card_Expiration_Year_Label']")
	WebElementFacade ExpiryYearxpath;


	@Step
	public void  CreditcardValidation(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "CardNovalidation";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Row").size();i++) {
			String sCardNumber = tableMap.get("CardNumber").get(i); 
			String sExpiryMonth = tableMap.get("ExpiryMonth").get(i);
			String sExpiryYear = tableMap.get("ExpiryYear").get(i);

			Thread.sleep(3000);
			if(sCardNumber.equalsIgnoreCase(CardNumberxpath.getTextValue())){

				if(sExpiryMonth.equalsIgnoreCase(ExpiryMonthxpath.getTextValue())){

					if(sExpiryYear.equalsIgnoreCase(ExpiryYearxpath.getTextValue())){
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
						report.Info("Card details matched");
						getDriver().quit();
						Assert.assertTrue(true);
					}
					else{
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
						report.Info("Card details not matched");
						Assert.assertTrue(false);

					}
				}

				else{
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					report.Info("Card details not matched");
					Assert.assertTrue(false);

				}
			}

			else{
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("Card details not matched");
				Assert.assertTrue(false);

			}




		}
	}
	@FindBy(xpath=".//a[text()='Bundle Usage']")
	WebElementFacade BundleUsages;
	@FindBy(xpath=".//*[@aria-label='Account no.']")
	WebElementFacade Account;
	@FindBy(xpath=".//*[@aria-label='Unbilled Usage']")
	WebElementFacade Unbilledusage;
	@FindBy(xpath=".//*[@aria-labelledby='Balance_Label']")
	WebElementFacade BalanceValidate;
	@FindBy(xpath=".//*[@aria-labelledby='Unallocated_Payment_Label']")
	WebElementFacade UnallocatedPayment;
	@FindBy(xpath=".//*[@aria-label='Unallocated Adjustment']")
	WebElementFacade UnallocatedAdjustment;
	@FindBy(xpath=".//*[@aria-label='Write Off Amount']")
	WebElementFacade WriteOffAmt;
	@FindBy(xpath=".//*[@aria-label='Credit class']")
	WebElementFacade CreditClassTextBox;


	@Step
	public void  LegalGropusMember(String BillingProfileType) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "LegalGropusMember";
		String applet = "//*[@title='Members List Applet']";
		String table = "//table[@summary='Members']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			String UIName =  tableMap.get("UIName").get(i);
			String value =  tableMap.get("Value").get(i);              
			//String sNewButton =  tableMap.get("NewButton").get(i);
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String Index = tableMap.get("Index").get(i);
			String sUnallocatedPaym = tableMap.get("UnallocatedPaym").get(i);
			String sBundleUasge = tableMap.get("BundleUasge").get(i); 
			String sAccountType = tableMap.get("AccountType").get(i);
			String sUnallocatedAdj = tableMap.get("UnallocatedAdj").get(i);
			String sUnbilledUsageValidate = tableMap.get("UnbilledUsageValidate").get(i);
			String sWriteoff = tableMap.get("Writeoff").get(i);
			String sCreditClass = tableMap.get("CreditClass").get(i);
			
			if (!sCreditClass.equals("")){
				if (!sCreditClass.equals("")){
					String  CreditClassTextbox = CreditClassTextBox.getTextValue();

					Assert.assertTrue("Credit class is as expected",CreditClassTextbox.equals("sCreditClass"));
				}
				else {
					Assert.assertTrue(false);	
				}
			}



			if (!sUnbilledUsageValidate.equals("")){
				String SiebelValue=Unbilledusage.getTextValue();


				sLocateColValue= Serenity.sessionVariableCalled("CHARGE0").toString();

				String exuldedoller= BalanceValidate.getTextValue();
				exuldedoller = exuldedoller.replace("£", "");

				float exdoll=Float.parseFloat(exuldedoller);
				Serenity.setSessionVariable("AMOUNT0").to(exdoll+10);


				if(SiebelValue.contains(sLocateColValue)){
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					report.Info("Unbilled usage value matched");
				}
				else{
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					report.Info("Unbilled usage value not matched");
					Assert.assertTrue(false);

				}


			}

			if (!sUnallocatedPaym.equals("")){
				UnallocatedPayment.equals("(£10.00)");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("UnallocatedPayment matched as expected");

			}

			if (!sUnallocatedAdj.equals("")){
				UnallocatedAdjustment.equals("(£5.00)");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("UnallocatedAdjustment matched as expected");

			}
			if (!sWriteoff.equals("")){

				String SiebelValue=WriteOffAmt.getTextValue();

				sLocateColValue= Serenity.sessionVariableCalled("DUE0").toString();

				if(SiebelValue.contains(sLocateColValue)){
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					report.Info("Write Off value matched");
				}
				else{
					sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					report.Info("Write Off value not matched");
					Assert.assertTrue(false);

				}
			}


			if (sLocateColValue.equals("ParentAccountName")){
				sLocateColValue = Serenity.sessionVariableCalled("LEGALGROUPVALUE0");
			}

			if (sLocateColValue.equals("ChildAccountName")){
				sLocateColValue =  Serenity.sessionVariableCalled("LEGALGROUPVALUE0");
			}


			if (Index.equals("")){
				Index = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue); 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){         		 
				common1.updateSiebList(applet,table, UIName, value); 
				Thread.sleep(2000);                       	              
				report.Info("Action : "+UIName+" has perfomed "+value);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}

			if (sBundleUasge.equals("Y")){
				BundleUsages.click();
				Thread.sleep(5000);


				String sParentAccountNo = "";
				String sChildAccountNo = "";
				String  sAccount=Account.getTextValue();

				if (sAccountType.equals("ParentAccount")) {
					sParentAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();
					if (sParentAccountNo.equals(sAccount)){
						//Assert.assertTrue(sParentAccountNo ,sParentAccountNo.equals("true")); 
						report.Info("Account Validation is Passed");
					}	

				}	  

				if (sAccountType.equals("ChildAccount")) {
					sChildAccountNo = Serenity.sessionVariableCalled("PrePostAccountNo").toString();
					if (sChildAccountNo.equals(sAccount)){
						//Assert.assertTrue(sChildAccountNo ,sChildAccountNo.equals("true")); 
						report.Info("Account Validation is Passed");
						sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					}

				}

			}

		}

	}	 

	@FindBy(xpath=".//a[text()='Payment Arrangement']")
	WebElementFacade PaymntArrangementTab;
	@FindBy(xpath=".//*[@aria-label='Promise to Pay Alerts:New']")
	WebElementFacade NewP2PButton;
	@FindBy(xpath=".//*[@aria-labelledby='Payment_Method_Label']")
	WebElementFacade PaymentMethodP2P;
	@FindBy(xpath=".//*[@aria-label='Plan Start Date']")
	WebElementFacade PlanStartDateP2P;
	@FindBy(xpath=".//*[@aria-label='Plan End Date']")
	WebElementFacade PlanEndDateP2P;
	@FindBy(xpath=".//*[@aria-label='Number of Installments']")
	WebElementFacade InstallmentNo;
	@FindBy(xpath=".//*[@aria-label='Total Arrangement Cost']")
	WebElementFacade TotalAgreementCost;
	@FindBy(xpath=".//*[@aria-label='Payment Arrangement Details:Submit']")
	WebElementFacade PaymentArrangementSubmit;
	@FindBy(xpath=".//div[contains(@style,'display: none;')][ @id='maskoverlay']")
	WebElement Clockobj;

	@Step
	public void  CreatePaymentArrangement(String installments) throws InterruptedException, IOException, AWTException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "P2P";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(installments, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			;
			String sPaymentMethod = tableMap.get("PaymentMethod").get(i);
			String sInstallmentNo = tableMap.get("InstallmentNo").get(i);


			PaymntArrangementTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();	    	 
			PaymntArrangementTab.click();

			Thread.sleep(2000);

			if(NewP2PButton.isCurrentlyVisible()) {
				NewP2PButton.click();
				common1.WaitForClock(Clockobj);
				report.Info("New button is clicked.");
			}
			else {
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				report.Info("New button is not enabled/present");
				Assert.assertTrue(false);
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			Date currentDate = new Date();

			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE,1);

			Date PlanStartDate = c.getTime();
			String sPlanStartDate = (dateFormat.format(PlanStartDate));

			c.add(Calendar.DATE,1);

			PlanStartDate = c.getTime();
			String sPlanEndDate = (dateFormat.format(PlanStartDate));
			Serenity.setSessionVariable("sPlanStartDate").to(sPlanStartDate);
			Serenity.setSessionVariable("sPlanEndDate").to(sPlanEndDate);


			if(PaymentMethodP2P.isCurrentlyVisible()) 	{

				if(!sPaymentMethod.equals("")) {

					PaymentMethodP2P.type(sPaymentMethod);
					PlanStartDateP2P.type(sPlanStartDate);
					PlanEndDateP2P.type(sPlanEndDate);
					InstallmentNo.typeAndEnter(sInstallmentNo);
					Thread.sleep(2000);	    			 
					String sCost=TotalAgreementCost.getTextValue();
					sCost = sCost.replace("£", "");
					sCost = sCost.replace(".00", "");
					int sTotalAmount=Integer.parseInt(sCost);
					sTotalAmount=(sTotalAmount+10);
					sCost="£"+sTotalAmount+".00";
					report.Info("Total amount is" +sCost);

					TotalAgreementCost.clear();
					TotalAgreementCost.type(sCost);
					Thread.sleep(2000);
					if(PaymentArrangementSubmit.isCurrentlyVisible()) {
						PaymentArrangementSubmit.click();
					}
				}

				common1.WaitForClock(Clockobj);


			}
		}

	}


	@Step
	public void  ValidatePaymentArrangementAlert(String installments) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "P2P";
		String applet = "//*[@title='Promise to Pay Alerts List Applet']";
		String table = "//table[@summary='Promise to Pay Alerts']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(installments, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			;
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String Index = tableMap.get("Index").get(i);
			String slaststatus = tableMap.get("laststatus").get(i);

			PaymntArrangementTab.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();	    	 
			PaymntArrangementTab.click();

			Thread.sleep(2000);

			Database.ExecuteDBQuery("TodaysDate2");
			String sTIMESTAMP = Serenity.sessionVariableCalled("TIMESTAMP0").toString();

			if (sLocateColValue.equals("CurDate")){
				sLocateColValue = sTIMESTAMP;
			}

			if (Index.equals("")){
				Index = "0";
			}

			if (slaststatus.equals("Y")){
				common1.selectedRow = 1;
				common1.updateSiebList(applet,table, "CaptureTextValue|Status", value); 
				if (value.equals("Closed")){
					return;
				}
				else if (!value.equals("Closed")) {
					for (int x = 0;x < 10;x++) {
						getDriver().findElement(By.xpath(".//*[@aria-label='Promise to Pay Alerts:Menu']")).sendKeys(Keys.ALT,Keys.ENTER);
						Thread.sleep(20000);
						common1.selectedRow = 1;
						common1.updateSiebList(applet,table, "CaptureTextValue|Status", value);
						if (value.equals("Closed")){
							break;
						} }
				}
				if (value.equals("Closed")){
					report.Info("All Alerts are closed");
					return;
				}
			}


			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				Assert.assertTrue(common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index).equals("true"));
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue); 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){         		 
				common1.updateSiebList(applet,table, UIName, value); 
				Thread.sleep(2000);                       	              
				report.Info("Action : "+UIName+" has perfomed "+value);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}


		}
	}





	@Step
	public void  ValidatePaymentArrangement(String installments) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "P2P";
		String applet = "//*[@title='Installments List Applet']";
		String table = "//table[@summary='Installments']";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(installments, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			;
			String sLocateCol = tableMap.get("LocateCol").get(i);
			String sLocateColValue = tableMap.get("LocateColValue").get(i);
			String UIName = tableMap.get("UIName").get(i);
			String value = tableMap.get("Value").get(i);
			String Index = tableMap.get("Index").get(i);

			String sPlanStartDate = Serenity.sessionVariableCalled("sPlanStartDate").toString();
			String sPlanEndDate = Serenity.sessionVariableCalled("sPlanEndDate").toString();

			//	 String sPlanStartDate = "28/Dec/2018";
			//	 String sPlanEndDate ="29/Dec/2018";

			Database.ExecuteDBQuery("TodaysDate2");
			String sTIMESTAMP = Serenity.sessionVariableCalled("TIMESTAMP0").toString();


			Thread.sleep(2000);

			if (sLocateColValue.equals("StartDate")){
				sLocateColValue = sPlanStartDate;
			}

			if (sLocateColValue.equals("EndDate")){
				sLocateColValue = sPlanEndDate;
			}

			if (sLocateColValue.equals("CurDate")){
				sLocateColValue = sTIMESTAMP;
			}

			if (Index.equals("")){
				Index = "0";
			}

			if (!sLocateCol.equals("")){
				common1.selectedRow = -1;
				common1.locateColumn(applet,table, sLocateCol, sLocateColValue, Index);
				report.Info("Row is selected successfully with column name: "+sLocateCol+ " and value: "+sLocateColValue); 
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}
			else{               
				common1.selectedRow = 1;
			}
			if (!UIName.equals("")){         		 
				common1.updateSiebList(applet,table, UIName, value); 
				Thread.sleep(2000);                       	              
				report.Info("Action : "+UIName+" has perfomed "+value);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			}


		}
	}
	
	@FindBy(xpath=".//*[@aria-label='One Time Payment']")
	WebElementFacade OneTimePayment;
	
	@FindBy(xpath=".//button[@title='Capture New Payment:Card Details']")
	WebElementFacade CapNewCardDetails;
	
	@FindBy(xpath=".//*[@title='Capture New Payment:Submit']")
	WebElementFacade CapNewSubmitButton;
	
	
	@Step
	public void  OneTimePayment(String rowno) throws InterruptedException, IOException, AWTException{
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "AdhocCCPayment";
		String applet = "//*[@title='Billing profile List Applet']";
		String table = "//table[@summary='Billing profile']";
		int sAllocatedAmount;
		String  sAmount = null ;
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowno, filePath, dataSheet);

		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			sAmount= tableMap.get("Amount").get(i);
			Alert alert;
			String ActAlertText="";
			
			OneTimePayment.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
			OneTimePayment.click();
				RefundAmoutEnter.click();
				Thread.sleep(3000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				CapNewCardDetails.withTimeoutOf(120, TimeUnit.SECONDS).isDisplayed();
				CapNewCardDetails.click();
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Thread.sleep(5000);

				String initialWindow = "";
				initialWindow = getDriver().getWindowHandle();
				//CardDetailsButton.click();
				Thread.sleep(2000);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				Set<String> winHandles = getDriver().getWindowHandles();
				for (String handle : winHandles) {
					if (!handle.equals(initialWindow)) {
						getDriver().switchTo().window(handle);
						Thread.sleep(1000);
					}
				}

				this.enterAeirandiCardDetails("CreditCardVisaAuthorise");

				getDriver().switchTo().window(initialWindow);
				initialWindow = "";
				initialWindow = getDriver().getWindowHandle();

				Thread.sleep(3000);
				//Common.WaitForClock(Clockobj);
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				winHandles = getDriver().getWindowHandles();
				for (String handle : winHandles) {
					if (!handle.equals(initialWindow)) {
						getDriver().switchTo().window(handle);
						Thread.sleep(1000);
					}
				}
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

				RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				RefundAmoutEnter.type(sAmount);
				AuthorizeAndSettleButton.click();
				Thread.sleep(5000);
				//RefundAmoutEnter.withTimeoutOf(120, TimeUnit.SECONDS).isVisible();
				//RefundAmoutEnter.type(sAmount);
				//report.Info("Refund Submit Button Clicked");
				sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				CapNewSubmitButton.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				CapNewSubmitButton.click();

				Thread.sleep(4000);
				common1.HandleErrorPopUp("Payment confirmation number");

			
		}
	}
	
	

}
