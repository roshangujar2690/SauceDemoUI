package com.saucedemo.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
	
	public Page initBrowser(Properties prop)
	{
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is :" +browserName);
		
		playwright=Playwright.create();
		
		switch (browserName) {
		case "chromium": 
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox": 
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari": 
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome": 
			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;

		default:
			System.out.println("Please pass the correct browser name.");
			break;
		}
		
		browserContext = browser.newContext();
		page = browserContext.newPage();
		page.navigate(prop.getProperty("loginUrl").trim());
		
		return page;
	}
	
	//initialize the properties from the config.properties file
	public Properties init_prop() throws IOException
	{
		FileInputStream fip = new FileInputStream("./src/test/resources/config/config.propertiees");
		
		prop=new Properties();
		prop.load(fip);
		return prop;
	}

}
