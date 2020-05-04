Story: BRM Rating
Meta:@kpr
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM


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
When Make Payment for 'LockBoxDDPayment'
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

Scenario:CA01 R2.3_CR37159_Billing_TS_05_TC_01_EBU_Credit_Alerting_Validate that for the EBU subscriber with a Low category CRI 
         the dialler action is triggered after 80  Threshold reached.
@Tarun
Meta:@CA01
@FolderName CA01
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
When Make Credit Alert 'Platinum1005CRIValue'
When Make Credit Alert 'BusinessRetail300Amount'
Given Rate a 'Dmtic240RGBPOutPart' CDR
When Execute query 'CreditAltCheckstatusCustDial'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CreditAltCompare'
When Execute query 'CreditAltSiebelActiveCompare'
Given Login To Putty 'SiebelPutty1' in 'Env'
When Check Alert in Siebel DB
When Execute query 'CreditAltSiebelCompleted'

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
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatAdjustment'


Scenario: REPORT16 146647_R2.2_CR31371_Billing_TS_03_TC01_Unique Usage event to be populated by BRM after rating a Voice usage
@Vaishnavi
Meta:@REPORT16
@FolderName REPORT16
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

Scenario: RatingNew01 NewCo CCS3.0_WR31451_TS01_TC01_Perform roaming voice usage from Austria to Greece (With VAT)
@Vaishnavi
Meta: @RatingNew01
@FolderName RatingNew01
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
And Show All Purchase Products in the account ''
And Rate a 'GRCCO307865768454' CDR
When Execute query 'GRCCO307865768454'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: RatingNew12 NewCo_CCS 5.0_CR 104082_BRM_TS07_TC01_Validate that incoming voice calls in Europe Zone are correctly presented as Free on the invoice
@Vaishnavi
Meta:@RatingNew12
@FolderName RatingNew12
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'USAHT14702666018' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty5' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
And Download the Generated Report file to local system
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingNew13 - NewCo_CCS 3.0_CR101796_Billing_TS05_TC01_Verify that UoM changes for the mentioned UoMs are correctly displayed in the Itemised Usage view for Billed Usage for Postpay CBU Subscriber
@Vaishnavi
Meta:@RatingNew13
@FolderName RatingNew13
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
Given Generate First Bill
Given Rate a 'CDRType4' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType2' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType1' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType3' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToSMSandMMS'
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
When Event Level Adjustment selection 'UKVoiceValidation'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:BI01 R2.2_CR26581_Billing_TS03_TC01_Validate Top-up history view (section 7.1.1 in DA) for prepay customer 
@Tarun/Pankaj
Meta:@BI01
@FolderName BI01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'PrepaidConsumer'
And Create 'NewOrderNoPopUp' 'NewPrepaid' Order
And Promotion is added with 'PrepaidPackage'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Make payment with 'CashNon-CardPopNO'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'TopUpCredit' CDR
And Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then TopUpHistoryValidation 'TopUpCredit'

Scenario: BRMRatingNew2 CCS 7.0_CR 108215_TS04_Billing_TC01_Validate that Youth CDR (Renewal) processed by 
		  MZ is rated successfully in BRM
@Kiran
Meta:@BRMRatingNew2
@FolderName BRMRatingNew2
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'YouthCDR_Renewal_Rating' CDR
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Validate usage list Services 'DrillDownToMsisdn'
And Validate Usage List Summary Charges 'DrillDownNetAmountUsage'
And Validate Itemised List Selection TopUp 'ExpiredBundle'