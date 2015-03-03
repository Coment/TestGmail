package com.gmail.allpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.gmail.page.Page;



public class FinanceNbu extends Page {

	public FinanceNbu(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	String site = "Finance";
	String usd = "USD";
	String eur = "EUR";

	@FindBy(how = How.XPATH, using = "//table//tr[./td[contains(text(),'USD')]]/td[4]")
	public WebElement NbuUsd;

	@FindBy(how = How.XPATH, using = "//table//tr[./td[contains(text(),'EUR')]]/td[4]")
	public WebElement NbuEur;

	
}