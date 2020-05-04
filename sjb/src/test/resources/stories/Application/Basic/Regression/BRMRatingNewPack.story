Story: RatingNewPack
Meta:@RatingNewPackBulk
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: BRMRatingNew1 CCS 7.0_CR 108215_TS01_Billing_TC01_Validate that Youth CDR (Opt-in) 
          processed by MZ is rated successfully in BRM
@Vaishnavi
Meta:@BRMRatingNew1
@FolderName BRMRatingNew1
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'YouthCDR_Opti_Rating' CDR
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Validate usage list Services 'DrillDownToMsisdn'
And Validate Usage List Summary Charges 'DrillDownNetAmountUsage'
And Validate Itemised List Selection TopUp 'AutoRenewal'



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


Scenario: BRMRatingNew3 CCS 7.0_CR 109447_TS04_Billing_TC01_ Validate customer is charged at domestic rate 
		  for MO Voice usage originating from EuZone1(Croatia) to EUZone2 (Isle of Man),SMS usage from 
		  EuZone2(San Marino) to EUZone1(Gibraltar)
@Vaishnavi
Meta:@BRMRatingNew3
@FolderName BRMRatingNew3
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'EuZone1ToEUZone2Rating' CDR
When Execute query 'EuZone1ToEUZone2'
Given Rate a 'EuZone1ToEUZone2SMSUsage' CDR
When Execute query 'EuZone1ToEUZone2'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew4 CCS 7.0_CR 109447_TS07_Billing_TC01_Validate charges for voice call received in EU Zone1 (Malta)
@Vaishnavi
Meta:@BRMRatingNew4
@FolderName BRMRatingNew4
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'EUZone1Rating' CDR
When Execute query 'EuZone1ToEUZone2'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew5 CCS 7.0_CR 109447_TS08_Billing_TC01_Validate customer is charged as per International 
		  rate for SMS usage made from EUZone2(Turkey) to ROW(Cameroon)
@Vaishnavi
Meta:@BRMRatingNew5
@FolderName BRMRatingNew5
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer107785BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'EUZone2ToROWRating' CDR
When Execute query 'EUZone2ToROW'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
When Validate Itemised List Selection TopUp 'UsageToRoamingSMSandMMS'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew6 CCS 7.0_CR 109447_TS23_Billing_TC01_Validate calls made from Eu Zone2(Bosnia and Herzegovina) to 0800 and 0808 numbers are free
@Vaishnavi
Meta:@BRMRatingNew6
@FolderName BRMRatingNew6
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'BosniaToUKRating' CDR
When Execute query 'BosniaToUKRating'
Then Download the EDW Usage file to local system
Given Rate a 'BosniaToUKRatingIndex1' CDR
When Execute query 'BosniaToUKRating'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew7 CCS 7.0_CR 109447_TS33_Billing_TC01_ Validate Customer is charged at international 
		  rate when voice usage is made from Gibraltar EUZONE 1 to ROW Aruba
@Vaishnavi
Meta:@BRMRatingNew7
@FolderName BRMRatingNew7
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'GibraltarEUZONE1ToROWArubaRating' CDR
When Execute query 'GibraltarEUZONE1ToROWAruba'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Validate Itemised List Selection TopUp 'VoiceRoamingMinutes'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew8 NewCo_CCS 10.0_CR 111120_TS22_Validate correct charges and VAT is applied to the 
		  customer for roaming voice from EU Zone2 to Row Zone1
@Vaishnavi
Meta:@BRMRatingNew8
@FolderName BRMRatingNew8
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'EU2ZonetoROW1' CDR
When Execute query 'InternatVoiceUktoInd'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew10 CCS 7.0_CR 109447_TS10_Billing_TC01_Validate when customer perform voice usage 
		  from EU zone(Sweden) then usage is deducted first from roaming allowance and then from domestic 
		  bundle
