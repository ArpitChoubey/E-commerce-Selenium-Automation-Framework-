package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReport implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    private static Map<String, ExtentTest> suiteMap = new ConcurrentHashMap<>();

    // ================= EXTENT SETUP =================
    public synchronized static ExtentReports getExtent() {

        if (extent == null) {

            String timeStamp =
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                            .format(new Date());

            String reportPath =
                    System.getProperty("user.dir")
                            + "\\reports\\Extent-" + timeStamp + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Execution Summary");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentTest suite =
                getExtent().createTest(context.getSuite().getName());
        suiteMap.put(context.getName(), suite);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test =
                suiteMap.get(result.getTestContext().getName())
                        .createNode(result.getMethod().getMethodName());
        testNode.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testNode.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = testNode.get();
        test.log(Status.FAIL, result.getThrowable());

        try {
            String path =
                    BaseClass.captureScreen(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            test.warning("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testNode.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        getExtent().flush();
    }
}
