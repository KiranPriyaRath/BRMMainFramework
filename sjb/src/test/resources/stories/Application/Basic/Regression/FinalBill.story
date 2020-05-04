Story: BRM FinalBill
Meta:@FB
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: FB01 NewCo_CCS 15.0_VFUKE-337 FInal Bill_Siebel_Drop3_TC01_Validate that the parent and paying child account status changing from Cancelled to Inactive via RCR
Meta:@FB01
@FolderName FB01
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Validate Contacts tab for 'DrillDownToLastName'
And create new child account 'ClickOnNew'
And create new child account 'Rowno2'
And create new child account 'Rowno3'
And create new child account 'SelectAddressforChildAccount'
And search the child account ''
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Create New Billing Profile 'NewPostpaid'
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110041'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
When Submit the Order 'SubmitForDisconnectionforCollectionsJourney'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
Then Perform the action 'DisconnectwithPromotion'
When Make payment with 'ONAccountNon-CardPopNO'
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
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'PayingChildRetrievalCapture'
When Execute query 'BillingStatusFinalBillStatusParentChild'
And Final bill generation for 'ChildAccount'
When Execute query 'BillingFinalBillStatusChildComplete'
And Final bill generation for 'ParentAccount'
When Execute query 'BillingStatusFinalBillStatusComplete'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

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

Scenario: FB02 NewCo_CCS 14.0_VFUKE-38 - GDPR -Customer Journey Enhancement Final Bill_TS33_Generate Final bill for Indirect partners account
Meta:@FB02 -- VPS account
@FolderName FB02
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
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
When Execute query 'Account&Billingstatus'
When Execute query 'AccountRowIdCapture'
When Execute query 'SysdateRowupdateNew'
When ReLogin with 'ADMIN' in 'Env'
When Navigate To Jobs
And Create New Jobs 'PopVFChangesDisconnect'
And Select the Jobs 'CaptureDisconnectValue'
And Create New Jobs 'CaptureJobId'
And Create New Jobs 'QueryWithJobId'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
And Final bill generation for 'ParentAccount'
When Execute query 'BillingStatusFinalBillStatusComplete'
When Execute query 'FinalBill'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: FB03 NewCo_CCS 15.0_VFUKE-337 FInal Bill_BRM_Drop2_TC04_Execute MTA reconnect job where reconnection is happened before final bill generation and final bill is scheduled on next BDOM
Meta:@FB03
@FolderName FB03
@Kiran
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
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion107785'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
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
When Execute query 'BillingStatusFinalBillStatus'
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ReconnectWithRootProd'
When Submit the Order 'SubmitForReconnectionJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Account&Billingstatus'
And Execute query 'BillingStatusFinalBillStatus'
And Run Reconnection utility
And Execute query 'BillingStatusFinalBillStatusReject'
And Final bill generation for 'ParentAccount'
When Execute query 'NoFinalBill'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FB04 TESTCYCL_277846_Validate the invoicing for the bill generated post final bill generation having additional 
          charge due to credit or debit adjustment
Meta:@FB04
@FolderName FB04
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
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
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
And Final bill generation for 'ParentAccount'
When Execute query 'BillingStatusFinalBillStatusComplete'
When Execute query 'FinalBill'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'ABCAmount0Payment'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'MaxBillB1'
Then Perform validation for 'DrillDownToBillingProfile' in Profile's Tab Billing profile View
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ItemLevelCycleForwardAdjustment'
When Submit Adjustment 'GoodWillItemLevel'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'BillstatusValidation'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'MaxBillB1'
When Execute query 'InvoicePoid'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoicePoidAfterInvoice'


Scenario: FB05 NewCo_CCS 14.0_VFUKE-38 - GDPR -Customer Journey Enhancement Final Bill_TS24_Validate Refund Export report for DD CBU account for final bill generated with credit amount less then 1000,run mta_dd_payment utility with refund mode
Meta:@FB05
@FolderName FB05 - Extract DD refund report the next day
@Kiran
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
When Make Payment for 'ABCPayment500'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Retrieve Promotion 'PostpaidPromotion107785'
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
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
And Final bill generation for 'ParentAccount'
When Execute query 'BillingStatusFinalBillStatusComplete'
When Execute query 'FinalBill'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillstatusValidation'
And Run mta payment Refund
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'AccountBillDetail'
Then Perform validation for 'DrillDownToBillingProfile' in Profile's Tab Billing profile View
When Payment Refund Reversal Validation 'FinalBillCreditCheck'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'DDRefundsExport'


