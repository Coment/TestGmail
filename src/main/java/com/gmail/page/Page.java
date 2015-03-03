package com.gmail.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gmail.allpages.ExcelUtils;

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

	public void currencyVerefication(WebElement locator, String site,
			String currency) {

		String value = locator.getText();
		double curr = Double.parseDouble(value);
		ExcelUtils excell = new ExcelUtils();
		excell.updateKurs(curr, site, currency);
	}
}
