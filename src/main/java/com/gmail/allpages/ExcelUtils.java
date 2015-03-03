package com.gmail.allpages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Yurii
 * 
 */
public class ExcelUtils {

	public void createSheet() {
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("RegressionData");

		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", new Object[] { "Currency", "Finance", "Kurs", "Result" });
		data.put("2", new Object[] { "USD", null, null, null });
		data.put("3", new Object[] { "EUR", null, null, null });
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();

		int rownum = 0;
		for (String key : keyset) {
			// create a row of excelsheet
			Row row = sheet.createRow(rownum++);

			Object[] objArr = data.get(key);

			int cellnum = 0;

			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}
		try {
			// Write the workbook in file system

			FileOutputStream out = new FileOutputStream(new File(
					"C:\\RegressionData.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Creation sheet has been ompleted");
	}

	public void updateKurs(double kurs, String site, String currency) {

		try {
			FileInputStream file = new FileInputStream(new File(
					"C:\\RegressionData.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;
			// Update the value of cell

			if (site.equals("Finance") && currency.equals("USD")) {
				cell = sheet.getRow(1).getCell(1);
				cell.setCellValue(kurs);
				cell = sheet.getRow(1).getCell(3);
			} else if (site.equals("Finance") && currency.equals("EUR")) {
				cell = sheet.getRow(2).getCell(1);
				cell.setCellValue(kurs);
			} else if (site.equals("Kurs") && currency.equals("USD")) {
				cell = sheet.getRow(1).getCell(2);
				cell.setCellValue(kurs);
			} else if (site.equals("Kurs") && currency.equals("EUR")) {
				cell = sheet.getRow(2).getCell(2);
				cell.setCellValue(kurs);
			}

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(
					"C:\\RegressionData.xlsx"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// check percentage difference
	public boolean perKur(double fin, double kurs) {

		double formula = (kurs - fin) / fin * 100;
		boolean result = false;
		if (formula <= 30) {
			result = true;
		} else if (formula > 30) {
			result = false;
		}
		return result;
	}

	// add result of checking percentage to excel sheet
	public void result(String currency, boolean res) {
		try {
			FileInputStream file = new FileInputStream(new File(
					"C:\\RegressionData.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;
			if (!res) {
				switch (currency) {
				case "USD":
					cell = sheet.getRow(1).getCell(3);
					cell.setCellValue("False");
					System.out.println("USD False");
					break;
				case "EUR":
					cell = sheet.getRow(2).getCell(3);
					cell.setCellValue("False");
					System.out.println("EUR False");
					break;
				}
			} else if (res) {
				switch (currency) {
				case "USD":
					cell = sheet.getRow(1).getCell(3);
					cell.setCellValue("Passed");
					System.out.println("USD Passed");
					break;
				case "EUR":
					cell = sheet.getRow(2).getCell(3);
					cell.setCellValue("Passed");
					System.out.println("EUR Passed");
					break;
				}

			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(
					"C:\\RegressionData.xlsx"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

}