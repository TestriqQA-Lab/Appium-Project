package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.json.JSONObject;
import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

public class TC_03_Checkout extends BaseTest {

    private Add_To_Cart_Page_Object addToCart;
    private Login_PageObject login;
    private String email, password, fullName, addressLine1, addressLine2, city, state, postalCode, country;
    private String cardHolderName, cardNumber, expiryDate, cvv;

    @BeforeClass
    @Parameters({"deviceName", "systemPort", "appiumPort", "appPath"})
    public void configuration(String deviceName, int systemPort, int appiumPort, String appPath) throws IOException {
        // Start Appium server and initialize the driver for the specific device
        startAppiumServerAndInitializeDriver(deviceName, systemPort, appiumPort, appPath);

        // Load credentials and checkout details from JSON
        loadCredentialsAndCheckoutDetails();

        // Initialize page objects
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object(driver);
    }

    @Test(priority = 1)
    public void CheckoutTest() {
        // Login to the application
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess(), "Login verification failed.");

        // Perform checkout
        addToCart.Checkout(fullName, addressLine1, addressLine2, city, state, postalCode, country);
        addToCart.AddCardDetails(cardHolderName, cardNumber, expiryDate, cvv);
        Assert.assertTrue(addToCart.orderPage(), "Order confirmation page verification failed.");

        // Logout from the application
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess(), "Logout verification failed.");
    }

    @AfterClass
    public void Teardown() {
        // Quit the driver session
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
