package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangeRolePOM {
	private WebDriver driver; 
	
	public ChangeRolePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Users")
	public WebElement userslink;
	public void clickuserslink() {
		this.userslink.click(); 
	}
	@FindBy(linkText="All Users")
	public WebElement alluserslink;
	public void clickalluserslink() {
		this.alluserslink.click(); 
	}
	@FindBy(id="user-search-input")
	public WebElement searchusertextbox;
	public void sendusersearchtextbox(String usersearchtext) {
		this.searchusertextbox.clear(); 
		this.searchusertextbox.sendKeys(usersearchtext); 
	}
	@FindBy(id="cb-select-all-1")
	public WebElement selectcommoncheckbox;
	public boolean clickcommoncheckboxbtn() {
		this.selectcommoncheckbox.click(); 
		return true;
	}
	
	@FindBy(id="search-submit")
	public WebElement searchuserbtn;
	public void clicksearchuserbtn() {
		this.searchuserbtn.click(); 
	}
	@FindBy(id="user_780")
	public WebElement selectcheckbox;
	public boolean clickmanzoorcheckboxbtn() {
		this.selectcheckbox.click(); 
		return true;
	}
	@FindBy(id="user_781")
	public WebElement selectcheckbox1;
	public boolean clickalexcheckboxbtn() {
		this.selectcheckbox1.click(); 
		return true;
	}
	@FindBy(id="user_782")
	public WebElement selectcheckbox2;
	public boolean clickmariyacheckboxbtn() {
		this.selectcheckbox2.click(); 
		return true;
	}
	@FindBy(id="new_role2")
	public WebElement changeroledropdown;
	
	@FindBy(id="changeit2")
	public WebElement changebtn;
	public void clickchangerolebtn() {
		this.changebtn.click(); 
	}
	
	
	
	
}
