package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class CartPage {
	
	private Page page;
	
	// String locators
	
//	private String productName ="//a[@href='#']/child::div";
	private String checkoutBtn ="//button[@id='checkout']";
	private String firstProduct ="div.page_wrapper div.cart_contents_container div.cart_list div.cart_item:nth-child(3) div.cart_item_label a:nth-child(1) > div.inventory_item_name";
	private String secondProduct ="div.page_wrapper div.cart_contents_container div.cart_list div.cart_item:nth-child(4) div.cart_item_label a:nth-child(1) > div.inventory_item_name";
	
	//page constructor

	public CartPage (Page page) 
	{
		this.page=page;
		
	}
	
	//page action methods
	
	public String getHomePageUrl()
	{
		String url = page.url();
		System.out.println("Cart page Title is : "+url);
		return url;
	}
	
	public String geFirsttProductName()
	{
		String prdctName=page.locator(firstProduct).textContent(null);
		return prdctName;
	}
	
	public String geSecondProductName()
	{
		String prdctName=page.locator(secondProduct).textContent(null);
		return prdctName;
	}
	
	public CheckoutInfo clickCheckout()
	{
		page.click(checkoutBtn);
		
		return new CheckoutInfo(page); 
	}

}
