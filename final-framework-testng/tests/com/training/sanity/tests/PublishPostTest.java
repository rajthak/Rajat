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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLogoutPOM;
import com.training.pom.GeneratePasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PublishPostPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.CommonFunction;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.RandomInteger;
import com.training.utility.Randomstring;

//import utilities.Constant;

public class PublishPostTest  {

	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public GeneratePasswordPOM generatepwdPOM;
	public AdminLogoutPOM adminlogoutPOM;
	public PublishPostPOM publishpostPOM;
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
		generatepwdPOM = new GeneratePasswordPOM(driver);
		adminlogoutPOM = new AdminLogoutPOM(driver);
		publishpostPOM = new PublishPostPOM(driver);
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
//verify whether application allows admin to view added property details in Home screen
	@Test
	public void publishpostTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		assertTrue(driver.getPageSource().contains("Dashboard"));
		updateprflPOM = new UpdateProfilePOM(driver);
		generatepwdPOM = new GeneratePasswordPOM(driver);
		adminlogoutPOM = new AdminLogoutPOM(driver);
		publishpostPOM = new PublishPostPOM(driver);
        Actions actions = new Actions(driver);
        WebElement menuOption = publishpostPOM.propertyHover();
    	actions.moveToElement(menuOption).perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //click on add new button
        publishpostPOM.clickaddnewBtn();
        int n=RandomInteger.random();
        String validcredential=("new launch"+n);
        publishpostPOM.sendtitlebox(validcredential);
        publishpostPOM.clicktextbtn();
        publishpostPOM.sendtextbox(validcredential);
        Thread.sleep(8000);
        //click on publish button
        publishpostPOM.clickpublishBtn();
        Thread.sleep(8000);
        assertTrue(driver.getPageSource().contains("Post published."));
        //click on view post link
        publishpostPOM.clickviewpost();
        //verify post published displayed on screen
        assertTrue(driver.getPageSource().contains(validcredential));
        screenShot.captureScreenShot("publish post test case");
      
        
       
	}
}
