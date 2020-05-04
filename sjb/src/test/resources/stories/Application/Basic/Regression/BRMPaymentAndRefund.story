Story: BRM Payment And Refund
Meta:@BRMPayment&Refund
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM
 
 Scenario:R2.3_CR43205_Billing_TS07_TC_01_EBU_Payments management_Validate that in Proof and Balance Report, Payment will be 
         segregated by the account type field of the customer account
@Ankesh
Meta:@PR28
@FolderName PR28
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
Then Generate Month End Report from SBExport for 'proofAndBalanceReport'

Scenario:R2.3_CR 40156_Billing_TS_02_TC01_Validate only Recurring CC Payments are returned if the Payment type selected Recurring CC Payments with Transaction id field populated for Recurring CC Payments for the specified date range
@Vivek
Meta:@PR33
@FolderName PR33
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
And Execute query 'AccountBillDetail'
And Execute query 'RetrievePendBalanceCheck'
And Make Payment for 'CCAccountNBilloption'
And Execute query 'PaymentSearchCeditCardOutput'
And Execute query 'PaymentSearchCCOutput'
Given Login with 'AGENT04' in 'Env'
Then Search Payment in Seibel UI Site Map 'RecurringCCPayment'
And Validate Payment in Siebel UI Site Map 'RecurringCCPayment'

Scenario:NewCo_CCS10.0_CR113083 _Show correct discount end date on invoice
@Vivek
Meta:@PRNew10
@FolderName PRNew10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with '110041PostpaidPromotion'
And Customise 'RootProduct' with Econfig Item 'VodafoneSecureNetON'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PRNew09 CR_82099_BRM_Payments_TC12_BRM to BRM_Verify that Externally initiated payment ABC are applied on correct balance group of Parent child hierarchy account and 
          getting reflected on correct invoice for EBU Customers
@Ankesh
Meta:@PRNew09
@FolderName PRNew09
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'TALKMOBILE' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
And ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
And Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Perform Credit Vet
And Submit the Order 'NormalSubmitforSubAccount'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'GetBalanceGrpAccntMsisdnCapt'
And Execute query 'GetCurrntBalAccNoCapture'
Given Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
And Execute query 'UpdateVendorAccountTable'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'ABCPayment'
And Execute query 'GetBalanceGrpAccntMsisdnCapt'
And Execute query 'GetCurrntBalAccNoCapture'
And Execute query 'PayingChildRetrievalCapture'
And Execute query 'MSISDNFromAccount'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Show All Purchase Products in the account ''
When Execute query 'PayingChildRetrievalNonBilling'
When Execute query 'GetCurrntBalChildAccNoCapture'
And Execute query 'GetBalanceGroupAccMsisdn1'
Given Rate a 'DomestRating50EDROutPartial' CDR
When Execute query 'ComparingtheChildRatingValue'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
And Generate Month End Invoice with Account 'ACCOUNTNO0'
When Make Payment for 'ABCPayment'
And Execute query 'GetCurrntBalChildAccNoCapture'
And Execute query 'GetBalanceGroupAccMsisdn1Capt'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario:CR35698_Billing_TS01_TC01_Payment_Refunds_Solution_Enhancements_Back office agent searches the payment 
         for reversal and perform reversal flow from Siebel successfully.
@Ankesh
Meta:@PR08
@FolderName PR08
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentReversal 'ReversedPaymentRow1'
And Payment Reversal Submit
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'ReversedPaymentValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PR01 R2.1_Billing_TS01_TC01_Payment for line rental is successfully taken in 
          BRM using Direct Debit at the end of bill cycle
