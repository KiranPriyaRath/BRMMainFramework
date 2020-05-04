package utilities;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import utilities.SikuliUtility;
public class PuttyCommon {
	
	
	@Steps
    ReportMessege report;
	
	@Steps
	private SikuliUtility sikuliUtility;
	
	String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
	Screen s = new Screen();
	
	
	
	public boolean ValidatePutty(String where,String data){
		try {
			int iCnt = 0;
			boolean flag = false;
			String lines = "";
			do{
				String sSikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
				String sLogFileName = Serenity.sessionVariableCalled("LogFileName").toString();
				File file = new File(sSikulifilePathForSavingFile+"\\"+sLogFileName);
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String verify;
				while ((verify=br.readLine())!=null){
					lines = lines + verify;
				}
				if (where.equals("")){
					if (lines.indexOf(data)!=-1){
						flag = true;
						report.Info("Expected data found " + data);
						//return true;
					}
				}
				else if (lines.indexOf(data)!=-1){
					if ((lines.indexOf(data, lines.indexOf(where)))!=-1){
					flag = true;
					
					report.Info("Expected data found " + data + " after " + where);
					//return true;
					}
				}
				if (flag == false){
					iCnt = iCnt +1;
					Thread.sleep(10000);
				}
			}while (flag == false && iCnt<50);
			return flag;	
			
			}
			
		
		catch (Exception e){
			return false;
			
		}
	
	}
	
	public boolean ValidatePuttyData(String data, String where) throws IOException, InterruptedException{
		int iCnt = 0;
		boolean flag = false;
		do{
			String sSikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
			String sLogFileName = Serenity.sessionVariableCalled("LogFileName").toString();
			File file = new File(sSikulifilePathForSavingFile+"\\"+sLogFileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String verify,verify1 = null;
			if (where.equalsIgnoreCase("LastLine")){
				while ((verify=br.readLine())!=null){
					verify1 = verify;
				}
				if (verify1.contains(data)){
					flag = true;
				}
				if (flag == false){
					iCnt = iCnt +1;
					
					Thread.sleep(10000);
				}
				
			}
		}while (flag == false && iCnt<20);
		return flag;
		
	}
	
	public void PuttyType(String TypeAgainst,String TypeValue) throws IOException, InterruptedException, AWTException{
		String sSikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
		if (!TypeValue.equals("")){
			boolean sResult = this.ValidatePuttyData (TypeAgainst,"LastLine");
			if (sResult==true){
				s.type(SikulifilePath+"\\LoginCursor.png",TypeValue);
				s.type(Key.ENTER); 
			}
			else {
				sikuliUtility.SikuliScreenShot(sSikulifilePathForSavingFile);
				Assert.assertTrue("Error:"+ TypeAgainst + " not found to enter " + TypeValue, false);
			}
			
		}
		
	}
	
	public boolean CapturePuttyData(String data, String key1, String sIndex) throws IOException, InterruptedException{
		int iCnt = 0;
		boolean flag = false;
		Serenity.setSessionVariable(key1).to("");
		do{
			int actIndex=0;
			String sSikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
			String sLogFileName = Serenity.sessionVariableCalled("LogFileName").toString();
			File file = new File(sSikulifilePathForSavingFile+"\\"+sLogFileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String lineNextStr,verify1 = null;
			while ((lineNextStr=br.readLine())!=null){
				if (!data.equals("LastLine")){
					String data1 = data.replace("LINEAFTER:","");
					if (lineNextStr.contains(data1)){
						if (data.contains("LINEAFTER:")){
							lineNextStr=br.readLine();
							if (lineNextStr.equals("")){
								lineNextStr=br.readLine();
							}
						}
						if ((Integer.toString(actIndex)).equals(sIndex)){
							flag = true;
							Serenity.setSessionVariable(key1).to(lineNextStr);
							report.Info(key1 + " is " + lineNextStr);
							return true;
						}
						else{
							actIndex = actIndex + 1;
						}
						
					}
				}
				
			}
			if (data.equals("LastLine")){
				flag = true;
				Serenity.setSessionVariable(key1).to(lineNextStr);
				report.Info(key1 + " is " + lineNextStr);
				return true;
			}
			iCnt = iCnt +1;
			Thread.sleep(2000);
					
			
			
		}while (flag == false && iCnt<40);
		report.Info(data + " is not found");
		return false;
		
		
	}
	public boolean TypeCapturePuttyData(String where,String what,String data,String key1) throws InterruptedException, IOException, AWTException{
		int iCnt = 0;
		boolean flag = false;
		do {
			this.PuttyType(where, what);
			String sSikulifilePathForSavingFile = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
			String sLogFileName = Serenity.sessionVariableCalled("LogFileName").toString();
			File file = new File(sSikulifilePathForSavingFile+"\\"+sLogFileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String lineNextStr,verify1 = null;
			
			while ((lineNextStr=br.readLine())!=null){
				if (!data.equals("LastLine")){
					String data1 = data.replace("LINEAFTER:","");
					if (lineNextStr.contains(data1)){
						if (data.contains("LINEAFTER:")){
							lineNextStr=br.readLine();
							//if (lineNextStr.equals("")){
								//lineNextStr=br.readLine();
							//}
						}
						flag = true;
						Serenity.setSessionVariable(key1).to(lineNextStr);
						report.Info(key1 + " is " + lineNextStr);
						return true;
					}
				}	
				
			}
			if (data.equals("LastLine")){
				flag = true;
				Serenity.setSessionVariable(key1).to(lineNextStr);
				report.Info(key1 + " is " + lineNextStr);
				return true;
			}
			iCnt = iCnt + 1;
			Thread.sleep(2000);
			
			
		} while(flag == false && iCnt<20);
		return false;
		
	}


}

	