Story: Sharer
Meta:
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: BI08 CR89487_One Net Business_Billing_TS15_TC01_Validate that Itemized view of Non-Usage charges of a bill 
@Ankesh
Meta:@BI08
@FolderName BI08
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'TMTTAR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'OneNetPromotion'
And Customise 'RootProduct' with Econfig Item 'VodaOneNetExpOn'
And Select Menu 'PromotionEditPackage'
And Add Product 'FixedService'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
Given Rate a 'VoiceRatingUK' CDR
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'BillNumberCapture'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Execute query 'CycleForwardCaptureAmount'
When Item Bill Level Adjustment 'InstalledIdwithGTNMsisdn' 'CYCLEFORWARDNetAmount&DrillDown'
And Event Level Adjustment selection 'GTNMSISDN'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Execute query 'CycleForwardCaptureAmountPrepaid'
When Item Bill Level Adjustment 'CTNMSISDNInstallId' 'CYCLEFORWARDNetAmount&DrillDown'
And Event Level Adjustment selection 'CTNMSISDN'

Scenario: BillingNew04 R2.2_CR46839 & CR46120_Billing_TS01_TC01_Indirect_Partnes_Billing and Invoicing_Invoice to show correct VAT for all level of adjustments 
@Ankesh
Meta:@BillingNew04-VPS Account
@FolderName BillingNew04
Given Pre-requisite with logs in folder 'FolderName'
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
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
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

Scenario: BJNew02 LCS Phase2 Sprint1_CCS6.0_EBU Sharer_Billing_Rating Flow_Promotion_106405
@Devi
Meta:@BJNew02
@FolderName BJNew02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'VoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query '501inBundle'
Given Rate a 'DomesticUKSMS' CDR
When Execute query 'ComparingtheRatingValue'
When Execute query '501inBundle'
Given Rate a 'InternationalUKtoIndiaVoice' CDR
When Execute query 'Internatwithout10%off'
Given Rate a 'InternationalSMSUKtoIndia' CDR
When Execute query 'InternationalSMSUsage'
Given Rate a 'VoiceRating15000bundle' CDR
When Execute query 'VoiceRating15000bundle'
Given Rate a 'SMSRating2000bundleS' CDR
When Execute query 'SMSRating2000bundleS'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: BI08 CR89487_One Net Business_Billing_TS15_TC01_Validate that Itemized view of Non-Usage charges of a bill 
@Ankesh
Meta:@BI08
@FolderName BI08
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'TALKMOBILE' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'OneNetPromotion'
And Customise 'RootProduct' with Econfig Item 'VodaOneNetExpOn'
And Select Menu 'PromotionEditPackage'
And Add Product 'FixedService'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
When Execute query 'BillNumberCapture'
When Execute query 'MSISDNFromAccount'
Given Rate a 'VoiceRatingUK' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'SharingGroupAccountNoCompare'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Execute query 'CycleForwardCaptureAmount'
When Item Bill Level Adjustment 'InstalledIdwithGTNMsisdn' 'CYCLEFORWARDNetAmount&DrillDown'
And Event Level Adjustment selection 'GTNMSISDNAdJButtonClick'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'CTNMSISDNInstallId' 'CYCLEFORWARDNetAmount&DrillDown'
And Event Level Adjustment selection 'CTNMSISDNInstallId'

Scenario: CA12 CR89487_One Net Business_Billing_TS23_TC01_Perform Credit Alerting to generate Credit Alert Outgoing Bar removal for High CRI Credit Alert for threshold decreased below 100 percent 
@Tarun
Meta:@CA12
@FolderName CA12
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'TMTTAR' in 'Env'
When Create the account type 'SmallBusiness'
When Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'OneNetPromotion'
And Customise 'RootProduct' with Econfig Item 'VodaOneNetExpOn'
And Select Menu 'PromotionEditPackage'
And Add Product 'FixedService'
When Customise 'FixedService' with Econfig Item 'AreaVirtLandCallForwdWithMSISDN'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
When Make Payment for 'CA12'
When Execute query 'CA1204'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CA1206'
Then Check Credit Alert Status 'CA1207'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'OneNetPromotion'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'CA1201'
Then Check Order Status

Scenario: R2.4.1_49700_Oracle_TS28_TC01_Enterprise Customer is provisioned with usage based discount product for outofbundle roaming voice Validate customer is charged 
          roaming rates without usage based discount when making a cal to US frm EU country
@Ankesh
Meta:@INV03
@FolderName INV03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Customise 'RootProduct' with Econfig Item 'Disc10%RoamCallAddandSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'FrancToUSRating' CDR
When Execute query 'FrancetoUsAdd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNoIndex1'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: INV38 R2.4.1_49710_Oracle_TS04_TC01_Validate that a member will be able to use the discount 
		  product until depletion, then is charged for out of bundle minutes
