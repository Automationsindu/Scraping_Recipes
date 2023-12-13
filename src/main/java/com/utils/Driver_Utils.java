package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver_Utils {
	
	public static WebDriver driver;
	
	public Driver_Utils()
	{
		driver=driverSetUp();
	}
	
	public static WebDriver driverSetUp()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://www.tarladalal.com/");
		return driver;
		
	}

}
