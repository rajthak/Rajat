package com.training.sanity.tests;

import static org.testng.AssertJUnit.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLogoutPOM;
import com.training.pom.ChangeRolePOM;
import com.training.pom.GeneratePasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.UpdateProfilePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ChangeRoleTest {
	/*public WebDriver driver;
	public WebDriverWait wait;
	String appURL = "https://www.linkedin.com/";
	*/
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public UpdateProfilePOM updateprflPOM;
	public GeneratePasswordPOM generatepwdPOM;
	public AdminLogoutPOM adminlogoutPOM;
	public ChangeRolePOM changerolePOM;
	public static Properties properties;
	public ScreenShot screenShot;
	
	
	@BeforeMethod
	public void testSetup() throws IOException {
		
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		adminlogoutPOM=new AdminLogoutPOM(driver);
		updateprflPOM=new UpdateProfilePOM(driver);
		changerolePOM=new ChangeRolePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	

	@Test(dataProvider="empLogin")
	public void VerifyInvalidLogin(String usercheckbox, String changerole) throws InterruptedException {
        loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		AssertJUnit.assertTrue(driver.getPageSource().contains("Dashboard"));
	    Actions actions = new Actions(driver);
		WebElement menuOption = changerolePOM.userslink;
		actions.moveToElement(menuOption).perform();
		Thread.sleep(2000);
		assertTrue(driver.getPageSource().contains("All Users"));
		assertTrue(driver.getPageSource().contains("Add New"));
		assertTrue(driver.getPageSource().contains("Your Profile"));
		//click on all users
		//Thread.sleep(2000);
		changerolePOM.clickalluserslink();
		Thread.sleep(2000);
		assertTrue(driver.getPageSource().contains("Users"));
		changerolePOM.sendusersearchtextbox(usercheckbox);
		changerolePOM.clicksearchuserbtn();
		
		changerolePOM.clickcommoncheckboxbtn();
		
		Select objSelect =new Select(changerolePOM.changeroledropdown);
		objSelect.selectByVisibleText(changerole);
		
		Thread.sleep(2000);
		changerolePOM.clickchangerolebtn();
		Thread.sleep(2000);
		assertTrue(driver.getPageSource().contains("Changed roles."));
		
		screenShot.captureScreenShot("complex test case 2:change role of users");
		
	}
	
	@DataProvider(name="empLogin")
	public Object[][] loginData() {
		Object[][] arrayObject = getExcelData("C:/Users/RajatThakur/git/Rajat/final-framework-testng/testdata/sampledoc.xls","Sheet1","Users Checkbox","Change Role");
		return arrayObject;
	}

	/**
	 * @param File Name
	 * @param Sheet Name
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName, String usercheckbox, String changerole) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}
}