@Ankesh
Meta:@PR01 - DD export report extract next day
@FolderName PR01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Payment Refund Reversal Validation 'DDpaymntStatusSucessful'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PR02 R2.1_Finance_TS01_TC01_Validate that Payment is successfuly for Recurring Credit Card
@Tarun
Meta:@PR02
@FolderName PR02
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievePendBalanceCheck'
And Make Payment for 'CCAccountNBilloption'
When Execute query 'CCPNAMOUNT0'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'RetrieveCCPaymentPaid'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'RecurringCCPayValidate'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: PR22 CR_33804_BRM_Adhoc Payment_TC04_Siebel to BRM_ PCI Compliant – Outsourced Agent successfully performs Ad-Hoc Invoice Payments using an existing Debit or Credit card
@Tarun
Meta:@PR22
@FolderName PR22
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Click On PaymentRefund Button 'AdhocPaymentClick'
When Enter Refund Details 'AdhocCCPayment'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PR29 146431_R2.3_CR35698_Billing_TS18_TC01_Payment_Refunds_Solution_Enhancements_Front office agent performs the reversal on previously written off account debt
@Tarun
Meta:@PR29
@FolderName PR29
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
When Search The Account ''
When Execute query 'WriteOff1'
Then WriteOff 'WriteOff'
When Execute query 'WriteOff2'
When Execute query 'WriteOff3'
Then WriteOff 'ReverseWriteOff'
When Execute query 'WriteOff4'
When Execute query 'WriteOff5'



Scenario: PR12 BRM_CR88401_Refunds_Siebel to BRM_TC06_To verify that the Refund process is successful when Unbilled usage amount is greater than Unallocated Over Payment amount, for a CBU customer
@Tarun
Meta:@PR12
@FolderName PR12
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'PR12' CDR
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedTypePR12'
When Make Payment for 'LockBoxDDPayment'
When Execute query 'PaymentReceivedType123'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'ExtraPayment64'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PR20 R2.4.2_24281_Billing_TS13_TC01_Validate that refund is successfully made on Recurring Credit-Card Payment via Amex card
@Tarun
Meta:@PR20
@FolderName PR20
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'TokenizationAmex' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Generate First Bill
And Show All Purchase Products in the account ''
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'CCAccountNBilloption'
When Make Payment for 'LockBoxCardPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount12'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'CreditCardRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PR38 R2.2_CR26554_Billing_TS05_TC_01_Recurring CC Payment Search on suspense account
@Tarun  - use retrieve promotion
Meta:@PR38
@FolderName PR38
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Generate First Bill
And Show All Purchase Products in the account ''
Given Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'CCSuspensePayment'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Make Suspense Payment 'SuspensePaymentResult'
When Execute query 'suspenseAmount'
Then Suspense Payment Validation 'CCSuspenseValidation'

Scenario: PR17 BRM_CR88401_Refunds_Siebel to BRM_TC03_To verify that the Refund process is successful when Unbilled usage amount is 
          less than Unallocated Credit Adjustment + Over Payment amount, for a CBU customer
@Ankesh
Meta:@PR17
@FolderName PR17
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNoIndex1'
Given Rate a 'DomesticData10Rating' CDR
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Make Payment for 'Lockbox100Payment'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsage'
When Submit Adjustment 'RestrictedFriends&Family'
And ReLogin with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'RefundAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: PR23 R2.3_CR35698_Billing_TS25_TC01_Validate that the Refund Summary Daily report is generated in 
          BRM on the basis of daily performed refund process.
@Ankesh
Meta:@PR23
@FolderName PR23
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization1' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'ABCAmount0Payment'
When Make Payment for 'LockBoxPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
Then Generate Month End Report from SBExport for 'refundSummaryReport'

Scenario: PRNew03 168920_R2.4.1_49708_Billing_TS03_TC01_Validate bill presentment for negative or zero balance owed for a SME Consumer having DD profile
@Ankesh
Meta:@PRNew03
@FolderName PRNew03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
And Make Reverse Payment for 'Type1and0RC'
When Execute query 'GetLast2Bill'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'FailureReasonReferToPayer'

