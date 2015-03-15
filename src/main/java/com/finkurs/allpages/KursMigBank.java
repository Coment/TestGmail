package com.finkurs.allpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.finkurs.page.Page;

public class KursMigBank extends Page {

	public KursMigBank(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	String site = "Kurs";
	String usd = "USD";
	String eur = "EUR";

	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[3]/div[@class=\"value\"]")
	public WebElement MigUsd;

	@FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[3]/div[@class=\"value\"]")
	public WebElement MigEur;

}
