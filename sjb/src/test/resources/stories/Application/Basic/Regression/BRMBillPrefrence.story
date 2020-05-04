Story:BRM Bill Pref
Meta:@BillPref
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

 
Scenario: BILLPREF01 NewCo_CCS 2.0_CR99884_Billing_TS03_TC01_Verify in Invoice List Applet, columns and respective values are displayed correctly
@Ankesh
Meta:@BILLPREF01
@FolderName BILLPREF01
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
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'AccountBillDetail'
And DrillDown to Latest Bill 'CaptureTotalBill'

Scenario: BILLPREF02 NewCo_CCS 2.0_CR99884_Billing_TS07_TC01_Perform Billing Inquiry for over payment made on bill
@Ankesh
Meta:@BILLPREF02
@FolderName BILLPREF02
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
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'AMOUNT0Other'
When Make Payment for 'Lockbox100Payment'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'Capturelatestpaymnt'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'Capturpayidunallocatpaymnt'
And Payment Refund Reversal Validation 'UnAllocatedPAYMENTID'