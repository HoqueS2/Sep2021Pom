package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart App - Login Page")
@Story("US 101: Open Cart Login Design with multiple features")
@Listeners(TestAllureListener.class)
//  This class will execute my test which is extend from BaseTest

public class LoginPageTest extends BaseTest {
	
	@Description("login Page Title Test") // from Allure report
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTittle();
		System.out.println("page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login Page url Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		//loginPage.getLoginPageUrl();
		//System.out.println("Page title : " + actUrl);
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	
	@Description("forgot pwd link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	

	@Description("register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	//@Test(priority = 4, enabled=false) // this kewword use to avoid execute this specific test (enabled=false)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("login Test with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		// level :1
		//loginPage.doLogin("sharmin20@gmail.com", "Selenium12345");
		//level: 2
		//loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		// level :3 now doLogin() method give you next landing page obj so on the base of that 
		// we can do assert the loginTest that its successfully landing on the other page
		accountsPage= loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	   Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	   // here doLogin() give you accountpage obj and verify the title on new page
	}

	

}
