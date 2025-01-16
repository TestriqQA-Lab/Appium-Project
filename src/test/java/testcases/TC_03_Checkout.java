package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URISyntaxException;
import org.testng.Assert;
import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

public class TC_03_Checkout extends BaseTest{
	
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
	public void Checkout() {
		login.login("bod@example.com", "10203040");
		Assert.assertTrue(login.verifyLoginSuccess());
		addToCart.Checkout("Rebecca Winter", "Mandorley 112", "Entrance 1", "Truro", "Cornwall", "89750", "United Kingdom");
		cardDetail.AddCardDetails("Rebecca Winter", "3258125675687891", "0325", "123");
	  	login.logOut();
	  	Assert.assertTrue(login.verifyLogOutSuccess());
	}

	@AfterClass
	public void Teardown() throws InterruptedException {
//		driver.close();
		driver.quit();
	}
}
