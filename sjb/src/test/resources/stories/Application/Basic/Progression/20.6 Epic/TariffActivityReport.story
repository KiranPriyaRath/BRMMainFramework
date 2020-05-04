Story: Progression
Meta:@TAR
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: Rating_TAR_01 Perform domestic voice, SMS, MMS, Data usages on SME account and extract the reports mentioned
@Kiran
Meta:@Rating_TAR_01
@FolderName Rating_TAR_01
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
Given Rate a 'VoiceRating' CDR
When Execute query '501inBundle'
Given Rate a 'DomesticUKSMS' CDR
When Execute query '501inBundle'
Given Rate a 'PostpaidDataIndex2' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'

Scenario: Rating_TAR_002 Perform international out-of-bundle voice, SMS, MMS usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_002
@FolderName Rating_TAR_002 
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
Given Rate a 'InternationalVoiceUsage' CDR
When Execute query 'InternationalCallcharge'
Given Rate a 'InternationalSMSUKtoIndiaIndex1' CDR
When Execute query 'InternationalSMSUsage'
Given Rate a 'InternationalMMSFromUKtoBulgariaIndex2' CDR
When Execute query 'InternationalSMSFromUKtoTurkey'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_003 Perform roaming out-of-bundle voice, SMS, MMS, Data usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_003
@FolderName Rating_TAR_003 
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
Given Rate a 'RoamingCallFromCANMM' CDR
When Execute query 'InternationalVoiceUsageToTariff535child'
Given Rate a 'RoamingSMSIndex1' CDR
When Execute query '501inBundle'
Given Rate a 'MMSBraziltoUkIndex2' CDR
When Execute query 'InternationalVoiceUsageToTariff535child'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'

Scenario: Rating_TAR_004 Perform premium out-of-bundle  voice, SMS, NGN usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_004
@FolderName Rating_TAR_004
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
Given Rate a 'VoiceRatingUKtoNGN30Sec' CDR
When Execute query 'CheckPriceforPictureRoamFurtherToRoamFree'
Given Rate a 'PremiumNGNRatingIndex1' CDR
When Execute query '641Rating12'
Given Rate a 'DosmesticPremiumSMSUsageIndex2' CDR
When Execute query 'PremiumNGN'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_006 Perform in,partial,out of bundle NGN voice usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_006
@FolderName Rating_TAR_006
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
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
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_007 Perform in,partial,out of bundle domestic MMS, Data usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_007
@FolderName Rating_TAR_007 
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidConsumer111980'
And Customise 'RootProduct' with Econfig Item 'Extra100PictureMsgsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'MMSPictureMsgUsageUKtoUK' CDR
When Execute query '501inBundle'
Given Rate a 'MMSPictureMsgUsageUKtoUKIndex1' CDR
When Execute query '501outBundle12'
Given Rate a 'MMSPictureMsgUsageUKtoUKINdex2' CDR
When Execute query '501outBundle12'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_008 Perform in,partial,out of bundle International voice, SMS usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_008
@FolderName Rating_TAR_008 
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Customise 'RootProduct' with Econfig Item 'VodafoneCall75MinsOnExtra100TextsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'InternationalVoiceUsage' CDR
When Execute query '501inBundle'
Given Rate a 'InternationalVoiceUsageIndex1' CDR
When Execute query 'InternationalCallcharge'
Given Rate a 'InternationalVoiceUsageIndex2' CDR
When Execute query 'InternationalCallcharge'
Given Rate a 'InternationalSMSUsageUKtoIndiaIndex4' CDR
When Execute query '501inBundle'
Given Rate a 'InternationalSMSUsageUKtoIndiaIndex5' CDR
When Execute query '501outBundle'
Given Rate a 'InternationalSMSUsageUKtoIndiaIndex6' CDR
When Execute query '501outBundle'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_009 Perform out of bundle International voice usages on SME account and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_009
@FolderName Rating_TAR_009 
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad75MinsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'InternationalVoiceUsageOutOfBundle' CDR
When Execute query 'InternationalCallcharge'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_010 Perform in,partial,out of bundle roaming voice,SMS, Data usages while roaming in WT elligible country and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_010
@FolderName Rating_TAR_010 
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion4G'
And Customise 'RootProduct' with Econfig Item 'VodafoneCallAbroad75MinsOn'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'InternatioanalRoamingVoiceIndiatoUSA' CDR
When Execute query 'IND01VoiceRoaming'
Given Rate a 'InternatioanalRoamingVoiceIndiatoUSAIndex1' CDR
When Execute query 'InternationalCallcharge'
Given Rate a 'InternatioanalRoamingVoiceIndiatoUSAIndex2' CDR
When Execute query 'InternationalCallcharge'
Given Rate a 'InternationSMSUsageIndiatoUSAIndex3' CDR
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_011 Perform in,partial,out of bundle roaming voice,SMS usages while roaming in WT non-elligible country and extract the reports mentioned
@Vishwa
Meta:@Rating_TAR_011
@FolderName Rating_TAR_011 
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidConsumer109275'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'FrancetoUKCall' CDR
When Execute query '501inBundle'
Given Rate a 'SMSUsageIndex1' CDR
When Execute query '501inBundle'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'

Scenario: Rating_TAR_014 Extract the below mentioned for an SME account having a one-off charge  
Meta:@Rating_TAR_014
@FolderName Rating_TAR_014
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentPromotion107787'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
When Create new order 'NewOrderNoPopUp'
And Promotion is added with 'billcopyadd'
When Make payment with 'ONAccountNon-CardPopNO'
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Account charge Apply
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_015 Perform event, item, bill level adjustment on SME account and extract the reports mentioned  
Meta:@Rating_TAR_015
@FolderName Rating_TAR_015
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
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
And Rate a 'Report2IndiaNumber' CDR
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
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
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'


Scenario: Rating_TAR_17 Perform content (MPAY) usages of credit and debit on SME account and extract the reports mentioned
@Kiran
Meta:@Rating_TAR_17
@FolderName Rating_TAR_17
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
Given Rate a 'SpotifyCredit' CDR
When Execute query 'SpotifyCredit'
Given Rate a 'SpotifyDebitIndex1' CDR
When Execute query 'SpotifyDebit'
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'InvoiceAnalyticDateUpdate'
When Execute query 'ItemizedInvoiceDateUpdate'
When Execute query 'InvoiceCreatedDateUpdate'
Then Generate Month End Report from SBExport for 'itemizedInvoiceGenerate'
Then Generate Month End Report from SBExport for 'InvoiceExpAnalyticIndex1'