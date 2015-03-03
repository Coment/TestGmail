package com.gmail.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

<<<<<<< HEAD
/**
 * 
 * @author Yurii Demchenko
=======
/*
 * 
 * @author Yuri Demchenko
>>>>>>> 8bfbdfe126c0dfee1707a37fa3d0baee4c8e360d
 */

public class WebDriverFactory {

	public static final String CHROME = "chrome";
	public static final String FIREFOX = "firefox";

	public static WebDriver webDriver;

	private WebDriverFactory() {

	}

	public static WebDriver getInstance(String browser) throws Exception {

		webDriver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		
		if (webDriver == null) {
			if (CHROME.equals(browser)) {
				setChromeDriver();
				webDriver = new ChromeDriver();
			} else if (FIREFOX.equals(browser)) {

				webDriver = new FirefoxDriver(capabilities);

			} else {
				webDriver = new HtmlUnitDriver(true);
			}
		}
		return webDriver;
	}

	private static void setChromeDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String chromeBinary = "src/main/resources/drivers/chrome/chromedriver"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.chrome.driver", chromeBinary);
	}
}