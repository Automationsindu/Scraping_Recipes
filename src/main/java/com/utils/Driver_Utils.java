package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Driver_Utils {
	

	public static WebDriver driver;
	

	
	public static WebDriver driverSetUp()
	{
		WebDriverManager.chromedriver().setup();
		//String proxy="localhost";
		//int proxyPort=8080;
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--headless");
		 
	    //options.addArguments("--proxy-server=" + proxy + ":" + proxyPort);
		driver=new ChromeDriver(options);
		driver.get("https://www.tarladalal.com/");
		driver.manage().window().maximize();
		//driver
		return driver;
		
	}

	
//	public static WebDriver driver;
//	
//	@BeforeTest
//	public static WebDriver driverSetUp() throws IOException
//	{
//		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//global.properties");
//		Properties prop = new Properties();
//		prop.load(fis);
//		String url=prop.getProperty("Url");
//		ChromeOptions co = new ChromeOptions();
//	    co.addArguments("--remote-allow-origins=*");
//	    //co.addArguments("--headless");
// 	    co.setAcceptInsecureCerts(true);
// 	    FirefoxOptions fo= new FirefoxOptions();
//	    fo.addArguments("--remote-allow-origins=*");
//	    //fo.addArguments("--headless");
//	    fo.setAcceptInsecureCerts(true);
// 	    String browser_properties=prop.getProperty("browser");
//		String browser_maven=System.getProperty("browser");
//		// result = Test condition ? value1 : value2
//		String browser = browser_maven!=null ? browser_maven : browser_properties;	
//		if(driver == null) {
//			if(browser.equalsIgnoreCase("chrome")) {
//				driver = new ChromeDriver(co); 
//			}
//			if(browser.equalsIgnoreCase("firefox")) {
//				driver = new FirefoxDriver(fo); 
//			}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
//		driver.get(url);
//		driver.manage().window().maximize();
//		}
//		
//		return driver;
//		
//	}
//	
//	@AfterTest
//	 public void tearDown(){
//	     driver.close();
//	    }

}