Scenario: PR37 R2.3_CR43205_billing_TS08_TC01_EBU_FD_Payments management_Validate that for Suspense account , all payments will be reported together with no differentiation between Consumer, Business or Sole Trader 
         payments in Proof and Balance Report
@Ankesh
Meta:@PR37
@FolderName PR37
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
Then Generate Month End Report from SBExport for 'proofAndBalanceReport'

Scenario: PaymentRefund01 NewCo_CCS 9.0_CR 93160_Additional_Perform DD refund after pay type changes to Bill Me or CC from DD to validate refund SBExport reports
@Ankesh-Partial(Next day report download- DDRefundReport)
Meta:@PaymentRefund01
@FolderName PaymentRefund01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'LengthValidationOfDebitNum'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Make Payment for 'LockBoxPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And Change Payment for scenario 'DirectDebitToBillMe'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount10'
And Enter Refund Details 'DirecttoBillmeRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'

Scenario: PaymentRefund02 DELETED - 14631 - NewCo_CCS 6.0_CR 105400_eCare_TC07_Validate that the refund amount is correctly processed and displayed correctly 
          on invoice after refund from Siebel
@Ankesh-Partial Development need to validate Custom Comms and Invoice XML
Meta:@PaymentRefund02
@FolderName PaymentRefund02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'PR12' CDR
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedTypePR12'
When Make Payment for 'LockBoxDDPayment'
When Execute query 'PaymentReceivedType123'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'ExtraPayment64'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
Given Login to WCC with 'LoginToWCC' in 'Env'
And Search Invoice in WCC

Scenario: CCS 8.0_CR110148_Direct Debit Refund Fixes_TS10_Billing_TC01_DD refund should be failed in siebel when any of the DD Details is BLANK
@Ankesh
Meta:@BillJourney10
@FolderName BillJourney10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'DDAccount'
When Make Payment for 'LockBoxDDPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'DDSortCodeNull'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'ExtraPayment64'
And Enter Refund Details 'RefundValidation'


Scenario:PaymSBExport DB Encryption_TS06_Verify DD payments report is generated successfully and ACCOUNT_NO and SORT_CODE are in decrypted format when 
          encrypted details are changed to decrypted format in BRM DB
@Ankesh
Meta:@SBExportReport
@FolderName SBExportReport
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'DDSortCode'
When Execute query 'LengthValidationOfDebitNum'
When Execute query 'UpdateDebitNum'
When Execute query 'UpdateBankno'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'DDAccount'
When Execute query 'DDSortCode'
When Execute query 'LengthValidationOfDebitNum'


Scenario: InvoiceDoc01 NewCo CCS 4.1_BRM_73711_The bill template for customers with the Sole trader  account type should be the Consumer template, not the business template
@Ankesh
Meta:@InvoiceDoc01
@FolderName InvoiceDoc01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create New Billing Profile 'NewPostpaid'
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
Given Login to WCC with 'LoginToWCC' in 'Env'
And Search Invoice in WCC

Scenario: PaymentRefund10 R2.4.2_57285_Billing_TS01_TC01_Validate that BRM identifies and reverses the payment when customer raises an indemnity 
          claim for the payment made through DD and its is reflected on the reversal report including the Reversal Type= DDIC
@Ankesh
Meta:@PaymentRefund10
@FolderName PaymentRefund10
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
And Make Reverse Payment for 'Type2and3RC'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'DDpaymentStatusReversed'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'detailedReversedPaymentsReport'

Scenario:BRMInvoiceing1 DELETED - 11392 - NewCo_CCS 6.0_CR 105400_BRM_TS09_Validate that correct amount of payment and refund are displayed over invoice 
         when payment is made & reversed within same month for EBU Customer
@Ankesh
Meta:@BRMInvoiceing1
@FolderName BRMInvoiceing1
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for 'ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentReversal 'BankPaymSuccessful'
And Payment Reversal Submit
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: PR16 R2.1_Billing_TS02_BRM_TC01_Validate partial online payment of customer with one billing profile and one invoice generated
@Kiran
Meta:@PR16
@FolderName PR16
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
And Make Payment for 'CCpartialPaymnt'
When Execute query 'PaymentReceivedType5Partial'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'


