package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URISyntaxException;
import org.testng.Assert;
import ecommerce.Project.Login_PageObject;

public class TC_01_Login extends BaseTest {

    Login_PageObject login;

    @BeforeClass
    public void configuration() throws InterruptedException, IOException, URISyntaxException {
        // Start the Appium server and initialize the driver
        startAppiumServerAndInitializeDriver();
        login = new Login_PageObject(driver);
    }
    @Test(priority = 1)
    public void LogintoTheapp() {
  	login.login("bod@example.com", "10203040");
  	Assert.assertTrue(login.verifyLoginSuccess());
  	login.logOut();
  	Assert.assertTrue(login.verifyLogOutSuccess());
  	
    }
//    @Test(priority = 1, dataProvider = "loginData")
//    public void LogintoTheapp(HashMap<String, String> input) {
//        // Perform login using provided input data
//    	login.login("bod@example.com", "10203040");
////        login.login(input.get("email"), input.get("password"));
//        login.logOut();
//    }

	@AfterClass
	public void Teardown() throws InterruptedException {
//		driver.close();
		driver.quit();
	}
}