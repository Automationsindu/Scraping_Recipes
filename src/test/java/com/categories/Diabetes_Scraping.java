package com.categories;


import com.utils.Driver_Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Diabetes_Scraping {

	static WebDriver driver;
	
	public static void main(String[] args)
	{
		driver = Driver_Utils.driverSetUp();
		GetDiabetesPage();
	}
	
	private static void GetCurrentPageRecipes(int pageNo) {
		List<WebElement> allRecipe = driver.findElements(By.xpath("//article"));
		List<String> urlsToClick = new ArrayList<String>();
		int counter=0;
		for (WebElement rec : allRecipe) 
		{ 
		    String receipeIdText = rec.findElement(By.xpath("div[@class='rcc_rcpno']/span")).getText();
		    System.out.println(receipeIdText);
		    
		    String receipeNameText = rec.findElement(By.xpath("div[@class='rcc_rcpcore']/span[@class='rcc_recipename']/a")).getText();
		    String receipeUrl = rec.findElement(By.xpath("div[@class='rcc_rcpcore']/span[@class='rcc_recipename']/a")).getAttribute("href");
		    System.out.println(receipeNameText);
		    System.out.println("PageNo:" + pageNo + " ReceipeUrl:"+receipeUrl);
		    urlsToClick.add(receipeUrl);
		    counter++;
		}
		counter=0;
		for(String url:urlsToClick) {
			
			driver.get(url);
			
			String prepTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
			String cookTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
			String totalTime = driver.findElement(By.xpath("//time[@itemprop='totalTime']")).getText();
			System.out.println("================================");
			System.out.println("PageNo: " + pageNo + " RecipeCount:" + counter + " ReceipeUrl: "+url);
			System.out.println("================================");
			System.out.println("prepTime:"+prepTime);
			System.out.println("cookTime:"+cookTime);
			System.out.println("totalTime:"+totalTime);
			System.out.println("----------Ingredients------------");
			List<WebElement> ingredients = driver.findElements(By.xpath("//div[@id='rcpinglist']//span[@itemprop='recipeIngredient']"));
			int i=1;
			for(WebElement ele:ingredients) {
				System.out.println(i + ":" + ele.findElement(By.xpath("a/span")).getText());
				i++;
			}
			
			System.out.println("----------Nutrients------------");
			List<WebElement> nutrients = driver.findElements(By.xpath("//table[@id='rcpnutrients']/tbody/tr/td/span"));
			
			int j=1;
			for(WebElement ele:nutrients) {
				System.out.println(j + ":" + ele.getAttribute("itemprop") +":"+ ele.getText());
				j++;
			}
			counter++;
	}}
	
	public static void GetDiabetesPage() 
	{
		
		System.out.println("The title of the page is "+ driver.getTitle());
		driver.findElement(By.xpath("//div[text()='RECIPES']")).click();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'recipes-for-indian-diabetic-recipes-370')]")));
		
		WebElement diabeticClick= driver.findElement(By.xpath("//a[contains(@href, 'recipes-for-indian-diabetic-recipes-370')]"));
		diabeticClick.click();
		
		int pageNo = 1;
		int lastPageNo = Integer.parseInt(driver.findElement(By.xpath("(//div[@id='pagination']/a)[last()]")).getText());
		
		while(pageNo <= lastPageNo) {
			String currentPageUrl = driver.getCurrentUrl();
			long startTime = System.currentTimeMillis();
			GetCurrentPageRecipes(pageNo);
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
		    System.out.println("pageNo :" + pageNo + " Elapsed MilliSeconds :" + elapsedTime);
			driver.get(currentPageUrl);
			driver.findElement(By.xpath("//div[@id='pagination']/a[@class='rescurrpg']/following-sibling::a")).click();
			pageNo++;
		}
		
	}
		
		
		
	
}




