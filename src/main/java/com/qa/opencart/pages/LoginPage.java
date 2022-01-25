package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	// 1.decleare private driver
	private WebDriver driver;
	// create an obj of ElementUtil
	private ElementUtil eleUtil;
	
	// 2. create constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	
	// 3. create By Locator
		private By emailId = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//input[@value='Login']");
		private By registerLink = By.linkText("Register");
		private By forgotPwdLink = By.linkText("Forgotten Password");
		private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	// 4. Page Actions:
	@Step("getting login page title value.....")// from Allure Report
	public String getLoginPageTittle() {
		//return driver.getTitle();
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page url.....")
	public boolean getLoginPageUrl() {
		//return driver.getCurrentUrl();
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("checking frogot pwd link exist or not.....")
	public boolean isForgotPwdLinkExist(){
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("checking register link exist or not.....")
	public boolean isRegisterLinkExist(){
		//return driver.findElement(registerLink).isDisplayed();
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("do login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with : " + un + " : " + pwd);
		// level:1
		//driver.findElement(emailId).sendKeys(un);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		// level: 2
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//in Page chaining concept when one element click navigate you to the new page 
		// it's current page responsible to give you object for landing page
		// creating obj for next page
		return new AccountsPage(driver);
		
	}
	
	@Step("do login with wring username: {0} and wrong password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with wrong credentials: " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMesg = eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMesg);
		if(errorMesg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
	}
	
	@Step("navigating to registeration page.....")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
	
	

}
