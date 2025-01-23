package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;

public class TC_02_Add_To_Cart extends BaseTest {

    private Add_To_Cart_Page_Object addToCart;
    private Login_PageObject login;

    // Variables to store login credentials
    private String email;
    private String password;

    @BeforeClass
    @Parameters({"deviceName", "systemPort", "appiumPort", "appPath"})
    public void configuration(String deviceName, int systemPort, int appiumPort, String appPath) throws IOException {
        // Start Appium server and initialize the driver for the specific device
        startAppiumServerAndInitializeDriver(deviceName, systemPort, appiumPort, appPath);

        // Load login credentials from the JSON file
        loadLoginCredentials();

        // Initialize page objects
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object(driver);
    }

    @Test(priority = 1)
    public void WithLoginAddToCartAndCheckout() {
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess(), "Login failed: Unable to verify login success.");

        addToCart.Withlogin();
        Assert.assertTrue(addToCart.verifyMyCartPage(), "Add to cart failed: My Cart page not displayed.");

        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess(), "Logout failed: Unable to verify logout success.");
    }

    @Test(priority = 2)
    public void WithoutLoginAddToCart() {
        addToCart.AddToCartWithoutlogin();
        Assert.assertTrue(addToCart.verifyMyCartPage(), "Add to cart without login failed: My Cart page not displayed.");
    }

    @Test(priority = 3)
    public void RemoveProductFromCart() {
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess(), "Login failed: Unable to verify login success.");

        addToCart.RemoveProductFromCart();
        Assert.assertTrue(addToCart.verifyNoItem(), "Remove product from cart failed: Cart is not empty.");

        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess(), "Logout failed: Unable to verify logout success.");
    }

    @AfterClass
    public void Teardown() {
        // Quit the driver session
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to load login credentials from the JSON file
    private void loadLoginCredentials() throws IOException {
        String filePath = "G:\\Automation Projects\\testriq\\src\\main\\java\\AndroidUtils\\testData.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(content);

        // Load login details from the JSON
        JSONObject loginDetails = jsonObject.getJSONObject("loginDetails");
        email = loginDetails.getString("email");
        password = loginDetails.getString("password");
    }
}
