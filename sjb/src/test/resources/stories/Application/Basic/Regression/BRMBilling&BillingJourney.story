Story:My Story
Meta:
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative: Story is pending for testing


Scenario:BillingNew06 NewCo_CCS9.0_112233_BRM_TS01_Validate Bill level adjustment with Adjust Full Amount option from Adjustment tab in Billing Profile view
@Payel
Meta:@BillingNew06
@FolderName BillingNew06
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Submit BillLevel Adjustment 'ExitFeeAdjustmentFullBill'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BillingNew17 NewCo_R15.2_CR49706_New_Billing_TS02_TC01_Validate that SBExport job run successfully for EBU Small Business Customer Invoice
@Devi
Meta:@BillingNew17
@FolderName BillingNew17
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
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
Then Generate Month End Report from SBExport for 'InvoiceExpAnalytic'


Scenario: BILLING08 163996_R2.4.1_49261_Billing_TS08_TC01_Validate correct proration charges following 
		  mid month movement of service for a non paying child to a paying child for SME customer
@Vaishnavi
Meta:@BILLING08-need to develop- DD= PostpaidSME107785NotSysDateNonPayChildBRM
@FolderName BILLING08
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
And Retrieve BRM Account 'PostpaidSME107785NotSysDateNonPayChildBRM'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
And Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ModifyUsingUserAccount'
And Reserve Order
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Submit the Order 'NormalSubmitforSubAccount'
And ReLogin with 'RETAIL' in 'Env'
And Search The Account ''
And Modify with owned product 'ActiveRootProduct'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'UKNGN134'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Bill with Account 'ACCOUNTNO'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: BJ03 NewCo_CCS 2.0_CR100907_Billing_TS09_BRM_To Verify Bill Now is not performed when 
		  ownership of a service is transferred to an account which already has 1 active service 
		  for SMB account
@Zeba
Meta:@BJ03
@FolderName BJ03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion4G'
Then Perform the action 'TOOWithPromotion'
Given Retrieve Account 'PostpaidConsumer'
Then Perform Transfer Of OwnerShip
When Perform Credit Vet
And Reserve Order
And Submit the Order 'SubmitForTOOJourney'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: R15.2_CR75380_Billing_NGN_TS05_TC01_Validate that Customer is charged at PPC+PPM rates when calling 907375XX series numbers_Start of Call
@Ankesh
Meta:@BillingNew15
@FolderName BillingNew15
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
And Make Payment for 'CCpartialPaymnt'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: BillingNew23 Rio Phase1A_Core Tariff Refresh_GetBalance_TS13_TC18_GetBalance_Validate that the Balance is correctly dispayed 
         for Voice usage on Add On Bundle 
@Ankesh
Meta:@BillingNew23
@FolderName BillingNew23
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra300minsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Detail Bundle Usage Validation 'CompareAvailableBalanceforAddOn'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'NormalRoamingVoiceData' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Detail Bundle Usage Validation 'CompareAvailableBalanceforAddOn'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Event Level Adjustment selection 'UsageToRoamingCalls'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BillingNew24 Rio Phase1A_Core Tariff Refresh_Rating and Charging_BRM_TS05_TC03_Validate that agent is able to perform Rating and Charging for Data, Voice, SMS and MMS usage when a EBU Rio customer is roaming 
         in countries other than EU on promotion 110108 
