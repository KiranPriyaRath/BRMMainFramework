Story:My Story
Meta:@ReportingBulk
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative: Story is pending for testing

Scenario: ReportingNew01 CR_87344_BRM_Account charges_TC10_Siebel to BRM_Validate that Refund DD daily detailed report is successfully generated
@Vivek
Meta:@ReportingNew01
@FolderName ReportingNew01
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
And Make Payment for 'ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'RefundAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'CreditCardRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
Then Generate Month End Report from SBExport for 'refundDDDailyReport'

Scenario: REPORT1 R2.4.2_53937_Billing_TS04_TC01_Validation of Addition of the business profile field in Subscriber Adjustment EDW file for EBU Trader Postpaid Customer
@Suveni
Meta:@REPORT1
@FolderName REPORT1
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
When Execute query 'BillNumberCapture'
And ReLogin with 'ADJUSTADJ' in 'Env'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'postpaidAdjustments'

Scenario: REPORT4 R2.1_CR22832_Billing_TS09_TC01_Validate that the RCIT report is  produced for VF Postpay customer invoice
@Zeba
Meta:@REPORT4
@FolderName REPORT4
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
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'



Scenario: REPORT7 CR19334_Billing_TS01_TC01_Finance Reporting_Validation of postpaid VAT report produced for VF postpaid
@Zeba
Meta:@REPORT7
@FolderName REPORT7
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
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatReport'


Scenario: REPORT9 R2.1_Billing_TS05_TC01_Finance Reporting_Validation of postpaid Recurring Charges & OT report produced for VF postpaid
@Zeba
Meta:@REPORT9
@FolderName REPORT9
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
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'recurringChargesOT'


Scenario: REPORT39 R2.1_Billing_TS02_TC01_Validate that All airtime activities (including monthly recurring charges, usage, and bundles) for post-pay customers are posted into the general ledger at month end_Out Bundle
@Zeba
Meta:@REPORT39
@FolderName REPORT39-Gl Report
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
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate GLReport for 'PostpayReport'
And Download the Generated Report file to local system

Scenario:REPORT17 146645_R2.1_CR2020_Billing_Finance Reporting_TS04_TC01_Validation of postpaid Usage Detail report produced for VF postpaid
@Vivek
Meta:@REPORT17
@FolderName REPORT17

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
And Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'usageDetail'

Scenario: REPORT40 R2.4.2_46794_Billing_TS07_TC01_Validate In bound and Out of Bound Finance report  for CBU
@Zeba
Meta:@REPORT40
@FolderName REPORT40
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
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'OM03Rating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'NationalDataUsage' CDR
When Execute query 'ComparingtheRatingValue'
Then Generate GLReport for 'PostpayReport'

Scenario: REPORT8 R2.4.1_54266_Billing_TS10_TC01_Validate the correct generation of Monthly billed fee report
@Zeba
Meta:@REPORT8
@FolderName REPORT8
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'dailyBilled'


Scenario: REPORT5 R2.1_CR22832_Billing_TS03_TC01_Validate that the RCIT files are produced for Subscriber Usage Charges for MMS Usage for VF postpay subscibers
@Vivek
Meta:@REPORT5
@FolderName REPORT5

Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
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
And Rate a 'SMSUsagesReport5' CDR
When Execute query 'ComparingtheRatingValue'
Then Download the EDW Usage file to local system

Scenario: REPORT41 R2.1_Billing_TS01_TC01_ Validate that the Billing system (BRM)produces reports for payments made to suspense account for finanace Reconcillation
@Ankesh
Meta:@REPORT41
@FolderName REPORT41
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
When Make Payment for 'WrongPayment'
Then Generate Month End Report from SBExport for 'suspenseAccountPaymentsReport'

Scenario: REPORT3 R2.1_CR2021_Billing_TS010_TC01_Validate that the RCIT report is  produced for Billing profiles Created or closed on the current date for VF postpay Customers
@Ankesh
Meta:@REPORT3
@FolderName REPORT3
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107789'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Then Generate Month End Report from SBExport for 'postpaidBillingProfileFeed'

Scenario: REPORT35 R2.1_CR22832_Billing_TS01_TC01_BRM sends TPIT extract of subscriber base and related settings which can be verified by TPIT across systems
@Ankesh
Meta:@REPORT35
@FolderName REPORT35
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Then Generate Month End Report from SBExport for 'TPITExport'

Scenario:REPORT6 R2.1_CR22832_Billing_TS01_TC01_Validate that the RCIT files are produced for Subscriber Usage Charges 
         for Voice Usage for VF postpaid subscibers 
@Ankesh
Meta:@REPORT6
@FolderName REPORT6
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceRating' CDR
Then Download the EDW Usage file to local system

Scenario: REPORT26 R2.4.2_53937_Billing_TS08_TC01_Validation of Addition of the business profile field in Subscriber Charges RAID file for EBU Trader Postpaid Customer
@Ankesh
Meta:@REPORT26
@FolderName REPORT26
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
Given Generate First Bill
Then Generate Month End Report from SBExport for 'postpaidServiceCharges'


Scenario: REPORT11 R2.4.2_46794_Billing_TS11_TC01_ Validate EDW  subscriber usages file for EBU customer
@Ankesh
Meta:@REPORT11
@FolderName REPORT11
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion109161'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'PremiumNGNRating' CDR
When Execute query '641Rating'
Then Download the EDW Usage file to local system
Given Rate a 'IndUKMobileCall' CDR
When Execute query 'RoamingvoiceINDUK'
Then Download the EDW Usage file to local system



