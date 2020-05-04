Story:Rating
Meta:@
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: BRMRating055 CCS7.0_Defect 2664_PBI000000100541_Extra unlimited minutes is granting resource 55570011 but in discount VF_Free Minutes_Unlimited trigger is on resource id 55570009 Hence discount is not getting applied
Meta:@BRMRating055
@FolderName BRMRating055
@Devi
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'PostpaidConsumer'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'UnlimitedMinutesExtra'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'RecIdValidation'
Given Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query 'RecIdValAtrRating'
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R015 NewCo R15.2_Romaing MO Call to NGN Number starts with 084, 087 and 090 with WT
@Pankaj
Meta: @R015
@FolderName R015
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty9' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'VoiceRatingUKToIndia' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUKToIndia1Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUKToIndia2Index' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R016 R2.3_CR51400_Billing_TS03_TC01__World Traveller In Country Calling_A customer with World 
				Traveller who makes a voice call from South Africa to a different World Traveller country number is charged at international rates
@Devi
Meta:@R016
@FolderName R016
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceZafcctoIndia' CDR
When Execute query 'Zone1ToZone1'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R010 168951_R2.4.2_46794_Billing_TS27_TC01_ Validate Rating  and Invoicing for 009x premium number as per PPC Pricepoints combination for CBU customer
@Ankesh
Meta:@R010
@FolderName R010
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceTariffRating' CDR
When Execute query '641Rating12'
Given Rate a 'VoiceTariffRating523Index' CDR
When Execute query '523Rating'
Given Rate a 'VoiceTariffRating639Index' CDR
When Execute query '639Rating'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R012 167065_R2.4.2_46794_Billing_TS20_TC01_Validate call charges for NGN call made by roaming customer
          having both Red Brand and Non Geo Bundle, is charged at domestic rate
@Ankesh
Meta:@R012
@FolderName R012
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'TMTTAR' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion12monthWith300minBundle'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceFrancetoUkRating' CDR
When Execute query '641Rating'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R013 R2.4.2_46794_Billing_TS01_TC01_ Validate Rating  and Invoicing for 009x premium number as per PPM and PPC Pricepoints combination for CBU customer
@Ankesh
Meta:@R013
@FolderName R013
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceTariffRating' CDR
When Execute query '641Rating12'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R017 NewCo_R15.2_CR72180_New_Billing_TS04_TC01_ Validate that Bundle is correctly consumed when an inbundle, Partially inbundle, out of bundle call PPC is made to a 844643XX series number
@Ankesh
Meta:@R017
@FolderName R017
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
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
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoicewithTariff' CDR
When Execute query 'Voicetariff563Add'
Given Rate a 'VoicewithTariff1Index' CDR
When Execute query 'Execute18000durat'
Given Rate a 'VoicewithTariff2Index' CDR
When Execute query 'Execute563durat'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R018 NewCo_R15.2_CR72180_New_Billing_TS10_TC01_ Validate that Bundle is correctly consumed when an inbundle, Partially in-bundle, out of bundle call PPM is made to a 870340XX series number.
@Ankesh
Meta:@R018
@FolderName R018
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Extra300mins084to087'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'Voice60Calling' CDR
When Execute query 'TariffQty0Result'
Given Rate a 'Voice18000Calling1Index' CDR
When Execute query 'TariffQty18000Result'
Given Rate a 'Voice60Calling2Index' CDR
When Execute query 'TariffQty0.67Result'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R019 R2.4.2_46794_Billing_TS21_TC01_Customer having both Red Brand and Non Geo Bundle. Validate call charged as Non Geo Bundled call and Redbrand Bundle
          call based on the call type
@Ankesh
Meta:@R019
@FolderName R019
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'Extra300mins084to087'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceTariffDuration' CDR
When Execute query 'Tariff501and60Duration'
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceTariff669DurationIndex' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: R021 168949_R2.4.2_46794_Billing_TS22_TC01Customer having  Red Brand and Non Geo Bundle which is exhausted , validate voice call charged as Out
          Bundled call and In bundle depending upon call type
