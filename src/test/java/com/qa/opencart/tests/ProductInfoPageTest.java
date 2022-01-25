package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;


public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}

	@Test(priority = 3)
	public void productInfoTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		
		// Problem with hard assertion is if you provide "MacBook pro11"
		// It will failed the test along with rest of the other test
		// Not recommended to use back to back hare assertion
	//  class . static method
	/*	Assert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("price"), "$2,000.00"); */
		//softAssert.assertAll();
		
		 // Convert hard assertion to soft assertion
		// create obj of softassert then use it (baseTest)
		// in case one failed it will give chance to other assert to be executed
		// Its compulsory to write  softAssert.assertAll();
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	} 

}
