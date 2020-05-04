package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
//import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.sikuli.script.FindFailed;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import pages.BRMPuttyPages;
import pages.DatabasePage;
import utilities.SikuliUtility;
import utilities.common;

public class BRMPutty {
	@Steps
	private common common1;
	@Steps
	private SikuliUtility sikuliUtility;	
	@Steps
	private BRMPuttyPages BRMPuttyPages;

	@Steps
	private DatabasePage Database;
	

	@Given("Pre-requisite with logs in folder '$FolderName'")
	@Step
	public void Prerequisite(@Named("FolderName")String FolderName) throws JSchException, IOException, SftpException{
		BRMPuttyPages.Prerequisite(FolderName);
	}
	
	@Given("Capture Bill Amount")

	@Step
	public void CaptureBillAmount() throws Exception{
		BRMPuttyPages.CaptureBillAmount();
}
	
	@When("Make Reverse Payment for '$Index'")
	@Step
	public void DDReversalARUDD(String Type) throws Exception {
	BRMPuttyPages.DDReversalARUDD(Type);
	}
	
	
	@Given("Login To Putty '$PuttyLogFileName' in '$Environment'")

	@Step
	public void BRMPuttyLogin(String PuttyLogFileName,String Environment) throws InterruptedException, IOException, FindFailed, AWTException, JSchException, SftpException{
		//String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
		//String SikulifilePathForSavingFile = "";//\\target\\site\\serenity
		BRMPuttyPages.BRMPuttyLogin(PuttyLogFileName,Environment);

	}
	
	@When("Payment Date Change UEL")
	@Step
	public void PaymentDateChangeUEL_fn() throws Exception{
		BRMPuttyPages.PaymentDateChangeUEL_fn();
	}
	
	@Given("Account charge Apply")
	@Step
	public void AccountChargeApplyfn() throws Exception{
		//String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
		//String SikulifilePathForSavingFile = "";//\\target\\site\\serenity		
		BRMPuttyPages.AccountChargeApply();
}
	
	@Given("Rate a '$RatingType' CDR")
	@Step
	public void Rating(String RatingType) throws Exception{
				//String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
				//String SikulifilePathForSavingFile = "";//\\target\\site\\serenity		
				BRMPuttyPages.PuttyRating(RatingType);
	}
	
	@When("Rate bulk '$RatingType' CDR")
	@Step
	public void PuttyBulkRating(String RatingType) throws Exception{
				//String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
				//String SikulifilePathForSavingFile = "";//\\target\\site\\serenity		
				BRMPuttyPages.PuttyBulkRating(RatingType);
	}
	
	@Given("Rate bulk '$RatingType' CDR")
	@Step
	public void BulkRating(String RatingType) throws Exception{
				BRMPuttyPages.BulkRating(RatingType);
	}
	
	@Given("Generate First Bill")

	@Step
	public void BillNow() throws Exception{
		//String SikulifilePath = "\\src\\test\\resources\\BRM_Snapshot";
		//String SikulifilePathForSavingFile = "";//\\target\\site\\serenity		
		BRMPuttyPages.BillNow();
}
	@Given("Check Schema2 Account")

	@Step
	public void Schema2Account() throws Exception{
		BRMPuttyPages.Schema2Account();
}
	
	@Given("Select the TimeStamp '$Index'")
	@Step
	public void UnixTimestampfn(String RowNo) throws Exception{		
		BRMPuttyPages.UnixTimestamp(RowNo);
}
	
	@Given("Show All Purchase Products in the account '$Account_No'")
	@Step
	public void Purchase_Products(String Account_No) throws IOException, AWTException, InterruptedException, ClassNotFoundException, SQLException {
		BRMPuttyPages.Purchase_Products(Account_No);	
		
	}
	@Given("Generate Month End Bill with Account '$Index'")
	@Step
	public void Bill_fn(String Index) throws Exception {
		BRMPuttyPages.Bill_fn(Index);
	}		
	
