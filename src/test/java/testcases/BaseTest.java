package testcases;

import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import AndroidUtils.AppiumUtils;
import java.io.File;

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
                    .usingPort(4725)
                    .build();
            
            System.out.println("Starting Appium Server...");
            service.start();
            
            if (!service.isRunning()) {
                throw new IllegalStateException("Appium server failed to start.");
            }
            
            System.out.println("Appium Server started successfully.");

            // Configure desired capabilities
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("AndroidDeviceName"); // Replace with your actual device name
            options.setApp("G:\\Automation Projects\\testriq\\src\\main\\java\\apps\\mda-2.2.0-25.apk"); // Replace with your app path
            options.setAppPackage("com.saucelabs.mydemoapp.android"); // Application package
            options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity"); // Main activity
            options.setCapability("autoGrantPermissions", true); // Auto grant permissions

            // Initialize the driver
            driver = new AndroidDriver(service.getUrl(), options);
            System.out.println("Driver initialized successfully.");
            
        } catch (Exception e) {
            System.err.println("Error while starting Appium server or initializing driver: " + e.getMessage());
            e.printStackTrace();
            if (service != null && service.isRunning()) {
                service.stop();
            }
            throw new RuntimeException("Failed to start Appium server or initialize driver.", e);
        }
    }

    @AfterClass
    public void teardown() {
        try {
            if (driver != null) {
                System.out.println("Quitting driver...");
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error while quitting driver: " + e.getMessage());
        }

        try {
            if (service != null && service.isRunning()) {
                System.out.println("Stopping Appium server...");
                service.stop();
            }
        } catch (Exception e) {
            System.err.println("Error while stopping Appium server: " + e.getMessage());
        }
    }
}
