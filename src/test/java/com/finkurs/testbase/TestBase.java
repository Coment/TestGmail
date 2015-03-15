package com.finkurs.testbase;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.finkurs.allpages.AccountPage;
import com.finkurs.allpages.FinanceNbu;
import com.finkurs.allpages.FinanceSite;
import com.finkurs.allpages.HomePage;
import com.finkurs.allpages.KursMigBank;
import com.finkurs.allpages.KursSite;
import com.finkurs.util.Browser;
import com.finkurs.util.ExcelUtils;
import com.finkurs.util.PropertyLoader;
import com.finkurs.webdriver.WebDriverFactory;

/*
 * Base class for all the test classes
 * 
 * @author Yuri Demchenko
 */

public class TestBase {

	// protected WebDriver webDriver;
	protected EventFiringWebDriver eventDriver;
	protected String websiteUrl;
	protected Browser browser;

	protected HomePage home;

	protected AccountPage user;
	protected FinanceSite finHome;
	protected FinanceNbu finNbu;
	protected KursSite kursHome;
	protected KursMigBank kursMig;
	protected String pathExcelFile;

	@Parameters({ "browserName" })
	@BeforeMethod(groups = { "group" })
	public void init(String browserName) throws Exception {

		browser = new Browser();
		browser.setName(browserName);

		WebDriver webDriver2 = WebDriverFactory.getInstance(browser.getName());
		eventDriver = new EventFiringWebDriver(webDriver2);
		eventDriver.register(new WebDriverListener());
		eventDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		pathExcelFile = "C:\\RegressionData.xlsx";
		ExcelUtils excelcr = new ExcelUtils(pathExcelFile);
		excelcr.createSheet();

		finHome = PageFactory.initElements(eventDriver, FinanceSite.class);
		finNbu = PageFactory.initElements(eventDriver, FinanceNbu.class);
		kursHome = PageFactory.initElements(eventDriver, KursSite.class);
		kursMig = PageFactory.initElements(eventDriver, KursMigBank.class);

	}

	@AfterMethod(groups = { "group" })
	public void reopenApp() throws Exception {
		if (eventDriver != null) {
			eventDriver.close();// eventDriver.quit() does not work correctly
								// with firefox
			eventDriver = null;
		}
	}

}
