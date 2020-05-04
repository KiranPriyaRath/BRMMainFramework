package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import pages.AccountsPage;
import pages.ServerManagementPage;
import utilities.SikuliUtility;
import pages.BRMSiebelPage;


public class BRMSiebelStep {

	@Steps
	private AccountsPage Accountspage;
	
	@Steps
	private BRMSiebelPage BrmSiebel;
		@When("DrillDown to BillingProfile '$BillingProfileType'")
		public void DrillDownToBillingProfile(String BillingProfileType) throws InterruptedException, IOException, FindFailed {
			BrmSiebel.DrillDowntoBillingProfile(BillingProfileType);		
		}
		@When("Validate the Legal Status '$BillingProfileType'")
		public void LegalGropusMember(String BillingProfileType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.LegalGropusMember(BillingProfileType);
		}
		
		@When("Click On PaymentRefund Button '$BillingProfileType'")
		public void ClickOnPaymentRefund(String OrderType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.ClickOnPaymentRefund(OrderType);		
		}
		@When("Enter Refund Details '$BillingProfileType'")
		public void PaymentRefund(String OrderType) throws InterruptedException, IOException, FindFailed,AWTException {
			BrmSiebel.PaymentRefund(OrderType);		
		}
		@When("Payment Refund Reversal Validation '$BillingProfileType'")
				public void AdhocPaymentRunQuery(String OrderType) throws InterruptedException, IOException, FindFailed,AWTException, ClassNotFoundException, SQLException {
			BrmSiebel.AdhocPaymentRunQuery(OrderType);		
				}
		@When("Click On PaymentReversal '$BillingProfileType'")
			public void ClickOnPaymentsPaymentReversal(String OrderType) throws InterruptedException, IOException, FindFailed ,AWTException, ClassNotFoundException, SQLException{
			BrmSiebel.ClickOnPaymentsPaymentReversal(OrderType);		
			}
		@When("Payment Reversal Submit")
			public void PaymentReversal() throws InterruptedException, IOException, FindFailed ,AWTException {
			BrmSiebel.PaymentReversal();		
			}
		@When("DrillDown to Latest Bill '$BillingProfileType'")
			public void BillsAdjustments(String OrderType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.BillsAdjustments(OrderType);		
			}
		
