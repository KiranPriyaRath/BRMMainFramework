Story:Rating
Meta:@NewDev
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario:OMReg06 R15.2_CR51957_Billing _TS03_TC01_Validate that the Legal group account status and account status is reflected correctly in Siebel and BRM  respectively when a stolen bar is
          added on the Child account
Meta:@OMReg06
@FolderName OMReg06
@Ankesh
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
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Create new order 'NewOrderPopUp'
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify 'ModifyWithInstalledID' with Econfig Item 'StolenBarAddNew'
And Submit the Order 'SubmitwithBlacklist'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'VFAccLegalGrpStatus'
And Select the Jobs 'VFLegalGrp'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When Execute query 'LegalGrpStatus'
And ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildMSISDNRetrievalCapture'
And Modify Owned 'ModifyWithInstalledIDChild' with Econfig Item 'StolenBarAdd'
And Submit the Order 'SubmitwithBlacklist'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'VFAccLegalGrpStatus'
And Select the Jobs 'VFLegalGrp'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When Execute query 'LegalGrpStatusSuspended'

Scenario:RatingReg01 DELETED - 16293 - R2.4.2_24281_Billing_TS27_TC01_Validate that the PAS CDR is successfully processed in BRM 
         topup via AMEX payment card.
Meta:@RatingReg01
@FolderName RatingReg01
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'PrepaidTranctRating' CDR
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then TopUpHistoryValidation 'TopUpCredit'


Scenario: NewCo_CCS11.0 CR VFUKE-1 OOB_TS015_TC01_Perform Rating from UK to county eligible for International saver but not Vodafone 
          international bundle, Discounted OOB rates should be applied to customer
@Ankesh
Meta:@RatingReg09
@FolderName RatingReg09
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver500'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'International60UKtoBhutan' CDR
When Execute query 'BarbadosToRomaniaIndex1'
Then Download the EDW Usage file to local system
Given Rate a 'International30000UKtoBhutanIndx1' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Rate a 'International60UKtoBhutanIndx2' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceValidationUK'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: OMReg10 Validate that prepay MPS change MSISDN order is completed in OSM through siebel and Activation timestamp is updated 
         in MZ and BRM DB for correct rating to ensure no OOB is reported
@Ankesh
Meta:@OMReg10
@FolderName OMReg10
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Perform the action 'ModifyPromotion'
When Update ICCID against 'ChangeMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Reserve Order
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'TodaysDate'
When Execute query 'DateCompare'
Given Rate a 'VoiceRating0RateGBP' CDR
When Execute query 'PrepayRatingCount1'


Scenario:RatingReg01 DELETED - 16293 - R2.4.2_24281_Billing_TS27_TC01_Validate that the PAS CDR is successfully processed in BRM 
         topup via AMEX payment card.
Meta:@RatingReg01
@FolderName RatingReg01
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'PrepaidTranctRating' CDR
When Execute query 'PrepayRatingCount1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then TopUpHistoryValidation 'TopUpCredit'

Scenario:NewCo_CCS11.0 CR VFUKE-1 OOB_TS05_TC01_Perform Rating  from EU Zone to Country which is eligible for 
         international saver bundle for CBU Customer.
@Ankesh
Meta:@RatingReg23
@FolderName RatingReg23
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver100'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'VodafoneProductcheck'
Given Rate a 'BeltnToIndVoice' CDR
When Execute query 'VodafoneProductRating60sec'
Then Download the EDW Usage file to local system
Given Rate a 'CypptToIndVoice' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'BelgiumAndCyprusValidation'


Scenario: Printshop Invoice 1 EBU SME
@Ankesh
Meta:@INVReg01:Incomplete Script
@FolderName INVReg01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
When Create New Billing Profile 'NewPostpaid'
When select the MediaType with 'PaperMediaType'
And Goto Account Summary
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'DomesticDataRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingUsageWithoutIndex' CDR
When Execute query 'BillNumberCapture'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
When Execute query 'InvoiceOdiSharedNew'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'DOCSTORE'
And Execute ODI Scenario 'DocStorewidSlot'
And Validate ODI Scenario
When Execute query 'InvoiceOdiSharedCompleted'
Given Login To Putty 'SOA01Putty' in 'Env'
Then Printshop File extraction

Scenario: INVReg02 Printshop Invoice 2 CBU
@Kiran
Meta:@INVReg02
@FolderName INVReg02
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When select the MediaType with 'PaperMediaType'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'DomesticDataRating' CDR
When Execute query 'CaptureRateofDataUsage'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
And Make Payment for 'LockBoxPayment'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
When Execute query 'InvoiceOdiSharedNew'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'DOCSTORE'
And Execute ODI Scenario 'DocStorewidSlot'
And Validate ODI Scenario
When Execute query 'InvoiceOdiSharedCompleted'
Given Login To Putty 'SOA01Putty' in 'Env'
Then Printshop File extraction



Scenario:RatingReg01 NewCo CCS8.1.2_PBI100365_Defect 5590_FMW Change required as part of WR 41262 which will be delivered in CCS 8.1.1
Meta:@RatingReg04
@FolderName RatingReg04
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromCANTE' CDR
When Execute query 'WorldTraveller'
Given Rate a 'RoamingSMSFromCANTE' CDR
When Execute query 'SMSUsageRom'
Given Rate a 'RoamingMMSFromCANTE' CDR
When Execute query 'MMSUsageSEAMCToUK'
Given Rate a 'RoamingDataFromCANTE' CDR
When Execute query 'DataUsageBMU01ToUK'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageCANTE'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
When Validate Itemised List Selection TopUp 'SMSUsageCANTE'


