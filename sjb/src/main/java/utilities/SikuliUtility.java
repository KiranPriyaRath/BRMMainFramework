package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import utilities.ReportMessege;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;


public class SikuliUtility {
      
      @Steps
    private ReadWorkbook readWorkbook;
      @Steps
      ReportMessege report;
      
      
      public void ClickFraudRiskAlert(String SikulifilePath) throws FindFailed, InterruptedException, IOException {
            String currentDir = System.getProperty("user.dir");
            SikulifilePath = currentDir.concat(SikulifilePath);                                  
        Screen s=new Screen();
                
        /*
                        if(s.exists(SikulifilePath+"\\FraudRiskPopUp.png") != null)
                        {              
                              s.click(SikulifilePath+"\\FraudRiskConfirmButton.png");
                            Thread.sleep(5000);                       
        
                        }*/
        
             for(int loop=0;loop<3;loop++){
              if(s.exists(SikulifilePath+"\\FraudRiskConfirmButton.png",2) != null){
            	s.click(SikulifilePath+"\\FraudRiskConfirmButton.png");
            	Thread.sleep(5000);
             }else
            	break;
            } 

      }

      
      
      public void ErrorSubProcess(String SikulifilePath) throws FindFailed, InterruptedException, IOException {
          String currentDir = System.getProperty("user.dir");
          SikulifilePath = currentDir.concat(SikulifilePath);                                  
          Screen s=new Screen();
                  
          				Thread.sleep(3000);
                          if(s.exists(SikulifilePath+"\\ErrorSubProcesPopUp.png") != null)
                          {              
                            s.click(SikulifilePath+"\\ErrorSubProcesOKButton.png");
                              Thread.sleep(2000);                       
          
                          }

    }
      
      public void ClickModPromokAlert(String SikulifilePath) throws FindFailed, InterruptedException, IOException {
            String currentDir = System.getProperty("user.dir");
            SikulifilePath = currentDir.concat(SikulifilePath);                                  
        Screen s=new Screen();
                
        
                        /*if(s.exists(SikulifilePath+"\\ModifyPromoPopUp.png") != null)
                        {              
                              s.click(SikulifilePath+"\\ModPromoOKButton.png");
                            Thread.sleep(5000);                   
        
                        }*/
        
		        for(int loop=0;loop<3;loop++){
		        	//ModifyPromoPopUp
		            if(s.exists(SikulifilePath+"\\ModPromoOKButton.png",2) != null){
		            	s.click(SikulifilePath+"\\ModPromoOKButton.png");
		            	Thread.sleep(4000);
		            }else
		            	break;
		            } 
		      }
      
