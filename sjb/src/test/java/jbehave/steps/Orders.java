package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;
import pages.OrdersPage;
import pages.AccountSummaryPage;
import pages.DatabasePage;

public class Orders {
	@Steps
	private OrdersPage Orderspage;
	@Steps
	private DatabasePage Database;
	@Steps
	private AccountSummaryPage AccountSummary;
	
	@When("Create new order '$order'")
	public void Create_New_Order(String Order) throws InterruptedException, IOException, FindFailed, AWTException {
		Orderspage.CreateNewOrder(Order);
	}
	
	@Given("get back office agent text")
		public void BackOffice_Agent() throws InterruptedException, IOException, AWTException {
		Orderspage.backOfficeAgent();
		}
	@When("Update MSISDN by performing '$rowname' on '$MenuLineItem'")
	public void MenuLineItems(String MenuLineItem, String rowname) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Database.TryReserveAvailableICCID();
		Database.TryReserveAvailableMSISDN();
		Orderspage.MenuLineItems(MenuLineItem);
		Orderspage.getAboutRecord();
		Database.ExecuteDBQuery(rowname);
	}
	
	@When("Retrieve ICCID")
	public void RetrieveICCID() throws ClassNotFoundException, SQLException {
	Database.TryReserveAvailableICCID();
	
	 } 
	
	@When("Update ICCID against '$LineItemEntry'")
	public void OrderLineItemsEntry(String LineItemEntry) throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed, AWTException {
		Orderspage.OrderLineItemEntry(LineItemEntry);	
		//Database.ExecuteDBQuery(rowname);
	}
	
	@When("Perform Credit Vet")
	public void Credit_Vet() throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.CreditVettingResults(); ;
	}
	
	@When("Perform Credit Vet from Credit Vet Button for '$Action' popup")
	public void OneNet_Business_CreditVet(String Action) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.OneNetBusinessCreditVet(Action); ;
	}
	
	@When("Verify the Order")
    public void Verify_Order() throws InterruptedException, IOException, ClassNotFoundException, SQLException {
          Orderspage.OrderVerify();
    }
	
	@When("Submit the Order '$Action'")
	public void Submit(String Action) throws InterruptedException, IOException, ClassNotFoundException, SQLException, FindFailed, AWTException {
		Orderspage.CaptureOrderDetails();
		Orderspage.OrderFormEntry(Action);
	}
	
	@When("Perform '$OrderRow' on Order Number")
	public void CompleteOrders(String OrderRow) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.OrderDescriptionVerfy(OrderRow);
		Orderspage.getAboutRecord();				
	} 
	@When("Copy Line Item for scenario '$Action'")
	public void CopyLineItem(String Action) throws ClassNotFoundException, SQLException, FindFailed, InterruptedException, IOException, AWTException {
		Orderspage.CopyLineItemElements(Action);
	
	 } 
	@When("Make payment with '$sPaymentMethod'")
	public void OrdersPayment(String sPaymentMethod) throws InterruptedException, IOException, FindFailed, AWTException {
		Orderspage.OrdersPayment(sPaymentMethod);
	}
	@Then("Deliver the order")
    public void DeliverTheOrder() throws InterruptedException, IOException, AWTException{
	Orderspage.Delivery_Postcode();
    }
	
	@When("Modify '$action' with Econfig Item '$EconfigItem'")
    public void EconfigFn(String action,String EconfigItem) throws InterruptedException, IOException, FindFailed, AWTException{
	AccountSummary.UsedProductServices(action);
	Orderspage.Econfig(EconfigItem);	
    }
	
	@When("Modify Owned '$action' with Econfig Item '$EconfigItem'")
    public void EconfigFun(String action,String EconfigItem) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException{
	AccountSummary.OwnedProductServices(action);
	Orderspage.Econfig(EconfigItem);
    }
	
	@When("Customise '$Rownum' with Econfig Item '$EconfigItem'")	
    public void EconfigItem(String EconfigItem,String Rownum) throws InterruptedException, IOException, AWTException, FindFailed{
	Orderspage.CustomiseProduct(Rownum);
	Orderspage.Econfig(EconfigItem);
    }

	@Then("verify the Order line Items '$Action'")
    public void OrdersLineItemsVerify(String Action) throws IOException, InterruptedException, AWTException  {	    	
	Orderspage.OrdersLineItemsVerify(Action);
	}
	
	@When("Add Product '$Product'")
    public void Add_Product(String Product) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
          Orderspage.AddProduct(Product);
    }

	@When("Select Menu '$MenuLineItem'")
	public void Select_Menu(String MenuLineItem) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.MenuLineItems(MenuLineItem);
	}
	
	@Then("About View")
	public void VerifyOrdersAbout_View() throws InterruptedException, IOException, AWTException {
		Orderspage.VerifyOrdersAboutView();
		Orderspage.VerifyColumnsOrderView();
	}
	
	@When("Search Order '$OrderRow'")
    public void SearchOrder(String OrderRow) throws InterruptedException, IOException, AWTException{
	Orderspage.SearchOrder(OrderRow);
	
	}
	
	@When("Order Fulfilment With 'Item'")
	public void Order_Fulfilment_tab(String Item) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.OrdersFulfilmentTab(Item);
	}
	
	@When("Cancel Order")
	public void Cancel_Order() throws InterruptedException, IOException, AWTException {
		Orderspage.CancelOrder();
	}
	@When("Retrieve MISIDN ICCID")
	public void RetrieveMSISDNICCID()  throws InterruptedException, IOException, ClassNotFoundException, SQLException {
		Database.TryReserveAvailableICCID();
		Database.TryReserveAvailableMSISDN();
	} 
	
	@When("Order Status Validation")
	public void OrderCompletionValidation() throws InterruptedException, IOException, AWTException {
		Orderspage.OrderCompletionValidation();
	}
	
	
	@When("Econfig Item '$EconfigItem'")	
    public void AddEconfigItem(String EconfigItem) throws InterruptedException, IOException, AWTException, FindFailed{
	Orderspage.Econfig(EconfigItem);
    }
    @When("Perform the Action '$rowname'")
    public void OrderDescriptionVerfy(String rowname) throws InterruptedException, IOException, AWTException {
          Orderspage.OrderDescriptionVerfy(rowname);
    }
    @When("Perform the AboutRecord")
    public void AboutRecord() throws InterruptedException, IOException {
          Orderspage.getAboutRecord();
    }
    @When("Perform order billing profile selection '$rowname'")
    public void OrderBillingProfileSelection(String rowname ) throws InterruptedException, IOException, AWTException {
          Orderspage.OrderBillingProfileSelection(rowname);
    }
	@When("Complete the order by '$OrderRow'")
	public void CompleteOrder(String OrderRow) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.OrderDescriptionVerfy(OrderRow);
		Orderspage.getAboutRecord();				
	} 
	@When("Execute the query '$rowname' and '$rowname1'")
	public void CompleteOrder1(String rowname, String rowname1) throws InterruptedException, IOException, ClassNotFoundException, SQLException {		
		Database.ExecuteDBQuery(rowname);
		Database.ExecuteDBQuery(rowname1);
	} 
	
	@Then("validate the order list '$rowname'")
	public void OrderListVerification(String rowname) throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.CaptureOrderDetails();
		Orderspage.OrderListVerification(rowname);		
	}
	 @When("Perform Retrive Bilk MSISDNs")
	    public void RetrieveBulkMSISDN() throws InterruptedException, IOException, AWTException {
	      Orderspage.RetrieveBulkMSISDN();
	 }
	 
	@When("Click on Order Tab")
	  public void ClickOrdertab() throws InterruptedException, IOException {
    }
		
	@Then("Check Order Status")
	  public void CheckOrderStatus() throws InterruptedException, IOException, ClassNotFoundException, SQLException, AWTException {
		Orderspage.OrderStatusCheck();			
	}			
}
