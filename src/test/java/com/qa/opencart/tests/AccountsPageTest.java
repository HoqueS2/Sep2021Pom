package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
//import com.qa.opencart.utils.Errors;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accPageSetup() {
		// doLogin () method give you accountsPage obj -> BaseTest -> LoginPage
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
// LoginPage and AccountPage become couple together by the help of channing concept	2 level
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("acc page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accountsPage.getAccountsPageHeader();
		System.out.println("acc page header is: " + header);
		//Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MESSG);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MESSG);
	}
	
	
	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	
	@Test(priority = 4)
	// ASSERT list of String
	// Header and tittle are always constant
	// Create one method in Constant class array list then compare actual vs expected
	public void accPageSectionsTest() {
		List<String> actAccSecList = accountsPage.getAccountSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
	}
	
	// Every DataProvider return 2 dimention data array
	@DataProvider
	public Object[][] productData() {
		return new Object[][] { 
			{ "MacBook" }, 
			{ "Apple" }, 
			{ "Samsung" }, 
			};
	}
	
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
		// Level: 1
		 //accountsPage.doSearch("MacBook Pro");
		 // Level:2  Now I want to test more date but don't want to call doSearch method again and again
		 // so I can parameterise my test
		//accountsPage.doSearch(productName); // 3 times this will be executed
		//Level: 3
		// Here it coonect with reference from doSearch() method
		searchResultPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getProductsListCount() > 0);
	}
	
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { // 2 colum 4 row
			{ "MacBook" , "MacBook Pro"}, 
			{ "iMac", "iMac" }, 
			{ "Samsung" , "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
			};
	}

	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
