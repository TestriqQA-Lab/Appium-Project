package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import ecommerce.Project.Login_PageObject;
import org.json.JSONObject;

public class TC_01_Login extends BaseTest {

    Login_PageObject login;
    String email;
    String password;

    @BeforeMethod
    public void configuration() throws InterruptedException, IOException, URISyntaxException {
    	// Start the Appium server and initialize the driver
        startAppiumServerAndInitializeDriver();
        
        // Load login credentials from the JSON file
        loadLoginCredentials();
        login = new Login_PageObject(driver);
    }

    @Test(priority = 1)
    public void LogintoTheapp() {
        // Use credentials loaded from JSON file
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess());

        // Log out and verify logout success
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess());
    }

    @AfterMethod
    public void Teardown() throws InterruptedException {
        // Close the driver session
        driver.quit();
    }

    private void loadLoginCredentials() throws IOException {
        // Specify the relative path based on the project structure
        String path = "src/main/java/AndroidUtils/testData.json";

        // Read the content of the JSON file
        String content = new String(Files.readAllBytes(Paths.get(path)));

        // Parse the content as a JSONObject
        JSONObject jsonObject = new JSONObject(content);

        // Extract email and password from the JSON
        this.email = jsonObject.getString("email");
        this.password = jsonObject.getString("password");
    }
}
