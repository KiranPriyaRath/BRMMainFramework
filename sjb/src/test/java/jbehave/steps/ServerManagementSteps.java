package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import net.thucydides.core.annotations.Steps;
import pages.ServerManagementPage;
import org.jbehave.core.annotations.Then;


public class ServerManagementSteps {
	@Steps
    private ServerManagementPage Servermanagementpage;
                
    @When("Navigate To Employees Tab")
    public void Navigation() throws InterruptedException, IOException, AWTException {
                
            Servermanagementpage.NavigationToEmployees();
    }
    @Then("Capture Division value with '$Division'")
    public void AdminDiv(String Division) throws InterruptedException, IOException{
                
             Servermanagementpage.AdministratorDivision(Division);
    }
    
    @When("LOV check '$JobType'")
    public void LOVCheck(String UIName) throws Exception{   
            Servermanagementpage.CreateListofValue(UIName);
            
    }
    
    @When("Navigate To Jobs")
    public void NavigationToJobs() throws InterruptedException, IOException, AWTException {   
            Servermanagementpage.NavigationToJobs();
}
    
    @When("Create New Jobs '$JobType'")
    public void NewJobs(String UIName) throws InterruptedException, IOException, AWTException{   
            Servermanagementpage.CreateNewJob(UIName);
            
    }
    
    @When("Select the Jobs '$JobType'")
    public void SelectJob(String UIName) throws InterruptedException, IOException, AWTException{   
            Servermanagementpage.SelectJob(UIName);
    }
    
    @When("Perform '$RowId'")
    public void JobParameters(String UIName) throws InterruptedException, IOException, AWTException{   
            Servermanagementpage.JobParameters(UIName);
    }
      
}
