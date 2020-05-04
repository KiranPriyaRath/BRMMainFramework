Story: Adjustment BRM
Meta:@AdjustmentBulk
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: ADJ001 163977_R2.2_CR46839_Billing_TS01_TC01_Invoice to show correct VAT for adjustments  The adjustment is created against the open bill at the event level
@Vaishnavi
Meta:@ADJ001
@FolderName ADJ001
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'DomesticDataRating' CDR
When Execute query 'OutData10Pound'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DataUsageDrillNetAmount'
And Event Level Adjustment selection 'NetAmount10'
And Submit Adjustment 'GoodwillAdjust200017EventDetail'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
And Execute query 'Adjustment1EuroAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: ADJ002 R2.4.1_47912_Oracle_TS01_TC01_CSA makes an unbilled adjustment at event level for a Consumer customer.  Validate correct Tax code is associated with adjustment and adjustment appears correctly on invoice 
@Vaishnavi
Meta:@ADJ002
@FolderName ADJ002
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'DomesticDataRating' CDR
When Execute query 'OutData10Pound'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickYes'
When Submit Adjustment 'GoodWillEventCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
And Execute query 'CompareofP3TaxCode'
And Execute query 'Adjustment1EuroAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: ADJ003 R2.4.1_47912_Oracle_TS04_TC01_CSA makes an open billed djustment at bill level for a 
		  consumer customer.  Validate correct Tax code is associated with adjustment and adjustment 
		  appears correctly on invoice 
@Vaishnavi
Meta:@ADJ003
@FolderName ADJ003
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
And Execute query 'CompareofP3TaxCode'
And Execute query 'Adjustment1EuroAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:ADJ012 - R2.4.2_61166_Billing_TS01_TC01_Validation of billing adjustment at event level for adjustment with 20percent of VAT with tax code Low Risk for EBU Trader customer
@Tarun
Meta:@ADJ012
@FolderName ADJ012
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
And Rate a 'ADJ012Rating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'ADJ012Rating1' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'SMSUsageBillLevel'
And Event Level Adjustment selection 'ADJ012Adjustment'
When Submit Adjustment 'RestrictedFriends&Family'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
When Execute query 'TaxCode'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: ADJ006 R2.2_CR46839_Billing_TS05_TC01_Usage event is correctly populated after  the adjustment 
		  is created on unbilled usage at event level
@Vaishnavi
Meta:@ADJ006
@FolderName ADJ006
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'DomesticRateGBP10and0Partial' CDR
When Execute query 'CaptureRateofDataUsage'
Given Rate a 'Domestic447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'IND01917507416993' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickYes'
When Submit Adjustment 'RestrictedFriends&Family'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickYes'
When Submit Adjustment 'RoamingUsageAdjustment'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickYes'
When Submit Adjustment 'ExitFeeAdjustment'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: ADJ008 R2.4.2_61166_Billing_TS04_TC01_Validate billing adjustment at usage-item level for adjustment
		  with 20percent VAT with tax code Low Risk for an closed bill for EBU Business Customer
@Vaishnavi
Meta:@ADJ008
@FolderName ADJ008
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
And Rate a 'Report2IndiaNumber' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DrillDownToSMSUsageClickYes'
And Submit Adjustment 'ItemChargeGoodwillAdjustment'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: ADJ011 R2.4.1_CR49708_Billing_TS06_TC01_Validate that adjustment creation dates are displayed 
		  on the invoice for Item level adjustments on open bill for a SME Customer with a CC profile 
@Vaishnavi
Meta:@ADJ011
@FolderName ADJ011
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
When Execute query 'BillNumberCapture'
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
And Rate a 'Report2IndiaNumber' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DrillDownToSMSUsageClickYes'
And Submit Adjustment 'ItemChargeGoodwillAdjustment'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


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
And Submit BillLevel Adjustment 'GoodWillItemChargeHandlePopUp'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:AdjNew01  NewCo CCS 5.0_CR 103729_BRM_TS06_TC06_Validate that if the account status is cancelled, an Agent can still successully make adjustments from siebel GUI
@Devi
Meta:@AdjNew01
@FolderName AdjNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'Report2IndiaNumber' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
And ReLogin with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'InstalledMSISDNSelect' 'ItemLevelSMSAdjustment'
And Submit Adjustment 'GoodWillItemLevel'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
When Execute query 'Adjustment1EuroAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:ADJ009 R2.4.1_CR47912_billing_2028.a_Regression_TS06_TC01_Agent creates billing adjustment in Siebel against the closed bill and verify BRM has received adjustment 
          from Siebel and applied to the next invoice
@Ankesh
Meta:@ADJ009
@FolderName ADJ009
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'NormalVoice120SecIndRating' CDR
When Execute query 'RatingPriceValidation'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
When Execute query 'AMOUNT0CmpareABC&lockb&other'
Given Perform Bulk Adjustment 'BulkAdj'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'VoiceUsageAllCharge'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ItemAdjustmentandCaptureAmt'
And Submit Adjustment 'ItemChargeGoodwillAdjustmentFullAdj'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: ADJ013 R2.2_CR46839_Billing_TS03_TC01_Invoice to show correct VAT for adjustments The adjustment is created against the open bill 
         at the Bill level with VAT level as in tax config file 