@Ankesh
Meta:@BillingNew24
@FolderName BillingNew24
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Rate a 'VoiceRatingUKToIndia' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a '1RoamingSMSQuantity' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'MMSRoamingBrazil' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingBrazil' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: CCS 6.0_WR36454_TS01_TC01_Agent perform modify journey to add Vodafone Family PAYM(110136) to 105099 promotion and perform usage to vodafone mobile.
@Vishwa22
Meta:@BillingNew01
@FolderName BillingNew01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumer107785BRM'
Given Login with 'AGENTVIP' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify 'ModifyWithInstalledID' with Econfig Item 'VodafoneFamilyPostpayOn'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'ProductDesctbeforeRateVF'
Given Rate a 'NormalVoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query 'ProductDescriptionVF'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BillingNew21 NewCo_CCS9.0_112233_BRM_TS10_Validate Item level adjustment with Adjust Full Amount option on Billed Non Usage item from Bills view
@Tarun
Meta:@BillingNew21
@FolderName BillingNew21
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'DecimalDataRating' CDR
When Execute query 'DecimalDataRating'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'DataUsageAll'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickDataUsageAdjButton'
And Submit Adjustment 'GoodWillBillLevelFullBillBillingNew21'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BillingNew22 NewCo_CCS9.0_112233_BRM_TS18_Validation of  Adj_Detail report produced for VF postpaid
@Ankesh
Meta:@BillingNew22
@FolderName BillingNew22
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'ADJ012Rating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'ADJ012Rating1' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'SMSUsageBillLevel'
When Event Level Adjustment selection 'InternationalSMSIndia'
And Submit Adjustment 'RestrictedFriends&Family'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
When Execute query 'TaxCode'
Then Generate Month End Report from SBExport for 'DetailAdjustmentReport'

Scenario:BillingNew08 NewCo_CCS9.0_112233_BRM_TS06_Validate Bill level adjustment with Adjust Full Amount option from Adjustment tab 
        in Billing Profile view for Hierarchical account with Parent and non paying child
@Ankesh
Meta:@BillingNew08
@FolderName BillingNew08
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
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
When Execute query 'PayingChildRetrievalCapture'
And Execute query 'ChildMSISDNRetrieval'
When Execute query 'GetCurrntBalChildAccNoCapture'
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'InternationalVoiceUsageToTariff535child'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNRetrieval'
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'InternationalVoiceUsageToTariff535Amount6'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And Submit BillLevel Adjustment 'GoodWillBillLevelFullBill'
Given Login To Putty 'BRMPutty3' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BillingNew20 NewCo_CCS9.0_112233_BRM_TS08_Validate Bill level adjustment on the remaining due amount when the total bill amount is Partial paid
@Tarun
Meta:@BillingNew20
@FolderName BillingNew20
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'AccountBillDetail'
When Make Payment for 'LockBoxCardPayment'
When Execute query 'PaymentReceivedBilling20'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'BillNumberCapture'
And Submit BillLevel Adjustment 'GoodWillBillLevelFullBillBilling20'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BILLING04 168993_R2.2_CR27375_Billing_TS_03_TC01_Validate that Bill and invoice will be generated on the day customer takes a service
@Pankaj
Meta:@BILLING04
@FolderName BILLING04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Then verify the Order line Items 'AddPromotion'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BILLING03 216795_CR97309_BRM_Bill Now_TS07_BRM_To validate if bill now utility is successfully executed if a customer has multiple services active in single order
@Pankaj
Meta: @BILLING03
@FolderName BILLING03--Need to change in Excel and DB
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim1'
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex1'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Perform the CustomerAccount
Then Verify the Product and Services for 'AllActiveRootProductCollapse'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BILLING01 CR97309_BRM_Bill Now_TS01_BRM_To validate that Bill Now utility is not executed after adding new service mid month to account with services active on it
@Ankesh
Meta: @BILLING01
@FolderName BILLING01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve Account 'PostpayConsumerNonSysDateBRM'
When Execute query 'BDOMValueCapture'
And Execute query 'VfBillNowCheck'
And Execute query 'VfPurchaseHistory'
And Execute query 'BillNowDetails'
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
And Generate First Bill
When Execute query 'CompareBILLNOWCHECK0'
When Execute query 'CompareVfPurchaseHistory'
When Execute query 'BillNowDetails'

