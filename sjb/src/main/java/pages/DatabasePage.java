package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utilities.ReadWorkbook;
import utilities.ReportMessege;
import utilities.SikuliUtility;

public class DatabasePage extends PageObject{
	@Steps
	ReportMessege report;
	@Steps
	private ReadWorkbook readWorkbook;
	@Steps
	private SikuliUtility sikuliUtility;
	
	String driverName = "oracle.jdbc.driver.OracleDriver";
	public Connection ConnectionDetails(String sEnv) throws SQLException{

		String Env = Serenity.sessionVariableCalled("Env").toString();
		String sEnv1 = Env + sEnv;
		String strConn = null;
		Connection con=null;
		if (sEnv1.equals("E7")){
			// strConn = "\"jdbc:oracle:thin:@10.78.195.74:1522:DEVCRM\",\"SIEBEL\",\"SIEBEL\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.78.195.74:1522:DEVCRM","SIEBEL","SIEBEL");
		}
		if (sEnv1.equals("E4")){
			//  strConn = "\"jdbc:oracle:thin:@10.78.193.202:1522:DEVCRM\",\"SIEBEL\",\"SIEBEL\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.78.193.202:1522:DEVCRM","SIEBEL","SIEBEL");
		}
		if (sEnv1.equals("C2")){
			//strConn = "\"jdbc:oracle:thin:@10.78.221.7:1522:DEVCRM\",\"SIEBEL\",\"SIEBEL\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.78.221.7:1522:DEVCRM","SIEBEL","SIEBEL");
		}
		if (sEnv1.equals("SUP02")){
			//strConn = "\"jdbc:oracle:thin:@10.78.221.7:1522:DEVCRM\",\"SIEBEL\",\"SIEBEL\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.78.196.94:1522:DEVCRM","SIEBEL","SIEBEL");
		}
		if (sEnv1.equals("C2BRM")){
			//strConn = "\"jdbc:oracle:thin:@NewVoC2-dbbrm01:1521:DEVBRM\",\"pin\",\"pin\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@NewVoC2-dbbrm01:1521:DEVBRM","pin","pin");
		}
		if (sEnv1.equals("E7BRM")){
			//strConn = "\"jdbc:oracle:thin:@NewVoE7-dbbrm01:1521:DEVBRM\",\"pin\",\"pin\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@NewVoE7-dbbrm01:1521:DEVBRM","pin","pin");
		}
		if (sEnv1.equals("E4BRM")){
			// strConn = "\"jdbc:oracle:thin:@NewVoE4-dbbrm01:1521:DEVBRM\",\"pin\",\"pin\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@NewVoE4-dbbrm01:1521:DEVBRM","pin","pin");
		}
		//con = DriverManager.getConnection(strConn);
		if (sEnv1.equals("SUP02BRM")){
			// strConn = "\"jdbc:oracle:thin:@10.78.195.74:1522:DEVCRM\",\"SIEBEL\",\"SIEBEL\"";
			con = DriverManager.getConnection("jdbc:oracle:thin:@NewVoSUP02-dbbrm01:1521:DEVBRM","pin","pin");
		}


		return con;


	}



