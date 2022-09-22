package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/excelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
            int  colCount=sheet.getRow(0).getLastCellNum();
            System.out.println(colCount);
            Object[][] data=new Object[rowCount][colCount];
            for(int i=0;i<rowCount;i++) {
            	for(int j=0;j<colCount;j++) {
            		try {
            		data[i][j]=sheet.getRow(i).getCell(j).toString();
            		}
            		catch(NullPointerException e){
            			continue;
            		}
            	}
            }
            
            System.out.println(data[0][0]);
            for(Object[] i:data) {
            	//for(Object j:i) {
            		System.out.print(i+"\t");
            //	}
            	System.out.println();
            }
	}

}
