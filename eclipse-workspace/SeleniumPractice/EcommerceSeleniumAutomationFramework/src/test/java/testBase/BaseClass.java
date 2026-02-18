package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    // 🔥 Single source of truth for WebDriver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected Logger logger;

    // ================= SETUP =================
    @BeforeClass(alwaysRun = true)
    @Parameters({ "os", "browser" })
    public void setup(
            @Optional("windows") String os,
            @Optional("chrome") String browser) {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting execution | OS: " + os + " | Browser: " + browser);

        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            webDriver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Invalid browser name: " + browser);
        }

        setDriver(webDriver);

        getDriver().manage().window().maximize();
        getDriver().get("https://sparklecartonline.com/");
    }

    // ================= TEARDOWN =================
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
                logger.info("Browser closed successfully");
            }
        } finally {
            unload();
        }
    }

    // ================= DRIVER MANAGEMENT =================
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException(
                    "WebDriver is null. Did you forget to call setDriver()?"
            );
        }
        return driver.get();
    }

    public static void unload() {
        driver.remove();
    }

    // ================= SCREENSHOT UTILITY =================
    public static String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());

        TakesScreenshot takesScreenshot =
                (TakesScreenshot) BaseClass.getDriver();

        File sourceFile =
                takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir")
                + "\\screenshots\\" + testName + "_" + timeStamp + ".png";

        File targetFile = new File(targetPath);

        // create screenshots folder if not exists
        targetFile.getParentFile().mkdirs();

        sourceFile.renameTo(targetFile);

        return targetPath;
    }

}