Scenario:REPORT18 CR37158_Billing_TS_05_TC01_EBU_Finance_Management_Validate that the VAT Adjustment Report for EBU sole trader is generated correctly
@Ankesh
Meta:@REPORT18
@FolderName REPORT18
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
Given Generate First Bill
And Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
When ReLogin with 'ADJUSTADJ' in 'Env'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'BillNumberCapture'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
Then Generate Month End Report from SBExport for 'vatAdjustment'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatAdjustment'


Scenario: REPORT16 146647_R2.2_CR31371_Billing_TS_03_TC01_Unique Usage event to be populated by BRM after rating a Voice usage
@Vaishnavi
Meta:@REPORT16
@FolderName REPORT16
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'Tariffclass447387912299' CDR
When Execute query '501inBundle'
Then Download the EDW Usage file to local system
Given Login with 'ADJUSTADJ' in 'Env'
When Execute query 'BillNumberCapture'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: REPORT37 Finance_Test_Postpay_ME2_UC_R2.1_2020.a_19334_TS02_TC01_Finance Reporting_Validation of  Daily billed recurring charges report produced for VF postpaid_old
@Vaishnavi
Meta:@REPORT37
@FolderName REPORT37
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceRating' CDR
And Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'dailyBilled'
Then Generate Month End Report from SBExport for 'recurringChargesOTIndex1'

Scenario:REPORT30 R2.4.2_44134_Billing_TS08_TC01_Validate EDW subscriber usage report contain voice and sms Usages for unlimited
@Ankesh
Meta:@REPORT30
@FolderName REPORT30
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'DomesticRatingSMS' CDR
When Execute query 'Data600700with1Duration'
Then Download the EDW Usage file to local system
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:REPORT34 CR37158_Billing_TS_04_TC01_EBU_Finance_Management_Validate that the VAT Report for EBU small business is generated correctly
@Ankesh
Meta:@REPORT34
@FolderName REPORT34
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Then Generate Month End Report from SBExport for 'vatReport'


Scenario:REPORT24 CR2020_Billing_Finance Reporting_TS03_TC01_Validation of postpaid VAT Adjustment report produced for VF postpaid
@Ankesh
Meta:@REPORT24
@FolderName REPORT24
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'Voice535RatingNoIndex' CDR
When ReLogin with 'ADJUSTADJ' in 'Env'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickYes'
When Submit Adjustment 'RestrictedFriends&Family'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatAdjustment'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:REPORT2 R2.4.1_CR49708_Billing_TS23_TC01_Validate the EDW XML of all adjustment types for Postpay Consumer
@Ankesh
Meta:@REPORT2
@FolderName REPORT2
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G109161'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
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
When Verifying Adjustment Done on the Account
Then Generate Month End Report from SBExport for 'postpaidAdjustments'

Scenario:REPORT23 CR22832_Billing_TS06_TC01_Validate that the RCIT files are produced for Subscriber Level Service Charges for 
          VF postpay subscibers
@Ankesh
Meta:@REPORT23
@FolderName REPORT23
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim1'
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex1'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Show All Purchase Products in the account ''
And Generate First Bill
Then Generate Month End Report from SBExport for 'postpaidServiceCharges'

Scenario: REPORT32 R2.4.2_44134_Billing_TS09_TC01_Validate RAID report for unlimited voice and sms usages by new enterprise customer
@Devi
Meta:@REPORT32
@FolderName REPORT32
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
And Show All Purchase Products in the account ''
And Rate a 'Report24RatingSMS' CDR
When Execute query 'CaptureBundleDetails'
Then Download the EDW Usage file to local system
Given Rate a 'VoiceReport24Rating' CDR
When Execute query 'CaptureBundleDetails'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario:REPORT36 UC_R2.1_2020.a_19334_TS01_TC01_Finance Reporting_Validation of postpaid VAT report produced for VF postpaid
@Ankesh
Meta:@REPORT36
@FolderName REPORT36
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatReport'

Scenario: R2.0_E2EBRM_Billing_TS01_TC03_BRM output file of EDW for Voice CDR_CDR Through Surepay for Voice Calls
@Ankesh
Meta:@REPORT38
@FolderName REPORT38
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceRating0RateGBP' CDR
When Execute query 'PrepayRatingCount1'
Then Download the EDW Usage file to local system


Scenario: REPORT45 NewCo CCS 2.0 __BRM_Defect 71220_INC 2318941_Time Zone field missing in EDW file
@Ankesh
Meta:@REPORT45
@FolderName REPORT45
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceDomestic10RGBP' CDR
When Execute query 'PrepayRatingCount1'
Then Download the EDW Usage file to local system

Scenario: ReportingNew06 NewCo CCS 14.1_PBI111121_Defect No 20030_RCOT report needs to include additional columns to enable accurate Mobile and Fixed reporting
@Kiran
Meta:@ReportingNew06
@FolderName ReportingNew06
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'recurringChargesOT'


Scenario: ReportingNew04 Billing_Invoicing _ new itemised Billing Feed SB Export Report  for Postpaid_SME_Summary invoice Type_one service with no delayed usage
@Kiran
Meta:@ReportingNew04
@FolderName ReportingNew04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
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
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Given Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNoIndex1'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerateMode1'