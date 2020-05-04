Story: BRM Rating
Meta:@BRMStory
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario:Adhoc05 Post to Pre- check charges in BRM
Meta:@Adhoc05
@FolderName Adhoc05
@Payel
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New 'NewPrepaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'PosttoPreWithPromotion'
When Retrieve Promotion 'PrepaidPackage'
And Perform the upgrade
And Reserve Order
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:PretoPost CR_82099_BRM_Pre to Post Migration_TS09_Siebel to BRM_Verify that new default account level balance group is created when agent place an order for pre to post migration for CBU costumer
Meta:@PretoPost
@FolderName PretoPost
@Payel
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Retrieve Promotion 'PrepaidPackage'
Then Perform the action 'PretoPostWithPromotion'
When Retrieve Promotion 'PostpaidPromotion4G'
And Perform the upgrade
And Reserve Order
And Perform Credit Vet
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim111'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Invoice with Account 'ACCOUNTNO0'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: OM07 BRM_R2.2_CR_27375_UC_R2.1_2037.b_TS_01_TC01_BDOM Validation for new order placed with new billing profile having data services
@Pankaj
Meta: @OM07
@FolderName OM07
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
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
And Generate First Bill
When Execute query 'TodaysDate3'
When Execute query 'BDOMCheckNC'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:OM03 146393_R2.3_CR19645_Sharer_TS30_TC01_Generate invoice for account with one billing profile, two services and a Sharer Group
@Vaishnavi
Meta:@OM03
@FolderName OM03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Promotion is added with 'PostpaidEBUSharer106446'
And Customise 'RootProduct' with Econfig Item 'Sharer1GBDataOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'OM03Rating' CDR
When Execute query 'Indata0Pond'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'OM03Rating1' CDR
When Execute query 'OutData10Pound'
Given Rate a 'OM03Rating2' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: OM17 148621_R2.4.1_49708_Billing_TS19_TC01_Validate the Order ID for bill credits on bill is displayed on invoice for Postpay SME Consumer
@Ankesh
Meta:@OMBRM17
@FolderName OM17
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim1'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex1'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
And Search The Account ''
And Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Account charge Apply
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: OMNew01 CR_82099_BRM_Order Management_TC01_Siebel to Siebel_Verify that Siebel will not allow to submit order with new billing 
         profile as customer already has one active billing profile associated to that particular billing account
@Ankesh
Meta:@OMNew01
@FolderName OMNew01
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
When Search The Account ''
And Create New Billing Profile 'NewPostpaid'
When Create new order 'NewOrderPopUp'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
When Select Billing Serviceaccount 'BillingProfileNew'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'SubmitBillingValidation'

Scenario: BRMNEW05 NewCo_CCS 9.0_CR 93160_DB Encryption_TS03_Verify ACCOUNT_NO and SORT_CODE are decrypted  in DD Refund report extracted from SBExport
@Devi
Meta:@BRMNEW05
@FolderName BRMNEW05
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
When Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'LengthValidationOfDebitNum'
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'DDAccount'
When Make Payment for '64ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'ExtraPayment64'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'refundDDDailyReport'
Then Generate Month End Report from SBExport for 'refundSummaryReport'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: OM10 1650316_NewCo_R15.2_CR62614_New_Billing_TS05_Validate that the agent is able to place a successful child sharer order with Business Sharer Child with One Net Express £17.50 promotion
@Devi
Meta:@OM10
@FolderName OM10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'VoiceAndSMSSharerChildwithOneNetExpress'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
And Show All Purchase Products in the account ''
When Execute query 'SharingGroupAccountNoCompare'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: OM01 168988_R2.2_CR26580_Billing_TS05_TC01_Ability to provide media type (SMS + Large Print)  to customers having Direct Debit as payment method
@Devi
Meta:@OM01
@FolderName OM01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'DirectDebitAccount'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When select the MediaType with 'SMSMediaType'
And select the MediaType with 'LargePrintMediaType'
And Goto Account Summary
And Create 'NewOrderNoPopUp'Order
When Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'DeliveryPreferDDCompare'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMReconnection01 NewCo CCS 5.0_CR 103729_BRM_TS09_TC09_Agent places a reconnection order in an inactive account 
          and check post new order submission
