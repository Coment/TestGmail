package com.finkurs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.finkurs.testbase.TestBase;

public class EurRateTestCase extends TestBase {

	@Test(groups = { "group" })
	// TS 1.1
	public void EurVerificationPage() {
		finHome.moveToNbu();
		finNbu.currencyVerification(finNbu.NbuEur, "Finance", "EUR", pathExcelFile);
		kursHome.moveToMigBank();
		kursMig.currencyVerification(kursMig.MigEur, "Kurs", "EUR", pathExcelFile);
		Assert.assertTrue(kursMig.checkResultKurs("EUR", pathExcelFile),
				"Kurs EUR difference is more than 30%");
	}
}