        public void ClickJILLPopUp(String SikulifilePath) throws FindFailed, InterruptedException, IOException {
            String currentDir = System.getProperty("user.dir");
            SikulifilePath = currentDir.concat(SikulifilePath);                                  
            Screen s=new Screen();
                    
            
                            if(s.exists(SikulifilePath+"\\JILLPopUp.png") != null)
                            {              
                              s.click(SikulifilePath+"\\JILLOKButton.png");
                                Thread.sleep(2000);                       
            
                            }

      }

public void ClickReconnectionPopUp(String SikulifilePath) throws FindFailed, InterruptedException, IOException {
            String currentDir = System.getProperty("user.dir");
            SikulifilePath = currentDir.concat(SikulifilePath);                                  
            Screen s=new Screen();
                    
            
                            if(s.exists(SikulifilePath+"\\ReconnectionPopUp.png") != null)
                            {              
                              s.click(SikulifilePath+"\\ReconnectionOKButton.png");
                                Thread.sleep(2000);                       
            
                            }

      }
        

public void SaveProcessHistory(String SikulifilePath, String SikulifilePathForSavingFile, String ScriptNum) throws FindFailed, InterruptedException, IOException {
            String currentDir = System.getProperty("user.dir");
            SikulifilePath = currentDir.concat(SikulifilePath);   
            SikulifilePathForSavingFile = currentDir.concat(SikulifilePathForSavingFile); 
            Screen s=new Screen();
                    
            
                            if(s.exists(SikulifilePath+"\\SFP.png") != null)
                            {    
                            	s.click(SikulifilePath+"\\SFP.png");
                            	s.type(Key.DELETE);
                            	s.type(SikulifilePath+"\\SFP3.png",SikulifilePathForSavingFile);
                            	s.type(Key.ENTER);
                                Thread.sleep(2000);                       
            
                            }
                            if(s.exists(SikulifilePath+"\\FileNameatfirst.png") != null)
                            {  
                            	
                            	s.click(SikulifilePath+"\\FileNameatfirst.png");
                            	s.type(Key.DELETE);
                            	s.type(SikulifilePath+"\\FileName2.png",ScriptNum);
                                Thread.sleep(2000);                       
            
                            }
                            
                            if(s.exists(SikulifilePath+"\\SaveButton.png") != null)
                            {              
                              s.click(SikulifilePath+"\\SaveButton.png");
                                Thread.sleep(2000);                    
            
                            }
                            
                            if(s.exists(SikulifilePath+"\\SaveButton.png") != null)
                            {    Thread.sleep(2000);          
                            report.Info("File was not saved  successfully.");
                            Assert.assertTrue(false);
                                                     
            
                            }
        }
        @FindBy(xpath=".//*[@title='View Report']")
   	    WebElementFacade ViewReportList;

public void InvoiceSave(String SikulifilePath, String SikulifilePathForSavingFile, String InvoiceName) throws FindFailed, InterruptedException, IOException, AWTException {
            
            Screen s=new Screen();
            
            /*if(s.exists(SikulifilePath+"\\BIPDownloadLink.png") != null)
            { 
               Thread.sleep(1000);
               s.doubleClick(SikulifilePath+"\\BIPDownloadLink.png");
               Thread.sleep(1000);                          
            }   */   
            
            if(s.exists(SikulifilePath+"\\PDFLinkClick.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\PDFLinkClick.png");               
               Thread.sleep(1000);                          
            }   
            
            /*for(int loop=0;loop<2;loop++){
            	if(s.exists(SikulifilePath+"\\BIPDowloadExport.png") != null){
                	break;
                }
                else{
                	 s.click(SikulifilePath+"\\BIPDownloadLink.png");                	
                }
             }*/
            
            for(int loop=0;loop<2;loop++){
            	if(s.exists(SikulifilePath+"\\BIPDownloadPDF.png") != null){
            		s.click(SikulifilePath+"\\BIPDownloadPDF.png");
            		Thread.sleep(1000);
                	break;
                }
                else{
                	 s.click(SikulifilePath+"\\PDFLinkClick.png");                	
                }
             }
            
            /*if(s.exists(SikulifilePath+"\\BIPDowloadExport.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\BIPDowloadExport.png");
               Thread.sleep(1000);                          
            }
            else
        	{
             	report.Info("BIPDownloadExport.png is not available");
             	Assert.assertTrue(false);
             }*/                                                
            
            if(s.exists(SikulifilePath+"\\DownloadInvoice.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\DownloadInvoice.png");
               Thread.sleep(4000);                        
            }
            if(s.exists(SikulifilePath+"\\ChangeToPDF.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\ChangeToPDF.png");
               Thread.sleep(3000);                          
            }
            if(s.exists(SikulifilePath+"\\SaveAsPDF.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\SaveAsPDF.png");
               Thread.sleep(3000);                          
            }
            if(s.exists(SikulifilePath+"\\SavePDF.png") != null)
            { 
               Thread.sleep(1000);
               s.click(SikulifilePath+"\\SavePDF.png");
               Thread.sleep(3000);                          
            }
           
                            	s.type(Key.DELETE);
                            	Thread.sleep(2000);
                            	s.type(InvoiceName+".pdf");
                            	//s.type(SikulifilePathForSavingFile+"\\"+InvoiceName);
                                Thread.sleep(2000);                                                                                   

                                if(s.exists(SikulifilePath+"\\DownArrow.png") != null)
                			    {    
                			    	s.click(SikulifilePath+"\\DownArrow.png");
                			    	Thread.sleep(1000);
                			    	s.type(Key.DELETE);
                			    	if(s.exists(SikulifilePath+"\\FilePathTextBox.png") != null) {
                			    		//s.click(SikulifilePath+"\\FilePathTextBox.png");
                			    		Thread.sleep(2000);
                			    		s.type(Key.DELETE);
                			    		Thread.sleep(2000);                       
                			    	}
                			    }
                                String  filePath = Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
                			    if(s.exists(SikulifilePath+"\\FilePathType.png") != null)
                			    {                              	
                			
                			    	s.type(filePath);
                			    	Thread.sleep(2000);
                			    	s.type(Key.ENTER);                    	
                			    	Thread.sleep(3000);  
                			    	
                			    	report.Info("Report Downloaded to path :-"+SikulifilePathForSavingFile+"\\"+InvoiceName);
                			
                			    }
                			                			                                  
                                for(int loop=0;loop<3;loop++){
                                	Thread.sleep(3000);
                                    if(s.exists(SikulifilePath+"\\BIPSaveButton.png",2) != null){
                                    	SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
                                    	s.click(SikulifilePath+"\\BIPSaveButton.png");
                                    	}
                                    else {
                                    	report.Info("Invoice saved successfully");
                                    	break ;
                                    }
                            }                                                                                   
        }

public void SikuliScreenShot(String SikulifilePathForSavingFile) throws IOException, AWTException, InterruptedException
    {
        System.out.println();
        Screen s2=new Screen();
        Robot robot = new Robot();
        Thread.sleep(2000);
        BufferedImage myScreenshot = robot.createScreenCapture(s2.getBounds());
        //report.Info(SikulifilePathForSavingFile);
        
        SimpleDateFormat dateformat= new SimpleDateFormat("ddMMyyHHmmss");
        Date date = new Date();

        ImageIO.write(myScreenshot, "jpg", new File(SikulifilePathForSavingFile+"\\"+dateformat.format(date)+".png")); //SikulifilePathForSavingFile+"\\"+FolderName+"\\"+ZonedDateTime.now())
        }
             


public  void Puttyjsch() 
{
    String host="NewVoSUP01-brm01";
    String user="v.a.ranjan";
    String passPhrase="7857000";
    String privateKey = "C:\\Users\\ankesh.bansal\\Desktop\\ssh_key_putty_newVoSUP01";
    String command1="echo Test34,Test2 > /tmp/txt";
    //String command2="echo Test34 > QWE";
    String absoluteFilePathPrivatekey = "./";
    try{
    	
    	java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	
    	
    	JSch jsch = new JSch();
    	jsch.addIdentity("C:\\Users\\ankesh.bansal\\Desktop\\private",passPhrase);
    	
    	Session session=jsch.getSession(user, host, 22);
    	
    	//session.setPassword("pin123");
    	session.setConfig(config);
    	session.connect();
    	System.out.println("Connected");
    	
    	Channel channel=session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command1);
        //((ChannelExec)channel).setCommand(command2);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
        
        InputStream in=channel.getInputStream();
        channel.connect();
        byte[] tmp=new byte[1024];
        while(true){
          while(in.available()>0){
            int i=in.read(tmp, 0, 1024);
            if(i<0)break;
            System.out.print(new String(tmp, 0, i));
          }
          if(channel.isClosed()){
            System.out.println("exit-status: "+channel.getExitStatus());
            break;
          }
          try{Thread.sleep(1000);}catch(Exception ee){}
        }
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");
    }catch(Exception e){
    	e.printStackTrace();
    }
}



