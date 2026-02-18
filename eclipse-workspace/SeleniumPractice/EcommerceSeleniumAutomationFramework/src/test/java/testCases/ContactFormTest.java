package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.ExcelUtils;

public class ContactFormTest {

    WebDriver driver;
    ExcelUtils excel;

    @BeforeClass
    public void setup() throws IOException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sparklecartonline.com/pages/contact");

        excel = new ExcelUtils(
                "C:\\Users\\hi\\eclipse-workspace\\SeleniumPractice\\EcommerceSeleniumAutomationFramework\\TestData\\ContactTestData.xlsx",
                "ContactFormData");
    }

    @Test(groups = "sainty ")
    public void submitContactFormUsingExcelData() throws IOException, InterruptedException {

        int rows = excel.getRowCount();

        for (int i = 1; i <= rows; i++) {

            String name = excel.getCellData(i, 0);
            String email = excel.getCellData(i, 1);
            String phone = excel.getCellData(i, 2);
            String comment = excel.getCellData(i, 3);

            // Locate fields
            WebElement nameField = driver.findElement(By.id("ContactFormName"));
            WebElement emailField = driver.findElement(By.id("ContactFormEmail"));
            WebElement phoneField = driver.findElement(By.id("ContactFormPhone"));
            WebElement commentField = driver.findElement(By.id("ContactFormMessage"));
            WebElement sendButton = driver.findElement(By.xpath("//button[@type='submit']"));

            // Clear previous data
            nameField.clear();
            emailField.clear();
            phoneField.clear();
            commentField.clear();

            // Enter data
            nameField.sendKeys(name);
            emailField.sendKeys(email);
            phoneField.sendKeys(phone);
            commentField.sendKeys(comment);

            // Submit form
            sendButton.click();

            Thread.sleep(2000); // wait for submission response

            // Optional: refresh page for next iteration
            driver.navigate().refresh();
            Thread.sleep(2000);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