Meta:@BRMReconnection1
@FolderName BRMReconnection01
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Execute query 'Account&Billingstatus'
When Execute query 'AccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When ReLogin with 'AGENT04' in 'Env'
And Search The Account ''
When Execute query 'Accnt&Billafterinactive'
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ReconnectWithRootProd'
When Submit the Order 'SubmitForReconnectionJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Account&Billingstatus'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BI17 BI_R2.2_CR26581_Billing_TS02_TC01_Validate the Event view for prepay customer (section 7.1.1 in DA)
Meta:@BI17
@FolderName BI17
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'BI17_CDRType7' CDR
Given Rate a 'BI17_CDRType11' CDR
Given Rate a 'BI17_CDRType12' CDR
Given Rate a 'BI17_CDRType13' CDR
Given Rate a 'BI17_CDRType14' CDR
When Execute query 'PrepayRatingCount5'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Validate usage list Services 'DrillDownToMsisdn'
And Validate Usage List Summary Charges 'CaptureNetAmountUsage1value'
And Validate Usage List Summary Charges 'DrillDownNetAmountUsage'
And Validate Itemised List Selection TopUp 'SMSValidation'
And Validate Itemised List Selection TopUp 'PictureValidation'
And Validate Itemised List Selection TopUp 'DataValidation'
And Validate Itemised List Selection TopUp 'VoiceValidation'
And Validate Itemised List Selection TopUp 'TOPUp'

Scenario: 146650_R2.3_CR36463_Billing_TS_03_TC01_One_Net_Express_CTN calls the GTN for EBU small business account with One Net
@Ankesh
Meta:@OM04
@FolderName OM04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'TMTTAR' in 'Env'
When Create the account type 'SmallBusiness'
When Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'OneNetPromotion'
And Customise 'RootProduct' with Econfig Item 'VodaOneNetExpOn'
And Select Menu 'PromotionEditPackage'
And Add Product 'FixedService'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
When Execute query 'MSISDNFromAccount'
Given Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceRatingwithAccountMsisdn0' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: LCS Phase2 Sprint1_CCS6.0_EBU Sharer_Billing_Adjustment_Payment_Refund_on promotion 106403
@Ankesh
Meta:@OrderManagementBPRA
@FolderName OrderManagementBPRA
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidParentBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'BillNumberCapture'
And Submit BillLevel Adjustment 'UseRowNo1'
Given Login To Putty 'BRMPutty2' in 'Env'
When Make Payment for 'CCAccountNBilloption'
And Make Payment for 'LockBoxPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'RefundAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'CreditCardRefundValidation'
And Click On PaymentReversal 'ReversedRecurringCCPayment'
And Payment Reversal Submit
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'ReversedRecurringCCPaymentValidation'
When Execute query 'AccountBillDetail'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: AccountManagement01 NewCo_CCS8.0_CR108980_TS12_TC01_Validate that the Billing profile status is active after re-connection of service
@Ankesh
Meta:@AccountManagement01
@FolderName AccountManagement01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When ReLogin with 'AGENTFL' in 'Env'
And Search The Account ''
And Retrieve Promotion 'PostpaidPromotion4G'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
And Search The Account ''
And Retrieve Promotion 'PostpaidPromotion4G'
And Modify with owned product 'ReconnectWithRootProd'
When Submit the Order 'SubmitForReconnectionJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario:TOO within cross schema- Check charges in BRM
@Ankesh
Meta:@Adhoc02-need to run
@FolderName Adhoc02
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Check Schema2 Account
When Execute query 'BDOMValueCapture'
When Execute query 'SchemaDetailCapture'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'TOOWithPromotion'
Given Retrieve BRM Account 'PostpayConsumer'
Then Perform Transfer Of OwnerShip
When Perform Credit Vet
And Reserve Order
When Submit the Order 'SubmitForTOOJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'BillNowDetails'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNowDetails'
When Execute query 'SchemaDetailCapture'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:Transfer of service - check charges in BRM
Meta:@Adhoc03
@FolderName Adhoc03
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'RETAIL' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ServiceHierarchyChange'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Invoice with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:SBExportReport02 NewCo_CCS 9.0_CR 93160_DB Encryption_TS10_Verify BANK_ACCOUNT_NO and SORT_CODE for paying child account are encrypted in Siebel 
         and BRM DB as per AES 256 bit encryption
