package AndroidUtils;

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
    }

    @Override
    public void onFinish(ITestContext result) {
        extent.flush();  // Flush the extent report at the end of the test execution
        String subject = "Appium Test Passed - Test Report";
        String reportFilePath = "G:\\Automation Projects\\testriq\\test-output\\emailable-report.html";  // Path to the generated HTML report
        String recipient = "prathamesh@testriq.com, prathamesh+1@testriq.com";  // Multiple recipients
        EmailUtility.sendEmailWithHTMLReport(subject, recipient, reportFilePath);
    }
    }
