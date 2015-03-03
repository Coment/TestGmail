package com.gmail.page;

<<<<<<< HEAD
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
=======
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Abstract class representation of a Page in the UI with additional features. Page object pattern
 * @author Yuri Demchenko
>>>>>>> 8bfbdfe126c0dfee1707a37fa3d0baee4c8e360d
 */
public abstract class Page {

	public WebDriver webDriver;
<<<<<<< HEAD

	/**
=======
	
	/*
>>>>>>> 8bfbdfe126c0dfee1707a37fa3d0baee4c8e360d
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
<<<<<<< HEAD

=======
	
>>>>>>> 8bfbdfe126c0dfee1707a37fa3d0baee4c8e360d
	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}
<<<<<<< HEAD

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
=======
	
	
	public boolean isElementPresent(WebElement element) {
        try {
        	element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
>>>>>>> 8bfbdfe126c0dfee1707a37fa3d0baee4c8e360d
