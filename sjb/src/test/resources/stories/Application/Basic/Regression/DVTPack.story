Story: BRM Payment And Refund
Meta:@DVTPack
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

 
Scenario: RatingNew14 - NewCo_CCS 3.0_CR101796_Billing_TS05_TC01_Verify that UoM changes for the mentioned UoMs are correctly displayed in the Itemised Usage view for Billed Usage for Postpay CBU Subscriber
@Vaishnavi
Meta:@RatingNew14
@FolderName RatingNew14
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'CDRType4CountryFRAF1RateGBP0' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType2_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType5_447432134567' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToRoamingSMSandMMS'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToDataUsageClickNo'
When Event Level Adjustment selection 'UsageToMobileInternet'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToOtherUsageClickNo'
When Event Level Adjustment selection 'UsageToOthers'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToVoiceUsageClickNo'
When Event Level Adjustment selection 'UsageToRoamingCalls'

Scenario: BRMRatingNew25 NewCo_CCS 10.0_CR 111120_TS25_Validate billing and invoicing of VOICE , DATA , MMS , 
		  SMS rating for Postpay Consumer customer corresponding to ITEL , NTEL , NSMS , ISMS , NDATA , RDATA 
		  usages
@Vaishnavi
Meta:@BRMRatingNew25
@FolderName BRMRatingNew25
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'InternationalVoiceUsageToTariff535'
Then Download the EDW Usage file to local system
Given Rate a 'NationalVoiceUsageToTariff501' CDR
When Execute query '501inBundle'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalSMSUsage' CDR
When Execute query 'InternationalSMSUsage'
Then Download the EDW Usage file to local system
Given Rate a 'NationalSMSUsage1' CDR
When Execute query '501inBundle'
Then Download the EDW Usage file to local system
Given Rate a 'NationalDataUsage1' CDR
When Execute query 'NationalDataUsage'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingDataUsage1' CDR
When Execute query 'RoamingDataUsage'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BRMRatingNew11 CCS 7.0_CR 109447_TS12_Billing_TC01_Validate charges for prepay customer for roaming 
		  MO voice usage from EuropeZone2(Bosnia and Herzegovina),SMS from EuropeZone1(Latvia) to UK
@Vaishnavi
Meta:@BRMRatingNew11
@FolderName BRMRatingNew11
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'BosniaToHerzegovinaRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'LatviaToUKSMSUsageRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdnPrepay'
And Unbilled Type of Usage Adjustment selection 'DrillDownToPrepayUsage'
When Event Level Adjustment selection 'SMSUsagePrepay'
When Event Level Adjustment selection 'VoiceUsagePrepay'

Scenario: ADJ014 R2.2_CR46839_Billing_Invoice to show correct VAT for all level of adjustments 
@Tarun
Meta:@ADJ014
@FolderName ADJ014
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'Report2IndiaNumber' CDR
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'SMSUsageBillLevel'
And Event Level Adjustment selection 'SMSUsageAmountCapture'
When Submit Adjustment 'RestrictedFriends&Family'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ItemLevelSMSAdjustment'
When Submit Adjustment 'GoodWillItemLevel'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

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

Scenario: BINew03
@Vaishnavi
Meta:@BINew03
@FolderName BINew03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
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
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'MSISDNFromAccount'
When Execute query 'GetBalanceQueryMinutes'
When Bundle Usage Validation 'DrillDownToMSISDN1'
When Minute And SMS Bundle Usage Validation 'AvailableBalanceMinutes'
When Detail Bundle Usage Validation 'CompareAvailableBalance'
When Execute query 'GetBalanceQuerytext'
When Minute And SMS Bundle Usage Validation 'OriginalBalanceSMS'
When Detail Bundle Usage Validation 'CompareNamewithSMS'
When Product Bundle Usage Validation 'StatusAllActive'
When Execute query 'GetBalanceQueryMinutes'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'AvailableBalanceMinutes'
When Detail Bundle Usage Validation 'CompareAvailableBalance'
When Execute query 'GetBalanceQuerytext'
When Minute And SMS Bundle Usage Validation 'OriginalBalanceSMS'
When Detail Bundle Usage Validation 'CompareNamewithSMS'
When Product Bundle Usage Validation 'StatusAllActive'

Scenario: BILLPREF01 NewCo_CCS 2.0_CR99884_Billing_TS03_TC01_Verify in Invoice List Applet, columns and respective values are displayed correctly
@Ankesh
Meta:@BILLPREF01
@FolderName BILLPREF01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And DrillDown to Latest Bill 'CaptureTotalBill'

Scenario:CA03 R2.3_CR35736_Billing_TS_05_TC_01_Credit_Alerting_Extension_Validate that for the High and medium
category CRI subscriber,  Credit Alerting Network Bar will be activated when Credit reached 100 Threshold.
@Tarun
Meta:@CA03
@FolderName CA03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
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
When Make Credit Alert 'SBRONZE'
When Make Credit Alert 'NEWPROD'
Given Rate a 'CA03' CDR
When Execute query 'CA03'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CA0301'
When Execute query 'CA0302'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion107785'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'CA03'
Then Check Order Status
When Execute query 'CA0303'

Scenario: REPORT9 R2.1_Billing_TS05_TC01_Finance Reporting_Validation of postpaid Recurring Charges & OT report produced for VF postpaid
@Zeba
Meta:@REPORT9
@FolderName REPORT9
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'recurringChargesOT'

Scenario: NewCo CCS 11.0_PBI104659_Defect 10934_Docgen Execution Start and End time population in CSV log file
@Kiran
Meta:@BJNew04
@FolderName BJNew04
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
Then Docgen logfile details
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
Given Login to WCC with 'LoginToWCC' in 'Env'
And Search Invoice in WCC

Scenario: CCS 10.0_WR41727_TS01_TC01_Perform BulkAdjustment with New Reason Code VAT Adjustment on EBU Customer
@Ankesh
Meta:@BulkADJ01
@FolderName BulkADJ01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Perform Bulk Adjustment 'BulkAdjPYTaxcode'
When Execute query 'ProgramNameAdjValidation'
Then Generate Month End Report from SBExport for 'postpaidAdjustments'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'