@Vaishnavi
Meta:@BRMRatingNew10
@FolderName BRMRatingNew10
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'SwedenToUKRating' CDR
When Execute query 'EUZoneEEAtoUK'
Then Download the EDW Usage file to local system
Given Rate a 'SwedenToUKSMSUsageRating' CDR
When Execute query 'SwedenToUK'
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
When Execute query 'PrepayRatingCount1'
Given Rate a 'LatviaToUKSMSUsageRating' CDR
When Execute query 'PrepayRatingCount2'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdnPrepay'
And Unbilled Type of Usage Adjustment selection 'DrillDownToPrepayUsage'
When Event Level Adjustment selection 'SMSUsagePrepay'
When Event Level Adjustment selection 'VoiceUsagePrepay'


Scenario: BRMRatingNew12 CCS 7.0_CR 109447_TS21_Billing_TC01_Validate when voice usage made from Eu Zone2
		  (Switzerland) to non standard uk number(Premium no(09)),Personal numbers (07,05),Radio paging 
		  (076),customer is charged at roaming rate
@Vaishnavi
Meta:@BRMRatingNew12
@FolderName BRMRatingNew12
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'SwitzerlandToUKRating' CDR
When Execute query 'SwitzerlandToUK'
Given Rate a 'FranceToUKRating' CDR
When Execute query 'FranceToUK'
Given Rate a 'SwitzerlandToUKRatingIndex2' CDR
When Execute query 'SwitzerlandToUKIndex2'
Given Rate a 'SwitzerlandToRadiono' CDR
When Execute query 'FranceToUK'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew13 CCS 7.0_CR 109447_TS27_Billing_TC01_Validate charges for Voice usage made from 
		  ROW(Barbados) to EUZone1(Romania) for customer having Inc Business Traveller Euro (109109)
@Vaishnavi
Meta:@BRMRatingNew13
@FolderName BRMRatingNew13
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSOHO110300'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
Given Generate First Bill
And Show All Purchase Products in the account ''
When Execute query 'ProductDescriptionBT'
Given Rate a 'BarbadosToRomaniaRating' CDR
When Execute query 'BarbadosToRomania'
Then Download the EDW Usage file to local system
When Execute query 'ProductDescriptionBT60min'
Given Rate a 'BarbadosToRomaniaRatingIndex1' CDR
When Execute query 'BarbadosToRomaniaIndex1'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: BRMRatingNew14 CCS 7.0_CR 109447_TS37_Billing_TC01_ Validate Customer doing data usage in ROW
		  (Haiti) is covered under Global Data Traveller 1GB(109505)----Surepay validation
@Vaishnavi---product is not added yet login issue
Meta:@BRMRatingNew14
@FolderName BRMRatingNew14
Given Pre-requisite with logs in folder 'FolderName'
And Login with '' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion109500'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'HaitiRoamingRating' CDR
When Execute query 'HaitiRoaming'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew18 NewCo_CCS 10.0_CR 111120_TS01_Validate correct charges and VAT is applied to the 
		  customer for roaming voice call from ROW zone1 to ROW Zone1
@Vaishnavi
Meta:@BRMRatingNew18
@FolderName BRMRatingNew18
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'Zone1ToZone1Rating' CDR
When Execute query 'Zone1ToZone1'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew19 NewCo_CCS 10.0_CR 111120_TS02_Validate correct charges and VAT is applied to the customer for roaming voice call from ROW zone2 to ROW Zone2
@Vaishnavi
Meta:@BRMRatingNew19
@FolderName BRMRatingNew19
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'Zone2ToZone2Rating' CDR
When Execute query 'Zone2ToZone2'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew20 NewCo_CCS 10.0_CR 111120_TS03_Validate correct charges and VAT is applied to the 
		 	customer for roaming sms from ROW zone3 to ROW Zone3
@Vaishnavi
Meta:@BRMRatingNew20
@FolderName BRMRatingNew20
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'TMTTAR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionBusinessPremier'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'Zone3ToZone3Rating1' CDR
When Execute query 'Zone3ToZone3'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew21 NewCo_CCS 10.0_CR 111120_TS04_Validate correct charges and VAT is applied to the customer for roaming mms from ROW zone4 to ROW Zone4
@Vaishnavi
Meta:@BRMRatingNew21
@FolderName BRMRatingNew21
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'Zone4ToZone4Rating1' CDR
When Execute query 'Zone4ToZone4'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew22 NewCo_CCS 10.0_CR 111120_TS16_Validate correct charges and VAT is applied to the 
		  customer for roaming call from ROW zone4 to EU2
