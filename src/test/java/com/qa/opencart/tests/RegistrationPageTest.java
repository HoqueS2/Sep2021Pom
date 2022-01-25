package com.qa.opencart.tests;


//import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.qa.opencart.utils.Constants;
//import com.qa.opencart.utils.ExcelUtil;
public class RegistrationPageTest extends BaseTest {
	
	
	@BeforeClass
	public void setupRegistration() {
		registrationPage = loginPage.goToRegisterPage();
	}
	
	
	@Test
	public void userRegistrationTest() {

		Assert.assertTrue(
				registrationPage.accountRegistration("nono", "rars", 
						"nmyghtxbncxm@gmal.com", "876", "tewsq@123", "yes"));
	}

	
	
	
	
	

}
