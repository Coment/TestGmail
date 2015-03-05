package com.gmail.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.testbase.TestBase;


public class UsdPercentageTestSuite extends TestBase{
	
	@Test (groups = {"group"})
	//TS 1.1
	public void UsdVereficationPage(){
		finHome.moveToNbu();
		finNbu.currencyVerefication(finNbu.NbuUsd, "Finance", "USD");
		kursHome.moveToMigBank();
		kursMig.currencyVerefication(kursMig.MigUsd, "Kurs", "USD");
		Assert.assertTrue(kursMig.isElementPresent(kursMig.MigUsd));
	}
	
	
	
}