Scenario: NewCo_CCS11.0 CR VFUKE-1 OOB_TS06_TC01_Perform Rating  from UK to Countries which is not eligible for international saver bundle for EBU Customer
@Ankesh
Meta:@RatingReg10
@FolderName RatingReg10
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver100'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'VodafoneProductcheck'
Given Rate a 'InternationalVoiceUktoAfgan' CDR
When Execute query 'InternationalCallcharge'
When Execute query 'VodafoneProductcheck'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceValidationUK'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: AdjReg02 NewCo CCS 11.0_PBI103531_Defect 9903_Adjust Full Amount checkbox not working for Item and Event level adjustment 
          where charges get rounded up
@Ankesh
Meta:@AdjReg02
@FolderName AdjReg02
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'DomesticData2.06Rating' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query 'DataUsageAll'
Given Generate Month End Bill with Account 'AccountNo'
Given Rate a 'DomesticData2.06Ratingindex' CDR
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickYes'
When Submit Adjustment 'GoodWillBillLevelFullBillBillingNew21'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickDataUsageAdjButton'
And Submit Adjustment 'GoodWillBillLevelFullBillBillingNew21HandlePopup'

Scenario:CR_82099_BRM_Order Management_TC05_Siebel to Brm_Verify that new default account level balance group is created when agent place an order for change of Billing account from one paying child to another non- paying child
Meta:@OMReg04
@FolderName OMReg04
@Kiran
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
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ServiceHierarchyChange'
And Select Billing Serviceaccount 'ParentBillingAcc'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildMSISDNRetrievalSiebel'
When Execute query 'GetBalanceGroupAccMsisdn0Comp'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo1Noindx'
Given Generate Month End Bill with Account 'ACCOUNTNO0Indx1'
Given Generate Month End Bill with Account 'ACCOUNTNO1Indx2'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: NewCo_CCS11.0_INC_PBI104692_Overpayment Allocation issue
@Kiran
Meta:@PRReg04
@FolderName PRReg04
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'ItemStatusCapture'
Given Perform Bulk Adjustment 'BulkOverpaymentAllocation'
When Execute query 'AdjustmentStatusBeforeAllocationUtility'
When Run OverPayment utility 'OverPaymentAllocation'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'AdjustmentStatusAfterAllocationUtility'



Scenario: DELETED - 11388 - NewCo_CCS 6.0_CR 105400_BRM_TS06_Validate that partial payment is applied corretly to customers bill after one failed DD payment and same is correctly displayed over invoice
@Kiran
Meta:@PRReg05
@FolderName PRReg05
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
And Make Reverse Payment for 'Type1and2RC'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'DDpaymentStatusReversed'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Make Payment for 'LockBoxPayment'
When Execute query 'PaymentLockbox10'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'BankPaymentSucessful'
Given Login To Putty 'BRMPutty3' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:BillNewReg12 NewCo_CCS10.1_INC_PBI104006_Defect_10020_Billing is failing due to entry not present in config_vf_ca_actions_t table
Meta:@BillNewReg12
@FolderName BillNewReg12
@Kiran
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
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'ProfileCollectionsParamsTable'
When Execute query 'PayingChildRetrievalCapture'
And Execute query 'ChildMSISDNRetrieval'
Given Rate a 'Data200Rating' CDR
When Execute query '200DATAchild'
When Execute query 'ProfileCollectionsParamsTabledelchild'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'AccountNoIndex1'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


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

Scenario: PRReg08 NewCo_CCS9.0_INC_PBI103169_Defect_7581_BGC ABC & Lockbox Payments are not loaded into payment suspense account.
@Ankesh
Meta:@PRReg08
@FolderName PRReg08
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Generate First Bill
When Make Payment for 'WrongPayment'
When Make Payment for 'WrongPaymentLockbox'
When Make Payment for 'WrongPaymentOthers'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Make Suspense Payment 'PaymentSlip'
And Suspense Payment Validation 'ABCTransfer'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Make Suspense Payment 'Chequepayment'
And Suspense Payment Validation 'LOCKBOXTransfer'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Make Suspense Payment 'Electronicpayment'
And Suspense Payment Validation 'BGCTransfer'
When Payment Refund Reversal Validation 'PaymentValidation'


Scenario: OMReg08 NewCo CCS 8.0_5041_Transfer of service (TOS) orders are failing after an upgrade order
@Kiran
Meta:@OMReg08
@FolderName OMReg08
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AgentStoke' in 'Env'
When Search The Account ''
And Select 'UsedProdCommitment&DrillDown' AgreementId
And Retrieve Promotion 'PostpaidPromotion107785'
And Change the Date
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'ModifyPromoWithPromotion'
When Retrieve Promotion 'PostpaidPromotion112180'
Then Perform Migration
When Submit the Order 'Normal Submit'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New Billing Profile 'NewPostpaid'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion112180'
And Modify with owned product 'ServiceHierarchyChange'
And Select Billing Serviceaccount 'BillingAccountChildAccount'
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'PayingChildRetrievalCapture'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Invoice with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg25 NewCo CCS 11.1_PBI000000105250_Incorrect Pricing - 3rd Country SMS on Roam Further - Newco Postpay
@Kiran
Meta:@RatingReg25
@FolderName RatingReg25
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'SMSUsageSAToUK' CDR
When Execute query 'WorldTraveller'
Given Rate a 'SMSUsageSAToInd' CDR
When Execute query 'InternationalSMSUsage'
Given Rate a 'SMSUsageAusToAlgeria' CDR
When Execute query 'InternationalSMSUsage'
Given Rate a 'SMSUsageAUSTtoBulgaria' CDR
When Execute query 'InternationalSMSUsage11'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: CCS 7.0_CR 109447_TS37_Billing_TC01_ Validate Customer doing data usage in ROW(Haiti) is covered 
         under Global Data Traveller 1GB(109505).
