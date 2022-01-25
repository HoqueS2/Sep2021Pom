package com.qa.opencart.tests;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;
public class BaseTest {
// To call the driver from the DriverFactory class we need to create an instance / obj
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	AccountsPage accountsPage;
	// here we create reference from this class 
	SearchResultsPage searchResultPage;
	ProductInfoPage productInfoPage;
	RegistrationPage registrationPage;
	SoftAssert softAssert;
	
	
	
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		//driver = df.init_driver("chrome");
		driver = df.init_driver(prop);
		loginPage= new LoginPage (driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
