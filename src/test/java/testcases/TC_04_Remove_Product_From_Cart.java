package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.Assert;
import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

public class TC_04_Remove_Product_From_Cart extends BaseTest{
	
	Add_To_Cart_Page_Object addToCart;
	Login_PageObject login;
	Add_To_Cart_Page_Object cardDetail;
	
	@BeforeClass
    public void configuration() throws InterruptedException, IOException, URISyntaxException {
        // Pass the apkPath parameter to the AppiumServer method
		startAppiumServerAndInitializeDriver();
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object (driver);
        cardDetail = new Add_To_Cart_Page_Object (driver);
    }
	
	@Test(priority = 1)
	public void RemoveProductFromCart() {
		login.login("bod@example.com", "10203040");
		addToCart.RemoveProductFromCart();
		Assert.assertTrue(addToCart.verifyNoItem());
	  	login.logOut();
	  	Assert.assertTrue(login.verifyLogOutSuccess());
	}

	@AfterClass
	public void Teardown() throws InterruptedException {
//		driver.close();
		driver.quit();
	}
}