@Ankesh
Meta:@RatingReg12-need to develop shell scripting for rating
@FolderName RatingReg12
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENTVIP' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'BusinessBlack'
And Customise 'RootProduct' with Econfig Item 'GlobalDataTravel1GB'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'DataRoamingHT' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'

Scenario: CR_97296_BRM_Inbundle rating_TC07_Siebel to BRM_Validate that OSM sends the same timestamps for 
		all order line items within the order for addition of add-on bundle on an active FL + BB promotion
@Kiran- Retrieve FL+BB account manually
Meta:@OMReg01
@FolderName OMReg01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'QP' in 'Env'
When Execute query 'TodaysDate'
When Search The Account '7000448341'
And Modify 'ModifyFLservice' with Econfig Item 'International300FL'
And Submit the Order 'Normal Submit'
When Execute query 'OOBCreated_t'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'FLInternationalCallWithINT300' CDR
When Execute query 'InternationalFLCall'
Given Rate a 'FLInternationalCall' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: CCS 10.0_WR41727_TS01_TC02_Perform Adjustment via Siebel GUI for New Reason Code VAT Adjustment on EBU Customer
@Kiran
Meta:@AdjReg01
@FolderName AdjReg01
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
And Rate a 'DomesticDataRating' CDR
When Execute query 'OutData10Pound'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickYes'
When Submit Adjustment 'VATAdjustment'
When Search The Account ''
When Execute query 'MSISDNFromAccount'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickDataUsageAdjButton'
And Submit Adjustment 'VATAdjustment'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'VATAdjustment'


Scenario: Validate that postpay Tariff upgrade order is completed in OSM through siebel and Activation timestamp 
          is updated in MZ and BRM DB for correct rating to ensure no OOB is reported.
@Ankesh-Partial Script
Meta:@OMReg03
@FolderName OMReg03
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'TariffMigrationPromotion'
When Retrieve Promotion 'PostpaidPromotion112289'
Then Perform Migration
When Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'TodaysDate'
When Execute query 'ActivationDate'
When Execute query 'ProductDateDescription'


Scenario: INVReg09 NewCo_CCS9.0_INC_PBI101817_Defect_7594_0 Amount Invoice Notification Suppression Issue
@Kiran
Meta:@INVReg09
@FolderName INVReg09
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'PostpaidConsumer'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110043emp'
And Customise 'RootProduct' with Econfig Item '100%EmployeeDiscount'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'RetrievePendBalanceNotificationSupp'
Given Rate a 'DataNational0.0001' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'DataNational0.0011' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'
When Execute query 'InvoiceOdiSharedSuppressed'

Scenario: CR82099_Remedial Fixes_Billing_TS03_Validate after Pre to Post migration agent is not able to 
          place new service on dummy profile.
@Ankesh
Meta:@PretoPost02
@FolderName PretoPost02
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
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
When Retrieve Promotion 'PostpaidPromotion107785'
And Perform the upgrade
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim111'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Generate First Bill
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Create 'NewOrderPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
When Select Billing Serviceaccount 'BillingProfileselection'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'SubmitBillingValidationDummyProfile'

Scenario: NewCo_CCS11.0 CR VFUKE-1 OOB_TS09_TC01_Perform Rating and invoicing for Late Landing CDRs for 
          Mobile Phone and verify BoB for Customer having international Saver bundles for CBU Customer
@Ankesh
Meta:@INVReg04
@FolderName INVReg04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver100'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'VodafoneProductcheck'
Given Rate a 'InternationRatingIndia' CDR
When Execute query '501inBundle'
When Execute query 'ProductDescriptBTExhaust'
Given Rate a 'InternationRatingIndiaIndx1' CDR
When Execute query 'ValidationBundle'
Given Generate Month End Bill with Account 'AccountNo'
Given Select the TimeStamp 'CA06'
When Execute query 'Timestamp3Capture'
Given Rate a 'InternationRatingIndiaIndx2' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNoIndex1'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: NewCo CCS5.0_CR103729_BRM_TS08_TC08_Agent places a order with Parent Child Hierarchy and services are cancelled for non paying child  in Siebel  same is  reflected in BRM_Cancelled to Inactive_Automated_Mobile
@Kiran
Meta:@BJNew03
@FolderName BJNew03
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion110041'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Execute query 'Account&Billingstatus'
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildAccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
When Execute query 'AccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
Given Rate a 'DataNational' CDR
When Execute query 'SuspenseRating'

Scenario: PRReg06 NewCo_CCS 9.0_CR 93160_DB Encryption_TS12_Modify Paytype from CC to DD and verify 
     if BANK_ACCOUNT_NO and SORT_CODE are encrypted 
        in Siebel and BRM DB as per AES 256 bit Encryption
@Ankesh
Meta:@PRReg06
@FolderName PRReg06
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
When Search The Account ''
Then Create Direct Debit for scenario 'DirectDebit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'DDSortCode'
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'DebitnoBanknoCapture'
When Execute query 'RetrievpendbalanceCapture'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
When Execute query 'AccountBillDetail'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'DDpaymntStatusSucessfulOneBill'

Scenario: OMReg07 CR_97296_BRM_Inbundle rating_TC01_Siebel to BRM_Validate that OSM sends the same timestamps for all order line items 
         within the order for upgrade or modify promo journey on active MPS 
