package com.qa.opencart.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design open cart login page") //These annotations are coming from the Allure.
@Story("US 101: Login page features")
@Feature("F50: Feature login page")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
    private static final Logger log = LogManager.getLogger(LoginPageTest.class);
	
	@Description("login page title test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle(); //No need of creation of object of the LoginPage class as we have extended BaseTest which has LoginPage object(Line 51). This will help so as to not create LoginPage object for each test @Test annotation
		log.info("actual login page title: " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE); //Objective of the test layer is to just call the methods of the page layer and assert. No actions on page elements(No Selenium code) should be written in the Test Layer.
	}

	@Description("login page url test verfication...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("verifying forgot pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void fogotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("verifying App logo exist test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}

	@Description("verifying user is able to login with correct credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogutLinkExist());
	}

	
	
	
}
