package com.finkurs.page;

import java.io.File;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.finkurs.util.ExcelUtils;

/**
 * Abstract class representation of a Page in the UI with additional features.
 * Page object pattern
 * 
 * @author Yurii Demchenko
 */
public abstract class Page {

	public WebDriver webDriver;

	/**
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	public boolean resultBool = false;

	String result = null;

	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public boolean isElementPresent(WebElement element) {
		try {
			element.isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void currencyVerification(WebElement locator, String site,
			String currency, String path) {

		String value = locator.getText();
		double curr = Double.parseDouble(value);
		ExcelUtils excel = new ExcelUtils(path);
		excel.updateKurs(curr, site, currency);
	}

	// uses excel util for checking results between two currency rates
	public boolean checkResultKurs(String currency, String path) {

		ExcelUtils excel = new ExcelUtils(path);
		result = excel.checkDifferenceRates(currency);
		if (result.equals("\"True\"")) {
			resultBool = true;
		} else {
			resultBool = false;
		}
		System.out.println("ExcelResult= " + resultBool + " " + result);
		return resultBool;
	}
}
