Story: FL BRM
Meta:@FLTesting
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: FL BRM
Meta:@FLBRM
Scenario: FLR001,R15.2_CR75389_Billing_Rating_TS08_TC01_Validate that Customer is able to 
          successfully place a voice terminated domestic call for CBU consumer
@Ankesh
Meta: @FLR001
@FolderName FLR001
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceRatingCallUKVP' CDR
When Execute query 'VoicetermCallCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR002, R15.2_CR75380_Billing_Rating_TS02_TC01_Customer is able to successfully place a 3waycall for consumer
@Ankesh
Meta: @FLR002
@FolderName FLR002
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a '3WAYCALLVoiceCall' CDR
When Execute query '3WaycallcallCBU'
When Execute query 'CheckGLID3waycallCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


And Retrieve Account 'FLCBUBRM'
Scenario: FLR003, R15.2_CR75380_Billing_NGN_TS02_TC01_Validate that Customer is charged at PPM rates when calling 900365XX series numbers
@Ankesh
Meta: @FLR003
@FolderName FLR003
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Voice61CallingUk' CDR
When Execute query 'Charge900365XXno(PPM)CBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLR004,R15.2_CR75380_Billing_NGN_TS05_TC01_Validate that Customer is charged at PPC+PPM rates when calling 982727XX series numbers_Start of Call
@Ankesh
Meta: @FLR004
@FolderName FLR004
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceCalling61Uk' CDR
When Execute query 'Charge982727XXno(PPM+PPCstartCall)CBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLR005,R15.2_CR75380_Billing_NGN_TS07_TC01_Validate that Customer is charged at PPC+PPM rates when calling 871395XX series numbers_After 60 seconds
@Ankesh
Meta:@FLR005
@FolderName FLR005
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceCalling62Uk' CDR
When Execute query 'Charge871395XX(PPM+PPC 60sec)CBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLR006,R15.2_CR75380_Billing_Rating_TS09_TC01_Validate that the Customer with Evening and weekend landline and 0870 calls is able to successfully place a Landline call 
          starting in peak hours and ending in off-peak hours on weekday. Call charges are correct
@Ankesh
Meta: @FLR006
@FolderName FLR006
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'CBUFLEveningWeekendCall'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Select the TimeStamp '18hr59secTuesday'
When Execute query 'TimestampCapture'
Given Rate a 'Voice120RatingTimestamp0' CDR
When Execute query 'ChargeLandLinecallCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLR007,R2.4.2_CR65405_Billing_Rating_TS23_TC01_Validate that the customer having Fixed Line service is charged 
          correctly for UK Paging number starting with 076_Consumer
@Ankesh
Meta: @FLR007
@FolderName FLR007
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Voice60Rating00447600676547' CDR
When Execute query 'ChargePagingnoCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R15.2_CR75380_Billing_Rating_TS17_TC01_Validate the customer is charged correctly when use Reminder call for Enterprise customer
@Ankesh
Meta:@FLR010
@FolderName FLR010
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'FLREMINDERCall' CDR
When Execute query 'FLReminderCall'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R011 Newco CCS 1.0_June Catalog_WR-32158_TC7_Fixed_line_CBU Rate a cdr to landline and Mobile Reverse call UK
@Ankesh
Meta:@FLR011
@FolderName FLR011
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'UKlandlineReverseCall' CDR
When Execute query 'UKlandlineReverseCall'
Given Rate a 'UKMobileReverseCall' CDR
When Execute query 'UKMobileReverseCall'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR012 R012 Newco CCS 1.0_June Catalog_WR-32158_TC2_Fixed_line_CBU Rate a cdr to 070050xxxx personal number and 008813xxxx no
@Ankesh
Meta:@FLR012
@FolderName FLR012
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a '447005012341Calling' CDR
When Execute query '447005012341Calling'
Given Rate a 'Calling881312345612' CDR
When Execute query 'Calling881312345612'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR013 Newco CCS 1.0_June Catalog_WR-32158_TC19_Fixed_line_CBU Rate a cdr to WiFi 074066xxxxxx and New Voice Service 0551107xxxxxx
@Ankesh
Meta:@FLR013
@FolderName FLR013
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'WifiCalling447406687654' CDR
When Execute query 'WifiCalling447406687654'
Given Rate a 'Voicecall445511078765' CDR
When Execute query 'Voicecall445511078765'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR014 Newco CCS 1.0_June Catalog_WR-32158_TC17_Fixed_line_CBU Rate a cdr to landline UK and Mobile UK
@Ankesh
Meta:@FLR014
@FolderName FLR014
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'Landline441234567890' CDR
When Execute query 'Landline441234567890'
Given Rate a 'UKMobiles447387944933' CDR
When Execute query 'UKMobiles447387944933'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR015 R2.4.2_CR65405_Billing_Rating_TS11_TC01_Validate that the customer 
                 having Fixed Line service is charged correctly for a international 
                 voice call to  Isle of Man- Isle of Man_EBU