Scenario: FB06 NewCo_CCS 15.0_VFUKE-337 FInal Bill_BRM_Drop2_TC05_Execute MTA reconnect job for the account where final bill generation is failed and reconnection is happened
Meta:@FB06
@FolderName FB06
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
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
When Execute query 'BillingStatusFinalBillStatus'
When Execute query 'UpdateScheduletStatus'
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ReconnectWithRootProd'
When Submit the Order 'SubmitForReconnectionJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Account&Billingstatus'
And Execute query 'BillingStatusFinalBillStatusError'
And Run Reconnection utility
And Execute query 'BillingStatusFinalBillStatusReject'
And Final bill generation for 'ParentAccount'
When Execute query 'NoFinalBill'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FB07 NewCo_CCS 15.0_VFUKE-337 FInal Bill_BRM_Drop1_TC01_Validate invoicing should be get suppressed in BRM after final bill generation for zero bill amounts only
Meta:@FB07
@FolderName FB07
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'VFREDUSR' in 'Env'
When Create the account type 'PostpaidConsumer'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization' and Create New 'NewOrderNoPopUp' Order
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
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidPromotion110043emp'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
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
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
And Final bill generation for 'ParentAccount'
When Execute query 'BillingStatusFinalBillStatusComplete'
When Execute query 'FinalBill'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillstatusValidation'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'MaxBillB1'
When Execute query 'BillstatusValidation'
Then Perform validation for 'DrillDownToBillingProfile' in Profile's Tab Billing profile View
When DrillDown to Latest Bill 'CheckBillNotPresent'

Scenario: FB08 NewCo_CCS 15.0_VFUKE-337 FInal Bill_BRM_Drop2_TC01_Execute MTA reconnect job where reconnection is happened before final bill generation and BDOM ( WHEN_T is not BDOM )
Meta:@FB08 - Dont execute on same day when injection is done
@FolderName FB08
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
When Retrieve Promotion 'PostpaidPromotion107785'
When Modify with owned product 'DisconnectwithPromotion'
And Make payment with 'CreditDebitCardAuthorisepaymentwithCVV'
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
When Execute query 'BillingStatusFinalBillStatus'
And Retrieve Promotion 'PostpaidPromotion107785'
And Modify with owned product 'ReconnectWithRootProd'
When Submit the Order 'SubmitForReconnectionJourney'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Account&Billingstatus'
And Execute query 'BillingStatusFinalBillStatus'
And Run Reconnection utility
And Execute query 'BillingStatusFinalBillStatusReject'
And Final bill generation for 'ParentAccount'
When Execute query 'NoFinalBill'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: FB09 NewCo_CCS 14.0_VFUKE-38 - GDPR -Customer Journey Enhancement Final Bill_TS26_Generate Final Bill with credit amount less then 
			1000 after BDOM for Bill me EBU account,update Bill me to CC paytype and perform refund through utility
Meta:@FB09
@FolderName FB09
@Kiran
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
When Retrieve Promotion 'PostpaidPromotion107785'
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
When Make Payment for 'ABCPayment500'
And Final bill generation for 'ParentAccount'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'FinalBill'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Navigate to Profile Page and perform 'AboutRecord'
Then Perform validation for 'RunQuery' in Profile's Tab Billing profile View
When Execute query 'Tokenization'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'CCPayType'
Then Generate Month End Report from SBExport for 'CCRefundsExport'
When Execute query 'AccountBillDetail'
When Run ccReport from Putty
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Perform validation for 'DrillDownToBillingProfile' in Profile's Tab Billing profile View
When Payment Refund Reversal Validation 'FinalBillCCRefundCheck'

Scenario: FB10 CCS 19.8_VFUKE-8395_Build Ledger Billing Capability_TS41_Validate final bill generated for the Major account with credit due amount and payment method is CC
Meta:@FB10
@Kiran
@FolderName FB10
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'APPDIRECT' in 'Env'
When Create the account type 'SmallBusinessMajorPublicOnline'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'VCSMProduct111902'
And Update ICCID against 'ValidateSequenceIDagainstVCSM'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Perform the 'AboutRecordOfMenuItem' with About Record
Then Perform 'RunQueryOfMenuItem' and Execute query 'Tokenization'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Make Payment for 'ABCPayment12'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Retrieve Promotion 'VCSMProduct111902'
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
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'Accnt&Billafterinactive'
When Execute query 'BillingStatusFinalBillStatus'
And Final bill generation for 'ParentAccount'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'FinalBill'
When Execute query 'AccountBillDetail'
Then Generate Month End Report from SBExport for 'CCRefundsExport'
When Run ccReport from Putty
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Perform validation for 'DrillDownToBillingProfile' in Profile's Tab Billing profile View
When Payment Refund Reversal Validation 'FinalBillCCRefundCheck'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'RecentBillCapture'
When Execute query 'InvoicePoidSupressed'