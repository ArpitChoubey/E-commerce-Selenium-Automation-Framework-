package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ContactPage;
import utilities.Utilities;

public class ContactPageTest {

    WebDriver driver;
    ContactPage contactPage;
    Utilities utils;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sparklecartonline.com/pages/contact");

        contactPage = new ContactPage(driver);
        utils = new Utilities(driver);
        utils.waitForPageLoad();
    }

    @Test(priority = 1)
    
    public void verifySendButtonIsDisplayed() {
        Assert.assertTrue(contactPage.isSendButtonDisplayed(),
                "❌ Send button is not visible on Contact page");
    }

    @Test(priority = 2)
    public void submitContactForm() {

        contactPage.enterName("Arpit Choubey");
        contactPage.enterEmail("arpit@testmail.com");
        contactPage.enterPhone("9999999999");
        contactPage.enterComment("This is an automation test message.");

        contactPage.clickSend();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
