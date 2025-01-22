package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
<<<<<<< HEAD
    	// Start the Appium server and initialize the driver
        startAppiumServerAndInitializeDriver();
        
        // Load login credentials from the JSON file
        loadLoginCredentials();
=======
        // Start the Appium server and initialize the driver
>>>>>>> 35bb73d4c998bed77f81effd339389f177c7bbc3
        login = new Login_PageObject(driver);
    }

    @Test(priority = 1)
    public void LogintoTheapp() {
        // Use credentials loaded from JSON file
        login.login(email, password);
        Assert.assertTrue(login.verifyLoginSuccess());

<<<<<<< HEAD
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
=======
	@AfterClass
	public void Teardown() throws InterruptedException {
//		driver.close();
		driver.quit();
	}
>>>>>>> 35bb73d4c998bed77f81effd339389f177c7bbc3
}
