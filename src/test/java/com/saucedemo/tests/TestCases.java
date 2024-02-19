package com.saucedemo.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseClass;

public class TestCases extends BaseClass{


	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, "Swag Labs");
	}
	
	@Test(priority=2)
	public void loginPageUrlTest()
	{
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("loginUrl").trim());
	}
	
	@Test(priority=3)
	public void homePageUrlTest()
	{
		homePage=loginPage.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String actualUrl=homePage.getHomePageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("homeUrl").trim());
	}
	
	@Test(priority=4, dependsOnMethods="loginPageUrlTest")
	public void addLeastExpensiveProductTest()
	{
		List<String> prices =homePage.getallProductPrices();
		
		List<Float> finalPrice =convertPrice(prices);
		
        float min= Collections.min(finalPrice);
        
        int index_minPrice = finalPrice.indexOf(min);
        
        System.out.println("Min price is:" + min);
        System.out.println("Index of minimum price: " + index_minPrice);
        
        homePage.addToCart(index_minPrice); 
        
        int badgeCount=homePage.getCartBadgeValue();
        Assert.assertEquals(badgeCount, 1);
        
        String name = homePage.getProductName(index_minPrice);
        System.out.println("The least expensive product name is "+name);
        Assert.assertEquals(name, "Sauce Labs Onesie");
        
	}
	
	@Test(priority=5,dependsOnMethods="loginPageUrlTest")
	public void addMostExpensiveProductTest()
	{
		List<String> prices =homePage.getallProductPrices();
		
		List<Float> finalPrice =convertPrice(prices);
		
        float max= Collections.max(finalPrice);
        
        int index_maxPrice = finalPrice.indexOf(max);
        
        System.out.println("Max price is:" + max);
        System.out.println("Index of maximum price: " + index_maxPrice);
        
        homePage.addToCart(index_maxPrice);
        
        int badgeCount=homePage.getCartBadgeValue();
        Assert.assertEquals(badgeCount, 2);
        
        String name = homePage.getProductName(index_maxPrice);
        System.out.println("The most expensive product name is "+name);
        Assert.assertEquals(name, "Sauce Labs Fleece Jacket");
      
	}
	
	@Test(priority=6)
	public void productNameTest()
	{
		cartPage=homePage.clickShoppingCart();
		String cheapestProductName=cartPage.geFirsttProductName();
		Assert.assertEquals(cheapestProductName, "Sauce Labs Onesie");
		
		String mostExpensiveProductName=cartPage.geSecondProductName();
		Assert.assertEquals(mostExpensiveProductName, "Sauce Labs Fleece Jacket");
		
	}
	
	@Test(priority=7)
	public void checkoutTest()
	{
		checkoutInfo=cartPage.clickCheckout();
		checkoutInfo.enterFirsttName("Roshan");
		checkoutInfo.enterLastName("Gujar");
		checkoutInfo.enterPinCode("111111");
		String actualUrl = checkoutInfo.getCheckoutPageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("checkoutUrl"));
		
	}
	
	@Test(priority=8)
	public void priceAndTaxVerifier()
	
	{
		checkoutOverview=checkoutInfo.clickContinue();
		String productTotal_str = checkoutOverview.getProductTotal();
		float productTotal= Float.parseFloat(productTotal_str.replace("Item total: $",""));
		
		String totalWithTax_str = checkoutOverview.getTotalWithTax();
		float totalWithTax= Float.parseFloat(totalWithTax_str.replace("Total: $",""));

        float expectedTotalWithTax = (float) (productTotal * 1.08);
        
        if ((totalWithTax - expectedTotalWithTax) < 0.1)
		{
			System.out.println("Combined total price and tax rate equate to 8%");
			Assert.assertTrue(true);
			
		}
        else
        {
        	System.out.println("Combined total price and tax rate do not equate to 8%");
        	Assert.assertTrue(false);
        	
        }	
		
	}
	@Test(priority=9)
	public void completeCheckoutTest()
	{
		checkoutComplete =checkoutOverview.clickFinish();
		String actualTitle = checkoutComplete.getPageTitle();
		Assert.assertEquals(actualTitle, "Checkout: Complete!");
		
	}
	
	//converting string list into float list
		public List<Float> convertPrice(List<String> prices)
		{
			List<Float> floatList = new ArrayList<>();
			
	        for (String str : prices) 
		        {
		        	floatList.add(Float.parseFloat(str.replace("$","")));
		        }
	        
	        for (float prc : floatList)
		        {
		        	System.out.println(prc);
		        }
	        return (List<Float>) floatList;
		}

}