@Ankesh
Meta:@FLR015
@FolderName FLR015
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'IsleOfMan441624789076' CDR
When Execute query 'IsleOfMan441624789076'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: FLR018 R15.2_CR75380_Billing_NGN_TS06_TC01_Validate that Customer is charged at PPC+PPM rates when calling 901100XX series numbers_After 60 seconds
@Ankesh
Meta:@FLR018
@FolderName FLR018
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'NGNAfter60Sec' CDR
When Execute query 'NGNAfter60Sec'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR019 R15.2_CR75380_Billing_Rating_TS14_TC01_Validate the customer is charged correctly when use  3waycall for Enterprise customer 3waycalling product
@Ankesh
Meta:@FLR019
@FolderName FLR019
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'EBUFL3WayCall'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'EBU3WAYCALL' CDR
When Execute query 'EBU3WAYCALL'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FLR020 R15.2_CR75380_Billing_Rating_TS16_TC01_Validate the customer is charged correctly when use Ring Back for Enterprise customer with Ringback product
@Ankesh
Meta:@FLR020
@FolderName FLR020
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'EBUFLRingBack'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'EBURINGBACKWithRingbackproduct' CDR
When Execute query 'EBURINGBACKWithRingbackproduct'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FLINV01 R15.2_CR75389_Billing_Rating_TS01_TC01_Validate that calls to Indian mobile Landlines are charged correctly and are differentiated in the invoice
@Ankesh
Meta:@FLINV01
@FolderName FLINV01
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'IndianMobile1' CDR
When Execute query 'IndianMobile1'
Given Rate a 'Indianlandline1' CDR
When Execute query 'Indianlandline1'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: FLINV02 R15.2_CR75389_Billing_Rating_TS07_TC01_Validate that calls to Globalstar Satellite numbers(International) are correctly charged and presented on the invoice for cosumer account
@Ankesh
Meta:@FLINV02
@FolderName FLINV02
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'FLCBUGlobalstarSatellite' CDR
When Execute query 'FLCBUGlobalstarSatellite'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLINV03 R2.4.2_CR65405_Billing_Invoicing_TS55_TC01_Validate the Usage is displayed correctly in Invoice for a customer having Fixed Line service
@Ankesh
Meta:@FLINV03
@FolderName FLINV03
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a '3WAYCALL130' CDR
When Execute query '3WaycallcallCBU'
Given Rate a 'CBUUK447387952307' CDR
When Execute query 'CBUUK447387952307'
Given Rate a '132line447387952307' CDR
When Execute query 'UKMobileReverseCall'
Given Rate a '133Indianmobile' CDR
When Execute query 'IndianMobile1'
Given Rate a 'UKNGN134' CDR
When Execute query 'UKNGN134'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: FLINV05 R2.4.2_CR65405_Billing_Invoicing_TS71_TC01_Validate the EBU customer is billed and invoice is generated at the end of billing cycle for a Customer with Fixed
@Ankesh
Meta:@FLINV05
@FolderName FLINV05
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: FLREPORT01 R15.2_CR75380_Billing_Reporting_TS03_TC01_Validate the Subscriber usage feed EDW and RAID Report for Reverse charge calls & Operator connected calls_CONSUMER
@Ankesh
Meta:@FLREPORT01
@FolderName FLREPORT01
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLCBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'OperatorCallUK' CDR
Then Download the EDW Usage file to local system
Given Rate a 'ReverseUKcallIndex1' CDR
Then Download the EDW Usage file to local system


Scenario: FLREPORT02 R2.4.2_CR65405_Billing_Reporting_TS09_TC01_Validate that the VAT Report for EBU small business is generated correctly for Fixed Line service customer
@Ankesh
Meta:@FLREPORT02
@FolderName FLREPORT02
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'vatReport'

Scenario: FLR016 R2.4.2_CR65405_Billing_Rating_TS20_TC01_Validate that the customer having Fixed Line service is 
         charged correctly for a international voice call to Zone6 -Sri Lanka_EBU
@Ankesh
Meta:@FLR016
@FolderName FLR016
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'SriLanka941176543212' CDR
When Execute query 'SriLanka941176543212'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FLR017 R2.4.2_CR65405_Billing_Rating_TS36_TC01_Validate that the customer having Fixed Line service is charged correctly for a Voice Call starts in  
          Weekend and ends in Off-Peak hours_EBU
