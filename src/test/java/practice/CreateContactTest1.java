package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest1 {

	public static void main(String[] args) throws IOException {
		//converting physical representation file into java representation Object
		FileInputStream fisForExcel=new FileInputStream("./src/test/resources/excelData1.xlsx");
		//open workbook in read mode
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		//generating random contact
		Random random = new Random();
		int value=random.nextInt(1000);
		String lastName = workbook.getSheet("sheet3").getRow(1).getCell(2).getStringCellValue()+value;
		
		
		
		WebDriver driver=null;
		
		//converting physical representation file into java representation Object
		FileInputStream fisForProperty=new FileInputStream("./propertyFile.properties");
		//creating object of property class to load all the keys
		Properties property=new Properties();
		//loading keys 
		property.load(fisForProperty);
		//reading keys value
		String BROWSER = property.getProperty("browser");
		String URL = property.getProperty("url");
		String USERNAME = property.getProperty("username");
		String PASSWORD = property.getProperty("password");
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
		 driver = new FirefoxDriver();
		}
		else System.out.println("enter chrome only");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualResult = lastName;
		String expectedResult = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(expectedResult.contains(actualResult)) {
		//	FileInputStream fis=new FileInputStream("./src/test/resources/excelData1.xlsx");
			//open workbook in read mode
			//Workbook wb = WorkbookFactory.create(fis);
			workbook.getSheet("sheet3").getRow(1).createCell(3).setCellValue("pass");
			//open same workbook in write mode and save the file
			FileOutputStream fos=new FileOutputStream("./src/test/resources/excelData1.xlsx");
			workbook.write(fos);
			workbook.close();
		}
		else System.out.println("fail:the contact is not created successfully");
		
Actions action = new Actions(driver);
action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
driver.findElement(By.linkText("Sign Out")).click();

	}

}
