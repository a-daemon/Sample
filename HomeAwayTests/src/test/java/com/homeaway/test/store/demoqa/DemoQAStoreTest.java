package com.homeaway.test.store.demoqa;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.homeaway.test.pages.CheckoutPage;
import com.homeaway.test.pages.DemoStoreHomePage;
import com.homeaway.test.pages.IPhoneProductPage;
import com.homeaway.test.pages.MyAccountPage;

@Test(groups= {"demo-store", "all"})
public class DemoQAStoreTest {
	private static final String EMPTY_CART_MESSAGE = "Oops, there is nothing in your cart.";
	private static final String LOGIN_PASSWD = "43iPSoAZrTCg";
	private static final String LOGIN_ID = "dhunt";
	private WebDriver browser;
	private final String homePage = "http://store.demoqa.com/";
	private DemoStoreHomePage landingPage;
	
	@BeforeClass
	public void init(){
		browser = new FirefoxDriver();
		goToStartingPage();
	}
	
	@AfterClass(alwaysRun=true)
	public void end(){
		browser.quit();
	}
	
	@Test
	public void udpateAccountDetails(){
		MyAccountPage map = landingPage.goToMyAccountPage();
		map = map.loginAs(LOGIN_ID, LOGIN_PASSWD);
		map = map.openAccountDetails();
		
		String a = map.getPhoneNumberValue();
		Assert.assertTrue(!a.isEmpty(), "The phone number value should not be empty.");
		
		//get a random five digit number
		Random ran = new Random();
		int x = 1000000 + ran.nextInt(1000000) + 6;
		String b = String.valueOf(x);
		map.updatePhoneNumberValue(b);
		map.logOut();
		
		//go back to starting point and login again.
		goToStartingPage();
		map = landingPage.goToMyAccountPage();
		map = map.loginAs(LOGIN_ID, LOGIN_PASSWD);
		map = map.openAccountDetails();
		String c = map.getPhoneNumberValue();
		Assert.assertFalse(a.equalsIgnoreCase(c), "The two values should not be equal!");
		Assert.assertTrue(b.equalsIgnoreCase(c), "The two values should be equal!");
	}
	
	@Test(dependsOnMethods="udpateAccountDetails")
	public void submitIphoneOrder(){
		String iPhonePage = "http://store.demoqa.com/products-page/product-category/iphones/";
		browser.get(iPhonePage);
		
		IPhoneProductPage ipp = PageFactory.initElements(browser, IPhoneProductPage.class);
		String p = ipp.getIphoneListedPrice();
		
		//Select black iPhone 4s
		CheckoutPage cp = ipp.buyBlackiPhone4SAndCheckout();
		
		cp = cp.continueOrder();
		
		//get the prices listed on the final page.
		String a = cp.getTotalShippingPrice();
		String b = cp.getTotalItemPrice();
		String c = cp.getCheckOutTotal();
		
		Assert.assertTrue(p.equalsIgnoreCase(b), "The item price from the Product page [" + p + "] "
				+ "does not equal the item price [" + b + "] from the checkout page.");
		
		//Add our values
		BigDecimal ip = new BigDecimal(b.substring(1));
		BigDecimal sp = new BigDecimal(a.substring(1));
		BigDecimal tp = ip.add(sp);
		
		//compare total price with our calculated price.
		Assert.assertTrue(tp.toString().equalsIgnoreCase(c.substring(1)), "The total price should "
				+ "equal the calculated price.");
		
		cp.placeOrder();
	}
	
	@Test(dependsOnMethods="submitIphoneOrder")
	public void testHavingEmptyCart(){
		String iPhonePage = "http://store.demoqa.com/products-page/product-category/iphones/";
		browser.get(iPhonePage);
		
		IPhoneProductPage ipp = PageFactory.initElements(browser, IPhoneProductPage.class);
		
		CheckoutPage cp = ipp.buyBlackiPhone4SAndCheckout();
		cp = cp.removeItem();
		String msg = cp.getDisplayedMsg();
		
		Assert.assertTrue(EMPTY_CART_MESSAGE.equalsIgnoreCase(msg));
	}
	
	private void goToStartingPage(){
		browser.get(homePage);
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		landingPage = PageFactory.initElements(browser, DemoStoreHomePage.class);
	}

}