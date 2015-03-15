package com.finkurs.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.finkurs.testbase.TestBase;

public class UsdRateTestCase extends TestBase {

	@Test(groups = { "group" })
	// TS 1.1
	public void UsdVerificationPage() {
		finHome.moveToNbu();
		System.out.println("TestPath = "+ pathExcelFile);
		finNbu.currencyVerification(finNbu.NbuUsd, "Finance", "USD", pathExcelFile);
		kursHome.moveToMigBank();
		kursMig.currencyVerification(kursMig.MigUsd, "Kurs", "USD", pathExcelFile);
		Assert.assertTrue(kursMig.checkResultKurs("USD", pathExcelFile),
				"Kurs USD difference is more than 30%");
	}

}
