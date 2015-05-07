package com.gmail.allpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.gmail.page.Page;

public class AccountPage extends Page {

	public AccountPage(WebDriver webDriver) {
		super(webDriver);

	}

	@FindBy(how = How.XPATH, using = "//div//a[@class=\"gb_r gb_P gb_j identityWidgetIcon\"]")
	public WebElement userLogo;

	@FindBy(how = How.XPATH, using = "//div//a[@class=\"gb_nb gbp1 gb_F\"]")
	public WebElement userInfo;

	@FindBy(how = How.XPATH, using = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
	public WebElement writeMail;

	@FindBy(how = How.XPATH, using = "//textarea[@class='vO']")
	public WebElement adressTo;

	@FindBy(how = How.XPATH, using = "//input[@class='aoT']")
	public WebElement subjectOfMail;

	@FindBy(how = How.XPATH, using = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
	public WebElement sendMail;

	@FindBy(how = How.XPATH, using = "//div//span//a[@class='J-Ke n0 aBU']")
	public WebElement inbox;

	@FindBy(how = How.XPATH, using = "//div[@class = 'y6']/span[contains(., 'Succsses Send')]")
	public WebElement testMail;

	@FindBy(how = How.XPATH, using = "//div//button[@class='J-at1-auR']")
	public WebElement error;

	@FindBy(how = How.XPATH, using = "//div[@class='Kj-JD']//div[@class='Kj-JD-K7 Kj-JD-K7-GIHV4']//span[@class='Kj-JD-K7-K0']")
	public WebElement errorMessage;

	public boolean isUserLogo() {
		Reporter.log("Checking the avatar image presence on screen", true);
		return userLogo.isDisplayed();
	}

	
	public void writeMail(String address, String subject, String content) {
		writeMail.click();
		//addressTo.sendKeys(address); //Commented out for negative test
		// subjectOfMail.sendKeys(subject); //Commented out for negative test
		WebElement frame1 = webDriver.findElement(By
				.cssSelector("div[class='Am Al editable LW-avf']"));
		frame1.sendKeys(content);
		
		/* This block worked in earlier versions Gmail and Selenium.
		webDriver.switchTo().frame(frame1); 
		WebElement editable = webDriver.switchTo().activeElement();
		editable.sendKeys(content);
		webDriver.switchTo().defaultContent();*/
		
		sendMail.click();
	}
	public boolean isErrorDisplayed() {
		Reporter.log("Allert error", true);
		return errorMessage.isDisplayed();
	}

}