	@Given("Generate Month End Invoice with Account '$Index'")
	@Step
	public void Invoice_fn(String Index) throws Exception {
		BRMPuttyPages.Invoice_fn(Index);
	}
	@Then("Generate Month End Report from SBExport for '$Index'")
	@Step
	public void SBExportReport(String Index) throws Exception {
	BRMPuttyPages.SBExportReport(Index);
	BRMPuttyPages.FileTransferToLocalFromUNIX();
	}
	@Then("Download the Generated Report file to local system")
	@Step
	public void FileTransferToLocalFromUNIX() throws Exception {
	BRMPuttyPages.FileTransferToLocalFromUNIX();
	}
	@Then("Download the EDW Usage file to local system")
	@Step
	public void EDWUsageFilefn() throws Exception {
	BRMPuttyPages.EDWUsageFilefn();
	BRMPuttyPages.FileTransferToLocalFromUNIX();
	}
	@When("Verifying Adjustment Done on the Account")
	@Step
	public void AdjustmentDetails() throws Exception {
	BRMPuttyPages.AdjustmentDetails();
	}
	@When("Run ccReport from Putty")
	@Step
	public void ccRefundReport() throws Exception {
	BRMPuttyPages.ccRefundReport();
	}
	@Then("Generate GLReport for '$Index'")
	@Step
	public void GLReportfn(String GLReport) throws Exception {
	BRMPuttyPages.GLReportfn(GLReport);
	BRMPuttyPages.FileTransferToLocalFromUNIX();
	}
	
	@When("Make Payment for '$Index'")
	@Step
	public void PuttyPayment(String BillingProfileType) throws Exception {
	BRMPuttyPages.PuttyPayment(BillingProfileType);
	}
	
	@When("Make Credit Alert '$Index'")
	@Step
	public void CreditAlert(String Option) throws Exception {
	BRMPuttyPages.CreditAlert(Option);
	}
	@When("Check Alert in Siebel DB")
	@Step
	public void CreditAlertSiebel() throws Exception {
	BRMPuttyPages.CreditAlertSiebel();
	}
	
	@Given("Perform Bulk Adjustment '$Index'")
	@Step
	public void BulkAdjustment(String SelectChoice) throws Exception{		
				BRMPuttyPages.BulkAdjustment(SelectChoice);
	}
	
	@Given("Enter Docgen details '$rowname'")
	@Step
	public void Docgen(String Sequence) throws Exception{ 
		BRMPuttyPages.Docgen(Sequence);
	}
	
	@Then("Docgen logfile details")
	@Step
	public void DocgenLogs() throws Exception{ 
		BRMPuttyPages.DocgenLogs();
	}
	
	@Then("Printshop File extraction")
	@Step
	public void PrintshopFileExtraction() throws Exception{ 
		BRMPuttyPages.PrintshopFileExtraction();
		BRMPuttyPages.FileTransferToLocalFromSOAPutty();
	}
	
	
	@When("Run OverPayment utility '$Index'")
	@Step
	public void OverpaymentUtility(String SelectChoice) throws Exception{ 
		BRMPuttyPages.OverpaymentUtility(SelectChoice);	
	}
	
	@When("Run P2P batch '$Index'")
	@Step
	public void P2PBatchRun(String SelectChoice) throws Exception{ 
		BRMPuttyPages.P2PBatchRun(SelectChoice);	
	}
	@When("Final bill generation for '$Index'")
	@Step
	public void FinalBillGeneration(String RowNo) throws Exception{ 
		BRMPuttyPages.FinalBillGeneration(RowNo);	
	}
	@When("Run Reconnection utility")
	@Step
	public void ReconnectionUtility() throws Exception{ 
		BRMPuttyPages.ReconnectionUtility();	
	}
	@When("Run mta payment Refund")
	@Step
	public void MTAPaymentRefund() throws Exception{ 
		BRMPuttyPages.MTAPaymentRefund();	
	}
}
