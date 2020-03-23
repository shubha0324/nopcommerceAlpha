package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;

public class BaseClass {

	public Properties configPropObj;
	public WebDriver driver;
	
	public Logger logger=LogManager.getLogger("nopCommerce");

	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {

		// Load config.properties file
		configPropObj = new Properties();
		FileInputStream configfile = new FileInputStream(
				System.getProperty("user.dir") + "\\Configurations\\config.properties");
		configPropObj.load(configfile);
		
		//Opens browser
		if (br.equals("firefox")) {
			// Firefox Browser
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			// Chrome Browser
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
				
		// Global implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	

	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	
}