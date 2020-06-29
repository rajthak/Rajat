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

public class UpdateProfileTest {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private UpdateProfilePOM updateprflPOM;
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
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();

		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		WebElement menuOption = updateprflPOM.adminHover();
		actions.moveToElement(menuOption).perform();
		assertTrue(driver.getPageSource().contains("admin"));
		assertTrue(driver.getPageSource().contains("Edit My Profile"));
		assertTrue(driver.getPageSource().contains("Log Out"));
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
        screenShot.captureScreenShot("Second");
	}
}
