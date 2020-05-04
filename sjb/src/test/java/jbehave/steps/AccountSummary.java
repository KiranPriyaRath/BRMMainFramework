package jbehave.steps;


import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
//import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;

import pages.OrdersPage;
import pages.AccountSummaryPage;
import pages.AccountsPage;
import pages.DatabasePage;

public class AccountSummary {
	@Steps
	private AccountSummaryPage AccountSummary;
	@Steps
	private OrdersPage Orderspage;
	@Steps
	private AccountsPage Accountspage;
	@Steps
	private DatabasePage Database;
	
	@Then("Perform the action '$action'")
	public void Perform_Action(String action) throws IOException, InterruptedException, FindFailed, AWTException{
		AccountSummary.UsedProductServices(action);
	}
	@When("Verify Contacts '$rowname'")
    public void Add_VerifyContacts(String rowname) throws InterruptedException, IOException, AWTException {
  	  AccountSummary.AddVerifyContacts(rowname);
    }
	@Then("Click on Profiles tab and check if Payment terms is editable")
	public void ChckPaymentTerms()  throws InterruptedException, IOException, AWTException{
		AccountSummary.PaymentTerms();
	}
	@Then("Verify if For The Attention is present in Account Addresses List")
	public void FortheAttention()  throws InterruptedException, IOException, AWTException{
		AccountSummary.AccountAddressesList();
	}
	@Then("Reset Customer Comms for scenario '$ResetAction'")
	public void ValidateOnlineFlagAccountSummary(String ResetAction)  throws InterruptedException, IOException, AWTException
	{
		AccountSummary.ResetCustomerComms(ResetAction);	
	}
	@Then("Validate the Customer Summary with'$rowname'")
	public void CustomerSummary(String rowname) throws IOException, InterruptedException, AWTException{
		AccountSummary.CustomerSummary(rowname);	
	}
	
	@Then("Perform Validation for '$Editable'")
	public void Editable_annonymous(String Editable) throws InterruptedException, IOException, AWTException{
		AccountSummary.AddressEditable(Editable);
	}
	@Then ("Validate DOB Popup for '$rowname'" )
	public void Age_ID_Partner(String rowname)  throws InterruptedException, IOException, AWTException{
		AccountSummary.AgeIDVerification(rowname);
	}
	@Then("Verify Customer Comms Contacts '$rowname'")
    public void Verify_CustComContacts(String rowname) throws InterruptedException, IOException, AWTException {
  	  AccountSummary.VerifyCustComContacts(rowname);
    }
	@Then("Create Customer Comms '$CustComms'")
	public void Create_CustomerComms(String CustComms) throws InterruptedException, IOException, AWTException{
		AccountSummary.CreateCustComms(CustComms);
	}
	@Then("Create Direct Debit for scenario '$DirectDebitAction'")
	public void CreateDirectDebit(String DirectDebitAction)  throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException
	{
		AccountSummary.DirectDebit(DirectDebitAction);
		
	}
	@When("Change Payment for scenario '$logicalName'")
	public void Change_Payment(String logicalName)  throws InterruptedException, IOException, AWTException
	{
		AccountSummary.ChangePaymentMethod(logicalName);
		
	}
	@Then ("Validate Blocker Popup Occurs without entering Email '$rowname'" )
	public void BillsEmail(String rowname)  throws InterruptedException, IOException, AWTException{
		AccountSummary.BillsEmail(rowname);
	}
	@Given("About Record")
	    public void AboutRecordFn() throws InterruptedException  {
		  AccountSummary.AboutRecord();
	        
	}
	@Then("Validate Cust Comms Comments '$rowname'")
	public void captureComments(String rowname)  throws InterruptedException, IOException, AWTException
	{
		AccountSummary.captureComments_CustComms(rowname);
		
	} 
	@Then ("Validate the DD Transaction type" )
	public void Profiles_DirectDebit()  throws InterruptedException, IOException, AWTException{
		AccountSummary.Profiles_DirectDebit();
	}
	@Then("Validate the Account permission is full access '$ResetAction'")
	public void validateAccountPermission(String ResetAction) throws IOException, InterruptedException, AWTException{
		AccountSummary.ResetCustomerComms(ResetAction);
	}
	@Then("Validate fields under CustomerSummarry Applet '$rowname'")
	public void VerifyFields_CustomerSummaryApplet(String rowname) throws IOException, InterruptedException, AWTException{
		AccountSummary.VerifyCustomerSummary(rowname);
		}
	@When("Modify with owned product '$action'")
    public void OwnedProductServices(String action) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException  {
         AccountSummary.OwnedProductServices(action);
    }
	
