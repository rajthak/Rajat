package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QueryDatabasePOM {
	private WebDriver driver; 
	
	public QueryDatabasePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="New Launches")
	private WebElement launchBtn;
	public void clickLaunchBtn() {
		this.launchBtn.click(); 
	}
	@FindBy(xpath="//*[@id=\"text-4\"]/div/div/p[2]/a")
	public WebElement dropusalineBtn;
	/*public void clickDropusalineBtn() {
		this.dropusalineBtn.click(); 
	}*/
	@FindBy(name="name")
	private WebElement name;
	public void sendName(String userName) {
		this.name.clear();
		this.name.sendKeys(userName);
	}
	@FindBy(name="email")
	private WebElement email;
	public void sendEmail(String emailid) {
		this.email.clear();
		this.email.sendKeys(emailid);
	}
	@FindBy(name="subject")
	private WebElement subject;
	public void sendSubject(String subject) {
		this.subject.clear();
		this.subject.sendKeys(subject);
	}
	@FindBy(name="id:comments")
	private WebElement message;
	public void sendMessage(String message) {
		this.message.clear();
		this.message.sendKeys(message);
	}
	@FindBy(xpath="//input[@class='wpcf7-form-control wpcf7-submit']")
	public WebElement sendBtn;
	public void clickSendBtn() {
		this.sendBtn.click(); 
	}
	

}
