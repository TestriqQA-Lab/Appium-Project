package AndroidUtils;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {

    ExtentReports extent = ExtentReport.getReporterObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();  // ThreadLocal for thread safety
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);  // Set the ExtentTest object in the ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable()); // Log the failure in the report

        try {
            // Retrieve the driver instance from the test class
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            
            // Capture and attach screenshot
            String screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            extentTest.get().fail("Failed to access driver instance: " + e.getMessage());
        } catch (IOException e) {
            extentTest.get().fail("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext result) {
        extent.flush();  // Flush the extent report at the end of the test execution
    }
}
