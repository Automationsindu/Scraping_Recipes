package com.categories_Hypothyroidism;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.Driver_Utils;

public class SampleTest extends Driver_Utils
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
		/*List<WebElement> allRecipe=driver.findElements(By.xpath("//a[@itemprop='url']"));
		for(WebElement eachrecipe: allRecipe)
		{
			eachrecipe.click();
			eachrecipe.click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner")));
			System.out.println("Before navigating back: " + driver.getCurrentUrl());
			driver.navigate().back();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner")));
			System.out.println("After navigating back: " + driver.getCurrentUrl());*/
		
		//=============================================	
		// Assuming you are using Java and Selenium WebDriver
		/*List<WebElement> allRecipeLinks = driver.findElements(By.xpath("//a[@itemprop='url']"));

		for (int i = 0; i < allRecipeLinks.size(); i++) {
		    WebElement recipeLink = allRecipeLinks.get(i);
		    System.out.println("Index: " + i + ", Text: " + recipeLink.getText());

		    // Your logic with the current recipe link goes here
		    // For example, click on the link:
		    recipeLink.click();

		    // Wait for the page to load or any other actions...

		    // Go back to the previous page
		    driver.navigate().back();

		    // Re-fetch the elements to avoid StaleElementReferenceException
		    allRecipeLinks = driver.findElements(By.xpath("//a[@itemprop='url']"));
		}	*/
		
		List<WebElement> allRecipe = driver.findElements(By.xpath("//a[@itemprop='url']"));
		List<Integer> indicesToClick = new ArrayList<>();

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

		    System.out.println("The index of each recipe is: " + listRecipe.getText());

		    try {
		        Thread.sleep(3000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

		    // Re-fetch the list of elements after navigation
		    //listRecipeList.clear();//(This can be used just to avoid teh mixture of stale and fresh if facing error)
		    //listRecipeList.addAll(driver.findElements(By.xpath("//a[@itemprop='url']")));

		    // Explicit wait for the element to be clickable
		    wait.until(ExpectedConditions.elementToBeClickable(listRecipe)).click();

		    // Wait for the page to load or for specific elements to become visible
		    // Your logic here...

		    // Optionally, navigate back to the previous page
		    driver.navigate().back();

		    // Re-fetch the list of elements after navigation
		    listRecipeList.clear();
		    listRecipeList.addAll(driver.findElements(By.xpath("//a[@itemprop='url']")));
		});

			
		}
	}


