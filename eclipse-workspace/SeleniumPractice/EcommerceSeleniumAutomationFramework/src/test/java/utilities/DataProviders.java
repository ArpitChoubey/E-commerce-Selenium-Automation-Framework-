package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // ================================
    // Contact Form DataProvider
    // ================================

    @DataProvider(name = "ContactFormData")
    public String[][] getContactFormData() throws IOException {

        String path = "C:\\Users\\hi\\eclipse-workspace\\SeleniumPractice\\EcommerceSeleniumAutomationFramework\\TestData\\ContactTestData.xlsx";
        String sheetName = "ContactFormData";

        ExcelUtils excel = new ExcelUtils(path, sheetName);

        int totalRows = excel.getRowCount();      // excludes header
        int totalCols = excel.getCellCount(1);    // header column count

        String contactData[][] = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                contactData[i - 1][j] = excel.getCellData(i, j);
            }
        }
        return contactData;
    }

    // ================================
    // Future DataProviders (optional)
    // ================================

    // @DataProvider(name = "LoginData")
    // public String[][] getLoginData() throws IOException { }

}