@Ankesh
Meta:@R021
@FolderName R021
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra300mins084to087'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'Voice18000Tariff' CDR
When Execute query '501inBundle'
Given Rate a 'Voice60Tariff' CDR
When Execute query '563OutBundleR021'
Given Rate a 'Voice60Tariffindex' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R022 164087_R2.3_Billing_TS_03_TC01_Rating_Voice_Billing and Invoicing validation for an postpaid customer with a single CTN
@Ankesh
Meta:@R022
@FolderName R022
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R033 R2.3_CR35692_Billing_TS03_TC01_Processing of Postpay data roaming in bundle CDR in BRM and validating the display in the itemized section of the invoice
@Ankesh
Meta:@R033
@FolderName R033-Pass
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'DataRoamingInBundle' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R034 R2.4.1_49700_Oracle_TS08_TC01_Validate that the Enterprise Customer is charged correctly after applying the usage based discount
          product for out of bundle roaming SMS usage
@Vaishnavi
Meta:@R034--Descoped
@FolderName R034
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentSharer106401'
And Customise 'RootProduct' with Econfig Item '10OffallTextAbroad'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'SMSRoaming' CDR
When Execute query 'SMSFrmIndiaToUk'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R037 NewCo_R15.2_CR72180_New_Billing_TS15_TC01_ Validate that Customer is charged at PPC+PPM rates when calling 901100XX series numbers.
@Ankesh
Meta:@R037
@FolderName R037
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Pre-requisite with logs in folder 'FolderName'
And Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a '120VoiceRating641Tariff' CDR
When Execute query 'CallFrmUKToUKSpecNo'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R038 R2.3_CR41075_Billing_TS06_TC01_Validate that the fee is triggered only once for a customer with
          World traveller making voice call and SMS usage to UK number from the USA
@Ankesh
Meta:@R038
@FolderName R038
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'DomesticData10Rating0Indx' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingFrmUSAToFrance1Indx' CDR
When Execute query 'WorldTraveller'
Given Rate a 'RoamingSMSUSAHT2Indx' CDR
When Execute query 'Tariff501and60Duration'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R040 R2.1_Journey_Billing_TS13_TC01 Rate Voice In bundle, Out Bundle, Partial bundle, Domestic, International, Roaming, Premium
@Ankesh
Meta:@R040
@FolderName R040
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumer110041BRM'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'NormalVoice535Tariff' CDR
When Execute query 'InternatVoiceUktoInd'
Given Rate a 'Voice13200RatingIndex' CDR
When Execute query '60Voice501TariffAdd'
Given Rate a 'Voice3000RatingIndex2' CDR
When Execute query '1200Voice501TariffAdd'
Given Rate a 'Voice180RatingIndex3' CDR
When Execute query '180Voice501TariffAdd'
Given Rate a 'Voice60RatingIndex4' CDR
When Execute query '60Voice508TariffAdd'
Given Rate a 'VoiceIndToUKIndex5' CDR
When Execute query 'INDtoUKroamwithoutWT'
Given Rate a 'VoiceRatingIndex6' CDR
When Execute query '60Voice519TariffAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R044 R2.3_CR51400_Billing_TS03_TC01_World Traveller In Country Calling_Validate that WT  discounts and benefits will only applicable for B number other than 07,08,09
@Ankesh
Meta:@R044
@FolderName R044
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'IndToUkVoiceRatingindex1' CDR
When Execute query 'NGNWTCharges'
Given Rate a 'IndToUkVoiceRatingIndex2' CDR
When Execute query 'NGNWTCharges'
Given Rate a 'IndToUkVoiceRating' CDR
When Execute query 'WorldTraveller'
Given Rate a 'IndToUkVoiceRatingIndex3' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R032 R2.4.2_46794_Billing_TS16_TC01_ For future dated pricing configuration with call made during midnight, validate Rating for 087x premium number depend on
          the start time of the call
@Ankesh
Meta: @R032
@FolderName R032
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Select the TimeStamp '2d23hr59mins'
When Execute query 'TimestampCapture'
Given Rate a 'VoiceRatingTimestamp' CDR
When Execute query 'TariffQty0.67Result'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R036 R2.2_CR26601_Billing_2010_TS_12_TC_01_Customer having Ireland Plus makes call from Republic of Ireland to non-UK number
@AnkeshPass
Meta:@R036
@FolderName R036
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Customise 'RootProduct' with Econfig Item 'IrelandPlusOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'VoiceIrelandtoInd' CDR
When Execute query 'SMSFrmIrelandtoIndia'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'