@Vaishnavi
Meta:@BRMRatingNew22
@FolderName BRMRatingNew22
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'Zone4 to EU2Rating' CDR
When Execute query 'Zone4 to EU2'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: BRMRatingNew23 NewCo_CCS 10.0_CR 111120_TS19_Validate correct charges and VAT is applied to the 
		  customer for roaming voice call from EU Zone EEA (Norway) to UK
@Vaishnavi
Meta:@BRMRatingNew23
@FolderName BRMRatingNew23
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'EUZoneEEAtoUKRatingexhaust' CDR
When Execute query 'EUZoneEEAtoUK'
Then Download the EDW Usage file to local system
Given Rate a 'EUZoneEEAtoUKRating' CDR
When Execute query '501outBundle12'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: BRMRatingNew24 NewCo_CCS 10.0_CR 111120_TS23_Validate correct charges and VAT is applied to the 
		  customer for roaming call from Aerospace network
@Vaishnavi
Meta:@BRMRatingNew24
@FolderName BRMRatingNew24
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'MSISDNFromAccount'
Given Generate First Bill
Given Rate a 'AerospacenetworktoIndiaRating' CDR
When Execute query 'AerospacenetworktoIndia'
Then Download the EDW Usage file to local system
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



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
When Execute query 'InternationalVoiceUsageToTariff535Amount6'
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

Scenario: NewCo_CCS 9.0_CR 93160_Additional_Perform DD refund after pay type changes to Bill Me or CC from DD to validate refund SBExport reports
@Ankesh
Meta:@PaymentRefund01-need to run - extract report the next day
@FolderName PaymentRefund01
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
When Execute query 'LengthValidationOfDebitNum'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Make Payment for 'LockBoxPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And Change Payment for scenario 'DirectDebitToBillMe'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount_AMOUNT0'
And Enter Refund Details 'CreditCardRefund'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
Then Generate Month End Report from SBExport for 'DDRefundsExport'

Scenario: RatingNewScript CCS7.0_Defect 2663_PBI000000100447_Domestic Add on bundles are not working with VF Global traveler while roaming
@Ankesh
Meta:@RatingNewScript
@FolderName RatingNewScript
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra60minOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'ProductDescription60min'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'INDUKRoamingCallIndx0' CDR
When Execute query 'ComparingtheRating3540Value'
Given Rate a 'VoiceRatingFrmUSAToFrance' CDR
When Execute query 'ComparingtheRating3480Value'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BillingJourney01 NewCo_INC_PBI000000103262_Calls made in Dominica to Dominica numbers not covered by Global roaming (001767)
@Ankesh
Meta:@BillingJourney01
@FolderName BillingJourney01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
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
Given Rate a 'DominciaRating' CDR
When Execute query 'WorldTraveller'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BillingJourney02 NewCo_INC_PBI000000103058_Calls made in Suriname to Suriname numbers not covered by Global roaming (001597)
@Ankesh
Meta:@BillingJourney02
@FolderName BillingJourney02
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'SurinameRating' CDR
When Execute query 'WorldTraveller'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:BillingJourney03 NewCo_INC_PBI000000103073_PBI000000103073 Incorrect charging for calls to UK Non stand nos while roaming in Roam further countries with GT
@Ankesh
Meta:@BillingJourney03
@FolderName BillingJourney03
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'NGNRoaming' CDR
When Execute query 'NGNGlobalCharges'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:RatingNew26 R15.2_CR75380_Billing_Bill inquiry_TS01_TC01_Validate that the Agent is able to view the event level billed usage view in Siebel GUI 
@Ankesh
Meta:@RatingNew26
@FolderName RatingNew26
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Given Rate a 'DomesticUKSMS' CDR
When Execute query '501inBundle'
Given Rate a 'InternationalUKtoIndiaVoice' CDR
When Execute query 'InternatVoiceUktoInd'
Given Rate a 'SwitzerlandToRadiono' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToSMSandMMS'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToVoiceUsageClickNo'
When Event Level Adjustment selection 'UKVoiceValidation'

