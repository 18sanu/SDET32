package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetTheFirstTwoColoumnData {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/excelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		int rowCount = sheet.getLastRowNum();
		
	    for(int i=1;i<=rowCount;i++) {
	    	Row row = sheet.getRow(i);
	    	String firstColData = row.getCell(0).getStringCellValue();
	    String secondColData = row.getCell(1).toString();
            System.out.println(firstColData+"/t "+secondColData);
	    }
	
	}

}