Meta:@SBExportReport02
@FolderName SBExportReport02
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Search The Account ''
And Create new order 'NewOrderPopUp'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'GetInfoPaymentChildEncrypted'
When Execute query 'DDSortCodeChild'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Generate Month End Invoice with Account 'ACCOUNTNO0'
When Execute query 'ChildMSISDNRetrievalCapture'
When Make Payment for 'DDAccountChild'
When Execute query 'BillingEventPayChild'

Scenario: R2.4.2_24281_Billing_TS10_TC01_Validate for agent is able to Search for suspended payments made via AMEX
@Ankesh
Meta:@BI07
@FolderName BI07
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'TokenizationAmex' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'CCSuspenseNoAmnt'
When Execute query 'RetrievpendbalanceCapture'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Make Suspense Payment 'RecurringCCPayment'
When Execute query 'suspenseAmount'
Then Suspense Payment Validation 'CCSuspenseValidationAmount'

Scenario:BISiebel01 CCS 7.0_Billing_Validate GetBalance call for customer with TBO product is sucessful for parent-paying child hierarchy
@Ankesh
Meta:@BISiebel01
@FolderName BISiebel01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra60minOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Create new order 'NewOrderPopUp'
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra60minOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'ProductDescriptionCapture'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'MSISDNFromAccount'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Product Bundle Usage Validation 'Extra60min'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'MSISDNFromAccount'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Product Bundle Usage Validation 'Extra60min'
When ReLogin with 'AGENT04' in 'Env'
And search the child account ''
When Execute query 'ChildMSISDNRetrievalSiebel'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Product Bundle Usage Validation 'Extra60min'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Show All Purchase Products in the account ''
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildMSISDNRetrievalCapture'
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundleChild'
Given Login with 'AGENT04' in 'Env'
When search the child account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'ChildMSISDNRetrievalSiebel'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Product Bundle Usage Validation 'Extra60min'


Scenario:BISiebel02 Family Retirement_TS17_Billing_TC01_When tariff migration is performed on Postpay Hub then their Vodafone Family subscription remain intact on new Postpay plan  eligible for Vodafone Family
@Ankesh
Meta:@BISiebel02
@FolderName BISiebel02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Customise 'RootProduct' with Econfig Item 'VodafoneFamilyPAYMAdd'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'ProductDescriptionFamilyPAYM'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidParentPromotion107787'
Then Perform the action 'TariffMigrationPromotion'
When Retrieve Promotion 'PostpaidPromotion1100578'
Then Perform Migration
When Submit the Order 'Normal Submit'
When Execute query 'ProductDescriptionFamilyPAYM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
When Execute query 'ProductDescriptionFamilyPAYM60min'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:PaymentRev01 DELETED - 11209 - NewCo_CCS 6.0_CR 105400_BRM_TS03_Validate that Direct debit payment and reversal details are correctly dispalyed on invoice 
        after DD Payment is failed and reversed once & applied again in same month
@Ankesh-Partial Script-(Make Payment again)
Meta:@PaymentRev01
@FolderName PaymentRev01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'DirectDebitAccount'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
When Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'LengthValidationOfDebitNum'
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Payment Refund Reversal Validation 'DDpaymntStatusSucessful'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Make Reverse Payment for 'Type1and2RC'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'DDpaymentStatusReversed'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'detailedReversedPaymentsReport'
Then Generate Month End Report from SBExport for 'reversedPaymentsReportIndex1'

Scenario:PR27 Payments and Refunds_R2.3_CR35698_TS31_TC01_Front office agent performs payment transfer flow in Siebel for full payment from customer 
         account to a another customer account successfully via suspense account
