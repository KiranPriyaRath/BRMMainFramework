package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;

import pages.AccountsPage;
import pages.OrdersPage;
import pages.ServerManagementPage;


public class Accounts {
	@Steps
	private AccountsPage Accountspage;
	@Steps
	private OrdersPage Orderspage;
	
		@When("Create the account type '$accountType'")
		public void CreateAccount(String accountType)  throws InterruptedException, IOException, AWTException{
			Accountspage.CreateNewAccount(accountType);
			Accountspage.VerifyAccountSummary(accountType);	
		}	
		
		@When("Create QAS account '$accountType'")
		public void Create_QAS_Account(String accountType)  throws InterruptedException, IOException, AWTException{			
			Accountspage.CreateQASAccount(accountType);			
		}		
		
		@When("Create only account type '$accountType'")
		public void CreateOnlyAccount(String accountType)  throws InterruptedException, IOException, AWTException{			
			Accountspage.CreateNewAccount(accountType);
			
		}
		@When("Create '$OrderType' '$BillingProfileType' Order")
		public void CreateNewBillingProfile(String OrderType,String BillingProfileType) throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.CreateNewBillingProfile(BillingProfileType);
			Accountspage.CreateNewOrder(OrderType);			
		}
		
		@When("Create New Billing Profile '$BillingProfileType'")
        public void CreateNewBillingProfile(String BillingProfileType) throws InterruptedException, IOException, AWTException {
              Accountspage.CreateNewBillingProfile(BillingProfileType);
     }
		@When("Create the Anonymous account type '$accountType'")
		public void CreateAnonymousAccount(String accountType)  throws InterruptedException, IOException, AWTException{
			Accountspage.AnonymousAccount(accountType);					
		}	
	 	@Then("validate the buttons on my customer comms")
	    public void validation() throws InterruptedException  {	    	
			Accountspage.CustomerCommsButtonsValidation();	   
		}
		@When("Validate Online Flag for scenario '$accountType'")
		public void ValidateOnlineFlag(String accountType)  throws InterruptedException, IOException, AWTException
		{
			Accountspage.OnlineAccountValidations(accountType);			
		}
		@Then("Validate Online Flag in Account,Account Summary,Contacts for scenario '$OnlineFlagValidation'")
		public void ValidateOnlineFlagAccountSummary(String OnlineFlagValidation)  throws InterruptedException, IOException, AWTException
		{
			Accountspage.VerifyOnlineAnonymousAccount(OnlineFlagValidation);
		}
		@Then("validate the Non validated address popup'$rowname'")
		public void NonValidatedAddress(String rowname) throws IOException, InterruptedException, AWTException{
			Accountspage.NonValidatedAddress(rowname); 
		}		
		 @When("Select '$rowname' AgreementId")
	    	public void SelectAgreementRowID(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
	    		Accountspage.SelectAgreementRowID(rowname);
	    	}
		 @When("Change the Date")
	        public void AgreementDateChange() throws InterruptedException, IOException, FindFailed, AWTException {
	        	Accountspage.AgreementDateChange();
		 }
		@Then("validate the Address Applet in all views")
	    public void validate_AddressApplet() throws InterruptedException, IOException, AWTException  {
	    
			Accountspage.AddressAppletValidation();	
	    }		
		@Then("Modify Details with '$rowname'")
	    public void email_Update(String rowname) throws IOException, InterruptedException, AWTException{
			Accountspage.ModifyDetails(rowname);
	    }				
	   @When("Search The Account '$AccountNo'")
	    public void Search_The_Account(String AccountNo) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException  {
		   Accountspage.Searchaccount(AccountNo);
	   }   
	   @When("Search Another Account '$AccountNo'")
		    public void Search_Another_Account(String AccountNo) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException  {
			   Accountspage.Searchanotheraccount(AccountNo);
	    }	  
	    @When ("Create new Billing Profile via Profiles Tab '$rowname'" )
	    public void CreateNewProfilesBillingProfile(String rowname)  throws InterruptedException, IOException, AWTException{
	    	Accountspage.CreateNewProfilesBillingProfile(rowname);
	    }	
	    @Then("Perform Service Request Validation with '$rowname'")
	    public void Service_Request_Validation(String rowname) throws IOException, InterruptedException, AWTException{
			Accountspage.ServiceRequestColumnValidation(rowname);
	    }
        @Then ("Validate that Payment term is editable '$rowname'" )
        public void CreateNewProfilesBillingProfile_PaymentTerms(String rowname)  throws InterruptedException, IOException, AWTException{
          Accountspage.CreateNewProfilesBillingProfile(rowname);
        }
        @Then ("Perform Validation for Check Payment Date Button '$rowname'" )
        public void CreateNewProfilesBillingProfile_ChckPaymentDate(String rowname)  throws InterruptedException, IOException, AWTException{
          Accountspage.CreateNewProfilesBillingProfile(rowname);
        }
        
        @Then ("Perform validation for '$rowname' in Billing profile View" )
        public void CreateNewBillingProfile_RealTimeBalance(String rowname)  throws InterruptedException, IOException, AWTException{
          Accountspage.CreateNewBillingProfile(rowname);
        }  
        
        @Then ("Perform validation for '$rowname' in Profile's Tab Billing profile View" )
        public void CreateNewProfilesBillingProfile_RealTimeBalance(String rowname)  throws InterruptedException, IOException, AWTException{
          Accountspage.CreateNewProfilesBillingProfile(rowname);
        }
        @Then ("Navigate to Profile Page and perform '$rowname'" )
        public void CreateNewProfilesBillingProfile_AbtRecord(String rowname)  throws InterruptedException, IOException, AWTException{
          Accountspage.CreateNewProfilesBillingProfile(rowname);
          Orderspage.getAboutRecord();
        }
        
        @Then("Perform Services Validation for '$View'")
	    public void All_ProductServices_Validation(String View) throws IOException, InterruptedException, AWTException{
			Accountspage.AllProductServicesValidation(View);
	    }
	
        @When("Goto Account Summary")
	    public void Goto_AccountSummary() throws InterruptedException {
		   Accountspage.GotoAccountSummary();
	    }
        @Then("Verify Order page fields '$rowname'")
	    public void VerifyOrderDetails(String rowname) throws IOException, InterruptedException, AWTException{
			Accountspage.VerifyOrderDetails(rowname);
	    }
        @When("Create '$OrderType'Order")
    	public void Create_NewOrder(String Ordertype) throws InterruptedException, IOException, FindFailed, AWTException {
    		Accountspage.CreateNewOrder(Ordertype); 
        }
        
        @When("Create New '$BillingProfileType'")     

        public void CreateNewBilling(String BillingProfileType) throws InterruptedException, IOException, AWTException{
               Accountspage.CreateNewBillingProfile(BillingProfileType);
          } 
        
        @When("Goto Sub Account")
        public void Goto_SubAccount(){
            Accountspage.GotoSubAccount();
        }
        @When("Create New SubAccount Billing Profile '$BillingProfileType'")
        public void CreateNewBillingProfileSubAccounts(String BillingProfileType) throws InterruptedException, IOException, AWTException {
              Accountspage.CreateNewBillingProfileSubAccounts(BillingProfileType);
        }
        @When("Click New Billing Account")
        public void ClickNewBillingAccount() throws InterruptedException, IOException, FindFailed, AWTException {
            Accountspage.ClickNewBillingAccount();
}
        
        @When("Click New Service Account")
        public void ClickNewServiceAccount() throws InterruptedException, IOException, FindFailed, AWTException {
        	Accountspage.ClickNewServiceAccount();
        } 
        
        @When("Get agreement date for '$row'")
    	public void GetAgreementDate(String Row) throws InterruptedException, IOException, FindFailed, AWTException {
    		Accountspage.GetAgreementDate(Row);
        }
        @When("capture the Agreement ID '$rowname'")
    	public void SelectAgreementID(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
    		Accountspage.SelectAgreementRowID(rowname);
        }
        @Then("verify the Agreement ID '$rowname'")
    	public void VerifyAgreementRowID(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
    		Accountspage.SelectAgreementRowID(rowname); 
        }

		@When("Select ConditionalChargeRowID '$rowname'")
		public void ConditionalChargesRowID(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.ConditionalChargesRowID(rowname);
		}
		
		@When("Select ConditionalCharge '$rowname'")
		public void SelectConditionalChargesRow(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.SelectConditionalChargesRow(rowname);
		}
		
		@When("Retrieve Account Details")
		public void RetrieveAccountDetails() throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.RetrieveAccountDetails();
		}
		
		@Then("Perform Audit Trail for Account View '$rowname'")
		public void AuditTrailAccount(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.AuditTrailAccount(rowname);
		}
		
		@Then("Perform Audit Trail for Contact View '$rowname'")
		public void AuditTrailContact(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			Accountspage.AuditTrailContact(rowname);
		}
		@Then("Add Credit Balance for '$rowname'")
        public void AddCreditBalance(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
              Accountspage.AddCreditBalance(rowname);
        }
		
		@Then("Credit Vetting Tab Verification for '$rowname'")
        public void CreditVettingTabVerify(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
              Accountspage.CreditVettingTabVerify(rowname);
        }	
		@When("Verify the Child accout under sub_Account '$rowname'")
        public void ServiceAccountVerify(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
              Accountspage.ServiceAccountVerify(rowname);
        }
		@When("select the MediaType with '$rowname'")
        public void AddDeleteMediaType(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
              Accountspage.AddDeleteMediaType(rowname);
        }
		@When("Update Contact Number '$rowname'")
	    public void  UpdateContactPhoneNumber(String Action) throws InterruptedException, IOException, FindFailed, ClassNotFoundException {
			Accountspage.UpdateContactPhoneNumber(Action);
		}
		
}
