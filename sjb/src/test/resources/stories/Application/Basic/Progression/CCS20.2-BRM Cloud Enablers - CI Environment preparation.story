Story: RatingNewPack
Meta:@new
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM
 
Scenario: PRCloud01 Perform Lockbox payment using new Input Output locations and validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud01
@FolderName PRCloud01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Search The Account ''
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Payment for 'LockBoxDDPayment'
When Execute query 'PaymentReceivedType123'

Scenario: PRCloud02 Perform Other payment using new Input Output locations and validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud02
@FolderName PRCloud02
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Search The Account ''
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Make Payment for 'LockBoxDDPayment'
When Execute query 'PaymentReceivedType123'


Scenario: PRCloud03 Perform ABC payment using new Input Output locations and validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud03
@FolderName PRCloud03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
Given Retrieve BRM Account 'PostpaidConsumerBRMWithoutAnyTravellerTest1'
When Search The Account ''
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'ABCAmount0Payment'
When Execute query ''

Scenario: PRCloud04 Perform CC payment using new Input Output locations and validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud04
@FolderName PRCloud04
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New 'NewPostpaid' and Perform 'AboutRecordOfMenuItem' with About Record
And Perform 'RunQueryOfMenuItem' and Execute query 'TokenizationAmex' and Create New 'NewOrderNoPopUp' Order
And Promotion is added with 'PostpaidPromotion107785'
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
And Make Payment for 'CCAccountNBilloption'
When Execute query 'CCPNAMOUNT0'

Scenario: PRCloud05 Perform DD payment to validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud05
@FolderName PRCloud05
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create New Billing Profile 'NewPostpaid'
Then Create Direct Debit for scenario 'DirectDebit'
When Goto Account Summary
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
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedTypePR12'


Scenario: PRCloud06 Perform ARUDD / DDIC payment reversal using new Input Output locations and validate the CM DM and utility logs generated
@Kiran
Meta:@PRCloud06
@FolderName PRCloud06
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
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
When Make Payment for 'DDAccount'
And Make Reverse Payment for 'Type1and0RC'