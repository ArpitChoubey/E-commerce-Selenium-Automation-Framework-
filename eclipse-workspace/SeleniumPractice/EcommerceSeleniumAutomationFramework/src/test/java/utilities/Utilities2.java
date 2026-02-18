package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Utilities2 {
	
	WebDriver driver;

    public void Utilities(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

    public String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            Files.copy(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
