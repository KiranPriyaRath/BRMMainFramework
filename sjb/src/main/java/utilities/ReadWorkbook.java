package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ReadWorkbook extends PageObject{
		//private static final String FILE_NAME = "C:\\VATS Automation\\Automation\\NewCo\\Data\\Account.xls";
		
		
	@Steps
	ReportMessege report;	
		
		
		public Map<String, Map<Integer, String>> readRow(String row, String filePath, String dataSheet) throws IOException{
		
		//public static void main(String[] args) throws IOException{
			 Map <String,Map<Integer, String>> tableMap = new HashMap<String, Map<Integer, String>>();

		try{
			//String filePath = "\\src\\test\\resources\\data\\Account.xls";
			//String dataSheet = "CreateNewAccount";
			//String excelFilePath = "Books.xlsx";
			String currentDir = System.getProperty("user.dir");
			filePath = currentDir.concat(filePath);
			 
	        FileInputStream inputStream = new FileInputStream(new File(filePath));
	         
	        Workbook workbook = new HSSFWorkbook(inputStream);
	        
		//FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		//HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet datatypeSheet = (HSSFSheet) workbook.getSheet(dataSheet);
		
	        Iterator<Row> iterator = datatypeSheet.iterator();
		 //String row = "PostpaidConsumerdd";
		 
		 Row firstRow = iterator.next();
		 Iterator<Cell> firstRowCell = firstRow.iterator();
		 List<String> cellNames = new ArrayList<String>();
		 firstRowCell.next();
		 while (firstRowCell.hasNext()){
			 //
			 Cell cell = firstRowCell.next();
			 cellNames.add(cell.getStringCellValue());
		 }
		 List<Integer> totalRows = new ArrayList<Integer>();
		 String flag = "";
         while (iterator.hasNext()) {
        	 
             Row currentRow = iterator.next();
             //CellStyle currRow = currentRow.getCell(0).
             String currRow = currentRow.getCell(0).getStringCellValue();
              
             if (row.equals(currRow)){
            	 totalRows.add(currentRow.getRowNum());
            	 flag = "found";
             } else if (flag == "found"){
        		 break;
        	 }
              
         }
         if (!flag.equals("found")){
        	 report.Info("Logical Name " + row + " not found in the sheet " + dataSheet);
        	 Assert.assertTrue(false);        	 
         }
         
         int i = 1;
         for (String x : cellNames){
        	 int k = 0; 
        	 Map<Integer,String> entireRow = new HashMap<Integer,String>();
        	 for (Integer y : totalRows){
        		 Row currRow = datatypeSheet.getRow(y);
        		 String cellContent = "";
        		 if (currRow.getCell(i) == null){
        			 cellContent = "";
        		 }else if (currRow.getCell(i).getCellTypeEnum() == CellType.STRING){
        			 cellContent = currRow.getCell(i).getStringCellValue();
        		 }else if (currRow.getCell(i).getCellTypeEnum() == CellType.NUMERIC){
        			 cellContent = String.valueOf(currRow.getCell(i).getNumericCellValue());
        		 }
        		 else if (currRow.getCell(i).getCellTypeEnum() == CellType.BLANK){
        			 cellContent = "";
        		 }
        		 entireRow.put(k, cellContent);
        		 k = k + 1;
        	 }
        	 tableMap.put(x, entireRow);
        	 i =i +1;
        	 
         }
         
        
         //return tableMap;
        	// Map<String,Integer> cellValues = new HashMap<String,Integer>();
            // Iterator<Cell> cellIterator = currentRow.iterator();
            // int i =0;
           //  while (cellIterator.hasNext()) {

               //  Cell cell = cellIterator.next();
                 //getCellTypeEnum shown as deprecated for version 3.15
                 //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                // if (cell.getCellTypeEnum()==CellType.STRING){
              ///  	 cellValues.put(cell.getStringCellValue(), i);
              //   }
               //  i = i+1;
          //   }
            // System.out.println();
          

         
      
		}	catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (Exception e){
    	 e.printStackTrace();
     }
		return tableMap;
		

	
	}
		
	@Step
	public void testData(Map <String,Map<Integer, String>> tableMap){
		
		
	}
}
