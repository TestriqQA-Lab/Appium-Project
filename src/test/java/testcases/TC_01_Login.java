package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import ecommerce.Project.Login_PageObject;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TC_01_Login extends BaseTest {

    private Login_PageObject login;
    private String email;
    private String password;

    @BeforeClass
    @Parameters({"deviceName", "systemPort", "appiumPort", "appPath"})
    public void configuration(String deviceName, int systemPort, int appiumPort, String appPath) throws IOException {
        // Start Appium server and initialize the driver for the specific device
        startAppiumServerAndInitializeDriver(deviceName, systemPort, appiumPort, appPath);

        // Load login credentials from the JSON file
        loadLoginCredentials();

        // Initialize the page object
        login = new Login_PageObject(driver);
    }

    @Test(priority = 1)
    public void LogintoTheApp() {
        // Use credentials loaded from JSON file to login
        login.login(email, password);

        // Verify login success
        Assert.assertTrue(login.verifyLoginSuccess(), "Login verification failed.");

        // Log out and verify logout success
        login.logOut();
        Assert.assertTrue(login.verifyLogOutSuccess(), "Logout verification failed.");
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
