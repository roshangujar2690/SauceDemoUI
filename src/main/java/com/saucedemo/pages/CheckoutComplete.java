package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class CheckoutComplete {

	private Page page;
	
	private String pageTitle =".title";
	
	public CheckoutComplete(Page page)
	{
		this.page=page;
	}
	
	public String getPageTitle()
	{
		String title=page.locator(pageTitle).textContent();
		return title;
	}
	
}