@Ankesh
Meta:@OMReg07
@FolderName OMReg07
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
And Login with 'AgentStoke' in 'Env'
When Search The Account ''
And Select 'UsedProdCommitment&DrillDown' AgreementId
And Retrieve Promotion 'PostpaidPromotion110041'
And Change the Date
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
Then Perform the action 'ModifyPromoWithPromotion'
When Retrieve Promotion 'PostpaidPromotion112281'
Then Perform Migration
When Submit the Order 'RecurringExtraPopUpOn'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'TodaysDate'
When Execute query 'ActivationDate'
When Execute query 'ProductDescriptDate'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: NewCo CCS5.0_CR103729_BRM_TS08_TC08_Agent places a order with Parent Child Hierarchy and services are cancelled for non paying child  in Siebel  same is  reflected in BRM_Cancelled to Inactive_Automated_Mobile
@Ankesh
Meta:@BJNew03
@FolderName BJNew03
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
When Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion4G'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion4G'
When Modify with owned product 'DisconnectwithPromotion'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
When Modify with owned product 'DisconnectwithPromotion'
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
When Execute query 'ChildAccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
When Execute query 'Accnt&Billafterinactive'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'DataNational' CDR
When Execute query 'ComparingtheRatingValue0'


Scenario:NewCo_CCS 9.0_WR41473_Youth Employee Discounts_TS02_TC01_Validate that Opt-out CDR for VOXI 15GB Plan 
– Ambassador discount(priced 0)  processed by MZ is rated successfully in BRM
@Kiran
Meta:@BillNewReg09
@FolderName BillNewReg09
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'PrepaidConsumerVOXI'
And Create 'NewOrderNoPopUp' 'NewPrepaid' Order
And Promotion is added with 'PrepaidPromotionVOXI'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInVOXITripleSIMAdd'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'OptOutCountCheck'
Given Rate a 'YouthCDR_Optout_Rating' CDR
When Execute query 'OptOutUsageCheck'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdnPrepay'
And Unbilled Type of Usage Adjustment selection 'DrillDownToPrepayUsage'
When Event Level Adjustment selection 'YouthOptOutPrepay'


Scenario:NewCo_CCS11.0 CR VFUKE-1 OOB_TS03_TC01_Perform Rating  from ROW1 to ROW2 when customer 
         having international saver 100  for EBU Customer 
Meta:@RatingReg24
@FolderName RatingReg24
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver100'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'VodafoneProductcheck'
Given Rate a 'IndtoSaudiRating' CDR
When Execute query 'VodafoneProductRating60sec'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsagePrepay'


Scenario: NewCo_CCS11.0 Spotify_TS02_BRM_Perform billing inquiry for modified customer which are having Credit balance process from MZ
@Kiran
Meta:@BillIngNew01
@FolderName BillIngNew01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create New Billing Profile 'NewPostpaid'
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'SpotifyCredit' CDR
When Execute query 'SpotifyCredit'
Then Download the EDW Usage file to local system
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'SpotifyUsages'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: NewCo_CCS11.0 Spotify_TS01_BRM_Perform billing inquiry for modified customer which are having debited balance process from MZ
@Kiran
Meta:@BillIngNew02
@FolderName BillIngNew02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SoleTraderAccount'
And Create New Billing Profile 'NewPostpaid'
When Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'SpotifyDebit' CDR
When Execute query 'SpotifyDebit'
Then Download the EDW Usage file to local system
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'SpotifyUsages'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: NewCo_CCS 2.0_CR99888_Billing_TS06_TC01_Perform Pre Collection P2P where Agent changes Total Agreement Cost Manually
@Kiran
Meta:@BillNewReg01
@FolderName BillNewReg01
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBillMePaymentBRM'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
When Create Payment arrangement for P2P '2Installments'
Then Validate Payment arrangement 'CheckPendingStatus'
When Execute query 'P2PMilestoneTable'
When Execute query 'UNIXSysdate'
When Execute query 'UpdateDueDateP2P'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Run P2P batch 'P2PBatch'
And Make Payment for 'ABCAmount0Payment'
When Run P2P batch 'PreCollectionsBatch'
When Execute query 'StatusCheckP2P'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
Then Validate Alert for Payment arrangement 'PendingAlertDrillDown'
Then Validate Payment arrangement 'CheckClosedStatus'
When Execute query 'UpdateSecondDueDateP2P'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Run P2P batch 'P2PBatch'
And Make Payment for 'ABCAmount1Payment'
When Run P2P batch 'PreCollectionsBatch'
When Execute query 'StatusCheckP2P1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
Then Validate Alert for Payment arrangement 'CheckClosedAlert'


Scenario: CR82099_Remedial Fixes_Billing_TS02_Perform Pre Collection P2P where Agent changes Total Agreement Cost Manually after Pre to Post migration
@Kiran
Meta:@BillNewReg04
@FolderName BillNewReg04
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
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
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
When Create Payment arrangement for P2P '2Installments'
Then Validate Payment arrangement 'CheckPendingStatus'
When Execute query 'P2PMilestoneTable'
When Execute query 'UNIXSysdate'
When Execute query 'UpdateDueDateP2P'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Run P2P batch 'P2PBatch'
And Make Payment for 'ABCAmount0Payment'
When Run P2P batch 'PreCollectionsBatch'
When Execute query 'StatusCheckP2P'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
Then Validate Alert for Payment arrangement 'PendingAlertDrillDown'
Then Validate Payment arrangement 'CheckClosedStatus'
When Execute query 'UpdateSecondDueDateP2P'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Run P2P batch 'P2PBatch'
And Make Payment for 'ABCAmount1Payment'
When Run P2P batch 'PreCollectionsBatch'
When Execute query 'StatusCheckP2P1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
Then Validate Alert for Payment arrangement 'CheckClosedAlert'