Scenario: PRNew015 NewCo CCS 9.0_Defect 6744_PBI102353_Card Refund Failures through Siebel
@Vishwa
Meta:@PRNew015
@FolderName PRNew015
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'DDPayType'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for 'ABCPayment500'
Given Login with 'Refund1' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'Paymentmethodcapture'
And Enter Refund Details 'OneTimeRefundPayment'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'AdhocCCpayementRefund10AmountValidationations'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: PRNew016 NewCo_CCS 18.0_VFUS-794_Refund Process Improvement_TS12_Perform 
the DD refund for the available credit amount where payment method changed from DD to CC
@Vishwa
Meta:@PRNew016
@FolderName PRNew016
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'DDPayType'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedTypePR12'
When Make Payment for 'LockBoxDDPayment'
When Execute query 'Tokenization'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Search The Account ''
When Execute query 'CCPayType'
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'DDPayValidate'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'DDRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PRNew017 NewCo_CCS 18.0_VFUS-794_Refund Process Improvement_TS04_Perform the One-Time CC refund for the credit amount due to credit adjustment + overpayment
@Vishwa
Meta:@PRNew017
@FolderName PRNew017
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocPaymentClick'
And Enter New Card Details 'AdhocCCPaymentAmount50'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for '50ABCPayment'
Given Perform Bulk Adjustment 'BulkAdjPYTaxcode'
Given Login with 'Refund1' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocCCPaymentValidate'
And Enter Refund Details 'OneTimeRefundAmount5'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocCCpayementValidations'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: PRNew018 NewCo_CCS 18.0_VFUS-794_Refund Process Improvement_TS07_Perform the CC refund for the Adhoc CC overpayment done where payment method DD and there is DD payment available
@Vishwa
Meta:@PRNew018
@FolderName PRNew018
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'DDPayType'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
And ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocPaymentClick'
And Enter New Card Details 'AdhocCCPaymentAmount50'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocCCPaymentValidate'
And Enter Refund Details 'CreditCardRefundWithExpiryMonth'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'refundCCDailyReport'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: PRNew019 Perform invoicing where dispute has been applied on the account, Overpayment is present, refund and reversals are also present
@Vishwa
Meta:@PRNew019
@FolderName PRNew019
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for 'ABCPayment500'
Given Login with 'Refund1' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'AdhocCCPaymentValidate'
And Enter Refund Details 'OneTimeRefundAmount5'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LocateAdhocCCpayement'
And Click On PaymentReversal 'ReversedPaymentRow1'
And Payment Reversal Submit
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LocateStatusReversed'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: PRNew020 R2.4.2_57285_Billing_Validate that BRM identifies and reverses the direct debit rejected payment received from ARUDD file and it is reflected inthrough DD and its is reflected on the reversal report including the Reversal Type=  ARU
@Ankesh
Meta:@PRNew020
@FolderName PRNew020
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'DDPayType'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LocateDDPayment'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Make Reverse Payment for 'Type1and0RC'
When Execute query 'GetLast2Bill'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'FailureReasonReferToPayer'
When Execute query 'DDPayTypeARUReversedBeforeODI'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'VFPaymentScen'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'DDPayTypeARUReversedAfterODI'
Given Login To Putty 'BRMPutty3' in 'Env'
Then Generate Month End Report from SBExport for 'reversedPaymentsReport'


Scenario: PRNew021 CCS 8.0_CR110148_Direct Debit Refund Fixes_TS05_Billing_TC01_DDPaymentsExport report is successfully extracted after the DD account details are changed for customer where payment is made with old DD account details
@Vishwa
Meta:@PRNew021Dup
@FolderName PRNew021
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000757876'
Then Validate DD Details