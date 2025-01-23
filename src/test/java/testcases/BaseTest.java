package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.android.options.UiAutomator2Options;
import AndroidUtils.AppiumUtils;
import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    
    public void startAppiumServerAndInitializeDriver(String deviceName, int systemPort, int appiumPort, String appPath)
            throws MalformedURLException {
        
        // Start Appium server dynamically
        service = startAppiumServer("127.0.0.1", appiumPort);

        // Configure UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setUdid(deviceName); // Use device name as UDID for now
        options.setApp(appPath);
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.saucelabs.mydemoapp.android");
        options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
        options.setCapability("autoGrantPermissions", true);
        options.setSystemPort(systemPort); // Set unique system port for each device

        // Initialize AndroidDriver
        driver = new AndroidDriver(service.getUrl(), options);
    }
    
    @AfterClass
    public void Teardown() {
        // Quit the driver session
        if (driver != null) {
            driver.quit();
        }
    }
}