Scenario: CCS9.1.2_PBI103210_Incorrect value for fieldBUNDLE_NEWCO_ID_1 in brm_po_data
@Ankesh
Meta:@RatingNew35-need to validate
@FolderName RatingNew35
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
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
When Execute query 'MSISDNFromAccount'
Given Rate a 'InternationalCallfromUktoInd' CDR
Then Download the EDW Usage file to local system
When Execute query 'PoidNo&ProductName'


Scenario: NewCo CCS14.0_Defect _PBI109626_FMW Change required as part of WR_49162 which will be delivered in CCS 13.1
@Ankesh
Meta:@RatingReg34
@FolderName RatingReg34
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'CodsaVoiceRating' CDR
When Execute query 'WorldTraveller'
Given Rate a 'MTXVoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
When Validate Usage List Summary Charges 'DrillDownToVoiceUsage'
And Validate Itemised List Selection TopUp 'DR&LuxembourgValidation'


Scenario: RatingReg40 NewCo CCS19.10_VFUKE-3983_TS04_Perform top up and check get balance view for prepay 
@Vishwa
Meta:@RatingReg40
@FolderName RatingReg40
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PrepaidConsumerBrm'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'TopUpCredit' CDR
When Execute query 'PrepayRatingCount1'
When Execute query 'CheckEventBalImpactTPrepay'
When Execute query 'CheckEventTPrepay'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then TopUpHistoryValidation 'TopUpCredit'


Scenario: RatingReg41 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS02_TC10_Perform International voice usages for account having black tariff promotion and Vodafone Call Abroad - 200 minutes
@Vishwa
Meta:@RatingReg41
@FolderName RatingReg41
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSOHO110860'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad200Check'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'ROWToBhutanVoiceUsageInBundle' CDR
When Execute query '501inBundle'
When Execute query 'CheckVodafoneCallAbroad200Mints'
Given Rate a 'ROWToBhutanVoiceUsagePartial' CDR
When Execute query 'VoiceUsageROWToBhutanPartial'
When Execute query 'CheckVodafoneCallAbroad200MintsExhausted'
Given Rate a 'ROWToBhutanVoiceUsageOutOfBundle' CDR
When Execute query 'VoiceUsageROWToBhutanPartial'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckUsageMintsForInternationalMintsAndVodaneCallAbroad200'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UKVoiceValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg42 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS03_TC30_Perform Roaming voice usages from Raom-free zone to other zones with customer having Vodafone Black International + Vodafone Call Abroad - 75 minutes
@Vishwa
Meta:@RatingReg42
@FolderName RatingReg42
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSOHO110860'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad75MinsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'ExtraVodfone75MinsBeforeUsage'
Given Rate a 'DomesticRoamingVoiceGermanytoPoland' CDR
When Execute query '501inBundle'
Given Rate a 'InternatioanalRoamingVoiceBelgiumToIndia' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckUsageMintsForInBundle'
When Minute And SMS Bundle Usage Validation 'CheckSMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBal'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'InternatioanalRoamingVoiceItalyToBhutan' CDR
When Execute query '501inBundle'
When Execute query 'ExtraVodfone75MinsInBundle'
Given Rate a 'InternatioanalRoamingVoiceFranceToBhutan' CDR
When Execute query 'VoiceUsagefranceToBhutan'
When Execute query 'ExtraVodfone75MinsOutBundle'
Given Rate a 'InternatioanalRoamingVoiceFranceToBhutanOutBundle' CDR
When Execute query 'VoiceUsagefranceToBhutan'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckUsageMintsForInBundleAfterUsage'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UKVoiceValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario:  RatingReg43 CCS 19.8 WR60960_VF Black MMS Changes_TS01_TC01 Perform domestic MMS usage (Long text) to UK number
@Vishwa
Meta:@RatingReg43
@FolderName RatingReg43
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSOHO110860'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Execute query 'MSISDNFromAccount'
When Execute query 'GetBalanceQueryMMSBeforerating'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckMinutesUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckSMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBal'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'MMSUsageUKtoUK' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'GetBalanceQueryMMSAftererating'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBalAfterRating'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToRoamingSMSandMMS'


