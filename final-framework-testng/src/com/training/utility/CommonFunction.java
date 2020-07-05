package com.training.utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunction {
	public static boolean isTextOnPagePresent(WebDriver driver,String text) {
	    WebElement body = driver.findElement(By.tagName("body"));
	    String bodyText = body.getText();
	    return bodyText.contains(text);
	}
	
	
	
	public static boolean searchUsername(WebDriver driver, String searchname){
		// Grab the table 
		boolean bool=false;
		WebElement table = driver.findElement(By.xpath("//*[@id=\"posts-filter\"]/table"));
		
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr")); 

		// And iterate over them, getting the cells 
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td")); 
		  
		    // Print the contents of each cell
		    for (WebElement cell : cells) { 
		        System.out.println(cell.getText());
		        if(cell.getText().contains(searchname))
		        {
		        	bool=true;
		        	break;
		        }
		        
		        
		    }
		    
		 
		}
		return bool;
		
	

}
	public static boolean searchnamefromlist(WebDriver driver, String searchname)
	{
		boolean bool=false;
	
	WebElement dropdown = driver.findElement(By.id("property_featurechecklist"));
	//dropdown.click(); 
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(searchname))
	    {
	        option.click();
	        bool=true;// click the desired option
	        break;
	    }
	}
	return bool;
}
	public static boolean searchregionfromlist(WebDriver driver, String searchname)
	{
		boolean bool=false;
	
	WebElement dropdown = driver.findElement(By.xpath("//ul[@id='regionchecklist' and @class='categorychecklist form-no-clear']"));
	//dropdown.click(); 
	List<WebElement> options = dropdown.findElements(By.tagName("li"));
	for (WebElement option : options)
	{
	    if (option.getText().equals(searchname))
	    {
	        option.click();
	        bool=true;// click the desired option
	        break;
	    }
	    
	}
	return bool;
}
}


