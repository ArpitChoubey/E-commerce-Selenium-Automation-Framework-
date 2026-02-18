package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private String filePath;
    private String sheetName;

    public ExcelUtils(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
    }

    // Get total row count
    public int getRowCount() throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }

    // Get total column count
    public int getCellCount(int rowNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rowNum);
            return row.getLastCellNum();
        }
    }

    // Read cell data
    public String getCellData(int rowNum, int colNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rowNum);
            XSSFCell cell = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }

    // Write cell data
    public void setCellData(int rowNum, int colNum, String value) throws IOException {

        File file = new File(filePath);

        if (!file.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            workbook.createSheet(sheetName);
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
            fos.close();
        }

        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        XSSFCell cell = row.createCell(colNum);
        cell.setCellValue(value);

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);

        workbook.close();
        fis.close();
        fos.close();
    }
}

