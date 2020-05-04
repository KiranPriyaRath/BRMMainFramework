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

public class OSMHome {
	@Steps
    private OSMHomePage osmhomepage;

    @When("Search Order Number '$OrderNum' in OSM")
    public void Search_Order (String OrderNum) throws FindFailed, InterruptedException, IOException, AWTException  {
    	osmhomepage.SearchOrderNumberOSM(OrderNum);
    	
    } 
    
    @Then("Save Process History from OSM for script '$TCID'")
    public void Save_Process (String TCID) throws FindFailed, InterruptedException, IOException, AWTException  {
    	osmhomepage.ProcessHistory(TCID);
    	
    } 
    
    
}