Scenario: NewCo CCS8.1.2_PBI100956_Defect 5593_FMW Change required as part of WR 41986 which will be delivered in CCS 8.1.1
@Kiran
Meta:@RatingReg17
@FolderName RatingReg17
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromCANMM' CDR
When Execute query 'WorldTraveller'
Given Rate a 'RoamingSMSFromCANMM' CDR
When Execute query 'SMSUsageRom'
Given Rate a 'RoamingMMSFromCANMM' CDR
When Execute query 'ConsumerMMS'
Given Rate a 'RoamingDataFromCANMM' CDR
When Execute query 'DataUsageBMU01ToUK'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageCANMM'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageCANMM'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickNo'
When Validate Itemised List Selection TopUp 'VoiceUsageCANMM'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'VoiceUsageCANMM'

Scenario: Unable to pass credit adjustments on bills with item dues
@Ankesh
Meta:@AdjReg03
@FolderName AdjReg03
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationVoiceRate' CDR
Given Generate Month End Bill with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'VoiceUsageAllCharge'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ItemAdjustmentandCaptureAmt'
And Submit Adjustment 'ItemChargeGoodwillAdjustmentFullAdj'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'CycleItemAdjustmentandCaptureAmt'
And Submit Adjustment 'ItemChrgeGdwillAdjustFullAdj'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: NewCo CCS 11.0_PBI104527_Defect 11082_FMW Change required as part of WR_46068 which will be delivered in CCS 11.0
@Kiran
Meta:@RatingReg08
@FolderName RatingReg08
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromAUTMT' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageAUTMT'

Scenario: RatingReg11 NewCo CCS 11.1_PBI105018_Defect 11728_Incorrect Origin description in siebel itemised usage view when usage is made from MDGAN
@Kiran
Meta:@RatingReg11
@FolderName RatingReg11
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromMDGAN' CDR
When Execute query 'Execute563duration'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageMDGAN'


Scenario: INVReg03 Printshop Invoice 3- Fixed Line & Mobile 
@Kiran
Meta:@INVReg03
@FolderName INVReg03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When select the MediaType with 'PaperMediaType'
And Goto Account Summary
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Submit the Order 'Normal Submit'
When Search The Account ''
And Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Enter Docgen details 'DocgenReport'
When Execute query 'BillNumberCapture'
When Execute query 'BillInfoTableStatus'
When Execute query 'InvoiceOdiSharedNew'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'DOCSTORE'
And Execute ODI Scenario 'DocStorewidSlot'
And Validate ODI Scenario
When Execute query 'InvoiceOdiSharedCompleted'
Given Login To Putty 'SOA01Putty' in 'Env'
Then Printshop File extraction


Scenario:NewCo_CCS11.0 CR VFUKE-1 OOB_TS01_TC01_Perform Rating Journey from UK to ROW Zone 1 and 2 when customer having international saver 100  for CBU Customer 
Meta:@RatingReg22
@FolderName RatingReg22
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver100'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'Voice60Duration535Tariff' CDR
When Execute query '501inBundle'
Then Download the EDW Usage file to local system
Given Rate a 'InternationRatingIndiaIndtSaver1' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Rate a 'InternationRatingIndiaIndtSaver2' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Rate a 'InternationRatingSaudiIntSaver' CDR
When Execute query 'ValidationBundle'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceValidationUK'


Scenario:NewCo CCS8.1.2_PBI101135_Defect 6508_FMW Change required as part of WR 42060 which will be delivered in CCS 9.0 
Meta:@RatingReg18
@FolderName RatingReg18
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromVGBDC' CDR
When Execute query 'WorldTraveller'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageVGBDC'

Scenario:NewCo CCS8.1.2_PBI101452_Defect 6509_FMW Change required as part of WR_42826 which will be delivered in CCS 9.0 
Meta:@RatingReg19
@FolderName RatingReg19
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingCallFromCOLTI' CDR
When Execute query 'WorldTraveller'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceUsageCOLTI'


