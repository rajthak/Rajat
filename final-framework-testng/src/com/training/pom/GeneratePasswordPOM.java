package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneratePasswordPOM {
	private WebDriver driver; 
	
	public GeneratePasswordPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(xpath="/html/body/div[1]/div[2]/div[1]/div/ul[2]/li/a/img")
	private WebElement admin;
	public WebElement adminHover() {
		return admin;
	}*/

	/*@FindBy(linkText="Edit My Profile")
	private WebElement updateprflbtn;
	public void clickupdateprflBtn() {
		this.updateprflbtn.click(); 
	}*/
	
	
	@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[5]/tbody/tr[1]/td/button")
	public WebElement generatepwd;
	public void clickgeneratepwd() {
		this.generatepwd.click();
	
	}
	@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[5]/tbody/tr[1]/td/div/span/input[2]")
	public WebElement cleargeneratepwd;
	public void cleargeneratepwd() {
		this.cleargeneratepwd.clear();
		
	}
	
	@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[5]/tbody/tr[3]/td/label/input")
	public WebElement pwdcnfrmcheckbox;
	public void clickpwdcnfrmbox() {
		this.pwdcnfrmcheckbox.click();
	}
	@FindBy(xpath="/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/form/table[5]/tbody/tr[1]/td/div/span/input[2]")
	public WebElement newpasswrd;
	public void sendNewpwd(String newpwd) {
		
		this.newpasswrd.sendKeys(newpwd); 
	}
	
	
	
	
	
	
}
