package com.homeaway.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {
	
	private WebDriver driver;
	
	@FindBy(how = How.ID, using = "log")
	private WebElement userName;
	
	@FindBy(how = How.ID, using ="pwd")
	private WebElement passWord;
	
	@FindBy(how = How.ID, using ="login")
	private WebElement submit;
	
	@FindBy(how = How.LINK_TEXT, using ="Register")
	private WebElement register;
	
	@FindBy(how = How.LINK_TEXT, using ="Log in")
	private WebElement logInLink;
	
	@FindBy(how = How.LINK_TEXT, using ="Your Details")
	private WebElement accountDetails;
	
	@FindBy(how = How.LINK_TEXT, using ="Log out")
	private WebElement logOut;
	
	@FindBy(how = How.ID, using ="wpsc_checkout_form_8")
	private WebElement zipCode;
	
	@FindBy(how = How.ID, using ="wpsc_checkout_form_18")
	private WebElement phone;
	
	@FindBy(how = How.NAME, using ="submit")
	private WebElement submitProfile;
	
	public MyAccountPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public MyAccountPage loginAs(final String userID, final String password){
		typeUsername(userID);
		typePassword(password);
		return submitLogin();
	}
	
	
	public MyAccountPage typeUsername(final String name){
		userName.sendKeys(name);
		return this;
	}
	
	public MyAccountPage typePassword(final String passwd){
		passWord.sendKeys(passwd);
		return this;
	}
	
	public MyAccountPage submitLogin(){
		submit.submit();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver d) {
                return d.findElement(By.linkText("Log out")).isDisplayed();
            }
        });
		return PageFactory.initElements(driver, MyAccountPage.class);
	}
	
	public MyAccountPage openAccountDetails(){
		accountDetails.click();
		return PageFactory.initElements(driver, MyAccountPage.class);
	}
	
	public final String getZipCodeValue(){
		return zipCode.getAttribute("value");
	}
	
	public MyAccountPage updateZipCodeValue(final String zip){
		this.zipCode.clear();
		submitProfile.click();
		this.zipCode.sendKeys(zip);
		submitProfile.click();
		return PageFactory.initElements(driver, MyAccountPage.class);
	}
	
	public final String getPhoneNumberValue(){
		return phone.getAttribute("value");
	}
	
	public MyAccountPage updatePhoneNumberValue(final String phoneNumber){
		this.phone.click();
		this.phone.clear();
		this.phone.sendKeys(phoneNumber);
		//the following is a workaround to keep the new value from disappearing.
		this.phone.sendKeys(Keys.RETURN);
		accountDetails.click();
		submitProfile.click();
		return PageFactory.initElements(driver, MyAccountPage.class);
	}
	
	public LogInPage logOut(){
		logOut.click();
		return PageFactory.initElements(driver, LogInPage.class);
	}

}