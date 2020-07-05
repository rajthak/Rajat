package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertydetailsPOM {
	private WebDriver driver; 
	
	public PropertydetailsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Features")
	public WebElement features;
	public void clickfeaturesBtn() {
		this.features.click(); 
	}
	@FindBy(id="tag-name")
	public WebElement nametextbox;
	public void sendnametextbox(String nametext) {
		this.nametextbox.clear(); 
		this.nametextbox.sendKeys(nametext); 
	}
	@FindBy(id="tag-slug")
	public WebElement slugtextbox;
	public void sendslugtextbox(String slugtext) {
		this.slugtextbox.clear(); 
		this.slugtextbox.sendKeys(slugtext); 
	}
	@FindBy(id="tag-description")
	public WebElement desctextbox;
	public void senddesctextbox(String desctext) {
		this.desctextbox.clear(); 
		this.desctextbox.sendKeys(desctext); 
	}
	
	@FindBy(id="submit")
	public WebElement addfeature;
	public void clickaddfeatureBtn() {
		this.addfeature.click(); 
	}
	@FindBy(id="tag-search-input")
	public WebElement tagsearch;
	public void sendtagsearch(String tagsearchtext) {
		this.tagsearch.clear(); 
		this.tagsearch.sendKeys(tagsearchtext); 
	}
	
	@FindBy(id="search-submit")
	public WebElement searchfeatures;
	public void clicksearchfeaturesBtn() {
		this.searchfeatures.click(); 
	}
	@FindBy(linkText="Add New")
	public WebElement addnew;
	public void clickaddnewBtn() {
		this.addnew.click(); 
	}
	@FindBy(linkText="All Features")
	public WebElement allfeatures;
	public void clickallfeatures() {
		this.allfeatures.click(); 
	}
	
	
	
	
}
