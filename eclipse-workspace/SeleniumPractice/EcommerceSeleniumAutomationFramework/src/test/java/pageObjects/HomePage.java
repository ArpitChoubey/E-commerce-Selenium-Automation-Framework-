package pageObjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

import testBase.BaseClass;

public class HomePage extends BaseClass {

    // Constructor
    public HomePage(WebDriver driver) {
        super();   // ✅ FIXED: pass driver to BasePage
        PageFactory.initElements(driver, this);
    }

    // ================== Web Elements ==================

    // SparkleCart Logo
    @FindBy(xpath = "//img[@alt='SparkleCart ']")
    private WebElement imgSparkleCartLogo;

    // FAQs link
    @FindBy(xpath = "//a[.//span[normalize-space()='FAQs']]")
    private WebElement lnkFAQs;

    // Track Order link
    @FindBy(xpath = "//a[.//span[normalize-space()='Track Order']]")
    private WebElement lnkTrackOrder;

    // Contact link
    @FindBy(xpath = "//a[.//span[normalize-space()='Contact']]")
    private WebElement lnkContact;

    // ================== Actions ==================

    public boolean isLogoDisplayed() {
        return isElementDisplayed(imgSparkleCartLogo);
    }

    private boolean isElementDisplayed(WebElement imgSparkleCartLogo2) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clickFAQs() {
        click(lnkFAQs);
    }

    private void click(WebElement lnkFAQs2) {
		// TODO Auto-generated method stub
		
	}

	public void clickTrackOrder() {
        click(lnkTrackOrder);
    }

    public void clickContact() {
        click(lnkContact);
    }
    public void captureLogoScreenshot(String fileName) {

        File src = imgSparkleCartLogo.getScreenshotAs(OutputType.FILE);

        String destPath =
            "C:/Users/hi/eclipse-workspace/SeleniumPractice/" +
            "EcommerceSeleniumAutomationFramework/screenshots/" +
            fileName + ".png";

        try {
            Files.copy(src, new File(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