	@Step
	public void ExecuteDBQuery (String accountType) throws ClassNotFoundException, SQLException, IOException,InterruptedException  {

		//String sAccountNo = Serenity.sessionVariableCalled("AccountNo").toString();

		//Serenity.sessionVariableCalled("AccountNo").toString()
		Connection con =null;
		String filePath = "\\src\\test\\resources\\data\\Database.xls";        

		String dataSheet = "ExecuteDBQuery";
		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(accountType, filePath, dataSheet);
		readWorkbook.testData(tableMap);


		for (int i = 0;i < tableMap.get("DD").size();i++) {
			String sDD = tableMap.get("DD").get(i);
			String sResult = tableMap.get("Result").get(i);	
			String sKeys = tableMap.get("Keys").get(i);
			String[] sKeysArr = sKeys.split(Pattern.quote(";"));
			String sDB = tableMap.get("DB").get(i);
			String sAction = tableMap.get("Action").get(i);

			Class.forName("oracle.jdbc.driver.OracleDriver");  
			con = this.ConnectionDetails(sDB);
			Statement stmt=con.createStatement();
			ResultSet rs = null;

			String filePath1 = "\\src\\test\\resources\\data\\Database.xls";
			String dataSheet1 = "Queries";

			Map <String,Map<Integer, String>> tableMap1 = readWorkbook.readRow(sDD, filePath1, dataSheet1);
			readWorkbook.testData(tableMap1);
			String sQuery = tableMap1.get("Query").get(0);
			//report.Info("Query is:  "+sQuery);
			for (int j =0;j < sKeysArr.length; j++){
				int k = j+1;
				try{

					String keyVal = Serenity.sessionVariableCalled(sKeysArr[j]).toString();
					sQuery = sQuery.replace("Key" + k , keyVal);
					//            report.Info("Query3 is:  "+sQuery);
					//report.Info("Query3 is:  "+sQuery);
				}
				catch(NullPointerException e){
					sQuery = sQuery.replace("Key" + k , sKeysArr[j]);
					report.Info("NullPointerException:  "+sQuery);

				}
			}
			report.Info("Query is:  "+sQuery);
			try {
				rs=stmt.executeQuery(sQuery); 

				int j = 0;
				if (sAction.equals("Capture")){
					while (rs.next()){
						int numCols = rs.getMetaData().getColumnCount();
						for ( int i1 = 1 ; i1 <= numCols ; i1++ ) {
							System.out.println("Database reuslt"+rs.getMetaData().getColumnName(i1).concat(Integer.toString(j)));
							report.Info("Database reuslt"+rs.getMetaData().getColumnName(i1).concat(Integer.toString(j))+rs.getString(i1));
							Serenity.setSessionVariable(rs.getMetaData().getColumnName(i1).concat(Integer.toString(j))).to(rs.getString(i1)); 

						}
						j = j + 1;

					}

					Assert.assertTrue("No rows returned",!(j==0));


				}
				else if (sAction.equals("Add")){
					double sActResult = 0;
					boolean flag1 =  false;

					DecimalFormat df = new DecimalFormat("#.######");
					df.setRoundingMode(RoundingMode.HALF_EVEN);
					while (rs.next()){
						int numCols = rs.getMetaData().getColumnCount();
						for ( int i1 = 1 ; i1 <= numCols ; i1++ ) {

							sActResult = Double.parseDouble(df.format(sActResult)) + Double.parseDouble(df.format(Double.parseDouble(rs.getString(i1))));
							sActResult = Double.parseDouble(df.format(sActResult));
						}
						j = j + 1;
						flag1 = true;
					}
					if (flag1 == false){
						Assert.assertTrue("No rows found", false);
					}

					if (sActResult == Double.parseDouble(sResult)){
						report.Info("Value is " + sResult + " as expected");
						Assert.assertTrue("Value is " + sResult + " as expected",true);
					}
					else{
						Assert.assertTrue("Actual value is " + sActResult + " but expected is " + sResult,false);
					}

				}
			 	else if (sAction.equals("Compare")){
					String[] sResultArr = sResult.split(Pattern.quote("+"));
					while (rs.next()){
						//int numCols = rs.getMetaData().getColumnCount();
						String[] sResultArr2 = sResultArr[j].split(Pattern.quote(";"));
						for ( int i1 = 0 ; i1 < sResultArr2.length ; i1++ ) {
							
							//String[] sResultArr2 = sResultArr[j].split(Pattern.quote(";"));
							if (sResultArr2[i1].startsWith("-")){
								sResultArr2[i1] = sResultArr2[i1].replace("-", "");
								try{

									String keyVal = Serenity.sessionVariableCalled(sResultArr2[i1]).toString();
									sResultArr2[i1] = sResultArr2[i1].replace(sResultArr2[i1], keyVal);

								}
								catch(NullPointerException e){}
								sResultArr2[i1] = "-" + sResultArr2[i1];
							}
							try{

								String keyVal = Serenity.sessionVariableCalled(sResultArr2[i1]).toString();
								sResultArr2[i1] = sResultArr2[i1].replace(sResultArr2[i1], keyVal);

							}
							catch(NullPointerException e){}
							if (sResultArr2[i1].equals(rs.getString(i1+1))){
								report.Info("Value is " + sResultArr2[i1] + " as expected");
							}
							else{
								Assert.assertTrue("Actual value is " + rs.getString(i1+1) + " but expected is " + sResultArr2[i1], false);
							}

						}
						j = j + 1;

					}

					Assert.assertTrue("No rows returned",!(j==0));


				}
			} catch (SQLException ex){
				
				report.Info("Failed while executing query:"+ex.getMessage());
				Assert.assertTrue(false);
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
			}
		}

	}