Scenario: RatingReg44 CCS 19.8 WR60960_VF Black MMS Changes_TS01_TC06 Perform international MMS usage from UK to Channel Islands.
@Vishwa
Meta:@RatingReg44
@FolderName RatingReg44
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotionSOHO110860'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Execute query 'MSISDNFromAccount'
When Execute query 'GetBalanceQueryMMSBeforerating'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckMinutesUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckSMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBal'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'MMSUsageUKtoChannelsIslands' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'GetBalanceQueryMMSAftererating'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBalAfterRating'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToRoamingSMSandMMS'



Scenario: RatingReg45 CCS 19.8 WR60960_VF Black MMS Changes_TS01_TC18 Perform roaming MMS usage from Roam-further zone to Roam-free zone.
@Vishwa
Meta:@RatingReg45
@FolderName RatingReg45
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidVFBLACK110860'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Execute query 'MSISDNFromAccount'
When Execute query 'GetBalanceQueryMMSBeforerating'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckMinutesUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckSMSUnitUnlimandAvalBal'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBal'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'MMSUsageChinaToGreece' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'GetBalanceQueryMMSAftererating'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckMMSUnitUnlimandAvalBalAfterRating'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToRoamingSMSandMMS'


Scenario: RatingReg50 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS01_TC07_Perform domestic data usage in UK
@Vishwa
Meta:@RatingReg50
@FolderName RatingReg50
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidVFBLACK110860'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBal'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'DomesticDataUsage' CDR
When Execute query '501inBundle'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckGBUnitUnlimandAvalBalUsage'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToMobileInternet'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingReg51 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS01_TC10_Perform domestic voice usage to NGN number having charge type PPM+PPC
@Vishwa
Meta:@RatingReg51
@FolderName RatingReg51
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidVFBLACK110860'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceUsageToTariff564' CDR
When Execute query 'CheckPriceforVoiceUKToNGN'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageTo08or09NumbersClickNo'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg52 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS03_TC29_Perform Roaming voice usages from ROW zone to other zones with customer having Vodafone Black International + Vodafone Call Abroad - 75 minutes
@Vishwa
Meta:@RatingReg52
@FolderName RatingReg52
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidVFBLACK110860'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad75MinsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Execute query 'MSISDNFromAccount'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckUsageMints'
When Execute query 'CheckInternationalMinutesandSMS1'
When Execute query 'ExtraVodfone75MinsBeforeUsage'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'InternatioanalRoamingVoiceAlgeriaToTurkey' CDR
When Execute query 'CheckPriceforVoiceAlgeriaToTurkey'
Given Rate a 'InternatioanalRoamingVoiceIraqToChina' CDR
When Execute query 'CheckPriceforVoiceIraqToChina'
Given Rate a 'InternatioanalRoamingVoiceIraqToAlgeria' CDR
When Execute query 'CheckPriceforVoiceIraqToAlgeria'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
When Bundle Usage Validation 'DrillDownToMSISDN'
When Minute And SMS Bundle Usage Validation 'CheckUsageMints'
When Execute query 'CheckInternationalMinutesandSMS1'
When Execute query 'ExtraVodfone75MinsBeforeUsage'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToRoamingCalls'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: RatingReg61 CCS17.0_VFUKE-3631_VSM_BRM_TS05_TC01_Validate RT NGN voice usage PPM Only rated in BRM
@Vishwa
Meta:@RatingReg61
@FolderName RatingReg61
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRatingUKtoNGNPPM' CDR
When Execute query 'CheckPriceforNGNPPMRating'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UKVoiceValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg62 CCS17.0_VFUKE-3631_VSM_BRM_TS05_TC04_Validate RT NGN voice usage PPM+PPC after 1min rated in BRM
@Vishwa
Meta:@RatingReg62
@FolderName RatingReg62
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRatingUKtoNGNPPM+Plus' CDR
When Execute query 'CheckPriceforNGNPPMplusPPCRating'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UKVoiceValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg63 CCS17.0_VFUKE-3631_VSM_BRM_TS05_TC05_Validate RT NGN voice usage (in bundle, partial bundle and out bundle ) rated in BRM with 300 mins to 084 and 087 product
@Vishwa
Meta:@RatingReg63
@FolderName RatingReg63
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Customise 'RootProduct' with Econfig Item 'Extra300mins084to087'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRatingUKtoNGN' CDR
When Execute query 'Extra300Minsto084and087InBundle'
When Execute query '501inBundle'
Given Rate a 'VoiceRatingUKtoNGN1' CDR
When Execute query 'Extra300Minsto084and087InPartial'
When Execute query 'CheckPricefor300minsInpartial'
Given Rate a 'VoiceRatingUKtoNGN2' CDR
When Execute query 'CheckPricefor300minsOOB'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UKVoiceValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg68 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC04_Validate MMS made from Roam Free zone(Long text)to Non-Roam further and usage rated in BRM
@Vishwa
Meta:@RatingReg68
@FolderName RatingReg68
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidRedEntertainmentCTR15'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'MMSUsageFranceToZimbabwe' CDR
When Execute query 'CheckPriceforLongTextUsageUktoChannelIsland'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToSMSandMMS'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg69 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC08_Perform a MMS usage (Picture) from Roam Further Zone to Roam Free Zone
@Vishwa
Meta:@RatingReg69
@FolderName RatingReg69
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion111186'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'MMSUsageRoamFurtherToRoamFree' CDR
When Execute query 'CheckPriceforPictureRoamFurtherToRoamFree'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'PictureValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg70 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC12_Perform a MMS usage(Video) from NON-RoamFurther zone to Same country (Internal)
@Vishwa
Meta:@RatingReg70
@FolderName RatingReg70
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'MMSVideoUsageZimbabweToZimbabwe' CDR
When Execute query 'CheckPriceforPictureRoamFurtherToRoamFree'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'PictureValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingReg71 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC17_Perform a MMS usage(Picture) from Satellite to UK
@Vishwa
Meta:@RatingReg71
@FolderName RatingReg71
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
Given Rate a 'MMSUsageUKToSatellite' CDR
When Execute query 'CheckPriceforVideoUsageUktoUk'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'PictureValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingReg72 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC21_Perform a Domestic MMS usage (Video)
@Vishwa
Meta:@RatingReg72
@FolderName RatingReg72
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'MMSVideoUsageUKtoUK' CDR
When Execute query 'CheckPriceforVideoUsageUktoUk'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'PictureValidation'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingReg73 CCS_19.8_BRM_WR60852_Reinvent_MMS_Changes_TS01_TC26_Perform a MMS usage(Long Text) from UK to a Channel Island
@Vishwa
Meta:@RatingReg73
@FolderName RatingReg73
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
Given Rate a 'MMSLontextUsageUKtoChannelIsland' CDR
When Execute query 'CheckPriceforLongTextUsageUktoChannelIsland'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Validate Itemised List Selection TopUp 'UsageToSMSandMMS'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingReg74 NewCo_CCS9.0_PBI000000101370_Late Landing usage, bundled using the customers current bundle 
         and not the bundle the call was made
@Vishwa - Check Invoice manually mandatory
Meta:@RatingReg74
@FolderName RatingReg74
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceUsageToTariff501' CDR
When Execute query 'CurrentBalRatingReg74'
Given Generate Month End Bill with Account 'AccountNo'
Given Rate a 'VoiceUsageToTariff501Index1' CDR
When Execute query 'CurrentBalRatingReg74With2ndBillProf'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'