package com.gmail.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.gmail.testbase.TestBase;

public class HomePageTestSuite extends TestBase{
	
	@Test (groups = {"group"})
	//TS 1.1
	public void openHomePage(){
		Assert.assertTrue(home.isSiteLogoDisplayed());
	}
	
	
	
}
