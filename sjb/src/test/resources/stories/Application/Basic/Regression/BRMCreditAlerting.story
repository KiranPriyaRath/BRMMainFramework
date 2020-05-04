Story:My Story
Meta:@CreditAlerting
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02


Narrative:
 BRM

Scenario:CA01 R2.3_CR37159_Billing_TS_05_TC_01_EBU_Credit_Alerting_Validate that for the EBU subscriber with a Low category CRI 
         the dialler action is triggered after 80  Threshold reached.
@Tarun
Meta:@CA01
@FolderName CA01
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
And Generate First Bill
When Make Credit Alert 'Platinum1005CRIValue'
When Make Credit Alert 'BusinessRetail300Amount'
Given Rate a 'Dmtic240RGBPOutPart' CDR
When Execute query 'CreditAltCheckstatusCustDial'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CreditAltCompare'
When Execute query 'CreditAltSiebelActiveCompare'
Given Login To Putty 'SiebelPutty' in 'Env'
When Check Alert in Siebel DB
When Execute query 'CreditAltSiebelCompleted'

Scenario:CA03 R2.3_CR35736_Billing_TS_05_TC_01_Credit_Alerting_Extension_Validate that for the High and medium
category CRI subscriber,  Credit Alerting Network Bar will be activated when Credit reached 100 Threshold.
@Tarun
Meta:@CA03
@FolderName CA03
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
And Generate First Bill
When Make Credit Alert 'SBRONZE'
When Make Credit Alert 'NEWPROD'
Given Rate a 'CA03' CDR
When Execute query 'CA03'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CA0301'
When Execute query 'CA0302'
Given Login with 'RETAIL' in 'Env'
When Promotion Selection 'PostpaidPromotion107785'
When Search The Account ''
When Create 'NoNewNoPopUp'Order
Then Verify Order page fields 'CA03'
Then verify the Order line Items 'CA03'
Then Check Order Status
When Execute query 'CA0303'

Scenario:CA05 NewCo_CCS 3.0_CR 100496_Credit Alerting on unbilled usage_BRM_TS01_Perform Credit Alerting to generate Dialer action with low category CRI after 60 percent threshold reached
@Tarun
Meta:@CA05
@FolderName CA05
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Make Credit Alert 'EXISTPRODSILVERCA05'
When Make Credit Alert 'EXISTPRODBRONZECA05'
Given Rate a 'CA05' CDR
When Execute query 'CA05'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CA0501'
Then Check Credit Alert Status 'CA0503'
Given Login To Putty 'BRMPutty2' in 'Env'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'CA05'
When Execute query 'CA0504'
When Execute query 'CA0505'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'CA0506'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
Then Verfiy Customer Comms 'CA05'


Scenario:CA06 NewCo_CCS 3.0_CR 100496_Credit Alerting on unbilled usage_BRM_TS07_Validate credit limit of customer is impacted when late landing CDR is rated
@Tarun
Meta:@CA06
@FolderName CA06
Given Pre-requisite with logs in folder 'FolderName'
And Retrieve BRM Account 'PostpaidConsumerBRMSysDate60to40Days'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
When Make Credit Alert 'CA06'
When Make Credit Alert 'CA0601'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'CA0506'
Given Select the TimeStamp 'CA06'
When Execute query 'Timestamp3Capture'
Given Rate a 'CA06' CDR
When Execute query 'CA06'
When Execute query 'CA0601'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CA0602'
When Execute query 'CA0603'
Given Login To Putty 'BRMPutty2' in 'Env'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'CA0506'
Given Generate Month End Invoice with Account 'AccountNo'
Given Login to BIP with 'LoginToBIP' for 'PostPaid'

Scenario:CA11 NewCo_CCS 3.0_CR 100496_Credit Alerting on unbilled usage_BRM_TS09_Validate agent able to change credit limit of customer from siebel GUI
@Tarun
Meta:@CA11
@FolderName CA11
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'CA11'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
Then Verify the Product and Services for 'CA11'
And Set Credit limit in siebel
When Execute query 'CA1101'


