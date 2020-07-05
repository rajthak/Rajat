package com.training.sanity.tests;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLogoutPOM;
import com.training.pom.GeneratePasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


//import utilities.Constant;

public class GeneratePasswordTest {

	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public GeneratePasswordPOM generatepwdPOM;
	public AdminLogoutPOM adminlogoutPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	
	@BeforeMethod
	public  void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		adminlogoutPOM=new AdminLogoutPOM(driver);
		updateprflPOM=new UpdateProfilePOM(driver);
		generatepwdPOM=new GeneratePasswordPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
      //verify admin able to generate password  
    @Test
	public void generatepasswordTest(){
    	
    	loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		AssertJUnit.assertTrue(driver.getPageSource().contains("Dashboard"));
	    Actions actions = new Actions(driver);
		WebElement menuOption = updateprflPOM.adminHover();
		actions.moveToElement(menuOption).perform();
	    Assert.assertTrue(driver.getPageSource().contains("admin"));
		Assert.assertTrue(driver.getPageSource().contains("Edit My Profile"));
		Assert.assertTrue(driver.getPageSource().contains("Log Out"));
        //click on update profile button
		updateprflPOM.clickupdateprflBtn();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        //generate password
        generatepwdPOM.clickgeneratepwd();
        generatepwdPOM.cleargeneratepwd();
        generatepwdPOM.sendNewpwd("admin@123");
        generatepwdPOM.clickpwdcnfrmbox();
        js.executeScript("window.scrollBy(0,2000)");
        updateprflPOM.clicksubmtBtn();
        //verify update successfull message on the screen
        Assert.assertTrue(driver.getPageSource().contains("Profile updated."));
        screenShot.captureScreenShot("generate admin password test case");
	
	}
    
   	}
  


