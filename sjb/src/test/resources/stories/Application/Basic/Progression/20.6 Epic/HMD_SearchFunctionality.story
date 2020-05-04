Story: BRM Rating
Meta:@SearchFn
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM
 
Scenario: SearchFunctionality01 Validate in unbilled itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality01
@FolderName SearchFunctionality01
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy7times'
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
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'RatingPriceValidation'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDNMoreThanThreshold'
And Unbilled Type of Usage Adjustment selection 'CheckVoiceUsage'


Scenario: SearchFunctionality02 Validate in unbilled itemized view after selecting a service in search option when the number of services are less than threshold
@Kiran
Meta:@SearchFunctionality02
@FolderName SearchFunctionality02
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'RatingPriceValidation'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDN'
And Unbilled Type of Usage Adjustment selection 'CheckVoiceUsage'


Scenario: SearchFunctionality03 Validate in billed itemized view after selecting a service in search option when the number of services are more than threshold
@Kiran
Meta:@SearchFunctionality03
@FolderName SearchFunctionality03
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy4times'
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
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'RatingPriceValidation'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDNMoreThanThreshold' 'CheckVoiceUsage'


Scenario: SearchFunctionality04 Validate in billed itemized view after selecting a service in search option when the number of services are less than threshold
@Kiran
Meta:@SearchFunctionality04
@FolderName SearchFunctionality04
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'InternationalVoiceUsageToTariff535' CDR
When Execute query 'RatingPriceValidation'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'CheckVoiceUsage'

Scenario: SearchFunctionality05 Validate in unbilled itemized view after selecting an item charge when the number of events returned are more than threshold
@Kiran
Meta:@SearchFunctionality05
@FolderName SearchFunctionality05
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalCallFromUKtoTurkeyIndx3' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDN'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByDtUsageType'


Scenario: SearchFunctionality06 Validate in unbilled itemized view after selecting an item charge when the number of events returned are less than threshold
@Kiran
Meta:@SearchFunctionality06
@FolderName SearchFunctionality06
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceUSAToFrance11EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDN'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'SearchByDateAmt'
When Itemized List search 'SearchByUsageType'
When Itemized List search 'SearchByDiffDate'


Scenario: SearchFunctionality07 Validate in unbilled itemized view after applying search criteria when number of events returned are more than threshold
@Kiran
Meta:@SearchFunctionality07
@FolderName SearchFunctionality07
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceUSAToFrance11EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDN'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByAmt'

Scenario: SearchFunctionality08 Validate in unbilled itemized view after applying search criteria when number of events returned are less than threshold
@Kiran
Meta:@SearchFunctionality08
@FolderName SearchFunctionality08
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalCallFromUKtoTurkeyIndx3' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'SearchMSISDN'
And Unbilled Type of Usage Adjustment selection 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByDtPhNum'


Scenario: SearchFunctionality09 Validate in billed itemized view after selecting an item charge when the number of events returned are more than threshold
@Kiran
Meta:@SearchFunctionality09
@FolderName SearchFunctionality09
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByAmtUsageTypeDt'
When Itemized List search 'SearchByDtPhNum'
When Itemized List search 'SearchByPhNumAmt'


Scenario: SearchFunctionality10 Validate in billed itemized view after selecting an item charge when the number of events returned are less than threshold
@Kiran
Meta:@SearchFunctionality10
@FolderName SearchFunctionality10
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance4EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account '7000761363'
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'DrillDownToVoiceUsage'
When Itemized List search 'SearchByPhNum'
When Itemized List search 'SearchByPhNumAmount'
When Itemized List search 'SearchByAmtUsageType'


Scenario: SearchFunctionality11 Validate in billed itemized view after applying search criteria when number of events returned are more than threshold
@Kiran
Meta:@SearchFunctionality11
@FolderName SearchFunctionality11
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByDate'

Scenario: SearchFunctionality12 Validate in billed itemized view after applying search criteria when number of events returned are less than threshold
@Kiran
Meta:@SearchFunctionality12
@FolderName SearchFunctionality12
Given Pre-requisite with logs in folder 'FolderName'
And Login with 'AGENT04' in 'Env'
When Create the account type 'SmallSMEPostpay'
And Create 'NewOrderNoPopUp' 'NewPostpaid' Order
And Promotion is added with 'PostpaidPromotion107785'
And Select Menu 'PromotionSelection'
And Copy Line Item for scenario 'Copy2times'
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
Given Rate a 'VoiceRating7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'InternationalUsage7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Rate a 'VoiceRatingUSAToFrance7EDR' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
Given Login with 'RETAIL' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
When DrillDown to Latest Bill 'DrillDownTobillAdjustment'
When Item Bill Level Adjustment 'SearchMSISDN' 'DrillDownToVoiceUsage'
When Itemized List search 'HandleThreshold'
When Itemized List search 'SearchByPhNumAmt'