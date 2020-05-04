package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.sikuli.natives.SysUtil;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import net.serenitybdd.core.IgnoredStepException;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.PuttyCommon;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;
import utilities.common;
import pages.DatabasePage;


public class BRMPuttyPages extends PageObject{
	@Steps
	ReportMessege report;

	@Steps
	private ReadWorkbook readWorkbook;

	@Steps
	private PuttyCommon puttyCommon;

	@Steps
	private DatabasePage Database;



	@Steps
	private common common1;
	@Steps
	private SikuliUtility sikuliUtility;

	String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
	String SikulifilePathForSavingFile = "";
	Screen  s=new Screen();
	String currentDir = System.getProperty("user.dir");

	public String FolderName ="";
	@Step
	public void Prerequisite(String FolderName) throws JSchException, IOException, SftpException{



		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "LoginToSiebel";
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow("RETAIL", filePath, dataSheet);
		readWorkbook.testData(tableMap);
		for (int i = 0  ;i < tableMap.get("Username").size();i++) {			
			String sEnv = tableMap.get("EnvURL").get(i);

			Serenity.setSessionVariable("Env").to(sEnv);
		}            		
		Serenity.setSessionVariable("FolderName").to(FolderName);


		this.FolderName =FolderName;


		System.out.println("myCurrentDir" +currentDir);

		try{
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date();       		   

			File file =new File(currentDir+"\\BRM_Outputs\\" +sdfDate.format(now));
			if(!file.exists()){		
				new File(currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)).mkdir();			
			}
			String Randomno = Integer.toString((11111 + (int)(Math.random()*222222)));  

			File ScriptFolder =new File(currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)+"\\"+FolderName+"_"+Randomno);
			if(!ScriptFolder.exists()){		

				System.out.println("Inside if " + currentDir+"\\BRM_Outputs\\" +sdfDate.format(now));
				new File(currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)+"\\"+FolderName+"_"+Randomno).mkdir();	
				this.SikulifilePathForSavingFile = (currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)+"\\"+FolderName+"_"+Randomno);
			}
			else{


				new File(currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)+"\\"+FolderName+"_"+Randomno).mkdir();
				this.SikulifilePathForSavingFile = (currentDir+"\\BRM_Outputs\\" +sdfDate.format(now)+"\\"+FolderName+"_"+Randomno);
			}

		}

		catch(Exception e){

		}           		
		Serenity.setSessionVariable("SikulifilePathForSavingFile").to(this.SikulifilePathForSavingFile);

	} 



	@Step
	public void BRMPuttyLogin(String PuttyLogFileName,String Environment ) throws FindFailed, InterruptedException, IOException, AWTException, JSchException, SftpException {


		String filePath = "\\src\\test\\resources\\data\\Account.xls";
		String dataSheet = "LoginToSiebel";

		//Runtime.getRuntime().exec("Y:\\Putty.exe");
		//ReadWorkbook readWorkbook = new ReadWorkbook();
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Environment, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		//Map <String,Map<Integer, String>> tableMap = this.testData(rowNo);
		for (int i = 0  ;i < tableMap.get("Username").size();i++) {

			String BRMEnv = tableMap.get("Env").get(i);
			System.out.println("BRMEnv is :- " +BRMEnv);
			Serenity.setSessionVariable("Environment").to(BRMEnv);
		}

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_D);

		robot.keyRelease(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		report.Info("All Application minimized");
		//Serenity.setSessionVariable("SikulifilePathForSavingFile").to(SikulifilePathForSavingFile);


		filePath = "\\src\\test\\resources\\data\\Putty.xls";
		dataSheet = "LoginToPutty";
		Environment = Serenity.sessionVariableCalled("Environment").toString();
		Serenity.setSessionVariable("Env").to(Environment);

		System.out.println("Environment is :- " +Environment);
		tableMap = readWorkbook.readRow(PuttyLogFileName, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		getDriver().quit();
		App.close("putty.exe");

		for (int i = 0  ;i < tableMap.get("RowN").size();i++) {

			String Port = tableMap.get("Port").get(i);
			String Number = tableMap.get("Number").get(i);
			String LogFileName = tableMap.get("LogFileName").get(i);
			Serenity.setSessionVariable("LogFileName").to(LogFileName);
			String RootUser = tableMap.get("RootUser").get(i);		
			String RootPassword = tableMap.get("RootPassword").get(i);
			String Passphrase="Passphrase"+Environment;
			Thread.sleep(1000);
			//						if(s.exists(SikulifilePath+"\\GoToDesktop.png",2) != null)
			//		                	
			//		                {
			//		                  s.click(SikulifilePath+"\\GoToDesktop.png");                         
			//		                  report.Info("All Application minimized");
			//		                }
			//s.click(SikulifilePath+"\\GoToDesktop.png");
			Thread.sleep(1000);
			App.open("C:\\Program Files\\PuTTY\\putty.exe");

			Thread.sleep(1000);
			Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
			report.Info("Putty App Launched");
			String HostName="HostName"+Environment;
			s.type(tableMap.get(HostName).get(i));
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			//s.click(SikulifilePath+"\\PuttyLoginLogs.png");
			for(int loop=0;loop<3;loop++){
				if(s.exists(SikulifilePath+"\\PuttyLoginLogs.png",2) != null){
					s.click(SikulifilePath+"\\PuttyLoginLogs.png");
				}else
					break;
			}
			s.click(SikulifilePath+"\\AllSessionOutputPutty.png");

			for(int loop=0;loop<2;loop++){
				if(s.exists(SikulifilePath+"\\AllSessionCheckboxverify.png",2) != null){
					break;
				}
				else{
					s.click(SikulifilePath+"\\AllSessionOutputPutty.png");
					System.out.println("Clicked");
				}
			}
			s.doubleClick(SikulifilePath+"\\PuttyLogFilePath.png");
			s.type(Key.DELETE);
			s.doubleClick();
			s.type(Key.DELETE);
			s.type(SikulifilePathForSavingFile+"\\"+LogFileName);
			s.click(SikulifilePath+"\\AlwaysAppendPuttyLogs.png");
			for(int loop=0;loop<2;loop++){
				if(s.exists(SikulifilePath+"\\VerifyAlwaysAppendPuttyLogs.png",2) != null){
					break;
				}
				else{
					s.click(SikulifilePath+"\\AlwaysAppendPuttyLogs.png");
					System.out.println("Clicked");
				}
			}

			sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
			//s.doubleClick(SikulifilePath+"\\SSH.png");
			// if (s.exists(SikulifilePath+"\\Auth.png",2)== null)
			//s.doubleClick(SikulifilePath+"\\ReclickSSH.png");                

			// s.click(SikulifilePath+"\\Auth.png");
			// s.click(SikulifilePath+"\\AllowAgentForwarding.png");
			//s.click(SikulifilePath+"\\AllowAttemptedChanges.png");               

			String UserName="UserName"+Environment;
			//s.type(SikulifilePath+"\\PPKPath.png","C:/Users/"+(tableMap.get(UserName).get(i))+"/Putty/ssh_key_putty_newVo"+Environment+".ppk");
			//s.type(SikulifilePath+"\\PPKPath.png","C:/Users/"+(tableMap.get(UserName).get(i))+"/Putty/ssh_key_putty_newVo"+Environment+".ppk");
			Serenity.setSessionVariable("UserName").to(tableMap.get(UserName).get(i));
			Serenity.setSessionVariable("HostName").to(tableMap.get(HostName).get(i));
			Serenity.setSessionVariable("Passphrase").to(tableMap.get(Passphrase).get(i));
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			// s.click(SikulifilePath+"\\Open.png");
			//s.click(SikulifilePath+"\\Open.png");
			Thread.sleep(2000);
			for(int loop=0;loop<3;loop++){
				if(s.exists(SikulifilePath+"\\Open.png",2) != null)

				{
					s.click(SikulifilePath+"\\Open.png");                         
					report.Info("Open Button clicked on second atempt");
				}
				else{
					break;
				}
			}
			Thread.sleep(2000);
			//System.out.println("checking Yes option");
			// System.out.println("SikulifilePath : "+SikulifilePath);
			//Runtime.getRuntime().exec("C:\\AutoIt\\abc.exe");
			//Screen  s1=new Screen();
			/* if(s.exists(SikulifilePath+"\\PuttyPopUp.png",4) != null){
                	s.click(SikulifilePath+"\\PuttyPopUp.png");
                	s1.delayClick(3000);
                	robot.keyPress(KeyEvent.VK_LEFT);
    			    robot.keyRelease(KeyEvent.VK_LEFT);
    			    robot.keyPress(KeyEvent.VK_LEFT);
    			    robot.keyRelease(KeyEvent.VK_LEFT);

    			    robot.keyPress(KeyEvent.VK_ENTER);
    			    robot.keyRelease(KeyEvent.VK_ENTER);
                	//s.click(SikulifilePath+"\\PuttyPopUp.png");
                	Thread.sleep(2000);
                	s.click(SikulifilePath+"\\Yes.png");
                }*/
			/*if(s.exists(SikulifilePath+"\\Yes.png",4) != null)        	
                {*/
			// System.out.println("clicking Yes option");


			/*  robot.keyPress(KeyEvent.VK_LEFT);
			    robot.keyRelease(KeyEvent.VK_LEFT);
			    robot.keyPress(KeyEvent.VK_LEFT);
			    robot.keyRelease(KeyEvent.VK_LEFT);

			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);

			    Thread.sleep(1000);
                 System.out.println("Clicked Yes option");
                  report.Info("Yes Exists, Clicked on Yes");*/

			Thread.sleep(1000);
			//s.click(SikulifilePath+"\\MaximisePutty.png");
			for(int loop=0;loop<1;loop++){
				if(s.exists(SikulifilePath+"\\MaximisePutty.png",2) != null)

				{	
					s.click(SikulifilePath+"\\MaximisePutty.png");  
					Thread.sleep(2000);
				}
				else{
					break;
				}

			}

			puttyCommon.PuttyType("login as:", tableMap.get(UserName).get(i)); 			
			/*if(!Environment.equals("SUP01")){
                    	  puttyCommon.PuttyType("enter a number","01");                      
                    	  puttyCommon.PuttyType("Connect to","Y");
                      	}*/
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			//puttyCommon.PuttyType("password:", "P@ssw0rd");
			//puttyCommon.PuttyType("$",RootUser);         
			puttyCommon.PuttyType("password:",RootPassword);

			report.Info("Switch User to PIN Sucess");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		}
	}



	@Step
	public void AccountChargeApply() throws Exception
	{
		puttyCommon.PuttyType("$","cd $PIN_HOME/apps/pin_billd_01");
		puttyCommon.PuttyType("pin_billd","pin_cycle_fees -purchase -start 1 -end 0 -verbose");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		boolean b=puttyCommon.ValidatePutty("pin_cycle_fees -purchase -verbose","Total number of records processed");
		if (b==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		report.Info("pin_cycle_fees executed successfully");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
	}

	@Step
	public void PuttyPayment(String BillingProfileType) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Payment";     	

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(BillingProfileType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
			String  sPaymentType = tableMap.get("PaymentType").get(i);                 	
			String sAccountNo = tableMap.get("AccountNo").get(i);  
			String  sAmount = tableMap.get("Amount").get(i);                 	
			String sBillNoOption = tableMap.get("BillNoOption").get(i); 
			String sOverPayment = tableMap.get("OverPayment").get(i);

			if(sOverPayment.equals("")){
				sOverPayment = "N";
			}


			if(sPaymentType.equals("5")){            	        		
				Serenity.setSessionVariable("OverPayment").to(sAmount);
			}
			try{
				if(!Serenity.sessionVariableCalled("sAmount").toString().equalsIgnoreCase(null)){
					sAmount = Serenity.sessionVariableCalled("sAmount").toString();            	        	
				}
			}

			catch(Exception e){

			}
			if(sAmount.equals("AMOUNT0")){
				sAmount = Serenity.sessionVariableCalled("AMOUNT0").toString();
			}
			if(sAmount.equals("AMOUNT1")){
				sAmount = Serenity.sessionVariableCalled("AMOUNT1").toString();
			}
			if(sAmount.equals("")){
				sAmount = "";
			}
			else{
				Serenity.setSessionVariable("AMOUNT0").to(sAmount);
				report.Info("AMOUNT0 is set to :- " +sAmount);
			}


			if(sAccountNo.equals("ACCNTMSISDN")){
				sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();
			}

			if(sAccountNo.equals("ACCNTMSISDN0")){
				sAccountNo = Serenity.sessionVariableCalled("ACCOUNTNO0").toString();
			}

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/payment");         
			puttyCommon.PuttyType("payment","./payments.sh");
			puttyCommon.PuttyType("",sPaymentType);
			puttyCommon.PuttyType("",sAccountNo);


			if (!(sPaymentType.equals("1")|| sPaymentType.equals("2") || sPaymentType.equals("3")))
			{
				puttyCommon.PuttyType("",sBillNoOption);
			}
			if(sPaymentType.equals("5"))  {
				long unixTime = System.currentTimeMillis() / 1000L;
				unixTime = unixTime - 86400;
				Serenity.setSessionVariable("UNIXTIME0").to(unixTime);
				Database.ExecuteDBQuery("UpdatePaymentDate");
				if(sBillNoOption.equals("N"))            	        			
					puttyCommon.PuttyType("",sAmount);
				puttyCommon.PuttyType("",sOverPayment);
			}
			else             	        	
				puttyCommon.PuttyType("",sAmount);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			report.Info("sPaymentType :- "+sPaymentType +"  , sAccountNo :- "+sAccountNo +"  , sBillNoOption :- "+sBillNoOption +"  , sAmount :- "+sAmount +"  , sOverPayment :- "+sOverPayment);

			boolean b=puttyCommon.ValidatePutty("./payments.sh","payment done");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			report.Info("Payment Done");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Serenity.setSessionVariable("AccountNo").to(sAccountNo);
		}
	}

	@Step
	public void BillNow() throws Exception 
	{
		puttyCommon.PuttyType("$","cd $PIN_HOME/automation/bill_invoice");
		puttyCommon.PuttyType("$","./bill_now.sh");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		boolean b=puttyCommon.ValidatePutty("bill_now.sh","Bill now done");
		if (b==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		report.Info("Bill Now Done");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

	}

	@Step
	public void UnixTimestamp(String Index) throws Exception 
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Timestamp";           

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Index, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
			String  iDays = tableMap.get("Days").get(i);                      
			String  iHr = tableMap.get("Hr").get(i);  
			String  iMin = tableMap.get("Min").get(i);                  
			String  iSec = tableMap.get("Sec").get(i);  
			String  sWeekDay = tableMap.get("WeekDay").get(i);
			Serenity.setSessionVariable("Days").to(iDays);
			Serenity.setSessionVariable("Hr").to(iHr);
			Serenity.setSessionVariable("Min").to(iMin);
			Serenity.setSessionVariable("Sec").to(iSec);
			Serenity.setSessionVariable("WeekDay").to(sWeekDay);
			//System.out.println("Days :-"+iDays);
			// System.out.println("Hr :-"+iHr);
			//System.out.println("Min :-"+iMin);

		}
	}


	@Step	                
	public void Purchase_Products(String Account_No) throws IOException, AWTException, InterruptedException, ClassNotFoundException, SQLException {

		if(!Account_No.equals(""))
		{			
			Serenity.setSessionVariable("AccountNo").to(Account_No);
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/sql_query");		
			puttyCommon.PuttyType("$","./purchased_product.sh "+Account_No);
			Database.ExecuteDBQuery("MSISDNFromAccount");
			String MSISDNFromAccount = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
			Serenity.setSessionVariable("ACCNTMSISDN").to(MSISDNFromAccount);
			//String MSISDNFromAccount = "447389841133";
			report.Info("MSISDN From Account :- "+MSISDNFromAccount);
			System.out.println("MSISDN From Account :- "+MSISDNFromAccount);
		}
		else {
			Account_No = Serenity.sessionVariableCalled("AccountNo").toString();
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/sql_query");		
			puttyCommon.PuttyType("$","./purchased_product.sh "+Account_No);
			Database.ExecuteDBQuery("MSISDNFromAccount");
			String MSISDNFromAccount = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
			Serenity.setSessionVariable("ACCNTMSISDN").to(MSISDNFromAccount);
			report.Info("MSISDN From Account :- "+MSISDNFromAccount);
			System.out.println("MSISDN From Account :- "+MSISDNFromAccount);


		}
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
	}
	@Step
	public void Bill_fn(String Index) throws Exception 
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Billing";     	

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Index, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Accnt").size();i++) {   
			String  sIndex = tableMap.get("Index").get(i);                 	
			String sAccnt = tableMap.get("Accnt").get(i);  
			String  sSequence = tableMap.get("Sequence").get(i);                 	

			if (sIndex.equals("")) 
				sIndex="0";

			if (sSequence.equals(""))        		
				sSequence="1";

			String Account_No = Serenity.sessionVariableCalled("AccountNo").toString();
			if (sAccnt.contains("ACCOUNTNO0"))
			{
				Account_No=Serenity.sessionVariableCalled("ACCOUNTNO0").toString();
			}
			if (sAccnt.contains("AccountNo1"))
			{
				Account_No=Serenity.sessionVariableCalled("AccountNo1").toString();
			}
			if (sAccnt.contains("ACCOUNTNO1"))
			{
				Account_No=Serenity.sessionVariableCalled("ACCOUNTNO1").toString();
			}
			if (sAccnt.contains("AccountNo"))
			{
				Account_No=Serenity.sessionVariableCalled("AccountNo").toString();
			}

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/bill_invoice");
			puttyCommon.PuttyType("bill_invoice","./billing.sh");
			puttyCommon.PuttyType("enter Account no for billing",Account_No);
			puttyCommon.PuttyType("sequence",sSequence);
			boolean b=puttyCommon.ValidatePutty("./billing.sh","Bill_No");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			b=puttyCommon.ValidatePutty("","Total number of records processed = 1");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				report.Info("Billing Failed");
				Assert.assertTrue(false);
			}
			puttyCommon.CapturePuttyData("LINEAFTER:Bill_No","BillNo",sIndex);	
			report.Info("Month End Billing Done :- BillNo - "+Serenity.sessionVariableCalled("BillNo").toString());
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}
	}


	@Step
	public void Invoice_fn(String Index) throws Exception 
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Invoicing";     	

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Index, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Accnt").size();i++) {   
			String  sIndex = tableMap.get("Index").get(i);                 	
			String sAccnt = tableMap.get("Accnt").get(i);  
			String  sSequence = tableMap.get("Sequence").get(i);             	
			String  sInvoiceTimeSeq = tableMap.get("InvoiceTimeSeq").get(i);  
			if (sIndex.equals("")) 
				sIndex="0";        			

			if (sSequence.equals(""))
				sSequence="1";

			if (sInvoiceTimeSeq.equals("")) 
				sInvoiceTimeSeq="1;0";        			

			String Account_No = Serenity.sessionVariableCalled("AccountNo").toString();
			if (sAccnt.contains("ACCOUNTNO0"))
			{
				Account_No=Serenity.sessionVariableCalled("ACCOUNTNO0").toString();
			}
			if (sAccnt.contains("AccountNo1"))
			{
				Account_No=Serenity.sessionVariableCalled("AccountNo1").toString();
			}
			if (sAccnt.contains("AccountNo"))
			{
				Account_No=Serenity.sessionVariableCalled("AccountNo").toString();
			}
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/bill_invoice");
			puttyCommon.PuttyType("bill_invoice","./invoicing.sh");
			puttyCommon.PuttyType("enter Account no for Invoicing",Account_No);
			puttyCommon.PuttyType("sequence",sSequence);
			puttyCommon.PuttyType("seq;sec",sInvoiceTimeSeq);
			//sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			//Assert.assertTrue("Month End Invocing Failed",s.exists(SikulifilePath+"\\BillNowDone.png",15) != null);
			////if(s.exists(SikulifilePath+"\\BillNowDone.png", 15) != null){
			boolean b=puttyCommon.ValidatePutty("./invoicing.sh","Invoice poid is");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}


			puttyCommon.CapturePuttyData("Invoice poid is", "PoidID", sIndex);
			String PoidID =Serenity.sessionVariableCalled("PoidID").toString().replace("Invoice poid is " , "");	
			report.Info("Month End Invocing Done :- Poid ID - "+PoidID);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);


		}
	}
	@Step
	public void PuttyRating(String RatingType) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Rating";  
		Screen  s=new Screen();
		long TIMESTAMP0=0;

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RatingType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
			String  sStreamChoice = tableMap.get("StreamChoice").get(i);                 	
			String sCDRType = tableMap.get("CDRType").get(i);  
			String  sNoEDR = tableMap.get("NoEDR").get(i);                 	
			String sDomesticRoaming = tableMap.get("Domestic/Roaming").get(i); 
			String sCr_Dr = tableMap.get("CrDr").get(i);
			String  sTax = tableMap.get("Tax").get(i);                 	
			String sDestination = tableMap.get("Destination").get(i); 
			String sBearerCapability= tableMap.get("BearerCapability").get(i);
			String sCountryCode= tableMap.get("CountryCode").get(i);
			String sCallingNo= tableMap.get("CallingNo").get(i);
			String sCalledNo= tableMap.get("CalledNo").get(i);
			String sTimestamp= tableMap.get("Timestamp").get(i);
			String sCnumber= tableMap.get("Cnumber").get(i);
			String sSMSQuantity= tableMap.get("SMSQuantity").get(i);
			String sGSM= tableMap.get("GSM").get(i);
			String sDuration= tableMap.get("Duration").get(i);            	        
			String sTariffClass= tableMap.get("TariffClass").get(i);
			String sSrcCountry= tableMap.get("SrcCountry").get(i);
			String sDetnCountry= tableMap.get("DetnCountry").get(i);
			String sRateGBP= tableMap.get("RateGBP").get(i);
			String iIndex= tableMap.get("Index").get(i);
			String sInOutPartial= tableMap.get("InOutPartial").get(i);
			String sTransactionType = tableMap.get("TransactionType").get(i);
			String sOldDayCDR =tableMap.get("OldDayCDR").get(i);
			String sC_D =tableMap.get("CD").get(i);
			Serenity.setSessionVariable("RatingIndex").to(iIndex);
			//String MSISDNFromAccount = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();	     

			try {
				if(sCountryCode.equals("")){                                      
					sCountryCode = Serenity.sessionVariableCalled("CountryCode").toString();
				}}
			catch(Exception e){}                            
			try {
				if (sCallingNo.equals("ACCNTMSISDN")) {                     
					if(!Serenity.sessionVariableCalled("ACCNTMSISDN0").equals("")){ 

						sCallingNo = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
					}}
				else if (sCallingNo.equals("ACCNTMSISDN1"))     
				{
					if(!Serenity.sessionVariableCalled("ACCNTMSISDN1").equals("")){ 

						sCallingNo = Serenity.sessionVariableCalled("ACCNTMSISDN1").toString();
					}}
				else {System.out.println("Issue in ACCNT MSISDN ,please check the putty rating excel ");}


			}
			catch(Exception e){}                            

			try {
				if(!Serenity.sessionVariableCalled("sCalledNo").equals("")){
					sCalledNo = Serenity.sessionVariableCalled("sCalledNo").toString();

				}}
			catch(Exception e){}
			try {
				if(!Serenity.sessionVariableCalled("Cnumber").equals("")){
					sCnumber = Serenity.sessionVariableCalled("Cnumber").toString();            	        	
				}}
			catch(Exception e){}
			try {
				if(!sSMSQuantity.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sSMSQuantity);
				}}
			catch(Exception e){}
			try {
				if(sGSM.equals("")){            	        		
					sGSM = Serenity.sessionVariableCalled("GSM").toString();
				}}
			catch(Exception e){}
			try {
				if(!sDuration.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sDuration);
				}}
			catch(Exception e){}
			try {
				if(sTariffClass.equals("")){
					sTariffClass = Serenity.sessionVariableCalled("TariffClass").toString();
				}
			}
			catch(Exception e){}
			try {
				if(sSrcCountry.equals("")){
					sSrcCountry = Serenity.sessionVariableCalled("SrcCountry").toString();
				}}
			catch(Exception e){}
			try {
				if(sDetnCountry.equals("")){
					sDetnCountry = Serenity.sessionVariableCalled("DetnCountry").toString();
				}}
			catch(Exception e){}
			Serenity.setSessionVariable("RateGBP").to(sRateGBP);
			if(iIndex.equals("")){
				iIndex = "0";
			}

			long unixTimestamp = Instant.now().getEpochSecond();
			report.Info("Unix Time Stamp: "+unixTimestamp);

			if(sTimestamp.equals("")){



				Database.ExecuteDBQuery("AccountCreationTime");
				report.Info("AccountCreationTime    "+Serenity.sessionVariableCalled("MAXCREATED0").toString());
				long sAccountCreationTime = Integer.parseInt(Serenity.sessionVariableCalled("MAXCREATED0").toString()); 
				report.Info("sAccountCreationTime Time Stamp: "+sAccountCreationTime);
				if (sAccountCreationTime<=unixTimestamp){

					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}

				if (sAccountCreationTime==unixTimestamp) {
					TIMESTAMP0 = unixTimestamp+14400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}
				if (sAccountCreationTime==0) {
					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}
				if (Long.toString(sAccountCreationTime)==sOldDayCDR) {
					TIMESTAMP0 = unixTimestamp-5270400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}
				if (sAccountCreationTime>unixTimestamp){                                        
					report.Info("Account Creation Time :- "+sAccountCreationTime +"  , is greater than Unix time stamp :- "+unixTimestamp);
					Assert.assertTrue(false);     
				}
				if (sOldDayCDR.equals("FLOOB")){                                        
					TIMESTAMP0 = unixTimestamp-93600;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);    
				}
			}
			else if (sTimestamp.equals("2ndDay")){
				TIMESTAMP0 = unixTimestamp+172800;
				report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
			}
			else if (sTimestamp.equals("3rdDay")){
				TIMESTAMP0 = unixTimestamp+259200;
				report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
			}

			else {
				TIMESTAMP0 =Long.parseLong(Serenity.sessionVariableCalled("TIMESTAMP0").toString());
				report.Info("Time stamp extracted from DB :-  "+TIMESTAMP0);
			}
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/rating");            	        	
			puttyCommon.PuttyType("rating","./rating.sh");
			puttyCommon.PuttyType("",sStreamChoice);
			puttyCommon.PuttyType("",sCDRType);
			puttyCommon.PuttyType("EDR",sNoEDR);
			puttyCommon.PuttyType("Domestic/Roaming",sDomesticRoaming);
			puttyCommon.PuttyType("code",sCountryCode);
			puttyCommon.PuttyType("In/Out/Partial",sInOutPartial);
			puttyCommon.PuttyType("A number",sCallingNo);
			puttyCommon.PuttyType("B number",sCalledNo);
			puttyCommon.PuttyType("C number",sCnumber);
			puttyCommon.PuttyType("credit/debit",sCr_Dr);
			puttyCommon.PuttyType("Duration",sDuration);
			puttyCommon.PuttyType("quantity",sSMSQuantity);
			puttyCommon.PuttyType("Rate in GBP",sRateGBP);
			puttyCommon.PuttyType("Transaction Type",sTransactionType);            	      
			puttyCommon.PuttyType("Timestamp",Long.toString(TIMESTAMP0));
			puttyCommon.PuttyType("Destination",sDestination);
			puttyCommon.PuttyType("Bearer",sBearerCapability);
			puttyCommon.PuttyType("(C/D)",sC_D);
			puttyCommon.PuttyType("tax",sTax);
			puttyCommon.PuttyType("GSM",sGSM);
			puttyCommon.PuttyType("Tariff class",sTariffClass);
			puttyCommon.PuttyType("Source country",sSrcCountry);
			puttyCommon.PuttyType("Destination country",sDetnCountry);
			boolean b=puttyCommon.CapturePuttyData("LINEAFTER:Your CDR is", "CDRName", iIndex);
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			//puttyCommon.CapturePuttyData("LINEAFTER:Your CDR is", "CDRName", iIndex);

			String sCDR=Serenity.sessionVariableCalled("CDRName").toString();
			puttyCommon.PuttyType("$","cd $PIPE_DIR/acrinput/router");            	        	
			puttyCommon.PuttyType("router","cat "+sCDR);
			Thread.sleep(1000);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			sCDR=sCDR.replace(".data", "");
			String[] sCDRs = sCDR.split("_");
			Serenity.setSessionVariable("CDRSeq").to(sCDRs[3]);
			//sCDR = sCDR.replace(sCDRs[0], "");
			if (sOldDayCDR.equals("Y"))
			{
				puttyCommon.PuttyType("$","cd $PIPE_DIR/log/pin01/ifw/stream");	
				boolean sTypeCapturePuttyData = puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDR + "*",sCDR +".data.log","OutCDR");
				if (sTypeCapturePuttyData==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Assert.assertTrue(false);
					//puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDR + "*",sCDR +".data.log","OutCDR");
				}
			}
			else 
			{
				puttyCommon.PuttyType("$","cd $PIPE_DIR/ploutput/pin01/pin_rel_01/archive");
				boolean sTypeCapturePuttyData = puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDRs[1]+"*"+sCDRs[3] + "* | tail -1",sCDRs[3] +".out.bc","OutCDR");
				/*if (sTypeCapturePuttyData==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);   
				//puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDRs[3] + "*",sCDRs[3] +".out.bc","OutCDR");
			}*/
			}
			try {
				String sOutCDR=Serenity.sessionVariableCalled("OutCDR").toString();
				sOutCDR = sOutCDR.replace("[00m", "");
				puttyCommon.PuttyType("$","cat "+sOutCDR);
			}
			catch(Exception e) {

			}
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}

	}

	@Step
	public void PuttyBulkRating(String RatingType) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Rating";  
		Screen  s=new Screen();
		long TIMESTAMP0=0;

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RatingType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
			String sStreamChoice = tableMap.get("StreamChoice").get(i);                 	
			String sCDRType = tableMap.get("CDRType").get(i);  
			String sNoEDR = tableMap.get("NoEDR").get(i);                 	
			String sDomesticRoaming = tableMap.get("Domestic/Roaming").get(i); 
			String sCr_Dr = tableMap.get("CrDr").get(i);
			String sTax = tableMap.get("Tax").get(i);                 	
			String sDestination = tableMap.get("Destination").get(i); 
			String sBearerCapability= tableMap.get("BearerCapability").get(i);
			String sCountryCode= tableMap.get("CountryCode").get(i);
			String sCallingNo= tableMap.get("CallingNo").get(i);
			String sCalledNo= tableMap.get("CalledNo").get(i);
			String sTimestamp= tableMap.get("Timestamp").get(i);
			String sCnumber= tableMap.get("Cnumber").get(i);
			String sSMSQuantity= tableMap.get("SMSQuantity").get(i);
			String sGSM= tableMap.get("GSM").get(i);
			String sDuration= tableMap.get("Duration").get(i);            	        
			String sTariffClass= tableMap.get("TariffClass").get(i);
			String sSrcCountry= tableMap.get("SrcCountry").get(i);
			String sDetnCountry= tableMap.get("DetnCountry").get(i);
			String sRateGBP= tableMap.get("RateGBP").get(i);
			String iIndex= tableMap.get("Index").get(i);
			String sInOutPartial= tableMap.get("InOutPartial").get(i);
			String sTransactionType = tableMap.get("TransactionType").get(i);
			String sOldDayCDR =tableMap.get("OldDayCDR").get(i);
			String sC_D =tableMap.get("CD").get(i);
			Serenity.setSessionVariable("RatingIndex").to(iIndex);


			try {
				if(sCountryCode.equals("")){                                      
					sCountryCode = Serenity.sessionVariableCalled("CountryCode").toString();
				}}
			catch(Exception e){}                            
			try {
				if (sCallingNo.equals("ACCNTMSISDN")) {                     
					if(!Serenity.sessionVariableCalled("ACCNTMSISDN0").equals("")){ 

						sCallingNo = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
					}}
				else if (sCallingNo.equals("ACCNTMSISDN1"))     
				{
					if(!Serenity.sessionVariableCalled("ACCNTMSISDN1").equals("")){ 

						sCallingNo = Serenity.sessionVariableCalled("ACCNTMSISDN1").toString();
					}}
				else {System.out.println("Issue in ACCNT MSISDN ,please check the putty rating excel ");}


			}
			catch(Exception e){}                            

			try {
				if(!Serenity.sessionVariableCalled("sCalledNo").equals("")){
					sCalledNo = Serenity.sessionVariableCalled("sCalledNo").toString();

				}}
			catch(Exception e){}
			try {
				if(!Serenity.sessionVariableCalled("Cnumber").equals("")){
					sCnumber = Serenity.sessionVariableCalled("Cnumber").toString();            	        	
				}}
			catch(Exception e){}
			try {
				if(!sSMSQuantity.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sSMSQuantity);
				}}
			catch(Exception e){}
			try {
				if(sGSM.equals("")){            	        		
					sGSM = Serenity.sessionVariableCalled("GSM").toString();
				}}
			catch(Exception e){}
			try {
				if(!sDuration.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sDuration);
				}}
			catch(Exception e){}
			try {
				if(sTariffClass.equals("")){
					sTariffClass = Serenity.sessionVariableCalled("TariffClass").toString();
				}
			}
			catch(Exception e){}
			try {
				if(sSrcCountry.equals("")){
					sSrcCountry = Serenity.sessionVariableCalled("SrcCountry").toString();
				}}
			catch(Exception e){}
			try {
				if(sDetnCountry.equals("")){
					sDetnCountry = Serenity.sessionVariableCalled("DetnCountry").toString();
				}}
			catch(Exception e){}
			Serenity.setSessionVariable("RateGBP").to(sRateGBP);
			if(iIndex.equals("")){
				iIndex = "0";
			}

			if(sTimestamp.equals("")){

				long unixTimestamp = Instant.now().getEpochSecond();
				report.Info("Unix Time Stamp: "+unixTimestamp);


				Database.ExecuteDBQuery("AccountCreationTime");
				report.Info("AccountCreationTime    "+Serenity.sessionVariableCalled("MAXCREATED0").toString());
				long sAccountCreationTime = Integer.parseInt(Serenity.sessionVariableCalled("MAXCREATED0").toString()); 
				report.Info("sAccountCreationTime Time Stamp: "+sAccountCreationTime);
				if (sAccountCreationTime<=unixTimestamp){

					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}

				if (sAccountCreationTime==unixTimestamp) {
					TIMESTAMP0 = unixTimestamp+14400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}
				if (sAccountCreationTime==0) {
					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}

				if (sAccountCreationTime>unixTimestamp){                                        
					report.Info("Account Creation Time :- "+sAccountCreationTime +"  , is greater than Unix time stamp :- "+unixTimestamp);
					Assert.assertTrue(false);     
				}

			}

			else {
				TIMESTAMP0 =Long.parseLong(Serenity.sessionVariableCalled("TIMESTAMP0").toString());
				report.Info("Time stamp extracted from DB :-  "+TIMESTAMP0);
			}
			puttyCommon.PuttyType("$","cd $PIN_HOME/one/auto/rating");            	        	
			puttyCommon.PuttyType("rating","./country.sh");
			puttyCommon.PuttyType("",sStreamChoice);
			puttyCommon.PuttyType("",sCDRType);
			puttyCommon.PuttyType("EDR",sNoEDR);
			puttyCommon.PuttyType("Domestic/Roaming",sDomesticRoaming);
			puttyCommon.PuttyType("code",sCountryCode);
			puttyCommon.PuttyType("In/Out/Partial",sInOutPartial);
			puttyCommon.PuttyType("Calling Number",sCallingNo);
			puttyCommon.PuttyType("Called Nummber",sCalledNo);
			puttyCommon.PuttyType("C number",sCnumber);
			puttyCommon.PuttyType("credit/debit",sCr_Dr);
			puttyCommon.PuttyType("Duration",sDuration);
			puttyCommon.PuttyType("quantity",sSMSQuantity);
			puttyCommon.PuttyType("Rate in GBP",sRateGBP);
			puttyCommon.PuttyType("Transaction Type",sTransactionType);            	      
			puttyCommon.PuttyType("Timestamp",Long.toString(TIMESTAMP0));
			puttyCommon.PuttyType("Destination",sDestination);
			puttyCommon.PuttyType("Bearer",sBearerCapability);
			puttyCommon.PuttyType("(C/D)",sC_D);
			puttyCommon.PuttyType("tax",sTax);
			puttyCommon.PuttyType("GSM",sGSM);
			puttyCommon.PuttyType("Tariff class",sTariffClass);
			puttyCommon.PuttyType("Source country",sSrcCountry);
			puttyCommon.PuttyType("Destination country",sDetnCountry);
			boolean b=puttyCommon.CapturePuttyData("LINEAFTER:Your CDR is", "CDRName", iIndex);
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			String sCDR=Serenity.sessionVariableCalled("CDRName").toString();
			puttyCommon.PuttyType("$","cd $PIPE_DIR/acrinput/router");            	        	
			puttyCommon.PuttyType("router","cat "+sCDR);
			Thread.sleep(1000);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			sCDR=sCDR.replace(".data", "");
			String[] sCDRs = sCDR.split("_");
			Serenity.setSessionVariable("CDRSeq").to(sCDRs[3]);
			//sCDR = sCDR.replace(sCDRs[0], "");
			if (sOldDayCDR.equals("Y"))
			{
				puttyCommon.PuttyType("$","cd $PIPE_DIR/log/pin01/ifw/stream");	
				boolean sTypeCapturePuttyData = puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDR + "*",sCDR +".data.log","OutCDR");
				if (sTypeCapturePuttyData==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Assert.assertTrue(false);
					//puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDR + "*",sCDR +".data.log","OutCDR");
				}
			}
			else 
			{
				puttyCommon.PuttyType("$","cd $PIPE_DIR/ploutput/pin01/pin_rel_01/archive");
				boolean sTypeCapturePuttyData = puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDRs[3] + "*",sCDRs[3] +".out.bc","OutCDR");
				/*if (sTypeCapturePuttyData==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);   
				//puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDRs[3] + "*",sCDRs[3] +".out.bc","OutCDR");
			}*/
			}
			try {
				String sOutCDR=Serenity.sessionVariableCalled("OutCDR").toString();
				sOutCDR = sOutCDR.replace("[00m", "");
				puttyCommon.PuttyType("$","cat "+sOutCDR);
			}
			catch(Exception e) {

			}
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}

	}

	@Step
	public void BulkRating(String RatingType) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Rating";  
		Screen  s=new Screen();
		long TIMESTAMP0=0;

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RatingType, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   

			String sCDRType = tableMap.get("CDRType").get(i);  
			String sCountryCode= tableMap.get("CountryCode").get(i);
			String sCallingNo= tableMap.get("CallingNo").get(i);
			String sCalledNo= tableMap.get("CalledNo").get(i);
			String sTimestamp= tableMap.get("Timestamp").get(i);
			String sSMSQuantity= tableMap.get("SMSQuantity").get(i);
			String sDuration= tableMap.get("Duration").get(i);            	        
			String sDetnCountry= tableMap.get("DetnCountry").get(i);
			String iIndex= tableMap.get("Index").get(i);
			String sChoice =tableMap.get("Choice").get(i);
			String sType =tableMap.get("Type").get(i);
			Serenity.setSessionVariable("RatingIndex").to(iIndex);
			String sRoamingchoice =tableMap.get("Roamingchoice").get(i);

			//String MSISDNFromAccount = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();	     

			try {
				if(sCountryCode.equals("")){                                      
					sCountryCode = Serenity.sessionVariableCalled("CountryCode").toString();
				}}
			catch(Exception e){}                            
			try {
				if (sCallingNo.equals("ACCNTMSISDN")) {                     
					if(!Serenity.sessionVariableCalled("ACCNTMSISDN0").equals("")){ 

						sCallingNo = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
					}}
				else {System.out.println("Issue in ACCNT MSISDN ,please check the putty rating excel ");}


			}
			catch(Exception e){}                            

			try {
				if(!Serenity.sessionVariableCalled("sCalledNo").equals("")){
					sCalledNo = Serenity.sessionVariableCalled("sCalledNo").toString();

				}}
			catch(Exception e){}
			try {
				if(!sSMSQuantity.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sSMSQuantity);
				}}

			catch(Exception e){}
			try {
				if(!sDuration.equals("")){
					Serenity.setSessionVariable("Duration_Quantity").to(sDuration);
				}}
			catch(Exception e){}
			try {
				if(sDetnCountry.equals("")){
					sDetnCountry = Serenity.sessionVariableCalled("DetnCountry").toString();
				}}
			catch(Exception e){}

			if(iIndex.equals("")){
				iIndex = "0";
			}

			if(sTimestamp.equals("")){

				long unixTimestamp = Instant.now().getEpochSecond();
				report.Info("Unix Time Stamp: "+unixTimestamp);


				Database.ExecuteDBQuery("AccountCreationTime");
				report.Info("AccountCreationTime    "+Serenity.sessionVariableCalled("MAXCREATED0").toString());
				long sAccountCreationTime = Integer.parseInt(Serenity.sessionVariableCalled("MAXCREATED0").toString()); 
				report.Info("sAccountCreationTime Time Stamp: "+sAccountCreationTime);
				if (sAccountCreationTime<=unixTimestamp){

					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}

				if (sAccountCreationTime==unixTimestamp) {
					TIMESTAMP0 = unixTimestamp+14400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}
				if (sAccountCreationTime==0) {
					TIMESTAMP0 = unixTimestamp+86400;
					report.Info("Unix TIMESTAMP0 Stamp: "+TIMESTAMP0);
				}

				if (sAccountCreationTime>unixTimestamp){                                        
					report.Info("Account Creation Time :- "+sAccountCreationTime +"  , is greater than Unix time stamp :- "+unixTimestamp);
					Assert.assertTrue(false);     
				}
			}

			else {
				TIMESTAMP0 =Long.parseLong(Serenity.sessionVariableCalled("TIMESTAMP0").toString());
				report.Info("Time stamp extracted from DB :-  "+TIMESTAMP0);
			}
			puttyCommon.PuttyType("$","cd $PIN_HOME/two/rating/input");  
			puttyCommon.PuttyType("rating","mv country_code_y.txt country_code_y.txt");
			puttyCommon.PuttyType("rating","cp IndiaDND.txt country_code_y.txt");
			puttyCommon.PuttyType("$","cd $PIN_HOME/two/rating");
			puttyCommon.PuttyType("rating","./rating.sh");
			puttyCommon.PuttyType("CLEAN UP",sCDRType);
			puttyCommon.PuttyType("MSISDN",sCallingNo);
			puttyCommon.PuttyType("TIMESTAMP",Long.toString(TIMESTAMP0));
			puttyCommon.PuttyType("EXIT",sRoamingchoice);
			puttyCommon.PuttyType("TEST",sChoice);
			puttyCommon.PuttyType("MMS",sType);
			puttyCommon.PuttyType("DURATION",sDuration);
			puttyCommon.PuttyType("NUMBER",sCalledNo);
			puttyCommon.PuttyType("CODE",sDetnCountry);
			Thread.sleep(3000);
			boolean b=puttyCommon.CapturePuttyData("LINEAFTER:Your CDR is", "CDRName", iIndex);
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}

			String sCDR=Serenity.sessionVariableCalled("CDRName").toString();
			puttyCommon.PuttyType("$","cd $PIPE_DIR/acrinput/router");            	        	
			puttyCommon.PuttyType("router","cat "+sCDR);
			Thread.sleep(1000);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			sCDR=sCDR.replace(".data", "");
			String[] sCDRs = sCDR.split("_");
			Serenity.setSessionVariable("CDRSeq").to(sCDRs[3]);
			//sCDR = sCDR.replace(sCDRs[0], "");
			puttyCommon.PuttyType("$","cd $PIPE_DIR/ploutput/pin01/pin_rel_01/archive");
			boolean sTypeCapturePuttyData = puttyCommon.TypeCapturePuttyData ("$","ls -1 *" + sCDRs[3] + "*",sCDRs[3] +".out.bc","OutCDR");

		}
		try {
			String sOutCDR=Serenity.sessionVariableCalled("OutCDR").toString();
			sOutCDR = sOutCDR.replace("[00m", "");
			puttyCommon.PuttyType("$","cat "+sOutCDR);
		}
		catch(Exception e) {

		}
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

	}



	@Step
	public void SBExportReport(String Index) throws Exception 
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "SBExportReport";     

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Index, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("ReportName").size();i++) {   
			String  sReportOption = tableMap.get("ReportOption").get(i);                       
			String sReportType = tableMap.get("ReportType").get(i);  
			String  sAllSegments = tableMap.get("AllSegments").get(i);  
			String  sAccountNo = tableMap.get("AccountNo").get(i);
			String  sIndex = tableMap.get("Index").get(i);  

			//String   sAccountNo ="";

			if (sAccountNo.contains("ACCOUNTNO0"))
			{
				sAccountNo=Serenity.sessionVariableCalled("ACCOUNTNO0").toString();
			}
			if (sAccountNo.contains("AccountNo"))
			{
				sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();
			}                    
			/* try{
            	sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();           	        	

            	}
            	catch(Exception e){

            	}*/
			//String sAccountNo = "7000323259";
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/edw");
			// if(sReportOption.equals("1") || sReportOption.equals("3")){
			puttyCommon.PuttyType("edw","./SBExport_report.sh "+sAccountNo);
			puttyCommon.PuttyType("",sReportOption);
			puttyCommon.PuttyType("",sReportType);
			if(sReportOption.equals("4")){
				if(sAllSegments.equals("")){
					sAllSegments ="N";
				}
				Thread.sleep(2000);
				puttyCommon.PuttyType("",sAllSegments);
			}

			/* if(sReportOption.equals("2")){
                  puttyCommon.PuttyType("$","./SBExport_report.sh "+sAccountNo);
                  puttyCommon.PuttyType("",sReportOption);
            }*/
			// Thread.sleep(90000);       
			boolean b=puttyCommon.ValidatePutty("SBExport.sh","SBExport file is ");
			// if(sReportOption.equals("1") || sReportOption.equals("2")){
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				report.Info("SBExport report could not generate for your account "+sAccountNo);
				Assert.assertTrue(false);
			}
			report.Info("SBExport report generated successfully for your account "+sAccountNo);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);


			/* if(sReportOption.equals("3")){
                  if (b==false){
                        sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
                        report.Info("SBExport report could not generate ");
                        Assert.assertTrue(false);
                  }
                        report.Info("SBExport report generated successfully");
                        sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
            }
			 */  

			//Thread.sleep(90000);
			boolean b1=puttyCommon.CapturePuttyData("SBExport file is ","ReportFile",sIndex);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			if (b1==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
		}
	}


	@Step
	public  void FileTransferToLocalFromUNIX() throws JSchException, IOException, SftpException 
	{

		String host = Serenity.sessionVariableCalled("HostName").toString();
		//String host="NewVoE7-brm01";
		String user = Serenity.sessionVariableCalled("UserName").toString();	
		//String user="pin";
		//String Passphrase = Serenity.sessionVariableCalled("RootPassword").toString();	    
		//String Passphrase = "pin123";
		String Passphrase = "pinbrm";
		//  String privateKey = "C:\\Users\\ankesh.bansal\\Desktop\\ssh_key_putty_newVoSUP01";
		//String command1="echo Test34,Test2 > /tmp/txt";
		//String command2="echo Test34 > QWE";
		// String absoluteFilePathPrivatekey = "./";

		ChannelSftp sftpClient = null;
		Channel channel = null;
		JSch jsch = new JSch();
		//jsch.addIdentity(Passphrase);


		Session session = jsch.getSession(user, host, 22);
		session.setPassword(Passphrase);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking","no");
		session.setConfig(config);
		session.connect();

		channel = session.openChannel("sftp");
		channel.connect();

		sftpClient = (ChannelSftp)channel;


		try
		{    
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			//Serenity.setSessionVariable("sFileName").to("ABC");
			String sFileName = Serenity.sessionVariableCalled("ReportFile").toString();
			//String sFileName = "VATReportCustom_EbuSME_201907_20190725131516_01.csv";
			sFileName = (sFileName.replace("SBExport file is ", "")).trim();
			System.out.println("sFileName : -"+sFileName);
			String UnixFilePath="/tmp/"+sFileName;
			String SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
			//String SikulifilePathForSavingFile = "\\src\\test\\resources";
			String LocalSystemPath=SikulifilePathForSavingFile;
			System.out.println("UnixFilePath : -"+UnixFilePath);
			System.out.println("LocalSystemPath : -"+LocalSystemPath);
			sftpChannel.get(UnixFilePath, LocalSystemPath);
			//System.out.println("File transfered");
			report.Info("File Transfered to :- "+LocalSystemPath);
			sftpChannel.exit();
			session.disconnect();    
		}
		finally
		{
			sftpClient.disconnect();
			channel.disconnect();
			session.disconnect();
		}
	}



	@Step                 
	public void AdjustmentDetails() throws IOException, AWTException, InterruptedException, ClassNotFoundException, SQLException {
		puttyCommon.PuttyType("$","cd $PIN_HOME/automation/sql_query");
		String sAccnt = Serenity.sessionVariableCalled("AccountNo").toString();
		puttyCommon.PuttyType("sql_query","./adjustment_detail.sh "+sAccnt+" "+Serenity.sessionVariableCalled("ReasonCode").toString()+" "+"\""+Serenity.sessionVariableCalled("Reason").toString()+"\"");
		boolean b=puttyCommon.ValidatePutty("adjustment_detail.sh","Adjustment detail for account "+sAccnt);
		if (b==false){
			report.Info("Adjustment not done in BRM");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			//Assert.assertTrue(false);
		}
		report.Info("Adjustment done in BRM");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
	}
	@Step                 
	public void EDWUsageFilefn() throws IOException, AWTException, InterruptedException, ClassNotFoundException, SQLException {
		String sCallingNo =Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
		String iIndex = "0";
		puttyCommon.PuttyType("$","cd $PIPE_DIR/ploutput/aggregation/out/edw");
		puttyCommon.PuttyType("edw","./EDW.sh " + sCallingNo);
		iIndex = Serenity.sessionVariableCalled("RatingIndex").toString();
		if(iIndex.equals(""))
			iIndex="0";

		boolean b1=puttyCommon.CapturePuttyData("Your File Name","ReportFile",iIndex);
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		if (b1==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		Serenity.setSessionVariable("ReportFile").to((Serenity.sessionVariableCalled("ReportFile").toString()).replace("Your File Name ", "").trim());
		if((Serenity.sessionVariableCalled("ReportFile").toString().trim()).equals("")){
			report.Info("File is not generated successfully");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}


	}

	@Step
	public void GLReportfn(String GLReport) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "GL_Report";  
		Screen  s=new Screen();


		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(GLReport, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {   
			String sBusinessProfileId  = tableMap.get("BusinessProfileId").get(i);
			System.out.println("sBusinessProfileId :- "+sBusinessProfileId);
			String iIndex ="0";
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/edw");
			puttyCommon.PuttyType("edw","./GL_Report.sh");
			puttyCommon.PuttyType("CreditAccount",sBusinessProfileId);
			Thread.sleep(10000);
			boolean sTypeCapturePuttyData = puttyCommon.CapturePuttyData ("GL Report Zip File is ","ReportFile",iIndex );
			if (sTypeCapturePuttyData==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
		}

	}

	@Step
	public void RetrieveVoiceData(String VoiceDataRow) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "VoiceData";  
		Screen  s=new Screen();


		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(VoiceDataRow, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {  
			Serenity.setSessionVariable("TariffClass").to(tableMap.get("TARIFFCLASS").get(i));
			Serenity.setSessionVariable("GroupName").to(tableMap.get(tableMap.get("GROUPNAME").get(i)));
			Serenity.setSessionVariable("Beat").to(tableMap.get("BEAT").get(i));
			Serenity.setSessionVariable("StdDuration").to(tableMap.get("StdDuration").get(i));
			Serenity.setSessionVariable("StdPrice").to(tableMap.get("PRICE").get(i));

		}
	}


	@Step
	public void RetrieveVoiceRoamingData(String RoamingVoiceDataRow) throws Exception {
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "RoamingVoiceData";  
		Screen  s=new Screen();


		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RoamingVoiceDataRow, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {  
			Serenity.setSessionVariable("SrcCountry").to(tableMap.get("SRCCOUNTRY").get(i));
			Serenity.setSessionVariable("DestnCountry").to(tableMap.get(tableMap.get("DESTNCOUNTRY").get(i)));
			Serenity.setSessionVariable("Beat").to(tableMap.get("BEAT").get(i));
			Serenity.setSessionVariable("StdDuration").to(tableMap.get("StdDuration").get(i));
			Serenity.setSessionVariable("StdPrice").to(tableMap.get("PRICE").get(i));
			Serenity.setSessionVariable("Zone").to(tableMap.get("ZONE").get(i));

		}
	}

	@Step
	public void DDReversalARUDD(String Type) throws Exception 
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "DDReversalARUDD";     

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Type, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Type").size();i++) {   
			String  sType = tableMap.get("Type").get(i);                       
			String sReasonCode = tableMap.get("ReasonCode").get(i);  
			String sAccountNo=Serenity.sessionVariableCalled("AccountNo").toString(); 


			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/payment");     
			puttyCommon.PuttyType("payment","./reverse_pay.sh");
			puttyCommon.PuttyType("",sType);
			puttyCommon.PuttyType("",sReasonCode);
			puttyCommon.PuttyType("",sAccountNo);

			boolean b=puttyCommon.ValidatePutty("./reverse_pay.sh","mode 0");
			if (b==false){
				report.Info("DDReversalARUDD Payment Not Done");
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			report.Info("Reverse Payment Done");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		}
	}

	@Step
	public void CreditAlert(String Option) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "CreditAlert";     	

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(Option, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Option").size();i++) {   
			String  sOption = tableMap.get("Option").get(i);                 	
			String sAccountNo = tableMap.get("AccountNo").get(i);  
			String  sAmount = tableMap.get("Amount").get(i);                 	
			String sThreshold = tableMap.get("Threshold").get(i); 
			String sBusinessType = tableMap.get("BusinessType").get(i);
			String sCRIValue = tableMap.get("CRIValue").get(i);
			String sMSISDN = tableMap.get("MSISDN").get(i);
			if (sAccountNo.equals("ACCNTMSISDN"))
				sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();
			if(sMSISDN.equals("MSISDN0"))
				sMSISDN=Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
			else
				sMSISDN=Serenity.sessionVariableCalled("ACCNTMSISDN").toString();

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/credit_alert");
			puttyCommon.PuttyType("$","./credit_alert.sh");
			puttyCommon.PuttyType("",sOption);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

			if (sOption.equals("1"))
			{
				puttyCommon.PuttyType("Account Number",sAccountNo);
				puttyCommon.PuttyType("CRI Value",sCRIValue);
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			}
			if (sOption.equals("2"))
			{
				puttyCommon.PuttyType("MSISDN",sMSISDN);
				puttyCommon.PuttyType("AMOUNT",sAmount);
				puttyCommon.PuttyType("THRESHOLDS",sThreshold);
				puttyCommon.PuttyType("BUSINESSTYPE",sBusinessType);
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			}

			if (sOption.equals("1")) {
				boolean b1 =puttyCommon.ValidatePutty("credit_alert.sh","CRI Value changed to "+ sCRIValue);
				if (b1==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					report.Info("CRI value could not changed");
					Assert.assertTrue(false);
				}
				report.Info("CRI Value changed successfully");
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

			}

			if (sOption.equals("2")) {
				boolean b1 =puttyCommon.ValidatePutty("credit_alert.sh","Credit limit changed to "+ sAmount);
				if (b1==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					report.Info("Credit Limit could not changed");
					Assert.assertTrue(false);
				}
				report.Info("Credit limit changed successfull");
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

			}


		}
	}

	@Step
	public void CreditAlertSiebel() throws Exception {
		String sMSISDN = Serenity.sessionVariableCalled("ACCNTMSISDN").toString();

		puttyCommon.PuttyType("$","cd /opt/SP/oracle/crm/batchfs/automation");
		puttyCommon.PuttyType("$","./ca.sh " + sMSISDN);
		boolean b1 =puttyCommon.ValidatePutty("./ca.sh","Alert generated for your MSISDN "+ sMSISDN);
		if (b1==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		report.Info("Alert generated for your MSISDN");
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
	}


	@Step
	public void PaymentDateChangeUEL_fn() throws Exception
	{

		//String sACCOUNT=Serenity.sessionVariableCalled("AccountNo");
		//String sBILLNAME1=Serenity.sessionVariableCalled("BILLNAME1");

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();                   

		String sdate =sdfDate.format(now);
		String sdateNew=sdate.replace("/" ,"_");
		String Randomno = Integer.toString((11111 + (int)(Math.random()*222222)));
		String sFileName = "Extend_Payment_due_date_"+sdateNew+"_"+Randomno+".csv";


		LocalDateTime nowTime = LocalDateTime.now();
		int desiredYear = nowTime.getYear();
		int desiredMonth = nowTime.getMonthValue();
		int nextMonth=desiredMonth + 2;
		String myDate = "01"+"/"+nextMonth+"/"+desiredYear;

		Date myDate1 =new SimpleDateFormat("dd/MM/yyyy").parse(myDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate1);
		cal.add(Calendar.DATE, -1);
		Date dateBefore1Day = cal.getTime();
	}

	@Step
	public void Schema2Account() throws Exception 
	{


		puttyCommon.PuttyType("$","cd $PIN_HOME/automation/sql_query");
		puttyCommon.PuttyType("$","./Schema2_Account.sh");
		boolean b1 = puttyCommon.ValidatePutty("","Schema 2 Account Number");
		if (b1==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}

		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		puttyCommon.CapturePuttyData("Schema 2 Account Number : ","Schema2Account","0");			
		report.Info("Schema 2 account "+Serenity.sessionVariableCalled("Schema2Account").toString());

	}

	@Step
	public void CaptureBillAmount() throws Exception 
	{
		String sAmount = Serenity.sessionVariableCalled("AMOUNT0").toString();
		sAmount =""+(Integer.parseInt(sAmount))* 0.85;
		Serenity.setSessionVariable("Amount0").to(sAmount);

	}

	@Step
	public void BulkAdjustment(String SelectChoice) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "PuttyBulkAdjustment";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(SelectChoice, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Row").size();i++) {
			String  sSelectChoice = tableMap.get("SelectChoice").get(i);
			String  sACCNTMSISDN = tableMap.get("ACCNTMSISDN").get(i);

			if (sACCNTMSISDN.equals("ACCNTMSISDN")) {                     
				sACCNTMSISDN = Serenity.sessionVariableCalled("ACCNTMSISDN0").toString();
			}		        	
			puttyCommon.PuttyType("$","cd $PIN_HOME/automation");
			puttyCommon.PuttyType("automation","cd Adjustment/");
			puttyCommon.PuttyType("Adjustment","bulkAdj_GoneNbkp.sh");
			Thread.sleep(10000);
			puttyCommon.PuttyType("",sSelectChoice);
			puttyCommon.PuttyType("Msisdn",sACCNTMSISDN);


			boolean b1 = puttyCommon.ValidatePutty("","Tmp file has been moved");
			if (b1==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}
	}

	@Step
	public void Docgen(String rowid) throws Exception
	{
		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "Docgen";        

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(rowid ,filePath, dataSheet);
		for (int i = 0;i < tableMap.get("EX").size();i++) {
			String sSequence = tableMap.get("Sequence").get(i);
			String sIndex = tableMap.get("Index").get(i);
			String sAccountNo = "";

			if (sIndex.equals("")){
				sIndex = "0";
			}

			sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation/bill_invoice");
			puttyCommon.PuttyType("$","./docgen.sh");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			puttyCommon.PuttyType("enter Account",sAccountNo);
			puttyCommon.PuttyType("enter sequence",sSequence);
			boolean b=puttyCommon.ValidatePutty("docgen.sh","Docgen completed successfully please check in WCC");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			report.Info("Invoice uploaded on WCC");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		}

	}

	@Step
	public void DocgenLogs() throws Exception
	{

		//puttyCommon.PuttyType("$","cd $PIN_HOME/var/log");
		puttyCommon.PuttyType("$","cd /home/pinbrm/log/log_01");
		puttyCommon.PuttyType("$","date +'%Y%m%d%H'");
		boolean sTime=puttyCommon.CapturePuttyData("LINEAFTER:date", "TIMESTAMP", "0");
		if (sTime==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}

		String sTimestamp = Serenity.sessionVariableCalled("TIMESTAMP").toString();
		// String sTimestamp ="2018112709";
		puttyCommon.PuttyType("$","ls -1t *pin_inv_doc_gen_" +sTimestamp+ "*");

		boolean c=puttyCommon.CapturePuttyData("LINEAFTER:ls -1t *pin_inv_doc_gen", "FileName", "0");
		if (c==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		String sDocLogFile=Serenity.sessionVariableCalled("FileName").toString().trim();

		sDocLogFile=sDocLogFile.substring(sDocLogFile.indexOf("pin"),sDocLogFile.indexOf(".csv"));
		sDocLogFile=(sDocLogFile+ ".csv");
		report.Info("Log File Name is :" +sDocLogFile);

		puttyCommon.PuttyType("log","cat "+sDocLogFile);
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		boolean sStartEndDate=puttyCommon.CapturePuttyData("LINEAFTER:cat pin_inv_doc_gen", "StartEndTime", "0");
		if (sStartEndDate==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		String sStartEndTime=Serenity.sessionVariableCalled("StartEndTime").toString().trim();		


		String[] sStartEndTime1 = sStartEndTime.split(Pattern.quote(","));
		String sStartTime = sStartEndTime1[1];
		String sEndTime = sStartEndTime1[2];
		report.Info("start time is :" +sStartTime);
		report.Info("End time is :" +sEndTime);

	}



	@Step
	public void PrintshopFileExtraction() throws Exception
	{

		puttyCommon.PuttyType("$","cd /opt/soa/odi1/Middleware/Oracle_ODI1/DocStore/FileMerged");
		Database.ExecuteDBQuery("TodaysDate");
		String sTIMESTAMP = Serenity.sessionVariableCalled("TIMESTAMP0").toString();
		//String sTIMESTAMP = "20190610";
		puttyCommon.PuttyType("$","ls -1t *" +sTIMESTAMP+ "* | head -1");

		boolean b=puttyCommon.CapturePuttyData("LINEAFTER:ls -1t", "ZipFile", "0");

		if (b==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}

		String sZipFile=Serenity.sessionVariableCalled("ZipFile").toString().trim(); 

		if (!sZipFile.contains(".zip")) {
			BRMPuttyLogin("SOA02Putty" ,"Env");
			puttyCommon.PuttyType("$","cd /opt/soa/odi1/Middleware/Oracle_ODI1/DocStore/FileMerged");
			puttyCommon.PuttyType("$","ls -1t *" +sTIMESTAMP+ "* | head -1");
			Thread.sleep(1000);
			boolean c=puttyCommon.CapturePuttyData("LINEAFTER:ls -1t", "ZipFileNew", "0");
			if (c==false){report.Info("File not found in SOA2");
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
			}
			String sZipFileNew=Serenity.sessionVariableCalled("ZipFileNew").toString().trim(); 
			sZipFileNew=sZipFileNew.substring(sZipFileNew.indexOf("VF"),sZipFileNew.indexOf(".zip"));
			sZipFileNew= (sZipFileNew+ ".zip");

			report.Info("Printshop zipfile is :" +sZipFileNew);

			puttyCommon.PuttyType("$","unzip "+sZipFileNew);
			Thread.sleep(1000);
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			String sZipFilecsv=sZipFileNew.replace(".zip", ".csv");
			String sZipFilePdf=sZipFileNew.replace(".zip", ".pdf");

			Serenity.setSessionVariable("sZipFilePdf").to(sZipFilePdf);
			report.Info("Printshop Files are : "+sZipFilePdf+ " and " +sZipFilecsv);
			report.Info("Printshop Files are : "+sZipFilePdf);

			return;
		}
		sZipFile=sZipFile.substring(sZipFile.indexOf("VF"),sZipFile.indexOf(".zip"));
		sZipFile= (sZipFile+ ".zip");
		//puttyCommon.PuttyType("$",sZipFile);
		report.Info("Printshop zipfile is :" +sZipFile);

		puttyCommon.PuttyType("$","unzip "+sZipFile);
		Thread.sleep(1000);
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		String sZipFilecsv=sZipFile.replace(".zip", ".csv");
		String sZipFilePdf=sZipFile.replace(".zip", ".pdf");
		//String sZipFilePdf="VF_20181112_ODI_BL_UK_1.pdf";
		Serenity.setSessionVariable("sZipFilePdf").to(sZipFilePdf);
		report.Info("Printshop Files are : "+sZipFilePdf+ " and " +sZipFilecsv);
		report.Info("Printshop Files are : "+sZipFilePdf);
	}

	@Step
	public  void FileTransferToLocalFromSOAPutty() throws JSchException, IOException, SftpException 
	{

		String host = Serenity.sessionVariableCalled("HostName").toString();

		String user = Serenity.sessionVariableCalled("UserName").toString();	
		String Passphrase = Serenity.sessionVariableCalled("Passphrase").toString();	    

		ChannelSftp sftpClient = null;
		Channel channel = null;
		JSch jsch = new JSch();

		Session session = jsch.getSession(user, host, 22);
		session.setPassword(Passphrase);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking","no");
		session.setConfig(config);
		session.connect();

		channel = session.openChannel("sftp");
		channel.connect();

		sftpClient = (ChannelSftp)channel;


		try
		{    
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			String sFileName = Serenity.sessionVariableCalled("sZipFilePdf").toString();
			System.out.println("sFileName : -"+sFileName);
			String UnixFilePath="/opt/soa/odi1/Middleware/Oracle_ODI1/DocStore/FileMerged/"+sFileName;

			String SikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();

			String LocalSystemPath=SikulifilePathForSavingFile;
			System.out.println("UnixFilePath : -"+UnixFilePath);
			System.out.println("LocalSystemPath : -"+LocalSystemPath);
			sftpChannel.get(UnixFilePath, LocalSystemPath);

			report.Info("File Transfered to :- "+LocalSystemPath);
			sftpChannel.exit();
			session.disconnect();    
		}
		finally
		{
			sftpClient.disconnect();
			channel.disconnect();
			session.disconnect();
		}
	}

	@Step
	public void OverpaymentUtility(String SelectChoice) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "PuttyBulkAdjustment";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(SelectChoice, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("Row").size();i++) {
			String  sSelectChoice = tableMap.get("SelectChoice").get(i);

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation");
			puttyCommon.PuttyType("automation","shortcut.sh");
			Thread.sleep(5000);
			puttyCommon.PuttyType("",sSelectChoice);

			Thread.sleep(20000);
			boolean b1 = puttyCommon.ValidatePutty("","Total number of errors encountered");
			if (b1==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}
	}
	@Step
	public void ccRefundReport() throws Exception {


		String sFileName = Serenity.sessionVariableCalled("ReportFile").toString();
		sFileName = (sFileName.replace("SBExport file is ", "")).trim();
		System.out.println("sFileName : -"+sFileName);
		//String sFileName = "REFUND_vf-root.brm-jb-user_20200429_084801.csv";
		puttyCommon.PuttyType("$","cd $PIN_HOME/custom_tools/SBExport_01/output/ccRefunds");
		String BillNo = Serenity.sessionVariableCalled("BILLNUMBER0").toString();
		BillNo=BillNo.replace("-", ".");
		
		//String BillNo = "F1.1654619";
		puttyCommon.PuttyType("$","grep "+BillNo+" "+sFileName);
		boolean b=puttyCommon.CapturePuttyData("LINEAFTER:"+sFileName, "ccRefundDetail", "0");
		if (b==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		String ccRefundDetail=Serenity.sessionVariableCalled("ccRefundDetail").toString();
		String AccountNo=Serenity.sessionVariableCalled("AccountNo").toString();

		String[] sCCDetail = ccRefundDetail.split(",");
		
		puttyCommon.PuttyType("$","cd $PIN_HOME/automation/payment");
		puttyCommon.PuttyType("payment","CCRefund.sh");
		Thread.sleep(2000);
		puttyCommon.PuttyType("",AccountNo);
		puttyCommon.PuttyType("",sCCDetail[1]);
		puttyCommon.PuttyType("",sCCDetail[5]);
		Thread.sleep(2000);
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

	}

	@Step
	public void P2PBatchRun(String SelectChoice) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Catalog.xls";
		String dataSheet = "P2P";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(SelectChoice, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			String  sChoice = tableMap.get("Choice").get(i);

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation");
			puttyCommon.PuttyType("automation","P2P.sh");
			Thread.sleep(5000);
			puttyCommon.PuttyType("",sChoice);

			if(sChoice.equals("1")) {
				Thread.sleep(3000);
				boolean b1 = puttyCommon.ValidatePutty("automation","");
				if (b1==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Assert.assertTrue(false);
				}
			}
			else if(sChoice.equals("2")) {
				Thread.sleep(10000);
				boolean b1 = puttyCommon.ValidatePutty("","Total number of errors encountered");
				if (b1==false){
					sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
					Assert.assertTrue(false);
				}
			}
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}
	}


	@Step
	public void FinalBillGeneration(String RowNo) throws Exception {

		String filePath = "\\src\\test\\resources\\data\\Putty.xls";
		String dataSheet = "FinalBill";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(RowNo, filePath, dataSheet);
		for (int i = 0;i < tableMap.get("RowNo").size();i++) {
			String  sAccountno = tableMap.get("Account").get(i);


			String Account_No = Serenity.sessionVariableCalled("AccountNo").toString();
			if (sAccountno.contains("ACCOUNTNO0"))
			{
				Account_No=Serenity.sessionVariableCalled("ACCOUNTNO0").toString();
			}
			if (sAccountno.contains("AccountNo"))
			{
				Account_No=Serenity.sessionVariableCalled("AccountNo").toString();
			}

			puttyCommon.PuttyType("$","cd $PIN_HOME/automation");
			puttyCommon.PuttyType("automation","./finalbill.sh");
			Thread.sleep(5000);
			puttyCommon.PuttyType("",Account_No);

			boolean b=puttyCommon.ValidatePutty("./finalbill.sh","Total number of errors encountered");
			if (b==false){
				sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
				Assert.assertTrue(false);
			}

			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);

		}
	}
	@Step
	public void ReconnectionUtility() throws Exception {

		puttyCommon.PuttyType("$","cd $PIN_HOME/appsCustom/mta_reconnect_01");
		puttyCommon.PuttyType("mta_reconnect","./mta_reconnect -verbose");
		Thread.sleep(5000);

		boolean b1 = puttyCommon.ValidatePutty("","Total number of errors encountered");
		if (b1==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		report.Info("Reconnection utility successfully processed");					


	}
	@Step
	public void MTAPaymentRefund() throws Exception {

		puttyCommon.PuttyType("$","cd $PIN_HOME/appsCustom/mta_direct_debit_payments");
		puttyCommon.PuttyType("mta_direct_debit_payments","./mta_direct_debit_payments_app -type PostPaid -fb_refund -verbose");
		Thread.sleep(5000);

		boolean b1 = puttyCommon.ValidatePutty("","Total number of errors encountered");
		if (b1==false){
			sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
			Assert.assertTrue(false);
		}
		sikuliUtility.SikuliScreenShot(SikulifilePathForSavingFile);
		report.Info("Reconnection utility successfully processed");					


	}
}