		@When("Item Bill Level Adjustment '$OrderType' '$CycleForward'")
			public void ItemLevelServiceCharges(String OrderType, String CycleForward) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.ItemLevelServiceCharges(OrderType);
			BrmSiebel.ItemLevelSummaryCharges(CycleForward);
		}
			
			@When("Item Level Service Charges '$OrderType'")
			public void ItemLevelServiceCharges(String OrderType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.ItemLevelServiceCharges(OrderType);
						}
			
			@When("Itemized List search '$OrderType'")
			public void ItemisedList(String BillingProfileType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.ItemisedList(BillingProfileType);
						}
			
		@When("Item Level Summary Charges '$CycleForward'")
			public void ItemLevelSummaryCharges(String CycleForward) throws InterruptedException, IOException, FindFailed {
			BrmSiebel.ItemLevelSummaryCharges(CycleForward);
			}
		@When("Bundle Usage Validation '$MSISDNSelection'")
			public void Bundle_Usage_Validation(String MSISDNSelection) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.BundleUsage_Services_fn(MSISDNSelection);
		}
		@When("Minute And SMS Bundle Usage Validation '$iRowNo'")
			public void BundleUsage_Balance_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
			BrmSiebel.BundleUsage_Balance_fn(iRowNo);
		}
		@When("Detail Bundle Usage Validation '$iRowNo'")
		public void BundleUsage_BalanceDetails_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.BundleUsage_BalanceDetails_fn(iRowNo);
	
		}
		@When("Product Bundle Usage Validation '$iRowNo'")
			public void BundleUsage_Products_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
			BrmSiebel.BundleUsage_Products_fn(iRowNo);
		}
		
		@When("Validate usage list Services '$iRowNo'")
		public void UsageListServices_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.UsageListServices_fn(iRowNo);
	}
		
		@When("Validate Usage List Summary Charges '$iRowNo'")
		public void UsageListSummaryCharges_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.UsageListSummaryCharges_fn(iRowNo);
	}
		
		@When("Validate Itemised List Selection TopUp '$iRowNo'")
		public void ItemisedListSelectionTopUp_fn(String iRowNo) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.ItemisedListSelectionTopUp_fn(iRowNo);
	}
		
		@When("Submit Adjustment '$BillingProfileType'")
			public void BillLevelAdjustment(String OrderType) throws InterruptedException, IOException, FindFailed ,AWTException {
			BrmSiebel.BillLevelItem(OrderType);		
			}
		@When("Submit BillLevel Adjustment '$BillingProfileType'")
			public void BillLevelAdjustmentnew(String OrderType) throws InterruptedException, IOException, FindFailed ,AWTException {
			BrmSiebel.BillLevelAdjustment_new(OrderType);		
			}
		
		@When("Event Level Adjustment selection '$BillingProfileType'")
			public void ItemisedListSelectionClickAdjust(String OrderType) throws InterruptedException, IOException, FindFailed ,AWTException{
			BrmSiebel.ItemisedListSelectionClickAdjust(OrderType);		
			}
		
		@Then("Unbilled Event Level Adjustment selection '$BillingProfileType'")
			public void Unbilled_event_level_selection(String OrderType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.EventLevelMSISDNSelect_fn(OrderType);		
			}
		
		@Then("Unbilled Type of Usage Adjustment selection '$BillingProfileType'")
			public void Unbilled_event_typeof_usage(String OrderType) throws InterruptedException, IOException, FindFailed,AWTException {
			BrmSiebel.EventLevelAdjustment_fn(OrderType);		
			}
		
		@Then("Payment Transfer '$BillingProfileType'")
			public void Payment_transfer(String OrderType) throws InterruptedException, IOException, FindFailed,AWTException {
			BrmSiebel.ClickOnPayments_PaymentTransfer_fn(OrderType);		
			}
		
		@Then("TopUpHistoryValidation '$BillingProfileType'")
		public void TopUp_History(String BillingProfileType) throws InterruptedException, IOException, FindFailed,AWTException {
		BrmSiebel.TopUpHistory(BillingProfileType);		
		}
		
		@Then("WriteOff '$BillingProfileType'")
		public void WriteOff(String BillingProfileType) throws InterruptedException, IOException, FindFailed,AWTException {
		BrmSiebel.WriteOff(BillingProfileType);		
		}
		
		@Then("Search Payment in Seibel UI Site Map '$rowname'")
        public void PaymentSearch_fn(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.PaymentSearch_fn(rowname);
        }
		
		@Then("Validate Payment in Siebel UI Site Map '$rowname'")
        public void ValidatePaymentResult_fn(String rowname) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.ValidatePaymentResult_fn(rowname);
		}
			
		@Then("Make Suspense Payment '$BillingProfileType'")
		public void SuspensePayment(String BillingProfileType) throws InterruptedException, IOException, FindFailed,AWTException {
		BrmSiebel.SuspensePayment(BillingProfileType);
		}
		
		@Then("Suspense Payment Validation '$BillingProfileType'")
		public void SuspensePaymentValidation(String BillingProfileType) throws InterruptedException, IOException, FindFailed,AWTException {
		BrmSiebel.SuspensePaymentResult(BillingProfileType);	
		}
		
		
		@Then("Check Credit Alert Status '$QueryName'")
		public void waitplease(String QueryName) throws InterruptedException, IOException, FindFailed,AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.CreditStatusCheck(QueryName);	
		}
		
		@Then("Set Credit limit in siebel")
		public void CheckCreditLimit() throws InterruptedException, IOException, FindFailed,AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.CheckCreditLimit();	
		}
		@When("Validate Siebel Payment '$BillingProfileType'")
		public void PaymentSiebelValidation(String OrderType) throws InterruptedException, IOException, FindFailed, AWTException {
			BrmSiebel.PaymentSiebelValidation(OrderType);		
		}
		
		@Then("Validate the Creditcard '$BillingProfileType'")
		public void CreditcardValidation(String BillingProfileType) throws InterruptedException, IOException, FindFailed,AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.CreditcardValidation(BillingProfileType);
		}
		@When("Create Payment arrangement for P2P '$Installments'")
		public void CreatePaymentArrangement(String Installments) throws InterruptedException, IOException, FindFailed, AWTException {
		BrmSiebel.CreatePaymentArrangement(Installments);		
		}
		@Then("Validate Alert for Payment arrangement '$Installments'")
		public void ValidatePaymentArrangementAlert(String Installments) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.ValidatePaymentArrangementAlert(Installments);		
		}		
		@Then("Validate Payment arrangement '$Installments'")
		public void ValidatePaymentArrangement(String Installments) throws InterruptedException, IOException, FindFailed, AWTException, ClassNotFoundException, SQLException {
		BrmSiebel.ValidatePaymentArrangement(Installments);		
		}
		
		@When("Enter New Card Details '$rowno'")
		public void OneTimePayment(String rowno) throws InterruptedException, IOException, FindFailed,AWTException {
			BrmSiebel.OneTimePayment(rowno);		
		}
}
