package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URISyntaxException;

import ecommerce.Project.Add_To_Cart_Page_Object;
import ecommerce.Project.Login_PageObject;
import org.json.JSONObject;

public class TC_02_Add_To_Cart extends BaseTest {

    Add_To_Cart_Page_Object addToCart;
    Login_PageObject login;

    // Variables to store login credentials
    String email;
    String password;

    @BeforeClass
    public void configuration() throws InterruptedException, IOException, URISyntaxException {
    	// Start the Appium server and initialize the driver
        startAppiumServerAndInitializeDriver();
        
        // Load login credentials from the JSON file
        loadLoginCredentials();
        login = new Login_PageObject(driver);
        addToCart = new Add_To_Cart_Page_Object(driver);
    }

    @Test(priority = 1)
    public void WithLoginAddToCartAndCheckout() {
        // Use the credentials from the JSON file
        login.login(email, password);
        AssertJUnit.assertTrue(login.verifyLoginSuccess());
        addToCart.Withlogin();
        Assert.assertTrue(addToCart.verifyMyCartPage());
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess());
    }

    @Test(priority = 2)
    public void WithoutLoginAddToCart() {
        addToCart.AddToCartWithoutlogin();
        Assert.assertTrue(addToCart.verifyMyCartPage());
    }

    @Test(priority = 3)
    public void RemoveProductFromCart() {
        // Use the credentials from the JSON file
        login.login(email, password);
        addToCart.RemoveProductFromCart();
        Assert.assertTrue(addToCart.verifyNoItem());
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess());
    }

    @AfterClass
    public void Teardown() throws InterruptedException {
        // Close the driver session
        driver.quit();
    }

    // Method to load login credentials from the JSON file
    private void loadLoginCredentials() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("G:\\Automation Projects\\testriq\\src\\main\\java\\AndroidUtils\\testData.json")));
        JSONObject jsonObject = new JSONObject(content);

        // Load login details from the JSON
        JSONObject loginDetails = jsonObject.getJSONObject("loginDetails");
        email = loginDetails.getString("email");
        password = loginDetails.getString("password");
    }
}
