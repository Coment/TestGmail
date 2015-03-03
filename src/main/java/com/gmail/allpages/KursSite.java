package com.gmail.allpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.gmail.page.Page;

public class KursSite extends Page {

	public KursSite(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(how = How.XPATH, using = "//div[@class=\"menu_top_level2\"]/ul/li[4]/a")
	public WebElement KursMigBank;

	public void moveToMigBank() {
		webDriver.get("http://kurs.com.ua");
		KursMigBank.click();
	}
}