Scenario:R2.4.2_CR65405_Billing_Credit Alerting_TS01_TC01_Validate that the NEW CUST CRI Subscriber will receive the CFH_DIAL1 after 60 percent and CFH_DIAL2 after 80 percent for consumer at Threshold reached and correct service type is updated 
Meta:@CAReg03 - Retrieve FL+BB account
@FolderName CAReg03
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Credit Alert 'NewCustHighCRI'
When Make Credit Alert 'FLNewCust1001'
Given Rate a 'VoiceRatingFLCA' CDR
When Execute query 'CreditAlertFixedLineDialer'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CreditAlertFixedLineDialerStatus'
When Execute query 'CreditAlertFixedLineDialerSieb'
Given Login To Putty 'SiebelPutty1' in 'Env'
When Check Alert in Siebel DB
When Execute query 'CreditAlertFixedLineSMScomplete'


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC16_Perform premium VOICE and SMS usages from Europe Zone 1
@Kiran
Meta:@RatingReg32
@FolderName RatingReg32
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'NetherlandRoamingPremiumCall' CDR
When Execute query 'NetherlandRoamingPremiumCall'
Then Download the EDW Usage file to local system
Given Rate a 'NetherlandRoamingPremiumSMS' CDR
When Execute query 'NetherlandRoamingPremiumSMS'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC25_Perform roaming MMS usages from Europe Zone 2 to Asia pacific, USA & Canada and ROW 1 & 2 zone
@Kiran
Meta: @RatingReg26
@FolderName RatingReg26
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'RoamingMMSSanMarinoToThailand' CDR
When Execute query 'RoamingMMSFromSanMarino'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingMMSSanMarinoToUSA' CDR
When Execute query 'RoamingMMSFromSanMarino'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingMMSSanMarinoToPeru' CDR
When Execute query 'RoamingMMSFromSanMarino'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingMMSSanMarinoToLebanon' CDR
When Execute query 'RoamingMMSFromSanMarino'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC40_Perform roaming MT usagess while roaming in Asia-pacific, USA & Canada zone and ROW Zone 1 & 2
@Kiran
Meta: @RatingReg27
@FolderName RatingReg27
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'MTCallFromThailand' CDR
When Execute query 'MTfromThailand'
Then Download the EDW Usage file to local system
Given Rate a 'MTCallFromCanada' CDR
When Execute query 'MTfromCanada'
Then Download the EDW Usage file to local system
Given Rate a 'MTCallFromBangaladesh' CDR
When Execute query 'MTfromBangaladesh'
Then Download the EDW Usage file to local system
Given Rate a 'MTCallFromCookIsland' CDR
When Execute query 'MTfromCookIsland'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC50_Perform roaming MO & MT VOICE,SMS and MMS usage from Aerospace to ROW zone 1
@Kiran
Meta: @RatingReg28
@FolderName RatingReg28
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'MTAerospaceCall' CDR
When Execute query 'MTAerospaceCall'
Then Download the EDW Usage file to local system
Given Rate a 'MOAerospaceCall' CDR
When Execute query 'MOAerospaceCall'
Then Download the EDW Usage file to local system
Given Rate a 'SMSAerospaceCall' CDR
When Execute query 'SMSfromAerospace'
Then Download the EDW Usage file to local system
Given Rate a 'MMSAerospaceCall' CDR
When Execute query 'MMSfromAerospace'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC47_Perform roaming MMS usages from ROW Zone 1 to UK, Europe Zone 1 & 2 and all rest of the zones
@Kiran
Meta: @RatingReg29
@FolderName RatingReg29
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'MMSBraziltoUk' CDR
When Execute query 'MMSBraziltoUk'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoGibraltal' CDR
When Execute query 'MMSBraziltoGibraltal'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoSanMarino' CDR
When Execute query 'MMSBraziltoSanMarino'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoThailand' CDR
When Execute query 'MMSBraziltoThailand'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoCanada' CDR
When Execute query 'MMSBraziltoCanada'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoBahrain' CDR
When Execute query 'MMSBraziltoBahrain'
Then Download the EDW Usage file to local system
Given Rate a 'MMSBraziltoLebanon' CDR
When Execute query 'MMSBraziltoLebanon'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC06_Perform roaming SMS usages from Europe Zone 1 to Asia pacific, USA & Canada and ROW 1 & 2 zone
@Kiran
Meta: @RatingReg30
@FolderName RatingReg30
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'SMSFinlandtoNewZealand' CDR
When Execute query 'SMSFromFinland'
Then Download the EDW Usage file to local system
Given Rate a 'SMSFinlandToCanada' CDR
When Execute query 'SMSFromFinland'
Then Download the EDW Usage file to local system
Given Rate a 'SMSFinlandToKeneya' CDR
When Execute query 'SMSFromFinland'
Then Download the EDW Usage file to local system
Given Rate a 'SMSFinlandToGreenland' CDR
When Execute query 'SMSFromFinland'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:PRReg09 VFUKE-103_DD Information Masking_TS03_Validate that DD Reversal made by agent is reflected correclty in Siebel
@Ankesh
Meta:@PRReg09
@FolderName PRReg09
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
When Execute query 'LengthValidationOfDebitNum'
When Execute query 'SiebelEncrytedValidation'
And Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentReversal 'SortCodeValidation'
And Payment Reversal Submit


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC65_Perform roaming data usages while roaming in Asia-Pacific,USA & Canada and ROW 1&2 zone on MBB subscriber
@Kiran
Meta: @RatingReg31
@FolderName RatingReg31
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidPromotionBPMBBSIMO'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDForMBB'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingDataFromAUSTA' CDR
When Execute query 'DataUsageBMU01ToUK'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingDataFromCANADA' CDR
When Execute query 'DataUsageSEA01ToUK'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingDataFromIndia' CDR
When Execute query 'DataUsageBMU01ToUK'
Then Download the EDW Usage file to local system
Given Rate a 'RoamingDataFromBhutan' CDR
When Execute query 'DataUsageSEA01ToUK'
Then Download the EDW Usage file to local system



Scenario: NewCo CCS 11.0_PBI104833_Defect 10870_Update BID info in EDW and RAID usage feed
@Kiran
Meta: @ReportReg03
@FolderName ReportReg03
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
Given Rate a 'VoiceRating' CDR
When Execute query 'BIDValueBusiness'
Then Download the EDW Usage file to local system
Given Rate a 'DomesticUKSMS' CDR
When Execute query 'BIDValueBusiness'
Then Download the EDW Usage file to local system
Given Rate a 'BI17_CDRType12' CDR
When Execute query 'BIDValueBusiness'
Then Download the EDW Usage file to local system
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'BIDValueBusiness'
Then Download the EDW Usage file to local system
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRating' CDR
When Execute query 'BIDValueConsumer'
Then Download the EDW Usage file to local system
Given Rate a 'DomesticUKSMS' CDR
When Execute query 'BIDValueConsumer'
Then Download the EDW Usage file to local system
Given Rate a 'PostpaidDataIndex2' CDR
When Execute query 'BIDValueConsumer'
Then Download the EDW Usage file to local system
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'BIDValueConsumer'
Then Download the EDW Usage file to local system


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC02_Perform international VOICE,SMS and MMS usages from UK to Europe Zone 1 & 2
Meta:@RatingReg31
@FolderName RatingReg31
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'InternationalCallFromUKtoBulgaria' CDR
When Execute query 'InternationalCallFromUKtoBulgaria'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalCallFromUKtoTurkey' CDR
When Execute query 'InternationalCallFromUKtoBulgaria'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalSMSFromUKtoBulgaria' CDR
When Execute query 'InternationalSMSFromUKtoTurkey'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalSMSFromUKtoTurkey' CDR
When Execute query 'InternationalSMSFromUKtoTurkey'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalMMSFromUKtoBulgaria' CDR
When Execute query 'InternationalMMSFromUKtoBulgaria'
Then Download the EDW Usage file to local system
Given Rate a 'InternationalMMSFromUKtoTurkey' CDR
When Execute query 'MMSfromAerospace'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceValidationUK'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToSMSUsage'
And Validate Itemised List Selection TopUp 'InternationalSMSBulgaria'
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


