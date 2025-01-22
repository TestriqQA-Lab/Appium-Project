package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

    @BeforeClass
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

    @AfterClass
    public void Teardown() throws InterruptedException {
        // Close the driver session
        driver.quit();
    }

    private void loadLoginCredentials() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("G:\\Automation Projects\\testriq\\src\\main\\java\\AndroidUtils\\testData.json")));
        JSONObject jsonObject = new JSONObject(content);

        // Load login details from the JSON
        JSONObject loginDetails = jsonObject.getJSONObject("loginDetails");
        email = loginDetails.getString("email");
        password = loginDetails.getString("password");
    }
}
