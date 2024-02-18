package com.saucedemo.pages;

import java.util.Properties;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	Properties prop;
	
	// String locators
	private String username = "#user-name";
	private String password = "#password";
	private String loginButton = "//input[@id='login-button']";
//	private String shoppingCart ="";
	
	//page constructor
	
	public LoginPage(Page page)
	{
		this.page=page;
	}
	
	//page action methods
	
	public String getLoginPageTitle()
	{
		String title = page.title();
		System.out.println("Login page Title is : "+title);
		return title;
	}
	
	public String getLoginPageUrl()
	{
		String url = page.url();
		System.out.println("Before loing page URL is : "+url);
		return url;
	}
	
	public HomePage login(String un, String pswd)
	{
		page.fill(username, un);
		page.fill(password, pswd);
		page.click(loginButton);
		return new HomePage(page);
	}

}
