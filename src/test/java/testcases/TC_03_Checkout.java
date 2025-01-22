package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
<<<<<<< HEAD
=======
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

>>>>>>> 35bb73d4c998bed77f81effd339389f177c7bbc3
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.json.JSONObject;
import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

<<<<<<< HEAD
public class TC_03_Checkout extends BaseTest {

    Add_To_Cart_Page_Object addToCart, cardDetail, checkOut;
    Login_PageObject login;

    String email, password, fullName, addressLine1, addressLine2, city, state, postalCode, country, 
           cardHolderName, cardNumber, expiryDate, cvv;

    @BeforeMethod
    public void configuration() throws IOException {
     // Start the Appium server and initialize the driver
        startAppiumServerAndInitializeDriver();
        
        // Load login credentials from the JSON file
        loadCredentialsAndCheckoutDetails();
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object(driver);
        cardDetail = new Add_To_Cart_Page_Object(driver);
        checkOut = new Add_To_Cart_Page_Object(driver);
    }

    @Test
    public void CheckoutTest() {
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess());

        addToCart.Checkout(fullName, addressLine1, addressLine2, city, state, postalCode, country);
        cardDetail.AddCardDetails(cardHolderName, cardNumber, expiryDate, cvv);

        Assert.assertTrue(checkOut.orderPage());
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess());
    }

    @AfterMethod
    public void Teardown() {
        driver.quit();
    }

    private void loadCredentialsAndCheckoutDetails() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("G:\\Automation Projects\\testriq\\src\\main\\java\\AndroidUtils\\testData.json")));
        JSONObject jsonObject = new JSONObject(content);

        // Load login details from the JSON
        JSONObject loginDetails = jsonObject.getJSONObject("loginDetails");
        email = loginDetails.getString("email");
        password = loginDetails.getString("password");

        // Load checkout details from the JSON
        JSONObject checkoutDetails = jsonObject.getJSONObject("checkoutDetails");
        fullName = checkoutDetails.getString("fullName");
        addressLine1 = checkoutDetails.getString("addressLine1");
        addressLine2 = checkoutDetails.getString("addressLine2");
        city = checkoutDetails.getString("city");
        state = checkoutDetails.getString("state");
        postalCode = checkoutDetails.getString("postalCode");
        country = checkoutDetails.getString("country");
        cardHolderName = checkoutDetails.getString("cardHolderName");
        cardNumber = checkoutDetails.getString("cardNumber");
        expiryDate = checkoutDetails.getString("expiryDate");
        cvv = checkoutDetails.getString("cvv");
    }
=======
public class TC_03_Checkout extends BaseTest{
	
	Add_To_Cart_Page_Object addToCart;
	Login_PageObject login;
	Add_To_Cart_Page_Object cardDetail;
	Add_To_Cart_Page_Object checkOut;
	
	@BeforeClass
    public void configuration() throws InterruptedException, IOException, URISyntaxException {
        // Pass the apkPath parameter to the AppiumServer method
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object (driver);
        cardDetail = new Add_To_Cart_Page_Object (driver);
        checkOut = new Add_To_Cart_Page_Object (driver);
    }
	
	@Test(priority = 1)
	public void Checkout() {
		login.login("bod@example.com", "10203040");
		Assert.assertTrue(login.verifyLoginSuccess());
		addToCart.Checkout("Rebecca Winter", "Mandorley 112", "Entrance 1", "Truro", "Cornwall", "89750", "United Kingdom");
		cardDetail.AddCardDetails("Rebecca Winter", "3258125675687891", "0325", "123");
		Assert.assertTrue(checkOut.orderPage());
	  	login.logOut();
	  	Assert.assertTrue(login.verifyLogOutSuccess());
	}

	@AfterClass
	public void Teardown() throws InterruptedException {
//		driver.close();
		driver.quit();
	}
>>>>>>> 35bb73d4c998bed77f81effd339389f177c7bbc3
}