@Vaishnavi
Meta:@ADJ013
@FolderName ADJ013
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'SMSRoamingMultiple' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BPRA LCS Phase2 Sprint1_CCS6.0_EBU Sharer_Billing_Adjustment_Payment_Refund_on promotion 106403
@Ankesh
Meta:@BPRA
@FolderName BPRA
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidParentSharer106403'
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
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
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

Scenario: BRM_TS07_Validate Bill level adjustment with Adjust Full Amount option from Adjustment tab in Billing Profile view for account with multiple services
@Ankesh
Meta:@ADJNewReg01
@FolderName ADJNewReg01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'SMSUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'MMSUsageSEA01ToUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'AccountBillDetail'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillBillLevelFullBill'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: NewCo_CCS7.0_INC_PBI100115_Defect_1823_NEW Payment details retained from previous customer
@Kiran
Meta:@AdjustmentOld
@FolderName AdjustmentOld
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Create the account type 'ConsumerPostpaid1'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization1' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Click On PaymentRefund Button 'CaptureCardDetails'
When Search Another Account ''
When Execute query 'BillNumberCapture2'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Click On PaymentRefund Button 'CompareCardDetails'

Scenario:AdjNew01 NewCo_CCS9.0_112233_BRM_TS03_Validate Bill Level Adjustment for the amount greater than 80 percent and  
         less than total bill charge from Adjustment tab in Billing Profile view
@Ankesh
Meta:@AdjustNew01
@FolderName AdjustNew01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'RetrievpendbalanceCapture'
Given Capture Bill Amount
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'RestrictedVodafoneAdj'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:NewCo_CCS9.0_PBI000000101370_Late Landing usage, bundled using the customers current bundle 
         and not the bundle the call was made
@Ankesh
Meta:@RatingNew1111
@FolderName RatingNewScript
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMSysDate60to40Days'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'RetrievpendbalanceCapture'
Given Capture Bill Amount
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'RestrictedVodafoneAdj'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


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



Scenario:BulkADJ02 CR_82099_BRM_Order Management_TC11_BRM to BRM_Verify that adjustment via bulk tool is getting applied on correct balance group of Parent child hierarchy account 
         and getting reflected on correct invoice of CBU consumer.
@Ankesh
Meta:@BulkADJ02
@FolderName BulkADJ02
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
When Execute query 'PayingChildRetrievalCapture'
And Execute query 'ChildMSISDNRetrievalCapture'
Given Perform Bulk Adjustment 'BulkAdj'
And Generate Month End Bill with Account 'ACCOUNTNO0'
And Generate Month End Invoice with Account 'ACCOUNTNO0'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Perform Bulk Adjustment 'BulkAdj'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BulkADJ03 NewCo_CCS7.0_INC_PBI100518_Defect_2137_Defaulting Account Information Displayed in Balance Group
@Ankesh
Meta:@BulkADJ03
@FolderName BulkADJ03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
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
When ReLogin with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'ParentLegalGrpValue'
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ParentAccDrillDown'
And Validate the Legal Status 'ClickParentBundleUsage'
When Search The Account ''
When Execute query 'ChildLegalGrpValue'
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ChildAccDrillDown'
And Validate the Legal Status 'ClickChildBundleUsage'

Scenario:BillInq01 NewCo_CCS10.0 _PBI94784_Defect_9199_Service Transfer creating Account poid mismatch in service_t and uniqueness_t tables 9199
@Ankesh
Meta:@BillInq01
@FolderName BillInq01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Execute query 'AccountParentServiceid'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
And Search The Account ''
And Create 'NewOrderAddressPopUp'Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Perform Credit Vet
And Submit the Order 'NormalSubmitforSubAccount'
When Execute query 'AccountChildServiceid'
When Search The Account ''
And Retrieve Promotion 'PostpaidParentPromotion107787'
And Modify with owned product 'ServiceHierarchyChange'
And Select Billing Serviceaccount 'ParentBillingAcc'
And Select Billing Serviceaccount 'ParentServiceAcc'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'CompareParent&ChildServiceId'

Scenario: R2.3_CR35698_Billing_TS31_TC01_Payment_Refunds_Solution_Enhancements_Front office agent performs payment transfer flow in Siebel for full payment from customer 
          account to a another customer account successfully via suspense account
@Ankesh
Meta:@PR27-kal dekhange
@FolderName PR27
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
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'BI06Suspense'
When Execute query 'PaymentInquiryBI06'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Payment Transfer 'PaymentTransfer'
When Payment Refund Reversal Validation 'BlankRowPass'
When ReLogin with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
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
Then Make Suspense Payment
And Suspense Payment Validation 'CCSuspenseValidationPayTransf'
When Execute query 'PaymentInquiryBI06'
And Payment Refund Reversal Validation 'BalanceCheck'

Scenario:BillInq10 R2.4.2_51390_Billing_TS11_TC01_Validate Balance Summary fields in Legal Group Members View for SME customer  not in collection
@Ankesh
Meta:@BillInq10
@FolderName BillInq10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
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
And Generate First Bill
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'AMOUNT0Other'
When Make Payment for 'ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'UnallocatedPaymentValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Perform Bulk Adjustment 'BulkAdj'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ValidateUnallocatedAdj'
Given Login To Putty 'BRMPutty3' in 'Env'
Given Rate a 'PR12' CDR
When Execute query 'DataUsageAll'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ValidateUnbilledUsage'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
When Search The Account ''
When Execute query 'WriteOff1'
Then WriteOff 'WriteOff'
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ValidateWriteOff'