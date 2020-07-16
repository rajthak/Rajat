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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddNewCategoryPOM;
import com.training.pom.AdminLogoutPOM;
import com.training.pom.ChangeRolePOM;
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

public class AddNewCategoryTest {

	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public GeneratePasswordPOM generatepwdPOM;
	public AdminLogoutPOM adminlogoutPOM;
	public ChangeRolePOM changerolePOM;
	public AddNewCategoryPOM addnewcategoryPOM;
	public PublishPostPOM publishpostPOM;
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
		changerolePOM=new ChangeRolePOM(driver);
		addnewcategoryPOM=new AddNewCategoryPOM(driver);
		publishpostPOM=new PublishPostPOM(driver);
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
	//verify admin is able to logout
	@Test
	public void addnewcategoryTest() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();	
		AssertJUnit.assertTrue(driver.getPageSource().contains("Dashboard"));
	    Actions actions = new Actions(driver);
		WebElement menuOption = addnewcategoryPOM.postslink;
		actions.moveToElement(menuOption).perform();
		Thread.sleep(2000);
		assertTrue(driver.getPageSource().contains("All Posts"));
		assertTrue(driver.getPageSource().contains("Add New"));
		assertTrue(driver.getPageSource().contains("Categories"));
		assertTrue(driver.getPageSource().contains("Tags"));
		//click on all users
		//Thread.sleep(2000);
		addnewcategoryPOM.clickaddnewlink();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)");
        assertTrue(driver.getPageSource().contains("+ Add New Category"));
        addnewcategoryPOM.clickaddnewcategorylink();
        int n=RandomInteger.random();
        String categoryname=("Plots"+n);
        addnewcategoryPOM.sendcategorytextbox(categoryname);
		Select objSelect =new Select(addnewcategoryPOM.newcategorydropdown);
	    objSelect.selectByVisibleText("New Launches");	
	    addnewcategoryPOM.clickaddcategorybtn();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,350)");
		addnewcategoryPOM.clickallcategorylink();
		Assert.assertTrue(CommonFunction.searchcategoryfromlist(driver, categoryname));
		
		publishpostPOM.sendtitlebox("vihar"+n);
		publishpostPOM.sendtextbox("New Launch in Plots"+n);
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(5000);
		publishpostPOM.clickpublishBtn();
		Thread.sleep(3000);
		assertTrue(driver.getPageSource().contains("Post published."));
		//Actions actions = new Actions(driver);
		WebElement menuOption1 = updateprflPOM.adminHover();
		actions.moveToElement(menuOption1).perform();
		assertTrue(driver.getPageSource().contains("admin"));
		assertTrue(driver.getPageSource().contains("Edit My Profile"));
		assertTrue(driver.getPageSource().contains("Log Out"));
		adminlogoutPOM.clicklogoutBtn();
		assertTrue(driver.getPageSource().contains("Real Estate"));
		//click on real estate link
		addnewcategoryPOM.clickrealestatelink();
		Thread.sleep(2000);
		
		screenShot.captureScreenShot("complex test case 3:add new category and publish it");
		
		
      
	}
	
}
