Story: BRM Channels
Meta:
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario:CR89481_VPS_Indirect_Partner_Enablement_Billing_TS30_TC01_Validate the Proof and balance BIP report. 
         PartnerManaged will be reflected as a new section after the current sections
@Ankesh
Meta:@ReportingNew03
@FolderName ReportingNew03
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'proofAndBalanceReport'

Scenario: InvNew06 CR89481_VPS_Indirect_Partner_Enablement_Billing_TS21_TC01_Validate the account level products are
          added and displayed correctly on the Invoice
@Ankesh
Meta:@InvNew06
@FolderName InvNew06
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Search The Account '7000847692'
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'UpdateVPSLogo'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: CR89481_VPS_Indirect_Partner_Enablement_Billing_TS38_TC01_Validate the Payment summary report via BIP for VPS account
@Ankesh
Meta:@ReportingNew02
@FolderName ReportingNew02
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'receivedPaymentsReport'
When Make Payment for 'ABCPayment'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'receivedPaymentsReport'


Scenario: RatingNew20 CR89481_VPS_Indirect_Partner_Enablement_Billing_TS15_TC01_Rate a Domestic Mpay CDR for a VPS customer
@Tarun/Pankaj
Meta:@RatingNew20
@FolderName RatingNew20
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000361410'
Given Rate a 'VPSDomesticMPAYCDR' CDR
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'