Scenario: NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC24_Perform roaming SMS usages from Europe Zone 2 to Asia pacific, USA & Canada and ROW 1 & 2 zone
Meta:@RatingReg39
@FolderName RatingReg39
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'EU2toTaiwanSMS' CDR
When Execute query 'EU2toOthers'
Then Download the EDW Usage file to local system
Given Rate a 'EU2toCanadaSMS' CDR
When Execute query 'EU2toOthers'
Then Download the EDW Usage file to local system
Given Rate a 'EU2toBermudaSMS' CDR
When Execute query 'EU2toOthers'
Then Download the EDW Usage file to local system
Given Rate a 'EU2toMadagascarSMS' CDR
When Execute query 'EU2toOthers'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToSMSUsage'
And Validate Itemised List Selection TopUp 'RoamingfromIsleofMan'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: PRNew01 [1]DELETED - 14188 - Finance_Test_Postpay_CCS6.0_ME3_CBU_Validate that the Refund Summary Daily and Monthly 
               report is generated in BRM as per the monthly performed refund process
@Ankesh
Meta:@PRNew01
@FolderName PRNew01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
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
Then Generate Month End Report from SBExport for 'refundSummaryReport'
Then Generate Month End Report from SBExport for 'refundCCDailyReport'


Scenario: R2.4.1_CR36487_Billing_TS06_TC01_Validate that all subscriber Emergency calls are aggregated and presented correctly on the wholesale Unmanaged MVNO partner invoice
Meta:@RatingReg33
@FolderName RatingReg33
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'EmergencyCallUK' CDR
When Execute query 'UKNGN134'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'




Scenario: AdjReg04 NewCo CCS15.0_PBI110431_Defect No 20895_FMW Change required as part of WR_49795 which will be delivered in CCS 15.0 
@Kiran
Meta:@AdjReg04
@FolderName AdjReg04
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'Voice60Duration535Tariff' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'VoiceUsageEventLevel'
And Event Level Adjustment selection 'VoiceUsageAmountCapture'
When Submit Adjustment 'SpendManagerAdjustment'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickVoiceUsageAdjButton'
When Submit Adjustment 'SpendManagerAdjustment'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'SpendManagerAdjustmentBill'
When Execute query 'AdjustmentAmountAll'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:NewCo_CCS 9.0_CR 93160_DB Encryption_TS08_Modify Paytype from Invoice to DD and verfiy if BANK_ACCOUNT_NO and SORT_CODE 
        are encrypted in Siebel and BRM DB as per AES 256 bit Encryption
@Ankesh
Meta:@PRReg01
@FolderName PRReg01
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Create Direct Debit for scenario 'DirectDebit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'DDSortCode'
When Execute query 'LengthValidationOfBankAccount'
When Execute query 'DebitnoBanknoCapture'

Scenario: BillNewReg06 NewCo_CCS 15.0_P12_EBU_Rezoning_BRM_TC58_Verify getbalance view in siebel for all VOICE,SMS and DATA bundles after usage
Meta:@BillNewReg06
@FolderName BillNewReg06
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionChildBusinessSharer'
And Customise 'RootProduct' with Econfig Item 'SharerParent'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'CDRType4CountryFRAF1RateGBP0' CDR
When Execute query '501inBundle'
Given Rate a 'CDRType2_447387912299' CDR
When Execute query '501inBundle'
Given Rate a 'CDRType5_447432134567' CDR
When Execute query '501inBundle'
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'InternationalMMSFromUKtoBulgaria'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Detail Bundle Usage Validation 'CompareAvailableBalance'
When Search The Account ''
When Execute query 'GetBalanceQuerytext'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'MSISDNFromAccount'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Detail Bundle Usage Validation 'CompareAvailableBalance'

Scenario:NewDev01 NewCo_CCS11.0_INC_PBI102238_Defect_10845_Issue with the Legal group account Sync Between Siebel and BRM
Meta:@OMReg02
@FolderName OMReg02
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Modify 'ModifyWithRootProduct' with Econfig Item 'FraudOn'
And Submit the Order 'Normal Submit'
And Complete the order by 'PerformAboutRecord'
When Execute query 'AccountStatusActive'
When ReLogin with 'ADMIN' in 'Env'
And LOV check 'LOVUpdateandCapture'
When Execute query 'UpdateDate'
When Search The Account ''
When Navigate To Jobs
And Create New Jobs 'VFAccLegalGrpStatus'
And Select the Jobs 'VFLegalGrp'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
And ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Change Payment for scenario 'LegalGrpvalidation'
When Execute query 'LegalGrpStatusSuspended'


