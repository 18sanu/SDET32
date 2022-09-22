package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException {
		FileInputStream fisForExcel=new FileInputStream("./src/test/resources/excelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		
		Random random = new Random();
		int value=random.nextInt(1000);
		String organizationName = workbook.getSheet("sheet2").getRow(1).getCell(2).getStringCellValue()+value;
		
		
		
		WebDriver driver=null;
		FileInputStream fisForProperty=new FileInputStream("./propertyFile.properties");
		Properties property=new Properties();
		property.load(fisForProperty);
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
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualResult = organizationName;
		String expectedResult = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(expectedResult.contains(actualResult)) {
			FileInputStream fis=new FileInputStream("./src/test/resources/excelData1.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			wb.getSheet("sheet2").getRow(1).createCell(3).setCellValue("pass");
			
			FileOutputStream fos=new FileOutputStream("./src/test/resources/excelData1.xlsx");
			wb.write(fos);
		}
		else System.out.println("fail:the organization is not created successfully");
		
Actions action = new Actions(driver);
action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
driver.findElement(By.linkText("Sign Out")).click();

		
		

	}

}