	@When("Create New '$BillingProfileType' and Perform '$MenuProfile' with About Record")     

    public void MenubillingprofileAboutRecord(String MenuProfile, String BillingProfileType) throws InterruptedException, IOException, AWTException{
           Accountspage.CreateNewBillingProfile(BillingProfileType);
           AccountSummary.MenuBillingProfile(MenuProfile);
           Orderspage.getAboutRecord();
      }     

      @When("Perform '$MenuProfile' and Execute query '$rowname' and Create New '$OrderType' Order")     

    public void Menubillingprofile(String MenuProfile,String rowname,String OrderType) throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed, AWTException{
            AccountSummary.MenuBillingProfile(MenuProfile);
            Database.ExecuteDBQuery(rowname);
            Accountspage.CreateNewOrder(OrderType);
      }
      
      @Then("Verify the Product and Services for '$Action'")
      public void Product_Services(String Action) throws InterruptedException, IOException, AWTException  {
  	  AccountSummary.ProductServicesVerify(Action);          
      }
      @Then("Perform Migration")
      public void Perform_Migration() throws InterruptedException, IOException, AWTException  {
  	  AccountSummary.PromotionUpgrade();
      }
  	@Then("Perform Transfer Of OwnerShip")
    public void PerformTOO() throws InterruptedException, IOException, AWTException {
	  AccountSummary.TransferOfOwnerShip();          
    }
  	@Then("Validate the Address applet")
    public void gotoAddreses() throws IOException, InterruptedException, AWTException{
  		AccountSummary.gotoAddresss();  
    }
	    @Then("Perform NLFL")
	    public void Perform_NLFL() throws InterruptedException, IOException, AWTException  {
	    AccountSummary.PackagesNLFL();
    }
	    @When("Perform the '$MenuProfile' with About Record")     

	     public void Menuprofile_aboutRecord(String MenuProfile) throws InterruptedException, IOException, AWTException{
	           AccountSummary.MenuBillingProfile(MenuProfile);
	           Orderspage.getAboutRecord();
	      } 
	    @When("Perform the CustomerAccount")     
	     public void CustomerAccount() throws InterruptedException, IOException, AWTException{
	           AccountSummary.CustomerAccount();
	           
	      } 
	    @Then("Validate the owned product and services '$action'")
	    public void ValidateOwnedProductServices(String action) throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException  {
	         AccountSummary.OwnedProductServices(action);
	    }
  
	    @Then("Perform '$MenuProfile' and Execute query '$rowname'")     

	    public void MenuProfExecutequery(String MenuProfile,String rowname) throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed, AWTException{
	            AccountSummary.MenuBillingProfile(MenuProfile);
	            Database.ExecuteDBQuery(rowname);
	    }          
  
