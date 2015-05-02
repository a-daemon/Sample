package com.homeaway.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class IPhoneProductPage {
	
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using ="//form[@name='product_40']//input[@name='Buy']")
	private WebElement magicMouseBuyButton;
	
	@FindBy(how = How.XPATH, using ="//form[@name='product_96']//input[@name='Buy']")
	private WebElement iPhone4SBlackBuyButton;
	
	@FindBy(how = How.XPATH, using ="//form[@name='product_98']/input[@name='Buy']")
	private WebElement iPhone4SWhiteBuyButton;
	
	@FindBy(how = How.LINK_TEXT, using ="Go to Checkout")
	private WebElement checkout;
	
	@FindBy(how = How.LINK_TEXT, using ="Continue Shopping")
	private WebElement keepShopping;
	
	@FindBy(how = How.XPATH, using ="//form[@name='product_96']//div[@class='wpsc_product_price']//span[2]")
	private WebElement iPhonePrice;
	
	public IPhoneProductPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public CheckoutPage buyBlackiPhone4SAndCheckout(){
		iPhone4SBlackBuyButton.click();
		driver.findElement(By.linkText("Go to Checkout")).click();
		return PageFactory.initElements(driver, CheckoutPage.class);
	}
	
	public String getIphoneListedPrice(){
		return iPhonePrice.getText();
	}

}