@Ankesh
Meta:@FLR017
@FolderName FLR017
Given Pre-requisite with logs in folder 'FolderName'
--And Retrieve Account 'FLEBUBRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Select the TimeStamp 'Sunday23Hr59Sec'
When Execute query 'Timestamp2Capture'
Given Rate a 'TimestamPersonalNo' CDR
When Execute query 'OffPeakchargePersona1lNo'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'



Scenario: FLBRMBillingNew16 R15.2_CR75380_Billing_NGN_TS05_TC01_Validate that Customer is charged at PPC+PPM 
	 	  rates when calling 907375XX series numbers_Start of Call
@Vaishnavi
Meta:@FLBRMBillingNew16
@FolderName FLBRMBillingNew16
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceRatingFL' CDR
When Execute query 'VoiceRatingFL'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R2.4.2_CR65405_Billing_Bill Inquiry_TS10_TC01_Validate CC payment inquiry in the Bill Details View  
          for Fixed Line of customer with one billing profile
@Ankesh
Meta:@FLBI02
@FolderName FLBI02
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'CCAccountNBilloption'
When Execute query 'PaymentInquiryBI06'
When Execute query 'BillNumberCapture'
And ReLogin with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Payment Refund Reversal Validation 'PAYIDAllocatebalancecheck&PaymentRecurring'

Scenario: FLREPORT03 R2.4.2_CR65405_Billing_Reporting_TS01_TC01_Validate that the Payment Summary Daily report is generated in BRM on the basis of daily performed payment process.
@Vivek
Meta:@FLREPORT03
@FolderName FLREPORT03
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Then Generate Month End Report from SBExport for 'receivedPaymentsReport'
When Make Payment for 'ABCPayment'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'receivedPaymentsReportIndex1'

Scenario:FLBillingJourney01 DELETED - 18549 - Newco_CCS6.0_INC_ PBI97216_Defect_476_ Update to Staying Connected Bill adjustment Code - WR 37925 
@Ankesh
Meta:@FLBillingJourney01
@FolderName FLBillingJourney01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'HomeBroadbandPackageAdjustment'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLRatingNew27 R2.4.2_CR65405_Billing_AdjustmentsAndPayments_TS13_TC01_Validate Reversal is successful for the Fixed Line Service account_CONSUMER 
@Ankesh
Meta:@FLRatingNew27
@FolderName FLRatingNew27
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Payment for '64ABCPayment'
When Execute query 'PaymentReceivedType123'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentReversal 'ReversedPaymentRow1'
And Payment Reversal Submit
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'ReversedPaymentValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLPR02 R2.4.2_CR65405_Billing_AdjustmentsAndPayments_TS12_TC01_Validate that refund is processed successfully on the unallocated amount present on the account_CONSUMER
@Tarun
Meta:@FLPR02
@FolderName FLPR02
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
And Make Payment for 'ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'FLPR02'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'FLPR02'
When Execute query 'RetrievePendBalanceCheck'
Given Login To Putty 'BRMPutty2' in 'Env'
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLBillingNew05 Need FL EBU account-BillingNew05 R2.4.2_CR65405_Billing_Invoicing_TS71_TC01_Validate the EBU customer is billed and invoice is generated at the end of billing cycle for a Customer with Fixed line service
Meta:@FLBillingNew05
@FolderName FLBillingNew05
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: FLREPORT04 R2.4.2_CR65405_Billing_Reporting_TS03_TC01_Validate that Payment reversals are reflected in the Reversal Detailed report in BRM
@Tarun
Meta:@FLREPORT04
@FolderName FLREPORT04
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'ABCAmount0Payment'
When Execute query 'AMOUNT0CmpareABC&lockb&other'
Then Generate Month End Report from SBExport for 'detailedReversedPaymentsReport'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Click On PaymentReversal 'ReversedPaymentRow1'
And Payment Reversal Submit
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Execute query 'Capturelatestpaymnt'
And Payment Refund Reversal Validation 'FLREPORT04'
Given Login To Putty 'BRMPutty1' in 'Env'
Then Generate Month End Report from SBExport for 'detailedReversedPaymentsReport'

Scenario: FLINV04 R2.4.2_CR65405_Billing_Invoicing_TS54_TC01_Validate Bill Copy Charge is displayed correctly in Invoice for a customer having Fixed Line and Fixed Broadband services
@Tarun
Meta:@FLINV04
@FolderName FLINV04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'QP' in 'Env'
When Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'billcopyadd'
Then verify the Order line Items 'BillCopyAdd'
When Make payment with 'ONAccountNon-CardPopNO'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Account charge Apply
And Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLADJ01 R2.4.2_CR65405_Billing_AdjustmentsAndPayments_TS03_TC01_Adjustment reason description to
appear on the invoice of Fixed Line service account.  The adjustment is created against the open bill at the item level with VAT level as in tax config file_Consumer
@Tarun
Meta:@FLADJ01
@FolderName FLADJ01
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'FLADJ01' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickVoiceUsageAdjButton'
When Submit Adjustment 'ItemChargeGoodwillAdjustment'
When Execute query 'Adjustment1EuroAdd'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: FLR001,R15.2_CR75389_Billing_Rating_TS08_TC01_Validate that Customer is able to 
          successfully place a voice terminated domestic call for CBU consumer