	   /* @When("Perform '$MenuProfile' with About Record")   

	    public void MenubillingprofAboutRecord(String MenuProfile) throws InterruptedException, IOException, AWTException{
	           
	           AccountSummary.MenuBillingProfile(MenuProfile);
	           Orderspage.getAboutRecord();
	      }*/
	    @When("Validate the BillingProfile status '$logicalName'")
		  public void BillingProfile(String logicalName) throws IOException, InterruptedException, AWTException{
		  
		  AccountSummary.ChangePaymentMethod(logicalName);
		  
	  }
	  @When("Execute DBQurey '$rowname'")
	  public void executeDBqurery(String accountType) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
	  Database.ExecuteDBQuery(accountType);
     }
	  
      @Then("Verify the Product and Services and Attribute for '$Action'")
      public void VerifyAttributesTab(String Action) throws InterruptedException, IOException, AWTException  {
  	  AccountSummary.VerifyAttributesTab(Action);          
      }
      @When("Perform BulkModify with '$rowname'")
      public void BulkMOdify(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.BulkMOdify(rowname);
      
   }
      @When("Add product via BulkModify with '$rowname'")
      public void BulkMOdifyAddProducts(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException, FindFailed, AWTException{
            AccountSummary.BulkMOdifyAddProducts(rowname);
      
   }
      @When("Perform Menu bulkmodify with '$rowname'")
      public void MenuBulkModify(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException, FindFailed, AWTException{
            AccountSummary.MenuBulkModify(rowname);
      
   }
      @When("Validate the Bulk view process status '$rowname'")
      public void BulkViewProcessStatusValidation(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.BulkViewProcessStatusValidation(rowname);
      
   }
      @Then("Validate  the usedproduct services '$action'")
          public void UsedProductServices(String action) throws IOException, InterruptedException, FindFailed, AWTException{
                AccountSummary.UsedProductServices(action);
          }
      @When("Validate the Bulk Modify All Promotions")
      public void BulkModify_TargetPromotion_AllPromotions() throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.BulkModify_TargetPromotion_AllPromotions();
      
   }
      @When("perform BilkModify TargetPromotion import '$rowname'")
      public void BulkModify_TargetPromotionProcess_ImportDialog(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.BulkModify_TargetPromotionProcess_ImportDialog(rowname);
      }
      @When("search the child account '$AccountNo'")
      public void SearchAccountPrePost(String AccountNo) throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.SearchAccountPrePost(AccountNo);
      }
      @When("select new address")
      public void Accounts_AddressLine() throws ClassNotFoundException, SQLException, IOException, InterruptedException{
            AccountSummary.Accounts_AddressLine();
      }
      @When("Perform the upgrade")
      public void PrePost_Upgrade() throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
            AccountSummary.PrePost_Upgrade();
      }
      @When("create new child account '$rowname'")
      public void AccountsPrePost(String rowname) throws InterruptedException, IOException, AWTException {
    	  AccountSummary.AccountsPrePost(rowname);
      }
      
      @Then("Perform AddDeleteMedia '$mediatype'")
      public void MediaType(String LogicalName) throws InterruptedException, IOException, AWTException{
      AccountSummary.AddDeleteMedia(LogicalName);
      } 
      @When("Check Updated By field under customer comms '$rowname'")
      public void ValidateUpdatedByFeild(String rowname) throws InterruptedException, IOException, AWTException {
    	  AccountSummary.ValidateUpdatedByField(rowname);;
      }
      @Then("Validate the Billed product and services '$action'")
	    public void ValidateBilledProductServices(String action) throws InterruptedException, IOException, AWTException  {
	         AccountSummary.BilledProductServices(action);
	    }
		  @When("Check Customer Comms '$rowname'")
      public void ValidateCustComms(String rowname) throws InterruptedException, IOException, AWTException {
    	  AccountSummary.ValidateCustComms(rowname);
      }
		 @Then("Verify SecureNet Care Portal is enabled")
         public void VerifySecureNetCarePortal() throws InterruptedException, IOException  {
		 }
		 @Then("Validate the secureNet Portal butoon")
		    public void SecureNetPortalValidation() throws InterruptedException, IOException, AWTException  {
		     AccountSummary.SecureNetPortalValidation();	 
		      }
		 @Then("Verfiy Customer Comms '$VerifyMessage'")
		    public void VerfiyCustComms(String VerifyMessage) throws InterruptedException, IOException, AWTException  {
		    AccountSummary.CustCommsMsgVerify(VerifyMessage); 
	      }	
		 
		 @Then("Validate DD Details")
			public void CreateDirectDebit()  throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException
			{
				AccountSummary.ValidateDDdetails();
				
			}
		 
  }
