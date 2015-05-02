package com.homeaway.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DemoStoreHomePage {
	
	@FindBy(how = How.CLASS_NAME, using = "account_icon")
	private WebElement myAccount;
	
	@FindBy(how = How.XPATH, using ="//title[1]")
	private WebElement title;
	
	private WebDriver driver;
	
	
	//private final String title= "ONLINE STORE | Toolsqa Dummy Test site";
	
	public DemoStoreHomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public void loginToAccount(){
		myAccount.click();
		
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public MyAccountPage goToMyAccountPage(){
		myAccount.click();
		
		return PageFactory.initElements(driver, MyAccountPage.class);
	}

}