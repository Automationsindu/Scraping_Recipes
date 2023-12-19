package com.categories;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pojo.GetAndSetData;
import com.utils.Driver_Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeFilters extends Driver_Utils {
	
	
	public static int counter=1;
	public static List<String> eliminationList;
	static int recipeCountWithoutElimination = 0;
	public static List<GetAndSetData> recipes;
	public static String nutriString;
	
	public static List<String> getEliminationList()
	{
				eliminationList = Arrays.asList(
                "Tofu", "Edamame", "Tempeh", "Cauliflower", "Cabbage", "Broccoli", "Kale", "Spinach",
                "Sweet potatoes", "Strawberries", "Pine nuts", "Peanuts", "Peaches", "Green tea",
                "Coffee", "Alcohol", "Soy milk", "White bread", "Cakes", "Pastries", "Fried food",
                "Sugar", "Processed food", "Ham", "Bacon", "Salami", "Sausages", "Frozen food",
                "Gluten", "Sodas", "Energy drinks containing caffeine", "Packaged food", "Noodles",
                "Soups", "Salad dressings", "Sauces", "Candies");
		
		return null;
		
	}
	
	

	    public static void writeToExcel(List<GetAndSetData> recipes) {
	    	
	    	
	        String filePath = "C:\\Users\\harii\\eclipse-workspace\\ScrapingRecipes\\target\\recipeCrawlers.xlsx"; // Specify the path to your Excel file
	        try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("RecipeDetails");

	            // Create the header row
	            Row headerRow = sheet.createRow(0);
	            headerRow.createCell(0).setCellValue("Recipe ID");
	            headerRow.createCell(1).setCellValue("Recipe Name");
	            headerRow.createCell(2).setCellValue("Recipe Category");
	            headerRow.createCell(3).setCellValue("Ingredients");
	            headerRow.createCell(4).setCellValue("Preparation Time");
	            headerRow.createCell(5).setCellValue("Cook Time");
	            headerRow.createCell(6).setCellValue("Preparation Methods");
	            headerRow.createCell(7).setCellValue("Nutrient Values");
	            headerRow.createCell(8).setCellValue("Targetted Morbid");
	            headerRow.createCell(9).setCellValue("RecipeUrl");
	            
	            // Add other headers as needed

	            // Populate data starting from the second row
	            int rowNum = 1;
	            for (GetAndSetData pojoData : recipes) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(pojoData.getRecipe_ID());
	                row.createCell(1).setCellValue(pojoData.getRecipeName());
	                row.createCell(2).setCellValue(String.join(", ", pojoData.getrCateg()));
	                row.createCell(3).setCellValue(String.join("\n", pojoData.getIngredients()));  
	                row.createCell(4).setCellValue(pojoData.getPreptime());
	                row.createCell(5).setCellValue(pojoData.getCooktime());
	                row.createCell(6).setCellValue(String.join(", ",pojoData.getPrepMethod()));
	                row.createCell(7).setCellValue(nutriString);
	                
	                row.createCell(8).setCellValue(String.join(", ", pojoData.getMorbid()));
	                row.createCell(9).setCellValue(pojoData.getRurl());
	                //rest will add
	            }
	            // Write the workbook to the Excel file
	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	            }

	            System.out.println("Recipe details written to Excel successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
	public static void main(String[] args) throws InterruptedException {
		driverSetUp();
		//List<GetAndSetData> recipes=new ArrayList<>();
		driver.findElement(By.xpath("//div[text()='RECIPES']")).click();
		
		try {
		
		String myUrl="https://www.tarladalal.com/recipes-for-hypothyroidism-veg-diet-indian-recipes-849";
		String Hypourl="https://www.tarladalal.com/recipes-for-hypothyroidism-veg-diet-indian-recipes-849";
		String pcosUrl="https://www.tarladalal.com/recipes-for-polycystic-ovary-syndrome-pcos-645";
		String bpUrl="https://www.tarladalal.com/recipes-for-high-blood-pressure-644";
		String diabUrl="https://www.tarladalal.com/recipes-for-indian-diabetic-recipes-370";
		
		if(myUrl.equals(Hypourl))
		{
		
			System.out.println("myUrl is:"+Hypourl);
			driver.get(myUrl);
			completeRecipe();
		}
		else if(myUrl.equals(pcosUrl))
		{
			System.out.println("myUrl is:"+pcosUrl);
			driver.get(myUrl);
			completeRecipe();
		}
		
		else if(myUrl.equals(bpUrl))
		{
			System.out.println("myUrl is:"+bpUrl);
			driver.get(myUrl);
			completeRecipe();
		}
		else if(myUrl.equals(diabUrl))
		{
			System.out.println("myUrl is:"+diabUrl);
			driver.get(myUrl);
			completeRecipe();
		}
		}catch(Exception e) {
	
			System.out.println("No more recipes found, moving to write excel");
		}
		
		writeToExcel(recipes);
	}
		
			
		//driver.
		//WebElement hypoThyroidClick= driver.findElement(By.xpath("//a[contains(@href, 'recipes-for-hypothyroidism-veg-diet-indian-recipes-849')]"));
		//hypoThyroidClick.click();
		//========================================================================================
		
		
		public static void completeRecipe() throws InterruptedException
		{
			
			 recipes=new ArrayList<>();
			 List<String> recNames=new ArrayList<>();
			 
		List<WebElement> NoOfPages = driver.findElements(By.xpath("//div[@id='pagination']//a"));
		int TotalPages = NoOfPages.size();
		
		WebElement lastPageNoString = driver.findElement(By.xpath("//div[@id='pagination']//a["+TotalPages+"]"));
		int lastPageNo = Integer.parseInt(lastPageNoString.getText());
		System.out.println("The size of pagination is :"+lastPageNo);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		for (int j=3;j<=lastPageNo;j++)
		{
			
			
			WebElement nextPageLink = driver.findElement(By.xpath("//div[@id='pagination']//a["+j+"]"));
			nextPageLink.click();
			
			//wait.until(ExpectedConditions.visibilityOf(nextPageLink));
			
			List<WebElement> receipeList = driver.findElements(By.xpath("//div[@class='recipelist']//article"));
			int receipeListCount = receipeList.size();
			
			for (int i=counter; i<=receipeListCount; i++)
			{	
				GetAndSetData pojoObj=new GetAndSetData();
	//Recipe Name			
				WebElement receipeName = driver.findElement(By.xpath("//div[@class='recipelist']//article["+i+"]/div[@class='rcc_rcpcore']/span/a"));
				pojoObj.setRecipeName(receipeName.getText());
				recNames.add(receipeName.getText());
				pojoObj.setRecNames(recNames);
				
	//RecipeId			
				WebElement receipeId = driver.findElement(By.xpath("//div[@class='recipelist']//article["+i+"]/div[@class='rcc_rcpno']/span"));
				String receiptIdSubstring = receipeId.getText();
				String finalRecipeId = receiptIdSubstring.split("\\n")[0].split(" ")[1];	
				//System.out.println("Final RecipeId is "+ finalRecipeId);
				pojoObj.setRecipe_ID(finalRecipeId);
				
	//morbid conditions 
	
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		        String xpathExpression = "(//div[@class='recipelist']//article)[" + i + "]//div[@class='rcc_caticons']/img/@src";
		        		                  
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
		        concatenatedSrc = concatenatedSrc.replaceAll(separator + "$", "");
		        List<String> morbidConditions = Arrays.asList("diabetic", "low-cholestrol", "pcos");
		        for (String morbidCondition : morbidConditions) {
		        if (concatenatedSrc.contains(morbidCondition)) {
		        //System.out.println("RecipeId " + finalRecipeId + " has morbid condition: " + morbidCondition);
		        pojoObj.addMorbidCondition(morbidCondition);
		        }
		        }
				
	// RecipeCategory
				
				    List<String> recipeCategory = Arrays.asList("breakfast", "lunch", "snack","dinner");
				    for (String recipeCatgry : recipeCategory) {
				    if (concatenatedSrc.contains(recipeCatgry)) {
				    //System.out.println("RecipeId " + finalRecipeId + " recipe category : " + recipeCatgry);
				    	pojoObj.addRCategory(recipeCatgry); 
				     }
				    }
				    
	// Food Category
			      /*  WebElement foodCategry=driver.findElement(By.xpath("//span[@class='breadcrumb-link-wrap'][3]//span[@itemprop='name']"));
			        List<String> foodCategory = Arrays.asList("Vegan", "Vegetarian", "Jain","Eggitarian"," Non-veg");
			        for(String foodCat:foodCategory) {
			        if(foodCategry.getText().contains(foodCat)) {
			          pojoObj.setFcategory(foodCat); 
			         }
			        }
	*/
				    
   //Get Current url for each recipe
				    
				    pojoObj.setRurl(driver.getCurrentUrl());

		            receipeName.click();
		            
  //Cooking and Preparation time 
				    
		            Thread.sleep(5000);
				    WebElement prepTime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
				    pojoObj.setPreptime(prepTime.getText());
				    WebElement cookTime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
				    pojoObj.setCooktime(cookTime.getText());
				    
   // Preparation Method
			        
			        WebElement prepMethod=driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
			        pojoObj.setPrepMethod(prepMethod.getText());
			        
				    
//Nutrients value
			        
					List<WebElement> nutrients = driver.findElements(By.xpath("//table[@id='rcpnutrients']/tbody/tr/td/span"));
					
					
					HashMap<String,String> nutri = new HashMap<String,String>();
					for(WebElement ele:nutrients) {
						nutri.put(ele.getAttribute("itemprop"), ele.getText());
						
					}
					pojoObj.setNutri(nutri);
					
			        
  // Ingredients and Elimination	
			        // List of ingredients to be eliminated
			        getEliminationList();
			        
				    List<WebElement> ingredientsElements = driver.findElements(By.xpath("//div[@id='rcpinglist']/div/span"));

			        List<String> ingredients = ingredientsElements.stream()
			                .map(WebElement::getText)
			                .collect(Collectors.toList());

					        
			     
			        // Check if any ingredient matches the elimination list in a case-insensitive and partial manner
			        boolean hasEliminationItem = ingredients.stream()
			                .anyMatch(ingredient ->
			                eliminationList.stream()
			                .anyMatch(eliminationItem ->
			                ingredient.toLowerCase().contains(eliminationItem.toLowerCase())
			                                )
			                        
			                );
			        
			        boolean hasEliminationItemInRecNames = recNames.stream()
			                .anyMatch(recName ->
			                eliminationList.stream()
			                .anyMatch(eliminationItem ->
			                recName.toLowerCase().contains(eliminationItem.toLowerCase())));
			        
			       // pojoObj.setIngredients(ingredients);
			        
			       boolean addRecipe=(!hasEliminationItem ||hasEliminationItemInRecNames); 
			            recipeCountWithoutElimination++;
			          
			            
			            
			                
			                
			        // Print the result
			        
			         if (addRecipe) {
		                pojoObj.setIngredients(ingredients);
		                pojoObj.setRecNames(recNames);
			        	System.out.println("===================================================");
			        	System.out.println("No elimination items are listed in the ingredients,Details are below");
			        	System.out.println();
			        	System.out.println("The current url of the page is: "+ pojoObj.getRurl());
			        	
			  			System.out.println( "The recipe Id is: "+pojoObj.getRecipe_ID());
			  			
			  			System.out.println("The receipeName is: "+ pojoObj.getRecipeName());
			  			System.out.println();
			  		
			        	List<String> pojoMorb= pojoObj.getMorbid();
			        	System.out.println("The morbid conditions are:"+pojoObj.getMorbid());
			        	/*for (String condition : pojoMorb) {
			        	    System.out.println("The morbid conditions is/are: "+condition);
			        	}*/
			        	
			        	
			        	System.out.println();
			        	List<String> pojorcateg= pojoObj.getrCateg();
			        	for(String rcategory:pojorcateg)
			        	{
			        		System.out.println("The recipe category is/are: "+rcategory);
			        	}
			  			
			  			//System.out.println("The receipe category is :"+ pojoObj.getFcategory());
			  			
			        	System.out.println();
			        	System.out.println("The ingredients for the recipe are");
			  			//ingredients.forEach(System.out::println);
			        	
			        	List<String> ingredientsList = pojoObj.getIngredients();
			        	//converting into single string
			        	String ingString=String.join(",", ingredientsList);
			        	//split the string in, delimiter
			        	String[] ingStringArray=ingString.split(",");
			        	
			        	for(String eachLine:ingStringArray)
			        	{
			        		 System.out.println(eachLine);
			        	}
			        	
			           	System.out.println();
			           	System.out.println("The cooking time is: "+ pojoObj.getCooktime());
			           
			           	System.out.println("The preparation time is: "+ pojoObj.getPreptime());
			           
			           	System.out.println();
			           	System.out.println("The preparation method is: ");
			        	System.out.println(pojoObj.getPrepMethod());
			        	
			        	System.out.println();
			        	System.out.println(pojoObj.getRurl());
			        	
			        	System.out.println();
			        	HashMap<String, String> nutriMap = pojoObj.getNutri();
			        	// Converting  HashMap to a string
			        	nutriString = nutriMap.entrySet().stream()
			        	        .map(entry -> entry.getKey() + ": " + entry.getValue())
			        	        .collect(Collectors.joining("\n "));
			        	System.out.println("The nutrients for this recipe are:"+nutriString);
			        	/*String[] nutrStringArray=nutriString.split(",");
			        	
			        	for(String nuteachLine:nutrStringArray)
			        	{
			        		 System.out.println(nuteachLine);
			        	}*/
			        	
			        	
			        	
			        	recipes.add(pojoObj);
			        	
			        }
			        
			        else  {
			        	System.out.println("===================================================");
			            System.out.println("Elimination item found in ingredientsElements. No output.");
			        
			        }
			        
			        //System.out.println("Total number of recipes without elimination items: " + recipeCountWithoutElimination);
			        String curUrl=driver.getCurrentUrl();
			    	
			        driver.navigate().back();
			        Thread.sleep(600);
			        if(curUrl.equals(driver.getCurrentUrl()))
			        {
			        driver.navigate().back();
			       
			        Thread.sleep(600);
			        WebElement page= driver.findElement(By.xpath("//div[@id='pagination']"));
			        wait.until(ExpectedConditions.visibilityOf(page));
			        
			        }
			        System.out.println("Before navigating the url is "+driver.getCurrentUrl());
			        }
			        System.out.println("The size of recipe is "+recipes.size());
			        } 
		}
		// writeToExcel(recipes);
						//driver.quit();
		}						//driver.close();

		



//==============================Other Tested codes but didnot work as expected ===========================================

//Morbid condition from img

/*	WebElement morbidImg=driver.findElement(By.xpath("//div[@class='rcc_caticons']/img"));
    String morbid=morbidImg.getAttribute("src");
    List<String> morbidConditions = Arrays.asList("diabetic", "low-cholesterol", "pcos");
    for (String morbidCondition : morbidConditions) {
    	 if (morbid.contains(morbidCondition)) {
    		 System.out.println("The recipe can be added to other morbid condition "+morbidCondition);  }
    }*/

 // Remove trailing separator
    

    //System.out.println("Concatenated Src values for RecipeId " + finalRecipeId + ": " + concatenatedSrc);

    // Check if any of the src values match morbid conditions