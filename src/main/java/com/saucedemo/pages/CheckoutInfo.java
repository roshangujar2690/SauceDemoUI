package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class CheckoutInfo {
	
	private Page page;
	private String firstName="//input[@id='first-name']";
	private String lastName="//input[@id='last-name']";
	private String pinCode="//input[@id='postal-code']";
	private String continueBtn="//input[@id='continue']";

	public CheckoutInfo(Page page) 
	{
		
		this.page=page;
	}
	
	public String getCheckoutPageUrl()
	{
		String url = page.url();
		System.out.println("Checkout page Title is : "+url);
		return url;
	}
	
	public String enterFirsttName (String fn)
	{
		page.locator(firstName).fill(fn);
		return fn;
	}
	
	public String enterLastName (String ln)
	{
		page.locator(lastName).fill(ln);
		return ln;
	}
	
	public String enterPinCode (String pc)
	{
		page.locator(pinCode).fill(pc);
		return pc;
	}
	
	public CheckoutOverview clickContinue()
	{
		page.click(continueBtn);
		
		return new CheckoutOverview(page);
	}
    

}
