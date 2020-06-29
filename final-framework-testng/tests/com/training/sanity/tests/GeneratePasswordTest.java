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
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GeneratePasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.RandomInteger;
import com.training.utility.Randomstring;

//import utilities.Constant;

public class GeneratePasswordTest {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private UpdateProfilePOM updateprflPOM;
	private GeneratePasswordPOM generatepwdPOM;
	private static Properties properties;
	private ScreenShot screenShot;

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
		generatepwdPOM = new GeneratePasswordPOM(driver);
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
	@Test
	public void updateprofileTest() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPOM.sendUserName("admin");
		loginPOM.sendPassword(properties.getProperty("password"));
		loginPOM.clickLoginBtn();
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		WebElement menuOption = updateprflPOM.adminHover();
		actions.moveToElement(menuOption).perform();
		assertTrue(driver.getPageSource().contains("admin"));
		assertTrue(driver.getPageSource().contains("Edit My Profile"));
		assertTrue(driver.getPageSource().contains("Log Out"));
        updateprflPOM.clickupdateprflBtn();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        generatepwdPOM.clickgeneratepwd();
        generatepwdPOM.cleargeneratepwd();
        generatepwdPOM.sendNewpwd(properties.getProperty("password"));
        generatepwdPOM.clickpwdcnfrmbox();
        js.executeScript("window.scrollBy(0,2000)");
        updateprflPOM.clicksubmtBtn();
        //verify update successfull message on the screen
		assertTrue(driver.getPageSource().contains("Profile updated."));
        screenShot.captureScreenShot("generate admin password test case");
        
        
       
	}
}
