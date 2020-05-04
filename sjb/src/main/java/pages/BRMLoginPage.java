package pages;
import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
public class BRMLoginPage  extends PageObject{
	private static final TimeUnit SECONDS = null;
	@Steps
	private ReadWorkbook readWorkbook;
	
	@Steps
	private SikuliUtility SikuliUtility;
	
	String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";

	@Steps
	ReportMessege report;
   public static WebDriver driver = null;
	
	@FindBy(xpath=".//*[@id='id']")
	WebElementFacade BIPUserName;
	
	@FindBy(xpath=".//*[@id='passwd']")
	WebElementFacade BIPPassword;
	
	@FindBy(xpath=".//*[@name='SUBMIT_BUTTON']")
	WebElementFacade BIPLogin;
	
	@FindBy(linkText="Home")
	WebElementFacade BIPHomeLink;
	
	
	
	

    @Step
    public void LoginToBIP(String rowName) throws IOException, InterruptedException {		

    	String Environment = Serenity.sessionVariableCalled("Environment").toString();
    	//String Environment = "E7";
	String filePath = "\\src\\test\\resources\\data\\OracleBI.xls";
	String dataSheet = "LoginToOracleBI";	
	Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowName, filePath, dataSheet);
	readWorkbook.testData(tableMap);
	//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
	for (int i = 0  ;i < tableMap.get("RowNo").size();i++) {
		String username = tableMap.get("Username").get(i);
		String password = tableMap.get("Password").get(i);
		String URL = tableMap.get(Environment).get(i);			
     		
		openAt(URL);  
		Thread.sleep(1000);
     	getDriver().manage().window().maximize();
     		

     		//Assert.assertTrue("Login Button is not visible", loginButton.isVisible());
     		Assert.assertTrue("Login Button is not visible", BIPLogin.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());
     		BIPUserName.type(username);	     		
     		BIPPassword.type(password);	     	
     		
     		BIPLogin.click();
     		report.Info("Login Button click");
			//Thread.sleep(8000);
     		BIPHomeLink.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
			//Homepage.waitUntilVisible();
			Assert.assertTrue("Login Failed", BIPHomeLink.isDisplayed());
			report.Info("Login Page is displayed successfully");
	}

}
    
    @FindBy(xpath=".//*[@id='pt1:repository_alias::content']")
    WebElementFacade ODIRepository;
    
    @FindBy(xpath=".//*[@id='pt1:j_username::content']")
    WebElementFacade ODIUsername;
    
    @FindBy(xpath=".//*[@id='pt1:j_password::content']")
    WebElementFacade ODIPassword;
    
    @FindBy(xpath=".//*[@id='pt1:login']")
    WebElementFacade ODILoginButton;
    
    @FindBy(xpath=".//*[@id='shell:logout']")
    WebElementFacade LogoutButton;

    @Step
    public void LoginToODI(String rowName) throws IOException, InterruptedException, AWTException, FindFailed {          

        String filePath = "\\src\\test\\resources\\data\\ODI.xls";
        String dataSheet = "LoginToODI";
        
        Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowName, filePath, dataSheet);
        readWorkbook.testData(tableMap);
        for (int i = 0  ;i < tableMap.get("RowNo").size();i++) {
              String username = tableMap.get("Username").get(i);
              String password = tableMap.get("Password").get(i);    
              //Serenity.setSessionVariable("Environment").to("E7");
              String Environment = Serenity.sessionVariableCalled("Environment").toString();   
              
              String URL = tableMap.get(Environment).get(i);  
              String currentDir = System.getProperty("user.dir");
              getDriver().quit();
  			openAt(URL);

  			getDriver().manage().window().maximize();
  			getDriver().navigate().refresh();
              /*driver = new FirefoxDriver();

              driver.get(URL);*/
   
  			getDriver().findElement(By.xpath(".//input[@id='pt1:j_username::content']")).click();
  			getDriver().findElement(By.xpath(".//input[@id='pt1:j_username::content']")).sendKeys(username);
  			getDriver().findElement(By.xpath(".//*[@id='pt1:j_password::content']")).sendKeys(password);
  			getDriver().findElement(By.xpath(".//*[@id='pt1:login']")).click();       
              Thread.sleep(5000);
              SikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
              report.Info("Login Button click");
                    Assert.assertTrue("Login Failed", getDriver().findElement(By.xpath(".//*[@id='shell:logout']")).isDisplayed());
                    report.Info("Login Page is displayed successfully");
        }

}
   
    @FindBy(xpath=".//*[@id='j_username']")
    WebElementFacade WCCUsername;
    
    @FindBy(xpath=".//*[@id='j_password']")
    WebElementFacade WCCPassword;
    
    @FindBy(xpath=".//*[@value='Sign In']")
    WebElementFacade WCCLogin;
    
    @FindBy(xpath=".//*[@id='MENU_A_SEARCH']/a")
    WebElementFacade LoginValidation;
    
    @Step
    public void LoginToWCCfn(String iRowNo,String EnvURL) throws IOException, InterruptedException {		

	String filePath = "\\src\\test\\resources\\data\\ODI.xls";
	String dataSheet = "LoginToWCC";	
	String Environment = Serenity.sessionVariableCalled("Environment").toString();
	//String Environment = "E7";
	
	Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(iRowNo, filePath, dataSheet);
	readWorkbook.testData(tableMap);
	
	//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
	for (int i = 0  ;i < tableMap.get("RowNo").size();i++) {
		String username = tableMap.get("Username").get(i);
		String password = tableMap.get("Password").get(i);	
		String URL = tableMap.get(Environment).get(i);
		
		openAt(URL);   
     	getDriver().manage().window().maximize();    		
     		Assert.assertTrue("Login Button is not visible", WCCLogin.withTimeoutOf(120,TimeUnit.SECONDS).isVisible());     		
     		WCCUsername.type(username);	     		
     		WCCPassword.type(password);  
     		WCCLogin.click();   
     		
     		LoginValidation.withTimeoutOf(120,TimeUnit.SECONDS).isDisplayed();
			Assert.assertTrue("Login Failed", LoginValidation.isDisplayed());
			//report.Info("Login Page is displayed successfully");			
	}

}
	
}
