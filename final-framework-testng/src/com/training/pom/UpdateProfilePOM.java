package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateProfilePOM {
	private WebDriver driver; 
	
	public UpdateProfilePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="/html/body/div[1]/div[2]/div[1]/div/ul[2]/li/a/img")
	private WebElement admin;
	public WebElement adminHover() {
		return admin;
	}

	@FindBy(linkText="Edit My Profile")
	private WebElement updateprflbtn;
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
