package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOppurtinuityUsingContact {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		//generating random numbers
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		//getting the data from property file
		FileInputStream fisForProperties=new FileInputStream("./src/test/resources/propertyFile.properties");
		Properties property=new Properties();
		property.load(fisForProperties);
		String BROWSER=property.getProperty("browser");
		String URL=property.getProperty("url");
		String USERNAME=property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		
		FileInputStream fisForExcel=new FileInputStream("./src/test/resources/excelData1.xlsx");
		Workbook workbook = WorkbookFactory.create(fisForExcel);
		Sheet sheet = workbook.getSheet("sheet2");
		String organizationName = sheet.getRow(2).getCell(2).getStringCellValue()+randomNumber;
		String firstName = sheet.getRow(2).getCell(3).getStringCellValue()+randomNumber;
		String lastName = sheet.getRow(2).getCell(4).getStringCellValue()+randomNumber;
		String OpportunityName=sheet.getRow(3).getCell(6).getStringCellValue()+randomNumber;
		Select select=new Select(driver.findElement(By.id("related_to_type")));
		
		
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else System.out.println("browser not found");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//creating contact
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualOrganization = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(actualOrganization.contains(organizationName)) {
			System.out.println("Pass: organization created successfully");
		}
		else System.out.println("FAIL: organization NOT created successfully");
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		Select selectSalutation=new Select(driver.findElement(By.name("salutationtype")));
		selectSalutation.selectByValue("Mr.");
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//td[text()='Organization Name 			']/parent::tr/descendant::img")).click();
		String parent = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		for(String child:window) {
			driver.switchTo().window(child);
			if(!child.equals(parent)) {
				driver.findElement(By.id("search_txt")).sendKeys(organizationName+Keys.ENTER);
				driver.findElement(By.linkText(organizationName)).click();
			}
		}
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String actualContact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actualContact.contains(lastName)) {
			System.out.println("PASS: contact created successfully");
		}
		else System.out.println("Fail: contact not created successfully");
		
		//creating opportunity
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		

	}

}
