package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import net.thucydides.core.annotations.Steps;
import pages.DatabasePage;
import pages.OrdersPage;

public class Database {
	@Steps
	private DatabasePage Database;
	@Steps
	private OrdersPage Orders;
	
	@When("Execute query '$rowname'")
	public void ExecuteQuery(String rowname) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
		Database.ExecuteDBQuery(rowname);
	}
	
	@When("Reserve Order")
	public void Reserve_Order() throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException{
		Orders.CaptureOrderDetails();
		Database.ExecuteDBQuery("ReserveOrder");
	}
	@When("Popup show")
	public void Popup() throws  IOException, InterruptedException, AWTException{
		Database.Popup();
	}
	@When("Update Multiple VSM")
	public void ExecuteQueryMultiVSM() throws Exception, SQLException {
		Database.UpdateVSMMultiple_fn();
	} 
	@When("Update Multiple MSISDNs")
	public void ExecuteQueryMultiMSIDNs() throws ClassNotFoundException, SQLException, IOException{
		Database.UpdateMSISDNMultiple_fn();
	}
	@When("Update Multiple ICCIDs")
	public void ExecuteQueryMultiICCID() throws ClassNotFoundException, SQLException, IOException{
		Database.UpdateICCIDMultiple_fn();
	}
	@When("Retrieve Promotion '$productKey'")
	public void Retrieve_Promotion(String productKey) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
		Database.RetrievePromotionDetails(productKey);
	}
  
	@Given("Retrieve Account '$productKey'")
	public void Retrieve_Account(String productKey) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
		Database.RetrieveAccount(productKey);
	}
	
	@Given("Retrieve BRM Account '$productKey'")
	public void Retrieve_BRM_Account(String productKey) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
		Database.RetrieveAccountBRM(productKey);
	}
    @When("Get Address '$Address'")
	public void Get_Address(String Address) throws ClassNotFoundException, SQLException, IOException, InterruptedException{
		Database.GetAddress(Address);
	}
	

}
