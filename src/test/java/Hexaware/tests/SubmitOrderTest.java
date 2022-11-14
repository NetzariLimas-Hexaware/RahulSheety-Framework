package Hexaware.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hexaware.AbstractComponents.CartPage;
import Hexaware.AbstractComponents.CheckOutPage;
import Hexaware.AbstractComponents.ConfirmationPage;
import Hexaware.AbstractComponents.OrderPage;
import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String email = "netzari_limas@hotmail.com";
	String password = "Angela13";
	String productName = "ZARA COAT 3";
	String address = "Mexico";
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException {		
		ProductCatalogue productCatalog = landingPage.login(input.get("email"), input.get("password"));
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCart();
		Assert.assertTrue(cartPage.verifyProductExists(input.get("product")));
		CheckOutPage chkout = cartPage.goToCheckOut();
		chkout.chooseAddress(address);
		ConfirmationPage confirmationPage = chkout.placeOrder();
		Assert.assertEquals(confirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalog = landingPage.login(email, password);
		OrderPage ordersPage = productCatalog.goToOrders();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	public String getScreeshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	//Extent Reports -> Excelent HTML Reports.
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Hexaware//data//PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "netzari_limas@hotmail.com");
//	map.put("password", "Angela13");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "netzari_limas@hotmail.com");
//	map1.put("password", "Angela13");
//	map1.put("product", "ADIDAS ORIGINAL");
}
