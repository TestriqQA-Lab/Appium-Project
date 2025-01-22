package testcases;

import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import AndroidUtils.AppiumUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void startAppiumServerAndInitializeDriver() {
        try {
            // Start the Appium server
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("C:\\Users\\Testriq\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .build();
            service.start();
            
            // Wait for server to start
            Thread.sleep(5000);  // Wait for 5 seconds to allow Appium server to start

            // Configure desired capabilities
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("AndroidDeviceName"); // Replace with your actual device name
            options.setApp("G:\\Automation Projects\\testriq\\src\\main\\java\\apps\\mda-2.2.0-25.apk"); // Replace with your app path
            options.setAppPackage("com.saucelabs.mydemoapp.android"); // Application package
            options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity"); // Main activity
            options.setCapability("autoGrantPermissions", true); // Auto grant permissions

            // Initialize the Android driver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            System.out.println("Appium driver initialized successfully.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid URL for AndroidDriver.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Appium session. Check configurations.");
        }
    }

    @AfterClass
    public void Teardown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