Scenario: BILLING06 CR97309_BRM_Bill Now_TS06_BRM_To validate that Bill Now utility is  not executed on the  account with active services after performing transfer of ownership.
@Ankesh
Meta:@BILLING06
@FolderName BILLING06
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'BDOMValueCapture'
And Execute query 'VfBillNowCheck'
And Execute query 'VfPurchaseHistory'
And Execute query 'BillNowDetails'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'TOOWithPromotion'
Given Retrieve Account 'PostpaidConsumer'
Then Perform Transfer Of OwnerShip
When Perform Credit Vet
And Reserve Order
And Submit the Order 'SubmitForTOOJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'VfPurchaseHistory'
And Execute query 'BillNowDetails'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'
When Execute query 'BillNowValue1Compare'
Given Login To Putty 'BRMPutty3' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:BILLING09 CR97309_BRM_Bill Now_TS01_BRM_To validate that Bill Now utility is not executed after adding new service mid month to account with services active on it
@Ankesh
Meta: @BILLING09
@FolderName BILLING09
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107789'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Goto Sub Account
And Click New Billing Account
And Create only account type 'BillingAccountRR'
And Create New SubAccount Billing Profile 'NewPostpaid'
And Click New Service Account
And Create only account type 'ServiceAccount'
And Goto Account Summary
And Create new order 'NewPostpaid'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'BlankWhiteSimFormatSIMAdd'
And Select Billing Serviceaccount 'BillingAccount'
And Select Billing Serviceaccount 'ServiceAccount'
And Perform Credit Vet
And Submit the Order 'SubmitForLineItemsValidationJourney'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'PayingChildRetrievalCapture'
And Execute query 'ChildMSISDNRetrievalCapture'
Given Rate a 'VoiceRatingwithnoIndexing' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
And Generate Month End Invoice with Account 'ACCOUNTNO0'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Rate a 'VoiceRatingwithnoIndexing' CDR
When Execute query '501inBundle'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BILLING10 R2.3_CR37160_Billing_TS_10_TC01_EBU_Invoice_Billing and Invoicing validation for 
		  an EBU customer adding  a paying child accountes
@Ankesh
Meta:@BILLING10
@FolderName BILLING10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
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
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Invoice with Account 'ACCOUNTNO0'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BRMBillingNew01 CCS 6.0_WR38407_TS01_TC01_Agent perform roaming postpaid voice,sms,mms,data usage with SEA01, BMU01, SEAMC country code and validate the invoice BINew03
@Vaishnavi
Meta:@BRMBillingNew01
@FolderName BRMBillingNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceUsageSEA01ToUK' CDR
When Execute query 'VoiceUsageSEA01ToUK'
Given Rate a 'SMSUsageSEA01ToUK' CDR
When Execute query 'SMSUsageSEA01ToUK'
Given Rate a 'MMSUsageSEA01ToUK' CDR
When Execute query 'ConsumerMMS'
Given Rate a 'DataUsageSEA01ToUK' CDR
When Execute query 'DataUsageSEA01ToUK'
Given Rate a 'VoiceUsageBMU01ToUK' CDR
When Execute query 'VoiceUsageBMU01ToUK'
Given Rate a 'SMSUsageBMU01ToUK' CDR
When Execute query 'SMSUsageBMU01ToUK'
Given Rate a 'MMSUsageBMU01ToUK' CDR
When Execute query 'MMSUsageBMU01ToUK'
Given Rate a 'DataUsageBMU01ToUK' CDR
When Execute query 'DataUsageBMU01ToUK'
Given Rate a 'VoiceUsageSEAMCToUK' CDR
When Execute query 'VoiceUsageSEAMCToUK'
Given Rate a 'SMSUsageSEAMCToUK' CDR
When Execute query 'SMSUsageSEAMCToUK'
Given Rate a 'MMSUsageSEAMCToUK' CDR
When Execute query 'MMSUsageSEAMCToUK'
Given Rate a 'DataUsageSEAMCToUK' CDR
When Execute query 'DataUsageSEAMCToUK'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToSMSUsage'
And Validate Itemised List Selection TopUp 'UsageToRoamingSMSandMMS'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'UsageToRoamingCalls'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToDataUsageClickNo'
And Validate Itemised List Selection TopUp 'UsageToMobileInternet'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToOtherUsageClickNo'
And Validate Itemised List Selection TopUp 'UsageToOthers'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BILLING05 2167722_CR97309_BRM_Bill Now_TS04_BRM_To validate that Bill Now bill is successfully 
		  generated for account with service present on only non paying child.
