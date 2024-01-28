package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage{
		
	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");
	
    private static final Logger log = LogManager.getLogger(LoginPage.class);

	// page constructor //Every page class will have its own constructor and it expects one driver.
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver); //So that when object of LoginPage is created driver is passed to ElementUtil Object as well which is used in the page action methods below.
		
	}

	// page actions/methods:
	@Step("getting login page title") //@Step annotation is coming from Allure and is used only on page action methods.
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("login page title:" + title);
		log.info("login page title:" + title);
		return title;
	}

	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("login page url:" + url);
		log.info("login page url:" + url);
		return url;
	}

	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("checking logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
		
	}

	@Step("username is : {0} and password {1} ") //In the allure report it will show the username and password value. If we have three parameters, increase the same numbers like 1,2,3
	public AccountsPage doLogin(String username, String pwd) {
		//System.out.println("creds are: " + username + " : " + pwd);
		log.info("creds are : " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn); //IMP: Whenever we do any click or login button or link and we land on a new page afterwards, then it is the method responsibility to return the landing page class object.
		
		return new AccountsPage(driver); //Returning Landing page class object.
		//IMP: Suppose the AccountPage is not present or created yet, then it will give suggestion to create a AccountsPage, This is TDD(Test Driven Development) approach which means to fulfill my TC, whatever the new classes or methods that I have to create, Will keep creating it.
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

	
	
	

}