Scenario: R046 R2.4.2_46794_Billing_TS26_TC01_ Validate Rating , Invoice and Finance GL ID  for 087x premium number as per PPC Pricepoints combination for EBU Sole Trader
@Ankesh
Meta: @R046
@FolderName R046
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
Given Rate a 'VoiceRating669Tariff' CDR
When Execute query 'PremiumCall60Duration'
And Execute query 'Compare3ResultsGLID'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: R049 BRM_R2.3_CR28661_Suspend CDRs that fail age check_UC_R2.1_2015.b_TS03_TC01_Postpay CDR with timestamp on the 60th day with the system time should not fail and rate successfully
@Ankesh
Meta:@R049
@FolderName R049
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRM60DaysOld'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Select the TimeStamp '59d10min10hr'
When Execute query 'Timestamp4Capture'
Given Rate a 'VoiceTimestamp' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
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


Scenario: RatingNew02 NewCo CCS3.0_WR31451_TS01_TC05_Perform roaming voice usage from Iceland to Angola (Without VAT)
@Vaishnavi
Meta: @RatingNew02
@FolderName RatingNew02
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
And Rate a 'AGOMV244767876778' CDR
When Execute query 'AGOMV244767876778'
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


Scenario: RatingNew03  NewCo_CCS 5.0_CR 104082_BRM_TS01_TC01_Validate that call to 191 number is correctly presented as Free on the invoice.
@Vaishnavi
Meta: @RatingNew03
@FolderName RatingNew03
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'CalledNo44191' CDR
When Execute query 'Calledto191'
Then Download the EDW Usage file to local system
Given Rate a 'CalledNo445555' CDR
When Execute query 'Calledto191'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingNew04  NewCo_CCS 5.0_CR 104082_BRM_TS01_TC03_Validate that call to 56789 number is correctly presented as Free on the invoice.
@Vaishnavi
Meta: @RatingNew04
@FolderName RatingNew04
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
And Rate a 'CalledNo4456789' CDR
When Execute query 'Calledto191'
When Execute query 'ComparingtheRatingValue'
Then Download the EDW Usage file to local system
Given Rate a 'CalledNo917387912299' CDR
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingNew05 NewCo_CCS 5.0_CR 104082_BRM_TS03_TC01_Validate that SMS to 600714 GSM Service codes are correctly presented as Free on the invoice.
@Ankesh
Meta:@RatingNew05
@FolderName RatingNew05
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
And Rate a 'DomesticSMSGRMRating' CDR
When Execute query 'Calledto191'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: RatingNew06 NewCo_CCS 5.0_CR 104082_BRM_TS06_TC01_Validate that calls to 558 tariff class  numbers are correctly presented as Free on the invoice.
@Vaishnavi
Meta: @RatingNew06
@FolderName RatingNew06
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
And Rate a 'Voice558TariffClassRating' CDR
When Execute query 'MSISDNFromAccount'
And Execute query 'MSISDNFromAccount'
Then Download the EDW Usage file to local system
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: RatingNew07 NewCo CCS 4.1_BRM_73719_Finance Month End - Detail usage Report
@Vaishnavi
Meta: @RatingNew07
@FolderName RatingNew07
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
And Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceRatingindx1' CDR
When Execute query 'ComparingtheRatingValue'
Then Generate Month End Report from SBExport for 'usageDetail'


Scenario: RatingNew08 NewCo CCS3.0_WR31451_TS09_TC02_Perform roaming voice usage from GUAM (USA) to Cyprus
@Vaishnavi
Meta:@RatingNew08
@FolderName RatingNew08
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
And Rate a 'CYPCT357678987896' CDR
When Execute query 'USAtoCyprusRate'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingNew09 NewCo CCS3.0_WR31451_TS08_TC01_Perform roaming voice call from Bulgaria to Estonia with bundle Inclusive roaming calls texts and picture messages
@Vaishnavi
Meta:@RatingNew09
@FolderName RatingNew09
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion109157'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'ESTRB372738764512' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query '501inBundle'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingNew10 - NewCo CCS3.0_WR31451_TS10_TC01_Perform roaming voice MT usage from UK  to Bhutan
@Vaishnavi
Meta:@RatingNew10
@FolderName RatingNew10
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
And Rate a 'GBRVF447387912299' CDR
When Execute query 'GBRVF447387912299'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: RatingNew11 NewCo_CCS 5.0_CR 104082_BRM_TS07_TC01_Validate that incoming voice calls in Europe Zone are correctly presented as Free on the invoice
@Vaishnavi
Meta:@RatingNew11
@FolderName RatingNew11
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'FRAF1337678987896' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
Then Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
And Download the Generated Report file to local system
Given Login to BIP with 'LoginToBIP' for 'PostPaid'


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


