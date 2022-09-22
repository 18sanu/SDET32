package com.crm.comcast.GenericLib.SDET32;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	WebDriver  driver;
	@Parameters ("browser")
	@BeforeClass
	public void bcConfig(String browser) {
		if(browser.equals("chrome")) {
			//opens browser with setting as disable-popup 
			 ChromeOptions option = new ChromeOptions();
			 option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			
			 driver=new ChromeDriver();
		}
		
	}

}