Scenario:VSM01 NewCo_CCS 15.0_VFUKE-548_VSM_BRM_Drop_1&2_TC04_Perform international SMS usages to breach VSM limit
@Kiran
Meta:@VSM01
@FolderName VSM01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'VodafoneSpentManagerAndICCID'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'RecIdAndBalanceValidation1'
Given Rate a 'InternationalSMSUsageVSM' CDR
When Execute query 'RecIdAndBalanceValidation2'
Given Rate a 'InternationalSMSUsageVSMExhaust' CDR
When Execute query 'RecIdAndBalanceValidation3'
Given Rate a 'InternationalSMSUsage' CDR
When Execute query '501inBundle'
When Execute query 'VSMSME'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'VSMSME01'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion107785'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'VSM'
Then Check Order Status
When Execute query 'VSM04'


Scenario:VSM02 [1]NewCo_CCS 15.0_VFUKE-548_VSM_BRM_Drop_1&2_TC08_Perform late landing voice usages to breach VSM limit
@Kiran
Meta:@VSM02
@FolderName VSM02
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'VodafoneSpentManagerAndICCID'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'RecIdAndBalanceValidation1'
Given Rate a 'InternationalSMSUsageVSM' CDR
When Execute query 'RecIdAndBalanceValidation2'
Given Rate a 'InternationalSMSUsageVSMExhaust' CDR
When Execute query 'RecIdAndBalanceValidation3'
Given Rate a 'InternationalSMSUsage' CDR
When Execute query '501inBundle'
When Execute query 'VSMSME'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'VSMSME01'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion107785'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'VSM'
Then Check Order Status
When Execute query 'VSM04'


Scenario: RatingReg35 NewCo_CCS 18.0_VFUKE-546_TS01_Perfrom roaming voice usage from BT country(Andorra) to UK with SS2 promotion with product  small Business Traveller(109109)
Meta:@RatingReg35
@FolderName RatingReg35
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSS2'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'RecIdValidation'
Given Rate a 'AndorraToUKCall' CDR
When Execute query 'IND01VoiceRoaming'
When Execute query 'RecIdValAtrRating'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'AndorraToUKValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: RatingReg36 NewCo_CCS 18.0_VFUKE-546_TS10_Validate roaming SMS usage from BT country(Oman) to BT country(Nicaragua) with SS2 promotion with product small Business Traveller(109109)
Meta:@RatingReg36
@FolderName RatingReg36
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSS2'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'OmanToNicaraguaSMS' CDR
When Execute query 'OmanToNicaraguaSMS'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToSMSUsage'
And Validate Itemised List Selection TopUp 'OmanToNicaragua'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:NewCo CCS15.0_PBI111618_Defect No 18188_Spend Manager FUT - 80percent and 100percent notifications received, 
         when VSM limit set to 0 GBP
Meta:@VSM02
@FolderName VSM02
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'RecIdAndBalanceVSMOff'
When Promotion Selection 'PostpaidPromotion107785'
Then Perform the action 'ModifyPromotion'
When Update ICCID against 'VodafoneSpentManagerValue0'
And Submit the Order 'ThresholdValidation'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Show All Purchase Products in the account ''
When Execute query 'RecIdAndBalanceVSMOn'
When Execute query 'VSM0Value'
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query '501inBundle'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'VSM0ValueProcessed'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion107785'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'VSM'
Then Check Order Status
When Execute query 'VSM00'

Scenario: RatingReg37 NewCo_CCS 18.0_VFUKE-546_TS18_Perform roaming international voice call from old BT EU country(Belgium) to New BT country(Fiji) having product  Inclusive 500 International minutes (111018) and Inclusive Small business Traveller(111011) with SS2 prom_Copy_1
Meta:@RatingReg37
@FolderName RatingReg37
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionVFBLACK110861'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'International500IncCheck'
Given Rate a 'BelgiumToFiji' CDR
When Execute query '501inBundle'
When Execute query 'International500IncValidation'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'BelgiumtoFiji'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: RatingReg38 NewCo_CCS 18.0_VFUKE-546_TS33_Perform roaming international voice call from existing BT country to New sBT country having product  Inclusive 500 International minutes (111018) and  Vodafone Call Abroad - 200 minutes(104010)
Meta:@RatingReg38
@FolderName RatingReg38
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionVFBLACK110861'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad200'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'ProductDescriptionVFCallAbroad'
Given Rate a 'CANBMToARMKT' CDR
When Execute query 'International500IncValidation'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'VoiceUsageCanada'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: VSM03 NewCo_CCS 15.0_VFUKE-548_VSM_BRM_Drop_3_TC05_Validate the Ops Report generated for the parent-non paying child hierarchy account having separate bill cap limit and limit is breached
@Kiran
Meta:@VSM03
@FolderName VSM03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'VodafoneSpentManagerAndICCID'
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
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidPromotion110041'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'ICCIDandVodafoneSpentManagerValue30'
And Reserve Order
And Perform Credit Vet
And Select Billing Serviceaccount 'ServiceAccountChildAccount'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'ChildMSISDNRetrieval'
Given Rate a 'DATA30' CDR
When Execute query 'VSMChild'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'VSMChild1'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion110041'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'VSM'
Then Check Order Status
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'AlertActionReportChild'
Given Rate a 'OM03RatingRateGBP12' CDR
When Execute query 'VSMSME'
Then Generate Month End Report from SBExport for 'AlertActionReport'



Scenario: OMReg11 Newco_CCS 14.0_VFUKE-242_TS34_Validate End date set as next_bill_t for modify order when Extra opt out value set as N to Y for Technical TBO product
@Vishwa
Meta:@OMReg11
@FolderName OMReg11
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'InternationalSaver500'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'CompareEndfDate'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Perform the action 'ModifyPromotion'
When Update ICCID against 'SelectYForVodaIntsaver500'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'CaptureNextBillDate'
When Execute query 'CompareNextBillDate'