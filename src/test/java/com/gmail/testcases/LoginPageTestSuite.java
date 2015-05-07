package com.gmail.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.allpages.AccountPage;
import com.gmail.testbase.TestBase;


public class LoginPageTestSuite extends TestBase{  
	
	@Test (groups = {"group"})
	public void openUserPage() throws InterruptedException{
		home.loginPage(getUsermail(), getPassword());	
		user.writeMail(username, subject, content);
		Assert.assertTrue(user.isErrorDisplayed());
	}

}
