package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
		 
	    //options.addArguments("--proxy-server=" + proxy + ":" + proxyPort);
		driver=new ChromeDriver(options);
		driver.get("https://www.tarladalal.com/");
		driver.manage().window().maximize();
		//driver
		return driver;
		
	}

}
