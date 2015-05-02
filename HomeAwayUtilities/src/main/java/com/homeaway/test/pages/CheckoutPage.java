package com.homeaway.test.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using ="//form[@name=‘product_40’]//input[@name=‘Buy’]")
	private WebElement magicMouseBuyButton;
	
	@FindBy(how = How.XPATH, using ="//form[@name=‘product_96’]//input[@name=‘Buy’]")
	private WebElement iPhone4SBlackBuyButton;
	
	@FindBy(how = How.XPATH, using ="//form[@name=‘product_98’]//input[@name=‘Buy’]")
	private WebElement iPhone4SWhiteBuyButton;
	
	@FindBy(how = How.ID, using ="wpsc_checkout_form_9")
	private WebElement emailAddr;
	
	@FindBy(how = How.XPATH, using ="//input[@value='Remove']")
	private WebElement remove;
	
	@FindBy(how = How.CLASS_NAME, using ="entry-content")
	private WebElement msg;
	
	@FindBy(how = How.LINK_TEXT, using ="Continue")
	private WebElement keepGoing;
	
	@FindBy(how = How.XPATH, using ="//input[@value='Purchase']")
	private WebElement submitOrder;
	
	@FindBy(how = How.XPATH, using ="//form[@name='product_96']//div[@class='wpsc_product_price']//span[2]")
	private WebElement iPhonePrice;
	
	@FindBy(how = How.XPATH, using ="//form[@class='wpsc_checkout_forms']//span[@class='pricedisplay']")
	private List<WebElement> pricing;
	
	@FindBy(how = How.XPATH, using ="//form[@class='wpsc_checkout_forms']//span[@class='pricedisplay'][1]")
	private WebElement totalShippingPrice;
	
	@FindBy(how = How.XPATH, using ="//form[@class='wpsc_checkout_forms']//span[@class='pricedisplay'][2]")
	private WebElement totalItemPrice;
	
	@FindBy(how = How.XPATH, using ="//form[@class='wpsc_checkout_forms']//span[@class='pricedisplay'][4]")
	private WebElement checkOutTotal;
	
	public CheckoutPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public CheckoutPage removeItem(){
		remove.click();
		return PageFactory.initElements(driver, CheckoutPage.class);
	}
	
	public CheckoutPage continueOrder(){
		keepGoing.click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//input[@value='Purchase']")).isDisplayed();
            }
        });
		return PageFactory.initElements(driver, CheckoutPage.class);
	}
	
	public CheckoutPage placeOrder(){
		submitOrder.click();
		return PageFactory.initElements(driver, CheckoutPage.class);
	}
	
	public String getTotalShippingPrice() {
		return pricing.get(0).getText();
	}
	
	public String getTotalItemPrice() {
		return pricing.get(1).getText();
	}
	
	public String getCheckOutTotal() {
		return pricing.get(3).getText();
	}
	
	public String getDisplayedMsg() {
		return msg.getText();
	}

}