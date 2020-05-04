package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;
import pages.OSMHomePage;
import pages.LoginPage;
import pages.BRMBIPPage;
import pages.BRMLoginPage;

public class BRMBIP {
	@Steps   
	private LoginPage LoginPage;
	@Steps 
	private BRMBIPPage BIPpage;
	@Steps
	private BRMLoginPage BRMLoginpage;
	
    
      
    @Given("Login to BIP with '$iRowNo' for '$InvoiceType'")
    public void LoginToBIP(String iRowNo,String InvoiceType) throws IOException, InterruptedException, FindFailed, AWTException  {
    	BRMLoginpage.LoginToBIP(iRowNo);
    	BIPpage.OpenBRMInvoicing(InvoiceType);
    	BIPpage.BIPSave();
    }
    
 
}