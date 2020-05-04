Story:BillInquiry BRM
Meta:@BillInq
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM


Scenario: BINew03
@Vaishnavi
Meta:@BINew03
@FolderName BINew03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidPromotion110041'
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

Scenario: BI11 146377_R2.2_CR26581_Billing_TS04_TC01_Validate the service charge view for billed usage 
		  (section 6.6 in DA) for a postpaid customer with  one bill profile
@Vaishnavi
Meta:@BI11
@FolderName BI11
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Rate a 'Duration60_917387912299' CDR
When Execute query 'InternatVoiceUktoInd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'DrillDownToMsisdn' 'CaptureValueVoiceUsage'
When Execute query 'VoiceUsageAll'


Scenario: BI10 R2.2_CR_26581_UC_R2.2_10_TS_01_TC01_Validate the agent is able to search voice event in the 
		  itemised list applet based on certain parameters of postpaid customer (section 6.7 in DA)
@Vaishnavi
Meta:@BI10
@FolderName BI10
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Rate a 'BI10_447387912299' CDR
And Rate a 'BI10_447437890123' CDR
And Rate a 'InternationalUKtoIndiaVoice' CDR
And Rate a 'BI10_448452105555' CDR
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
When Execute query 'VoiceUsageAll'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Level Service Charges 'InstalledMSISDNSelect'
And Item Level Summary Charges 'DrillDownToNetAmount'
When Event Level Adjustment selection 'UsageToUKCalls'
And Event Level Adjustment selection 'DescriptionOfRomaingCallfromIndia'
And Event Level Adjustment selection 'UsageToAbroadCalling'
And Event Level Adjustment selection 'UsageTo08or09NumbersClickNo'


Scenario: BI03 R2.2_CR26581_Billing_TS01_TC01_Validate the billed charges in the Bill List View 
		  (section 6.4 in DA) for a postpaid customer with one bill profile 
@Vaishnavi
Meta:@BI03
@FolderName BI03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionMBB4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
And Show All Purchase Products in the account ''
And Rate a 'Report2IndiaNumber' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'ABCAmount0Payment'
When Execute query 'AMOUNT0CmpareABC&lockb&other'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickYes'
And Submit Adjustment 'ItemChargeGoodwillAdjustment'
When Execute query 'AdjustmentAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Execute query 'BillNumberCapture'
And Item Level Service Charges 'InstalledMSISDNSelect'
When Execute query 'SMSUsageAll'
And Item Level Summary Charges 'CaptureValueSmsUsage'
When Execute query 'BillNumberCapture'
When Execute query 'CycleForwardCaptureAmountPrepaid'
And Item Level Summary Charges 'CaptureValueCycleForward'

Scenario: BI06 R2.4.2_24281_Billing_TS09_TC01_Validate that agent is able Search for payments made on account Logged within BRM
@Tarun
Meta:@BI06
@FolderName BI06
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
And Generate First Bill
And Show All Purchase Products in the account ''
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'BI06Suspense'
When Execute query 'PaymentInquiryBI06'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'BI06CardDetails'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: BI02 R2.2_CR_26581_UC_R2.2_4_TS_01_TC01_Validate the billed charges in the Bill List View 
		  (section 6.4 in DA) for a postpaid customer with one bill profile
@Vaishnavi
Meta:@BI02
@FolderName BI02
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Rate a 'Report2IndiaNumber' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'ABCAmount0Payment'
When Execute query 'AMOUNT0CmpareABC&lockb&other'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickYes'
And Submit Adjustment 'ItemChargeGoodwillAdjustment'
When Execute query 'AdjustmentAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'BillNumberCapture'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Level Service Charges 'InstalledMSISDNSelect'
When Execute query 'SMSUsageAll'
And Item Level Summary Charges 'CaptureValueSmsUsage'
When Execute query 'BillNumberCapture'
When Execute query 'CycleForwardCaptureAmountPrepaid'
And Item Level Summary Charges 'CaptureValueCycleForward'


Scenario:BI01 R2.2_CR26581_Billing_TS03_TC01_Validate Top-up history view (section 7.1.1 in DA) for prepay customer 
@Tarun/Pankaj
Meta:@BI01
@FolderName BI01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'PrepaidConsumer'
And Create New 'NewPrepaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PrepaidPackage'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'TopUpCredit' CDR
And Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then TopUpHistoryValidation 'TopUpCredit'

Scenario: CCS 7.0_Billing_BillEnquiry_Validate unbilled voice usage view for customer with all usage and From-To amount criteria
Meta:@BillInquiry01
@FolderName BillInquiry01
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceIndToUKrating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceIndToUKrating1' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage1Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage2Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage3Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Itemized List search 'SearchusingAmountAll'

Scenario: CCS 7.0_Billing_BillEnquiry_Validate billed Voice usage view for customer with all usage and different criteria
Meta:@BillInquiry02
@FolderName BillInquiry02
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
Given Rate a 'RoamingUsageWithoutIndex' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage1Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage2Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage3Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsage4Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DrillDownToVoiceUsageClickNo'
When Itemized List search 'SearchusingAmtBilledConsumer'
When Itemized List search 'ValidateinclusiveVatAmountBilled2'
When Itemized List search 'SearchusingAmountBilledUsingusagetype'
When Itemized List search 'SearchusingAmountBilledUsingDate'

Scenario: BI12 - CCS 7.0_Billing_Validate GetBalance call for customer with Extra Data bundle product and roaming bundle product is sucessful
@Tarun
Meta:@BI12
@FolderName BI12
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra1GBDataMonthlyRoamingAntiFraudON'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'MSISDNFromAccount'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Product Bundle Usage Validation 'Extra1GBData'