package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewCategoryPOM {
	private WebDriver driver; 
	
	public AddNewCategoryPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Posts")
	public WebElement postslink;
	public void clickpostslink() {
		this.postslink.click(); 
	}
	
	@FindBy(linkText="Add New")
	public WebElement addnewlink;
	public void clickaddnewlink() {
		this.addnewlink.click(); 
	}
	@FindBy(id="category-add-toggle")
	public WebElement addnewcategorylink;
	public void clickaddnewcategorylink() {
		this.addnewcategorylink.click(); 
	}
	@FindBy(linkText="All Categories")
	public WebElement allcategorylink;
	public void clickallcategorylink() {
		this.allcategorylink.click(); 
	}
	@FindBy(id="newcategory")
	public WebElement fillcategorytextbox;
	public void sendcategorytextbox(String categorytextbox) {
		this.fillcategorytextbox.clear(); 
		this.fillcategorytextbox.sendKeys(categorytextbox); 
	}
	@FindBy(id="newcategory_parent")
	public WebElement newcategorydropdown;
	
	@FindBy(id="category-add-submit")
	public WebElement categorysubmitbtn;
	public void clickaddcategorybtn() {
		this.categorysubmitbtn.click(); 
	}
	@FindBy(linkText="Real Estate")
	public WebElement realestatebtn;
	public void clickrealestatelink() {
		this.realestatebtn.click(); 
	}
	
	
	
	
	
}
