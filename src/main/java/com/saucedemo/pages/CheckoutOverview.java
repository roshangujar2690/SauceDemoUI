package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class CheckoutOverview {

	private Page page;
	
	private String productTotal = "//div[@class='summary_subtotal_label']";
	private String tax = "//div[@class='summary_tax_label']";
	private String totalWithTax = "//div[@class='summary_info_label summary_total_label']";
	private String finishBtn ="#finish";
	
	
	public CheckoutOverview(Page page) 
	{
		
		this.page=page;
	}
	
	public String getProductTotal()
	{
		String prdTotal = page.locator(productTotal).textContent();
		return prdTotal;
	}
	
	public String getTax()
	{
		String tax_ = page.locator(tax).textContent();
		return tax_;
	}
	
	public String getTotalWithTax()
	{
		String totalPriceWithTax = page.locator(totalWithTax).textContent();
		return totalPriceWithTax;
	}
	
	public CheckoutComplete clickFinish()
	{
		page.click(finishBtn);
		return new CheckoutComplete(page);
	}
}
