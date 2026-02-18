package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Utilities {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public Utilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===================== WAIT UTILITIES =====================

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ===================== ACTION UTILITIES =====================

    public void clickUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollByPixel(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    // ===================== SCREENSHOT UTILITIES =====================

 // Take full-page screenshot
    public String takeScreenshot(String testName) {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String destPath =
                "C:/Users/hi/eclipse-workspace/SeleniumPractice/" +
                "EcommerceSeleniumAutomationFramework/screenshots/"
                + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            Files.copy(src, new File(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
		return destPath;
    }

    // ===================== PAGE UTILITIES =====================

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
}

