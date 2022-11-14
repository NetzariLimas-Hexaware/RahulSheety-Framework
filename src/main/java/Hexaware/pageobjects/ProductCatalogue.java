package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productListBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getAllProduts(){
		waitForElementToAppear(productListBy);
		return productList;
	}
	
	public WebElement getProductByName(String productName) {
		return getAllProduts()
				.stream()
				.filter(s->s.findElement(By.cssSelector("b"))
						.getText()
						.equals(productName))
				.findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDissapear();
	}

}
