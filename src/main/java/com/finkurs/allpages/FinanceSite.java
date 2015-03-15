package com.finkurs.allpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.finkurs.page.Page;

public class FinanceSite extends Page {

	public FinanceSite(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how = How.XPATH, using = "//ul[@class=\"sn_menu\"]//a[@href='/nbu/']")
	public WebElement NBU;

	public void moveToNbu() {
		webDriver.get("http://finance.i.ua");
		NBU.click();
	}
}