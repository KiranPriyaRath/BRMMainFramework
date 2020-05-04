Story: BRM Rating
Meta:@kprrrrr
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario: SearchFunctionality Validate in unbilled itemized view after selecting a service in search option when the number of services are less than threshold
@Kiran
Meta:@SearchFunctionality
@FolderName SearchFunctionality
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000757749'
Given Generate First Bill
Given Rate a 'VoiceRating250EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage200EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance50EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'DrillDownToVoiceUsageClickNo'
When Itemized List search 'SearchusingAmtBilledConsumer'
When Itemized List search 'ValidateinclusiveVatAmountBilled2'
When Itemized List search 'SearchusingAmountBilledUsingusagetype'
When Itemized List search 'SearchusingAmountBilledUsingDate'


Scenario: SearchFunctionality Validate in unbilled itemized view after selecting a service in search option when the number of services are less than threshold
@Kiran
Meta:@SearchFunctionality11
@FolderName SearchFunctionality
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000761363'
Given Generate First Bill
Given Rate a 'VoiceRating4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByPhNum'
When Itemized List search 'SearchByPhNumAmount'
When Itemized List search 'SearchByDtUsageType'
When Itemized List search 'SearchusingAmountBilledUsingDate'

Scenario: SearchFunctionality01 Validate in unbilled itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality011
@FolderName SearchFunctionality01
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000762258'
Given Generate First Bill
Given Rate a 'VoiceRating7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDNMoreThanThreshold'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'SearchByPhNum'
When Itemized List search 'SearchByPhNumAmount'
When Itemized List search 'SearchByDtUsageType'
When Itemized List search 'SearchByAmtUsageType'

Scenario: SearchFunctionality03 Validate in billed itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality033
@FolderName SearchFunctionality03
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Search The Account '7000763807'
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByAmtUsageTypeDt'
When Itemized List search 'SearchByPhNumAmt'



Scenario: SearchFunctionality01 Validate in unbilled itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality01212
@FolderName SearchFunctionality01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Search The Account '7000766524'
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDNMoreThanThreshold' 'CheckVoiceUsage'

Scenario: SearchFunctionality01 Validate in unbilled itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality011112
@FolderName SearchFunctionality01
Given Pre-requisite with logs in folder 'FolderName'
Given Login with 'RETAIL' in 'Env'
When Search The Account '7000763989'
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDNMoreThanThreshold' 'CheckVoiceUsage'


Scenario: SearchFunctionality01 Validate in unbilled itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality0133
@FolderName SearchFunctionality01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy6times'
And Perform 'PerformAboutRecord' on Order Number
And Update Multiple MSISDNs
And Update Multiple ICCIDs
And Update Multiple VSM
And Reserve Order
And Perform Credit Vet
And Submit the Order 'Normal Submit'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
And Rate a 'VoiceRatingUKToIndia' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a '1RoamingSMSQuantity' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'MMSRoamingBrazil' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'RoamingBrazil' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDNMoreThanThreshold'
And Unbilled Type of Usage Adjustment selection 'CheckVoiceUsage'

Scenario: INV26 CR_91828_BRM_Invoicing_TC21_BRM to BIP_Perform Invoice of Transfer of service from 
          Non-paying child to Parent’s account
@Ankesh
Meta:@INV261
@FolderName INV26
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account '7000771495'
When Execute query 'RetrieveNonpayingChildAccount'
Given Generate Month End Bill with Account 'ACCOUNTNO0'
Given Generate Month End Bill with Account 'AccountNoIndex1'
Given Generate Month End Invoice with Account 'AccountNo'
And Login to BIP with 'LoginToBIP' for 'EBUInvoice'