package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import AndroidUtils.AppiumUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    
    @BeforeTest
    public void startAppiumServerAndInitializeDriver() {
        try {
            // Start the Appium server
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .build();
            service.start();

            // Configure desired capabilities
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("AndroidDeviceName"); // Replace with your actual device name
            options.setApp("/Users/testriqqalab/eclipse-workspace/Appium-Project/src/main/java/apps/mda-2.2.0-25.apk"); // Replace with your app path
            options.setAppPackage("com.saucelabs.mydemoapp.android"); // Application package
            options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity"); // Main activity
            options.setCapability("autoGrantPermissions", true); // Auto grant permissions

            // Initialize the Android driver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid URL for AndroidDriver.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Appium session. Check configurations.");
        }
    }
    
    @AfterTest
	public void Teardown() throws InterruptedException {
//		driver.close();
    	if (service != null && service.isRunning()) {
            service.stop();
        }
	}
}
