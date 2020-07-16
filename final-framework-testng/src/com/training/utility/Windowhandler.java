package com.training.utility;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Windowhandler {
	public static void windowhandler(WebDriver driver)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
        
        for(String handle : allWindowHandles)
        {
        System.out.println("Window handle - > " + handle);
        driver.switchTo().window(handle);
        }
	 
}
}
