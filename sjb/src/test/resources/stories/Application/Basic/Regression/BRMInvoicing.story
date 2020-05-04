Story: Invoicing
Meta:@InvoicingBulk
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative: User should be able to to perform Invoicing


Scenario: INV04 164018_R2.4.1_CR49708_Billing_TS21_TC01_Validate the presentment of VAT level summary on invoice for SME segment
@Devi
Meta:@INV04
@FolderName INV04
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
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'RatingPriceValidation'
Given Rate a 'VoiceRatingFrmUSAToFrance' CDR
When Execute query 'IND01VoiceRoaming'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: INV06 217315_CR_91828_BRM_Invoicing_TC12_BRM to BIP_Perform first month billing for Mobile Phone Service where only call and 
          data usage has been peformed on the service - no text and other usage
@Ankesh
Meta:@INV06
@FolderName INV06
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
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
And Rate a 'Normal60VoiceRatingNoInd' CDR
When Execute query '501inBundle'
Given Rate a 'Normal18000VoiceRating1Ind' CDR
And Rate a 'Normal60VoiceRating2Ind' CDR
When Execute query '501outBundle12'
Given Rate a 'InPartial0GBP' CDR
When Execute query 'Indata0Pond'
Given Rate a '10GBP4Ind' CDR
When Execute query 'OutData10Pound'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario:INV07 R2.4.1_CR49700_Billing_TS11_TC01_Validate that the Enterprise Customer is charged correctly after applying the recurring 
           credit discount product for the applicable billing cycle
@Ankesh
Meta:@INV07
@FolderName INV07
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Promotion is added with 'CreditFor24Months'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV11 R2.2_Defect36691_Billing_ Incorrect tax code configured for On Account Product One-off charge to my bill
@Pankaj
Meta:@INV11
@FolderName INV11
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
When Perform the CustomerAccount
Then Verify the Product and Services for 'AllActiveRootProductCollapse'
Given Login To Putty 'BRMPutty1' in 'Env'
And Account charge Apply
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: INV12 R2.4.1_54266_Billing_TS08_TC01_Validate the zero-rated product is synched in BRM and does not appear on invoice
@Pankaj
Meta: @INV12
@FolderName INV12
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'BillingActivation'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: INV13 NewCo_R15.2_CR64162_Billing_TS02_TC01_Validate that agent is able to add line rental discount Vodafone Employee Advantage 10 percent duration 27
@Pankaj
Meta: @INV13
@FolderName INV13
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Disc10%DataAbroadOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Perform the CustomerAccount
Then Verify the Product and Services for '10%OffPlan'
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV15 206777_NewCo_R15.2_CR64162_Billing_TS04_TC01_Validate that agent is able to add line rental discount 3 months 25 percent off line rental  at  level 3 and place
@Pankaj
Meta: @INV15 
@FolderName INV15
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Discount25%offplan'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: 168920_R2.4.1_49708_Billing_TS03_TC01_Validate bill presentment for negative or zero balance owed for a SME Consumer having DD profile
@Ankesh
Meta:@INV17
@FolderName INV17
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'SmallSMEPostpay'
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
And Make Payment for 'LockboxAccount'
When Execute query 'PaymentReceivedLockboxCompare'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNoIndex1'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV19	 168997_R2.3_CR35686_Billing_TS11_TC_01_Assetisation of ER products_Validate_Invoicing for 
		  Content products Before and After expiry in a postpaid promotion_Content A_XL_12month Content
@Vaishnavi
Meta:@INV19
@FolderName INV19
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSimoEntertainment'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotionSimoEntertainment'
And Modify 'ModifyWithInstalledID' with Econfig Item '12monthsinclusiveEntertainmentOFF'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV26 CR_91828_BRM_Invoicing_TC21_BRM to BIP_Perform Invoice of Transfer of service from 
          Non-paying child to Parent’s account
@Ankesh
Meta:@INV26
@FolderName INV26
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
And Generate First Bill
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ServiceHierarchyChange'
When Select Billing Serviceaccount 'ParentBillingAcc'
And Select Billing Serviceaccount 'ParentServiceAcc'
And Perform Credit Vet
And Reserve Order
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'RetrieveNonpayingChildAccount'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'AccountNoIndex1'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: INV33 NewCo CCS 1.0_Mobile_Billing_PBI93459_Defect69831_update of non-geo bubble message on CBU 
		  and EBU invoice
@Vaishnavi
Meta:@INV33
@FolderName INV33
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
And Rate a 'Tarrif563_448446431234' CDR
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'Tarrif563_448446431234' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: INV34 CR_91828_BRM_Invoicing_TC40_BRM to BIP_Perform invoicing where Front Page extended to 2 
		  pages and payment method is Credit Card or Direct Debit
@Vaishnavi
Meta:@INV34
@FolderName INV34
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
Given Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'AccountBillDetail'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'HardwareAdjustment'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'NetworkQualityAdjustment'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemChargeHandlePopUp'
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


Scenario: INV35 BRM_CR85735_Invoicing_TC20_Siebel to BRM_Verify that the Included flag is displayed as ‘Yes’ 
		  in the Itemised usage section of the Invoice when a voice call is received in Rest of World by a 
		  roaming customer, for a EBU customer