@Ankesh
Meta: @FLR001
@FolderName FLR001
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion106420'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidChildSharer106445'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
And Execute query 'MSISDNFromAccount'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'NormalVoiceRating' CDR
And Rate a 'DomesticRoaming' CDR
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: FLPR01 DELETED - 12378 - R2.4.2_CR65405_Billing_AdjustmentsAndPayments_TS10_TC01_Validate that Recurring Credit Card payment is taken using the details 
          present in the customer account_Consumer
@Pankaj
Meta:@FLPR01
@FolderName FLPR01
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
When Make Payment for 'CCAccountNBilloption'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Validate Siebel Payment 'RecurringPayValidate'


Scenario: FLR001,R2.42._CR65405_Billing_Bill inquiry_TS02_TC01_Validate the Customer Bill Profile Balance 
          Summary View at bill profile level for a Fixed line Customer
@Ankesh
Meta: @FLBI01
@FolderName FLBI01
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceRatingCallUKVP' CDR
When Execute query 'VoicetermCallCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLPaymentRefunds DELETED - 12378 - R2.4.2_CR65405_Billing_AdjustmentsAndPayments_TS10_TC01_Validate that Recurring Credit Card payment is taken using the details 
          present in the customer account_Consumer
@Pankaj
Meta:@FLPaymentRefunds
@FolderName FLPaymentRefunds
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Payment for 'CCAccountNBilloption'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Validate Siebel Payment 'RecurringPayValidate'


Scenario: FLNewReg01 CR111910_BRM_TS01_Validate the invoice PDF when the modify order is triggered to add sync speed discount
@Ankesh
Meta: @FLNewReg01
@FolderName FLNewReg01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'QP' in 'Env'
When Search The Account ''
And Modify 'FixedLineFBB' with Econfig Item 'UltSpeedGuarDiscount'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'ProductValidation'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:FLNewReg02 CCS 6.0_WR37925_TS01_TC01_Agent perform All Types of Adjustment with reason code Conv_FLBB Keep Connected on FLBB account
@Ankesh
Meta: @FLNewReg02
@FolderName FLNewReg02
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'Adj9000secsVoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'HomeBroadbandAdjustment'
When Execute query 'TaxCode'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'ClickVoiceUsageAdjButton'
And Submit Adjustment 'HomeBroadbandNoAdjustment'
When Execute query 'TaxCode'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DrillDownToNetAmount'
And Event Level Adjustment selection 'VoiceValidationAdj'
And Submit Adjustment 'HomeBroadbandNoAdjustment'
When Execute query 'TaxCode'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:FLNewReg03 CR111910_BRM_TS02_Validate the Invoice PDF after removing the sync speed Discount when speed is equal or more than promised speed
@Ankesh
Meta: @FLNewReg03
@FolderName FLNewReg03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'QP' in 'Env'
When Search The Account ''
And Modify 'FixedLineFBB' with Econfig Item 'UltSpeedGuarDiscount'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'ProductValidation'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'
And Login with 'QP' in 'Env'
When Search The Account ''
And Modify 'FixedLineFBB' with Econfig Item 'UltSpeedGuarDiscountOff'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: FLBillingNew15 R15.2_CR75380_Billing_NGN_TS05_TC01_Validate that Customer is charged at PPC+PPM rates when 
         calling 907375XX series numbers_Start of Call
@Ankesh
Meta:@FLBillingNew15
@FolderName FLBillingNew15
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'FLVoiceRating' CDR
When Execute query '907375call'
Given Rate a 'FLVoiceRatingInd1' CDR
When Execute query '907375callindx1'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: FLCAReg03 R2.4.2_CR65405_Billing_Credit Alerting_TS01_TC01_Validate that the NEW CUST CRI Subscriber will receive the CFH_DIAL1 after 60 percent and CFH_DIAL2 after 80 percent for consumer at Threshold reached and correct service type is updated 
Meta:@FLCAReg03 - Retrieve FL+BB account
@FolderName FLCAReg03
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Credit Alert 'NewCustHighCRI'
When Make Credit Alert 'FLNewCust1001'
Given Rate a 'IndianMobileCA' CDR
When Execute query 'CreditAlertFixedLineDialer'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CreditAlertFixedLineDialerStatus'
When Execute query 'CreditAlertFixedLineDialerSieb'
When Execute query 'CreditAlertFixedLineSMScomplete'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
Then Verfiy Customer Comms 'CA05'