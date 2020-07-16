package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.QueryDatabasePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.RandomInteger;
import com.training.utility.Windowhandler;

public class LoginDBTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private GenericMethods genericMethods; 
	private QueryDatabasePOM queryDatabasePOM;
	
	
	
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
		queryDatabasePOM=new QueryDatabasePOM(driver);
		baseUrl = properties.getProperty("baseURL_RealEstate");
		screenShot = new ScreenShot(driver);
		genericMethods = new GenericMethods(driver); 
		// open the browser
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}


	@Test(dataProvider = "db-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest(String name, String email, String subject, String message) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,950)", "");
        AssertJUnit.assertTrue(driver.getPageSource().contains("New Launches"));
        Thread.sleep(3000);
        queryDatabasePOM.clickLaunchBtn();
        Thread.sleep(3000);
        Windowhandler.windowhandler(driver);
        js.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();",queryDatabasePOM.dropusalineBtn);
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,450)", "");
        AssertJUnit.assertTrue(driver.getPageSource().contains("Contact Form"));
        int n=RandomInteger.random();
      
        queryDatabasePOM.sendName("manzoor");
        queryDatabasePOM.sendEmail("manzoor@gmail.com");
        queryDatabasePOM.sendSubject("hello");
        queryDatabasePOM.sendMessage("test");
        Thread.sleep(2000);
        queryDatabasePOM.clickSendBtn();
        AssertJUnit.assertTrue(driver.getPageSource().contains("There was an error trying to send your message. Please try again later."));
		screenShot.captureScreenShot("complex test case:fetch DB test");

	}

}