Scenario:NewCo_CCS 10.0_CR 111120_TS78_Validate postpay Business customer will receive credit alert bar action when unbilled usage breach the credit limit and unbar action when credit 
         limit is refreshed after billing
@Ankesh
Meta:@CA10
@FolderName CA10
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
And Generate First Bill
When Make Credit Alert 'Platinum1018CRIValue'
When Make Credit Alert 'BusinessRetail350Amount'
Given Rate a 'CA351' CDR
When Execute query 'CA350EbuBar'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'ProcessedOk350Ebu'
Then Check Credit Alert Status 'CA350SiebelEbu'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And Create 'NoNewNoPopUp'Order
When Order Status Validation
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'RetrievpendbalanceCapture'
Given Generate Month End Bill with Account 'AccountNo'
When Execute query 'CAUnbarStatusCheck'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CAUnbarStatusCheckProcessed'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And Create 'NoNewNoPopUp'Order
When Order Status Validation


Scenario: CANew10 CCS 19.6 VFUKE-5423 WR58597_Black tariff_BRM_TS04_TC01_Perform Credit alert journey for a EBU black tariff customer by breaching the 100 percent threshold
@Vishwa
Meta:@CANew10
@FolderName CANew10
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Credit Alert 'Platinum1103CRIValue'
When Make Credit Alert 'BusinessRetail200Amount'
When Execute query 'CA1101'
Given Rate a 'CredirAlerting120GBP' CDR
When Execute query 'CredirAlerting120GBP'
Given Rate a 'CredirAlerting45GBP' CDR
When Execute query 'CredirAlerting45GBP'
Given Rate a 'CredirAlerting50GBP' CDR
When Execute query 'CredirAlerting50GBP'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CheckCreditAlertProcessedOK'
When Execute query 'CheckCreditAlertActive'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'CheckCreditAlertCompleted'
Then Verfiy Customer Comms 'CreditAlertMessageVerify1'
Then Verfiy Customer Comms 'CreditAlertMessageVerify2'
Then Verfiy Customer Comms 'CreditAlertMessageVerify3'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: CANew12 Perform Credit alert journey for a CBU black tariff customer by breaching the 100
percent threshold
@Vishwa
Meta:@CANew12
@FolderName CANew12
Given Pre-requisite with logs in folder 'FolderName'
Given Retrieve BRM Account 'PostpaidConsumerBRMBlack'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Credit Alert 'Platinum1103CRIValue'
When Make Credit Alert 'BusinessRetail200Amount'
When Execute query 'CA1101'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingLegalGrp'
And Validate the Legal Status 'ValidateCreditClass'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account ''
Given Rate a 'RoamingVoiceFrancetoUk' CDR
When Execute query 'CredirAlerting120GBP'
Given Rate a 'RoamingVoiceFrancetoUkIndex1' CDR
When Execute query 'CredirAlerting45GBP'
Given Rate a 'RoamingVoiceFrancetoUkIndex2' CDR
When Execute query 'CredirAlerting50GBP'
Given Login to ODI with 'LoginToODI' in 'Env'
And Browse ODI Scenario 'CREDITALERTING'
And Execute ODI Scenario 'DocStoreNwidNoSlot'
And Validate ODI Scenario
When Execute query 'CheckCreditAlertProcessedOK'
Given Login with 'AGENT04' in 'Env'
When Search The Account ''
When Execute query 'CheckCreditAlertCompleted'
Then Verfiy Customer Comms 'CreditAlertMessageVerify1'
Then Verfiy Customer Comms 'CreditAlertMessageVerify2'
Then Verfiy Customer Comms 'CreditAlertMessageVerify3'
Given Login To Putty 'BRMPutty3' in 'Env'
And Show All Purchase Products in the account ''
Given Generate Month End Bill with Account 'AccountNo'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'