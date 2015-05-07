package com.gmail.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.gmail.allpages.AccountPage;
import com.gmail.allpages.HomePage;
import com.gmail.util.Browser;
import com.gmail.util.PropertyLoader;
import com.gmail.webdriver.WebDriverFactory;


/**
 * Base class for all the test classes
 * 
 * @author Yuri Demchenko
 */

public class TestBase{
	
	//protected WebDriver webDriver;
	protected EventFiringWebDriver eventDriver;
	protected String websiteUrl;
	protected Browser browser;
	
	protected static String testUrl;
	protected static String username;
	protected static String password;
	protected static String email;
	protected static String subject;
	protected static String content;
	
	public static String getEmail() {
		return email;
	}

	public static String getSubject() {
		return subject;
	}


	public static String getContent() {
		return content;
	}
	
	public static String getUsermail() {
		return username;
	}

	public static String getPassword() {
		return password;
	}
	
	public static String getUrl() {
		return testUrl;
	}
	
	protected HomePage home;
	
	protected AccountPage user;

	@Parameters({"browserName"})
	@BeforeMethod (groups = {"group"})
	public void init(String browserName) throws Exception {

		browser = new Browser();
		browser.setName(browserName);
		
		testUrl = PropertyLoader.loadProperty("test.url");
		username = PropertyLoader.loadProperty("user.username");
		password = PropertyLoader.loadProperty("user.password");
		subject = PropertyLoader.loadProperty("mail.subject");
		content = PropertyLoader.loadProperty("mail.content");
		
		
		WebDriver webDriver2 = WebDriverFactory.getInstance(browser.getName());
		eventDriver = new EventFiringWebDriver(webDriver2);
		eventDriver.register(new WebDriverListener());
		eventDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		eventDriver.get(testUrl);
		home = PageFactory.initElements(eventDriver, HomePage.class);
		user = PageFactory.initElements(eventDriver, AccountPage.class);
	
	}
	
	@AfterMethod(groups = {"group"})
	public void reopenApp() throws Exception{	
		if (eventDriver != null) {
			eventDriver.quit();
			eventDriver = null;
		}
	}
	
}
