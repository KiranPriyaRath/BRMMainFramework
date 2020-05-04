Story: BRM Rating
Meta:@BRMStory
@tag tag:admin,tag:application,version:Release-02,version:Sprint-01.02

Narrative:
 BRM

Scenario:AppDirect01 Extract and validate the postpaidAdjustments report
Meta:@AppDirect0111
@FolderName AppDirect01
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000572610'
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
Then Unbilled Event Level Adjustment selection 'DrillDownToMsisdn'
And Unbilled Type of Usage Adjustment selection 'DrillDownToMarketPlaceUsageClickYes'
When Submit Adjustment 'GoodWillEventCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
And Login with 'ADJUSTADJ' in 'Env'
When Search The Account ''
When Execute query 'BillNumberCapture'
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'MarketUsageBillLevel'
And Event Level Adjustment selection 'MarketUsageAmountCapture'
When Submit Adjustment 'RestrictedFriends&Family'
And Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And DrillDown to Latest Bill 'DrillDownTobillAdjustment'
And Item Bill Level Adjustment 'DrillDownToMsisdn' 'MarketLevelAdjustment'
When Submit Adjustment 'GoodWillItemLevel'
When Search The Account ''
When DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Submit BillLevel Adjustment 'GoodWillItemCharge'
Given Login To Putty 'BRMPutty2' in 'Env'
When Verifying Adjustment Done on the Account
Given Perform Bulk Adjustment 'BulkAdjPYTaxcode'
When Execute query 'ProgramNameAdjValidation'
Then Generate Month End Report from SBExport for 'postpaidAdjustments'
Then Generate Month End Report from SBExport for 'DetailAdjustmentReportIndex1'


Scenario:AppDirect01 Extract and validate the postpaidAdjustments and DetailAdjustment report
Meta:@AppDirect01
@FolderName AppDirect01
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty2' in 'Env'
And Show All Purchase Products in the account '7000572655'
Given Perform Bulk Adjustment 'BulkAdjPYTaxcode'
When Execute query 'ProgramNameAdjValidation'
Then Generate Month End Report from SBExport for 'postpaidAdjustments'
Then Generate Month End Report from SBExport for 'DetailAdjustmentReportIndex1'

Scenario:AppDirect02 Extract and validate the postpaidBillingProfileFeed and postpaidInvoiceExport report
Meta:@AppDirect02
@FolderName AppDirect02
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000572633'
Given Generate First Bill
Then Generate Month End Report from SBExport for 'postpaidBillingProfileFeed'
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidInvoiceExport'


Scenario:AppDirect03 Extract and validate the vatReportCustom report
Meta:@AppDirect03
@FolderName AppDirect03
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'vatReportCustom'


Scenario:AppDirect04 Extract and validate the LateAccuredReport report
Meta:@AppDirect04
@FolderName AppDirect04
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Then Generate Month End Report from SBExport for 'LateAccrued'


Scenario:AppDirect05 Extract and validate the invoiceExtract report
Meta:@AppDirect05
@FolderName AppDirect05
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'invoiceExtract'

Scenario:AppDirect06 Extract and validate the ccPaymensExport.csv report
Meta:@AppDirect06
@FolderName AppDirect06
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000574212'
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievePendBalanceCheck'
And Make Payment for 'CCAccountNBilloption'
Then Generate Month End Report from SBExport for ''

Scenario:AppDirect07 Extract and validate the ddPaymensExport.csv report
Meta:@AppDirect07
@FolderName AppDirect07
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000574203'
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
Then Generate Month End Report from SBExport for 'DDRefundsExport'

Scenario:AppDirect08 Extract and validate the ddRefundsExport.csv report
Meta:@AppDirect08
@FolderName AppDirect08
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account '7000574203'
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
When Execute query 'RetrievpendbalanceCapture'
And Make Payment for 'DDAccount'
When Execute query 'PaymentReceivedDDCompare'
When Make Payment for 'ABCPayment'
Given Login with 'PAYREV' in 'Env'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Click On PaymentRefund Button 'LocateAmount10'
And Enter Refund Details 'RefundAmount'
When Search The Account ''
And DrillDown to BillingProfile 'DrillDownToBillingProfile'
And Payment Refund Reversal Validation 'LockBoxRefundValidation'
@-- generate report next day
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Then Generate Month End Report from SBExport for 'DDRefundsExport'


Scenario:AppDirect09 Extract and validate the usageDetail report
Meta:@AppDirect09
@FolderName AppDirect09
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'usageDetail'
Then Generate Month End Report from SBExport for 'usageDetailCustom'

Scenario:AppDirect10 Extract and validate the proofAndBalanceReport report
Meta:@AppDirect10
@FolderName AppDirect10
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'proofAndBalanceReport'


Scenario:AppDirect11 Extract and validate the recurringChargesOT report
Meta:@AppDirect11
@FolderName AppDirect11
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'recurringChargesOT'


Scenario:AppDirect12 Extract and validate the postpaidAccountCharges report
Meta:@AppDirect12
@FolderName AppDirect12
@Kiran
Given Pre-requisite with logs in folder 'FolderName'
Given Login To Putty 'BRMPutty1' in 'Env'
And Show All Purchase Products in the account ''
Given Generate First Bill
Given Rate a 'AppDirect' CDR
When Execute query 'ComparingtheRatingValue'
Given Generate Month End Bill with Account 'AccountNo'
And Generate Month End Invoice with Account 'AccountNo'
Then Generate Month End Report from SBExport for 'postpaidAccountCharges'