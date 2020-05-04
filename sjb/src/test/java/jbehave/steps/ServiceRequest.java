package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;
import pages.ServerManagementPage;
import pages.ServiceRequestPage;

import org.jbehave.core.annotations.Then;


public class ServiceRequest {
	@Steps
    private ServiceRequestPage Servicerequestpage;
                   
    @Then("Service Request Field Validation for '$row'")
    public void ServiceRequest_Field_Validation(String rowname) throws InterruptedException, IOException, AWTException{   
    	Servicerequestpage.ServiceRequestFieldValidation(rowname);
    }
    
    @Then("Service request Type validation '$rowname'")
	public void CreateServiceRequestVia_ServiceRequest(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
    	Servicerequestpage.CreateServiceRequestViaServiceRequest(rowname);
 }
    
    @Then("Perform validation in Service Request screen for scenario '$accountType'")
	public void CreateAccount(String accountType)  throws InterruptedException, IOException, AWTException{
    	Servicerequestpage.ServiceRequestValiadtionInServiceRequest(accountType);
	}	
    
    @When("Create Service request for scenario '$rowname' in Service Request Applet")
	public void Create_ServiceRequestVia_ServiceRequest(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
    	Servicerequestpage.CreateServiceRequestViaServiceRequest(rowname);
 } 
    
}
