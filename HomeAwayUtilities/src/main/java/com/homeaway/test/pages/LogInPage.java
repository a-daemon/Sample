package com.homeaway.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	private WebDriver driver;
	
	@FindBy(how = How.ID, using = "log")
	private WebElement userName;
	
	@FindBy(how = How.ID, using ="pwd")
	private WebElement passWord;
	
	@FindBy(how = How.ID, using ="login")
	private WebElement submit;
	
	public LogInPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public MyAccountPage loginAs(final String userID, final String password){
		userName.sendKeys(userID);
		passWord.sendKeys(password);
		submit.submit();
		return PageFactory.initElements(driver, MyAccountPage.class);
	}

}