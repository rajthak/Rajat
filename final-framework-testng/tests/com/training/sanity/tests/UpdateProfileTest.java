package com.training.sanity.tests;

import static org.testng.AssertJUnit.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.RandomInteger;
import com.training.utility.Randomstring;

public class UpdateProfileTest  {

	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		updateprflPOM = new UpdateProfilePOM(driver);
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
	//verify admin is able to update profile details
	@Test
	public void updateprofileTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		AssertJUnit.assertTrue(driver.getPageSource().contains("Dashboard"));
         Actions actions = new Actions(driver);
		WebElement menuOption = updateprflPOM.adminHover();
		actions.moveToElement(menuOption).perform();
		assertTrue(driver.getPageSource().contains("admin"));
		assertTrue(driver.getPageSource().contains("Edit My Profile"));
		assertTrue(driver.getPageSource().contains("Log Out"));
		//click on update profile button
        updateprflPOM.clickupdateprflBtn();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", updateprflPOM.lastname);
        String str=properties.getProperty("name");
        System.out.println(str);
		updateprflPOM.sendLastname(str);
        js.executeScript("arguments[0].scrollIntoView();", updateprflPOM.phone);
        String x=String.valueOf(properties.getProperty("phoneno"));
		System.out.println(x);
		updateprflPOM.sendPhone(x);
        js.executeScript("arguments[0].scrollIntoView();", updateprflPOM.submitbtn);
        updateprflPOM.clicksubmtBtn();
        //verify upload successfull message on the screen
		assertTrue(driver.getPageSource().contains("Profile updated."));
        screenShot.captureScreenShot("update profile test case");
	}
}
