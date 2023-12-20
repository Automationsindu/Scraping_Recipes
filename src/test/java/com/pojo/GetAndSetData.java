package com.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetAndSetData {

	String Recipe_ID;
	String recipeName;
	List<String> Rcategory;
	String Fcategory;
	//List<String> Ingredients;
	String Preptime;
	String Cooktime;
	String prepMethod;
	//HashMap<String,String> Nutri;
	List<String> Morbid;
	String Rurl;
	String nutriString;
	

	public String getNutriString() {
		return nutriString;
	}


	public void setNutriString(String nutriString) {
		this.nutriString = nutriString;
	}
	private List<String> morbid = new ArrayList<>();
	private List<String> rCateg = new ArrayList<>();
	private HashMap<String, String> Nutri = new HashMap<>();
	private List<String> ingredients;
	private List<String> recNames;
	
	
	 public List<String> getRecNames() {
		return recNames;
	}


	public void setRecNames(List<String> recNames) {
		this.recNames = recNames;
	}


	public void addMorbidCondition(String Mcondition) {
	        morbid.add(Mcondition);
	    }
	 
	 
	 public void addRCategory(String RecCateg) {
		 rCateg.add(RecCateg);
	    }
	
	 
	
	public String getRecipe_ID() {
		return Recipe_ID;
	}
	public void setRecipe_ID(String recipe_ID) {
		Recipe_ID = recipe_ID;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	  

    public List<String> getrCateg() {
        return rCateg;
    }

    public void setrCateg(List<String> morbid) {
        this.rCateg = rCateg;
    }

	public String getFcategory() {
		return Fcategory;
	}
	public void setFcategory(String fcategory) {
		Fcategory = fcategory;
	}
	public List<String> getIngredients() {
	    return ingredients;
	}
	
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public String getPreptime() {
		return Preptime;
	}
	public void setPreptime(String preptime) {
		Preptime = preptime;
	}
	public String getCooktime() {
		return Cooktime;
	}
	public void setCooktime(String cooktime) {
		Cooktime = cooktime;
	}
	public String getPrepMethod() {
		return prepMethod;
	}
	public void setPrepMethod(String prepMethod) {
		this.prepMethod = prepMethod;
	}
	public HashMap<String, String> getNutri() {
	    return Nutri;
	}

	public void setNutri(HashMap<String, String> Nutri) {
	    this.Nutri = Nutri;
	}
	
	    public List<String> getMorbid() {
	        return morbid;
	    }

	    public void setMorbid(List<String> morbid) {
	        this.morbid = morbid;
	    }
	
	public String getRurl() {
		return Rurl;
	}
	public void setRurl(String rurl) {
		Rurl = rurl;
	}
	

}