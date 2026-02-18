package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class HomePageTest extends BaseClass {

    HomePage homePage;

    @Test(priority = 1)
    public void captureSparkleCartLogo() {

        logger.info("Capturing SparkleCart logo screenshot");

        homePage = new HomePage(driver);
        homePage.captureLogoScreenshot("SparkleCart_Logo");

        logger.info("Logo screenshot captured successfully");
    }

    @Test(priority = 2)
    public void verifyLogoIsDisplayed() {

        logger.info("Verifying SparkleCart logo visibility");

        Assert.assertTrue(homePage.isLogoDisplayed(),
                "❌ SparkleCart logo is NOT displayed on Home Page");

        logger.info("SparkleCart logo is displayed");
    }

    @Test(priority = 3)
    public void verifyFAQsNavigation() {

        logger.info("Clicking FAQs link");

        homePage.clickFAQs();

        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("faq"),
                "❌ FAQs page not opened");

        logger.info("FAQs page opened successfully");
    }
}
