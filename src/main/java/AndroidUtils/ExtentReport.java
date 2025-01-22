package AndroidUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentReport {

    static ExtentReports extent;

    public static ExtentReports getReporterObject() {
        // Ensure the reports directory exists
        String reportDirPath = System.getProperty("user.dir") + "\\reports";
        File reportDir = new File(reportDirPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Add timestamp to make report file name unique for each run
        String path = reportDirPath + "\\" + "index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Mobile Automation Results");
        reporter.config().setDocumentTitle("Testriq QA LAB");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Prathamesh M");
        extent.setSystemInfo("Device Name", getDeviceName());
        extent.setSystemInfo("Device Platform", getDevicePlatform());
        extent.setSystemInfo("Platform Version", getPlatformVersion());
        extent.setSystemInfo("App Version", getAppVersion());

        return extent;
    }

    private static String getDeviceName() {
        return "Google Pixel 7";
    }

    private static String getDevicePlatform() {
        return "Android";
    }

    private static String getPlatformVersion() {
        return "14.0";
    }

    private static String getAppVersion() {
        return "Test Build";
    }
}
