package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import net.thucydides.core.annotations.Steps;
import net.serenitybdd.core.pages.PageObject;
import pages.CataloguePage;
import pages.DatabasePage;

public class Catalogue extends PageObject {
	@Steps	
	private CataloguePage CataloguePage;
	@Steps	
	private DatabasePage Database;
	
	@When("Promotion is added with '$ProductName'")
	public void CatalogueSearch(String ProductName) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Database.RetrievePromotionDetails(ProductName);
		CataloguePage.CatalogueSearch();		
	}
	
	@Then("Check Payment Date and validate the Payment button")
    public void CheckPaymentDate()throws InterruptedException, IOException {
          CataloguePage.CheckPaymentDate();
	}
	
	  @When("Enter the OrderLineDetailDiscount '$Action'")
	    public void OrdersLineItemsLineDetailDiscount(String Action) throws InterruptedException, IOException {
	          CataloguePage.OrdersLineItemsLineDetailDiscount(Action);
	    }
	  
	  @When("Promotion Selection '$Value'")
		public void CatalogueSearch1(String ProductName) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
			Database.RetrievePromotionDetails(ProductName);			
		}	  
	  
    @Then("Validate Address Delete Button '$CheckAddressButton'")
    public void AddressesDeleteButtonCheck(String CheckAddressButton) throws InterruptedException, IOException {
          CataloguePage.AddressesDeleteButtonCheck(CheckAddressButton);
    }
    @When("Select Billing Serviceaccount '$ProductName'")
    public void OrderBillingServiceAccountSelection(String Account) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
    CataloguePage.OrderBillingServiceAccountSelection(Account);
    }
    
    @When("Select Order SubAccount Bill Profile Selection '$ProductName'")
    public void OrderSubAccountsBillingProfileSelection(String Account) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
    CataloguePage.OrderSubAccountsBillingProfileSelection(Account);
    }
    
    @When("Click on '$AccountName'")
    public void SubAccountBillingAccountNameSelection(String Account) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
    CataloguePage.SubAccountBillingAccountNameSelection(Account);
    }
    @Then("Perform Add or Remove products via FastOrder for product '$Action'")
    public void FastOrdersProducts(String Action) throws InterruptedException, IOException {
          CataloguePage.FastOrdersProducts(Action);
    }
    
    @When("Goto Profiles Tab and Select Billing Profile '$Action'")
    public void BillingProfileNameSelection(String Action) throws InterruptedException, IOException {
          CataloguePage.BillingProfileNameSelection(Action);
    }


}
