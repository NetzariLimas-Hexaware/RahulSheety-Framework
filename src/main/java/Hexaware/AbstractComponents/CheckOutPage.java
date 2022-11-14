package Hexaware.AbstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".user__address input")
	WebElement userAddress;
	
	@FindBy(css="section[class='ta-results list-group ng-star-inserted']")
	List<WebElement> adressesButtons;
	
	@FindBy(css=".actions a")
	WebElement placeOrderButton;
	
	By userAddressBy = By.cssSelector(".user__address input");
	By addressButtonBy = By.cssSelector("section[class='ta-results list-group ng-star-inserted']");
	
	public void introduceAddress(String address) {
		waitForElementToAppear(userAddressBy);
		userAddress.sendKeys(address);
	}
	
	public List<WebElement> getAllAddresses() {
		waitForElementToAppear(addressButtonBy);
		return adressesButtons;
	}
	
	public WebElement findSpecificAddress(String address) {
		return getAllAddresses().stream().filter(s->s.findElement(By.cssSelector("button span")).getText().equals(address)).findFirst().orElse(null);
	}
	
	public void chooseAddress(String address) {
		introduceAddress(address);
		findSpecificAddress(address).click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}

}
