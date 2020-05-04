package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import net.thucydides.core.annotations.Steps;
import pages.ContactsPage;

public class ContactsSteps {
	@Steps
	private ContactsPage ContactsPage;
	
	
	@When("Validate Contacts tab for '$Validation'")
	public void CreateAccount(String Validation)  throws InterruptedException, IOException, AWTException
	{
		ContactsPage.Contacts_LastName(Validation);			
	}
}