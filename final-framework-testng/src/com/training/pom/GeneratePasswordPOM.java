package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneratePasswordPOM {
	public WebDriver driver; 
	
	public GeneratePasswordPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@class='button wp-generate-pw hide-if-no-js']")
	public WebElement generatepwd;
	public void clickgeneratepwd() {
		this.generatepwd.click();
	
	}
	
	@FindBy(xpath="//input[@id='pass1-text']")
	public WebElement cleargeneratepwd;
	public void cleargeneratepwd() {
		this.cleargeneratepwd.clear();
		}
	
	
	@FindBy(xpath="//input[@class='pw-checkbox']")
	public WebElement pwdcnfrmcheckbox;
	public void clickpwdcnfrmbox() {
		this.pwdcnfrmcheckbox.click();
	}
	
	
	@FindBy(xpath="//input[@id='pass1-text']")
	public WebElement newpasswrd;
	public void sendNewpwd(String newpwd) {
		
		this.newpasswrd.sendKeys(newpwd); 
	}
	
}
