package com.saucedemo.base;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.saucedemo.factory.PlaywrightFactory;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutComplete;
import com.saucedemo.pages.CheckoutInfo;
import com.saucedemo.pages.CheckoutOverview;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class BaseClass {
	PlaywrightFactory pf;
	Page pg;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected CartPage cartPage;
	protected CheckoutInfo checkoutInfo;
	protected CheckoutOverview checkoutOverview;
	protected CheckoutComplete checkoutComplete;
	
	
	@BeforeTest(alwaysRun=true)
	public void setup() throws IOException
	{
		pf = new PlaywrightFactory();
		prop=pf.init_prop();
		pg = pf.initBrowser(prop);
		loginPage=new LoginPage(pg);
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown()
	{
//		pg.pause();
		pg.context().browser().close();
	}
}
