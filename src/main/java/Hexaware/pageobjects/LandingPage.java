package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	public ProductCatalogue login(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