@Vaishnavi
Meta:@BILLING05
@FolderName BILLING05
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create New Billing Profile 'NewPostpaid'
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
And create new child account 'SelectAddressforChildAccount'
And Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Submit the Order 'Normal Submit'
And Search The Account ''
And Verify the Child accout under sub_Account 'ChildAccountStatus'
And Retrieve Promotion 'PostpaidPromotion107785'
Then Validate the owned product and services 'ActivePromotion'
And Validate the owned product and services 'ActiveRootProduct'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'BillNowDetails'
And Execute query 'BillNowWithAccountNo'
And Execute query 'VfBillNowCheck'
And Execute query 'BillNowDetails'
And Execute query 'VfPurchaseHistory'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BJ01 NewCo_CCS 2.0_CR100907_Billing_TS01_BRM_To Verify Bill now is performed for a new service activated on a new Sole Trader account
@Pankaj
Meta: @BJ01
@FolderName BJ01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'SoleTraderAccount'
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
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BJ02 NewCo_CCS 2.0_CR100907_Billing_TS05_BRM_To Verify Bill Now is performed when multiple services are 
          activated in one single order on SME Account 
@Ankesh
Meta:@BJ02
@FolderName BJ02
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim1'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex1'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'BillNowCompare'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:NewCo_INC_PBI000000102920_Calls made in the Netherlands antillies to Netherlands Antillies ctns not covered by Global roaming
@Vivek
Meta:@BJNew01
@FolderName BJNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'NeitherLandRoamingCalls' CDR
When Execute query 'NeitherLandRoamingCall'
And ReLogin with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Minute And SMS Bundle Usage Validation 'AvailableBalanceMinutes'
And Detail Bundle Usage Validation 'CompareAvailableBalance'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Event Level Adjustment selection 'UsageToRoamingCalls'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BillingNew14 NewCo_CCS 2.0_CR100907_Billing_TS10_BRM_To Verify bill now is not performed on a new service is 
          activated on a Cancelled SME account
Meta:@BillingNew14
@FolderName BillingNew14
@Ankesh
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
When Execute query 'BILLNOWCHECKcapture'
When ReLogin with 'AGENT04' in 'Env'
And Search The Account ''
And Retrieve Promotion 'PostpaidPromotion4G'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Execute query 'VfBillNowCheckCompare'
When Execute query 'VfPurchaseHistoryCompare'
And Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'VfBillNowCheckCompare'
When Execute query 'VfPurchaseHistoryCompare'
Given Generate First Bill

Scenario:BillingNew30 NewCo_CCS9.0_112233_BRM_TS05_Validation Error message should popup when Agent tries to adjust amount greater than the Original Bill Amount
@Ankesh
Meta:@BillingNew30
@FolderName BillingNew30
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'SMSUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'MMSUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'MaxamountvalidationAdj'

Scenario:Billing20 Order Management_TC04_Siebel to Brm_Verify that new default account level balance group is created when agent place an 
         order for change of Billing account from one paying child to another paying child.
Meta:@Billing20 - check both poid id - child 1 and 2
@FolderName Billing20
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
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
When Execute query 'GetBalanceGrpAccntMsisdnCapt'
When Execute query 'ChildMSISDNRetrievalSiebel'
When Execute query 'GetBalanceGroupAccMsisdn0Capt'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
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
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildMSISDNRetrieval2ndchild'
When Execute query 'GetBalanceGroupAccMsisdn0Capt'
Given Generate Month End Bill with Account 'AccountNo1Noindx'
Given Generate Month End Bill with Account 'ACCOUNTNO0Indx1'
Given Generate Month End Bill with Account 'ACCOUNTNO1Indx2'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario:NewCo_CCS 6.0_CR 107339_BRM_TS11_In existing Account Hireachy(with paying and Non paying) ,
         Create another service account with payer as paying child for SME.
Meta:@BillingNew13
@FolderName BillingNew13
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
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
And create new child account 'Rowno4'
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
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'PayingChildcapture'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
And Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'Rowno4'
And create new child account 'SelectAddressforChildAccount'
And Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Select Billing Serviceaccount 'PayingChildAcc'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'ACCOUNTNO2'
Given Generate Month End Bill with Account 'ACCOUNTN1'
Given Generate Month End Invoice with Account 'ACCOUNTNO1'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


