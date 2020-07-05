package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegiondetailsPOM {
	private WebDriver driver; 
	
	public RegiondetailsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="Regions")
	public WebElement regions;
	public void clickregionsBtn() {
		this.regions.click(); 
	}
	@FindBy(id="parent")
	public WebElement selectdropdown;
	public void clickdropdown() {
		this.selectdropdown.click(); 
	}
	@FindBy(linkText="All Regions")
	public WebElement allregions;
	public void clickallregions() {
		this.allregions.click(); 
	}
	
	
	
	
}