Scenario: RatingNew14 - NewCo_CCS 3.0_CR101796_Billing_TS05_TC01_Verify that UoM changes for the mentioned UoMs are correctly displayed in the Itemised Usage view for Billed Usage for Postpay CBU Subscriber
@Vaishnavi
Meta:@RatingNew14
@FolderName RatingNew14
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'CDRType4CountryFRAF1RateGBP0' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType2_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType5_447432134567' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'InstalledMSISDNSelect' 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToRoamingSMSandMMS'
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
When Event Level Adjustment selection 'UsageToRoamingCalls'


Scenario: RatingNew15 NewCo_CCS 3.0_CR101796_Billing_TS04_TC02_Verify that UoM changes for the mentioned UoMs are correctly displayed in the Itemised Usage view for Unbilled Usage for Postpay CBU Subscriber
@Vaishnavi
Meta:@RatingNew15
@FolderName RatingNew15
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'CDRType4CountryFRAF1RateGBP0' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType2_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType5_447432134567' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'CDRType3_447387912299' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Execute query 'BillNumberCapture'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToRoamingSMS'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickNo'
When Event Level Adjustment selection 'UsageToUKMobileInternet'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Event Level Adjustment selection 'UsageToPictureMessages'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsageClickNo'
When Event Level Adjustment selection 'UsageToRoamingCalls'


Scenario: RatingNew16 NewCo_CCS 3.0_CR101796_Billing_TS04_TC01_Verify that UoM changes for the mentioned UoMs are correctly displayed in the Itemised Usage view for UnBilled Usage for Postpay CBU Subscriber
@Vaishnavi
Meta:@RatingNew16
@FolderName RatingNew16
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
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
When Execute query 'BillNumberCapture'
Given Login with 'RETAIL' in 'Env'
When Execute query 'BillNumberCapture'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToSMSUsageClickNo'
When Event Level Adjustment selection 'UsageToUKTexts'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Event Level Adjustment selection 'UsageToUKCalls'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToDataUsageClickNo'
When Event Level Adjustment selection 'UsageToUKMobileInternet'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToOtherUsageClickNo'
When Event Level Adjustment selection 'UsageToPictureMessages'


Scenario: R035 R2.3_CR_51400_Billing_TS02_TC01_A customer with World Traveller who  sends an SMS from India to an India number is charged at domestic rates
@Ankesh
Meta:@R035
@FolderName R035
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a '501SMSQuantityNoIndx' CDR
When Execute query 'WorldTraveller'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'


Scenario: R020 168948_R2.4.2_46794_Billing_TS09_TC01_Validate Rating ,billing, Invoice and Finance GL ID for 084x premium number based on PPM pricepoint for roaming Small Business
          Customer having World Traveller Product activated
@Ankesh
Meta:@R020
@FolderName R020
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
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'VoiceRatingUKToIndia' CDR
When Execute query 'INDtoUKCallroamwithoutWT'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: RatingNew18 R2.2_CR26581_Billing_TS07_TC01_Validate that products are viewed as stacked in the Balance Group Tab of the Bill Detail View for a postpaid customer.
@kiran
Meta:@RatingNew18
@FolderName RatingNew18
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion110041'
And Customise 'RootProduct' with Econfig Item 'Extra300minsOn'
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
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'MSISDNFromAccount'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Minute And SMS Bundle Usage Validation 'AvailableBalanceMinutes'
And Detail Bundle Usage Validation 'CompareAvailableBalance'
And Execute query 'GetBalanceQuerytext'
And Minute And SMS Bundle Usage Validation 'OriginalBalanceSMS'
And Detail Bundle Usage Validation 'CompareNamewithSMS'
And Product Bundle Usage Validation 'StatusAllActive'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Execute query 'MSISDNFromAccount'
And Execute query 'GetBalanceQueryMinutes'
And Bundle Usage Validation 'DrillDownToMSISDN'
And Minute And SMS Bundle Usage Validation 'AvailableBalanceMinutes'
And Detail Bundle Usage Validation 'CompareAvailableBalance'
And Execute query 'GetBalanceQuerytext'
And Minute And SMS Bundle Usage Validation 'OriginalBalanceSMS'
And Detail Bundle Usage Validation 'CompareNamewithSMS'
And Product Bundle Usage Validation 'StatusAllActive'

Scenario:RatingNew30 NewCo CCS2.0_SIT_ BRM_INC2321412_71492_charged incorrectly for calls to Minnesota area code 218 in USA
@Ankesh
Meta:@RatingNew30
@FolderName RatingNew30
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'InternatCalltoUsa' CDR
When Execute query 'CalltoMinisota'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'