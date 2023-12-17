package com.categories;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.Driver_Utils;
import org.openqa.selenium.JavascriptExecutor;
public class RecipeFilters extends Driver_Utils {
	
	public static int counter=22;
	public static void main(String[] args) {
		driverSetUp();
	
		driver.findElement(By.xpath("//div[text()='RECIPES']")).click();
		WebElement hypoThyroidClick= driver.findElement(By.xpath("//a[contains(@href, 'recipes-for-hypothyroidism-veg-diet-indian-recipes-849')]"));
		hypoThyroidClick.click();
		List<WebElement> NoOfPages = driver.findElements(By.xpath("//div[@id='pagination']//a"));
		int TotalPages = NoOfPages.size();
		
		WebElement lastPageNoString = driver.findElement(By.xpath("//div[@id='pagination']//a["+TotalPages+"]"));
		int lastPageNo = Integer.parseInt(lastPageNoString.getText());
		System.out.println(lastPageNo);
		
		for (int j=1;j<=lastPageNo;j++)
		{
			WebElement nextPageLink = driver.findElement(By.xpath("//div[@id='pagination']//a[text()='"+j+"']"));
			nextPageLink.click();
			
			List<WebElement> receipeList = driver.findElements(By.xpath("//div[@class='recipelist']//article"));
			int receipeListCount = receipeList.size();
			
			for (int i=counter; i<=receipeListCount; i++)
			{				
				WebElement receipeName = driver.findElement(By.xpath("(//div[@class='recipelist']//article)["+i+"]/div[@class='rcc_rcpcore']/span/a"));
				//ReceipeNameList.put(i, ReceipeName.getText());
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", receipeName);
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
	//method 1 recipeId			
				WebElement receipeId = driver.findElement(By.xpath("(//div[@class='recipelist']//article)["+i+"]/div[@class='rcc_rcpno']/span"));
				String receiptIdSubstring = receipeId.getText();
				String finalRecipeId = receiptIdSubstring.split("\\n")[0].split(" ")[1];	
				System.out.println("Final RecipeId is "+ finalRecipeId);
				
				
	//method 2 morbid conditions list and check
	
				//List<WebElement> morbid=driver.findElements(By.xpath("//div[@class='recipelist']//article)["+i+"]"));
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		        String xpathExpression = "(//div[@class='recipelist']//article)[" + i + "]//div[@class='rcc_caticons']/img/@src";
		        		                   //"//div[@class='recipelist']//article)["+i+"]//div[@class='rcc_caticons']/img/@src";
		        String separator = "|";
		        
		        String concatenatedSrc = (String) jsExecutor.executeScript(
		                "var srcValues = document.evaluate(\"" + xpathExpression + "\", document, null, XPathResult.ANY_TYPE, null);" +
		                "var result = '';" +
		                "var srcNode = srcValues.iterateNext();" +
		                "while (srcNode) {" +
		                "    result += srcNode.value + '" + separator + "';" +
		                "    srcNode = srcValues.iterateNext();" +
		                "}" +
		                "return result;"
		        );

		     // Remove trailing separator
		        concatenatedSrc = concatenatedSrc.replaceAll(separator + "$", "");

		        System.out.println("Concatenated Src values for RecipeId " + finalRecipeId + ": " + concatenatedSrc);

		        // Check if any of the src values match morbid conditions
		        List<String> morbidConditions = Arrays.asList("diabetic", "low-cholestrol", "pcos");
		        for (String morbidCondition : morbidConditions) {
		            if (concatenatedSrc.contains(morbidCondition)) {
		                System.out.println("RecipeId " + finalRecipeId + " has morbid condition: " + morbidCondition);
		                // Add your logic for handling recipes with morbid conditions
		            }
				
				
	}
		        
 //method 3 
		}
}
	
}
}