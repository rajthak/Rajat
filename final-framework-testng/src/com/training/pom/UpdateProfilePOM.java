package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateProfilePOM {
	public WebDriver driver; 
	
	public UpdateProfilePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@class='avatar avatar-26 photo']")
	public WebElement admin;
	public  WebElement adminHover() {
		return admin;
	}

	@FindBy(linkText="Edit My Profile")
	public WebElement updateprflbtn;
	public void clickupdateprflBtn() {
		this.updateprflbtn.click(); 
	}
	
	
	@FindBy(id="last_name")
	public WebElement lastname;
	public void sendLastname(String last) {
		this.lastname.clear(); 
		this.lastname.sendKeys(last); 
	}
	
	@FindBy(id="phone")
	public WebElement phone;
	public void sendPhone(String phon) {
		this.phone.clear(); 
		this.phone.sendKeys(phon); 
	}
	@FindBy(id="submit")
	public WebElement submitbtn;
	public void clicksubmtBtn() {
		this.submitbtn.click(); 
	}
	
	
	
	
	
	
}
