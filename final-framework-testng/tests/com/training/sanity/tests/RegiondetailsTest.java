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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLogoutPOM;
import com.training.pom.GeneratePasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertydetailsPOM;
import com.training.pom.PublishPostPOM;
import com.training.pom.RegiondetailsPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.CommonFunction;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.utility.RandomInteger;
import com.training.utility.Randomstring;

//import utilities.Constant;

public class RegiondetailsTest  {

	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public GeneratePasswordPOM generatepwdPOM;
	public AdminLogoutPOM adminlogoutPOM;
	public PublishPostPOM publishpostPOM;
	public PropertydetailsPOM propertydetailsPOM;
	public RegiondetailsPOM regiondetailsPOM;
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
		propertydetailsPOM = new PropertydetailsPOM(driver);
		regiondetailsPOM = new RegiondetailsPOM(driver);
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
//verify whether application allows admin to create property details based on the Region created
	@Test
	public void propertydetailsTest() throws InterruptedException {
		
        loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		assertTrue(driver.getPageSource().contains("Dashboard"));
		Actions actions = new Actions(driver);
		WebElement menuOption = publishpostPOM.propertyHover();
    	actions.moveToElement(menuOption).perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //click on region link
        regiondetailsPOM.clickregionsBtn();
		int n=RandomInteger.random();
        String validcredential=("Electronic city"+n);
        propertydetailsPOM.sendnametextbox(validcredential);
        propertydetailsPOM.sendslugtextbox("Electronic city");
        Select drpCountry = new Select(regiondetailsPOM.selectdropdown);
        drpCountry.selectByVisibleText("None");
        propertydetailsPOM.senddesctextbox("New Launches of villas, apartments, flats");
        Thread.sleep(5000);
        //click on add feature button
        propertydetailsPOM.clickaddfeatureBtn();
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250)", "");
        propertydetailsPOM.sendtagsearch(validcredential);
        propertydetailsPOM.clicksearchfeaturesBtn();
        Assert.assertTrue(CommonFunction.searchUsername(driver, validcredential));
        propertydetailsPOM.clickaddnewBtn();
        publishpostPOM.sendtitlebox("prestige");
        publishpostPOM.clicktextbtn();
        publishpostPOM.sendtextbox("home town");
        regiondetailsPOM.clickallregions();
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(5000);
        Assert.assertTrue(CommonFunction.searchregionfromlist(driver, validcredential));
        Thread.sleep(5000);
        js.executeScript("window.scrollBy(0,-150)", "");
        Thread.sleep(5000);
        publishpostPOM.clickpublishBtn();
        Thread.sleep(8000);
        assertTrue(driver.getPageSource().contains("Post published. "));
        screenShot.captureScreenShot("create region test case");
        
    
       
	}
}
