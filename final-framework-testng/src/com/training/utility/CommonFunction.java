package com.training.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunction {
	public static boolean isTextOnPagePresent(WebDriver driver,String text) {
	    WebElement body = driver.findElement(By.tagName("body"));
	    String bodyText = body.getText();
	    return bodyText.contains(text);
	}

}
