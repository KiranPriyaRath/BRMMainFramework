package jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.junit.Assert;
import net.thucydides.core.annotations.Steps;
import pages.SearchAccountPage;


public class SearchAccountSteps {
	
	@Steps
    private SearchAccountPage searchaccountpage;
	
	   @Given("Search The Account '$AccountNo'")
	    public void Search_The_Account  (String AccountNo)  {
		   searchaccountpage.Searchaccount(AccountNo);
	    }

	    @Given("Verify the Account Name '$AccountName'")
	    public void Verify_the_Account_Name(String AccountName)  {
	    	searchaccountpage.VerifyAccountName(AccountName);
	        
	    }
 }

