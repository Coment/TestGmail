package com.gmail.allpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


import com.gmail.page.Page;

public class HomePage extends Page {

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}
	@FindBy (how = How.XPATH, using = "//div//img[@class=\"logo\"]")
	public WebElement siteLogo;
	
	@FindBy (how = How.ID, using = "Email")
	public WebElement email;
	
	@FindBy (how = How.ID, using = "Passwd")
	public WebElement passwd;
	
	@FindBy (how = How.XPATH, using = "//input[@id='signIn']")
	public WebElement submit;
	
	public boolean isSiteLogoDisplayed(){
		return siteLogo.isDisplayed();
	}
	
	public AccountPage loginPage (String login, String password){
		email.clear();
		passwd.clear();
		email.sendKeys(login);
		passwd.sendKeys(password);
		submit.click();
		//Reporting to html report view
		Reporter.log("Loging to User Account Page with e-mail " + login + " and password " + password, true);
		return PageFactory.initElements(webDriver, AccountPage.class);
	}
}
