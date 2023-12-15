package com.categories_Hypothyroidism;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.Driver_Utils;

public class HypoThyroidism_AllRecipes extends Driver_Utils
{
	public static void main(String[] args) throws IOException, InterruptedException {
	
		driverSetUp();
		
//getting title of the page
		System.out.println("The title of teh page is "+ driver.getTitle());
		driver.findElement(By.xpath("//div[text()='RECIPES']")).click();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'recipes-for-hypothyroidism-veg-diet-indian-recipes-849')]")));
		Thread.sleep(5000);
		WebElement hypoThyroidClick= driver.findElement(By.xpath("//a[contains(@href, 'recipes-for-hypothyroidism-veg-diet-indian-recipes-849')]"));
		hypoThyroidClick.click();
		Thread.sleep(5000);
		
		
		List<WebElement> allRecipe = driver.findElements(By.xpath("//a[@itemprop='url']"));
		List<Integer> indicesToClick = new ArrayList<>();
		int sizeIndex=allRecipe.size()-1;
		for (int i = 0; i < allRecipe.size(); i++) {
		    indicesToClick.add(i);
		}

		// Mutable container to hold the list of elements
		List<List<WebElement>> mutableContainer = new ArrayList<>();
		mutableContainer.add(allRecipe);

		indicesToClick.forEach(index -> {
		    // Fetch the list of elements from the container
		    List<WebElement> listRecipeList = mutableContainer.get(0);
		    WebElement listRecipe = listRecipeList.get(index);
		    String recipeCard= listRecipe.getText();

		    System.out.println("The index of each recipe is in : "+ index +" "+ recipeCard);
		 
		    try {
		        Thread.sleep(3000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    
		  // Explicit wait for the element to be clickable
		    wait.until(ExpectedConditions.elementToBeClickable(listRecipe)).click();
		
			
			
		WebElement prepTime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
	    wait.until(ExpectedConditions.visibilityOf(prepTime));
	    
			
			
		WebElement cookTime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
		wait.until(ExpectedConditions.visibilityOf(cookTime));
		
		System.out.println(recipeCard +"- The prep time is :"+ prepTime.getText()+" And The cook time is :"+ cookTime.getText());
		
		 driver.navigate().back();

		    // Re-fetch the list of elements after navigation
		    listRecipeList.clear();
		    listRecipeList.addAll(driver.findElements(By.xpath("//a[@itemprop='url']")));
		});
		
			
			
			
		
		
		
	
	driver.quit();
	}
	
}
            

		

