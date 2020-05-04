package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import org.sikuli.script.FindFailed;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.BRMLoginPage;
import pages.LoginPage;
import pages.BRMODIPage;


public class BRMODI {
	@Steps
    private BRMODIPage ODIpage;
	@Steps
	private LoginPage loginpage;
	@Steps
	private BRMLoginPage BRMLoginpage;
	
	@Given("Login to ODI with '$iRowNo' in '$EnvURL'")
	public void LoginToODI(String iRowNo,String EnvURL) throws IOException, InterruptedException, AWTException, FindFailed  {
		BRMLoginpage.LoginToODI(iRowNo);
	}
	
    @Given("Browse ODI Scenario '$password'")
    public void BrowseScenariosfn(String BrowseScenarios) throws InterruptedException, IOException, AWTException {
    	ODIpage.BrowseScenarios_fn(BrowseScenarios);
    }
    
    @Given("Execute ODI Scenario '$Execute'")
    public void ExecuteScenariofn(String Execute) throws InterruptedException, IOException ,AWTException, FindFailed {
    	ODIpage.ExecuteScenario_fn(Execute);
    }
    
    @Given("Validate ODI Scenario")
    public void SearchSessionStatusfn() throws InterruptedException, IOException ,AWTException, FindFailed {
    	ODIpage.SearchSessionStatus_fn();
    }
    
    @Given("Login to WCC with '$iRowNo' in '$EnvURL'")
    public void LoginToWCC(String iRowNo,String EnvURL) throws InterruptedException, IOException {
    	BRMLoginpage.LoginToWCCfn(iRowNo,EnvURL);
    }
    
    @Given("Search Invoice in WCC")
    public void SearchinvoiceWCCfn() throws InterruptedException, IOException ,AWTException {
    	ODIpage.SearchInvoiceWCCfn();
    }
   
}