@Vaishnavi
Meta:@INV35
@FolderName INV35
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
And Rate a 'CDRType10_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType10_447387912299_Index1' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'




Scenario: INV39 168922_R2.4.1_49708_Billing_TS28_TC01_Validate that for SME customers the detailed usage 
		  information is itemised correctly and appears in dual column format. Verify the invoice 
		  description of certain usage scenarios
@Ankesh
Meta:@INV39
@FolderName INV39
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
Given Generate First Bill
Given Rate a 'Voice50NOEDRNOIndex' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'SMS50DomesticEDR1Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'Domestic10RGBPOutPartial2Indx' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'Domestic4015Tariff3Indx' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV40 CR_91828_BRM_Invoicing_TC26_BRM to BIP_Perform rating and invoicing for Late Landing CDRs 
		  for Mobile phone and verify BoB
@Ankesh
Meta:@INV40
@FolderName INV40
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMSysDate60to40Days'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Select the TimeStamp '2d210hr10mins'
When Execute query 'Timestamp1Capture'
Given Rate a 'TIMESTAMPRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: NewCo_CCS 4.0_CR 103543_BRM_TS02_TC01_Validate that end date is displayed after the product in the plan line on both the Front page and Summary page 
          of invoice of account having Mobile services.
@Ankesh
Meta: @INVNew01
@FolderName INVNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSimoEntertainment15CTR'
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
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: InvNew03 NewCo_CCS 4.0_CR 103543_BRM_TS03_TC01_Validate that end date is displayed after the product on 
          Summary page of invoice  of account having Mobile services
@Ankesh
Meta:@InvNew03
@FolderName InvNew03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Extra300mins084to087'
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
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: InvNew04 NewCo_CCS 4.0_CR 103543_BRM_TS09_TC01_Validate that end date is displayed on invoice after 
           performing tariff migration for account having Mobile services
@Ankesh
Meta:@InvNew04
@FolderName InvNew04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra5Gb(7days)On'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
Then Perform the action 'TariffMigrationPromotion'
When Retrieve Promotion 'PostpaidPromotion110569'
Then Perform Migration
When Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BRMInvoiceing5 NewCo_CCS5.1_BRM_INC_PBI95656_Defect_68_PBI95656 DETAILS - summary invoice type issue where charges are not being displayed in the invoice pdf
@Vaishnavi
Meta:@BRMInvoiceing5
@FolderName BRMInvoiceing5
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
Given Generate First Bill
And Rate a 'Zone1ToZone1Rating' CDR
When Execute query 'Zone1ToZone1Amount8'
Given Rate a 'RoamingDataUsageIndex1' CDR
When Execute query 'OutData10Pound'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: NewCo_CCS 4.0_CR 103543_BRM_TS02_TC01_Validate that end date is displayed after the product in the plan line on both the Front page and Summary page 
          of invoice of account having Mobile services.
@Ankesh
Meta: @INVNew01
@FolderName INVNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSimoEntertainment'
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
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: InvNew02 NewCo_CCS 4.0_CR 103543_BRM_TS10_TC01_Validate that end date is displayed on invoice after performing TOO migration for account having Mobile services
@Devi
Meta:@InvNew02
@FolderName InvNew02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion111722'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion111722'
Then Perform the action 'TOOWithPromotion'
Given Retrieve BRM Account 'PostpaidConsumer110568BRM'
Then Perform Transfer Of OwnerShip
When Perform Credit Vet
And Reserve Order
And Submit the Order 'SubmitForTOOJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:CR_91828_BRM_Invoicing_TC25_BRM to BIP_Perform N NGN Calls -- to cover 2 pages of Itemised Usage for UK Calls-- from Mobile phone and verify Bubble Message
@Ankesh
Meta:@INV27
@FolderName INV27
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'NGNVoiceRating' CDR
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: INV28 - Newco_CCS_8.1.2_5969_Wrong dates on the summary page of the bill
//Invoice Validation Mandatory manuaaly
@Tarun
Meta:@INV28
@FolderName INV28
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Discount25%offplan'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
@ Run the next part next day
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Modify 'ModifyWithRootProduct' with Econfig Item 'Discount25%offplanDelete'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:NewCo_CCS9.0_INC_PBI97470 _Defect_6758_SAE - Update VAT message on invoice PDF
Meta:@InvoicingNew
@FolderName InvoicingNew
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'
Given Login with 'TMTTAR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: BRMInvoiceing6 NewCo_CCS 5.0_CR 104082_BRM_TS03_TC02_Validate that SMS to 600797 GSM Service codes are correctly presented as Free on the invoice.
@Ankesh
Meta:@BRMInvoiceing6
@FolderName BRMInvoiceing6
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'SMSUsageTo600797' CDR
When Execute query '501inBundle'
When Execute query 'GetCurrntBalAccNoCapture'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BRMInvoiceing7 NewCo_CCS 5.0_CR 104082_MZ_TS02_TC02_Validate that calls to 516 tariff class numbers are correctly presented as Free on the invoice.
@Ankesh
Meta:@BRMInvoiceing7
@FolderName BRMInvoiceing7
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
Given Generate First Bill
Given Rate a 'VoiceUsageToTariff516' CDR
When Execute query '501inBundle'
When Execute query 'GetCurrntBalAccNoCapture'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'