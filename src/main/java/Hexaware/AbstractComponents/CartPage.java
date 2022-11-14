package Hexaware.AbstractComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> cartList;
	
	@FindBy(css=".subtotal button")
	WebElement checkOutButton;
	
	By cartListBy = By.cssSelector(".cartSection");
	
	public List<WebElement> getCartItems() {
		waitForElementToAppear(cartListBy);
		return cartList;
	}
	
	public boolean verifyProductExists(String productName) {
		return getCartItems()
				.stream()
				.anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}
	
	public CheckOutPage goToCheckOut() {
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
	
	

}
