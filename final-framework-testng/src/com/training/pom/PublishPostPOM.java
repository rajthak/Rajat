package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PublishPostPOM {
	private WebDriver driver; 
	
	public PublishPostPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//li[@class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-property']")
	public WebElement property;
	public  WebElement propertyHover() {
		return property;
	}
	@FindBy(linkText="Add New")
	public WebElement addnewbtn;
	public void clickaddnewBtn() {
		this.addnewbtn.click(); 
	}
	@FindBy(name="post_title")
	public WebElement titlebox;
	public void sendtitlebox(String last) {
		this.titlebox.clear(); 
		this.titlebox.sendKeys(last); 
	}
	@FindBy(id="content-html")
	public WebElement textbtn;
	public void clicktextbtn() {
		this.textbtn.click(); 
	}
	@FindBy(id="content")
	public WebElement textbox;
	public void sendtextbox(String text) {
		this.textbox.clear(); 
		this.textbox.sendKeys(text); 
	}
	@FindBy(xpath="//*[@id=\"publish\"]")
	public WebElement publishbtn;
	public void clickpublishBtn() {
		this.publishbtn.click(); 
	}
	@FindBy(linkText="View post")
	public WebElement viewpost;
	public void clickviewpost() {
		this.viewpost.click(); 
	}
	
	
	
	
	
	
	
	
	
	
}