	@Step
	public void TryReserveAvailableICCID() throws ClassNotFoundException, SQLException{

		Class.forName("oracle.jdbc.driver.OracleDriver");  
		//String strConn = this.ConnectionDetails("SUP02");                        
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		ResultSet rs = null;
		String sICCID = "";
		String Env = Serenity.sessionVariableCalled("Env").toString();
		String sQuery = "select ICCID from ICCID_Repository where status = 'Available' and Environment = '"+Env+"' and rownum =1";
		try {
			rs=stmt.executeQuery(sQuery); 

			if (!rs.next()) {
				Assert.assertTrue("No ICC ID available.", false);
			} else {
				//display results
				do {
					sICCID = rs.getString(1);
					Serenity.setSessionVariable("ICCID").to(sICCID);
					report.Info("ICCID :"+sICCID);
				} while (rs.next());
			}
			sQuery = "update ICCID_Repository set status = 'Reserved' where ICCID = '"+sICCID+"'";
			stmt.executeQuery(sQuery);
		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}


	} 

	@Step
	public void TryReserveAvailableMSISDN() throws ClassNotFoundException, SQLException{

		Class.forName("oracle.jdbc.driver.OracleDriver");  
		//String strConn = this.ConnectionDetails("SUP02");                        
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		ResultSet rs = null;
		String sMSISDN = "";
		String Env = Serenity.sessionVariableCalled("Env").toString();
		String sQuery = "select MSISDN from MSISDN_Repository where status = 'Available' and Environment = '"+Env+"' and rownum =1";
		try {
			rs=stmt.executeQuery(sQuery); 
			if (!rs.next()) {
				Assert.assertTrue("No MSISDNS available", false);
			} else {
				//display results
				do {
					sMSISDN = rs.getString(1);
					Serenity.setSessionVariable("MSISDN").to(sMSISDN);
					report.Info("MSISDN :"+sMSISDN);


				} while (rs.next());
			} 

			sQuery = "update MSISDN_Repository set status = 'Reserved' where MSISDN = '"+sMSISDN+"'";
			stmt.executeQuery(sQuery);
		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}      
	@Step
	public void RetrievePromotionDetails(String productKey) throws IOException, ClassNotFoundException, SQLException{
		String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";
		String dataSheet = "RetrievePromotionDetails";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(productKey, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		ResultSet rs = null;

		String Env = Serenity.sessionVariableCalled("Env").toString();

		for (int i = 0;i < tableMap.get("Promotion").size();i++) {
			String sPromotion = tableMap.get("Promotion").get(i);
			Serenity.setSessionVariable("PromotionDD").to(sPromotion);
			String sQuery = "SELECT PART_NUMBER FROM PRODUCT_DATA WHERE ENVIRONMENT='"+Env+"' AND KEY='"+sPromotion+"'";
			String partNo = "";
			try {
				rs=stmt.executeQuery(sQuery); 

				if (!rs.next()) {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) { /* ignored */}
					}
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) { /* ignored */}
					}
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) { /* ignored */}
					}
					Assert.assertTrue("Part number not found in Automation DB", false);
				} else {
					//display results
					do {
						partNo = rs.getString(1);
						Serenity.setSessionVariable("PartNo").to(partNo);
						report.Info("PartNo: " + partNo);

					} while (rs.next());
				}

			} catch (SQLException ex){
				System.out.print(ex.getMessage());
				report.Info(ex.getMessage());
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
			}
			con = this.ConnectionDetails("");//to be left blank
			//String strConn = this.ConnectionDetails("");//to be left blank
			//con=DriverManager.getConnection(strConn);
			//con=DriverManager.getConnection("jdbc:oracle:thin:@10.78.193.202:1522:DEVCRM","SIEBEL","SIEBEL");
			stmt=con.createStatement();
			sQuery = "SELECT NAME FROM S_PROD_INT WHERE PART_NUM='"+partNo+"'";

			try {
				rs=stmt.executeQuery(sQuery); 

				if (!rs.next()) {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) { /* ignored */}
					}
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) { /* ignored */}
					}
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) { /* ignored */}
					}
					Assert.assertTrue("Part Number not found in Siebel DB", false);

				} else {
					//display results
					do {
						String promotionName = rs.getString(1);
						Serenity.setSessionVariable("ProductName").to(promotionName);
						report.Info("ProductName: " + promotionName);

					} while (rs.next());
				}


			} catch (SQLException ex){
				System.out.print(ex.getMessage());
				report.Info(ex.getMessage());
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}

			}

			sQuery = "select spi2.name from s_prom_item spm, s_prod_int spi, s_prod_int spi2 where spm.promotion_id = spi.row_id and " +
					"spm.prod_id = spi2.row_id " +
					"and spi2.name in ('Mobile phone service','Mobile broadband service','EBU Sharer','PAYM','CBU Sharer','VOXI Service','Fixed Service','One Net Fixed Data Service') " +
					// "and spi2.name in ('Mobile phone service','Mobile broadband service','Fixed Service','One Net Fixed Data Service','Fixed Line Service','Fixed Broadband Service','EBU Sharer','PAYM') " +
					// "and spi2.name in ('Mobile phone service','Mobile broadband service','Fixed Service','One Net Fixed Data Service') " +
					"and spm.dflt_qty = '1' " +
					"and spi.part_num = '"+partNo+"'";

			try {
				rs=stmt.executeQuery(sQuery); 


				//display results
				while (rs.next()) {
					String rootProduct = rs.getString(1);
					Serenity.setSessionVariable("RootProduct0").to(rootProduct);
					report.Info("RootProduct: " + rootProduct);


				} 

			} catch (SQLException ex){
				System.out.print(ex.getMessage());
				report.Info(ex.getMessage());
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
			}


		}

	}    

	@Step
	public void RetrieveAccount(String logicalName) throws IOException, ClassNotFoundException, SQLException{
		String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";
		String dataSheet = "RetrieveAccount";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(logicalName, filePath, dataSheet);
		readWorkbook.testData(tableMap);


		String sDataDefinition = tableMap.get("DataDefinition").get(0);
		String sRootProduct = tableMap.get("RootProduct").get(0);

		Class.forName("oracle.jdbc.driver.OracleDriver");                          
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		String Env = Serenity.sessionVariableCalled("Env").toString();

		ResultSet rs = null;

		String sQuery = "SELECT QUERY_SQL, QUERY_TYPE, PROMOTION_KEY FROM MINING_QUERY WHERE ENVIRONMENT='"+Env+"'  AND QUERY_NAME='" + sDataDefinition + "'";
		String vQuerySQL = null;
		String vQueryType = null;
		String vPromotionKey = null;
		String accountNo = null;
		String accntMsisdn = null;
		try {
			rs=stmt.executeQuery(sQuery); 

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
				Assert.assertTrue("No Results returned in mining query table for " +sDataDefinition , false);
			} else {
				//display results
				do {
					vQuerySQL = rs.getString(1);
					vQueryType = rs.getString(2);
					vPromotionKey = rs.getString(3);
					//Serenity.setSessionVariable("PartNo").to(partNo);
					//report.Info("PartNo: " + partNo);

				} while (rs.next());
			}
		}
		catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
		if (vPromotionKey != null){
			this.RetrievePromotionDetails(vPromotionKey);
			try{

				String keyVal = Serenity.sessionVariableCalled("RootProduct0").toString();
				vQuerySQL = vQuerySQL.replace("RootProduct", keyVal);

			}
			catch(NullPointerException e){}

			vQuerySQL = vQuerySQL + " and exists (select 1 from s_asset sa, s_prod_int spi where spi.name = '" + Serenity.sessionVariableCalled("ProductName").toString() + "' and spi.row_id = sa.prod_id and sa.owner_accnt_id = soe.row_id and sa.status_cd = 'Active' and sip.row_id = sa.bill_profile_id) ";
		}
		vQuerySQL = vQuerySQL +" AND ( ( soe.url NOT LIKE '#B') OR ( soe.url IS NULL ) OR ( soe.url = 'Corrupt'))";
		vQuerySQL = vQuerySQL.replace("SELECT soe.row_id", "SELECT soe.x_vf_customer_code CUSTOMER_CODE");
		vQuerySQL = vQuerySQL + " and rownum = 1 order by soe.created desc ";

		con = this.ConnectionDetails("");//to be left blank

		//con=DriverManager.getConnection(strConn);
		stmt=con.createStatement();


		try {
			rs=stmt.executeQuery(vQuerySQL); 

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
				Assert.assertTrue("There are no clean source entities for this scenario.", false);

			} else {
				//display results
				do {
					accountNo = rs.getString(1);
					Serenity.setSessionVariable("AccountNo").to(accountNo);
					report.Info("AccountNo: " + accountNo);

				} while (rs.next());
			}


		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}

		}

		sQuery = "select distinct z.service_num from s_org_ext soe , s_order o, s_order_item z where o.customer_id = soe.row_id and o.row_id = z.order_id and soe.x_vf_customer_code in ('" + Serenity.sessionVariableCalled("AccountNo").toString() + "') and z.service_num like '4%'";

		try {
			rs=stmt.executeQuery(sQuery); 

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
				Assert.assertTrue("MSISDN not found for account number "+Serenity.sessionVariableCalled("AccountNo").toString() , false);

			} else {
				//display results
				do {
					accntMsisdn = rs.getString(1);
					Serenity.setSessionVariable("ACCNTMSISDN").to(accntMsisdn);
					report.Info("ACCNTMSISDN: " + accntMsisdn);

				} while (rs.next());
			}


		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}

		}
		vQuerySQL = "update s_org_ext soe set soe.url='#B' where soe.x_vf_customer_code ='"+Serenity.sessionVariableCalled("AccountNo").toString()+"'";
		try {
			rs=stmt.executeQuery(vQuerySQL); 




		} catch (SQLException ex){

			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}

	}  
	//---------
	@Step
	public void GetAddress(String logicalName) throws IOException, ClassNotFoundException, SQLException{
		String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";
		String dataSheet = "Address";
		String sQuery = "";


		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(logicalName, filePath, dataSheet);
		readWorkbook.testData(tableMap);
		String sType = tableMap.get("Type").get(0);
		String sCustomerType = tableMap.get("CustomerType").get(0);                  
		Class.forName("oracle.jdbc.driver.OracleDriver");                                          
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		ResultSet rs = null;
		String sFULLADDRESS = "";
		//-=======
		if (!sCustomerType.equals(""))
		{
			sQuery += " and CUSTOMERTYPE = '" + sCustomerType + "'";
			Serenity.setSessionVariable("CustomerType").to(sCustomerType);
		}
		if (sType.equals("SPAREPAIR"))
		{
			sQuery = "Select FullAddress,id from Address where type like 'Spare Pair' and status = 'Available'  and CUSTOMERTYPE = '" + sCustomerType + "' and rownum = 1";
			Serenity.setSessionVariable("InstallationType").to("New Line");
			Serenity.setSessionVariable("ExpectedLineInfo").to("Spare Pair");
		}
		else
		{
			if (sType.equals("BTWLTNUMRET"))
			{
				sQuery = "Select FullAddress, LANDLINE_NMBR, id  from Address where type like 'Working PSTN Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BTWLTNONUMRET"))
			{
				sQuery = "Select FullAddress, id  from Address where type like 'Working PSTN Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("MPFWLTNUMRET"))
			{
				sQuery = "Select FullAddress, LANDLINE_NMBR, id  from Address where type like 'MPF Working Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("MPFWLTNONUMRET"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'MPF Working Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BTRESTARTSTOPPED"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'PSTN Stopped Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("MPFRESTARTSTOPPED"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'MPF Stopped Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BTRESTARTSTOPPED") || sType.equals("BTWLTNUMRET") || sType.equals("BTWLTNUMRET"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'Working PSTN Line PSTN Stopped Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("NOLINEPLANT"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'No Spare pair' and status = 'Available' and rownum = 1";
			}

			else if (sType.equals("THROTTLEDFIBRE"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'Spare pair - Only Fibre' and status = 'Available' and rownum = 1";
			}

			else if (sType.equals("PSTNWLNUMRET"))
			{

				sQuery = "Select FullAddress, LANDLINE_NMBR, id  from Address where type like 'PSTN Working Line Takeover' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BTRESTARTSTOPPEDEBU"))
			{
				sQuery = "Select FullAddress, LANDLINE_NMBR, id from Address where type like 'PSTN Stopped Line' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("PreOrderStandardFTTH"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'PreOrderStandardFTTH' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("PreOrderNonStandardFTTH"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'PreOrderNonStandardFTTH' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("PreOrderExtendedStandardFTTH"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'PreOrderExtendedStandardFTTH' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BlankNonStandardFTTH"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'BlankNonStandardFTTH' and status = 'Available' and rownum = 1";
			}
			else if (sType.equals("BlankExtendedStandardFTTH"))
			{
				sQuery = "Select FullAddress, id from Address where type like 'BlankExtendedStandardFTTH' and status = 'Available' and rownum = 1";
			}
		}
		String RetentionNumber="";
		//=======
		//sQuery = "select FULLADDRESS from ADDRESS where STATUS = 'Available' and TYPE =" + sType + "'";
		try {
			rs=stmt.executeQuery(sQuery); 
			if (!rs.next()) {
				Assert.assertTrue("No Addresses available", false);
			} else {
				//display results
				do {
					sFULLADDRESS = rs.getString(1);
					String sLandLine = rs.getString(2);
					Serenity.setSessionVariable("ADDRESS").to(sFULLADDRESS);
					report.Info("ADDRESS :"+sFULLADDRESS);
					if(!sQuery.contains("LANDLINE_NMBR")){
						Serenity.setSessionVariable("RetentionNumber").to("");
						RetentionNumber = Serenity.sessionVariableCalled("RetentionNumber").toString();
					}
					else if(sQuery.contains("LANDLINE_NMBR")){

						Serenity.setSessionVariable("RetentionNumber").to(sLandLine); 
						RetentionNumber = Serenity.sessionVariableCalled("RetentionNumber").toString();
					}


				} while (rs.next());
			} 
			if (!sType.equals("SPAREPAIR")){


				sQuery = "update ADDRESS set STATUS = 'Reserved' where FULLADDRESS = '"+sFULLADDRESS+"'";
				stmt.executeQuery(sQuery);
			}
		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	} 
	@Step
	public void TryReserveRouter() throws ClassNotFoundException, SQLException{

		Class.forName("oracle.jdbc.driver.OracleDriver");  
		String Env = Serenity.sessionVariableCalled("Env").toString();                        
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		ResultSet rs = null;
		String sROUTER = "";
		String sQuery = "select ROUTER from ROUTER_REPOSITORY where status = 'Available' and Environment = '"+Env+"' and rownum =1";

		try {
			rs=stmt.executeQuery(sQuery); 
			if (!rs.next()) {
				Assert.assertTrue("No ROUTERS available", false);
			} else {
				//display results
				do {
					sROUTER = rs.getString(1);
					Serenity.setSessionVariable("ROUTERNUMBER").to(sROUTER);
					report.Info("ROUTERNUMBER :"+sROUTER);


				} while (rs.next());
			} 

			sQuery = "update ROUTER_REPOSITORY set status = 'Reserved' where ROUTER = '"+sROUTER+"'";
			stmt.executeQuery(sQuery);
		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}
	@Step
	public void RetrieveAccountBRM(String logicalName) throws IOException, ClassNotFoundException, SQLException{
		String filePath = "\\src\\test\\resources\\data\\DataProxy.xls";
		String dataSheet = "RetrieveBRMAccount";

		Map <String,Map<Integer, String>> tableMap = readWorkbook.readRow(logicalName, filePath, dataSheet);
		readWorkbook.testData(tableMap);


		String sDataDefinition = tableMap.get("DataDefinition").get(0);
		String sRootProduct = tableMap.get("RootProduct").get(0);

		Class.forName("oracle.jdbc.driver.OracleDriver");                          
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@qtp.cqrpvr4944ct.eu-west-1.rds.amazonaws.com:1521:QTP","datamanagement","D2tamanagement");
		Statement stmt=con.createStatement();
		String Env = Serenity.sessionVariableCalled("Env").toString();

		ResultSet rs = null;

		String sQuery = "SELECT QUERY_SQL, QUERY_TYPE, PROMOTION_KEY FROM MINING_QUERY WHERE ENVIRONMENT='"+Env+"'  AND QUERY_NAME='" + sDataDefinition + "'";
		String vQuerySQL = null;
		String vQueryType = null;
		String vPromotionKey = null;
		String accountNo = null;
		String accntMsisdn = null;
		try {
			rs=stmt.executeQuery(sQuery); 

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
				Assert.assertTrue("No Results returned in mining query table for " +sDataDefinition , false);
			} else {
				//display results
				do {
					vQuerySQL = rs.getString(1);
					vQueryType = rs.getString(2);
					vPromotionKey = rs.getString(3);
					//Serenity.setSessionVariable("PartNo").to(partNo);
					//report.Info("PartNo: " + partNo);

				} while (rs.next());
			}
		}
		catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
		if (vPromotionKey != null){
			this.RetrievePromotionDetails(vPromotionKey);
			try{

				String keyVal = Serenity.sessionVariableCalled("RootProduct0").toString();
				vQuerySQL = vQuerySQL.replace("RootProduct", keyVal);

			}
			catch(NullPointerException e){}
			vQuerySQL = vQuerySQL.substring(0, vQuerySQL.lastIndexOf(")"));
			vQuerySQL = vQuerySQL + " and exists (select 1 from s_asset sa, s_prod_int spi where spi.name = '" + Serenity.sessionVariableCalled("ProductName").toString() + "' and spi.row_id = sa.prod_id and sa.owner_accnt_id = soe.row_id and sa.status_cd = 'Active' and sip.row_id = sa.bill_profile_id)) ";
		}
		vQuerySQL = vQuerySQL + " and rownum=1";

		con = this.ConnectionDetails("");//to be left blank

		//con=DriverManager.getConnection(strConn);
		stmt=con.createStatement();


		try {
			report.Info(vQuerySQL);
			rs=stmt.executeQuery(vQuerySQL); 

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) { /* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) { /* ignored */}
				}
				Assert.assertTrue("There are no clean source entities for this scenario.", false);

			} else {
				//display results
				do {
					accountNo = rs.getString(1);
					accntMsisdn = rs.getString(2);
					Serenity.setSessionVariable("AccountNo").to(accountNo);
					Serenity.setSessionVariable("ACCNTMSISDN").to(accntMsisdn);

					report.Info("AccountNo: " + accountNo);
					report.Info("ACCNTMSISDN: " + accntMsisdn);

				} while (rs.next());
			}


		} catch (SQLException ex){
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}

		}




		vQuerySQL = "update s_org_ext soe set soe.url='#B' where soe.x_vf_customer_code ='"+Serenity.sessionVariableCalled("AccountNo").toString()+"'";
		try {
			rs=stmt.executeQuery(vQuerySQL); 




		} catch (SQLException ex){

			report.Info(ex.getMessage());
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { /* ignored */}
			}
		}

	}
	
	public void UpdateMSISDNMultiple_fn() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = this.ConnectionDetails("");
		Statement stmt = con.createStatement();
		Statement stmt1 = con.createStatement();

		ResultSet rs = null;
		String RootProduct = Serenity.sessionVariableCalled("RootProduct0").toString();
		String OrderRowId = Serenity.sessionVariableCalled("RowId").toString();

		String sQuery = "select i.row_id from s_order_item i, s_prod_int p where order_id='" + OrderRowId
				+ "' and p.row_id=i.prod_id and p.name='" + RootProduct + "'";

		try {
			rs = stmt.executeQuery(sQuery);

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				Assert.assertTrue("No Root Product available.", false);

			} else {
				do {
					String vRootProductRowId = rs.getString(1);
					this.TryReserveAvailableMSISDN();
					String MSISDN = Serenity.sessionVariableCalled("MSISDN").toString();
					String sQueryMSISDN = "update siebel.s_order_item set service_num = '" + MSISDN
							+ "' where row_id = '" + vRootProductRowId + "'";

					ResultSet rs1 = null;
					rs1 = stmt1.executeQuery(sQueryMSISDN);

					if (!rs1.next()) {
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						Assert.assertTrue("No MSISDNS available", false);
					} else {
						// i= i+1;
						report.Info("MSISDN :" + MSISDN + "for Row ID : " + vRootProductRowId);
					}

				} while (rs.next());

			}

		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}
	public void UpdateICCIDMultiple_fn() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = this.ConnectionDetails("");
		Statement stmt = con.createStatement();
		Statement stmt1 = con.createStatement();

		ResultSet rs = null;
		String OrderRowId = Serenity.sessionVariableCalled("RowId").toString();

		String sQuery = "select i.row_id from s_order_item i, s_prod_int p where order_id='" + OrderRowId
				+ "' and p.row_id=i.prod_id and p.name='Blank White Triple Format SIM'";

		try {
			rs = stmt.executeQuery(sQuery);

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				Assert.assertTrue("No SIM available.", false);

			} else {
				// display results
				// int i=1;
				do {
					String vRootProductRowId = rs.getString(1);
					this.TryReserveAvailableICCID();
					String ICCID = Serenity.sessionVariableCalled("ICCID").toString();
					String sQueryICCID = "update siebel.s_order_item set service_num = '" + ICCID + "' where row_id = '"
							+ vRootProductRowId + "'";

					ResultSet rs1 = null;
					rs1 = stmt1.executeQuery(sQueryICCID);

					if (!rs1.next()) {
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						Assert.assertTrue("No ICCID available", false);
					} else {
						// i= i+1;
						report.Info("ICCID :" + ICCID + "for Row ID : " + vRootProductRowId);
					}
				} while (rs.next());
			}

		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}
	
	@Step
	public void UpdateVSMMultiple_fn() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = this.ConnectionDetails("");
		Statement stmt = con.createStatement();
		Statement stmt1 = con.createStatement();

		ResultSet rs = null;
		String OrderRowId = Serenity.sessionVariableCalled("RowId").toString();

		String sQuery = "select i.row_id from s_order_item i, s_prod_int p where order_id='" + OrderRowId
				+ "' and p.row_id=i.prod_id and p.name like 'Vodafone Spend Manager%'";

		try {
			rs = stmt.executeQuery(sQuery);

			if (!rs.next()) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						/* ignored */}
				}
				Assert.assertTrue("VSM not available.", false);

			} else {
				// display results
				// int i=1;
				do {
					String vRootProductRowId = rs.getString(1);
					// this.TryReserveAvailableICCID();
					// String ICCID = Serenity.sessionVariableCalled("ICCID").toString();
					String sQueryICCID = "update S_ORDER_ITEM_X set X_VF_VBC_SETTING = 'off' where row_id = '"
							+ vRootProductRowId + "'";

					ResultSet rs1 = null;
					rs1 = stmt1.executeQuery(sQueryICCID);

					if (!rs1.next()) {
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								/* ignored */}
						}
						Assert.assertTrue("No VSM available", false);
					} else {
						// i= i+1;
						report.Info("VSM off for " + vRootProductRowId);
					}
				} while (rs.next());
			}

		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
			report.Info(ex.getMessage());
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}
	

		  public void Popup() throws IOException, AWTException, InterruptedException {
		    JFrame parent = new JFrame();

		    JOptionPane.showMessageDialog(parent, "Printing complete");
		    sikuliUtility.SikuliScreenShot(Serenity.sessionVariableCalled("SikulifilePathForSavingFile").toString());
		    		    
		}

}