@Ankesh
Meta:@PR27
@FolderName PR27
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
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
And Execute query 'AccountBillDetail'
And Execute query 'RetrievePendBalanceCheck'
And Make Payment for 'CCAccountNBilloption'
When Execute query 'PaymentInquiryBI06'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Payment Transfer 'PaymentTransfer'
Then Make Suspense Payment 'RecurringCCPayment'
And Suspense Payment Validation 'CCSuspenseValidationPayTransf'
When Search The Account ''
When Execute query 'Capturelatestpaymnt'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'PaymentSuccessful'



Scenario:BISiebel03 NewCo CCS 5.0_CR 103729_BRM_TS03_TC03_Agent places a disconnection order and account status is changed from Suspended to Cancel and it is not
         reflected in BRM_Suspended to Cancelled_Automated_Mobile
@Ankesh
Meta:@BISiebel03
@FolderName BISiebel03
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
Then Perform the action 'SuspendwithPrmotion'
When Submit the Order 'Normal Submit'
When Execute query 'BillingProfileStatusValidation'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
Then Perform the action 'DisconnectwithPromotion'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
When Execute query 'BillingProfileStatusValidation'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for 'ABCPayment'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'

Scenario:PaymentRev02 NewCo_CCS 9.0_CR 93160_DB Encryption_TS02_Modify DD details and verify if new  BANK_ACCOUNT_NO and SORT_CODE 
           are encrypted in Siebel and BRM DB as per AES 256 bit Encryption
@Ankesh
Meta:@PaymentRev02
@FolderName PaymentRev02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
When Create 'NewOrderNoPopUp'Order
When Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'LengthValidationOfDebitNum'
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Payment Refund Reversal Validation 'DDpaymntStatusSucessful'
When Search The Account ''
Then Create Direct Debit for scenario 'MagarPatta'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'LengthValidationOfDebitNum'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDComparenew'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Payment Refund Reversal Validation 'DDpaymntStatusSucessful'

Scenario: NewCo_CCS 10.0_WR40505_Secure Net _TS02_Validate correct proration charges applied for addition 
        of Vodafone Secure Net  in Mid month
@Ankesh
Meta:@BRMRatingNew9
@FolderName BRMRatingNew9
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumer110568BRM'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
And Modify 'ModifyWithInstalledID' with Econfig Item 'VodafoneSecureNetON'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: CR89487_One Net Business_Billing_TS13_TC01_Validate that Itemized view of Usage charges retrieves unbilled 
          Usage performed with correct charges
@Ankesh
Meta:@BillingNew03-need to run
@FolderName BillingNew03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'TMTTAR' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'OneNetPromotion'
And Customise 'RootProduct' with Econfig Item 'VodaOneNetExpOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Select Menu 'PromotionEditPackage'
And Add Product 'FixedService'
Given Retrieve Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'MSISDNFromAccount'
Given Rate a 'VoiceUsage100Duration' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'NationalVoiceUsageToTariff501' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'

Scenario: R2.3_CR31931_Billing_TS08_TC03_In Account Hierarchy Validate the item & event level charges under billed usage for a Non Paying Child
@Ankesh
Meta:@Om16
@FolderName Om16
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'MSISDNFromAccount'
And Execute query 'PayingChildRetrievalCapture'
When Execute query 'GetCurrntBalChildAccNoCapture'
Given Rate a 'VoiceUsageSEA01ToUKChild' CDR
When Execute query 'ComparingtheChildRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'VoiceUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'UsageToRoamingCalls'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'ChildMSISDNRetrievalSiebel'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'UsageToRoamingCalls'

Scenario: NewCo CCS 5.0_CR 103729_BRM_TS10_TC10_Validate that acount status is set to inactive from cancelled after x days as defined
@Ankesh
Meta:@OrderManag10
@FolderName OrderManag10
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'ONAccountNon-CardPopNO'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
When Search The Account ''
Then Modify Details with 'Cancelled'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Select the TimeStamp '2d210hr10mins'
When Execute query 'Timestamp1Capture'
Given Rate a 'TIMESTAMPRating' CDR
When Execute query 'Account&Billingstatus'
When Execute query 'AccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
Given Login with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When Execute query 'Accnt&Billafterinactive'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
Then Modify Details with 'Inactive'