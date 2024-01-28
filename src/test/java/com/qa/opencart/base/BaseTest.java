package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop; //Without keyword 'protected' we cannot access the prop in the Child classes(Subclasses like LoginPageTest) of BaseTest(Parent Class). If kept 'default' i.e. without any access modifier, it cannot be accessed outside the package classes.
	DriverFactory df;
	protected LoginPage loginPage; //All the references of Pages we are maintaining here because what if we later other tests need the object references of these page classes. So, since all the classes are inheriting the BaseTest, they can easily access. 
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	
	protected SoftAssert softAssert;
	
    private static final Logger log = LogManager.getLogger(BaseTest.class);

	//Before running any TestClass, the BaseTest class will be executed first.(Global precondition for all test classes)
	@Parameters({"browser", "browserversion", "testname"})
	@BeforeTest
	public void setup(String browserName, String browserVersion, String testName) {
		log.info(browserName + " : " + browserVersion + " " + testName);
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver); //Object of LoginPage got created.
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
		log.info("browser is closed....");
	}
	

}
