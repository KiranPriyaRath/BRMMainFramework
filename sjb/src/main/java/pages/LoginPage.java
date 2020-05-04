package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.openqa.selenium.JavascriptExecutor;

import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;
import utilities.common;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class LoginPage extends PageObject {
	private static final TimeUnit SECONDS = null;
	@Steps
	private ReadWorkbook readWorkbook;
	@Steps
	private SikuliUtility SikuliUtility;
	
	@Steps
	private common Common;
	
	@FindBy(xpath=".//*[text()='My Homepage']")
	WebElementFacade Homepage;
	
	@FindBy(name="SWEUserName")
	WebElementFacade userNameTextBox;

	@FindBy(name="SWEPassword")
	WebElementFacade passwordTextBox;

	@FindBy(id="s_swepi_22")
	WebElementFacade loginButton;

	@FindBy(xpath=".//a[text()='Customer Comm List']")
  	 WebElementFacade CustomerCommsList;
	
	@FindBy(xpath=".//a[text()='Continue to this website (not recommended).']")
 	 WebElementFacade ContinueToWebsiteLink;
	
	@FindBy(xpath=".//*[@data-tabindex='tabScreen1']")
	WebElement AccountsTabSync;
	
	@FindBy(id="s_swepi_22")
	WebElement loginButtonsync;
	
	@FindBy(xpath=".//*[text()='My Homepage']")
	WebElement Homepage1;
	
	@Steps
	ReportMessege report;
	
	@Step
	public void login(String username, String password) {
		userNameTextBox.type(username);		
		passwordTextBox.typeAndEnter(password);
		loginButton.click();
	}
	
	@Step
	public void Login(String rowName,String EnvURL) throws IOException, InterruptedException, AWTException, FindFailed {	
		//Common.WritePerformanceOutput("----------------------------------Selenium Performance Check----------------------------------",true);																	
		//Common.waitForPageLoad(getDriver());
		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "LoginToSiebel";
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowName, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Username").size();i++) {
			String username = tableMap.get("Username").get(i);
			String password = tableMap.get("Password").get(i);
			String sEnv = tableMap.get("EnvURL").get(i);
			String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
			//String SikulifilePathForSavingFile = "";
			String URL = null ; 
			Serenity.setSessionVariable("Env").to(sEnv);

			if (sEnv.equalsIgnoreCase("E4"))
			{
				URL = "https://crm01-newvoe4.newvoe.eu/callcenterOUI_enu/start.swe?";
			}
			if (sEnv.equalsIgnoreCase("E7"))
			{
				URL = "https://crm01-newvoe7.newvoe.eu/callcenterOUI_enu/start.swe?";
			}
			if (sEnv.equalsIgnoreCase("C2"))
			{
				URL = "https://10.78.221.37/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("E8"))
			{
				URL = "https://10.78.195.233/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("SUP02"))
			{
				URL = "https://crm01-newvoesup02.newvoe.eu/callcenterOUI_enu/start.swe?";
			}
			getDriver().quit();
			openAt(URL);

			getDriver().manage().window().maximize();
			//SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
			//System.out.println("Function is  :-"+SikulifilePathForSavingFile);
			//Thread.sleep(5000);

		/*	if (Common.waitForElement(ContinueToWebsiteLinkWebElement)) {
				ContinueToWebsiteLink.click();
			}*/
			Thread.sleep(2000);
			
			//SikuliUtility.ClickTrustedPopUp(SikulifilePath);	
			Common.waitForElement(loginButtonsync);
			//Assert.assertTrue("Login Button is not visible", loginButton.isVisible());
			//Assert.assertTrue("Login Button is not visible", loginButton.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
			userNameTextBox.type(username);	     		
			//passwordTextBox.type(password);	
			passwordTextBox.type(password);
			
			

			long startTime = System.currentTimeMillis();
			loginButton.click();
			Common.waitForElement(AccountsTabSync);
			//Common.waitForPageLoad(getDriver());
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			Common.WritePerformanceOutput("Logged in ,Total Login Time " + totalTime/1000);
			//Thread.sleep(2000);

			//Common.waitForPageLoad(getDriver());
			//Homepage.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
			for (int j = 0  ;j < 3;j++) {
				boolean flag=Common.waitForElement(Homepage1);
				if (flag==false) {
					getDriver().close();
					openAt(URL);

					getDriver().manage().window().maximize();


				/*	if (Common.waitForElement(ContinueToWebsiteLinkWebElement)) {
						ContinueToWebsiteLink.click();
					}*/
					Thread.sleep(2000);
					Common.waitForElement(loginButtonsync);
					//Assert.assertTrue("Login Button is not visible", loginButton.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
					userNameTextBox.type(username);	     		
					//passwordTextBox.type(password);	
					passwordTextBox.type(password);

					report.Info("Login Button click");
					startTime = System.currentTimeMillis();
					loginButton.click();
					Common.waitForElement(AccountsTabSync);
					//Common.waitForPageLoad(getDriver());
					endTime = System.currentTimeMillis();
					totalTime = endTime - startTime;
					Common.WritePerformanceOutput("ReLogged in ,Total ReLogin Time " + totalTime/1000);
					Thread.sleep(2000);
					SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				}
				else{
					SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
					break;
				}				

				//Assert.assertTrue("Login Failed", Common.waitForElement(AccountsTabSync));

			}

		}
	}
	
	@FindBy(xpath=".//a[text()='Continue to this website (not recommended).']")
	 WebElement ContinueToWebsiteLinkWebElement;
	
	@Step
	public void ReLogin(String rowName,String EnvURL) throws IOException, InterruptedException, AWTException, FindFailed {

		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "LoginToSiebel";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowName, filePath, dataSheet);
		readWorkbook.testData(tableMap);

		for (int i = 0  ;i < tableMap.get("Username").size();i++) {
			String username = tableMap.get("Username").get(i);
			String password = tableMap.get("Password").get(i);
			String sEnv = tableMap.get("EnvURL").get(i);
			String SikulifilePath = "\\src\\test\\resources\\SikuliImages";
			//String SikulifilePathForSavingFile = "";
			String URL = null ; 
			Serenity.setSessionVariable("Env").to(sEnv);

			if (sEnv.equalsIgnoreCase("E4"))
			{
				URL = "https://10.78.193.233:443/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("E7"))
			{
				URL = "https://10.78.195.105/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("C2"))
			{
				URL = "https://10.78.221.37/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("E8"))
			{
				URL = "https://10.78.195.233/callcenterOUI_enu/start.swe?SWECmd=AutoOn";
			}
			if (sEnv.equalsIgnoreCase("SUP02"))
			{
				URL = "https://crm01-newvoesup02.newvoe.eu/callcenterOUI_enu/start.swe?";
			}
			getDriver().quit();
			openAt(URL);   
			getDriver().manage().window().maximize();


			/*if (Common.waitForElement(ContinueToWebsiteLinkWebElement)) {
				ContinueToWebsiteLink.click();
			}*/
			Thread.sleep(2000);
			//SikuliUtility.ClickTrustedPopUp(SikulifilePath);
			Thread.sleep(2000);
			Common.waitForElement(loginButtonsync);
			//Assert.assertTrue("Login Button is not visible", loginButton.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
			userNameTextBox.type(username);	     		
			passwordTextBox.type(password);	     	
			long startTime = System.currentTimeMillis();
			loginButton.click();

			//Homepage.withTimeoutOf(120,TimeUnit.SECONDS).isVisible();
			for (int j = 0  ;j < 3;j++) {
				boolean flag=Common.waitForElement(Homepage1);
				if (flag==false) {
					getDriver().close();
					openAt(URL);

					getDriver().manage().window().maximize();


					if (Common.waitForElement(ContinueToWebsiteLinkWebElement)) {
						ContinueToWebsiteLink.click();
					}
					Thread.sleep(2000);
					Common.waitForElement(loginButtonsync);
					//Assert.assertTrue("Login Button is not visible", loginButton.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
					userNameTextBox.type(username);		
					passwordTextBox.type(password);

					report.Info("Login Button click");
					loginButton.click();
					Thread.sleep(2000);
				}
				else{
					break;
				}
			}
			SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());

		//	Assert.assertTrue("Login Failed", Common.waitForElement(AccountsTabSync));
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			report.Info("Logged in ,Total Login Time " + totalTime);
		}

	}


	
	@Step
	public boolean isLoginSuccessful() {
		return !containsElements("//input[@name='SWEUserName']");
	}
	
	@FindBy(name="j_username")
	WebElementFacade userNameTextBoxOSM;

	@FindBy(name="j_password")
	WebElementFacade passwordTextBoxOSM;

	@FindBy(name="submit")
	WebElementFacade loginButtonOSM;
	
	@FindBy(xpath=".//*[@id='Body']/form/p[1]/b")
	WebElementFacade HomepageOSM;
	
	@Step
	public void LoginToOSM(String rowName,String EnvURL) throws IOException, InterruptedException, AWTException {		

		String filePath = "\\src\\test\\resources\\data\\OSM.xls";
		String dataSheet = "LoginToOSM";
		//ReadWorkbook readWorkbook = new ReadWorkbook();
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowName, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
		for (int i = 0  ;i < tableMap.get("Username").size();i++) {
			String username = tableMap.get("Username").get(i);
			String password = tableMap.get("Password").get(i);
			String URL = tableMap.get("EnvURL").get(i);			
	     		
			openAt(URL);   
	     	getDriver().manage().window().maximize();
	     		

	     		//Assert.assertTrue("Login Button is not visible", loginButton.isVisible());
	     		Assert.assertTrue("Login Button is not visible", loginButtonOSM.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
	     		userNameTextBoxOSM.type(username);	     		
	     		passwordTextBoxOSM.type(password);	     	
	     		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
	     		loginButtonOSM.click();
	     		report.Info("Login Button click");
				//Thread.sleep(8000);
	     		HomepageOSM.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
				//Homepage.waitUntilVisible();
	     		SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
				Assert.assertTrue("Login Failed", HomepageOSM.isDisplayed());
				report.Info("Login Page is displayed successfully");
		}

	}
	
}