public  void Puttyjsch2() throws JSchException, IOException, SftpException 
{
	   String host="NewVoSUP01-brm01";
	    String user="v.a.ranjan";
	    String passPhrase="7857000";
	   
	    String privateKey = "C:\\Users\\ankesh.bansal\\Desktop\\ssh_key_putty_newVoSUP01";
	    String command1="echo Test34,Test2 > /tmp/txt";
	    //String command2="echo Test34 > QWE";
	    String absoluteFilePathPrivatekey = "./";

ChannelSftp sftpClient = null;
Channel channel = null;
JSch jsch = new JSch();
jsch.addIdentity("C:\\Users\\ankesh.bansal\\Desktop\\private",passPhrase);
Session session = jsch.getSession(user, host, 22);

Properties config = new Properties();
config.put("StrictHostKeyChecking","no");
session.setConfig(config);
session.connect();
channel = session.openChannel("sftp");
channel.connect();
sftpClient = (ChannelSftp)channel;

System.out.println("Gone till Try");

try
{
    sftpClient.cd("/tmp");
    File localFile = new File("C:/Vv.txt");
    sftpClient.put(localFile.getAbsolutePath(),localFile.getName());
    System.out.println("File transfered");
    
}
finally
{
    sftpClient.disconnect();
    channel.disconnect();
    session.disconnect();
}
}
public void EnterMSISDN(String SikulifilePath,String OneNetMSISDN) throws FindFailed, InterruptedException, IOException, AWTException {
    String currentDir = System.getProperty("user.dir");
    SikulifilePath = currentDir.concat(SikulifilePath);   
    //SikulifilePathForSavingFile = currentDir.concat(SikulifilePathForSavingFile); 
    String SikulifilePathForSavingFile= Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString();
    Thread.sleep(5000); 
    Screen s=new Screen();

    if(s.exists(SikulifilePath+"\\OneNetMSISDN.png") != null) {
		 //s.click(SikulifilePath+"\\OneNetMSISDN.png");
    	s.type(OneNetMSISDN);
    Thread.sleep(2000);

              
    }
}

}
  

