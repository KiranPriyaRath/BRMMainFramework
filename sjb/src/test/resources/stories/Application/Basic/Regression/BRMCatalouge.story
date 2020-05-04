Story: FL BRM
Meta:@FLTesting
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: FL BRM
Meta:@FL004473900CBU
Scenario: FL004473900CBU For Fixedline  CBU Customer,Perform domestic usages on New personal number(B number) as 004473900
@Ankesh
Meta: @FL004473900CBU
@FolderName FL004473900CBU
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Catalouge004473900' CDR
When Execute query 'VoicetermCallCBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: FL004473900EBU For Fixedline  EBU Customer,Perform domestic usages on New personal number(B number) as 004473900
@Ankesh
Meta:@FL004473900EBU
@FolderName FL004473900EBU
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Catalouge004473900' CDR
When Execute query 'OffPeakchargePersona1lNo'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'


Scenario: FL BRM
Meta:@FL001833CBU
Scenario: For Fixedline CBU customer Perform international voice call from UK to USA with New B number prefix as 1833
@Ankesh
Meta: @FL001833CBU
@FolderName FL001833CBU
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Catalouge1833' CDR
When Execute query 'Catalouge001833CBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'



Scenario: FL001833EBU For Fixedline EBU customer Perform international voice call from UK to USA with New B number prefix as 1833
@Ankesh
Meta:@FL001833EBU
@FolderName FL004473900EBU
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'Catalouge1833' CDR
When Execute query 'Catalouge001833EBU'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'




Scenario:WR48593 For postpaid perform roaming voice or SMS usages from the new country code MMRTN(Myanmar).
@Ankesh
Meta:@WR48593
@FolderName WR48593
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'ConsumerPostpaid'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion109161'
And Update MSISDN by performing 'UpdateMSISDN' on 'RootProductAboutRecord'
And Update ICCID against 'EnterICCIDInBlankWhiteTripleFormatSim'
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
And Generate First Bill
Given Rate a 'WR48593' CDR
When Execute query 'WorldTraveller'
Given Rate a 'WR485931' CDR
When Execute query '501inBundle'
Given Rate a 'WR485932' CDR
When Execute query 'WR485932'
Given Rate a 'WR485933' CDR
When Execute query 'RoamingDataUsage'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'PostPaid'

