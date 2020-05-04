package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import net.serenitybdd.core.pages.PageObject;
import pages.CataloguePage;
import pages.CustomerCommsPage;
import net.thucydides.core.annotations.Steps;

public class CustomerComms {
	@Steps	
	private CustomerCommsPage CustomerCommspage;
	
	@Given("Customer Comms Verification")
	public void Custome_Comms_Verification() throws InterruptedException, IOException {
		CustomerCommspage.CustomerCommsVerification();	
	}	
	@Then("Validate Customer Comms Buttons")
	public void Custome_Comms_Button_Validation() throws InterruptedException, IOException {
		CustomerCommspage.CustCommsButtonsValidation();	
	}	
	@Then("Validate that Agent is able to '$rowname' in CustomerComms screen")
    public void CustomComsListNew(String rowname)  throws InterruptedException, IOException{
		CustomerCommspage.CustomComsListNew(rowname);
    }
	
	@Then("Validate Buttons of DPA Validations for '$rowname' in CustomerComms screen")
    public void VerifyCustCommList(String rowname)  throws InterruptedException, IOException{
		CustomerCommspage.VerifyCustCommList(rowname);
    }
	@When("Create Service request for scenario '$rowname' in Customer Comms")
	public void Create_ServiceRequestVia_ServiceRequest(String rowname) throws InterruptedException, IOException, AWTException {
		CustomerCommspage.CreateServiceRequestviaCustComms(rowname);
	}

}