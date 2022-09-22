package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RunTestScriptUsingMultipleData {

	@DataProvider
	public Object[][] dataProviderForTicketBooking(){
		Object[][] objArr=new Object[5][3];
		
		//adding values to array
		objArr[0][0]="Banglore";
		objArr[0][1]="Goa";
		objArr[0][2]=2000;
		
		objArr[1][0]="Banglore";
		objArr[1][1]="Mumbai";
		objArr[1][2]=3000;
		
		objArr[2][0]="Banglore";
		objArr[2][1]="Chennai";
		objArr[2][2]=5000;
		
		objArr[3][0]="Banglore";
		objArr[3][1]="Ladakh";
		objArr[3][2]=7000;
		
		objArr[4][0]="Banglore";
		objArr[4][1]="Kashmir";
		objArr[4][2]=10000;
		return objArr;
		
	}
	
	@Test (dataProvider = "dataProviderForTicketBooking")
	public void ticketBookingTest(String from, String to ,int price) {
		System.out.println("travelling from "+from+" to "+to+" and the price is "+price);
	}
}