@Ankesh
Meta:@INV38
@FolderName INV38
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidEBUSharer106446'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Execute query 'MSISDNFromAccount'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'NormalVoiceRatingNoIndx' CDR
When Execute query '501inBundle'
When Execute query 'ComparingtheRatingValue'
Given Rate a 'NormalVoiceRating600000Indx' CDR
When Execute query 'VoiceRating15000bundle'
Given Rate a 'NormalVoiceRating60Indx2' CDR
When Execute query 'VoiceRating15000bundle'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario:R2.4.1_49700_Oracle_TS03_TC01_Validate that the Enterprise Customer is charged correctly 
         after applying the usage based discount product for out of bundle roaming Voice usage.
Meta:@INV41
@FolderName INV41
@Ankesh
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceFrancetoUkRatingcalling' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidEBUSharer'
And Modify 'ModifyWithInstalledID' with Econfig Item 'Disc10%RoamCallAdd'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'VoiceFrancetoUkRatingcalling' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario: R006 148703_R2.4.1_CR49710_Billing_TS01_TC01_Validate charge applied to account owner for Voice or SMS Sharer bundle optin and invoiced correctly
@Ankesh
Meta:@R006
@FolderName R006
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidEBUSharer106446'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
And Execute query 'MSISDNFromAccount'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Rate a 'NormalVoiceRating' CDR
When Execute query '501inBundle'
Given Rate a 'DomesticRoaming' CDR
When Execute query '501inBundle'
Given Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: R009 148694_R2.4.1_CR49700_Billing_TS04_TC01_Validate that the Enterprise Sharer is charged correctly after applying the usage
          based discount product for out of bundle domestic
@Ankesh
Meta:@R009
@FolderName R009
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Customise 'RootProduct' with Econfig Item '10%ofUKdataoutsidandSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
When ReLogin with 'AGENT04' in 'Env'
When Search The Account ''
And Create 'NewOrderNoPopUp'Order
And Promotion is added with 'PostpaidEBUSharer106446'
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
And Rate a 'DomesticDataRating' CDR
When Execute query 'OutData10Pound'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: R047 R2.4.1_49700_Oracle_TS12_TC01_Validate that the Enterprise Customer is charged correctly after removing the recurring credit discount product for the applicable billing cycle
@Vaishnavi
Meta: @R047
@FolderName R047
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion24monthUnlimited'
And Promotion is added with '50CreditFor24Months'
And Promotion is added with '20CreditFor24Months'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'
Given Login with 'RETAIL' in 'Env'
Then Perform the action 'Disconnect1Promotion'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
When Search The Account ''
Then Perform the action 'Disconnect2Promotion'
When Submit the Order 'NormalSubmitwithDisconnectionReason'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: R023 164065_R2.4.1_49700_Billing_TS02_TC01_Validate that the Enterprise Customer is charged correctly after removing the usage based discount
          product for out of bundle international Voice usage.
@Ankesh
Meta:@R023
@FolderName R023
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Customise 'RootProduct' with Econfig Item '10%onInternatCallandSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Generate Month End Invoice with Account 'AccountNo'
And Rate a 'Voice60Duration535Tariff' CDR
When Execute query 'InternatLeadBusinessUktoInd'
Given Generate Month End Bill with Account 'AccountNo'
And Login with 'AGENT04' in 'Env'
When Search The Account ''
And Retrieve Promotion 'PostpaidEBUSharer'
And Modify 'ModifyWithRootProduct' with Econfig Item '10%offInternatCall'
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
And Rate a 'Voice60Duration535Tariff' CDR
When Execute query 'Internatwithout10%off'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario:R007 148669_R2.4.1_CR49700_Billing_TS26_TC01_Validate that a Customer provisioned with WT and Voice,SMS Sharer is charged using domestic shared minutes for roamed voice usage in a WT applicable counntry,
          and the roamed pricing discount is not applied
@Vaishnavi
Meta:@R007
@FolderName R007
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidEBUSharer'
And Customise 'RootProduct' with Econfig Item 'GlobalRoamingONandSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Promotion is added with 'PostpaidEBUSharer106446'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord1Index'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSimIndex'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'IndiatoUkVoiceRating' CDR
When Execute query 'ComparingtheRatingValue'
And Execute query 'IND01VoiceRoaming'
Given Rate a 'IndiatoUkVoiceRating1Index' CDR
When Execute query 'ComparingtheRatingValue'
And Execute query 'IND01VoiceRoaming VT2nd'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'

Scenario: RatingNew23 CR89481_VPS_Indirect_Partner_Enablement_Billing_TS01_TC01_Validate the Invoice  for a VPS customer 
         for VPS dual branding logo.
@Ankesh
Meta:@RatingNew23
@FolderName RatingNew23
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Execute query 'UpdateVendorAccountTable'
Given Rate a 'SMSUsageBelkoRoaming' CDR
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:R034 R2.4.1_49700_Oracle_TS08_TC01_Validate that the Enterprise Customer is charged correctly after applying the 
         usage based discount product for out of bundle roaming SMS usage
@Ankesh
Meta:@R034
@FolderName R034
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'VFREDUSR' in 'Env'
When Create the account type 'SmallBusiness'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidParentBusinessSharer'
And Customise 'RootProduct' with Econfig Item '10%allTextabroadandSharer'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'SMSRoaming' CDR
When Execute query 'SMSFrmIndiaToUk'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'