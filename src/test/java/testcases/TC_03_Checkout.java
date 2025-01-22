package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.json.JSONObject;
import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

public class TC_03_Checkout extends BaseTest {

    Add_To_Cart_Page_Object addToCart, cardDetail, checkOut;
    Login_PageObject login;

    String email, password, fullName, addressLine1, addressLine2, city, state, postalCode, country, 
           cardHolderName, cardNumber, expiryDate, cvv;

    @BeforeClass
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

    @AfterClass
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
}
