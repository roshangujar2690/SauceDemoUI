package com.saucedemo.pages;

import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	Properties prop;
	
	// String locators
	
//	private String products = ".inventory_item";
	private String productPrices = ".inventory_item_price";
	private String addToCartBtn = "button.btn";
	private String shoppingBadge =".shopping_cart_badge";
	private String shoppingCart =".shopping_cart_link";
	private String productName ="//a[@href='#']/child::div";
	
	//page constructor
	
		public HomePage (Page page)
		{
			this.page=page;
		}
		
		//page action methods
		
		public String getHomePageUrl()
		{
			String url = page.url();
			System.out.println("Home page Title is : "+url);
			return url;
		}
		
		public List<String> getallProductPrices()
		{
			Locator itemsPrices = page.locator(productPrices);
			List<String> prices = itemsPrices.allTextContents();
			for (String s : prices)
			{
				System.out.println(s);
			}
			return prices;
		}
		
		public int getCartBadgeValue()
		{
			String str = page.textContent(shoppingBadge);
			int count  =Integer.parseInt(str);
			return count;
		}
		
		public void addToCart(int index)
		{
			page.locator(addToCartBtn).locator("nth="+index).click();
			
		}
		
		public String getProductName(int index)
		{
			String prdctName=page.locator(productName).locator("nth="+index).textContent();
			return prdctName;
		}
		
		public CartPage clickShoppingCart()
		{
			page.click(shoppingCart);
			
			return new CartPage(page); 
		}

}
